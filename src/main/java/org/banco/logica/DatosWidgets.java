package org.banco.logica;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import org.banco.modelos.Banco;
import org.banco.modelos.Cuenta;


public class DatosWidgets {
    private final Banco banco;
    

    public DatosWidgets(Banco banco) {
        this.banco = banco;
    }
    
    public void cargarClientesWidgets(JLabel labelClientes){
        labelClientes.setText(String.valueOf(banco.getClientes().length - 1));
    }
    
    public void cargarCuentasWidgets(JLabel labelCuentas){
        labelCuentas.setText(String.valueOf(banco.getCuentas().length - 1));
    }
    
    public void cargarOperacionesWidgets(JLabel labelOperaciones){
        labelOperaciones.setText(String.valueOf(banco.getOperaciones().length - 1));
    }
    public void cargarSaldoWidgets(JLabel labelSaldo, JLabel labelSimbolo){
        String simbolo = labelSimbolo.getText();
        double saldoTotal = 0;
        
        for (int i = 0; i < banco.getCuentas().length - 1; i++) {
            if (banco.getCuentas()[i].getMoneda().getSimbolo().equals(simbolo)) {
                saldoTotal += banco.getCuentas()[i].getSaldo();
            }
        }
        labelSaldo.setText(String.valueOf(saldoTotal));
    }
    
    public void ponerUltimosClientesTabla(DefaultTableModel dtm) {
        dtm.setRowCount(0);

        int ultimo = banco.getClientes().length - 2;
        int inicio = Math.max(0, ultimo - 19);

        Object[] obj = new Object[4];
        for (int i = ultimo; i >= inicio; i--) {
            int idCliente = banco.getClientes()[i].getIdCliente();
            Cuenta[] cuentaCliente = banco.buscarCuentasporIdCLiente(idCliente);

            obj[0] = idCliente;
            obj[1] = banco.getClientes()[i].getNombres();

            if (cuentaCliente == null || cuentaCliente.length == 0) {
                obj[2] = "Sin Cuenta";
                obj[3] = "Sin Estado";
                dtm.addRow(obj);
            } else if (cuentaCliente.length > 1) {
                for (Cuenta cuentaCliente1 : cuentaCliente) {
                    obj[2] = cuentaCliente1.getTipoCuenta();
                    obj[3] = cuentaCliente1.getEstadoCuenta();
                    dtm.addRow(obj);
                }
            } else {
                obj[2] = cuentaCliente[0].getTipoCuenta();
                obj[3] = cuentaCliente[0].getEstadoCuenta();
                dtm.addRow(obj);
            }
        }
    }
}
