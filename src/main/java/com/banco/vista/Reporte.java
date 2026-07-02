package com.banco.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import com.banco.controlador.ControladorReporte;
import com.banco.util.AppTheme;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reporte extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Reporte.class.getName());
    private final Frame owner;

    public Reporte(Frame owner, int idCuenta) {
        super(owner, "Reporte", true);
        this.owner = owner;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 450, 323);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{20, 150, 100, 150, 20, 0};
        gbl.rowHeights = new int[]{20, 15, 214, 25, 20};
        gbl.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var lblTitulo = new JLabel("Reporte de cuenta");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        var gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridwidth = 3;
        gbcTitulo.fill = GridBagConstraints.VERTICAL;
        gbcTitulo.insets = new Insets(0, 0, 5, 5);
        gbcTitulo.gridx = 1;
        gbcTitulo.gridy = 1;
        contentPane.add(lblTitulo, gbcTitulo);

        String[] columnNames = {"Fecha", "Tipo", "Monto"};
        Object[][] data = new Object[0][];

        try {
            var control = new ControladorReporte();
            data = control.getReportData(idCuenta);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al obtener reporte", e);
        }

        var tableModel = new DefaultTableModel(data, columnNames);
        var table = new JTable(tableModel);
        var header = table.getTableHeader();
        header.setBackground(AppTheme.VERDE_OSCURO);
        header.setForeground(AppTheme.BLANCO);
        header.setFont(header.getFont().deriveFont(12f));

        var scrollPane = new JScrollPane(table);
        var gbcScroll = new GridBagConstraints();
        gbcScroll.gridwidth = 3;
        gbcScroll.insets = new Insets(0, 0, 5, 5);
        gbcScroll.fill = GridBagConstraints.BOTH;
        gbcScroll.gridx = 1;
        gbcScroll.gridy = 2;
        contentPane.add(scrollPane, gbcScroll);

        var lblSaldo = new JLabel("Saldo: ");
        var gbcLblSaldo = new GridBagConstraints();
        gbcLblSaldo.insets = new Insets(0, 0, 5, 5);
        gbcLblSaldo.gridx = 1;
        gbcLblSaldo.gridy = 3;
        contentPane.add(lblSaldo, gbcLblSaldo);

        var textAreaSaldo = new JTextArea();
        try {
            var control = new ControladorReporte();
            double saldo = control.saldo(idCuenta);
            textAreaSaldo.setText(String.format("%.2f", saldo));
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al obtener saldo", e);
        }

        var gbcSaldo = new GridBagConstraints();
        gbcSaldo.insets = new Insets(0, 0, 5, 5);
        gbcSaldo.fill = GridBagConstraints.BOTH;
        gbcSaldo.gridx = 3;
        gbcSaldo.gridy = 3;
        contentPane.add(textAreaSaldo, gbcSaldo);

        var btnSalir = new JButton("Salir");
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(new MatteBorder(0, 0, 0, 0, AppTheme.VERDE_OSCURO));
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
        var gbcBtnSalir = new GridBagConstraints();
        gbcBtnSalir.fill = GridBagConstraints.BOTH;
        gbcBtnSalir.insets = new Insets(0, 0, 0, 5);
        gbcBtnSalir.gridx = 2;
        gbcBtnSalir.gridy = 4;
        contentPane.add(btnSalir, gbcBtnSalir);

        setLocationRelativeTo(owner);
    }
}
