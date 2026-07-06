package org.banco.logica;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import org.banco.dao.ClienteDAO;
import org.banco.dao.CuentaDAO;
import org.banco.dao.OperacionDAO;
import org.banco.modelos.Banco;

import java.math.BigDecimal;


public class DatosWidgets {
    private final ClienteDAO clienteDAO;
    private final CuentaDAO cuentaDAO;
    private final OperacionDAO operacionDAO;

    public DatosWidgets() {
        clienteDAO = new ClienteDAO();
        cuentaDAO = new CuentaDAO();
        operacionDAO = new OperacionDAO();
    }
    
    public void cargarClientesWidgets(JLabel labelClientes){
        labelClientes.setText(String.valueOf(clienteDAO.contarClientes()));
    }
    
    public void cargarCuentasWidgets(JLabel labelCuentas){
        labelCuentas.setText(String.valueOf(cuentaDAO.contarCuentas()));
    }
    
    public void cargarOperacionesWidgets(JLabel labelOperaciones){
        labelOperaciones.setText(String.valueOf(operacionDAO.contarOperaciones()));
    }
    public void cargarSaldoWidgets(JLabel labelSaldo, JLabel labelSimbolo){
        String simbolo = labelSimbolo.getText();
        BigDecimal saldoTotal = cuentaDAO.contarSaldoDeTodasLasCuentas(simbolo);
        labelSaldo.setText(String.valueOf(saldoTotal));
    }
    
    public void ponerUltimosClientesTabla(DefaultTableModel dtm) {
        clienteDAO.listarUltimos20Clientes(dtm);
    }
}
