package com.banco.vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import com.banco.util.AppTheme;

public class MenuPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton selectedButton;

    public MenuPrincipal() {
        setTitle("Banco Mundial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 517, 293);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{20, 80, 50, 20, 0};
        gbl.rowHeights = new int[]{20, 0, 20, 30, 5, 30, 5, 30, 0};
        gbl.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var lblTitulo = new JLabel("Bienvenido");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        var gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridwidth = 2;
        gbcTitulo.insets = new Insets(0, 0, 5, 5);
        gbcTitulo.gridx = 1;
        gbcTitulo.gridy = 1;
        contentPane.add(lblTitulo, gbcTitulo);

        var panel = new ImagePanel("/resources/LogoBancoMundial.png");
        var gbcPanel = new GridBagConstraints();
        gbcPanel.gridwidth = 2;
        gbcPanel.gridheight = 6;
        gbcPanel.insets = new Insets(0, 0, 0, 5);
        gbcPanel.fill = GridBagConstraints.BOTH;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 2;
        contentPane.add(panel, gbcPanel);

        var btnClientes = crearBotonNav("Clientes");
        btnClientes.addActionListener(e -> {
            seleccionar(btnClientes);
            new Menu(this).setVisible(true);
            dispose();
        });
        var gbcClientes = new GridBagConstraints();
        gbcClientes.insets = new Insets(0, 0, 5, 5);
        gbcClientes.fill = GridBagConstraints.BOTH;
        gbcClientes.gridx = 2;
        gbcClientes.gridy = 5;
        contentPane.add(btnClientes, gbcClientes);

        var btnCuentas = crearBotonNav("Cuentas");
        btnCuentas.addActionListener(e -> {
            seleccionar(btnCuentas);
            new Planes(this).setVisible(true);
            dispose();
        });
        var gbcCuentas = new GridBagConstraints();
        gbcCuentas.fill = GridBagConstraints.BOTH;
        gbcCuentas.insets = new Insets(0, 0, 5, 5);
        gbcCuentas.gridx = 2;
        gbcCuentas.gridy = 3;
        contentPane.add(btnCuentas, gbcCuentas);
    }

    private JButton crearBotonNav(String texto) {
        var btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (btn != selectedButton) {
                    btn.setOpaque(true);
                    btn.setBackground(AppTheme.VERDE_BOTON);
                    btn.setFont(new Font("Tahoma", Font.BOLD, 10));
                }
            }
            public void mouseExited(MouseEvent e) {
                if (btn != selectedButton) {
                    btn.setOpaque(false);
                    btn.setBackground(AppTheme.BLANCO);
                    btn.setFont(new Font("Tahoma", Font.BOLD, 11));
                }
            }
        });
        return btn;
    }

    private void seleccionar(JButton btn) {
        if (selectedButton != null) {
            selectedButton.setOpaque(false);
            selectedButton.setBackground(AppTheme.BLANCO);
            selectedButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        }
        btn.setOpaque(true);
        btn.setBackground(AppTheme.VERDE_BOTON);
        btn.setFont(new Font("Tahoma", Font.BOLD, 10));
        selectedButton = btn;
    }
}
