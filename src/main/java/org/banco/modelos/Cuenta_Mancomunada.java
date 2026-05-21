package org.banco.modelos;

import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;

public class Cuenta_Mancomunada extends Cuenta {

    public Cuenta_Mancomunada(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta);
    }

    @Override
    public String getTipoCuenta() {
        return "Mancomunada";
    }
    
}
