package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ControladorCambios {
    public String nuevaContrasena;
	private Connection conn;
	private JTextField usuario;
    public boolean cerrar;
	
	public ControladorCambios(JTextField usuario) {
		this.usuario = usuario;
		try {
            this.conn = ConexionBD.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public String contraIngresada(JPasswordField contrasena) {
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

    public int id_usuario(int id) {
    	try{
            String query = "SELECT usuario.id_usuario FROM banco.usuario, banco.personas WHERE personas.id_usuario = usuario.id_usuario "
            		+ "AND personas.id_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                    	id = rs.getInt("id_usuario");
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
	
	public String contraAntigua() {
		String sql = "";
        int id = 0;
        ResultSet buscar = null;
        Statement stmt = null;
        String laContrasena = "";
        try {
            stmt = conn.createStatement();
            sql = "SELECT contrasena FROM banco.usuario WHERE usuario_nombre = '" + usuario.getText() + "'";
            buscar = stmt.executeQuery(sql);
            while (buscar.next()) {
                laContrasena = buscar.getString("contrasena");
            }
            
            if (laContrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El usuario no existe");
            } else {
            	return laContrasena;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        }
	    return null;
	}

    public void cambiarContra(int id, JPasswordField contraNueva) {
        String nuevaContrasena = new String(contraNueva.getPassword());
        cerrar = false;
        String query = "UPDATE usuario SET contrasena = ? WHERE id_usuario = ?";
        int id_cliente = 0;
        int id_usuario = id_usuario(id);
        System.out.println("id:" + id_usuario(id));
        
        try {
            conn.setAutoCommit(false); 
            
            try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, nuevaContrasena);
                pstmt.setInt(2, id_usuario);
                
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    conn.commit(); 
                    System.out.println("Contraseña actualizada con éxito.");
                    cerrar = true;
                } else {
                    throw new SQLException("No se pudo actualizar la contraseña.");
                }
            }
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback(); 
                    System.out.println("Transacción fallida. Se han revertido los cambios.");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        }
    }
    
    
    public void cambiarUsuario(int id) {
        cerrar = false;
        String query = "UPDATE usuario SET usuario_nombre = ? WHERE id_usuario = ?";
        
        try {
            conn.setAutoCommit(false); 
            
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, usuario.getText());
                pstmt.setInt(2, id_usuario(id));
                
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    conn.commit(); 
                    JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito.");
                    cerrar = true;
                } else {
                    throw new SQLException("No se pudo actualizar el usuario.");
                }
            }
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback(); 
                    System.out.println("Transacción fallida. Se han revertido los cambios.");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        }
    }
    public boolean hecho (){
        return cerrar;
    }
    
}
