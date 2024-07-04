package Controlador;

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

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorRegister {
	private JComboBox cuentas;
    private JTextField apellidos;
    private JTextField nombres;
    private JTextField telefono;
    private JTextField cedula;
    private Connection conn;
    public boolean cerrar = false;


    public ControladorRegister(JComboBox cuentas, JTextField apellidos,JTextField nombres, JTextField telefono, JTextField cedula){
        this.cuentas = cuentas;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.telefono = telefono;
        this.cedula = cedula;
        try {
            this.conn = ConexionBD.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public String obtenerPlanSeleccionado() {
        String cuenta = (String) cuentas.getSelectedItem();
        switch(cuenta){
            case "Ahorro Junior":
                return "junior";
            case "Ahorro Clásico":
                return "clasica";
            case "Ahorro Senior":
                return "senior";
            case "Ahorro Platino":
                return "premium";
            default:
             return null;
        }
    }
    
    public ArrayList<String> numDias(int mes, int anio, int dia) {
        YearMonth yearMonth = YearMonth.of(anio, mes);
        int dias = yearMonth.lengthOfMonth();
        ArrayList<String> arrayDias = new ArrayList<>();
        for (int i = 1; i <= dias; i++) {
            arrayDias.add(Integer.toString(i));
        }
        return arrayDias;
    }
    public  boolean nDias(int mes, int anio, int dia) {
        return numDias(mes, anio, dia).contains(Integer.toString(dia));
    }
    public  boolean mayorDe(int dia, int mes, int anio, int edad){
		LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
		Period age = Period.between(fechaNacimiento, LocalDate.now());
		return age.getYears() >= edad || (age.getYears() == edad && (age.getMonths() > 0 || age.getDays() > 0));
    }
    public int edad (int dia, int mes, int anio) {
    	LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
		return (Period.between(fechaNacimiento, LocalDate.now())).getYears();
    }
    
    
    public void insertar() {
        cerrar = false;
        String plan = "";
        String apelli = "";
        String nombre = "";
        int numCelular = 0;
        String nCedula = "";
        String usuario = "";
        String clave = "";
        try{
            plan = obtenerPlanSeleccionado();
            apelli = apellidos.getText();
            nombre = nombres.getText();
            numCelular = Integer.parseInt(telefono.getText());
            nCedula = cedula.getText();
            usuario = nCedula;
            clave = nCedula;
            if(plan.isEmpty() || apelli.isEmpty() || nombre.isEmpty() || Integer.toString(numCelular).isEmpty() || nCedula.isEmpty()){
                JOptionPane.showMessageDialog(null, "Introduzca valores");
                apellidos.setText("");
                nombres.setText("");
                telefono.setText("");
                cedula.setText("");

            }
        } catch(NumberFormatException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ingrese datos correctos");
        } catch(Exception err){
            err.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error intente de nuevo");
        }
        
        if(plan.isEmpty() || apelli.isEmpty() || nombre.isEmpty() || Integer.toString(numCelular).isEmpty() || nCedula.isEmpty()){
            JOptionPane.showMessageDialog(null, "Introduzca valores");
                apellidos.setText("");
                nombres.setText("");
                telefono.setText("");
                cedula.setText("");
        }
        else{
            try {
                conn.setAutoCommit(false);
                try {
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
                    query = "INSERT INTO cuentas_cliente (id_cuenta, id_cliente) VALUES (?, ?)";
                    int id_cuentas_cliente = 0; 
                    try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                        pstmt.setInt(1, id_cuenta);
                        pstmt.setInt(2, id_cliente);
                        int rowsInserted = pstmt.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("Inserción exitosa en cuentas_cliente.");
                            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    id_cuentas_cliente = generatedKeys.getInt(1);
                                    System.out.println("ID de cuentas_cliente generado: " + id_cuentas_cliente);
                                    cerrar = true;
                                } else {
                                    throw new SQLException("No se pudo obtener el ID generado de cuentas_cliente.");
                                }
                            }
                        } else {
                            throw new SQLException("No se pudo insertar en cuentas_cliente.");
                        }
                    }
    
                        
                    conn.commit();
                    if(cerrar){
                        JOptionPane.showMessageDialog(null, "Se creo la cuenta. \nN. de cuenta: " + id_cuentas_cliente+"\nUsuario:"+
                        nCedula+"\nContraseña:" + nCedula);
                    }
                    System.out.println("Transacción exitosa.");
    
                } catch (SQLException ex) {
                    conn.rollback();
                    System.out.println("Transacción fallida. Se han revertido los cambios.");
                    ex.printStackTrace();
                } finally {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        

    }

    public boolean exito(){
        return cerrar;
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
