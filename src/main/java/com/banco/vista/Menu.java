package com.banco.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.banco.util.AppTheme;
import java.awt.event.*;

public class Menu extends JDialog {
    private static final long serialVersionUID = 1L;
    private final Frame owner;

    public Menu(Frame owner) {
        super(owner, "Menú", true);
        this.owner = owner;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 259);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 155, 132, 0, 0};
        gbl.rowHeights = new int[]{20, 25, 25, 0, 20, 0};
        gbl.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var panel = new ImagePanel("/resources/LogoBancoMundial.png");
        var gbcPanel = new GridBagConstraints();
        gbcPanel.gridheight = 3;
        gbcPanel.insets = new Insets(0, 0, 5, 5);
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.gridx = 1;
        gbcPanel.gridy = 1;
        contentPane.add(panel, gbcPanel);

        var btnRegistro = crearBoton("Registro");
        btnRegistro.addActionListener(e -> {
            new Register(owner).setVisible(true);
        });
        var gbcRegistro = new GridBagConstraints();
        gbcRegistro.fill = GridBagConstraints.BOTH;
        gbcRegistro.insets = new Insets(0, 0, 5, 5);
        gbcRegistro.gridx = 2;
        gbcRegistro.gridy = 1;
        contentPane.add(btnRegistro, gbcRegistro);

        var btnLogin = crearBoton("Inicio de sesion");
        btnLogin.addActionListener(e -> {
            dispose();
            new Login(owner).setVisible(true);
        });
        var gbcLogin = new GridBagConstraints();
        gbcLogin.fill = GridBagConstraints.BOTH;
        gbcLogin.insets = new Insets(0, 0, 5, 5);
        gbcLogin.gridx = 2;
        gbcLogin.gridy = 2;
        contentPane.add(btnLogin, gbcLogin);

        var btnVolver = new JButton("Volver al Menu");
        btnVolver.setHorizontalAlignment(SwingConstants.CENTER);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnVolver.addActionListener(e -> dispose());
        btnVolver.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnVolver.setOpaque(true);
                btnVolver.setBackground(AppTheme.ROJO);
                btnVolver.setForeground(AppTheme.BLANCO);
            }
            public void mouseExited(MouseEvent e) {
                btnVolver.setOpaque(false);
                btnVolver.setBackground(AppTheme.FONDO_NORMAL);
                btnVolver.setForeground(AppTheme.NEGRO);
            }
        });
        var gbcVolver = new GridBagConstraints();
        gbcVolver.fill = GridBagConstraints.BOTH;
        gbcVolver.insets = new Insets(0, 0, 5, 5);
        gbcVolver.gridx = 2;
        gbcVolver.gridy = 3;
        contentPane.add(btnVolver, gbcVolver);

        setLocationRelativeTo(owner);
    }

    private JButton crearBoton(String texto) {
        var btn = new JButton(texto);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setFocusPainted(false);
        btn.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btn.setBackground(AppTheme.FONDO_NORMAL);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setOpaque(true);
                btn.setBackground(AppTheme.HOVER_VERDE);
            }
            public void mouseExited(MouseEvent e) {
                btn.setOpaque(false);
                btn.setBackground(AppTheme.VERDE_BOTON);
            }
        });
        return btn;
    }
}
