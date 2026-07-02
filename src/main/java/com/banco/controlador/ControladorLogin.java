package com.banco.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;

public class ControladorLogin {
    private static final Logger LOG = Logger.getLogger(ControladorLogin.class.getName());
    private final Connection conn;

    public ControladorLogin() throws SQLException {
        this.conn = ConexionBD.getInstance().getConnection();
    }

    public int buscar(String usuario, String contrasena) {
        if (usuario == null || usuario.isBlank()) {
            return -1;
        }
        String sql = "SELECT id_usuario, usuario_nombre, contrasena FROM banco.usuario WHERE usuario_nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashGuardado = rs.getString("contrasena");
                    if (BCrypt.checkpw(contrasena, hashGuardado)) {
                        LOG.info("Login exitoso: " + usuario);
                        return idCliente(usuario);
                    } else {
                        LOG.warning("Contraseña incorrecta para: " + usuario);
                        return -2;
                    }
                }
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error en login para: " + usuario, e);
        }
        LOG.warning("Usuario no encontrado: " + usuario);
        return -1;
    }

    public int idCliente(String usuario) {
        String query = """
            SELECT personas.id_cliente
            FROM banco.usuario, banco.personas
            WHERE personas.id_usuario = usuario.id_usuario
            AND usuario.usuario_nombre = ?
            """;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, usuario);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_cliente");
                }
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al obtener id_cliente", e);
        }
        return 0;
    }
}
