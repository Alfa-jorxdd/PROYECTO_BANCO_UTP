package org.banco.modelos;

import org.banco.enums.EstadoCuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;

public class Cuenta_Ahorro extends Cuenta{

    private final int limiteTransaccionesDiarios = 5;
    private int contador;
    private double montoLimitePorOperacion;

    public Cuenta_Ahorro(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta, TipoCuenta.AHORRO);
        contador = 0;
        convertirCamposEspecificos(tipoMoneda);
    }

    public Cuenta_Ahorro(int idCuenta, Moneda tipoMoneda, EstadoCuenta estadoCuenta, long numeroCuenta) {
        super(idCuenta, TipoCuenta.AHORRO, tipoMoneda, estadoCuenta, numeroCuenta);
        contador = 0;
        convertirCamposEspecificos(tipoMoneda);
    }

    public int getLimiteTransaccionesDiarios() {
        return limiteTransaccionesDiarios;
    }

    @Override
    public double getMontoLimitePorOperacion() {
        return montoLimitePorOperacion;
    }

    public int getContador() {
        return contador;
    }

    public void aumentarContador(){
        this.contador++;
    }

    @Override
    protected final void convertirCamposEspecificos(Moneda nuevaMoneda) {
        montoLimitePorOperacion = nuevaMoneda == Moneda.DOLAR ? 5000 : 10000;
    }  
}
