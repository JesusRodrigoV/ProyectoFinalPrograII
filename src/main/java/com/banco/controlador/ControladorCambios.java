package com.banco.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;

public class ControladorCambios {
    private static final Logger LOG = Logger.getLogger(ControladorCambios.class.getName());
    private final Connection conn;

    public ControladorCambios() throws SQLException {
        this.conn = ConexionBD.getInstance().getConnection();
    }

    public int idUsuario(int idCliente) {
        String query = """
            SELECT usuario.id_usuario
            FROM banco.usuario, banco.personas
            WHERE personas.id_usuario = usuario.id_usuario
            AND personas.id_cliente = ?
            """;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id_usuario");
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al obtener id_usuario", e);
        }
        return 0;
    }

    public String contraAntigua(int idCliente) {
        String query = "SELECT contrasena FROM banco.usuario WHERE id_usuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUsuario(idCliente));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getString("contrasena");
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al obtener contraseña antigua", e);
        }
        return null;
    }

    public boolean verificarContrasenaActual(int idCliente, String contrasenaIngresada) {
        String hash = contraAntigua(idCliente);
        if (hash == null) return false;
        return BCrypt.checkpw(contrasenaIngresada, hash);
    }

    public boolean cambiarContra(int idCliente, String nuevaContrasena) {
        int idUsuario = idUsuario(idCliente);
        if (idUsuario <= 0) return false;

        String hash = BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt());
        String query = "UPDATE usuario SET contrasena = ? WHERE id_usuario = ?";

        try {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, hash);
                pstmt.setInt(2, idUsuario);
                if (pstmt.executeUpdate() > 0) {
                    conn.commit();
                    LOG.info("Contraseña actualizada para usuario id: " + idUsuario);
                    return true;
                }
            }
            conn.rollback();
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { LOG.log(Level.WARNING, "Rollback fallido", ex); }
            LOG.log(Level.SEVERE, "Error al cambiar contraseña", e);
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { LOG.log(Level.WARNING, "Error restaurando autoCommit", e); }
        }
        return false;
    }

    public boolean cambiarUsuario(int idCliente, String nuevoUsuario) {
        int idUsuario = idUsuario(idCliente);
        if (idUsuario <= 0 || nuevoUsuario == null || nuevoUsuario.isBlank()) return false;

        String query = "UPDATE usuario SET usuario_nombre = ? WHERE id_usuario = ?";
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, nuevoUsuario);
                pstmt.setInt(2, idUsuario);
                if (pstmt.executeUpdate() > 0) {
                    conn.commit();
                    LOG.info("Usuario actualizado a: " + nuevoUsuario);
                    return true;
                }
            }
            conn.rollback();
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { LOG.log(Level.WARNING, "Rollback fallido", ex); }
            LOG.log(Level.SEVERE, "Error al cambiar usuario", e);
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { LOG.log(Level.WARNING, "Error restaurando autoCommit", e); }
        }
        return false;
    }
}
