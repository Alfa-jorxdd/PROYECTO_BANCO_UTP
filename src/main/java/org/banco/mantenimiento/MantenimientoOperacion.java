package org.banco.mantenimiento;

import org.banco.modelos.Banco;
import org.banco.modelos.Cuenta;
import org.banco.enums.Moneda;
import org.banco.interfaces.Operable;

public class MantenimientoOperacion implements Operable {

    private Banco banco;

    public MantenimientoOperacion(Banco banco) {
        this.banco = banco;
    }

    @Override
    public void depositar(long numeroCuenta, double monto, Moneda monedaOperacion) {

        Cuenta cuenta = banco.buscarCuentaPorNumeroCuenta(numeroCuenta);
        double montoDepositar = montoSegunMoneda(monto, monedaOperacion, cuenta);
        cuenta.depositar(montoDepositar);
    }

    @Override
    public void retirar(long numeroCuenta, double monto, Moneda monedaOperacion) {
        
        Cuenta cuenta = banco.buscarCuentaPorNumeroCuenta(numeroCuenta);
        double montoRetirar = montoSegunMoneda(monto, monedaOperacion, cuenta);
        cuenta.retirar(montoRetirar);
    }

    @Override
    public void consultar() {

    }

    @Override
    public void transferir() {

    }
    
    
    private double montoSegunMoneda(double monto, Moneda monedaOperacion, Cuenta cuenta) {
        double montoOperacion = 0;
        switch (monedaOperacion) {
            case SOL:
                montoOperacion = cuenta.getMoneda().equals(Moneda.SOL)
                        ? monto
                        : monto / Moneda.DOLAR.getTipoCambio();
                break;

            case DOLAR:
                montoOperacion = cuenta.getMoneda().equals(Moneda.DOLAR)
                        ? monto
                        : monto * Moneda.DOLAR.getTipoCambio();
                break;
        }
        return montoOperacion;
    }

}
