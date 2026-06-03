package org.banco.mantenimiento;

import org.banco.modelos.*;

import javax.swing.table.DefaultTableModel;
import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;
import org.banco.modelos.interfaces.Gestionable;

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
    }

    @Override
    public void eliminar() {
        banco.disminuirListaCliente_CuentaPorIdCuenta(idCuenta);
        int indiceCuenta = banco.buscarIndiceCuenta(idCuenta);
        banco.disminuirListaCuentas(indiceCuenta);

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
    }

    @Override
    public void listar(DefaultTableModel dtm, Object[] obj) {
        dtm.setRowCount(0);

        for (int i = 0; i < banco.getCuentas().length - 1; i++) {

            int idCuenta = banco.getCuentas()[i].getIdCuenta();
            obj[0] = idCuenta;
            obj[1] = banco.getCuentas()[i].getTipoCuenta();
            obj[2] = banco.getCuentas()[i].getEstadoCuenta();
            obj[3] = banco.getCuentas()[i].getNumeroCuenta();

            Cliente[] clientes = banco.buscarClientesPorIdCuenta(idCuenta);

            if (clientes.length > 1) {
                for (int j = 0; j < clientes.length; j++) {
                    obj[4] = clientes[j].getNombres() + " " + clientes[j].getApellidos();
                    obj[5] = banco.getCuentas()[i].getSaldo();
                    obj[6] = banco.getCuentas()[i].getMoneda();

                    dtm.addRow(obj);
                }
            } else {
                obj[4] = clientes[0].getNombres() + " " + clientes[0].getApellidos();
                obj[5] = banco.getCuentas()[i].getSaldo();
                obj[6] = banco.getCuentas()[i].getMoneda();

                dtm.addRow(obj);
            }
        }
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
