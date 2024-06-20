package ejercicioCuenta;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reporte extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int id;
    private int id_cuenta;
    private JTextArea textArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Reporte frame = new Reporte(0, 0);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Reporte(int id, int id_cuenta) {
        this.id = id;
        this.id_cuenta = id_cuenta;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 323);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null); // Cambiar a null layout no recomendado

        JLabel lblNewLabel = new JLabel("Reporte de cuenta");
        lblNewLabel.setBounds(160, 11, 120, 14);
        contentPane.add(lblNewLabel);

        // Crear JTextArea
        textArea = new JTextArea();
        textArea.setEditable(false); // Hacer el JTextArea no editable
        textArea.setLineWrap(true); // Activar el ajuste automático de línea
        textArea.setWrapStyleWord(true); // Activar el ajuste de palabras
        // Crear JScrollPane y agregar el JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 36, 414, 214); // Establecer tamaño y posición del JScrollPane
        contentPane.add(scrollPane);
        
        JButton btnNewButton = new JButton("Salir");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnNewButton.setBounds(255, 260, 85, 21);
        contentPane.add(btnNewButton);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		pdf();
        	}
        });
        btnGuardar.setBounds(102, 260, 85, 21);
        contentPane.add(btnGuardar);

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/banco?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "admin";
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();
            String query = "SELECT fecha, tipo, monto FROM banco.transacciones WHERE id_cuenta_cliente = " + id_cuenta;
            double saldo = 0;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String fecha = rs.getString("fecha");
                String tipo = rs.getString("tipo");
                double elMonto = rs.getDouble("monto");
                switch (tipo) {
                case "deposito":
                    saldo += elMonto;
                    break;
                case "retiro":
                    saldo -= elMonto;
                    break;
            }
                // Agregar texto al JTextArea de manera acumulativa
                textArea.append("Fecha: " + fecha + "\nTipo: " + tipo + "\nMonto: " + elMonto + "\n\n");
            }
            textArea.append("Saldo: " + saldo);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener el reporte de cuenta.");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void pdf() {
    	
    }
}
