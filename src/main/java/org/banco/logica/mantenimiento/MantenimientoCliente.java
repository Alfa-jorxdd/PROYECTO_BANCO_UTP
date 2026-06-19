package org.banco.logica.mantenimiento;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.banco.modelos.*;

import org.banco.modelos.Banco;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.banco.interfaces.Gestionable;

public class MantenimientoCliente implements Gestionable {

    private Banco banco;
    private int idCliente;
    private String nombres;
    private String apellidos;
    private int DNI;
    private int telefono;
    private String correo;

    public MantenimientoCliente(Banco banco) {
        this.banco = banco;
    }

    @Override
    public void agregar() {
        banco.agregarListaClientes(nombres, apellidos, DNI, telefono, correo);
    }

    @Override
    public void eliminar() {

        Cuenta[] cuentas = banco.buscarCuentasporIdCLiente(idCliente);
        for (int i = 0; i < cuentas.length; i++) {
            Cliente[] titulares = banco.buscarClientesPorIdCuenta(cuentas[i].getIdCuenta());

            if (titulares.length == 2) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar a un cliente que forma parte de una cuenta mancomunada de 2 titulares. Por favor, de primero elimine la cuenta", "Error al eliminar cliente", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        int indiceCliente = banco.buscarIndiceCliente(idCliente);

        banco.disminuirListaCliente_CuentaPorIdCliente(idCliente);

        for (int i = 0; i < cuentas.length; i++) {
            Cliente[] clientesRestantes = banco.buscarClientesPorIdCuenta(cuentas[i].getIdCuenta());
            if (clientesRestantes.length == 0) {
                banco.disminuirListaCuentas(banco.buscarIndiceCuenta(cuentas[i].getIdCuenta()));
            }
        }

        banco.disminuirListaClientes(indiceCliente);
    }

    @Override
    public void actualizar() {
        int indiceCliente = banco.buscarIndiceCliente(idCliente);
        banco.getClientes()[indiceCliente].setNombres(nombres);
        banco.getClientes()[indiceCliente].setApellidos(apellidos);
        banco.getClientes()[indiceCliente].setDni(DNI);
        banco.getClientes()[indiceCliente].setTelefono(telefono);
        banco.getClientes()[indiceCliente].setCorreo(correo);
    }

    @Override
    public void listar(DefaultTableModel dtm, boolean ascendente, int criterioOrden, int criterioFiltrar, String textoFiltrado) {
        List<List<Object[]>> bloque = construirBloque();
        bloque = filtrar(bloque, criterioFiltrar, textoFiltrado);
        
        Comparator<List<Object[]>> comparador = null;
        switch(criterioOrden){
            case 0: 
                comparador = (o1, o2) -> Integer.compare((int)o1.get(0)[0], (int)o2.get(0)[0]); break;
            case 1 :
                comparador = (o1, o2) -> o1.get(0)[1].toString().compareTo(o2.get(0)[1].toString()); break;
            case 2 :
                comparador = (o1, o2) -> o1.get(0)[2].toString().compareTo(o2.get(0)[2].toString()); break;
            case 3 :
                comparador = (o1, o2) -> Integer.compare((int)o1.get(0)[3], (int)o2.get(0)[3]); break;
            case 4 :
                comparador = (o1, o2) -> Integer.compare((int)o1.get(0)[4], (int)o2.get(0)[4]); break;
            case 5 : 
                comparador = (o1, o2) -> o1.get(0)[5].toString().compareTo(o2.get(0)[5].toString()); break;
        }
        
        bloque.sort(ascendente ? comparador : comparador.reversed());
        
        dtm.setRowCount(0);
        for (List<Object[]> filas : bloque) {
            for (Object[] objects : filas) {
                dtm.addRow(objects);
            }
        }
        
    }
    
    private List<List<Object[]>> filtrar(List<List<Object[]>> bloque, int criterioFiltrar, String textoFiltrar){
        if (textoFiltrar.trim().isEmpty()) {
            return bloque;
        }
        List<List<Object[]>> bloqueFiltrado = new ArrayList<>();
        String texto = textoFiltrar.toLowerCase().trim();
        
        for (List<Object[]> filas : bloque) {
            boolean coincide = false;
            for (Object[] fila : filas) {
                if (fila[criterioFiltrar].toString().toLowerCase().contains(texto)) {
                    coincide = true;
                    break;
                }
            }
            if (coincide) bloqueFiltrado.add(filas);
        }
        return bloqueFiltrado;
    }
    
    private List<List<Object[]>> construirBloque(){
        List<List<Object[]>> bloque = new ArrayList<>();
        
        for (int i = 0; i < banco.getClientes().length - 1; i++) {
            List<Object[]> filas = new ArrayList<>();
            Object[] clienteDeFila = new Object[6];
            clienteDeFila[0] = banco.getClientes()[i].getIdCliente();
            clienteDeFila[1] = banco.getClientes()[i].getNombres();
            clienteDeFila[2] = banco.getClientes()[i].getApellidos();
            clienteDeFila[3] = banco.getClientes()[i].getDni();
            clienteDeFila[4] = banco.getClientes()[i].getTelefono();
            clienteDeFila[5] = banco.getClientes()[i].getCorreo();
            
            filas.add(clienteDeFila);
            bloque.add(filas);
        }
        return bloque;
    }

    public void setDatosCliente(String nombres, String apellidos, int DNI, int telefono, String correo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.DNI = DNI;
        this.telefono = telefono;
        this.correo = correo;

    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
