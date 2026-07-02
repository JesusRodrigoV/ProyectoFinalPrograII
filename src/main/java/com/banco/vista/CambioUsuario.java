package com.banco.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import com.banco.controlador.ControladorCambios;
import com.banco.util.AppTheme;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CambioUsuario extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CambioUsuario.class.getName());
    private final Frame owner;
    private final int id;
    private JTextField nombre;

    public CambioUsuario(Frame owner, int id) {
        super(owner, "Cambiar usuario", true);
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

        var lblTitulo = new JLabel("Usuario");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        var gbcTitulo = new GridBagConstraints();
        gbcTitulo.fill = GridBagConstraints.VERTICAL;
        gbcTitulo.insets = new Insets(0, 0, 5, 5);
        gbcTitulo.gridwidth = 5;
        gbcTitulo.gridx = 1;
        gbcTitulo.gridy = 1;
        contentPane.add(lblTitulo, gbcTitulo);

        var lblIngreso = new JLabel("Ingresar nuevo usuario");
        var gbcLbl = new GridBagConstraints();
        gbcLbl.anchor = GridBagConstraints.EAST;
        gbcLbl.gridwidth = 2;
        gbcLbl.insets = new Insets(0, 0, 5, 5);
        gbcLbl.gridx = 1;
        gbcLbl.gridy = 2;
        contentPane.add(lblIngreso, gbcLbl);

        nombre = new JTextField();
        nombre.setColumns(10);
        var gbcNombre = new GridBagConstraints();
        gbcNombre.gridwidth = 3;
        gbcNombre.insets = new Insets(0, 0, 5, 5);
        gbcNombre.fill = GridBagConstraints.HORIZONTAL;
        gbcNombre.gridx = 3;
        gbcNombre.gridy = 2;
        contentPane.add(nombre, gbcNombre);

        var btnAceptar = new JButton("Aceptar");
        btnAceptar.setHorizontalAlignment(SwingConstants.CENTER);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorder(new MatteBorder(1, 0, 0, 0, AppTheme.VERDE_OSCURO));
        btnAceptar.setBackground(AppTheme.FONDO_NORMAL);
        btnAceptar.addMouseListener(hoverAdapter(btnAceptar));
        btnAceptar.addActionListener(e -> {
            try {
                var control = new ControladorCambios();
                if (control.cambiarUsuario(id, nombre.getText())) {
                    JOptionPane.showMessageDialog(this, "Usuario actualizado con éxito.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar usuario");
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, "Error al cambiar usuario", ex);
                JOptionPane.showMessageDialog(this, "Error de conexión");
            }
        });
        var gbcAceptar = new GridBagConstraints();
        gbcAceptar.fill = GridBagConstraints.BOTH;
        gbcAceptar.gridwidth = 2;
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
        gbcCancelar.fill = GridBagConstraints.BOTH;
        gbcCancelar.gridx = 4;
        gbcCancelar.gridy = 3;
        contentPane.add(btnCancelar, gbcCancelar);

        setLocationRelativeTo(owner);
    }

    private MouseAdapter hoverAdapter(JButton btn) {
        return new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setOpaque(true);
                btn.setBackground(AppTheme.VERDE_BOTON);
                btn.setFont(new Font("Tahoma", Font.BOLD, 10));
            }
            public void mouseExited(MouseEvent e) {
                btn.setOpaque(false);
                btn.setBackground(AppTheme.FONDO_NORMAL);
                btn.setFont(new Font("Tahoma", Font.BOLD, 11));
            }
        };
    }
}
