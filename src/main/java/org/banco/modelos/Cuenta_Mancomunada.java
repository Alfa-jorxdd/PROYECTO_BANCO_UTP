package org.banco.modelos;

import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;

public class Cuenta_Mancomunada extends Cuenta {
    private TipoCuenta tipoCuenta;

    public Cuenta_Mancomunada(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta);
        this.tipoCuenta = TipoCuenta.MANCOMUNADA;
    }

    @Override
    public String getTipoCuenta() {
        return tipoCuenta.toString();
    }
    
}
