package org.banco.modelos;

import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;

public class Cuenta_Ahorro extends Cuenta{

    private final int LimiteRetirosDiarios = 5;

    public Cuenta_Ahorro(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta, TipoCuenta.AHORRO);
    }

    public int getLimiteRetirosDiarios() {
        return LimiteRetirosDiarios;
    }
}
