package com.banco.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPerfil {
    private static final Logger LOG = Logger.getLogger(ControladorPerfil.class.getName());
    private final Connection conn;

    public ControladorPerfil() throws SQLException {
        this.conn = ConexionBD.getInstance().getConnection();
    }

    public List<String> datos(int idCliente) {
        List<String> data = new ArrayList<>();
        String query = "SELECT * FROM banco.personas WHERE id_cliente = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    data.add(rs.getString("id_cliente"));
                    data.add(rs.getString("id_usuario"));
                    data.add(rs.getString("apellidos"));
                    data.add(rs.getString("nombres"));
                    data.add(rs.getString("cedula"));
                    data.add(rs.getString("telefono"));
                    Date fechaNac = rs.getDate("fecha_nacimiento");
                    if (fechaNac != null) {
                        data.add(new SimpleDateFormat("dd/MM/yyyy").format(fechaNac));
                    } else {
                        data.add("");
                    }
                }
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al obtener datos del perfil", e);
        }
        return data;
    }
}
