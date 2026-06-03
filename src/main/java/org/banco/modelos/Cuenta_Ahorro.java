package org.banco.modelos;

import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;

public class Cuenta_Ahorro extends Cuenta{

    private TipoCuenta tipoCuenta;
    private int cantidadOperaciones;

    public Cuenta_Ahorro(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta);
        this.tipoCuenta = TipoCuenta.AHORRO;
    }

    @Override
    public String getTipoCuenta() {
        return tipoCuenta.toString();
    }
      
}
