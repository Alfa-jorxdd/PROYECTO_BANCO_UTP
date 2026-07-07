package org.banco.logica;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import org.banco.dao.ClienteDAO;
import org.banco.dao.CuentaDAO;
import org.banco.dao.OperacionDAO;

import java.math.BigDecimal;
import java.text.DecimalFormat;


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
        BigDecimal saldo = cuentaDAO.contarSaldoDeTodasLasCuentas(simbolo);
        String saldoRedondeado = redondearSaldo(saldo);
        labelSaldo.setText(saldoRedondeado);
    }

    private String redondearSaldo(BigDecimal saldo){
        String[] SUFIJOS = {"", "K", "M", "B", "T"};
        double valor = saldo.doubleValue();
        boolean negativo = valor < 0;
        valor = Math.abs(valor);

        int indiceSufijos = 0;
        while (valor >= 1000 && indiceSufijos < SUFIJOS.length - 1) {
            valor /= 1000;
            indiceSufijos++;
        }

        DecimalFormat df = new DecimalFormat(indiceSufijos == 0 ? "#,##0.##" : "#,##0.#");
        String resultado = df.format(valor) + SUFIJOS[indiceSufijos];

        return negativo ? "-" + resultado : resultado;



    }
    
    public void ponerUltimosClientesTabla(DefaultTableModel dtm) {
        clienteDAO.listarUltimos20Clientes(dtm);
    }
}
