package org.banco.modelos;

import java.util.Random;
import org.banco.enums.EstadoCuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;

public abstract class Cuenta {
    private static int incrementarId = 0;
    private final int idCuenta;
    private double saldo;
    private long numeroCuenta;
    private EstadoCuenta estadoCuenta;
    private Moneda tipoMoneda;
    private final TipoCuenta tipoCuenta;
    
    public abstract double getMontoLimitePorOperacion();

    public Cuenta(Moneda tipoMoneda, EstadoCuenta estadoCuenta, TipoCuenta tipoCuenta) {
        crearDigitosCuenta();

        this.idCuenta = ++incrementarId;
        this.tipoMoneda = tipoMoneda;
        this.estadoCuenta = estadoCuenta; 
        this.tipoCuenta = tipoCuenta;

        saldo = 0;
    }

    private void crearDigitosCuenta() {
        Random digito = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(digito.nextInt(8) + 1);
        for (int i = 1; i < 10; i++) {
            sb.append(digito.nextInt(10));
        }

        numeroCuenta = Long.parseLong(sb.toString());
    }
    public Moneda getTipoMoneda() {
        return tipoMoneda;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void modificarEstado(EstadoCuenta estado){
        this.estadoCuenta = estado;
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

    public void setTipoMoneda(Moneda nuevaMoneda) {
        if (this.tipoMoneda == nuevaMoneda) return;
        double tipoDeCambio = Moneda.DOLAR.getTipoCambio();
        
        if (this.tipoMoneda == Moneda.SOL && nuevaMoneda == Moneda.DOLAR){
            this.saldo /= tipoDeCambio;
        } else {
            this.saldo *= tipoDeCambio;
        }
       
        this.tipoMoneda = nuevaMoneda;
        
        convertirCamposEspecificos(nuevaMoneda);
    }
    
    protected void convertirCamposEspecificos(Moneda nuevaMoneda) {
        
    }
}
