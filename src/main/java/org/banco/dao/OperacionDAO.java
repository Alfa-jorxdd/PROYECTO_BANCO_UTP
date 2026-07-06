package org.banco.dao;

import org.apache.poi.sl.draw.geom.GuideIf;
import org.banco.config.ConexionSQLServer;
import org.banco.enums.Moneda;
import org.banco.enums.TipoOperacion;
import org.banco.modelos.Operacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OperacionDAO {

    private static final Logger log = LoggerFactory.getLogger(OperacionDAO.class);

    public void agregarOperacion(Operacion operacion){
        String query = "{call paInsertarOperacion(?,?,?,?,?,?,?,?)}";

        try(CallableStatement cs = ConexionSQLServer.getInstancia().getConexion().prepareCall(query)) {

            switch (operacion.getOperacion()){
                case DEPOSITO, RETIRO :
                    cs.setInt(1, operacion.getIdCuentaOrigen());
                    cs.setLong(2, operacion.getNumeroCuentaOrigen());
                    cs.setNull(3, Types.INTEGER);  // ← idCuentaDestino es INT
                    cs.setNull(4, Types.BIGINT);   // ← numeroCuentaDestino es BIGINT
                    cs.setInt(5, operacion.getDni());
                    cs.setString(6, operacion.getOperacion().toString());
                    cs.setDouble(7, operacion.getMonto());
                    cs.setString(8, operacion.getMoneda().toString());
                    break;
                case TRANSFERENCIA_ENVIADA, TRANSFERENCIA_RECIBIDA :
                    cs.setInt(1, operacion.getIdCuentaOrigen());
                    cs.setLong(2, operacion.getNumeroCuentaOrigen());
                    cs.setInt(3, operacion.getIdCuentaDesitino());
                    cs.setLong(4, operacion.getNumeroCuentaDestino());
                    cs.setInt(5, operacion.getDni());
                    cs.setString(6, operacion.getOperacion().toString());
                    cs.setDouble(7, operacion.getMonto());
                    cs.setString(8, operacion.getMoneda().toString());
                    break;
                case CONSULTA :
                    cs.setInt(1, operacion.getIdCuentaOrigen());
                    cs.setLong(2, operacion.getNumeroCuentaOrigen());
                    cs.setNull(3, Types.INTEGER);
                    cs.setNull(4, Types.BIGINT);   // ← numeroCuentaDestino
                    cs.setNull(5, Types.INTEGER);  // ← dni
                    cs.setString(6, operacion.getOperacion().toString());
                    cs.setNull(7, Types.DOUBLE);   // ← monto (FLOAT en SQL = DOUBLE en JDBC)
                    cs.setNull(8, Types.VARCHAR);  // ← tipoMoneda
                    break;
            }

            cs.execute();

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR en agregar" , JOptionPane.WARNING_MESSAGE);
        }
    }

    public void listarOperaciones(DefaultTableModel dtm, boolean ascendente, int criterioOrden, int criterioFiltrar, String textoFiltrado){
        String[] columnas = {"idOperacion", "fechaOperacion", "tipoOperacion"};
        String orden = columnas[criterioOrden];
        String filtrar = columnas[criterioFiltrar];
        String direccion = ascendente ? "ASC" : "DESC";

        String query = "SELECT idOperacion, fechaOperacion, tipoOperacion " +
                        "FROM Operacion " +
                        "WHERE " + filtrar + " LIKE ? " +
                        "ORDER BY " + orden + " " + direccion;

        try(PreparedStatement ps = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {
            ps.setString(1, "%" + textoFiltrado + "%");
            try(ResultSet rs = ps.executeQuery()) {
                dtm.setRowCount(0);
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", new Locale("es", "PE"));
                while (rs.next()){
                    LocalDateTime fecha = rs.getObject("fechaOperacion", LocalDateTime.class);
                    Object[] obj = {
                            rs.getInt("idOperacion"),
                            formatoFecha.format(fecha),
                            rs.getString("tipoOperacion")
                    };
                    dtm.addRow(obj);
                }
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
    }

    public List<Object[]> listarOperaciones(boolean ascendente, int criterioOrden, int criterioFiltrar, String textoFiltrado){
        String[] columnas = {"idOperacion", "fechaOperacion", "tipoOperacion"};
        String orden = columnas[criterioOrden];
        String filtrar = columnas[criterioFiltrar];
        String direccion = ascendente ? "ASC" : "DESC";

        String query = "SELECT * " +
                "FROM Operacion " +
                "WHERE " + filtrar + " LIKE ? " +
                "ORDER BY " + orden + " " + direccion;

        try(PreparedStatement ps = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {
            ps.setString(1, "%" + textoFiltrado + "%");
            try(ResultSet rs = ps.executeQuery()) {
                List<Object[]> lista = new ArrayList<>();
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", new Locale("es", "PE"));
                while (rs.next()){
                    LocalDateTime fecha = rs.getObject("fechaOperacion", LocalDateTime.class);
                    String fechaFormateada = (fecha != null) ? formatoFecha.format(fecha) : null;

                    double saldoDouble = rs.getDouble("monto");
                    BigDecimal saldoRedondeado = rs.wasNull() ? null : BigDecimal.valueOf(saldoDouble).setScale(2, RoundingMode.HALF_UP);

                    int idOperacion = rs.getInt("idOperacion");
                    Integer idOperacionObj = rs.wasNull() ? null : idOperacion;

                    long numCuentaOrigen = rs.getLong("numeroCuentaOrigen");
                    Long numCuentaOrigenObj = rs.wasNull() ? null : numCuentaOrigen;

                    long numCuentaDestino = rs.getLong("numeroCuentaDestino");
                    Long numCuentaDestinoObj = rs.wasNull() ? null : numCuentaDestino;

                    int dni = rs.getInt("dni");
                    Integer dniObj = rs.wasNull() ? null : dni;

                    String tipoMoneda = rs.getString("tipoMoneda");
                    String tipoOperacion = rs.getString("tipoOperacion");

                    Object[] obj = {
                            idOperacionObj,
                            numCuentaOrigenObj,
                            numCuentaDestinoObj,
                            dniObj,
                            saldoRedondeado,
                            tipoMoneda,
                            fechaFormateada,
                            tipoOperacion
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

    public int contarOperaciones(){
        String query = "SELECT COUNT(idOperacion) FROM Operacion";

        try(PreparedStatement ps = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return -1;
    }

    public Operacion buscarOperacionPorId(int idOperacion){
        String query = "SELECT * FROM Operacion WHERE idOperacion = ?";

        try(PreparedStatement ps = ConexionSQLServer.getInstancia().getConexion().prepareStatement(query)) {

            ps.setInt(1, idOperacion);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    return mapearOperacion(rs);
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR" , JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    private Operacion mapearOperacion(ResultSet rs) throws SQLException {
        TipoOperacion tipoOperacion = TipoOperacion.valueOf(rs.getString("tipoOperacion"));

        switch (tipoOperacion){
            case DEPOSITO, RETIRO :
                return new Operacion(
                        rs.getInt("idOperacion"),
                        rs.getInt("idCuentaOrigen"),
                        rs.getLong("numeroCuentaOrigen"),
                        rs.getInt("dni"),
                        tipoOperacion,
                        rs.getTimestamp("fechaOperacion").toLocalDateTime(),
                        rs.getDouble("monto"),
                        Moneda.valueOf(rs.getString("tipoMoneda"))
                );
            case TRANSFERENCIA_ENVIADA, TRANSFERENCIA_RECIBIDA :
                return new Operacion(
                        rs.getInt("idOperacion"),
                        rs.getInt("idCuentaOrigen"),
                        rs.getInt("idCuentaDestino"),
                        rs.getLong("numeroCuentaOrigen"),
                        rs.getLong("numeroCuentaDestino"),
                        rs.getInt("dni"),
                        tipoOperacion,
                        rs.getTimestamp("fechaOperacion").toLocalDateTime(),
                        Moneda.valueOf(rs.getString("tipoMoneda")),
                        rs.getDouble("monto")
                );
            case CONSULTA :
                return new Operacion(
                        rs.getInt("idOperacion"),
                        rs.getInt("idCuentaOrigen"),
                        tipoOperacion,
                        rs.getLong("numeroCuentaOrigen"),
                        rs.getTimestamp("fechaOperacion").toLocalDateTime()
                );
        }

        return null;
    }
}
