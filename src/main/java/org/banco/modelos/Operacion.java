package org.banco.modelos;

import org.banco.enums.Moneda;
import org.banco.enums.TipoOperacion;
import java.time.LocalDateTime;

public class Operacion {
    private int idOperacion;
    private int idCuentaOrigen;
    private int idCuentaDesitino;
    private long numeroCuentaOrigen;
    private long numeroCuentaDestino;
    private int dni;
    private TipoOperacion operacion;
    private LocalDateTime fechaOperacion;
    private double monto;
    private Moneda moneda;

    /*
     * CONSTRUCTORES PARA TRANSFERENCIAS
     */
    //REGRESO DE DB
    public Operacion(int idOperacion, int idCuentaOrigen, int idCuentaDesitino, long numeroCuentaOrigen, long numeroCuentaDestino, int dni, TipoOperacion operacion, LocalDateTime fechaOperacion, Moneda moneda, double monto) {
        this.idOperacion = idOperacion;
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDesitino = idCuentaDesitino;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.dni = dni;
        this.operacion = operacion;
        this.fechaOperacion = fechaOperacion;
        this.moneda = moneda;
        this.monto = monto;
    }

    //ENVIO A DB
    public Operacion(int idCuentaOrigen, int idCuentaDesitino, long numeroCuentaOrigen, long numeroCuentaDestino, int dni, TipoOperacion operacion, Moneda moneda, double monto) {
        this.idOperacion = 0;
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDesitino = idCuentaDesitino;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.dni = dni;
        this.operacion = operacion;
        this.fechaOperacion = LocalDateTime.now();
        this.moneda = moneda;
        this.monto = monto;
    }

    /*
     * CONSTRUCTORES PARA RETIROS O DEPOSIOS
     */
    //ENVÍO A DB
    public Operacion(int idCuentaOrigen, long numeroCuentaOrigen, int dni, TipoOperacion operacion, double monto, Moneda moneda) {
        this.idOperacion = 0;
        this.idCuentaOrigen = idCuentaOrigen;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.dni = dni;
        this.operacion = operacion;
        this.fechaOperacion = LocalDateTime.now();
        this.monto = monto;
        this.moneda = moneda;
    }

    //REGRESO DE DB
    public Operacion(int idOperacion, int idCuentaOrigen, long numeroCuentaOrigen, int dni, TipoOperacion operacion, LocalDateTime fechaOperacion, double monto, Moneda moneda) {
        this.idOperacion = idOperacion;
        this.idCuentaOrigen = idCuentaOrigen;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.dni = dni;
        this.operacion = operacion;
        this.fechaOperacion = fechaOperacion;
        this.monto = monto;
        this.moneda = moneda;
    }

    /*
     * CONSTRUCTORES PARA CONSULTAS
     */
    //ENVÍO A DB
    public Operacion(int idOperacion, int idCuentaOrigen, TipoOperacion operacion, long numeroCuentaOrigen, LocalDateTime fechaOperacion) {
        this.idOperacion = idOperacion;
        this.idCuentaOrigen = idCuentaOrigen;
        this.operacion = operacion;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.fechaOperacion = fechaOperacion;
    }

    //REGRESO DE DB
    public Operacion(int idCuentaOrigen, TipoOperacion operacion, long numeroCuentaOrigen) {
        this.idOperacion = 0;
        this.idCuentaOrigen = idCuentaOrigen;
        this.operacion = operacion;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.fechaOperacion = LocalDateTime.now();
    }

    public int getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public int getIdCuentaDesitino() {
        return idCuentaDesitino;
    }

    public long getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    public long getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public int getIdOperacion() {
        return idOperacion;
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
