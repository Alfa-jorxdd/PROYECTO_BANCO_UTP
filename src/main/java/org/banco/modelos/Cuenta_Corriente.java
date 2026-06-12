package org.banco.modelos;

import javax.swing.JOptionPane;
import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;

public class Cuenta_Corriente extends Cuenta{

    private final double limiteSobregiro = -500;
    private double acumulador;

    public Cuenta_Corriente(Moneda tipoMoneda, EstadoCuenta estadoCuenta) {
        super(tipoMoneda, estadoCuenta, TipoCuenta.CORRIENTE);
        acumulador = 0;
    }

    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    @Override
    public void depositar(double monto) {
        setSaldo(monto);
        JOptionPane.showMessageDialog(null, "Deposito exitoso");
    }

    @Override
    public void retirar(double monto) {
        double saldoAuxiliar = getSaldo() - monto;
        if (saldoAuxiliar < 0) {
            if (saldoAuxiliar > limiteSobregiro) {
                setSaldo(-monto);
                JOptionPane.showMessageDialog(null, "Retiro exitoso");
                return;
            } 
            JOptionPane.showMessageDialog(null, "Esta cuenta ha llegado a su límite de sobregiro");
        }
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
