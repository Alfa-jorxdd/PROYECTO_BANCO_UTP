package org.banco.modelos;

import org.banco.enums.EstadoCuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;

public class Cuenta_Corriente extends Cuenta{

    private double limiteSobregiro;
    private double montoLimitePorOperacion;

    public Cuenta_Corriente(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta, TipoCuenta.CORRIENTE);
        convertirCamposEspecificos(tipoMoneda);
    }

    public Cuenta_Corriente(int idCuenta, Moneda tipoMoneda, EstadoCuenta estadoCuenta, long numeroCuenta) {
        super(idCuenta, TipoCuenta.CORRIENTE, tipoMoneda, estadoCuenta, numeroCuenta);
        convertirCamposEspecificos(tipoMoneda);
    }

    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    @Override
    public double getMontoLimitePorOperacion() {
        return montoLimitePorOperacion;
    }

    @Override
    protected final void convertirCamposEspecificos(Moneda nuevaMoneda) {
        limiteSobregiro = nuevaMoneda == Moneda.DOLAR ? -200 : -500;
        montoLimitePorOperacion = nuevaMoneda == Moneda.DOLAR ? 25000 : 50000;
    } 
}
