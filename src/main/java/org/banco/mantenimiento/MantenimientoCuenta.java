package org.banco.mantenimiento;

import org.banco.modelos.*;
import org.banco.modelos.interfaces.Mantenimiento;

import java.util.Scanner;
import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;

public class MantenimientoCuenta implements Mantenimiento {

    private Scanner sc = new Scanner(System.in);
    private Banco banco;
    private int[] idClientes;
    private int idCuenta;
    private int tipoCuenta;
    private EstadoCuenta estadoCuenta;
    private Moneda tipoMoneda;

    public MantenimientoCuenta(Banco banco) {
        this.banco = banco;

    }

    @Override
    public void agregar() {

        Cuenta nuevaCuenta = banco.guardarListaCuentas(tipoCuenta, tipoMoneda, estadoCuenta);

        if (idClientes.length > 1) {
            for (int i = 0; i < idClientes.length; i++) {
                Cliente nuevoCliente = banco.buscarIdCliente(idClientes[i]);
                banco.guardarListaCliente_Cuenta(nuevoCliente, nuevaCuenta);
            }
        } else {
            Cliente nuevoCliente = banco.buscarIdCliente(idClientes[0]);
            banco.guardarListaCliente_Cuenta(nuevoCliente, nuevaCuenta);
        }
    }

    @Override
    public void eliminar() {
        int indiceCuenta = banco.buscarIndiceCuenta(idCuenta);
        banco.disminuirListaCuentas(indiceCuenta);

    }

    @Override
    public void actualizar() {
        int indiceCuenta = banco.buscarIndiceCuenta(idCuenta);
        
        banco.getCuentas()[indiceCuenta].setEstadoCuenta(estadoCuenta);
        banco.getCuentas()[indiceCuenta].setTipoMoneda(tipoMoneda);
        
        banco.disminuirListaCliente_CuentaPorIdCuenta(idCuenta);
        
        Cuenta cuenta = banco.buscarIdCuenta(idCuenta);
        for (int i = 0; i < idClientes.length; i++) {
            Cliente nuevoCliente = banco.buscarIdCliente(idClientes[i]);
            banco.guardarListaCliente_Cuenta(nuevoCliente, cuenta);
        }
    }

    @Override
    public void imprimir() {

    }

    public void setDatosCuenta(int tipoCuenta, EstadoCuenta estadoCuenta, Moneda tipoMoneda) {
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
