package com.banco.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import com.banco.controlador.ControladorCajero;
import com.banco.util.AppTheme;
import java.awt.event.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cajero extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Cajero.class.getName());
    private final Frame owner;
    private final int id;
    private int idCuenta;

    public Cajero(Frame owner, int id) {
        super(owner, "Cajero", true);
        this.owner = owner;
        this.id = id;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 687, 300);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{20, 125, 125, 125, 125, 20, 0};
        gbl.rowHeights = new int[]{10, 30, 35, 30, 23, 33, 23, 0};
        gbl.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var logo = new ImagePanel("/resources/logoMejor.png");
        var gbcLogo = new GridBagConstraints();
        gbcLogo.gridheight = 3;
        gbcLogo.insets = new Insets(0, 0, 5, 5);
        gbcLogo.fill = GridBagConstraints.BOTH;
        gbcLogo.gridx = 1;
        gbcLogo.gridy = 1;
        contentPane.add(logo, gbcLogo);

        var lblBienvenido = new JLabel("Bienvenido ");
        lblBienvenido.setForeground(AppTheme.VERDE_OSCURO);
        lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        var gbcBienvenido = new GridBagConstraints();
        gbcBienvenido.anchor = GridBagConstraints.WEST;
        gbcBienvenido.gridheight = 3;
        gbcBienvenido.fill = GridBagConstraints.VERTICAL;
        gbcBienvenido.insets = new Insets(0, 0, 5, 5);
        gbcBienvenido.gridx = 2;
        gbcBienvenido.gridy = 1;
        contentPane.add(lblBienvenido, gbcBienvenido);

        var lblNombre = new JLabel("");
        lblNombre.setForeground(AppTheme.VERDE_OSCURO);
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
        var gbcNombre = new GridBagConstraints();
        gbcNombre.fill = GridBagConstraints.BOTH;
        gbcNombre.insets = new Insets(0, 0, 5, 5);
        gbcNombre.gridx = 3;
        gbcNombre.gridy = 2;
        contentPane.add(lblNombre, gbcNombre);

        try {
            var control = new ControladorCajero();
            idCuenta = control.obtenerIdCuentaCliente(id);
            String nombre = control.obtenerNombreClientePorId(id);
            lblNombre.setText(nombre);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al cargar datos del cajero", e);
        }

        var btnTransferencias = crearBoton("Transferencias", AppTheme.VERDE_OSCURO);
        btnTransferencias.addActionListener(e -> {
            new Transferencia(owner, idCuenta).setVisible(true);
        });

        var btnPagos = crearBoton("Pagos", AppTheme.VERDE_OSCURO);
        btnPagos.addActionListener(e -> {
            new Pagos(owner, idCuenta).setVisible(true);
        });

        var btnReportes = crearBoton("Reportes", AppTheme.VERDE_OSCURO);
        btnReportes.addActionListener(e -> {
            new Reporte(owner, idCuenta).setVisible(true);
        });

        var btnPerfil = crearBoton("Perfil", AppTheme.VERDE_OSCURO);
        btnPerfil.addActionListener(e -> {
            new Perfil(owner, id, idCuenta).setVisible(true);
        });

        var btnSalir = new JButton("Cerrar Sesion");
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(new MatteBorder(0, 0, 0, 0, AppTheme.VERDE_OSCURO));
        btnSalir.setBackground(AppTheme.FONDO_NORMAL);
        btnSalir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnSalir.setOpaque(true);
                btnSalir.setForeground(AppTheme.ROJO);
            }
            public void mouseExited(MouseEvent e) {
                btnSalir.setOpaque(false);
                btnSalir.setForeground(AppTheme.NEGRO);
            }
        });
        btnSalir.addActionListener(e -> {
            dispose();
            new Menu(owner).setVisible(true);
        });

        int[][] posiciones = {{1,5}, {2,5}, {3,5}, {4,5}, {4,1}};
        var botones = new JButton[]{btnTransferencias, btnPagos, btnReportes, btnPerfil, btnSalir};

        for (int i = 0; i < botones.length; i++) {
            var gbc = new GridBagConstraints();
            if (i < 4) gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(0, 0, 5, 5);
            gbc.gridx = posiciones[i][0];
            gbc.gridy = posiciones[i][1];
            if (i == 4) gbc.anchor = GridBagConstraints.NORTHEAST;
            contentPane.add(botones[i], gbc);
        }

        setLocationRelativeTo(owner);
    }

    private JButton crearBoton(String texto, Color borde) {
        var btn = new JButton(texto);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setFocusPainted(false);
        btn.setBorder(new MatteBorder(0, 0, 1, 0, borde));
        btn.setBackground(AppTheme.FONDO_NORMAL);
        btn.addMouseListener(new MouseAdapter() {
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
        });
        return btn;
    }


}
