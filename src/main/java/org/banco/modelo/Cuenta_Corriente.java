package org.banco.modelo;

public class Cuenta_Corriente extends Cuenta{
    
    @Override
    public String toString() {
        return getTipoCuenta() + "| N° " + getNumeroCuenta();
    }

    @Override
    public String getTipoCuenta() {
        return "Cuenta corriente";
    }
    
}
