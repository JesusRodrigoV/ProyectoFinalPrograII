package Controlador;

import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class ControladorCajero {
    /*
    private void deposito(double monto) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/banco?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", 
                    "root", 
                    "admin");
            conn.setAutoCommit(false);

            String query = "INSERT INTO transacciones (monto, tipo, fecha, id_cuenta_cliente) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, monto);
                pstmt.setString(2, "deposito");
                pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setInt(4, id_cuenta);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Se realizó la transaccion");
                    //textFieldMonto.setText("");
                    System.out.println("Insertado");
                    conn.commit();
                } else {
                    throw new SQLException("No se pudo insertar la transacción.");
                }
            } catch (SQLException ex) {
                if (conn != null) {
                    try {
                        conn.rollback();
                        System.out.println("Transacción fallida. Se han revertido los cambios.");
                    } catch (SQLException rollbackEx) {
                        rollbackEx.printStackTrace();
                    }
                }
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.setAutoCommit(true);
                        conn.close();
                    } catch (SQLException closeEx) {
                        closeEx.printStackTrace();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private void retiro(double monto) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/banco?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "root",
                    "admin");
            conn.setAutoCommit(false);

            double saldo = 0;
            String query = "SELECT monto, tipo FROM banco.transacciones WHERE id_cuenta_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id_cuenta);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        double elMonto = rs.getDouble("monto");
                        String tipo = rs.getString("tipo");
                        switch (tipo) {
                            case "deposito":
                                saldo += elMonto;
                                break;
                            case "retiro":
                                saldo -= elMonto;
                                break;
                        }
                    }
                }
            }

            if (saldo >= monto) {
                query = "INSERT INTO transacciones (monto, tipo, fecha, id_cuenta_cliente) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setDouble(1, monto);
                    pstmt.setString(2, "retiro");
                    pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                    pstmt.setInt(4, id_cuenta);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                    	JOptionPane.showMessageDialog(null, "Retiro exitoso");
                        conn.commit();
                    } else {
                        throw new SQLException("No se pudo insertar la transacción.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            }
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transacción fallida. Se han revertido los cambios.");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
    
    private double saldo() {
    	double saldo = 0;
    	return saldo;
    }
        */
}
