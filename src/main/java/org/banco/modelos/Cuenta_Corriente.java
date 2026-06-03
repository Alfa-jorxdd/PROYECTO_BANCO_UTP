package org.banco.modelos;

import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;

public class Cuenta_Corriente extends Cuenta{

    private final double limiteSobregiro = 500;

    public Cuenta_Corriente(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta, TipoCuenta.CORRIENTE);
    }

    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }
}
