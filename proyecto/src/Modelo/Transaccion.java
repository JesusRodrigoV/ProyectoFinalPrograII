package Modelo;

import java.sql.*;
import java.time.LocalDateTime;

public class Transaccion {
 private double monto;
 private String tipo;
 private Timestamp fecha;
 private int idCuentaCliente;

 public Transaccion(double monto, String tipo, int idCuentaCliente) {
     this.monto = monto;
     this.tipo = tipo;
     this.fecha = Timestamp.valueOf(LocalDateTime.now());
     this.idCuentaCliente = idCuentaCliente;
 }

 public boolean guardarTransaccion() {
     Connection conn = null;
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         conn = DriverManager.getConnection(
                 "jdbc:mysql://127.0.0.1:3306/banco?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                 "root",
                 "admin");
         conn.setAutoCommit(false);

         String query = "INSERT INTO transacciones (monto, tipo, fecha, id_cuenta_cliente) VALUES (?, ?, ?, ?)";
         try (PreparedStatement pstmt = conn.prepareStatement(query)) {
             pstmt.setDouble(1, monto);
             pstmt.setString(2, tipo);
             pstmt.setTimestamp(3, fecha);
             pstmt.setInt(4, idCuentaCliente);

             int rowsInserted = pstmt.executeUpdate();
             if (rowsInserted > 0) {
                 conn.commit();
                 return true;
             } else {
                 throw new SQLException("No se pudo insertar la transacción.");
             }
         } catch (SQLException ex) {
             if (conn != null) {
                 conn.rollback();
             }
             ex.printStackTrace();
             return false;
         } finally {
             if (conn != null) {
                 conn.setAutoCommit(true);
                 conn.close();
             }
         }
     } catch (SQLException | ClassNotFoundException ex) {
         ex.printStackTrace();
         return false;
     }
 }

 // Métodos para obtener el saldo, manejar retiros, etc. también deben ir aquí.
}

