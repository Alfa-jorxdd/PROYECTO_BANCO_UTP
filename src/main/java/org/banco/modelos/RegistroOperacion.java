package org.banco.modelos;

import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoOperacion;

import java.time.LocalDate;

public class RegistroOperacion {
    private static int incrementar = 0;
    private int idOperacion;
    private int idCuenta;
    private TipoOperacion operacion;
    private LocalDate fechaOperacion;
    private double monto;
    private Moneda moneda;

    public RegistroOperacion(int idCuenta, TipoOperacion operacion, double monto, Moneda moneda) {
        idOperacion = ++incrementar;
        
        this.idCuenta = idCuenta;
        this.operacion = operacion;
        this.monto = monto;
        this.moneda = moneda;
        
        
        this.fechaOperacion = LocalDate.now();
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public TipoOperacion getOperacion() {
        return operacion;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public double getMonto() {
        return monto;
    }

    public Moneda getMoneda() {
        return moneda;
    }
}
