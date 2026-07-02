package com.banco.controlador;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorCajero {
    private static final Logger LOG = Logger.getLogger(ControladorCajero.class.getName());
    private final Connection conn;

    public ControladorCajero() throws SQLException {
        this.conn = ConexionBD.getInstance().getConnection();
    }

    public String obtenerNombreClientePorId(int idCliente) {
        String query = """
            SELECT personas.nombres
            FROM banco.personas
            WHERE personas.id_cliente = ?
            """;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getString("nombres");
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al obtener nombre", e);
        }
        return "";
    }

    public int obtenerIdCuentaCliente(int idCliente) {
        String query = """
            SELECT cuentas_cliente.id_cuenta_cliente
            FROM banco.cuentas_cliente, banco.personas
            WHERE personas.id_cliente = cuentas_cliente.id_cliente
            AND personas.id_cliente = ?
            """;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id_cuenta_cliente");
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al obtener id_cuenta_cliente", e);
        }
        return 0;
    }

    public double calcularSaldo(int idCuentaCliente) {
        double saldo = 0;
        String query = "SELECT monto, tipo FROM banco.transacciones WHERE id_cuenta_cliente = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idCuentaCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    double monto = rs.getDouble("monto");
                    String tipo = rs.getString("tipo");
                    saldo += switch (tipo) {
                        case "deposito" -> monto;
                        case "retiro" -> -monto;
                        default -> 0;
                    };
                }
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al calcular saldo", e);
        }
        return saldo;
    }

    public boolean depositar(int idCuentaCliente, double monto) {
        if (monto <= 0) return false;
        try {
            conn.setAutoCommit(false);
            String query = "INSERT INTO transacciones (monto, tipo, fecha, id_cuenta_cliente) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, monto);
                pstmt.setString(2, "deposito");
                pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setInt(4, idCuentaCliente);
                if (pstmt.executeUpdate() > 0) {
                    conn.commit();
                    LOG.info("Depósito exitoso: " + monto + " en cuenta " + idCuentaCliente);
                    return true;
                }
            }
            conn.rollback();
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { LOG.log(Level.WARNING, "Rollback fallido", ex); }
            LOG.log(Level.SEVERE, "Error en depósito", e);
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { LOG.log(Level.WARNING, "Error restaurando autoCommit", e); }
        }
        return false;
    }

    public boolean realizarTransferencia(int idCuentaOrigen, int idCuentaDestino, double monto) {
        if (monto <= 0 || idCuentaOrigen == idCuentaDestino) return false;
        double saldoActual = calcularSaldo(idCuentaOrigen);
        if (saldoActual < monto) return false;

        try {
            conn.setAutoCommit(false);

            String query = "INSERT INTO transacciones (monto, tipo, fecha, id_cuenta_cliente) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, monto);
                pstmt.setString(2, "retiro");
                pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setInt(4, idCuentaOrigen);
                if (pstmt.executeUpdate() <= 0) throw new SQLException("Error al registrar retiro");
            }

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, monto);
                pstmt.setString(2, "deposito");
                pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setInt(4, idCuentaDestino);
                if (pstmt.executeUpdate() <= 0) throw new SQLException("Error al registrar depósito");
            }

            conn.commit();
            LOG.info("Transferencia exitosa: " + monto + " de " + idCuentaOrigen + " a " + idCuentaDestino);
            return true;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) { LOG.log(Level.WARNING, "Rollback fallido", ex); }
            LOG.log(Level.SEVERE, "Error en transferencia", e);
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { LOG.log(Level.WARNING, "Error restaurando autoCommit", e); }
        }
        return false;
    }

    public static int idCuentaServicio(String servicio) {
        return switch (servicio) {
            case "Luz" -> 9;
            case "Agua" -> 8;
            case "Gas" -> 11;
            case "Internet" -> 10;
            default -> 0;
        };
    }
}
