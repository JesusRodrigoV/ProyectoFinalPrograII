package Vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.MenuPrincipal;
import Modelo.CalculosPlanes;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Planes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textArea;
    private ButtonGroup planGroup;

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
        contentPane.setBackground(new Color(204, 255, 228));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Seleccione uno de los planes");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 393, 14);
        contentPane.add(lblNewLabel);

        JCheckBox checkNormalButton = new JCheckBox("Normal");
        checkNormalButton.setBackground(new Color(204, 255, 228));
        checkNormalButton.setBounds(22, 41, 97, 23);
        contentPane.add(checkNormalButton);

        JCheckBox checkJovenButton = new JCheckBox("Joven");
        checkJovenButton.setBackground(new Color(204, 255, 228));
        checkJovenButton.setBounds(121, 41, 97, 23);
        contentPane.add(checkJovenButton);

        JCheckBox checkProfesionalButton = new JCheckBox("Profesional");
        checkProfesionalButton.setBackground(new Color(204, 255, 228));
        checkProfesionalButton.setBounds(230, 41, 97, 23);
        contentPane.add(checkProfesionalButton);

        JCheckBox checkMayorButton = new JCheckBox("Mayor");
        checkMayorButton.setBackground(new Color(204, 255, 228));
        checkMayorButton.setBounds(331, 41, 97, 23);
        contentPane.add(checkMayorButton);

        textArea = new JTextArea();
        textArea.setBackground(new Color(232, 253, 244));
        textArea.setBounds(10, 83, 418, 124);
        contentPane.add(textArea);

        JButton btnNewButton = new JButton("Menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal app = new MenuPrincipal();
                app.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setBounds(255, 227, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Calcular Interes");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalculosPlanes app = new CalculosPlanes();
                app.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1.setBounds(94, 227, 124, 23);
        contentPane.add(btnNewButton_1);

        planGroup = new ButtonGroup();
        planGroup.add(checkNormalButton);
        planGroup.add(checkJovenButton);
        planGroup.add(checkProfesionalButton);
        planGroup.add(checkMayorButton);

        checkNormalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalculosPlanes.setSelectedPlan("Normal");
                mostrarInformacionCuenta("Normal");
            }
        });
        checkJovenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalculosPlanes.setSelectedPlan("Joven");
                mostrarInformacionCuenta("Joven");
            }
        });
        checkProfesionalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalculosPlanes.setSelectedPlan("Profesional");
                mostrarInformacionCuenta("Profesional");
            }
        });
        checkMayorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalculosPlanes.setSelectedPlan("Mayor");
                mostrarInformacionCuenta("Mayor");
            }
        });
    }

    private void mostrarInformacionCuenta(String tipoCuenta) {
        String informacion = "";
        switch (tipoCuenta) {
            case "Normal":
                informacion = "Cuenta de Ahorro Clásica:\n" +
                        "Operaciones bancarias estándar, para todos los clientes.\n" +
                        "Interés: 2 % anual.\n" ;
                        //+ "Mínimo depósito de apertura de 100 Bs.";
                break;
            case "Joven":
                informacion = "Cuenta de Ahorro para Jóvenes o Junior:\n" +
                        "Sin costo de mantenimiento mensual, descuentos en servicios asociados, \npara clientes jóvenes, con beneficios adicionales como tarifas reducidas.\n" +
                        "Interés: 3 % anual\n" ;
                        //+"Edad menor de 25 años, mínimo depósito de apertura de 50 Bs.";
                break;
            case "Mayor":
                informacion = "Cuenta de Ahorro para Mayores de 60 Años o Senior:\n" +
                        "Tasas de interés preferenciales, acceso prioritario en sucursales.\n" +
                        "Interés: 4 % anual\n" ;
                        //+ "Edad mayor de 60 años, mínimo depósito de apertura de 1000 Bs.";
                break;
            case "Profesional":
                informacion = "Cuenta de Ahorro Premium:\n" +
                        "Intereses más altas, servicios bancarios personalizados, acceso a productos exclusivos, consultoría financiera.\n" +
                        "Interés: 4.8 % anual\n" ;
                        //+"Mínimo depósito de apertura de 5000 Bs, máximo 3 retiros por mes, monto mínimo en la cuenta para mantener el interés es de 5000 Bs.";
                break;
        }
        textArea.setText(informacion);
    }
}
