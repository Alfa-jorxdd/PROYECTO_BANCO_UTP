package org.banco.dao;

import org.banco.config.ConexionSQLServer;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class Cliente_CuentaDAO {

    public void agregarCliente_Cuenta(int idCliente, int idCuenta){
        String query = "{call paInsertarCliente_Cuenta(?, ?)}";

        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {

            cs.setInt(1, idCliente);
            cs.setInt(2, idCuenta);
            cs.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }

    public void eliminarCliente_CuentaPorIdCuenta(int idCuenta){
        String query = "{call paEliminarCliente_CuentaPorIdCuenta(?)}";

        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {

            cs.setInt(1,idCuenta);
            cs.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }

    public void eliminarCliente_CuentaPorIdCliente(int idClienta){
        String query = "{call paEliminarCliente_CuentaPorIdCliente(?)}";

        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {

            cs.setInt(1,idClienta);
            cs.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }

}
