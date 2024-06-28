package Controlador;

import java.sql.Connection;
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

    public void buscar() {
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
                    JOptionPane.showMessageDialog(null, "La contraseña es correcta");
                    /*Cajero cajero = new Cajero(id, id_cuenta_client);
                    cajero.setVisible(true);
                    dispose();
                    */
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Inténtelo de nuevo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar archivo");
            }
        }
    }
}
