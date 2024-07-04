package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ControladorReporte {
    private int id;
    private int id_cuenta;
    private Connection conn;

    public ControladorReporte(int id, int id_cuenta) {
        this.id = id;
        this.id_cuenta = id_cuenta;
        try {
            this.conn = ConexionBD.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getReportData() {
        Statement stmt = null;
        Object[][] data = new Object[0][];
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT fecha, tipo, monto FROM banco.transacciones WHERE id_cuenta_cliente = " + id_cuenta;
            ResultSet rs = stmt.executeQuery(query);

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new Object[rowCount][3];
            int i = 0;

            while (rs.next()) {
                data[i][0] = rs.getString("fecha");
                data[i][1] = rs.getString("tipo");
                data[i][2] = rs.getDouble("monto");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener el reporte de cuenta.");
        }
        return data;
    }
    public String saldo() {
    	double saldo = 0;
        try{
            String query = "SELECT monto, tipo FROM banco.transacciones WHERE id_cuenta_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id_cuenta);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        double elMonto = rs.getDouble("monto");
                        String tipo = rs.getString("tipo");
                        switch (tipo) {
                            case "deposito":
                                saldo += elMonto;
                                break;
                            case "retiro":
                                saldo -= elMonto;
                                break;
                        }
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Class error");
        } 
    	return Double.toString(saldo);
    }
}
