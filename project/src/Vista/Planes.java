package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

public class Planes extends JFrame {
    private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
    private JButton selectedButton = null;
    private final Color verdeBoton = new Color(66, 245, 158);
    private final Color rojo = new Color(255, 91, 91);
    private final Color normalBackground = Color.WHITE;
    private final Color negro = Color.BLACK;
    private final Color blanco = Color.white;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{10, 60, 40, 50, 50, 50, 50, 40, 60, 10, 0};
        gbl_contentPane.rowHeights = new int[]{10, 20, 25, 0, 60, 0, 60, 0, 60, 35, 20, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
        gbc_lblNewLabel.gridwidth = 8;
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);

        
        JButton btnNormal = createPlanButton("Normal");
        JButton btnJoven = createPlanButton("Joven");
        JButton btnProfesional = createPlanButton("Profesional");
        JButton btnMayor = createPlanButton("Mayor");
        
        
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

        
		
        btnJoven .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSelectedButton(btnJoven);
                CalculosPlanes.setSelectedPlan("Joven");
                mostrarInformacionCuenta("Joven");
            }
        });
        GridBagConstraints gbc_btnJoven = new GridBagConstraints();
        gbc_btnJoven.gridwidth = 2;
        gbc_btnJoven.fill = GridBagConstraints.BOTH;
        gbc_btnJoven.insets = new Insets(0, 0, 5, 5);
        gbc_btnJoven.gridx = 3;
        gbc_btnJoven.gridy = 2;
        contentPane.add(btnJoven, gbc_btnJoven);

        
        
        btnProfesional.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSelectedButton(btnProfesional);
                CalculosPlanes.setSelectedPlan("Profesional");
                mostrarInformacionCuenta("Profesional");
            }
        });
        GridBagConstraints gbc_btnProfesional = new GridBagConstraints();
        gbc_btnProfesional.gridwidth = 2;
        gbc_btnProfesional.fill = GridBagConstraints.BOTH;
        gbc_btnProfesional.insets = new Insets(0, 0, 5, 5);
        gbc_btnProfesional.gridx = 5;
        gbc_btnProfesional.gridy = 2;
        contentPane.add(btnProfesional, gbc_btnProfesional);

        
        
        btnMayor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSelectedButton(btnMayor);
                CalculosPlanes.setSelectedPlan("Mayor");
                mostrarInformacionCuenta("Mayor");
            }
        });
        GridBagConstraints gbc_btnMayor = new GridBagConstraints();
        gbc_btnMayor.gridwidth = 2;
        gbc_btnMayor.fill = GridBagConstraints.BOTH;
        gbc_btnMayor.insets = new Insets(0, 0, 5, 5);
        gbc_btnMayor.gridx = 7;
        gbc_btnMayor.gridy = 2;
        contentPane.add(btnMayor, gbc_btnMayor);
        
        JLabel lblNewLabel_1 = new JLabel("Beneficios");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 3;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        

        textArea = new JTextArea();
        textArea.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.gridheight = 6;
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.insets = new Insets(0, 0, 5, 5);
        gbc_textArea.gridwidth = 7;
        gbc_textArea.gridx = 2;
        gbc_textArea.gridy = 3;
        contentPane.add(textArea, gbc_textArea);
        
        JLabel lblNewLabel_2 = new JLabel("Caracteristicas");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 5;
        contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Requisitos");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 1;
        gbc_lblNewLabel_3.gridy = 7;
        contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        
        JButton btnMenu = new JButton("Volver al Menú");
        btnMenu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnMenu.setOpaque(true);
        		btnMenu.setBackground(rojo);
        		btnMenu.setForeground(blanco);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnMenu.setOpaque(false);
        		btnMenu.setBackground(normalBackground);
        		btnMenu.setForeground(negro);
        	}
        });
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal app = new MenuPrincipal();
                app.setVisible(true);
                dispose();
            }
        });
        
                JButton btnCalcularInteres = new JButton("Calcular Interes");
                btnCalcularInteres.setBackground(verdeBoton);
                btnCalcularInteres.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        CalculosPlanes app = new CalculosPlanes();
                        app.setVisible(true);
                        dispose();
                    }
                });
                GridBagConstraints gbc_btnCalcularInteres = new GridBagConstraints();
                gbc_btnCalcularInteres.gridwidth = 2;
                gbc_btnCalcularInteres.fill = GridBagConstraints.BOTH;
                gbc_btnCalcularInteres.insets = new Insets(0, 0, 5, 5);
                gbc_btnCalcularInteres.gridx = 1;
                gbc_btnCalcularInteres.gridy = 9;
                btnCalcularInteres.setFocusPainted(false);
                //btnCalcularInteres.setContentAreaFilled(false);
                 btnCalcularInteres.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
                 btnCalcularInteres.setHorizontalAlignment(SwingConstants.CENTER);
                 contentPane.add(btnCalcularInteres, gbc_btnCalcularInteres);
                 btnCalcularInteres.addMouseListener(new MouseAdapter() {
                 	@Override
                 	public void mouseEntered(MouseEvent e) {
                 		btnCalcularInteres.setOpaque(true);
                 		btnCalcularInteres.setBackground(new Color(0, 229, 118));
                 	}
                 	@Override
                 	public void mouseExited(MouseEvent e) {
                 		//btnCalcularInteres.setOpaque(false);
                 		btnCalcularInteres.setBackground(verdeBoton);
                 	}
                 });
        
        JButton btnCalcularInteres_1 = new JButton("Calcular Interes");
        btnCalcularInteres_1.setHorizontalAlignment(SwingConstants.CENTER);
        btnCalcularInteres_1.setFocusPainted(false);
        btnCalcularInteres_1.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        btnCalcularInteres_1.setBackground(verdeBoton);
        GridBagConstraints gbc_btnCalcularInteres_1 = new GridBagConstraints();
        gbc_btnCalcularInteres_1.fill = GridBagConstraints.BOTH;
        gbc_btnCalcularInteres_1.gridwidth = 2;
        gbc_btnCalcularInteres_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnCalcularInteres_1.gridx = 4;
        gbc_btnCalcularInteres_1.gridy = 9;
        contentPane.add(btnCalcularInteres_1, gbc_btnCalcularInteres_1);
        GridBagConstraints gbc_btnMenu = new GridBagConstraints();
        gbc_btnMenu.gridwidth = 2;
        gbc_btnMenu.fill = GridBagConstraints.BOTH;
        gbc_btnMenu.insets = new Insets(0, 0, 5, 5);
        gbc_btnMenu.gridx = 7;
        gbc_btnMenu.gridy = 9;
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
        gbc_panel.gridy = 4;
        contentPane.add(panel, gbc_panel);
        
        ImagePanel panel_1 = new ImagePanel("/resources/ncaracteristicas.png");
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 6;
        contentPane.add(panel_1, gbc_panel_1);
        
        ImagePanel panel_2 = new ImagePanel("/resources/ndescripcion.png");
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 5, 5);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 1;
        gbc_panel_2.gridy = 8;
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
                    button.setBackground(verdeBoton);
                    //button.setForeground(hoverForeground);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != selectedButton) {
                    button.setOpaque(false);
                    button.setBackground(normalBackground);
                    //button.setForeground(fondoNormalbton);
                }
            }
        });
        return button;
    }

    private void setSelectedButton(JButton button) {
        if (selectedButton != null) {
            selectedButton.setOpaque(false);
            selectedButton.setBackground(normalBackground);
            button.setForeground(Color.white); 
        }
        selectedButton = button;
        selectedButton.setOpaque(true);
        selectedButton.setBackground(verdeBoton);
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