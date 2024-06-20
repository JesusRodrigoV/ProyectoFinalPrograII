package Vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class Cajero extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldMonto;
    private int id;
    private int id_cuenta;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cajero frame = new Cajero(0, 0);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Cajero(int id, int id_cuenta) {
        this.id = id;
        this.id_cuenta = id_cuenta;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 255, 228));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Monto");
        lblNewLabel_1.setBounds(76, 73, 46, 14);
        contentPane.add(lblNewLabel_1);

        textFieldMonto = new JTextField();
        textFieldMonto.setBackground(new Color(232, 253, 244));
        textFieldMonto.setBounds(140, 70, 146, 20);
        contentPane.add(textFieldMonto);
        textFieldMonto.setColumns(10);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setBackground(new Color(240, 240, 240));
        btnDepositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double montoDepositar = Double.parseDouble(textFieldMonto.getText());
                deposito(montoDepositar);
            }
        });
        btnDepositar.setBounds(76, 146, 120, 23);
        contentPane.add(btnDepositar);

        JButton btnRetirar = new JButton("Retirar");
        btnRetirar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	double montoDepositar = Double.parseDouble(textFieldMonto.getText());
                retiro(montoDepositar);
            }
        });
        btnRetirar.setBounds(245, 146, 120, 23);
        contentPane.add(btnRetirar);

        JButton btnReporte = new JButton("Reporte");
        btnReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reporte reporte = new Reporte(id, id_cuenta);
                reporte.setVisible(true);
            }
        });
        btnReporte.setBounds(76, 208, 120, 23);
        contentPane.add(btnReporte);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnSalir.setBounds(245, 208, 120, 23);
        contentPane.add(btnSalir);
        
        JLabel lblNewLabel = new JLabel("Transacciones");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(118, 23, 191, 13);
        contentPane.add(lblNewLabel);
    }
    
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
}
