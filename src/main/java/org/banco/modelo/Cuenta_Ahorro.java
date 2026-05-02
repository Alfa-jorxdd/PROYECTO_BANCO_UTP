package org.banco.modelo;

public class Cuenta_Ahorro extends Cuenta{
    
    @Override
    public String toString() {
        return getTipoCuenta() +" | N° " + getNumeroCuenta();
    }

    @Override
    public String getTipoCuenta() {
        return "Cuenta de ahorro";
    }
      
}
