package com.banco.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import com.banco.controlador.ControladorLogin;
import com.banco.util.AppTheme;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Login.class.getName());
    private final Frame owner;
    private JTextField usuario;
    private JPasswordField contrasena;
    private boolean contrasenaVisible;

    public Login(Frame owner) {
        super(owner, "Inicio de sesión", true);
        this.owner = owner;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 517, 293);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{15, 60, 80, 0, 40, 40, 30, 15, 0};
        gbl.rowHeights = new int[]{166, 24, 24, 30, 10, 0};
        gbl.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var panel = new ImagePanel("/resources/2.png");
        var gbcPanel = new GridBagConstraints();
        gbcPanel.gridwidth = 8;
        gbcPanel.insets = new Insets(0, 0, 5, 0);
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        contentPane.add(panel, gbcPanel);

        var lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(new Color(70, 121, 72));
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        var gbcLblUsuario = new GridBagConstraints();
        gbcLblUsuario.fill = GridBagConstraints.BOTH;
        gbcLblUsuario.insets = new Insets(0, 0, 5, 5);
        gbcLblUsuario.gridx = 1;
        gbcLblUsuario.gridy = 1;
        contentPane.add(lblUsuario, gbcLblUsuario);

        usuario = new JTextField();
        usuario.setBackground(AppTheme.FONDO_INPUT);
        usuario.setColumns(10);
        var gbcUsuario = new GridBagConstraints();
        gbcUsuario.fill = GridBagConstraints.BOTH;
        gbcUsuario.insets = new Insets(0, 0, 5, 5);
        gbcUsuario.gridwidth = 4;
        gbcUsuario.gridx = 2;
        gbcUsuario.gridy = 1;
        contentPane.add(usuario, gbcUsuario);

        var lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(new Color(70, 121, 72));
        lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
        var gbcLblContrasena = new GridBagConstraints();
        gbcLblContrasena.fill = GridBagConstraints.BOTH;
        gbcLblContrasena.insets = new Insets(0, 0, 5, 5);
        gbcLblContrasena.gridx = 1;
        gbcLblContrasena.gridy = 2;
        contentPane.add(lblContrasena, gbcLblContrasena);

        contrasena = new JPasswordField();
        contrasena.setBackground(AppTheme.FONDO_INPUT);
        var gbcPass = new GridBagConstraints();
        gbcPass.fill = GridBagConstraints.BOTH;
        gbcPass.insets = new Insets(0, 0, 5, 5);
        gbcPass.gridwidth = 4;
        gbcPass.gridx = 2;
        gbcPass.gridy = 2;
        contentPane.add(contrasena, gbcPass);

        var btnOjo = new JButton();
        btnOjo.addActionListener(e -> {
            contrasena.setEchoChar(contrasenaVisible ? '*' : (char) 0);
            contrasenaVisible = !contrasenaVisible;
        });
        btnOjo.setHorizontalAlignment(SwingConstants.LEFT);
        try {
            var img = ImageIO.read(getClass().getResourceAsStream("/eye.png"));
            if (img != null) {
                btnOjo.setIcon(new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            }
        } catch (IOException | NullPointerException ignored) {
        }
        var gbcOjo = new GridBagConstraints();
        gbcOjo.insets = new Insets(0, 0, 5, 5);
        gbcOjo.gridx = 6;
        gbcOjo.gridy = 2;
        contentPane.add(btnOjo, gbcOjo);

        var btnAceptar = new JButton("Aceptar");
        btnAceptar.setHorizontalAlignment(SwingConstants.CENTER);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorder(new MatteBorder(1, 0, 0, 0, AppTheme.VERDE_BOTON));
        btnAceptar.setBackground(AppTheme.VERDE_BOTON);
        btnAceptar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnAceptar.setBackground(AppTheme.HOVER_VERDE);
            }
            public void mouseExited(MouseEvent e) {
                btnAceptar.setBackground(AppTheme.VERDE_BOTON);
            }
        });
        btnAceptar.addActionListener(e -> autenticar());
        var gbcAceptar = new GridBagConstraints();
        gbcAceptar.fill = GridBagConstraints.BOTH;
        gbcAceptar.insets = new Insets(0, 0, 5, 5);
        gbcAceptar.gridx = 2;
        gbcAceptar.gridy = 3;
        contentPane.add(btnAceptar, gbcAceptar);

        var btnSalir = new JButton("Salir");
        btnSalir.setFocusPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorder(new MatteBorder(1, 0, 0, 0, AppTheme.ROJO));
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.addActionListener(e -> dispose());
        btnSalir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnSalir.setOpaque(true);
                btnSalir.setBackground(AppTheme.ROJO);
                btnSalir.setForeground(AppTheme.BLANCO);
            }
            public void mouseExited(MouseEvent e) {
                btnSalir.setOpaque(false);
                btnSalir.setBackground(AppTheme.FONDO_NORMAL);
                btnSalir.setForeground(AppTheme.NEGRO);
            }
        });
        var gbcSalir = new GridBagConstraints();
        gbcSalir.gridwidth = 2;
        gbcSalir.fill = GridBagConstraints.BOTH;
        gbcSalir.insets = new Insets(0, 0, 5, 5);
        gbcSalir.gridx = 4;
        gbcSalir.gridy = 3;
        contentPane.add(btnSalir, gbcSalir);

        // Enter key en password field
        contrasena.addActionListener(e -> autenticar());

        setLocationRelativeTo(owner);
    }

    private void autenticar() {
        String user = usuario.getText();
        String pass = new String(contrasena.getPassword());

        try {
            var login = new ControladorLogin();
            int id = login.buscar(user, pass);

            if (id > 0) {
                dispose();
                new Cajero(owner, id).setVisible(true);
            } else if (id == -2) {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta. Inténtelo de nuevo");
            } else {
                JOptionPane.showMessageDialog(this, "El usuario no existe");
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error de conexión en login", e);
            JOptionPane.showMessageDialog(this, "Error de conexión a la base de datos");
        }
    }
}
