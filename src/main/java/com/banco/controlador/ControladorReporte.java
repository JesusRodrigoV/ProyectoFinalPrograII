package com.banco.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorReporte {
    private static final Logger LOG = Logger.getLogger(ControladorReporte.class.getName());
    private final Connection conn;

    public ControladorReporte() throws SQLException {
        this.conn = ConexionBD.getInstance().getConnection();
    }

    public Object[][] getReportData(int idCuentaCliente) {
        String query = "SELECT fecha, tipo, monto FROM banco.transacciones WHERE id_cuenta_cliente = ? ORDER BY fecha DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, idCuentaCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.last();
                int rowCount = rs.getRow();
                rs.beforeFirst();

                Object[][] data = new Object[rowCount][3];
                int i = 0;
                while (rs.next()) {
                    data[i][0] = rs.getString("fecha");
                    data[i][1] = rs.getString("tipo");
                    data[i][2] = rs.getDouble("monto");
                    i++;
                }
                return data;
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al obtener reporte de cuenta", e);
        }
        return new Object[0][];
    }

    public double saldo(int idCuentaCliente) {
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
}
