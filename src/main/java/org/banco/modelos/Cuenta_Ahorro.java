package org.banco.modelos;

import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;

public class Cuenta_Ahorro extends Cuenta{

    public Cuenta_Ahorro(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta);
    }
    
    @Override
    public String toString() {
        return getTipoCuenta() +" | N° " + getNumeroCuenta();
    }

    @Override
    public String getTipoCuenta() {
        return "Cuenta de ahorro";
    }
      
}
