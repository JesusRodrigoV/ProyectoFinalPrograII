package Controlador;

import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorCajero {
    private JTextField textFieldMonto;
    private JTextField usuario;
    private int id_cuenta;
    private Connection conn;

    public ControladorCajero (int id_cuenta, JTextField textFieldMonto, JTextField usuario){
        this.id_cuenta = id_cuenta;
        this.textFieldMonto = textFieldMonto;
        this.usuario = usuario;
        try {
            this.conn = ConexionBD.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String nombre() {
    	String nombre = "";
    	try{
            String query = "SELECT personas.nombres FROM banco.usuario, banco.personas WHERE personas.id_usuario = usuario.id_usuario "
            		+ "AND usuario.usuario_nombre = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, usuario.getText());
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                    	nombre = rs.getString("nombres");
                    }
                    return nombre;
                }
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        } 
    	return nombre;
    }
    public void deposito(double monto) {
        try {
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
                    textFieldMonto.setText("");
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
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Class error");
        } 
    }
    
    public void retiro(double monto) {
        try {
            conn.setAutoCommit(false);
            if (saldo() >= monto) {
                String query = "INSERT INTO transacciones (monto, tipo, fecha, id_cuenta_cliente) VALUES (?, ?, ?, ?)";
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
        } 
    }
    
    public void transferencia(double monto, int id_cuenta_destino) {
        try {
            conn.setAutoCommit(false);
            if (saldo() >= monto) {
                String query = "INSERT INTO transacciones (monto, tipo, fecha, id_cuenta_cliente) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setDouble(1, monto);
                    pstmt.setString(2, "retiro");
                    pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                    pstmt.setInt(4, id_cuenta);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                    	JOptionPane.showMessageDialog(null, "Transaccion exitosa");
                        conn.commit();
                    } else {
                        throw new SQLException("No se pudo insertar la transacción.");
                    }
                }
                query = "INSERT INTO transacciones (monto, tipo, fecha, id_cuenta_cliente) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setDouble(1, monto);
                    pstmt.setString(2, "deposito");
                    pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                    pstmt.setInt(4, id_cuenta_destino);
    
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Se realizó la transaccion");
                        textFieldMonto.setText("");
                        System.out.println("Insertado");
                        conn.commit();
                    } else {
                        throw new SQLException("No se pudo insertar la transacción.");
                    }
                } 
            }else {
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
        } 
    }

    public double saldo() {
    	double saldo = 0;
        try{
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
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        } 
    	return saldo;
    }
}
