package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

public class Planes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textArea;
    private JButton selectedButton = null; // Variable para rastrear el botón seleccionado
    private final Color hoverBackground = new Color(0, 168, 107);
    private final Color selectedBackground = new Color(0, 168, 107);
    private final Color normalBackground = Color.WHITE;
    private final Color fondoNormalbton = Color.BLACK;
    private final Color hoverForeground = Color.white;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Planes frame = new Planes();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Planes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 557, 293);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{10, 60, 40, 97, 100, 97, 10, 0};
        gbl_contentPane.rowHeights = new int[]{10, 20, 25, 60, 60, 60, 25, 20, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
/*
        JButton btnNormal = new JButton();
        JButton btnProfesional = new JButton();
        JButton btnJoven = new JButton();
        JButton btnMayor = new JButton();
*/        
        JLabel lblNewLabel = new JLabel("Ahorro e Inversión");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridwidth = 5;
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        
        JButton btnNormal = createPlanButton("Normal");
        btnNormal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSelectedButton(btnNormal);
                CalculosPlanes.setSelectedPlan("Normal");
                mostrarInformacionCuenta("Normal");
            }
        });
        GridBagConstraints gbc_btnNormal = new GridBagConstraints();
        gbc_btnNormal.gridwidth = 2;
        gbc_btnNormal.fill = GridBagConstraints.BOTH;
        gbc_btnNormal.insets = new Insets(0, 0, 5, 5);
        gbc_btnNormal.gridx = 1;
        gbc_btnNormal.gridy = 2;
        
        contentPane.add(btnNormal, gbc_btnNormal);

        
		JButton btnJoven = createPlanButton("Joven");
        btnJoven .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSelectedButton(btnJoven);
                CalculosPlanes.setSelectedPlan("Joven");
                mostrarInformacionCuenta("Joven");
            }
        });
        GridBagConstraints gbc_btnJoven = new GridBagConstraints();
        gbc_btnJoven.fill = GridBagConstraints.BOTH;
        gbc_btnJoven.insets = new Insets(0, 0, 5, 5);
        gbc_btnJoven.gridx = 3;
        gbc_btnJoven.gridy = 2;
        contentPane.add(btnJoven, gbc_btnJoven);

        
        JButton btnProfesional = createPlanButton("Profesional");
        btnProfesional.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSelectedButton(btnProfesional);
                CalculosPlanes.setSelectedPlan("Profesional");
                mostrarInformacionCuenta("Profesional");
            }
        });
        GridBagConstraints gbc_btnProfesional = new GridBagConstraints();
        gbc_btnProfesional.fill = GridBagConstraints.BOTH;
        gbc_btnProfesional.insets = new Insets(0, 0, 5, 5);
        gbc_btnProfesional.gridx = 4;
        gbc_btnProfesional.gridy = 2;
        contentPane.add(btnProfesional, gbc_btnProfesional);

        
        JButton btnMayor = createPlanButton("Mayor");
        btnMayor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSelectedButton(btnMayor);
                CalculosPlanes.setSelectedPlan("Mayor");
                mostrarInformacionCuenta("Mayor");
            }
        });
        GridBagConstraints gbc_btnMayor = new GridBagConstraints();
        gbc_btnMayor.fill = GridBagConstraints.BOTH;
        gbc_btnMayor.insets = new Insets(0, 0, 5, 5);
        gbc_btnMayor.gridx = 5;
        gbc_btnMayor.gridy = 2;
        contentPane.add(btnMayor, gbc_btnMayor);
        
        

        textArea = new JTextArea();
        textArea.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.gridheight = 3;
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.insets = new Insets(0, 0, 5, 5);
        gbc_textArea.gridwidth = 4;
        gbc_textArea.gridx = 2;
        gbc_textArea.gridy = 3;
        contentPane.add(textArea, gbc_textArea);

        JButton btnCalcularInteres = new JButton("Calcular Interes");
        btnCalcularInteres.setBackground(new Color(0, 168, 107));
        btnCalcularInteres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalculosPlanes app = new CalculosPlanes();
                app.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnCalcularInteres = new GridBagConstraints();
        gbc_btnCalcularInteres.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCalcularInteres.insets = new Insets(0, 0, 5, 5);
        gbc_btnCalcularInteres.gridx = 3;
        gbc_btnCalcularInteres.gridy = 6;
        btnCalcularInteres.setFocusPainted(false);
       //btnCalcularInteres.setContentAreaFilled(false);
        btnCalcularInteres.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnCalcularInteres.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(btnCalcularInteres, gbc_btnCalcularInteres);
        btnCalcularInteres.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnCalcularInteres.setOpaque(true);
        		btnCalcularInteres.setBackground(new Color(150, 210, 0));
        		btnCalcularInteres.setForeground(hoverForeground);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		//btnCalcularInteres.setOpaque(false);
        		btnCalcularInteres.setBackground(new Color(0, 168, 107));
        		btnCalcularInteres.setForeground(fondoNormalbton);
        	}
        });
        
        
        JButton btnMenu = new JButton("Volver al Menú");
        btnMenu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnMenu.setOpaque(true);
        		btnMenu.setBackground(Color.RED);
        		btnMenu.setForeground(hoverForeground);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnMenu.setOpaque(false);
        		btnMenu.setBackground(normalBackground);
        		btnMenu.setForeground(fondoNormalbton);
        	}
        });
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal app = new MenuPrincipal();
                app.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnMenu = new GridBagConstraints();
        gbc_btnMenu.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnMenu.insets = new Insets(0, 0, 5, 5);
        gbc_btnMenu.gridx = 4;
        gbc_btnMenu.gridy = 6;
        btnMenu.setFocusPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnMenu.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(btnMenu, gbc_btnMenu);
        
        ImagePanel panel = new ImagePanel("/resources/nbeneficios.png");
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 3;
        contentPane.add(panel, gbc_panel);
        
        ImagePanel panel_1 = new ImagePanel("/resources/ncaracteristicas.png");
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 4;
        contentPane.add(panel_1, gbc_panel_1);
        
        ImagePanel panel_2 = new ImagePanel("/resources/ndescripcion.png");
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 5, 5);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 1;
        gbc_panel_2.gridy = 5;
        contentPane.add(panel_2, gbc_panel_2);     
    }

    private JButton createPlanButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(normalBackground);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != selectedButton) {
                    button.setOpaque(true);
                    button.setBackground(hoverBackground);
                    button.setForeground(hoverForeground);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != selectedButton) {
                    button.setOpaque(false);
                    button.setBackground(normalBackground);
                    button.setForeground(fondoNormalbton);
                }
            }
        });
        return button;
    }

    private void setSelectedButton(JButton button) {
        if (selectedButton != null) {
            selectedButton.setOpaque(false);
            selectedButton.setBackground(normalBackground);
            button.setForeground(Color.white); // Restaurar el color original
        }
        selectedButton = button;
        selectedButton.setOpaque(true);
        selectedButton.setBackground(selectedBackground);
        button.setForeground(Color.BLACK);
    }

    private void mostrarInformacionCuenta(String tipoCuenta) {
        String informacion = "";
        switch (tipoCuenta) {
            case "Normal":
                informacion = "Cuenta de Ahorro Clásica:\n" +
                        "Operaciones bancarias estándar, para todos los clientes.\n" +
                        "Interés: 2 % anual.\n";
                break;
            case "Joven":
                informacion = "Cuenta de Ahorro para Jóvenes o Junior:\n" +
                        "Sin costo de mantenimiento mensual, descuentos en servicios asociados, \npara clientes jóvenes, con beneficios adicionales como tarifas reducidas.\n" +
                        "Interés: 3 % anual\n";
                break;
            case "Mayor":
                informacion = "Cuenta de Ahorro para Mayores de 60 Años o Senior:\n" +
                        "Tasas de interés preferenciales, acceso prioritario en sucursales.\n" +
                        "Interés: 4 % anual\n";
                break;
            case "Profesional":
                informacion = "Cuenta de Ahorro Premium:\n" +
                        "Intereses más altas, servicios bancarios personalizados, \nacceso a productos exclusivos, consultoría financiera.\n" +
                        "Interés: 4.8 % anual\n";
                break;
        }
        textArea.setText(informacion);
    }
}
