package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Vista.*;

public class ControladorLogin {
	public JPasswordField contrasena;
	public JTextField usuario;
	private Connection conn;

    
	public ControladorLogin(JPasswordField contrasena, JTextField usuario) {
		this.contrasena = contrasena;
		this.usuario = usuario;
        try {
            this.conn = ConexionBD.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public String contraIngresada() {
        char[] passwordChars = contrasena.getPassword();
        return new String(passwordChars);
    }
    public int id_client() {
    	int id = 0;
    	try{
            String query = "SELECT personas.id_cliente FROM banco.usuario, banco.personas WHERE personas.id_usuario = usuario.id_usuario "
            		+ "AND usuario.usuario_nombre = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, usuario.getText());
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                    	id = rs.getInt("id_cliente");
                    }
                    return id;
                }
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        } 
    	return id;
    }
    public boolean buscar() {
    	
        String sql = "";
        int id = 0;
        ResultSet buscar = null;
        Statement stmt = null;
        String laContrasena = "";
        try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM banco.usuario WHERE usuario_nombre = '" + usuario.getText() + "'";
            buscar = stmt.executeQuery(sql);
            while (buscar.next()) {
                id = buscar.getInt("id_usuario");
                String usuario = buscar.getString("usuario_nombre");
                laContrasena = buscar.getString("contrasena");
            }
            
            if (laContrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El usuario no existe");
            } else {
                if (laContrasena.equals(contraIngresada())) {
                    int id_client = 0;
                    sql = "SELECT id_cliente FROM banco.personas WHERE id_usuario = " + id;
                    buscar = stmt.executeQuery(sql);
                    while (buscar.next()) {
                        id_client = buscar.getInt("id_cliente");
                    }
                    
                    return true;
                    
                    
                } else {
                     JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Inténtelo de nuevo");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        }
        return false;
    }
}
