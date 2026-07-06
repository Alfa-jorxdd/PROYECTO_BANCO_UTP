package org.banco.logica.mantenimiento;
import org.banco.enums.TipoReporte;
import org.banco.logica.ReporteExcel;
import org.banco.logica.ReporteHtml;
import org.banco.logica.ReportePdf;
import org.banco.modelos.*;

import javax.swing.table.DefaultTableModel;
import org.banco.dao.ClienteDAO;
import org.banco.enums.Formato;
import org.banco.interfaces.Gestionable;

import java.util.List;

public class MantenimientoCliente implements Gestionable {

    private final ClienteDAO clienteDao;
    private int idCliente;
    private String nombres;
    private String apellidos;
    private int dni;
    private int telefono;
    private String correo;

    public MantenimientoCliente() {
        this.clienteDao = new ClienteDAO();
    }

    @Override
    public void agregar() {
        Cliente nuevoCliente = new Cliente(nombres, apellidos, dni, telefono, correo);
        clienteDao.agregarCliente(nuevoCliente);
    }

    @Override
    public void eliminar() {
        clienteDao.eliminarCliente(idCliente);
    }

    @Override
    public void actualizar() {
        Cliente cliente = new Cliente(idCliente, nombres, apellidos, dni, telefono, correo);
        clienteDao.actualizarCliente(cliente);
    }

    @Override
    public void listar(DefaultTableModel dtm, boolean ascendente, int criterioOrden, int criterioFiltrar, String textoFiltrado) {
        clienteDao.listarClientes(dtm, ascendente, criterioOrden, criterioFiltrar, textoFiltrado);
    }
    
    public void generarReporte(String nombre, Formato formato, boolean ascendente, int criterioOrden, int criterioFiltrar, String textoFiltrado){
        Reporte nuevoReporte = null;
        switch(formato){
            case PDF :
                nuevoReporte = new ReportePdf(nombre, TipoReporte.CLIENTES);
                break;
            case EXCEL :
                nuevoReporte = new ReporteExcel(nombre, TipoReporte.CLIENTES);
                break;
            case HTML :
                nuevoReporte = new ReporteHtml(nombre, TipoReporte.CLIENTES);
                break;
        }
        List<Object[]> lista = clienteDao.listarClientes(ascendente, criterioOrden, criterioFiltrar, textoFiltrado);
        nuevoReporte.crearReporte(lista);
    }

    public void setDatosCliente(String nombres, String apellidos, int DNI, int telefono, String correo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = DNI;
        this.telefono = telefono;
        this.correo = correo;

    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
