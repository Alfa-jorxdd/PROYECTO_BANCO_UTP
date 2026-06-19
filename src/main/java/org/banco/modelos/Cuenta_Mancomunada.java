package org.banco.modelos;

import org.banco.enums.EstadoCuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;

public class Cuenta_Mancomunada extends Cuenta {
    private double montoLimitePorOperacion;

    public Cuenta_Mancomunada(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta, TipoCuenta.MANCOMUNADA);
        convertirCamposEspecificos(tipoMoneda);
    }

    @Override
    public double getMontoLimitePorOperacion() {
        return montoLimitePorOperacion;
    }

    @Override
    protected final void convertirCamposEspecificos(Moneda nuevaMoneda) {
        montoLimitePorOperacion = nuevaMoneda == Moneda.DOLAR ? 50000 : 100000;
    }
}
