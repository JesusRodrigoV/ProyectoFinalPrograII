package com.banco.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import com.banco.controlador.ControladorCajero;
import com.banco.util.AppTheme;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pagos extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Pagos.class.getName());
    private final Frame owner;
    private final int idCuenta;
    private JTextField monto;
    private JComboBox<String> servicio;

    public Pagos(Frame owner, int idCuenta) {
        super(owner, "Pagos", true);
        this.owner = owner;
        this.idCuenta = idCuenta;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 397, 259);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{20, 40, 30, 30, 30, 30, 40, 20, 0};
        gbl.rowHeights = new int[]{37, 13, 19, 21, 35, 25, 0, 0};
        gbl.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var lblTitulo = new JLabel("Pagos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        var gbcTitulo = new GridBagConstraints();
        gbcTitulo.fill = GridBagConstraints.VERTICAL;
        gbcTitulo.gridwidth = 6;
        gbcTitulo.insets = new Insets(0, 0, 5, 5);
        gbcTitulo.gridx = 1;
        gbcTitulo.gridy = 1;
        contentPane.add(lblTitulo, gbcTitulo);

        var lblMonto = new JLabel("Ingrese el monto:");
        lblMonto.setHorizontalAlignment(SwingConstants.RIGHT);
        var gbcLblMonto = new GridBagConstraints();
        gbcLblMonto.fill = GridBagConstraints.HORIZONTAL;
        gbcLblMonto.gridwidth = 2;
        gbcLblMonto.insets = new Insets(0, 0, 5, 5);
        gbcLblMonto.gridx = 1;
        gbcLblMonto.gridy = 2;
        contentPane.add(lblMonto, gbcLblMonto);

        monto = new JTextField();
        var gbcMonto = new GridBagConstraints();
        gbcMonto.fill = GridBagConstraints.HORIZONTAL;
        gbcMonto.insets = new Insets(0, 0, 5, 5);
        gbcMonto.gridwidth = 4;
        gbcMonto.gridx = 3;
        gbcMonto.gridy = 2;
        contentPane.add(monto, gbcMonto);
        monto.setColumns(10);

        var lblServicio = new JLabel("Servicio");
        lblServicio.setHorizontalAlignment(SwingConstants.RIGHT);
        var gbcLblServicio = new GridBagConstraints();
        gbcLblServicio.gridwidth = 2;
        gbcLblServicio.fill = GridBagConstraints.HORIZONTAL;
        gbcLblServicio.insets = new Insets(0, 0, 5, 5);
        gbcLblServicio.gridx = 1;
        gbcLblServicio.gridy = 3;
        contentPane.add(lblServicio, gbcLblServicio);

        String[] serv = {"Luz", "Agua", "Gas", "Internet"};
        servicio = new JComboBox<>(serv);
        var gbcServicio = new GridBagConstraints();
        gbcServicio.fill = GridBagConstraints.HORIZONTAL;
        gbcServicio.insets = new Insets(0, 0, 5, 5);
        gbcServicio.gridwidth = 4;
        gbcServicio.gridx = 3;
        gbcServicio.gridy = 3;
        contentPane.add(servicio, gbcServicio);

        var btnAceptar = new JButton("Aceptar");
        btnAceptar.setHorizontalAlignment(SwingConstants.CENTER);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorder(new MatteBorder(1, 0, 0, 0, AppTheme.VERDE_OSCURO));
        btnAceptar.setBackground(AppTheme.FONDO_NORMAL);
        btnAceptar.addMouseListener(hoverAdapter(btnAceptar));
        btnAceptar.addActionListener(e -> pagar());
        var gbcAceptar = new GridBagConstraints();
        gbcAceptar.fill = GridBagConstraints.BOTH;
        gbcAceptar.insets = new Insets(0, 0, 5, 5);
        gbcAceptar.gridwidth = 2;
        gbcAceptar.gridx = 2;
        gbcAceptar.gridy = 5;
        contentPane.add(btnAceptar, gbcAceptar);

        var btnCancelar = new JButton("Cancelar");
        btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(new MatteBorder(1, 0, 0, 0, AppTheme.ROJO));
        btnCancelar.setBackground(AppTheme.FONDO_NORMAL);
        btnCancelar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnCancelar.setOpaque(true);
                btnCancelar.setBackground(AppTheme.ROJO);
                btnCancelar.setForeground(AppTheme.FONDO_NORMAL);
            }
            public void mouseExited(MouseEvent e) {
                btnCancelar.setOpaque(false);
                btnCancelar.setBackground(AppTheme.FONDO_NORMAL);
                btnCancelar.setForeground(AppTheme.NEGRO);
            }
        });
        btnCancelar.addActionListener(e -> dispose());
        var gbcCancelar = new GridBagConstraints();
        gbcCancelar.gridwidth = 2;
        gbcCancelar.insets = new Insets(0, 0, 5, 5);
        gbcCancelar.fill = GridBagConstraints.BOTH;
        gbcCancelar.gridx = 4;
        gbcCancelar.gridy = 5;
        contentPane.add(btnCancelar, gbcCancelar);

        setLocationRelativeTo(owner);
    }

    private void pagar() {
        try {
            double montoVal = Double.parseDouble(monto.getText());
            String serv = (String) servicio.getSelectedItem();
            int idServicio = ControladorCajero.idCuentaServicio(serv);

            if (idServicio == 0) {
                JOptionPane.showMessageDialog(this, "Servicio no válido");
                return;
            }

            var control = new ControladorCajero();
            boolean ok = control.realizarTransferencia(idCuenta, idServicio, montoVal);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Pago Exitoso");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido");
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error en pago", ex);
            JOptionPane.showMessageDialog(this, "Error de conexión");
        }
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
