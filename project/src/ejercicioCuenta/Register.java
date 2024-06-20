package ejercicioCuenta;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
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
import javax.swing.ButtonGroup;

public class Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNombre;
    private JTextField textFieldClave;
    private JRadioButton radioCuentaJoven;
    private JRadioButton radioCuentaClasica;
    private JRadioButton radioCuentaMayores;
    private JRadioButton radioCuentaPremium;
    private ButtonGroup planGroup;
    private JTextField apellidos;
    private JTextField nombres;
    private JTextField cedula;
    private JTextField telefono;
    private JTextField anio;
    

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
        setBounds(100, 100, 450, 351);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Usuario:");
        lblNewLabel.setBounds(29, 37, 46, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_3 = new JLabel("Contraseña:");
        lblNewLabel_3.setBounds(29, 61, 64, 14);
        contentPane.add(lblNewLabel_3);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(129, 35, 181, 20);
        contentPane.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        textFieldClave = new JTextField();
        textFieldClave.setBounds(129, 59, 181, 20);
        contentPane.add(textFieldClave);
        textFieldClave.setColumns(10);

        JButton btnNewButton_1 = new JButton("Salir");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(323, 99, 89, 23);
        contentPane.add(btnNewButton_1);

        planGroup = new ButtonGroup();

        radioCuentaJoven = new JRadioButton("Cuenta Junior");
        radioCuentaJoven.setBounds(321, 242, 109, 23);
        contentPane.add(radioCuentaJoven);
        planGroup.add(radioCuentaJoven);

        radioCuentaClasica = new JRadioButton("Cuenta clásica");
        radioCuentaClasica.setSelected(true);
        radioCuentaClasica.setBounds(29, 242, 109, 23);
        contentPane.add(radioCuentaClasica);
        planGroup.add(radioCuentaClasica);

        radioCuentaMayores = new JRadioButton("Cuenta Senior");
        radioCuentaMayores.setBounds(168, 242, 109, 23);
        contentPane.add(radioCuentaMayores);
        planGroup.add(radioCuentaMayores);

        radioCuentaPremium = new JRadioButton("Cuenta Premium");
        radioCuentaPremium.setBounds(168, 282, 109, 23);
        contentPane.add(radioCuentaPremium);
        planGroup.add(radioCuentaPremium);

        JLabel lblNewLabel_1 = new JLabel("Seleccionar plan para registro");
        lblNewLabel_1.setBounds(150, 11, 190, 14);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblApellidos = new JLabel("Apellidos: ");
        lblApellidos.setBounds(29, 91, 64, 14);
        contentPane.add(lblApellidos);
        
        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(29, 115, 46, 14);
        contentPane.add(lblNombres);
        
        JLabel lblCedula = new JLabel("Cedula:");
        lblCedula.setBounds(29, 139, 46, 14);
        contentPane.add(lblCedula);
        
        apellidos = new JTextField();
        apellidos.setColumns(10);
        apellidos.setBounds(129, 89, 181, 20);
        contentPane.add(apellidos);
        
        nombres = new JTextField();
        nombres.setColumns(10);
        nombres.setBounds(129, 113, 181, 20);
        contentPane.add(nombres);
        
        cedula = new JTextField();
        cedula.setColumns(10);
        cedula.setBounds(129, 137, 181, 20);
        contentPane.add(cedula);
        
        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(29, 163, 46, 14);
        contentPane.add(lblTelefono);
        
        telefono = new JTextField();
        telefono.setColumns(10);
        telefono.setBounds(129, 161, 181, 20);
        contentPane.add(telefono);
        
        JLabel lblFechaNac = new JLabel("Fecha Nacimiento");
        lblFechaNac.setBounds(29, 194, 99, 14);
        contentPane.add(lblFechaNac);
        
        JComboBox dia = new JComboBox();
        for (int i = 1; i <= 31; i++) {
        	dia.addItem(i);
        }
        dia.setBounds(129, 191, 42, 21);
        contentPane.add(dia);
        
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio","Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        JComboBox mes = new JComboBox<>(meses);
        mes.setBounds(181, 191, 73, 21);
        contentPane.add(mes);
        
        anio = new JTextField();
        anio.setBounds(264, 192, 46, 19);
        contentPane.add(anio);
        anio.setColumns(10);
        
        JButton btnNewButton = new JButton("Crear");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int elanio = Integer.parseInt(anio.getText());
            	int edadMin = 0;
            	if (nDias(mes.getSelectedIndex() + 1, elanio, dia.getSelectedIndex() +1 )) {
					if (mayorDe( dia.getSelectedIndex() + 1 , mes.getSelectedIndex() + 1, elanio, edadCuenta())) {
						if (obtenerPlanSeleccionado().equals("joven") && edad(dia.getSelectedIndex() + 1 , mes.getSelectedIndex() + 1, elanio) <= 25) {
							insertar();
						} else if(obtenerPlanSeleccionado() != "joven"){
							insertar();
						}
						else {
							JOptionPane.showMessageDialog(null, "La persona no cumple con el requisito de edad");
						}
					}else {
						JOptionPane.showMessageDialog(null, "La persona no cumple con el requisito de edad");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Dia seleccionado no existe");
				}
            	
            }
        });
        btnNewButton.setBounds(323, 61, 89, 23);
        contentPane.add(btnNewButton);
    }

    private String obtenerPlanSeleccionado() {
        if (radioCuentaJoven.isSelected()) {
            return "junior";
        } else if (radioCuentaClasica.isSelected()) {
            return "clasica";
        } else if (radioCuentaMayores.isSelected()) {
            return "senior";
        } else if (radioCuentaPremium.isSelected()) {
            return "premium";
        } else {
            return "clasica";
        }
    }
    
    public static ArrayList<String> numDias(int mes, int anio, int dia) {
        YearMonth yearMonth = YearMonth.of(anio, mes);
        int dias = yearMonth.lengthOfMonth();
        ArrayList<String> arrayDias = new ArrayList<>();
        for (int i = 1; i <= dias; i++) {
            arrayDias.add(Integer.toString(i));
        }
        return arrayDias;
    }
    public static boolean nDias(int mes, int anio, int dia) {
        return numDias(mes, anio, dia).contains(Integer.toString(dia));
    }
    public static boolean mayorDe(int dia, int mes, int anio, int edad){
		LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
		Period age = Period.between(fechaNacimiento, LocalDate.now());
		return age.getYears() >= edad || (age.getYears() == edad && (age.getMonths() > 0 || age.getDays() > 0));
    }
    public int edad (int dia, int mes, int anio) {
    	LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
		return (Period.between(fechaNacimiento, LocalDate.now())).getYears();
    }
    
    
    public void insertar() {
        String usuario = textFieldNombre.getText();
        String clave = textFieldClave.getText();
        String plan = obtenerPlanSeleccionado();
        String apelli = apellidos.getText();
        String nombre = nombres.getText();
        int numCelular = Integer.parseInt(telefono.getText());
        String nCedula = cedula.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/banco?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", 
                    "root", 
                    "admin");
            conn.setAutoCommit(false);

            try {
                // Inserción en banco.usuario
                String query = "INSERT INTO banco.usuario (usuario_nombre, contrasena) VALUES (?, ?)";
                int userId = 0;
                try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    pstmt.setString(1, usuario);
                    pstmt.setString(2, clave);
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                userId = generatedKeys.getInt(1);
                            } else {
                                throw new SQLException("Error al obtener el ID del usuario insertado.");
                            }
                        }
                    } else {
                        throw new SQLException("No se pudo insertar el usuario.");
                    }
                }

                // Inserción en banco.personas
                query = "INSERT INTO banco.personas (apellidos, nombres, cedula, telefono, id_usuario) VALUES (?, ?, ?, ?, ?)";
                int id_cliente = 0;
                try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    pstmt.setString(1, apelli);
                    pstmt.setString(2, nombre);
                    pstmt.setString(3, nCedula);
                    pstmt.setInt(4, numCelular);
                    pstmt.setInt(5, userId);
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                id_cliente = generatedKeys.getInt(1);
                            } else {
                                throw new SQLException("Error al obtener el ID del cliente insertado.");
                            }
                        }
                    } else {
                        throw new SQLException("No se pudo insertar en banco.personas.");
                    }
                }

                // Selección de id_cuenta
                query = "SELECT id_cuenta FROM banco.cuentas WHERE nombre = ?";
                int id_cuenta = 0;
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, plan);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            id_cuenta = rs.getInt("id_cuenta");
                        } else {
                            throw new SQLException("Cuenta no encontrada.");
                        }
                    }
                }

                // Inserción en cuentas_cliente
                query = "INSERT INTO cuentas_cliente (id_cuenta, id_cliente) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setInt(1, id_cuenta);
                    pstmt.setInt(2, id_cliente);
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Inserción exitosa en cuentas_cliente.");
                    } else {
                        throw new SQLException("No se pudo insertar en cuentas_cliente.");
                    }
                }

                // Commit de la transacción si todas las operaciones tuvieron éxito
                conn.commit();
                System.out.println("Transacción exitosa.");

            } catch (SQLException ex) {
                // Rollback en caso de error
                conn.rollback();
                System.out.println("Transacción fallida. Se han revertido los cambios.");
                ex.printStackTrace();
            } finally {
                // Cerrar la conexión y restaurar auto-commit
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        dispose();
    }

    
    public int edadCuenta() {
    	switch(obtenerPlanSeleccionado()) {
    		case "junior":
    			return 18;
    		case "clasica":
    			return 18;
    		case "senior":
    			return 60;
    		case "premium":
    			return 18;
    		default:
    			return 18;
    	}
    }
}
