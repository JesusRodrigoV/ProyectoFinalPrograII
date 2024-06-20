package Vista;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorTransaccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cajero extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldMonto;
    private int id;
    private int id_cuenta;

    public Cajero(int id, int id_cuenta) {
        this.id = id;
        this.id_cuenta = id_cuenta;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Monto actual: ");
        lblNewLabel.setBounds(10, 10, 200, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Monto");
        lblNewLabel_1.setBounds(76, 73, 46, 14);
        contentPane.add(lblNewLabel_1);

        textFieldMonto = new JTextField();
        textFieldMonto.setBounds(140, 70, 146, 20);
        contentPane.add(textFieldMonto);
        textFieldMonto.setColumns(10);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double montoDepositar = Double.parseDouble(textFieldMonto.getText());
                ControladorTransaccion.deposito(id_cuenta, montoDepositar);
            }
        });
        btnDepositar.setBounds(76, 170, 120, 23);
        contentPane.add(btnDepositar);

        JButton btnRetirar = new JButton("Retirar");
        btnRetirar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double montoDepositar = Double.parseDouble(textFieldMonto.getText());
                ControladorTransaccion.retiro(id_cuenta, montoDepositar);
            }
        });
        btnRetirar.setBounds(245, 170, 120, 23);
        contentPane.add(btnRetirar);

        JButton btnReporte = new JButton("Reporte");
        btnReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí se llama al controlador para generar el reporte
                // Reporte reporte = new Reporte(id, id_cuenta);
                // reporte.setVisible(true);
            }
        });
        btnReporte.setBounds(76, 230, 120, 23);
        contentPane.add(btnReporte);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnSalir.setBounds(245, 231, 120, 23);
        contentPane.add(btnSalir);
    }
}
