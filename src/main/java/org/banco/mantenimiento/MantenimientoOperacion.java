package org.banco.mantenimiento;

import javax.swing.JOptionPane;
import org.banco.modelos.Banco;
import org.banco.modelos.Cuenta;
import org.banco.modelos.interfaces.Operable;

public class MantenimientoOperacion implements Operable {

    private Banco banco;
    
    public MantenimientoOperacion(Banco banco){
        this.banco = banco;
    }
    
    @Override
    public void depositar(long numeroCuenta, double monto) {
        
        Cuenta cuenta = banco.buscarCuentaPorNumeroCuenta(numeroCuenta);
        cuenta.setSaldo(monto);
        
        JOptionPane.showMessageDialog(null, "Deposito exitoso");
    }

    @Override
    public void retirar() {
        
    }

    @Override
    public void consultar() {
        
    }

    @Override
    public void transferir() {
        
    }
    
}
