package ejercicioCuenta;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reporte extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int id;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Reporte frame = new Reporte(0);
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
    public Reporte(int id) {
        this.id = id;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Reporte de cuenta");
        lblNewLabel.setBounds(160, 11, 120, 14);
        contentPane.add(lblNewLabel);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 36, 414, 214);
        contentPane.add(textArea);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC", "root", "12345678");
            String query = "SELECT * FROM transacciones inner join cuentas_cliente on cuentas_cliente.id_cuenta_cliente=transacciones.id_cuenta_cliente inner join cliente on cliente.id_cliente=cuentas_cliente.id_cliente inner join personas on personas.id_persona=cliente.id_persona inner join usuario on usuario.id_usuario=personas.id_usuario WHERE id = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String nombre = rs.getString("usuario_nombre");
                String plan = rs.getString("tipo");
                double monto = rs.getDouble("monto");

                textArea.setText("Nombre: " + nombre + "\nPlan: " + plan + "\nMonto: " + monto);
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
