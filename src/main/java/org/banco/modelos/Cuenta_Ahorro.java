package org.banco.modelos;

import org.banco.enums.EstadoCuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;

public class Cuenta_Ahorro extends Cuenta{

    private final int limiteTransaccionesDiarios = 5;
    private double montoLimitePorOperacion;

    public Cuenta_Ahorro(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta, TipoCuenta.AHORRO);
        convertirCamposEspecificos(tipoMoneda);
    }

    public Cuenta_Ahorro(int idCuenta, Moneda tipoMoneda, EstadoCuenta estadoCuenta, long numeroCuenta) {
        super(idCuenta, TipoCuenta.AHORRO, tipoMoneda, estadoCuenta, numeroCuenta);
        convertirCamposEspecificos(tipoMoneda);
    }

    public int getLimiteTransaccionesDiarios() {
        return limiteTransaccionesDiarios;
    }

    @Override
    public double getMontoLimitePorOperacion() {
        return montoLimitePorOperacion;
    }

    @Override
    protected final void convertirCamposEspecificos(Moneda nuevaMoneda) {
        montoLimitePorOperacion = nuevaMoneda == Moneda.DOLAR ? 5000 : 10000;
    }  
}
