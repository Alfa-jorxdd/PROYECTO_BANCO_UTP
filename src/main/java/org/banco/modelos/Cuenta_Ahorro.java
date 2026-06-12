package org.banco.modelos;

import javax.swing.JOptionPane;
import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;

public class Cuenta_Ahorro extends Cuenta{

    private final int limiteTransaccionesDiarios = 5;
    private int contador;

    public Cuenta_Ahorro(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta, TipoCuenta.AHORRO);
        contador = 0;
    }

    public int getLimiteRetirosDiarios() {
        return limiteTransaccionesDiarios;
    }

    @Override
    public void depositar(double monto) {
        if (contador != limiteTransaccionesDiarios) {
            setSaldo(monto);
            contador++;
            JOptionPane.showMessageDialog(null, "Deposito exitoso");
            return;
        }
        JOptionPane.showMessageDialog(null, "Esta cuenta ha llegado a su límite de transacciones diarias");
    }

    @Override
    public void retirar(double monto) {
        double saldoAuxiliar = getSaldo() - monto;
        if (saldoAuxiliar > 0) {
            setSaldo(-monto);
            JOptionPane.showMessageDialog(null, "Retiro exitoso");
            return;
        }
        JOptionPane.showMessageDialog(null, "Saldo insuficiente");
    }

    @Override
    public void consultar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void transferir(double montoEmisor, double montoReceptor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
