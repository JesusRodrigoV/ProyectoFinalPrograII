package com.banco.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;

public class ControladorRegister {
    private static final Logger LOG = Logger.getLogger(ControladorRegister.class.getName());
    private final Connection conn;

    public ControladorRegister() throws SQLException {
        this.conn = ConexionBD.getInstance().getConnection();
    }

    public static String obtenerPlanBD(String nombrePlan) {
        return switch (nombrePlan) {
            case "Ahorro Junior" -> "junior";
            case "Ahorro Clásico" -> "clasica";
            case "Ahorro Senior" -> "senior";
            case "Ahorro Platino" -> "premium";
            default -> null;
        };
    }

    public static int edadCuenta(String plan) {
        return switch (plan) {
            case "junior", "clasica", "premium" -> 18;
            case "senior" -> 60;
            default -> 18;
        };
    }

    public boolean insertar(String apellidos, String nombres, String telefono,
                            String cedula, String plan, String fechaNac, String password) {
        if (apellidos == null || apellidos.isBlank() ||
            nombres == null || nombres.isBlank() ||
            telefono == null || telefono.isBlank() ||
            cedula == null || cedula.isBlank() ||
            password == null || password.isBlank() || plan == null) {
            return false;
        }

        int numCelular;
        try {
            numCelular = Integer.parseInt(telefono);
        } catch (NumberFormatException e) {
            LOG.warning("Teléfono inválido: " + telefono);
            return false;
        }

        String planBD = obtenerPlanBD(plan);
        if (planBD == null) return false;

        String usuario = cedula;
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());

        try {
            conn.setAutoCommit(false);

            int userId = insertarUsuario(usuario, hash);
            if (userId <= 0) throw new SQLException("Error al insertar usuario");

            int idCliente = insertarPersona(apellidos, nombres, cedula, numCelular, fechaNac, userId);
            if (idCliente <= 0) throw new SQLException("Error al insertar persona");

            int idCuenta = obtenerIdCuenta(planBD);
            if (idCuenta <= 0) throw new SQLException("Tipo de cuenta no encontrado");

            int idCuentaCliente = insertarCuentaCliente(idCuenta, idCliente);
            if (idCuentaCliente <= 0) throw new SQLException("Error al vincular cuenta");

            conn.commit();
            LOG.info("Cuenta creada: " + idCuentaCliente + " para usuario: " + usuario);
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
                LOG.log(Level.WARNING, "Transacción fallida, revertida", e);
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, "Error en rollback", ex);
            }
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.log(Level.WARNING, "Error restaurando autoCommit", e);
            }
        }
    }

    private int insertarUsuario(String usuario, String hash) throws SQLException {
        String query = "INSERT INTO banco.usuario (usuario_nombre, contrasena) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, usuario);
            pstmt.setString(2, hash);
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    private int insertarPersona(String apellidos, String nombres, String cedula,
                                int telefono, String fechaNac, int userId) throws SQLException {
        String query = "INSERT INTO banco.personas (apellidos, nombres, cedula, telefono, fecha_nacimiento, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, apellidos);
            pstmt.setString(2, nombres);
            pstmt.setString(3, cedula);
            pstmt.setInt(4, telefono);
            pstmt.setString(5, fechaNac);
            pstmt.setInt(6, userId);
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    private int obtenerIdCuenta(String plan) throws SQLException {
        String query = "SELECT id_cuenta FROM banco.cuentas WHERE nombre = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, plan);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id_cuenta");
            }
        }
        return -1;
    }

    private int insertarCuentaCliente(int idCuenta, int idCliente) throws SQLException {
        String query = "INSERT INTO cuentas_cliente (id_cuenta, id_cliente) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, idCuenta);
            pstmt.setInt(2, idCliente);
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }
}
