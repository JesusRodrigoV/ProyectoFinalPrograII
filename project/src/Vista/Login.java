package Vista;

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
import java.awt.Font;
import java.awt.Color;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usuario;
    private JPasswordField contrasena;
    private boolean contrasenaVisible = false;
    private JTextField numCuenta;
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
        contentPane.setBackground(new Color(204, 255, 228));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Usuario");
        lblNewLabel.setBounds(66, 41, 66, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Clave");
        lblNewLabel_1.setBounds(66, 81, 66, 14);
        contentPane.add(lblNewLabel_1);

        usuario = new JTextField();
        usuario.setBackground(new Color(232, 253, 244));
        usuario.setBounds(142, 38, 182, 20);
        contentPane.add(usuario);
        usuario.setColumns(10);
        
        contrasena = new JPasswordField();
        contrasena.setBackground(new Color(232, 253, 244));
        contrasena.setBounds(142, 79, 182, 19);
        contentPane.add(contrasena);

        JButton btnNewButton = new JButton("Aceptar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
        btnNewButton.setBounds(142, 165, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Salir");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(255, 165, 89, 23);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Mostrar");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (contrasenaVisible) {
                    contrasena.setEchoChar('*');
                } else {
                    contrasena.setEchoChar((char) 0);
                }
        		contrasenaVisible = !contrasenaVisible;
        	}
        });
        btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
        btnNewButton_2.setBounds(341, 78, 59, 21);
        contentPane.add(btnNewButton_2);
        
        JLabel lblNewLabel_1_1 = new JLabel("Num. Cuenta");
        lblNewLabel_1_1.setBounds(66, 120, 66, 14);
        contentPane.add(lblNewLabel_1_1);
        
        numCuenta = new JTextField();
        numCuenta.setBackground(new Color(232, 253, 244));
        numCuenta.setColumns(10);
        numCuenta.setBounds(142, 118, 182, 20);
        contentPane.add(numCuenta);
    }
    
    public String contraIngresada() {
        char[] passwordChars = contrasena.getPassword();
        return new String(passwordChars);
    }

    public void buscar() {
        String sql = "";
        int id = 0;
        ResultSet buscar = null;
        Statement stmt = null;
        Connection conn = null;
        String laContrasena = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/banco?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "admin";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            sql = "SELECT * FROM banco.usuario WHERE usuario_nombre = '" + usuario.getText() + "'";
            buscar = stmt.executeQuery(sql);
            while (buscar.next()) {
            	id = buscar.getInt("id_usuario");
            	String usuario = buscar.getString("usuario_nombre");
                laContrasena = buscar.getString("contrasena");
            }
            
            
            if (laContrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El usuario no existe");
            } else {
                boolean ver = laContrasena.equals(contraIngresada());
                
                if (ver) {
                	int id_client = 0;
                	sql = "SELECT id_cliente FROM banco.personas WHERE id_usuario = "+ id ;
                    buscar = stmt.executeQuery(sql);
                    while (buscar.next()) {
                    	id_client = buscar.getInt("id_cliente");
                        
                    }
                	
                	sql = "SELECT * FROM banco.cuentas_cliente WHERE id_cuenta_cliente = " + Integer.parseInt(numCuenta.getText()) + " AND id_cliente = "+ id_client ;
                	int id_cuenta_client = 0;
                    buscar = stmt.executeQuery(sql);
                    while (buscar.next()) {
                    	id_cuenta_client = buscar.getInt("id_cuenta_cliente");
                    	int tipocuenta = buscar.getInt("id_cuenta");
                        int id_cliente = buscar.getInt("id_cliente");
                        
                    }
                    if (id_cuenta_client == 0) {
                    	JOptionPane.showMessageDialog(null, "La cuenta no existe");
                    }else{
                    	Cajero cajero = new Cajero(id, id_cuenta_client);
                        cajero.setVisible(true);
                        dispose();
                    }
                    
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Inténtelo de nuevo");
                }
            }
            
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar archivo");
            }
        }
    }
}
