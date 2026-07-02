package com.banco.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.banco.util.AppTheme;
import java.awt.event.*;

public class Planes extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private JButton selectedButton;
    private String planSeleccionado = "Normal";
    private final Frame owner;

    public Planes(Frame owner) {
        super(owner, "Planes", true);
        this.owner = owner;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 574, 518);
        var contentPane = new JPanel();
        contentPane.setBackground(AppTheme.BLANCO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        var gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{20, 60, 40, 50, 50, 50, 50, 40, 60, 10, 20};
        gbl.rowHeights = new int[]{20, 20, 25, 25, 60, 25, 60, 0, 60, 35, 20, 20};
        gbl.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl);

        var lblTitulo = new JLabel("Ahorro e Inversión");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        var gbcTitulo = new GridBagConstraints();
        gbcTitulo.fill = GridBagConstraints.HORIZONTAL;
        gbcTitulo.insets = new Insets(0, 0, 5, 5);
        gbcTitulo.gridwidth = 8;
        gbcTitulo.gridx = 1;
        gbcTitulo.gridy = 1;
        contentPane.add(lblTitulo, gbcTitulo);

        var btnNormal = crearBotonPlan("Normal");
        var btnJoven = crearBotonPlan("Joven");
        var btnProfesional = crearBotonPlan("Profesional");
        var btnMayor = crearBotonPlan("Mayor");

        btnNormal.addActionListener(e -> seleccionarPlan(btnNormal, "Normal"));
        var gbcNormal = new GridBagConstraints();
        gbcNormal.gridwidth = 2;
        gbcNormal.fill = GridBagConstraints.BOTH;
        gbcNormal.insets = new Insets(0, 0, 5, 5);
        gbcNormal.gridx = 1;
        gbcNormal.gridy = 2;
        contentPane.add(btnNormal, gbcNormal);

        btnJoven.addActionListener(e -> seleccionarPlan(btnJoven, "Joven"));
        var gbcJoven = new GridBagConstraints();
        gbcJoven.gridwidth = 2;
        gbcJoven.fill = GridBagConstraints.BOTH;
        gbcJoven.insets = new Insets(0, 0, 5, 5);
        gbcJoven.gridx = 3;
        gbcJoven.gridy = 2;
        contentPane.add(btnJoven, gbcJoven);

        btnProfesional.addActionListener(e -> seleccionarPlan(btnProfesional, "Profesional"));
        var gbcProf = new GridBagConstraints();
        gbcProf.gridwidth = 2;
        gbcProf.fill = GridBagConstraints.BOTH;
        gbcProf.insets = new Insets(0, 0, 5, 5);
        gbcProf.gridx = 5;
        gbcProf.gridy = 2;
        contentPane.add(btnProfesional, gbcProf);

        btnMayor.addActionListener(e -> seleccionarPlan(btnMayor, "Mayor"));
        var gbcMayor = new GridBagConstraints();
        gbcMayor.gridwidth = 2;
        gbcMayor.fill = GridBagConstraints.BOTH;
        gbcMayor.insets = new Insets(0, 0, 5, 5);
        gbcMayor.gridx = 7;
        gbcMayor.gridy = 2;
        contentPane.add(btnMayor, gbcMayor);

        var lblBeneficios = new JLabel("Beneficios");
        var gbcBeneficios = new GridBagConstraints();
        gbcBeneficios.insets = new Insets(0, 0, 5, 5);
        gbcBeneficios.gridx = 1;
        gbcBeneficios.gridy = 3;
        contentPane.add(lblBeneficios, gbcBeneficios);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        var gbcText = new GridBagConstraints();
        gbcText.gridheight = 6;
        gbcText.fill = GridBagConstraints.BOTH;
        gbcText.insets = new Insets(0, 0, 5, 5);
        gbcText.gridwidth = 7;
        gbcText.gridx = 2;
        gbcText.gridy = 3;
        contentPane.add(textArea, gbcText);

        var lblCaracteristicas = new JLabel("Caracteristicas");
        var gbcCaract = new GridBagConstraints();
        gbcCaract.insets = new Insets(0, 0, 5, 5);
        gbcCaract.gridx = 1;
        gbcCaract.gridy = 5;
        contentPane.add(lblCaracteristicas, gbcCaract);

        var lblRequisitos = new JLabel("Requisitos");
        var gbcRequisitos = new GridBagConstraints();
        gbcRequisitos.insets = new Insets(0, 0, 5, 5);
        gbcRequisitos.gridx = 1;
        gbcRequisitos.gridy = 7;
        contentPane.add(lblRequisitos, gbcRequisitos);

        var imgBeneficios = new ImagePanel("/resources/nbeneficios.png");
        var gbcImgBenef = new GridBagConstraints();
        gbcImgBenef.insets = new Insets(0, 0, 5, 5);
        gbcImgBenef.fill = GridBagConstraints.BOTH;
        gbcImgBenef.gridx = 1;
        gbcImgBenef.gridy = 4;
        contentPane.add(imgBeneficios, gbcImgBenef);

        var imgCaract = new ImagePanel("/resources/ncaracteristicas.png");
        var gbcImgCaract = new GridBagConstraints();
        gbcImgCaract.insets = new Insets(0, 0, 5, 5);
        gbcImgCaract.fill = GridBagConstraints.BOTH;
        gbcImgCaract.gridx = 1;
        gbcImgCaract.gridy = 6;
        contentPane.add(imgCaract, gbcImgCaract);

        var imgReq = new ImagePanel("/resources/ndescripcion.png");
        var gbcImgReq = new GridBagConstraints();
        gbcImgReq.insets = new Insets(0, 0, 5, 5);
        gbcImgReq.fill = GridBagConstraints.BOTH;
        gbcImgReq.gridx = 1;
        gbcImgReq.gridy = 8;
        contentPane.add(imgReq, gbcImgReq);

        var btnCalcular = new JButton("Calcular Interes");
        btnCalcular.setBackground(AppTheme.VERDE_BOTON);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnCalcular.setHorizontalAlignment(SwingConstants.CENTER);
        btnCalcular.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnCalcular.setBackground(AppTheme.HOVER_VERDE);
            }
            public void mouseExited(MouseEvent e) {
                btnCalcular.setBackground(AppTheme.VERDE_BOTON);
            }
        });
        btnCalcular.addActionListener(e -> {
            new CalculosPlanes(owner, planSeleccionado).setVisible(true);
            dispose();
        });
        var gbcCalcular = new GridBagConstraints();
        gbcCalcular.gridwidth = 2;
        gbcCalcular.fill = GridBagConstraints.BOTH;
        gbcCalcular.insets = new Insets(0, 0, 5, 5);
        gbcCalcular.gridx = 3;
        gbcCalcular.gridy = 9;
        contentPane.add(btnCalcular, gbcCalcular);

        var btnMenu = new JButton("Volver al Menú");
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnMenu.setHorizontalAlignment(SwingConstants.CENTER);
        btnMenu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnMenu.setOpaque(true);
                btnMenu.setBackground(AppTheme.ROJO);
                btnMenu.setForeground(AppTheme.BLANCO);
            }
            public void mouseExited(MouseEvent e) {
                btnMenu.setOpaque(false);
                btnMenu.setBackground(AppTheme.FONDO_NORMAL);
                btnMenu.setForeground(AppTheme.NEGRO);
            }
        });
        btnMenu.addActionListener(e -> dispose());
        var gbcMenu = new GridBagConstraints();
        gbcMenu.gridwidth = 2;
        gbcMenu.fill = GridBagConstraints.BOTH;
        gbcMenu.insets = new Insets(0, 0, 5, 5);
        gbcMenu.gridx = 5;
        gbcMenu.gridy = 9;
        contentPane.add(btnMenu, gbcMenu);

        setLocationRelativeTo(owner);
    }

    private JButton crearBotonPlan(String texto) {
        var btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setBackground(AppTheme.FONDO_NORMAL);
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (btn != selectedButton) {
                    btn.setOpaque(true);
                    btn.setBackground(AppTheme.VERDE_BOTON);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (btn != selectedButton) {
                    btn.setOpaque(false);
                    btn.setBackground(AppTheme.FONDO_NORMAL);
                }
            }
        });
        return btn;
    }

    private void seleccionarPlan(JButton btn, String plan) {
        planSeleccionado = plan;
        if (selectedButton != null) {
            selectedButton.setOpaque(false);
            selectedButton.setBackground(AppTheme.FONDO_NORMAL);
            selectedButton.setForeground(Color.BLACK);
        }
        selectedButton = btn;
        selectedButton.setOpaque(true);
        selectedButton.setBackground(AppTheme.VERDE_BOTON);
        selectedButton.setForeground(Color.BLACK);
        mostrarInformacion(plan);
    }

    private void mostrarInformacion(String plan) {
        String info = switch (plan) {
            case "Normal" -> """
                Cuenta de Ahorro Clásica:
                Operaciones bancarias estándar, para todos los clientes.
                Interés: 2 % anual.
                """;
            case "Joven" -> """
                Cuenta de Ahorro para Jóvenes o Junior:
                Sin costo de mantenimiento mensual, descuentos en servicios asociados,
                para clientes jóvenes, con beneficios adicionales como tarifas reducidas.
                Interés: 3 % anual
                """;
            case "Mayor" -> """
                Cuenta de Ahorro para Mayores de 60 Años o Senior:
                Tasas de interés preferenciales, acceso prioritario en sucursales.
                Interés: 4 % anual
                """;
            case "Profesional" -> """
                Cuenta de Ahorro Premium:
                Intereses más altas, servicios bancarios personalizados,
                acceso a productos exclusivos, consultoría financiera.
                Interés: 4.8 % anual
                """;
            default -> "";
        };
        textArea.setText(info);
    }
}
