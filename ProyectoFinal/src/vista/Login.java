package vista;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblUsername;
    public JTextField usuario;
    private JButton btnNewButton;
    private boolean isPasswordVisible = false;
    public JPasswordField contrasena;
    private JPanel pnlContenedorImagen;
    private ImageIcon icon;

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

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{46, 271, 41, 0};
        gridBagLayout.rowHeights = new int[]{98, 33, 37, 68, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gridBagLayout);

        lblUsername = new JLabel("Usuario:");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.anchor = GridBagConstraints.EAST;
        gbc_lblUsername.gridx = 0;
        gbc_lblUsername.gridy = 1;
        contentPane.add(lblUsername, gbc_lblUsername);

        usuario = new JTextField();
        GridBagConstraints gbc_usuario = new GridBagConstraints();
        gbc_usuario.insets = new Insets(0, 0, 5, 5);
        gbc_usuario.fill = GridBagConstraints.HORIZONTAL;
        gbc_usuario.gridx = 1;
        gbc_usuario.gridy = 1;
        contentPane.add(usuario, gbc_usuario);
        usuario.setColumns(10);

        JLabel lblPassword = new JLabel("Contraseña:");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 2;
        contentPane.add(lblPassword, gbc_lblPassword);

        contrasena = new JPasswordField();
        GridBagConstraints gbc_contrasena = new GridBagConstraints();
        gbc_contrasena.insets = new Insets(0, 0, 5, 5);
        gbc_contrasena.fill = GridBagConstraints.HORIZONTAL;
        gbc_contrasena.gridx = 1;
        gbc_contrasena.gridy = 2;
        contentPane.add(contrasena, gbc_contrasena);

        btnNewButton = new JButton("");
        ImageIcon ojo = new ImageIcon(getClass().getResource("/resources/eye.png"));
        
        btnNewButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                boton(ojo);
            }
        });

        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Color celesteClarito = new Color(173, 216, 230);
                btnNewButton.setBackground(celesteClarito);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton.setBackground(Color.WHITE);
            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isPasswordVisible) {
                    contrasena.setEchoChar('*');
                } else {
                    contrasena.setEchoChar((char) 0);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 2;
        contentPane.add(btnNewButton, gbc_btnNewButton);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.gridwidth = 3;
        gbc_btnLogin.gridx = 0;
        gbc_btnLogin.gridy = 3;
        contentPane.add(btnLogin, gbc_btnLogin);

        pnlContenedorImagen = new JPanel();
        GridBagConstraints gbc_pnlContenedorImagen = new GridBagConstraints();
        gbc_pnlContenedorImagen.gridwidth = 3;
        gbc_pnlContenedorImagen.insets = new Insets(0, 0, 5, 0);
        gbc_pnlContenedorImagen.fill = GridBagConstraints.BOTH;
        gbc_pnlContenedorImagen.gridx = 0;
        gbc_pnlContenedorImagen.gridy = 0;
        contentPane.add(pnlContenedorImagen, gbc_pnlContenedorImagen);

        icon = new ImageIcon(getClass().getResource("/resources/2.png"));
        pnlContenedorImagen.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                imagen();
            }
        });
    }

    public String contraIngresada() {
        char[] passwordChars = contrasena.getPassword();
        return new String(passwordChars);
    }

    public void buscar() {
        String sql = "";
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

            sql = "SELECT contrasena FROM banco.usuario WHERE usuario_nombre = '" + usuario.getText() + "'";
            buscar = stmt.executeQuery(sql);
            while (buscar.next()) {
                laContrasena = buscar.getString("contrasena");
            }
            if (laContrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El usuario no existe");
            } else {
                String texto = laContrasena.equals(contraIngresada()) ? "La contraseña es correcta" : "Contraseña incorrecta. Inténtelo de nuevo";
                JOptionPane.showMessageDialog(null, texto);
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

    public void imagen() {
        Dimension d = pnlContenedorImagen.getSize();
        int alto = d.height;
        int ancho = d.width;

        if (ancho > 0 && alto > 0) {
            Image img = icon.getImage();
            Image nuevaImagen = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            ImageIcon nuevoIcono = new ImageIcon(nuevaImagen);

            JLabel lbl = new JLabel();
            lbl.setIcon(nuevoIcono);
            lbl.setBounds(0, 0, ancho, alto);

            pnlContenedorImagen.removeAll();
            pnlContenedorImagen.add(lbl);
            pnlContenedorImagen.revalidate();
            pnlContenedorImagen.repaint();
        }
    }

    public void boton(ImageIcon icon) {
        int alto = 20;
        int ancho = 20;
        Image img = icon.getImage();
        Image nuevaImagen = img.getScaledInstance(ancho , alto, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono = new ImageIcon(nuevaImagen);
        btnNewButton.setIcon(nuevoIcono);
    }
}
