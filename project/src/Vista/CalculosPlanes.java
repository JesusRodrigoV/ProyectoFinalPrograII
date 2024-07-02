package Vista;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import java.awt.event.*;


public class CalculosPlanes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static String selectedPlan = "Normal";
    private ButtonGroup timeGroup;
    private JRadioButton radioCalculoMensual;
    private JRadioButton radioCalculoSemestral;
    private JRadioButton radioCalculoAnual;
    private Color verdeBoton = new Color(66, 245, 158);
    private Color verdeOscuro = new Color(13, 171, 0);
    private Color rojo = new Color(255, 91, 91);
    private Color fondoNormal = Color.WHITE;
    private Color negro = Color.BLACK;
    private Color blanco = Color.white;
    private JLabel lblNewLabel_2;
    private JTextField textField;
    private JTextArea textArea;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CalculosPlanes frame = new CalculosPlanes();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Set the selected plan from Planes.
     */
    public static void setSelectedPlan(String plan) {
        selectedPlan = plan;
    }

    /**
     * Create the frame.
     */
    public CalculosPlanes() {
        timeGroup = new ButtonGroup();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 325);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{15, 50, 50, 50, 50, 15};
        gbl_contentPane.rowHeights = new int[]{15, 0, 20, 23, 23, 23, 50, 23, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 4.9E-324};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        
        lblNewLabel_2 = new JLabel("Calcula el Interes de tu ahorro");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel_2.gridwidth = 2;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 2;
        gbc_lblNewLabel_2.gridy = 1;
        contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        radioCalculoMensual = new JRadioButton("Mensual");
        radioCalculoMensual.setBackground(new Color(204, 255, 228));
        GridBagConstraints gbc_radioCalculoMensual = new GridBagConstraints();
        gbc_radioCalculoMensual.anchor = GridBagConstraints.WEST;
        gbc_radioCalculoMensual.fill = GridBagConstraints.VERTICAL;
        gbc_radioCalculoMensual.insets = new Insets(0, 0, 5, 5);
        gbc_radioCalculoMensual.gridx = 3;
        gbc_radioCalculoMensual.gridy = 2;
        contentPane.add(radioCalculoMensual, gbc_radioCalculoMensual);
        timeGroup.add(radioCalculoMensual);
        
        radioCalculoSemestral = new JRadioButton("Semestral");
        radioCalculoSemestral.setBackground(new Color(204, 255, 228));
        GridBagConstraints gbc_radioCalculoSemestral = new GridBagConstraints();
        gbc_radioCalculoSemestral.anchor = GridBagConstraints.WEST;
        gbc_radioCalculoSemestral.fill = GridBagConstraints.VERTICAL;
        gbc_radioCalculoSemestral.insets = new Insets(0, 0, 5, 5);
        gbc_radioCalculoSemestral.gridx = 3;
        gbc_radioCalculoSemestral.gridy = 3;
        contentPane.add(radioCalculoSemestral, gbc_radioCalculoSemestral);
        timeGroup.add(radioCalculoSemestral);
        
        radioCalculoAnual = new JRadioButton("Anual");
        radioCalculoAnual.setSelected(true);
        radioCalculoAnual.setBackground(new Color(204, 255, 228));
        GridBagConstraints gbc_radioCalculoAnual = new GridBagConstraints();
        gbc_radioCalculoAnual.anchor = GridBagConstraints.WEST;
        gbc_radioCalculoAnual.fill = GridBagConstraints.VERTICAL;
        gbc_radioCalculoAnual.insets = new Insets(0, 0, 5, 0);
        gbc_radioCalculoAnual.gridwidth = 3;
        gbc_radioCalculoAnual.gridx = 3;
        gbc_radioCalculoAnual.gridy = 4;
        contentPane.add(radioCalculoAnual, gbc_radioCalculoAnual);
        timeGroup.add(radioCalculoAnual);
        
        JLabel lblNewLabel = new JLabel("Monto a calcular");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 2;
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 5;
        contentPane.add(lblNewLabel, gbc_lblNewLabel);
        
        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 2;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 3;
        gbc_textField.gridy = 5;
        contentPane.add(textField, gbc_textField);
        textField.setColumns(10);
                
        //Boton calcular
        JButton botonCalcular = new JButton("Calcular");
        botonCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //calcularAhorro();
            }
        });
        botonCalcular.setHorizontalAlignment(SwingConstants.CENTER);
        botonCalcular.setFocusPainted(false);
        botonCalcular.setBorder(new MatteBorder(1, 0, 0, 0, verdeOscuro));
        botonCalcular.setBackground(fondoNormal);
        botonCalcular.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                entraMouse(botonCalcular);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                saleMouse(botonCalcular);
            }
            });
        
        textArea = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.gridwidth = 4;
        gbc_textArea.insets = new Insets(0, 0, 5, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 1;
        gbc_textArea.gridy = 6;
        contentPane.add(textArea, gbc_textArea);
        GridBagConstraints gbc_botonCalcular = new GridBagConstraints();
        gbc_botonCalcular.fill = GridBagConstraints.BOTH;
        gbc_botonCalcular.insets = new Insets(0, 0, 5, 5);
        gbc_botonCalcular.gridx = 2;
        gbc_botonCalcular.gridy = 7;
        contentPane.add(botonCalcular, gbc_botonCalcular);
        
        //Boton Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cajero app = new Cajero();
                app.setVisible(true);
                dispose();
            }
        });
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(new MatteBorder(1, 0, 0, 0, rojo));
        btnSalir.setBackground(fondoNormal);
        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnSalir.setOpaque(true);
            	btnSalir.setBackground(rojo);
            	btnSalir.setForeground(blanco);
            	btnSalir.setFont(new Font("Tahoma", Font.BOLD, 10));
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	btnSalir.setOpaque(false);
            	btnSalir.setBackground(fondoNormal);
                btnSalir.setForeground(negro);
            	btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
            }
            });
        GridBagConstraints gbc_btnSalir = new GridBagConstraints();
        gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
        gbc_btnSalir.fill = GridBagConstraints.BOTH;
        gbc_btnSalir.gridx = 3;
        gbc_btnSalir.gridy = 7;
        contentPane.add(btnSalir, gbc_btnSalir);
                

        
    }
/*
    private void calcularAhorro() {
        try {
            double monto = Double.parseDouble(textField.getText());
            double interes = obtenerInteres(selectedPlan);
            int meses = obtenerMesesSeleccionados();
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
        switch (plan) {
            case "Joven":
                return 0.03;
            case "Mayor":
                return 0.04;
            case "Profesional":
                return 0.048;
            case "Normal":
            default:
                return 0.02;
        }
    }

    private int obtenerMesesSeleccionados() {
        if (radioCalculoMensual.isSelected()) {
            return 1;
        } else if (radioCalculoSemestral.isSelected()) {
            return 6;
        } else if (radioCalculoAnual.isSelected()) {
            return 12;
        } else {
            return 0;
        }
    }
    */
    private void entraMouse(JButton boton){
        boton.setOpaque(true);
        boton.setBackground(verdeBoton);
        boton.setFont(new Font("Tahoma", Font.BOLD, 10));
    }
    private void saleMouse(JButton boton){
        boton.setOpaque(false);
        boton.setBackground(fondoNormal);
        boton.setFont(new Font("Tahoma", Font.BOLD, 11));
    }
    
}
