package Controlador;

import java.sql.*;
import java.time.LocalDateTime;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorCajero {
    private JTextField textFieldMonto;
    private JTextField usuario;
    private int id_cuenta;
    private Connection conn;
    public boolean realizado = false;

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

    public int idCuenta(int id_cliente) {
    	int id = 0;
    	try{
            String query = "SELECT cuentas_cliente.id_cuenta_cliente FROM banco.cuentas_cliente, banco.personas WHERE personas.id_cliente = cuentas_cliente.id_cliente "
            		+ "AND personas.id_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id_cliente);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                    	id = rs.getInt("id_cuenta_cliente");
                    }
                    return id;
                }
            } 
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        } 
    	return id;
    }

    public void deposito(double monto, int id_cuenta_cliente) {
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
    

    public void retiro(double monto, JComboBox servicio) {
        realizado = false;
        String ser = (String) servicio.getSelectedItem();
        switch(ser){
            case "Luz":
                id_cuenta = 9;
            case "Agua":
                id_cuenta = 8;
            case "Gas":
                id_cuenta = 11;
            case "Internet": 
                id_cuenta = 10;
        }
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
                        conn.commit();
                        JOptionPane.showMessageDialog(null, "Retiro exitoso");
                        realizado = true;
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
    
    public void transferencia(int id_cuenta_destino) {
        double monto = Double.parseDouble(textFieldMonto.getText());
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
                    	realizado = true;
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
                        
                        textFieldMonto.setText("");
                        System.out.println("Insertado");
                        realizado = true;
                        conn.commit();
                    } else {
                        realizado = false;
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

    public boolean hecho(){
        JOptionPane.showMessageDialog(null, "Transaccion Exitosa");
        return realizado;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }
    public void setTextFieldMonto(JTextField textFieldMonto) {
        this.textFieldMonto = textFieldMonto;
    }
    public void setUsuario(JTextField usuario) {
        this.usuario = usuario;
    }
    public int getId_cuenta() {
        return id_cuenta;
    }
    public JTextField getTextFieldMonto() {
        return textFieldMonto;
    }
    public JTextField getUsuario() {
        return usuario;
    }
    
}
