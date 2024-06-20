package ejercicioCuenta;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private JTextField textFieldClave;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
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
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Usuario");
        lblNewLabel.setBounds(66, 41, 46, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Clave");
        lblNewLabel_1.setBounds(66, 81, 46, 14);
        contentPane.add(lblNewLabel_1);

        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(142, 38, 182, 20);
        contentPane.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        textFieldClave = new JTextField();
        textFieldClave.setBounds(142, 78, 182, 20);
        contentPane.add(textFieldClave);
        textFieldClave.setColumns(10);

        JButton btnNewButton = new JButton("Aceptar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = textFieldUsuario.getText();
                String clave = textFieldClave.getText();

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC", "root", "12345678")) {
                    String query = "SELECT * FROM usuario WHERE usuario_nombre = '" + usuario + "' AND contrasena = '" + clave + "'";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs.next()) {
                        int id = rs.getInt("id");
                        double monto = rs.getDouble("monto");
                        Cajero cajero = new Cajero(id, monto);
                        cajero.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o ccontrasena incorrecta ");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(142, 150, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Salir");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(255, 150, 89, 23);
        contentPane.add(btnNewButton_1);
    }
}
