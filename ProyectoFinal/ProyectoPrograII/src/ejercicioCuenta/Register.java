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
import javax.swing.ButtonGroup;

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNombre;
    private JTextField textFieldClave;
    private JRadioButton radioCuentaJoven;
    private JRadioButton radioCuentaClasica;
    private JRadioButton radioCuentaMayores;
    private JRadioButton radioCuentaPremium;
    private ButtonGroup planGroup;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register frame = new Register();
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
    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nombre");
        lblNewLabel.setBounds(29, 65, 46, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_3 = new JLabel("Clave");
        lblNewLabel_3.setBounds(29, 103, 46, 14);
        contentPane.add(lblNewLabel_3);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(103, 62, 147, 20);
        contentPane.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        textFieldClave = new JTextField();
        textFieldClave.setBounds(103, 100, 147, 20);
        contentPane.add(textFieldClave);
        textFieldClave.setColumns(10);

        JButton btnNewButton = new JButton("Crear");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombre.getText();
                String clave = textFieldClave.getText();
                String plan = obtenerPlanSeleccionado();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC", "root", "12345678");
                    String query = "INSERT INTO transacciones inner join cuentas_cliente on cuentas_cliente.id_cuenta_cliente=transacciones.id_cuenta_cliente inner join cliente on cliente.id_cliente=cuentas_cliente.id_cliente inner join personas on personas.id_persona=cliente.id_persona inner join usuario on usuario.id_usuario=personas.id_usuario (nombre, contrasena, tipo, monto) VALUES ('" + nombre + "', '" + clave + "', '" + plan + "', 0)";
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                    stmt.close();
                    conn.close();
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                dispose();
            }
        });
        btnNewButton.setBounds(323, 61, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Salir");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(323, 99, 89, 23);
        contentPane.add(btnNewButton_1);

        planGroup = new ButtonGroup();

        radioCuentaJoven = new JRadioButton("Cuenta joven");
        radioCuentaJoven.setBounds(280, 168, 109, 23);
        contentPane.add(radioCuentaJoven);
        planGroup.add(radioCuentaJoven);

        radioCuentaClasica = new JRadioButton("Cuenta clásica");
        radioCuentaClasica.setBounds(6, 168, 109, 23);
        contentPane.add(radioCuentaClasica);
        planGroup.add(radioCuentaClasica);

        radioCuentaMayores = new JRadioButton("Cuenta mayores");
        radioCuentaMayores.setBounds(6, 214, 109, 23);
        contentPane.add(radioCuentaMayores);
        planGroup.add(radioCuentaMayores);

        radioCuentaPremium = new JRadioButton("Cuenta premium");
        radioCuentaPremium.setBounds(280, 214, 109, 23);
        contentPane.add(radioCuentaPremium);
        planGroup.add(radioCuentaPremium);

        JLabel lblNewLabel_1 = new JLabel("Seleccionar plan para registro");
        lblNewLabel_1.setBounds(150, 11, 190, 14);
        contentPane.add(lblNewLabel_1);
    }

    private String obtenerPlanSeleccionado() {
        if (radioCuentaJoven.isSelected()) {
            return "Joven";
        } else if (radioCuentaClasica.isSelected()) {
            return "Clasica";
        } else if (radioCuentaMayores.isSelected()) {
            return "Mayores";
        } else if (radioCuentaPremium.isSelected()) {
            return "Premium";
        } else {
            return "Clasica";
        }
    }
}
