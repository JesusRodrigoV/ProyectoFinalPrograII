package com.banco.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import com.banco.controlador.ControladorRegister;
import com.banco.modelo.Contrasena;
import com.banco.util.AppTheme;
import java.awt.*;
import java.awt.event.*;

public class Register extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField apellidos;
    private JTextField nombres;
    private JTextField cedula;
    private JTextField telefono;
    private JPasswordField passwordField;

    public Register(Frame owner) {
        super(owner, "Registro", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 438, 340);
        var contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{20, 95, 40, 40, 40, 40, 95, 20, 0};
        gbl.rowHeights = new int[]{20, 14, 20, 20, 20, 20, 21, 21, 21, 23, 20, 0};
        gbl.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var lblTitulo = new JLabel("Ingrese los siguientes datos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setForeground(AppTheme.VERDE_BOTON);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        var gbcTitulo = new GridBagConstraints();
        gbcTitulo.fill = GridBagConstraints.BOTH;
        gbcTitulo.insets = new Insets(0, 0, 5, 5);
        gbcTitulo.gridwidth = 6;
        gbcTitulo.gridx = 1;
        gbcTitulo.gridy = 1;
        contentPane.add(lblTitulo, gbcTitulo);

        apellidos = addFieldRow("Apellidos: ", 2, contentPane, gbl);
        nombres = addFieldRow("Nombres: ", 3, contentPane, gbl);
        cedula = addFieldRow("Cedula: ", 4, contentPane, gbl);
        telefono = addFieldRow("Telefono: ", 5, contentPane, gbl);

        var lblFechaNac = new JLabel("Fecha Nacimiento");
        var gbcFechaNac = new GridBagConstraints();
        gbcFechaNac.anchor = GridBagConstraints.EAST;
        gbcFechaNac.insets = new Insets(0, 0, 5, 5);
        gbcFechaNac.gridx = 1;
        gbcFechaNac.gridy = 6;
        contentPane.add(lblFechaNac, gbcFechaNac);

        var dia = new JComboBox<Integer>();
        dia.setBackground(AppTheme.FONDO_INPUT);
        for (int i = 1; i <= 31; i++) dia.addItem(i);
        var gbcDia = new GridBagConstraints();
        gbcDia.anchor = GridBagConstraints.NORTH;
        gbcDia.fill = GridBagConstraints.HORIZONTAL;
        gbcDia.insets = new Insets(0, 0, 5, 5);
        gbcDia.gridx = 2;
        gbcDia.gridy = 6;
        contentPane.add(dia, gbcDia);

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        var mes = new JComboBox<>(meses);
        mes.setBackground(AppTheme.FONDO_INPUT);
        var gbcMes = new GridBagConstraints();
        gbcMes.fill = GridBagConstraints.BOTH;
        gbcMes.insets = new Insets(0, 0, 5, 5);
        gbcMes.gridwidth = 3;
        gbcMes.gridx = 3;
        gbcMes.gridy = 6;
        contentPane.add(mes, gbcMes);

        var anio = new JTextField();
        anio.setBackground(AppTheme.FONDO_INPUT);
        var gbcAnio = new GridBagConstraints();
        gbcAnio.fill = GridBagConstraints.HORIZONTAL;
        gbcAnio.insets = new Insets(0, 0, 5, 5);
        gbcAnio.gridx = 6;
        gbcAnio.gridy = 6;
        contentPane.add(anio, gbcAnio);
        anio.setColumns(10);

        var lblCuenta = new JLabel("Cuenta:");
        var gbcCuenta = new GridBagConstraints();
        gbcCuenta.anchor = GridBagConstraints.NORTHEAST;
        gbcCuenta.insets = new Insets(0, 0, 5, 5);
        gbcCuenta.gridx = 1;
        gbcCuenta.gridy = 7;
        contentPane.add(lblCuenta, gbcCuenta);

        String[] lasCuentas = {"Ahorro Clásico", "Ahorro Junior", "Ahorro Senior", "Ahorro Platino"};
        var cuentas = new JComboBox<>(lasCuentas);
        var gbcCuentas = new GridBagConstraints();
        gbcCuentas.fill = GridBagConstraints.BOTH;
        gbcCuentas.insets = new Insets(0, 0, 5, 5);
        gbcCuentas.gridwidth = 5;
        gbcCuentas.gridx = 2;
        gbcCuentas.gridy = 7;
        contentPane.add(cuentas, gbcCuentas);

        var lblPassword = new JLabel("Contraseña:");
        var gbcLblPass = new GridBagConstraints();
        gbcLblPass.anchor = GridBagConstraints.NORTHEAST;
        gbcLblPass.insets = new Insets(0, 0, 5, 5);
        gbcLblPass.gridx = 1;
        gbcLblPass.gridy = 8;
        contentPane.add(lblPassword, gbcLblPass);

        passwordField = new JPasswordField();
        passwordField.setBackground(AppTheme.FONDO_INPUT);
        var gbcPass = new GridBagConstraints();
        gbcPass.fill = GridBagConstraints.BOTH;
        gbcPass.insets = new Insets(0, 0, 5, 5);
        gbcPass.gridwidth = 5;
        gbcPass.gridx = 2;
        gbcPass.gridy = 8;
        contentPane.add(passwordField, gbcPass);

        var btnCrear = new JButton("Crear Cuenta");
        estilizarBoton(btnCrear, AppTheme.VERDE_OSCURO);
        btnCrear.addActionListener(e -> {
            String pass = new String(passwordField.getPassword());
            var validador = new Contrasena(pass);
            if (!validador.contraSegura()) {
                JOptionPane.showMessageDialog(this,
                    "La contraseña no cumple:\n" + validador.errores(),
                    "Contraseña débil", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String fecha = String.format("%04d-%02d-%02d",
                Integer.parseInt(anio.getText()), mes.getSelectedIndex() + 1, dia.getSelectedIndex() + 1);

            try {
                var control = new ControladorRegister();
                boolean ok = control.insertar(
                    apellidos.getText(), nombres.getText(),
                    telefono.getText(), cedula.getText(),
                    (String) cuentas.getSelectedItem(), fecha, pass);
                if (ok) {
                    JOptionPane.showMessageDialog(this,
                        "Se creó la cuenta.\nUsuario: " + cedula.getText() +
                        "\nContraseña: " + pass);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al crear la cuenta");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        var gbcCrear = new GridBagConstraints();
        gbcCrear.fill = GridBagConstraints.BOTH;
        gbcCrear.insets = new Insets(0, 0, 5, 5);
        gbcCrear.gridwidth = 2;
        gbcCrear.gridx = 2;
        gbcCrear.gridy = 9;
        contentPane.add(btnCrear, gbcCrear);

        var btnSalir = new JButton("Salir");
        estilizarBoton(btnSalir, AppTheme.ROJO);
        btnSalir.addActionListener(e -> dispose());
        var gbcSalir = new GridBagConstraints();
        gbcSalir.insets = new Insets(0, 0, 5, 5);
        gbcSalir.fill = GridBagConstraints.BOTH;
        gbcSalir.gridwidth = 2;
        gbcSalir.gridx = 4;
        gbcSalir.gridy = 9;
        contentPane.add(btnSalir, gbcSalir);

        setLocationRelativeTo(owner);
    }

    private JTextField addFieldRow(String label, int y, JPanel panel, GridBagLayout gbl) {
        var lbl = new JLabel(label);
        var gbcLbl = new GridBagConstraints();
        gbcLbl.anchor = GridBagConstraints.EAST;
        gbcLbl.insets = new Insets(0, 0, 5, 5);
        gbcLbl.gridx = 1;
        gbcLbl.gridy = y;
        panel.add(lbl, gbcLbl);

        var field = new JTextField();
        field.setBackground(AppTheme.FONDO_INPUT);
        field.setColumns(10);
        var gbcField = new GridBagConstraints();
        gbcField.fill = GridBagConstraints.BOTH;
        gbcField.insets = new Insets(0, 0, 5, 5);
        gbcField.gridwidth = 5;
        gbcField.gridx = 2;
        gbcField.gridy = y;
        panel.add(field, gbcField);
        return field;
    }

    private void estilizarBoton(JButton btn, Color borde) {
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setFocusPainted(false);
        btn.setBorder(new MatteBorder(1, 0, 0, 0, borde));
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
    }
}
