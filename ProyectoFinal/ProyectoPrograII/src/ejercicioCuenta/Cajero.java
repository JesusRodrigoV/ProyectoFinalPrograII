package ejercicioCuenta;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Cajero extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldMonto;
    private int id;
    private double montoActual;

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
    public Cajero(int id, double monto) {
        this.id = id;
        this.montoActual = monto;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Monto actual: " + montoActual);
        lblNewLabel.setBounds(10, 10, 200, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Monto");
        lblNewLabel_1.setBounds(76, 73, 46, 14);
        contentPane.add(lblNewLabel_1);

        textFieldMonto = new JTextField();
        textFieldMonto.setBounds(140, 70, 146, 20);
        contentPane.add(textFieldMonto);
        textFieldMonto.setColumns(10);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double montoDepositar = Double.parseDouble(textFieldMonto.getText());
                montoActual += montoDepositar;
                actualizarMonto();
                lblNewLabel.setText("Monto actual " + montoActual);
            }
        });
        btnDepositar.setBounds(30, 170, 120, 23);
        contentPane.add(btnDepositar);

        JButton btnRetirar = new JButton("Retirar");
        btnRetirar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double montoRetirar = Double.parseDouble(textFieldMonto.getText());
                if (montoActual >= montoRetirar) {
                    montoActual -= montoRetirar;
                    actualizarMonto();
                    lblNewLabel.setText("Monto actual: " + montoActual);
                } else {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                }
            }
        });
        btnRetirar.setBounds(180, 170, 120, 23);
        contentPane.add(btnRetirar);

        JButton btnReporte = new JButton("Reporte");
        btnReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reporte reporte = new Reporte(id);
                reporte.setVisible(true);
            }
        });
        btnReporte.setBounds(330, 170, 120, 23);
        contentPane.add(btnReporte);
    }

    private void actualizarMonto() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC", "root", "12345678")) {
            String query = "UPDATE transacciones inner join cuentas_cliente on cuentas_cliente.id_cuenta_cliente=transacciones.id_cuenta_cliente inner join cliente on cliente.id_cliente=cuentas_cliente.id_cliente inner join personas on personas.id_persona=cliente.id_persona inner join usuario on usuario.id_usuario=personas.id_usuario SET monto = " + montoActual + " WHERE id_usuario = " + id;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
