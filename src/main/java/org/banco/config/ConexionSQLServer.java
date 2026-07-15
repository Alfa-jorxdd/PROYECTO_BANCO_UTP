package org.banco.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionSQLServer {
    private Connection conexion = null;
    private static ConexionSQLServer instancia = null;
    private final String usuario = "userSQL";
    private final String contraseña = "root";
    private final String cadenaConexion = "jdbc:sqlserver://localhost:1433;databaseName=Banco;encrypt=true;trustServerCertificate=true";
    
    private ConexionSQLServer (){
        try {
            conexion = DriverManager.getConnection(cadenaConexion, usuario, contraseña);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public static ConexionSQLServer getInstancia(){
        if (instancia == null) {
            instancia = new ConexionSQLServer();
        }
        return instancia;
    }
    
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar: " + e);
        }
    }

    public Connection getConexion(){
        return this.conexion;
    }
}
