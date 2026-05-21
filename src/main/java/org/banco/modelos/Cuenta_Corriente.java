package org.banco.modelos;

import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;

public class Cuenta_Corriente extends Cuenta{

    public Cuenta_Corriente(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta);
    }
    
    @Override
    public String toString() {
        return getTipoCuenta() + "| N° " + getNumeroCuenta();
    }

    @Override
    public String getTipoCuenta() {
        return "Cuenta corriente";
    }
    
}
