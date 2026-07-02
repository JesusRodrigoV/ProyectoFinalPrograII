package com.banco.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import com.banco.controlador.ControladorPerfil;
import com.banco.util.AppTheme;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Perfil extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Perfil.class.getName());
    private final Frame owner;
    private final int id;

    public Perfil(Frame owner, int id, int idCuenta) {
        super(owner, "Perfil", true);
        this.owner = owner;
        this.id = id;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 450, 300);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{20, 70, 100, 70, 20, 0};
        gbl.rowHeights = new int[]{33, 13, 20, 13, 13, 13, 13, 13, 25, 0, 0};
        gbl.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var btnCambio = new JButton("Cambiar datos");
        btnCambio.setHorizontalAlignment(SwingConstants.CENTER);
        btnCambio.setFocusPainted(false);
        btnCambio.setBorder(new MatteBorder(0, 0, 0, 0, AppTheme.VERDE_OSCURO));
        btnCambio.setBackground(AppTheme.FONDO_NORMAL);
        btnCambio.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnCambio.setOpaque(true);
                btnCambio.setForeground(AppTheme.ROJO);
            }
            public void mouseExited(MouseEvent e) {
                btnCambio.setOpaque(false);
                btnCambio.setForeground(AppTheme.NEGRO);
            }
        });
        btnCambio.addActionListener(e -> {
            String[] opciones = {"Usuario", "Contraseña"};
            int op = JOptionPane.showOptionDialog(this,
                "Seleccione una opción para cambiar:", "Cambio de datos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, opciones, opciones[0]);
            if (op == 0) {
                new CambioUsuario(owner, id).setVisible(true);
            } else if (op == 1) {
                new CambioContrasena(owner, id).setVisible(true);
            }
        });

        var gbcCambio = new GridBagConstraints();
        gbcCambio.anchor = GridBagConstraints.SOUTHEAST;
        gbcCambio.insets = new Insets(0, 0, 5, 5);
        gbcCambio.gridx = 3;
        gbcCambio.gridy = 0;
        contentPane.add(btnCambio, gbcCambio);

        var lblTitulo = new JLabel("Datos Personales");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(AppTheme.VERDE_OSCURO);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        var gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridwidth = 3;
        gbcTitulo.fill = GridBagConstraints.BOTH;
        gbcTitulo.insets = new Insets(0, 0, 5, 5);
        gbcTitulo.gridx = 1;
        gbcTitulo.gridy = 1;
        contentPane.add(lblTitulo, gbcTitulo);

        try {
            var control = new ControladorPerfil();
            var datos = control.datos(id);

            if (datos.size() >= 7) {
                agregarCampo("Nombres:", datos.get(3), 3, contentPane, gbl);
                agregarCampo("Apellidos:", datos.get(2), 4, contentPane, gbl);
                agregarCampo("Cedula:", datos.get(4), 5, contentPane, gbl);
                agregarCampo("Telefono:", datos.get(5), 6, contentPane, gbl);
                agregarCampo("Fecha Nacimiento:", datos.get(6), 7, contentPane, gbl);
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al cargar perfil", e);
        }

        var btnSalir = new JButton("Salir");
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(new MatteBorder(1, 0, 0, 0, AppTheme.ROJO));
        btnSalir.setBackground(AppTheme.FONDO_NORMAL);
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
        btnSalir.addActionListener(e -> dispose());
        var gbcSalir = new GridBagConstraints();
        gbcSalir.fill = GridBagConstraints.BOTH;
        gbcSalir.insets = new Insets(0, 0, 5, 5);
        gbcSalir.gridx = 2;
        gbcSalir.gridy = 8;
        contentPane.add(btnSalir, gbcSalir);

        setLocationRelativeTo(owner);
    }

    private void agregarCampo(String etiqueta, String valor, int y, JPanel panel, GridBagLayout gbl) {
        var lbl = new JLabel(etiqueta);
        lbl.setForeground(AppTheme.VERDE_OSCURO);
        var gbcLbl = new GridBagConstraints();
        gbcLbl.anchor = GridBagConstraints.EAST;
        gbcLbl.fill = GridBagConstraints.VERTICAL;
        gbcLbl.insets = new Insets(0, 0, 5, 5);
        gbcLbl.gridx = 1;
        gbcLbl.gridy = y;
        panel.add(lbl, gbcLbl);

        var txt = new JTextArea(valor);
        txt.setEditable(false);
        var gbcTxt = new GridBagConstraints();
        gbcTxt.gridwidth = 2;
        gbcTxt.insets = new Insets(0, 0, 5, 5);
        gbcTxt.fill = GridBagConstraints.BOTH;
        gbcTxt.gridx = 2;
        gbcTxt.gridy = y;
        panel.add(txt, gbcTxt);
    }
}
