package org.banco.dao;

import org.banco.config.ConexionSQLServer;
import org.banco.enums.EstadoCuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;
import org.banco.modelos.Cuenta;
import org.banco.modelos.Cuenta_Ahorro;
import org.banco.modelos.Cuenta_Corriente;
import org.banco.modelos.Cuenta_Mancomunada;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {

    public void agregarCuenta(Cuenta cuenta) {
        String query = "{call paInsertarCuenta(?,?,?,?,?,?,?)}";

        while(true){
            try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {
                cs.setDouble(1, cuenta.getSaldo());
                cs.setLong(2, cuenta.getNumeroCuenta());
                cs.setString(3, cuenta.getEstadoCuenta().toString());
                cs.setString(4, cuenta.getMoneda().toString());
                cs.setString(5, cuenta.getTipoCuenta().toString());

                cs.registerOutParameter(6, Types.INTEGER);
                cs.registerOutParameter(7, Types.INTEGER);

                cs.executeUpdate();
                System.out.println("Hay hasta aquí? 111");
                if (cs.getInt(6) == 1) {
                    cuenta.cambiarNumeroCuenta();
                } else {
                    cuenta.setIdCuenta(cs.getInt(7));
                    System.out.println("DAO");
                    System.out.println(cuenta.getIdCuenta());
                    break;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
    }

    public void eliminarCuenta(int idCuenta){
        String query = "{call paEliminarCuenta(?)}";

        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {

            cs.setInt(1, idCuenta);
            cs.executeUpdate();

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }

    public void actualizarCuenta(Cuenta cuenta) {
        String query = "{call paActualizarCuenta(?,?,?,?)}";

        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {

            cs.setInt(1, cuenta.getIdCuenta());
            cs.setString(2, cuenta.getEstadoCuenta().toString());
            cs.setString(3, cuenta.getTipoMoneda().toString());
            cs.setDouble(4, cuenta.getSaldo());
            cs.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }

    public Cuenta buscarCuentaPorId(int idCuenta) {
        String query = "{call paBuscarCuentaPorId(?)}";

        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {

            cs.setInt(1, idCuenta);

            try(ResultSet rs = cs.executeQuery()) {
                if (rs.next()){
                    return mapearCuenta(rs);
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public Cuenta buscarCuentaPorNumeroCuenta(long numeroCuenta){
        String query = "{call paBuscarCuentaPorNumeroCuenta(?)}";

        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)){

            cs.setLong(1, numeroCuenta);

            try(ResultSet rs = cs.executeQuery()) {
                if (rs.next()){
                    return mapearCuenta(rs);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    private Cuenta mapearCuenta(ResultSet rs) throws SQLException {
        int idCuenta = rs.getInt("idCuenta");
        TipoCuenta tipoCuenta = TipoCuenta.valueOf(rs.getString("tipoCuenta"));
        Moneda moneda = Moneda.valueOf(rs.getString("tipoMoneda"));
        EstadoCuenta estado = EstadoCuenta.valueOf(rs.getString("estadoCuenta"));
        long numeroCuenta = rs.getLong("numeroCuenta");
        double saldo = rs.getDouble("saldo");

        Cuenta cuenta;
        switch (tipoCuenta) {
            case AHORRO:
                cuenta = new Cuenta_Ahorro(idCuenta, moneda, estado, numeroCuenta);
                break;
            case CORRIENTE:
                cuenta = new Cuenta_Corriente(idCuenta, moneda, estado, numeroCuenta);
                break;
            case MANCOMUNADA:
                cuenta = new Cuenta_Mancomunada(idCuenta, moneda, estado, numeroCuenta);
                break;
            default:
                throw new IllegalStateException("Tipo de cuenta no reconocido");
        }
        cuenta.setSaldo(saldo);
        return cuenta;
    }

    public void listarCuentas(DefaultTableModel dtm, boolean ascendete, int criterioOrden, int criterioFiltrado, String textoFiltrar) {
        String[] columnas = {"idCuenta", "tipoCuenta", "estadoCuenta", "numeroCuenta", "saldo", "tipoMoneda"};
        String filtrar = columnas[criterioFiltrado];
        String orden = columnas[criterioOrden];
        String direccion = ascendete ? "ASC" : "DESC";

        String query = "SELECT cu.idCuenta, cu.tipoCuenta, cu.estadoCuenta, cl.nombres + ' ' + cl.apellidos AS Titulares, cu.numeroCuenta, cu.saldo, cu.tipoMoneda " +
                "FROM Cliente_Cuenta cc " +
                "INNER JOIN Cliente cl ON cl.idCliente = cc.idCliente " +
                "INNER JOIN Cuenta cu ON cu.idCuenta = cc.idCuenta " +
                "WHERE cu." + filtrar + " LIKE ? " +
                "ORDER BY " + orden + " " + direccion;

        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {

            pt.setString(1, "%" + textoFiltrar + "%");

            try(ResultSet rs = pt.executeQuery()){
                dtm.setRowCount(0);
                while (rs.next()){
                    BigDecimal saldoRedondeado = new BigDecimal(rs.getDouble("saldo"));
                    Object[] obj = {
                            rs.getInt("idCuenta"),
                            rs.getString("tipoCuenta"),
                            rs.getString("estadoCuenta"),
                            rs.getLong("numeroCuenta"),
                            rs.getString("Titulares"),
                            saldoRedondeado.setScale(2, RoundingMode.HALF_UP),
                            rs.getString("tipoMoneda")
                    };
                    dtm.addRow(obj);
                }
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }

    public List<Object[]> listarCuentas(boolean ascendete, int criterioOrden, int criterioFiltrado, String textoFiltrar) {
        String[] columnas = {"idCuenta", "tipoCuenta", "estadoCuenta", "numeroCuenta", "saldo", "tipoMoneda"};
        String filtrar = columnas[criterioFiltrado];
        String orden = columnas[criterioOrden];
        String direccion = ascendete ? "ASC" : "DESC";

        String query = "SELECT cu.idCuenta, cu.tipoCuenta, cu.estadoCuenta, cl.nombres + ' ' + cl.apellidos AS Titulares, cu.numeroCuenta, cu.saldo, cu.tipoMoneda " +
                "FROM Cliente_Cuenta cc " +
                "INNER JOIN Cliente cl ON cl.idCliente = cc.idCliente " +
                "INNER JOIN Cuenta cu ON cu.idCuenta = cc.idCuenta " +
                "WHERE cu." + filtrar + " LIKE ? " +
                "ORDER BY " + orden + " " + direccion;

        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {

            pt.setString(1, "%" + textoFiltrar + "%");

            try(ResultSet rs = pt.executeQuery()){
                List<Object[]> lista = new ArrayList<>();
                while (rs.next()){
                    BigDecimal saldoRedondeado = new BigDecimal(rs.getDouble("saldo"));
                    Object[] obj = {
                            rs.getInt("idCuenta"),
                            rs.getString("tipoCuenta"),
                            rs.getString("estadoCuenta"),
                            rs.getLong("numeroCuenta"),
                            rs.getString("Titulares"),
                            saldoRedondeado.setScale(2, RoundingMode.HALF_UP),
                            rs.getString("tipoMoneda")
                    };
                    lista.add(obj);
                }
                return lista;
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public int contarCuentas(){
        String query = "SELECT COUNT(idCuenta) FROM Cuenta";

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

    public BigDecimal contarSaldoDeTodasLasCuentas(String simboloMoneda){
        String moneda = simboloMoneda.equals("S/") ? "SOL" : "DOLAR";

        String query = "SELECT SUM(saldo) FROM Cuenta WHERE tipoMoneda = ?";

        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {

            pt.setString(1, moneda);

            try(ResultSet rs = pt.executeQuery()) {
                if (rs.next()){
                    BigDecimal saldoRedondeado = new BigDecimal(rs.getDouble(1));
                    return saldoRedondeado.setScale(2, RoundingMode.HALF_UP);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public int operacionesRealizadasHoy(int idCuenta){
        String query = "SELECT COUNT(*) AS cantidad " +
                "FROM Operacion " +
                "WHERE idCuentaOrigen = ? " +
                "AND CAST(fechaOperacion AS DATE) = CAST(GETDATE() AS DATE) " +
                "AND tipoOperacion IN ('DEPOSITO', 'RETIRO', 'TRANSFERENCIA_ENVIADA')";

        try(PreparedStatement pt = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {

            pt.setInt(1, idCuenta);
            try(ResultSet rs = pt.executeQuery()) {
                if (rs.next()){
                    return rs.getInt("cantidad");
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return -1;
    }
}
