package com.banco.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import com.banco.controlador.ControladorCambios;
import com.banco.modelo.Contrasena;
import com.banco.util.AppTheme;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CambioContrasena extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CambioContrasena.class.getName());
    private final Frame owner;
    private final int id;
    private JPasswordField passwordField;

    public CambioContrasena(Frame owner, int id) {
        super(owner, "Cambiar contraseña", true);
        this.owner = owner;
        this.id = id;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 374, 212);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{20, 80, 20, 30, 50, 80, 20, 0};
        gbl.rowHeights = new int[]{20, 13, 20, 20, 20, 0};
        gbl.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var lblTitulo = new JLabel("Contraseña");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        var gbcTitulo = new GridBagConstraints();
        gbcTitulo.fill = GridBagConstraints.VERTICAL;
        gbcTitulo.insets = new Insets(0, 0, 5, 5);
        gbcTitulo.gridwidth = 5;
        gbcTitulo.gridx = 1;
        gbcTitulo.gridy = 1;
        contentPane.add(lblTitulo, gbcTitulo);

        var lblNueva = new JLabel("Nueva Contraseña:");
        var gbcLbl = new GridBagConstraints();
        gbcLbl.gridwidth = 2;
        gbcLbl.fill = GridBagConstraints.HORIZONTAL;
        gbcLbl.insets = new Insets(0, 0, 5, 5);
        gbcLbl.gridx = 1;
        gbcLbl.gridy = 2;
        contentPane.add(lblNueva, gbcLbl);

        passwordField = new JPasswordField();
        var gbcPass = new GridBagConstraints();
        gbcPass.anchor = GridBagConstraints.NORTH;
        gbcPass.fill = GridBagConstraints.HORIZONTAL;
        gbcPass.insets = new Insets(0, 0, 5, 5);
        gbcPass.gridwidth = 3;
        gbcPass.gridx = 3;
        gbcPass.gridy = 2;
        contentPane.add(passwordField, gbcPass);

        var btnAceptar = new JButton("Aceptar");
        btnAceptar.setHorizontalAlignment(SwingConstants.CENTER);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorder(new MatteBorder(1, 0, 0, 0, AppTheme.VERDE_OSCURO));
        btnAceptar.setBackground(AppTheme.FONDO_NORMAL);
        btnAceptar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnAceptar.setOpaque(true);
                btnAceptar.setBackground(AppTheme.VERDE_BOTON);
            }
            public void mouseExited(MouseEvent e) {
                btnAceptar.setOpaque(false);
                btnAceptar.setBackground(AppTheme.FONDO_NORMAL);
            }
        });
        btnAceptar.addActionListener(e -> cambiar());
        var gbcAceptar = new GridBagConstraints();
        gbcAceptar.gridwidth = 2;
        gbcAceptar.anchor = GridBagConstraints.NORTHEAST;
        gbcAceptar.insets = new Insets(0, 0, 5, 5);
        gbcAceptar.gridx = 2;
        gbcAceptar.gridy = 3;
        contentPane.add(btnAceptar, gbcAceptar);

        var btnCancelar = new JButton("Cancelar");
        btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(new MatteBorder(1, 0, 0, 0, AppTheme.ROJO));
        btnCancelar.setBackground(AppTheme.FONDO_NORMAL);
        btnCancelar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnCancelar.setOpaque(true);
                btnCancelar.setForeground(AppTheme.ROJO);
            }
            public void mouseExited(MouseEvent e) {
                btnCancelar.setOpaque(false);
                btnCancelar.setForeground(AppTheme.NEGRO);
            }
        });
        btnCancelar.addActionListener(e -> dispose());
        var gbcCancelar = new GridBagConstraints();
        gbcCancelar.insets = new Insets(0, 0, 5, 5);
        gbcCancelar.anchor = GridBagConstraints.NORTH;
        gbcCancelar.fill = GridBagConstraints.HORIZONTAL;
        gbcCancelar.gridx = 4;
        gbcCancelar.gridy = 3;
        contentPane.add(btnCancelar, gbcCancelar);

        setLocationRelativeTo(owner);
    }

    private void cambiar() {
        String nueva = new String(passwordField.getPassword());
        var validador = new Contrasena(nueva);
        if (!validador.contraSegura()) {
            JOptionPane.showMessageDialog(this,
                "Contraseña no cumple los requisitos:\n" + validador.errores(),
                "Contraseña débil", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            var control = new ControladorCambios();
            if (control.cambiarContra(id, nueva)) {
                JOptionPane.showMessageDialog(this, "Contraseña actualizada");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar contraseña");
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al cambiar contraseña", ex);
            JOptionPane.showMessageDialog(this, "Error de conexión");
        }
    }
}
