package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorLogin;

import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private PanelConFondo contentPane;
    private JTextField usuario;
    private JPasswordField contrasena;
    private boolean contrasenaVisible = false;
    
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
        setBounds(100, 100, 450, 248);
        contentPane = new PanelConFondo("/resources/Banco Mundial.png");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{50, 70, 75, 75, 15, 30, 50, 0};
        gbl_contentPane.rowHeights = new int[]{166, 20, 21, 5, 23, 15, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
                
        JLabel lblNewLabel = new JLabel("Usuario:");
        lblNewLabel.setForeground(new Color(70, 121, 72));
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);
        
        usuario = new JTextField();
        usuario.setBackground(new Color(232, 253, 244));
        GridBagConstraints gbc_usuario = new GridBagConstraints();
        gbc_usuario.fill = GridBagConstraints.HORIZONTAL;
        gbc_usuario.insets = new Insets(0, 0, 5, 5);
        gbc_usuario.gridwidth = 2;
        gbc_usuario.gridx = 2;
        gbc_usuario.gridy = 1;
        contentPane.add(usuario, gbc_usuario);
        usuario.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Clave:");
        lblNewLabel_1.setForeground(new Color(70, 121, 72));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 2;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        contrasena = new JPasswordField();
        contrasena.setBackground(new Color(232, 253, 244));
        GridBagConstraints gbc_contrasena = new GridBagConstraints();
        gbc_contrasena.fill = GridBagConstraints.HORIZONTAL;
        gbc_contrasena.insets = new Insets(0, 0, 5, 5);
        gbc_contrasena.gridwidth = 2;
        gbc_contrasena.gridx = 2;
        gbc_contrasena.gridy = 2;
        contentPane.add(contrasena, gbc_contrasena);
        
        JButton btnNewButton_2 = new JButton();
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
/* 
        btnNewButton_2.setBorderPainted(false);
        btnNewButton_2.setContentAreaFilled(false);
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.setOpaque(false);
*/
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.gridx = 5;
        gbc_btnNewButton_2.gridy = 2;
        contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
        ImageIcon icono = imagen.getScaledImageIcon("/resources/eye.png", 20, 20);
        if (icono != null) {
        	btnNewButton_2.setIcon(icono);
        } else {
        	btnNewButton_2.setText("Sin imagen");
        }
        
        JButton btnNewButton_1 = new JButton("Salir");
        btnNewButton_1.setForeground(new Color(70, 121, 72));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        JButton btnNewButton = new JButton("Aceptar");
        btnNewButton.setForeground(new Color(70, 121, 72));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ControladorLogin log = new ControladorLogin(contrasena, usuario);
            	log.buscar();
                
            }
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 4;
        contentPane.add(btnNewButton, gbc_btnNewButton);
        
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.gridx = 3;
        gbc_btnNewButton_1.gridy = 4;
        contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
    }
    
    
}
