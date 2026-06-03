package org.banco.modelos;

import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;

public class Cuenta_Corriente extends Cuenta{

    private TipoCuenta tipoCuenta;

    public Cuenta_Corriente(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta);
        this.tipoCuenta = TipoCuenta.CORRIENTE;
    }

    @Override
    public String getTipoCuenta() {
        return tipoCuenta.toString();
    }
    
}
