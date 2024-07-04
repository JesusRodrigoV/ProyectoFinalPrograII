package Vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import Controlador.ControladorRegister;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ButtonGroup planGroup;
    private JTextField apellidos;
    private JTextField nombres;
    private JTextField cedula;
    private JTextField telefono;
    private JTextField anio;
    private Color verdeBoton = new Color(66, 245, 158);
    private Color verdeOscuro = new Color(13, 171, 0);
    private Color rojo = new Color(255, 91, 91);
    private Color fondoNormal = Color.WHITE;
    private Color negro = Color.BLACK;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register frame = new Register();
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
    public Register() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 438, 295);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{20, 95, 40, 40, 40, 40, 95, 20, 0};
        gbl_contentPane.rowHeights = new int[]{20, 14, 20, 20, 20, 20, 21, 21, 23, 20, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);
        
                JLabel lblNewLabel_1 = new JLabel("Ingrese los siguientes datos");
                lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
                lblNewLabel_1.setForeground(verdeBoton);
                lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
                GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
                gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
                gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
                gbc_lblNewLabel_1.gridwidth = 6;
                gbc_lblNewLabel_1.gridx = 1;
                gbc_lblNewLabel_1.gridy = 1;
                contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JLabel lblApellidos = new JLabel("Apellidos: ");
        GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
        gbc_lblApellidos.anchor = GridBagConstraints.EAST;
        gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
        gbc_lblApellidos.gridx = 1;
        gbc_lblApellidos.gridy = 2;
        contentPane.add(lblApellidos, gbc_lblApellidos);
        
        apellidos = new JTextField();
        apellidos.setBackground(new Color(232, 253, 244));
        apellidos.setColumns(10);
        GridBagConstraints gbc_apellidos = new GridBagConstraints();
        gbc_apellidos.fill = GridBagConstraints.BOTH;
        gbc_apellidos.insets = new Insets(0, 0, 5, 5);
        gbc_apellidos.gridwidth = 5;
        gbc_apellidos.gridx = 2;
        gbc_apellidos.gridy = 2;
        contentPane.add(apellidos, gbc_apellidos);
        
        JLabel lblNombres = new JLabel("Nombres:");
        GridBagConstraints gbc_lblNombres = new GridBagConstraints();
        gbc_lblNombres.anchor = GridBagConstraints.EAST;
        gbc_lblNombres.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombres.gridx = 1;
        gbc_lblNombres.gridy = 3;
        contentPane.add(lblNombres, gbc_lblNombres);
        
        nombres = new JTextField();
        nombres.setBackground(new Color(232, 253, 244));
        nombres.setColumns(10);
        GridBagConstraints gbc_nombres = new GridBagConstraints();
        gbc_nombres.fill = GridBagConstraints.BOTH;
        gbc_nombres.insets = new Insets(0, 0, 5, 5);
        gbc_nombres.gridwidth = 5;
        gbc_nombres.gridx = 2;
        gbc_nombres.gridy = 3;
        contentPane.add(nombres, gbc_nombres);
        
        JLabel lblCedula = new JLabel("Cedula:");
        GridBagConstraints gbc_lblCedula = new GridBagConstraints();
        gbc_lblCedula.anchor = GridBagConstraints.EAST;
        gbc_lblCedula.insets = new Insets(0, 0, 5, 5);
        gbc_lblCedula.gridx = 1;
        gbc_lblCedula.gridy = 4;
        contentPane.add(lblCedula, gbc_lblCedula);
        
        cedula = new JTextField();
        cedula.setBackground(new Color(232, 253, 244));
        cedula.setColumns(10);
        GridBagConstraints gbc_cedula = new GridBagConstraints();
        gbc_cedula.fill = GridBagConstraints.BOTH;
        gbc_cedula.insets = new Insets(0, 0, 5, 5);
        gbc_cedula.gridwidth = 5;
        gbc_cedula.gridx = 2;
        gbc_cedula.gridy = 4;
        contentPane.add(cedula, gbc_cedula);
        
        JLabel lblTelefono = new JLabel("Telefono:");
        GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
        gbc_lblTelefono.anchor = GridBagConstraints.EAST;
        gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
        gbc_lblTelefono.gridx = 1;
        gbc_lblTelefono.gridy = 5;
        contentPane.add(lblTelefono, gbc_lblTelefono);
        
        telefono = new JTextField();
        telefono.setBackground(new Color(232, 253, 244));
        telefono.setColumns(10);
        GridBagConstraints gbc_telefono = new GridBagConstraints();
        gbc_telefono.fill = GridBagConstraints.BOTH;
        gbc_telefono.insets = new Insets(0, 0, 5, 5);
        gbc_telefono.gridwidth = 5;
        gbc_telefono.gridx = 2;
        gbc_telefono.gridy = 5;
        contentPane.add(telefono, gbc_telefono);
        
        JLabel lblFechaNac = new JLabel("Fecha Nacimiento");
        GridBagConstraints gbc_lblFechaNac = new GridBagConstraints();
        gbc_lblFechaNac.anchor = GridBagConstraints.EAST;
        gbc_lblFechaNac.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaNac.gridx = 1;
        gbc_lblFechaNac.gridy = 6;
        contentPane.add(lblFechaNac, gbc_lblFechaNac);
        
        JComboBox dia = new JComboBox<>();
        
        dia.setBackground(new Color(232, 253, 244));
        GridBagConstraints gbc_dia = new GridBagConstraints();
        gbc_dia.anchor = GridBagConstraints.NORTH;
        gbc_dia.fill = GridBagConstraints.HORIZONTAL;
        gbc_dia.insets = new Insets(0, 0, 5, 5);
        gbc_dia.gridx = 2;
        gbc_dia.gridy = 6;
        contentPane.add(dia, gbc_dia);
        for (int i = 1; i <= 31; i++) {
        	dia.addItem(i);
        }

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio","Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String[] lasCuentas = {"Ahorro Clásico", "Ahorro Junior", "Ahorro Senior", "Ahorro Platino"};

        JComboBox mes = new JComboBox<>(meses);
        mes.setBackground(new Color(232, 253, 244));
        GridBagConstraints gbc_mes = new GridBagConstraints();
        gbc_mes.fill = GridBagConstraints.BOTH;
        gbc_mes.insets = new Insets(0, 0, 5, 5);
        gbc_mes.gridwidth = 3;
        gbc_mes.gridx = 3;
        gbc_mes.gridy = 6;
        contentPane.add(mes, gbc_mes);
        
        anio = new JTextField();
        anio.setBackground(new Color(232, 253, 244));
        GridBagConstraints gbc_anio = new GridBagConstraints();
        gbc_anio.fill = GridBagConstraints.HORIZONTAL;
        gbc_anio.insets = new Insets(0, 0, 5, 5);
        gbc_anio.gridx = 6;
        gbc_anio.gridy = 6;
        contentPane.add(anio, gbc_anio);
        anio.setColumns(10);
        
        JLabel lblCuenta = new JLabel("Cuenta:");
        GridBagConstraints gbc_lblCuenta = new GridBagConstraints();
        gbc_lblCuenta.anchor = GridBagConstraints.NORTHEAST;
        gbc_lblCuenta.insets = new Insets(0, 0, 5, 5);
        gbc_lblCuenta.gridx = 1;
        gbc_lblCuenta.gridy = 7;
        contentPane.add(lblCuenta, gbc_lblCuenta);
        
        JComboBox cuentas = new JComboBox<>(lasCuentas);
        GridBagConstraints gbc_cuentas = new GridBagConstraints();
        gbc_cuentas.fill = GridBagConstraints.BOTH;
        gbc_cuentas.insets = new Insets(0, 0, 5, 5);
        gbc_cuentas.gridwidth = 5;
        gbc_cuentas.gridx = 2;
        gbc_cuentas.gridy = 7;
        contentPane.add(cuentas, gbc_cuentas);
        
        JButton btnCrear = new JButton("Crear Cuenta");
        GridBagConstraints gbc_btnCrear = new GridBagConstraints();
        gbc_btnCrear.fill = GridBagConstraints.BOTH;
        gbc_btnCrear.insets = new Insets(0, 0, 5, 5);
        gbc_btnCrear.gridwidth = 2;
        gbc_btnCrear.gridx = 2;
        gbc_btnCrear.gridy = 8;
        btnCrear.setHorizontalAlignment(SwingConstants.CENTER);
        btnCrear.setFocusPainted(false);
        btnCrear.setBorder(new MatteBorder(1, 0, 0, 0, verdeOscuro));
        btnCrear.setBackground(fondoNormal);
        btnCrear.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mouseEntered(MouseEvent e) {
         		entraMouse(btnCrear);
         	}
         	@Override
         	public void mouseExited(MouseEvent e) {
         		saleMouse(btnCrear);
         	}
         });
        contentPane.add(btnCrear, gbc_btnCrear);

        planGroup = new ButtonGroup();
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        GridBagConstraints gbc_btnSalir = new GridBagConstraints();
        gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
        gbc_btnSalir.fill = GridBagConstraints.BOTH;
        gbc_btnSalir.gridwidth = 2;
        gbc_btnSalir.gridx = 4;
        gbc_btnSalir.gridy = 8;
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(new MatteBorder(1, 0, 0, 0, rojo));
        btnSalir.setBackground(fondoNormal);
        btnSalir.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnSalir.setOpaque(true);
        		btnSalir.setBackground(rojo);
        		btnSalir.setForeground(fondoNormal);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnSalir.setOpaque(false);
        		btnSalir.setBackground(fondoNormal);
        		btnSalir.setForeground(negro);
        	}
        });
        contentPane.add(btnSalir, gbc_btnSalir);

        
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    ControladorRegister control = new ControladorRegister(cuentas, apellidos, nombres, telefono, cedula);
                    control.insertar();
                    if(control.exito()){
                        //Menu menu = new Menu();
                        //menu.setVisible(true);
                        dispose();
                    }
                } catch(NumberFormatException ex){
                    ex.printStackTrace();
                }
                
            	
            }
        });
        
        
        
        
    }



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
