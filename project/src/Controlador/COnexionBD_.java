package Controlador;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class COnexionBD_ {
    private static COnexionBD_ instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/banco?useSSL=false&serverTimezone=UTC";
    private String usuario = "root";
    private String contrasena = "admin";

    private COnexionBD_() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, usuario, contrasena);
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static COnexionBD_ getInstance() throws SQLException {
        if (instance == null) {
            instance = new COnexionBD_();
        } else if (instance.getConnection().isClosed()) {
            instance = new COnexionBD_();
        }

        return instance;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Conexión a la base de datos cerrada.");
        }
    }
}

