package org.banco.modelo;

public class Cliente_Cuenta {
    private int idCliente;
    private int idCuenta;
    
    public Cliente_Cuenta(int idCliente, int idCuenta){
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdCuenta() {
        return idCuenta;
    }
}
