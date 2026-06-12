package org.banco.modelos.enums;

public enum Moneda {
    SOL(1.0, "S/"),
    DOLAR(3.75, "$");
    
    private double tipoCambio;
    private String simbolo;
    Moneda(double tipoCambio, String simbolo){
        this.tipoCambio = tipoCambio;
        this.simbolo = simbolo;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public String getSimbolo() {
        return simbolo;
    }
    
    
}
