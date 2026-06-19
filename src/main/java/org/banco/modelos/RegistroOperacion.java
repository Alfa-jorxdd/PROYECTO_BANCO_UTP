package org.banco.modelos;

import org.banco.enums.Moneda;
import org.banco.enums.TipoOperacion;
import java.time.LocalDateTime;

public class RegistroOperacion {
    private static int incrementar = 0;
    private int idOperacion;
    private int[] idCuentas;
    private int dni;
    private TipoOperacion operacion;
    private LocalDateTime fechaOperacion;
    private double monto;
    private Moneda moneda;

    public RegistroOperacion(int[] idCuentas, int dniEmisor, TipoOperacion operacion, double monto, Moneda moneda) {
        idOperacion = ++incrementar;
        
        this.idCuentas = idCuentas;
        this.operacion = operacion;
        this.monto = monto;
        this.moneda = moneda;
        this.dni = dniEmisor;
        
        
        this.fechaOperacion = LocalDateTime.now();
    }
    
    public RegistroOperacion(int idCuenta, TipoOperacion operacion){
        idOperacion = ++incrementar;
        
        this.idCuentas = new int[1];
        this.idCuentas[0] = idCuenta;
        
        this.operacion = operacion;
        
        this.fechaOperacion = LocalDateTime.now();
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public int getIdCuentarOrigen() {
        return idCuentas[0];
    }
    
    public int getIdCuentaDestino(){
        return idCuentas.length > 1 ? idCuentas[1] : -1;
    }

    public TipoOperacion getOperacion() {
        return operacion;
    }

    public LocalDateTime getFechaOperacion() {
        return fechaOperacion;
    }

    public double getMonto() {
        return monto;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public int getDni() {
        return dni;
    }
}
