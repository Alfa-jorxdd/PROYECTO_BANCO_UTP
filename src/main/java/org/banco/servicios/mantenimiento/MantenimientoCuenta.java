package org.banco.servicios.mantenimiento;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.banco.dao.ClienteDAO;
import org.banco.dao.Cliente_CuentaDAO;
import org.banco.dao.CuentaDAO;
import org.banco.servicios.Reporte;
import org.banco.modelos.*;

import javax.swing.table.DefaultTableModel;
import org.banco.enums.EstadoCuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;
import org.banco.interfaces.Gestionable;

import java.util.List;
import org.banco.enums.Formato;
import org.banco.enums.TipoReporte;
import org.banco.servicios.ReporteExcel;
import org.banco.servicios.ReporteHtml;
import org.banco.servicios.ReportePdf;

public class MantenimientoCuenta implements Gestionable {

    private final CuentaDAO cuentaDAO;
    private final ClienteDAO clienteDAO;
    private final Cliente_CuentaDAO cliente_cuentaDAO;
    private int[] idClientes;
    private int idCuenta;
    private TipoCuenta tipoCuenta;
    private EstadoCuenta estadoCuenta;
    private Moneda tipoMoneda;

    public MantenimientoCuenta() {
        cuentaDAO = new CuentaDAO();
        cliente_cuentaDAO = new Cliente_CuentaDAO();
        clienteDAO = new ClienteDAO();
    }

    @Override
    public void agregar() {
        Cuenta nuevaCue = null;

        switch (tipoCuenta){
            case AHORRO :
                nuevaCue = new Cuenta_Ahorro(tipoMoneda, estadoCuenta);
                break;
            case CORRIENTE :
                nuevaCue = new Cuenta_Corriente(tipoMoneda, estadoCuenta);
                break;
            case MANCOMUNADA :
                nuevaCue = new Cuenta_Mancomunada(tipoMoneda, estadoCuenta);
                break;
        }

        cuentaDAO.agregarCuenta(nuevaCue);
        int idCuenta = nuevaCue.getIdCuenta();
        for (int idCliente : idClientes) {
            cliente_cuentaDAO.agregarCliente_Cuenta(idCliente, idCuenta);
        }
        JOptionPane.showMessageDialog(null, "Cuenta agregada exitosamente");
    }

    @Override
    public void eliminar() {
        cliente_cuentaDAO.eliminarCliente_CuentaPorIdCuenta(idCuenta);
        cuentaDAO.eliminarCuenta(idCuenta);

        JOptionPane.showMessageDialog(null, "Cuenta eliminada exitosamente");
    }

    @Override
    public void actualizar() {
        Cuenta cuenta1 = cuentaDAO.buscarCuentaPorId(idCuenta);
        cuenta1.setEstadoCuenta(estadoCuenta);
        cuenta1.setTipoMoneda(tipoMoneda);
        cuentaDAO.actualizarCuenta(cuenta1);

        cliente_cuentaDAO.eliminarCliente_CuentaPorIdCuenta(idCuenta);

        for (int idCliente : idClientes) {
            cliente_cuentaDAO.agregarCliente_Cuenta(idCliente, idCuenta);
        }
    }

    @Override
    public void listar(DefaultTableModel dtm, boolean ascendete, int criterioOrden, int criterioFiltrado, String textoFiltrar) {
        cuentaDAO.listarCuentas(dtm, ascendete, criterioOrden, criterioFiltrado, textoFiltrar);
    }
    
    public void generarReporte(String nombre, Formato formato, boolean ascendete, int criterioOrden, int criterioFiltrado, String textoFiltrar){
        Reporte nuevoReporte = null;
        switch(formato){
            case PDF :
                nuevoReporte = new ReportePdf(nombre, TipoReporte.CUENTAS);
                break;
            case EXCEL :
                nuevoReporte = new ReporteExcel(nombre, TipoReporte.CUENTAS);
                break;
            case HTML :
                nuevoReporte = new ReporteHtml(nombre, TipoReporte.CUENTAS);
                break;
        }
        List<Object[]> lista = cuentaDAO.listarCuentas(ascendete, criterioOrden, criterioFiltrado, textoFiltrar);
        nuevoReporte.crearReporte(lista);
    }

    public DefaultListModel<String> filtrarTitulares(String texto) {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        List<Cliente> clientes = clienteDAO.filtrarClientes(texto);
        for (Cliente c : clientes) {
            modeloLista.addElement(c.getIdCliente() + ". " + c.getNombres() + " " + c.getApellidos());
        }

        return modeloLista;
    }

    public void recibirIds(DefaultListModel<String> modeloTitulares){
        int[] localIdClientes = new int[modeloTitulares.getSize()];
        if (modeloTitulares.getSize() > 1) {
            for (int i = 0; i < localIdClientes.length; i++) {
                int id = Integer.parseInt(modeloTitulares.getElementAt(i).split("\\.")[0]);
                localIdClientes[i] = id;
                System.out.println(id);
            }
            setIdClientes(localIdClientes);
        } else {
            int id = Integer.parseInt(modeloTitulares.firstElement().split("\\.")[0]);
            localIdClientes[0] = id;
            setIdClientes(localIdClientes);
            System.out.println(id);
        }
    }

    public List<Cliente> buscarTitularesCuenta(int idCuenta) {
        return clienteDAO.buscarClientesPorIdCuenta(idCuenta);
    }

    public void setDatosCuenta(TipoCuenta tipoCuenta, EstadoCuenta estadoCuenta, Moneda tipoMoneda) {
        this.tipoCuenta = tipoCuenta;
        this.estadoCuenta = estadoCuenta;
        this.tipoMoneda = tipoMoneda;
    }

    public void setIdClientes(int[] idClientes) {
        this.idClientes = idClientes;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
}
