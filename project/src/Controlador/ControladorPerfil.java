package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControladorPerfil {
	private int id;
	private Connection conn;
	public ControladorPerfil(int id) {
		this.id = id;
		try {
            this.conn = ConexionBD.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public ArrayList<String> datos() {
		ArrayList<String> data = new ArrayList<>();
		try{
            String query = "SELECT * FROM banco.personas WHERE id_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
						String id_cliente = rs.getString("id_cliente");
						String id_usuario = rs.getString("id_usuario");
                    	String apell = rs.getString("apellidos");
						String nombres = rs.getString("nombres");
						String nCedula = rs.getString("cedula");
						String nTelefono = rs.getString("telefono");
						Date fechaNac = rs.getDate("fecha_nacimiento");

						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						String fechaFormateada = dateFormat.format(fechaNac);

						data.add(id_cliente);
						data.add(id_usuario);
						data.add(apell);
						data.add(nombres);
						data.add(nCedula);
						data.add(nTelefono);
						data.add(fechaFormateada);
                    }
                    return data;
                }
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        } 
		return data;
	}
}
