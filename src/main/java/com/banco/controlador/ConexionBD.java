package com.banco.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    private static final Logger LOG = Logger.getLogger(ConexionBD.class.getName());
    private static ConexionBD instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/banco?useSSL=false&serverTimezone=UTC";
    private final String usuario = "root";
    private final String contrasena = "admin";

    private ConexionBD() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, usuario, contrasena);
            LOG.info("Conexión a BD establecida");
        } catch (ClassNotFoundException ex) {
            LOG.severe("Driver JDBC no encontrado: " + ex.getMessage());
            throw new SQLException(ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConexionBD getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConexionBD();
        }
        return instance;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    LOG.info("Conexión a la base de datos cerrada");
                }
            } catch (SQLException e) {
                LOG.log(Level.WARNING, "Error al cerrar conexión", e);
            }
        }
    }
}
