package ejercicioCuenta;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculosPlanes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    private static String selectedPlan = "Normal"; // Default plan
    private ButtonGroup timeGroup;
    private JRadioButton radioCalculoMensual;
    private JRadioButton radioCalculoSemestral;
    private JRadioButton radioCalculoAnual;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(197, 29, 86, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Monto para calcular");
        lblNewLabel.setBounds(66, 32, 121, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tiempo");
        lblNewLabel_1.setBounds(66, 56, 46, 14);
        contentPane.add(lblNewLabel_1);

        radioCalculoMensual = new JRadioButton("Mensual");
        radioCalculoMensual.setBounds(197, 56, 109, 23);
        contentPane.add(radioCalculoMensual);

        radioCalculoSemestral = new JRadioButton("Semestral");
        radioCalculoSemestral.setBounds(197, 82, 109, 23);
        contentPane.add(radioCalculoSemestral);

        radioCalculoAnual = new JRadioButton("Anual");
        radioCalculoAnual.setBounds(197, 107, 109, 23);
        contentPane.add(radioCalculoAnual);

        // Group the radio buttons to allow only one to be selected at a time
        timeGroup = new ButtonGroup();
        timeGroup.add(radioCalculoMensual);
        timeGroup.add(radioCalculoSemestral);
        timeGroup.add(radioCalculoAnual);

        textArea = new JTextArea();
        textArea.setBounds(66, 140, 317, 49);
        contentPane.add(textArea);

        JButton botonCalcular = new JButton("Calcular");
        botonCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularAhorro();
            }
        });
        botonCalcular.setBounds(217, 200, 89, 23);
        contentPane.add(botonCalcular);

        JButton btnNewButton_1 = new JButton("Salir");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal app = new MenuPrincipal();
                app.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1.setBounds(106, 200, 89, 23);
        contentPane.add(btnNewButton_1);
    }

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
}
