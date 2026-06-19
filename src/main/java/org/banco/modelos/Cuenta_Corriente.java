package org.banco.modelos;

import org.banco.enums.EstadoCuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;

public class Cuenta_Corriente extends Cuenta{

    private double limiteSobregiro;
    private double acumuladorDeSobregiro;
    private double montoLimitePorOperacion;

    public Cuenta_Corriente(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta, TipoCuenta.CORRIENTE);
        acumuladorDeSobregiro = 0;
        convertirCamposEspecificos(tipoMoneda);
    }

    public void acumulacionDeSobregiro(double monto){
        this.acumuladorDeSobregiro += monto;
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
        limiteSobregiro = nuevaMoneda == Moneda.DOLAR ? 200 : 500;
        montoLimitePorOperacion = nuevaMoneda == Moneda.DOLAR ? 25000 : 50000;
    } 
}
