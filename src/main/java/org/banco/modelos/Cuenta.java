package org.banco.modelos;

import java.util.Random;
import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;

public abstract class Cuenta {
    private static int incrementarId = 0;
    private final int idCuenta;
    private double saldo;
    private long numeroCuenta;
    private EstadoCuenta estadoCuenta;
    private Moneda tipoMoneda;

    public Cuenta(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        crearDigitosCuenta();
        
        this.idCuenta = ++incrementarId;
        this.tipoMoneda = tipoMoneda;
        this.estadoCuenta = estadoCuenta; 
        
        saldo = 0;
    }



    private final void crearDigitosCuenta() {
        Random digito = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append(digito.nextInt(9));
        }

        numeroCuenta = Long.parseLong(sb.toString());
    }
    
    public abstract String getTipoCuenta();
    
    public void modificarEstado(EstadoCuenta estado){
        this.estadoCuenta = estadoCuenta;
    }
    
    public EstadoCuenta getEstadoCuenta(){
        return estadoCuenta;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getIdCuenta() {
        return idCuenta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public Moneda getMoneda() {
        return tipoMoneda;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = this.saldo + saldo;
    }

    public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public void setTipoMoneda(Moneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
    
}
