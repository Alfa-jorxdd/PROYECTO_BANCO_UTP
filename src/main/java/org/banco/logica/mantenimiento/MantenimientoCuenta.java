package org.banco.logica.mantenimiento;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.banco.modelos.*;

import javax.swing.table.DefaultTableModel;
import org.banco.enums.EstadoCuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;
import org.banco.interfaces.Gestionable;

public class MantenimientoCuenta implements Gestionable {

    private Banco banco;
    private int[] idClientes;
    private int idCuenta;
    private TipoCuenta tipoCuenta;
    private EstadoCuenta estadoCuenta;
    private Moneda tipoMoneda;

    public MantenimientoCuenta(Banco banco) {
        this.banco = banco;
    }

    @Override
    public void agregar() {

        Cuenta nuevaCuenta = banco.agregarListaCuentas(tipoCuenta, tipoMoneda, estadoCuenta);

        if (idClientes.length > 1) {
            for (int i = 0; i < idClientes.length; i++) {
                Cliente nuevoCliente = banco.buscarIdCliente(idClientes[i]);
                banco.agregarListaCliente_Cuenta(nuevoCliente, nuevaCuenta);
            }
        } else {
            Cliente nuevoCliente = banco.buscarIdCliente(idClientes[0]);
            banco.agregarListaCliente_Cuenta(nuevoCliente, nuevaCuenta);
        }
        JOptionPane.showMessageDialog(null, "Cuenta agregada exitosamente");
    }

    @Override
    public void eliminar() {
        banco.disminuirListaCliente_CuentaPorIdCuenta(idCuenta);
        int indiceCuenta = banco.buscarIndiceCuenta(idCuenta);
        banco.disminuirListaCuentas(indiceCuenta);

        JOptionPane.showMessageDialog(null, "Cuenta eliminada exitosamente");
    }

    @Override
    public void actualizar() {
        int indiceCuenta = banco.buscarIndiceCuenta(idCuenta);
        
        banco.getCuentas()[indiceCuenta].setEstadoCuenta(estadoCuenta);
        banco.getCuentas()[indiceCuenta].setTipoMoneda(tipoMoneda);
        
        banco.disminuirListaCliente_CuentaPorIdCuenta(idCuenta);
        
        Cuenta cuenta = banco.buscarCuentaPorId(idCuenta);
        for (int i = 0; i < idClientes.length; i++) {
            Cliente nuevoCliente = banco.buscarIdCliente(idClientes[i]);
            banco.agregarListaCliente_Cuenta(nuevoCliente, cuenta);
        }
        JOptionPane.showMessageDialog(null, "Cuenta actualizada exitosamente");
    }

    @Override
    public void listar(DefaultTableModel dtm, boolean ascendete, int criterioOrden, int criterioFiltrado, String textoFiltrar) {
        dtm.setRowCount(0);
        
        List<List<Object[]>> bloque = construirBloque();
        
        bloque = filtrar(criterioFiltrado, bloque, textoFiltrar);
        
        Comparator<List<Object[]>> comparador = null;
        switch (criterioOrden) {
            case 0 ://Ordenar por ID
                comparador = (o1, o2) -> Integer.compare((int)o1.get(0)[0], (int)o2.get(0)[0]); break;
            case 1 : //Ordenar por tipo de cuenta
                comparador = (o1, o2) -> o1.get(0)[1].toString().compareTo(o2.get(0)[1].toString()); break;
            case 2 : //Ordenar por estado de cuenta
                comparador = (o1, o2) -> o1.get(0)[2].toString().compareTo(o2.get(0)[2].toString()); break;
            case 3 : //Ordenar por numero de cuenta
                comparador = (o1, o2) -> Long.compare((long)o1.get(0)[3], (long)o2.get(0)[3]); break;
            case 4 : //Ordenar por saldo
                comparador = (o1, o2) -> Double.compare((double)o1.get(0)[5], (double)o2.get(0)[5]); break;
            case 5 : //Ordenar por Moneda
                comparador = (o1, o2) -> o1.get(0)[6].toString().compareTo(o2.get(0)[6].toString()); break;
        }
        bloque.sort(ascendete ? comparador : comparador.reversed());
        
        
        
        dtm.setRowCount(0);
        for (List<Object[]> filas : bloque) {
            for (Object[] fila : filas) {
                dtm.addRow(fila);
            }
        }
    }
    
    private List<List<Object[]>> filtrar(int criterioFiltrado, List<List<Object[]>> bloque, String textoFiltrar){
        if (textoFiltrar.trim().isEmpty()) {
            return bloque;
        }
        if (criterioFiltrado > 3) {
            criterioFiltrado++;
        }
        
        List<List<Object[]>> bloqueFiltrado = new ArrayList<>();
        String texto = textoFiltrar.trim().toLowerCase();
        
        for (List<Object[]> filas : bloque) {
            boolean concide = false;
            for (Object[] fila : filas) {
                if (fila[criterioFiltrado].toString().toLowerCase().contains(texto)) {
                    concide = true;
                    break;
                }
            }
            if (concide) bloqueFiltrado.add(filas);
        }
        return bloqueFiltrado;
    }
    
    
    private List<List<Object[]>> construirBloque() {
        List<List<Object[]>> bloque = new ArrayList<>();

        for (int i = 0; i < banco.getCuentas().length - 1; i++) {
            int idCuenta = banco.getCuentas()[i].getIdCuenta();
            Cliente[] titulares = banco.buscarClientesPorIdCuenta(idCuenta);

            List<Object[]> filas = new ArrayList<>();
            for (Cliente titulare : titulares) {
                Object[] cuentaDeFila = new Object[7];
                cuentaDeFila[0] = idCuenta;
                cuentaDeFila[1] = banco.getCuentas()[i].getTipoCuenta();
                cuentaDeFila[2] = banco.getCuentas()[i].getEstadoCuenta();
                cuentaDeFila[3] = banco.getCuentas()[i].getNumeroCuenta();
                cuentaDeFila[4] = titulare.getNombres() + " " + titulare.getApellidos();
                cuentaDeFila[5] = banco.getCuentas()[i].getSaldo();
                cuentaDeFila[6] = banco.getCuentas()[i].getMoneda();
                filas.add(cuentaDeFila);
            }
            bloque.add(filas);
        }
        return bloque;
    }
    
    public DefaultListModel<String> cargarModeloLista() {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        modeloLista.removeAllElements();
        for (int i = 0; i < banco.getClientes().length - 1; i++) {
            Cliente c = banco.getClientes()[i];
            modeloLista.addElement(c.getNombres() + " " + c.getApellidos());
        }
        return modeloLista;
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
