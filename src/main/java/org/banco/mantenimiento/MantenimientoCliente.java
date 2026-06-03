package org.banco.mantenimiento;

import org.banco.modelos.*;

import org.banco.modelos.Banco;

import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.banco.modelos.interfaces.Gestionable;

public class MantenimientoCliente implements Gestionable {

    private Scanner sc = new Scanner(System.in);
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
        banco.guardarListaClientes(nombres, apellidos, DNI, telefono, correo);
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
    public void listar(DefaultTableModel dtm, Object[] obj) {
        dtm.setRowCount(0);

        for (int i = 0; i < banco.getClientes().length - 1; i++) {
            obj[0] = banco.getClientes()[i].getIdCliente();
            obj[1] = banco.getClientes()[i].getNombres();
            obj[2] = banco.getClientes()[i].getApellidos();
            obj[3] = banco.getClientes()[i].getDni();
            obj[4] = banco.getClientes()[i].getTelefono();
            obj[5] = banco.getClientes()[i].getCorreo();

            dtm.addRow(obj);
        }
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
