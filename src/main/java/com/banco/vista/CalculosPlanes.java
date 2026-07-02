package com.banco.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import com.banco.util.AppTheme;
import java.awt.event.*;

public class CalculosPlanes extends JDialog {
    private static final long serialVersionUID = 1L;
    private final String selectedPlan;
    private ButtonGroup timeGroup;
    private JRadioButton radioMensual;
    private JRadioButton radioSemestral;
    private JRadioButton radioAnual;
    private JTextField textField;
    private JTextArea textArea;
    private final Frame owner;

    public CalculosPlanes(Frame owner, String selectedPlan) {
        super(owner, "Calcular interés", true);
        this.selectedPlan = selectedPlan;
        this.owner = owner;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        timeGroup = new ButtonGroup();
        setBounds(100, 100, 440, 325);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{20, 50, 50, 50, 50, 20, 15};
        gbl.rowHeights = new int[]{15, 25, 0, 23, 20, 23, 23, 0, 50, 23, 0, 0};
        gbl.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 4.9E-324};
        gbl.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var lblTitulo = new JLabel("Calcula el Interes de tu ahorro");
        lblTitulo.setForeground(AppTheme.VERDE_OSCURO);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        var gbcTitulo = new GridBagConstraints();
        gbcTitulo.fill = GridBagConstraints.HORIZONTAL;
        gbcTitulo.gridwidth = 2;
        gbcTitulo.insets = new Insets(0, 0, 5, 5);
        gbcTitulo.gridx = 2;
        gbcTitulo.gridy = 1;
        contentPane.add(lblTitulo, gbcTitulo);

        var lblMonto = new JLabel("Monto a calcular");
        var gbcLblMonto = new GridBagConstraints();
        gbcLblMonto.anchor = GridBagConstraints.EAST;
        gbcLblMonto.insets = new Insets(0, 0, 5, 5);
        gbcLblMonto.gridx = 2;
        gbcLblMonto.gridy = 3;
        contentPane.add(lblMonto, gbcLblMonto);

        textField = new JTextField();
        textField.setColumns(10);
        var gbcField = new GridBagConstraints();
        gbcField.fill = GridBagConstraints.BOTH;
        gbcField.insets = new Insets(0, 0, 5, 5);
        gbcField.gridx = 3;
        gbcField.gridy = 3;
        contentPane.add(textField, gbcField);

        radioMensual = crearRadio("Mensual", 4);
        radioSemestral = crearRadio("Semestral", 5);
        radioAnual = crearRadio("Anual", 6);
        radioAnual.setSelected(true);

        var lblTipoCuenta = new JLabel("Tipo de Cuenta");
        var gbcLblTipo = new GridBagConstraints();
        gbcLblTipo.anchor = GridBagConstraints.EAST;
        gbcLblTipo.insets = new Insets(0, 0, 5, 5);
        gbcLblTipo.gridx = 2;
        gbcLblTipo.gridy = 7;
        contentPane.add(lblTipoCuenta, gbcLblTipo);

        String[] cuentas = {"Ahorro Clásico", "Ahorro Junior", "Ahorro Senior", "Ahorro Platino"};
        var comboBox = new JComboBox<>(cuentas);
        var gbcCombo = new GridBagConstraints();
        gbcCombo.insets = new Insets(0, 0, 5, 5);
        gbcCombo.fill = GridBagConstraints.HORIZONTAL;
        gbcCombo.gridx = 3;
        gbcCombo.gridy = 7;
        contentPane.add(comboBox, gbcCombo);

        textArea = new JTextArea();
        var gbcText = new GridBagConstraints();
        gbcText.gridwidth = 5;
        gbcText.insets = new Insets(0, 0, 5, 0);
        gbcText.fill = GridBagConstraints.BOTH;
        gbcText.gridx = 1;
        gbcText.gridy = 8;
        contentPane.add(textArea, gbcText);

        var btnCalcular = new JButton("Calcular");
        btnCalcular.setHorizontalAlignment(SwingConstants.CENTER);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setBorder(new MatteBorder(1, 0, 0, 0, AppTheme.VERDE_OSCURO));
        btnCalcular.setBackground(AppTheme.FONDO_NORMAL);
        btnCalcular.addMouseListener(hoverAdapter(btnCalcular));
        btnCalcular.addActionListener(e -> calcular());
        var gbcCalcular = new GridBagConstraints();
        gbcCalcular.fill = GridBagConstraints.BOTH;
        gbcCalcular.insets = new Insets(0, 0, 5, 5);
        gbcCalcular.gridx = 2;
        gbcCalcular.gridy = 9;
        contentPane.add(btnCalcular, gbcCalcular);

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
                btnSalir.setFont(new Font("Tahoma", Font.BOLD, 10));
            }
            public void mouseExited(MouseEvent e) {
                btnSalir.setOpaque(false);
                btnSalir.setBackground(AppTheme.FONDO_NORMAL);
                btnSalir.setForeground(AppTheme.NEGRO);
                btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
            }
        });
        btnSalir.addActionListener(e -> dispose());
        var gbcSalir = new GridBagConstraints();
        gbcSalir.gridwidth = 2;
        gbcSalir.fill = GridBagConstraints.BOTH;
        gbcSalir.insets = new Insets(0, 0, 5, 5);
        gbcSalir.gridx = 3;
        gbcSalir.gridy = 9;
        contentPane.add(btnSalir, gbcSalir);

        setLocationRelativeTo(owner);
    }

    private JRadioButton crearRadio(String texto, int y) {
        var radio = new JRadioButton(texto);
        radio.setBackground(Color.WHITE);
        var gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = 3;
        gbc.gridy = y;
        getContentPane().add(radio, gbc);
        timeGroup.add(radio);
        return radio;
    }

    private void calcular() {
        try {
            double monto = Double.parseDouble(textField.getText());
            double interes = obtenerInteres(selectedPlan);
            int meses = obtenerMeses();
            if (meses == 0) {
                textArea.setText("Seleccione un periodo de tiempo de ahorro");
                return;
            }
            double ahorro = monto * Math.pow(1 + (interes / 12), meses) - monto;
            textArea.setText("El monto ahorrado en " + meses + " meses es: " + String.format("%.2f", ahorro) + " Bs.");
        } catch (NumberFormatException e) {
            textArea.setText("Ingrese un monto valido");
        }
    }

    private double obtenerInteres(String plan) {
        return switch (plan) {
            case "Joven" -> 0.03;
            case "Mayor" -> 0.04;
            case "Profesional" -> 0.048;
            default -> 0.02;
        };
    }

    private int obtenerMeses() {
        if (radioMensual.isSelected()) return 1;
        if (radioSemestral.isSelected()) return 6;
        if (radioAnual.isSelected()) return 12;
        return 0;
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
