package org.banco.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xddf.usermodel.PresetPattern;
import org.banco.config.ConexionSQLServer;
import org.banco.enums.Formato;
import org.banco.modelos.Cliente;

public class ClienteDAO {
    
    public void agregarCliente(Cliente cliente){
        String query = "{call paInsertarCliente(?, ?, ?, ?, ?)}";
        
        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)){
            cs.setString(1, cliente.getNombres());
            cs.setString(2, cliente.getApellidos());
            cs.setInt(3, cliente.getDni());
            cs.setInt(4, cliente.getTelefono());
            cs.setString(5, cliente.getCorreo());
            cs.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente", "Éxito", JOptionPane.DEFAULT_OPTION);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void eliminarCliente(int idCliente){
        String query = "{call paEliminarCliente(?)}";
        
        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {
            
            cs.setInt(1, idCliente);
            cs.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente", "Éxito", JOptionPane.DEFAULT_OPTION);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void actualizarCliente(Cliente cliente){
        String query = "{call paActualizarCliente(?,?,?,?,?,?)}";
        
        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {
            
            cs.setInt(1, cliente.getIdCliente());
            cs.setString(2, cliente.getNombres());
            cs.setString(3, cliente.getApellidos());
            cs.setInt(4, cliente.getDni());
            cs.setInt(5, cliente.getTelefono());
            cs.setString(6, cliente.getCorreo());
            cs.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente", "Éxito", JOptionPane.DEFAULT_OPTION);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public Cliente buscarClientePorId(int idCliente){
        String query = "{call paBuscarCliente(?)}";
        
        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {
            
            cs.setInt(1, idCliente);
            
            try(ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("idCliente"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getInt("dni"),
                            rs.getInt("telefono"),
                            rs.getString("correo")
                    );
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public void listarClientes(DefaultTableModel dtm, boolean ascendente, int criterioOrden, int criterioFiltrar, String textoFiltrado) {
        String[] columnas = {"idCliente", "nombres", "apellidos", "dni", "telefono", "correo"};
        String orden = columnas[criterioOrden];
        String filtrar = columnas[criterioFiltrar];
        String direccion = ascendente ? "ASC" : "DESC";

        String query = "SELECT * FROM Cliente WHERE " + filtrar + " LIKE ? ORDER BY " + orden + " " + direccion;

        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {

            pt.setString(1, "%" + textoFiltrado + "%");

            try(ResultSet rs = pt.executeQuery()) {
                dtm.setRowCount(0);
                while (rs.next()){
                    Object[] obj = {
                            rs.getInt("idCliente"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getInt("dni"),
                            rs.getInt("telefono"),
                            rs.getString("correo")
                    };
                    dtm.addRow(obj);
                }

            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e){
           JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }

    public List<Object[]> listarClientes(boolean ascendente, int criterioOrden, int criterioFiltrar, String textoFiltrado){
        String[] columnas = {"idCliente", "nombres", "apellidos", "dni", "telefono", "correo"};
        String orden = columnas[criterioOrden];
        String filtrar = columnas[criterioFiltrar];
        String direccion = ascendente ? "ASC" : "DESC";

        String query = "SELECT * FROM Cliente WHERE " + filtrar + " LIKE ? ORDER BY " + orden + " " + direccion;

        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {

            pt.setString(1, "%" + textoFiltrado + "%");

            try(ResultSet rs = pt.executeQuery()) {
                List<Object[]> lista = new ArrayList<>();
                while (rs.next()){
                    Object[] obj = {
                            rs.getInt("idCliente"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getInt("dni"),
                            rs.getInt("telefono"),
                            rs.getString("correo")
                    };
                    lista.add(obj);
                }
                return lista;

            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public int contarClientes(){
        String query = "SELECT COUNT(idCliente) FROM Cliente";

        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query);
            ResultSet rs = pt.executeQuery()) {
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return -1;
    }

    public List<Cliente> filtrarClientes(String texto) {
        List<Cliente> resultado = new ArrayList<>();
        String query = "SELECT idCliente, nombres, apellidos FROM Cliente WHERE (nombres + ' ' + apellidos) LIKE ?";

        try (PreparedStatement ps = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {
            ps.setString(1, "%" + texto + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente c = new Cliente(rs.getInt("idCliente"), rs.getString("nombres"), rs.getString("apellidos"), 0, 0, "");
                    resultado.add(c);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
        }

        return resultado;
    }

    public List<String> filtrarNombresTitularesSegunNumeroCuenta(long numeroCuenta){
        String query = "SELECT  SUBSTRING(cl.nombres, 1, CHARINDEX(' ', cl.nombres) - 1) AS primerNombre, " +
                       "SUBSTRING(cl.apellidos, 1, CHARINDEX(' ', cl.apellidos ) - 1) AS primerApellido, " +
                       "cu.numeroCuenta " +
                       "FROM Cliente cl " +
                       "INNER JOIN Cliente_Cuenta cc ON cc.idCliente = cl.idCliente " +
                       "INNER JOIN Cuenta cu ON cc.idCuenta = cu.idCuenta " +
                       "WHERE cu.numeroCuenta LIKE ?";
        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {

            pt.setString(1, "%" + numeroCuenta + "%");

            List<String> nombres = new ArrayList<>();
            try(ResultSet rs = pt.executeQuery()) {
                while (rs.next()){
                    String x = rs.getString("primerNombre") + " " + rs.getString("primerApellido") + " - " + rs.getLong("numeroCuenta") ;
                    nombres.add(x);
                }
                return nombres;
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public List<String> filtrarNombresClientesPorDni(int dni){
        String query =  "SELECT SUBSTRING(cl.nombres, 1, CHARINDEX(' ', cl.nombres) - 1) AS primerNombre, " +
                        "SUBSTRING(cl.apellidos, 1, CHARINDEX(' ', cl.apellidos) - 1) AS primerApellido, " +
                        "cl.dni " +
                        "FROM Cliente cl " +
                        "WHERE cl.dni LIKE ?";
        try(PreparedStatement ps = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)){

            ps.setString(1, "%" + dni + "%");

            List<String> nombres = new ArrayList<>();
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    String x = rs.getString("primerNombre") + " " + rs.getString("primerApellido") + " - " + rs.getInt("dni");
                    nombres.add(x);
                }
                return nombres;
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public List<Cliente> buscarClientesPorIdCuenta(int idCuenta){
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * " +
                "FROM Cliente_Cuenta cc " +
                "INNER JOIN Cliente cl ON cl.idCliente = cc.idCliente " +
                "INNER JOIN Cuenta cu ON cu.idCuenta = cc.idCuenta " +
                "WHERE cu.idCuenta = ?";
        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)){

            pt.setInt(1, idCuenta);

            try(ResultSet rs = pt.executeQuery()) {
                while (rs.next()){
                    Cliente c = new Cliente(
                            rs.getInt("idCliente"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getInt("dni"),
                            rs.getInt("telefono"),
                            rs.getString("correo")
                    );
                    clientes.add(c);
                }
                return clientes;
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public void listarUltimos20Clientes(DefaultTableModel dtm){
        String query = "SELECT TOP 20 cl.idCliente, cl.nombres, ISNULL(cu.tipoCuenta, 'SIN CUENTA') AS tipoCuenta, ISNULL(cu.estadoCuenta, 'SIN ESTADO') AS estadoCuenta " +
                "FROM Cliente cl " +
                "LEFT JOIN Cliente_Cuenta cc ON cc.idCliente = cl.idCliente " +
                "LEFT JOIN Cuenta cu ON cc.idCuenta = cu.idCuenta " +
                "ORDER BY cl.idCliente DESC";

        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query);
            ResultSet rs = pt.executeQuery()){
            dtm.setRowCount(0);
            while (rs.next()){
                Object[] obj = {
                        rs.getInt("idCliente"),
                        rs.getString("nombres"),
                        rs.getString("tipoCuenta"),
                        rs.getString("estadoCuenta")
                };
                dtm.addRow(obj);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
        }

    }
}
