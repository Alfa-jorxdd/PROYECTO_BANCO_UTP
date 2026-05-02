package org.banco.modelo;

import java.util.Random;
import org.banco.modelo.enums.EstadoCuenta;

public abstract class Cuenta {
    private static int incrementarId = 0;
    private int idCuenta;
    private String titular;
    private double saldo;
    private long numeroCuenta;
    private EstadoCuenta estado;

    public Cuenta() {
        crearDigitosCuenta();
        this.idCuenta = ++incrementarId;
        estado = EstadoCuenta.ACTIVA;
    }

    public void crearDigitosCuenta() {
        Random digito = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append(digito.nextInt(9));
        }

        numeroCuenta = Long.parseLong(sb.toString());
    }
    
    public abstract String getTipoCuenta();
    
    public void modificarEstado(EstadoCuenta estado){
        this.estado = estado;
    }
    
    public EstadoCuenta getEstadoCuenta(){
        return estado;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getIdCuenta() {
        return idCuenta;
    }
}
