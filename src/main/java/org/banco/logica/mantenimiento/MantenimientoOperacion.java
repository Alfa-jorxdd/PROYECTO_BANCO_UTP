package org.banco.logica.mantenimiento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.banco.dao.ClienteDAO;
import org.banco.dao.CuentaDAO;
import org.banco.dao.OperacionDAO;
import org.banco.enums.*;
import org.banco.logica.ReporteExcel;
import org.banco.logica.ReporteHtml;
import org.banco.logica.ReportePdf;
import org.banco.modelos.*;
import org.banco.interfaces.Operable;

public class MantenimientoOperacion implements Operable {

    private CuentaDAO cuentaDAO;
    private ClienteDAO clienteDAO;
    private OperacionDAO operacionDAO;

    public MantenimientoOperacion() {
        cuentaDAO = new CuentaDAO();
        operacionDAO = new OperacionDAO();
        clienteDAO = new ClienteDAO();
    }

    @Override
    public void depositar(long numeroCuenta, double monto, Moneda monedaOperacion, int DNI) {
        //Obtener datos esenciales
        Cuenta cuenta = cuentaDAO.buscarCuentaPorNumeroCuenta(numeroCuenta);
        if (!cuentaExiste(cuenta)) {
            return;
        }

        double montoDepositar = montoSegunMoneda(monto, monedaOperacion, cuenta);
        //Validar el deposito
        if (!depositoValido(cuenta, montoDepositar, true)) {
            return;
        }
        System.out.println("Monto a depositar: " + montoDepositar);
        //Realizar el deposito
        cuenta.agregarSaldo(montoDepositar);
        System.out.println("Saldo actual de la cuenta: " + cuenta.getSaldo());
        cuentaDAO.actualizarCuenta(cuenta);

        Operacion nuevaOperacion = new Operacion(cuenta.getIdCuenta(), numeroCuenta, DNI, TipoOperacion.DEPOSITO, monto, monedaOperacion);
        operacionDAO.agregarOperacion(nuevaOperacion);
        Voucher voucherOperacion = new Voucher(nuevaOperacion);
        voucherOperacion.imprimirVoucher();
    }

    @Override
    public void retirar(long numeroCuenta, double monto, Moneda monedaOperacion, int DNI) {

        Cuenta cuenta = cuentaDAO.buscarCuentaPorNumeroCuenta(numeroCuenta);
        if (!cuentaExiste(cuenta)) {
            return;
        }
        double montoRetirar = montoSegunMoneda(monto, monedaOperacion, cuenta);

        //Validar el retiro
        if (!retiroValido(cuenta, montoRetirar, true)) {
            return;
        }
        //Realizar retiro
        cuenta.agregarSaldo(-montoRetirar);
        cuentaDAO.actualizarCuenta(cuenta);

        //Generar registro de la operacion y VOUCHER

        Operacion nuevaOperacion = new Operacion(cuenta.getIdCuenta(), numeroCuenta, DNI, TipoOperacion.RETIRO, monto, monedaOperacion);
        operacionDAO.agregarOperacion(nuevaOperacion);
        Voucher voucherOperacion = new Voucher(nuevaOperacion);
        voucherOperacion.imprimirVoucher();
    }

    @Override
    public void consultar(long numeroCuenta) {
        Cuenta cuenta = cuentaDAO.buscarCuentaPorNumeroCuenta(numeroCuenta);
        if (!cuentaExiste(cuenta)) {
            return;
        }
        Operacion nuevaOperacion = new Operacion(cuenta.getIdCuenta(), TipoOperacion.CONSULTA, numeroCuenta);
        operacionDAO.agregarOperacion(nuevaOperacion);
        Voucher voucher = new Voucher(nuevaOperacion);
        voucher.imprimirVoucher();
    }

    @Override
    public void transferir(long numeroCuentaOrigen, long numeroCuentaDestino, double monto, Moneda monedaOperacion, int DNI) {
        Cuenta cuentaOrigen = cuentaDAO.buscarCuentaPorNumeroCuenta(numeroCuentaOrigen);
        Cuenta cuentaDestino = cuentaDAO.buscarCuentaPorNumeroCuenta(numeroCuentaDestino);
        if (!cuentaExiste(cuentaOrigen)) {
            if (!cuentaExiste(cuentaDestino)) {
                return;
            }
            return;
        }

        double montoRetiro = montoSegunMoneda(monto, monedaOperacion, cuentaOrigen);
        double montoDeposito = montoSegunMoneda(monto, monedaOperacion, cuentaDestino);
        //Valida la transferencia
        if (!transferenciaValida(cuentaOrigen, cuentaDestino, montoRetiro, montoDeposito)) {
            return;
        }
        //Ejecuta la transferencia
        cuentaOrigen.agregarSaldo(-montoRetiro);
        cuentaDestino.agregarSaldo(montoDeposito);
        cuentaDAO.actualizarCuenta(cuentaOrigen);
        cuentaDAO.actualizarCuenta(cuentaDestino);

        Operacion operacionTransferenciaEnviada = new Operacion(cuentaOrigen.getIdCuenta(), cuentaDestino.getIdCuenta(), numeroCuentaOrigen, numeroCuentaDestino, DNI, TipoOperacion.TRANSFERENCIA_ENVIADA, monedaOperacion, montoRetiro);
        Operacion operacionTransferenciaRecibida = new Operacion(cuentaOrigen.getIdCuenta(), cuentaDestino.getIdCuenta(), numeroCuentaOrigen, numeroCuentaDestino, DNI, TipoOperacion.TRANSFERENCIA_RECIBIDA, monedaOperacion, montoDeposito);
        operacionDAO.agregarOperacion(operacionTransferenciaEnviada);
        operacionDAO.agregarOperacion(operacionTransferenciaRecibida);

        Voucher vaucherTransferenciaEnv = new Voucher(operacionTransferenciaEnviada);
        vaucherTransferenciaEnv.imprimirVoucher();
        Voucher voucherTransferenciaRec = new Voucher(operacionTransferenciaRecibida);
        voucherTransferenciaRec.imprimirVoucher();

    }

    private double montoSegunMoneda(double monto, Moneda monedaOperacion, Cuenta cuenta) {
        double montoOperacion = 0;
        switch (monedaOperacion) {
            case SOL:
                montoOperacion = cuenta.getMoneda().equals(Moneda.SOL)
                        ? monto
                        : monto / Moneda.DOLAR.getTipoCambio();
                break;

            case DOLAR:
                montoOperacion = cuenta.getMoneda().equals(Moneda.DOLAR)
                        ? monto
                        : monto * Moneda.DOLAR.getTipoCambio();
                break;
        }
        return montoOperacion;
    }

    private boolean montoValido(Cuenta cuenta, double monto) {
        boolean montoPermitido = cuenta.getMontoLimitePorOperacion() >= monto;
        if (!montoPermitido) {
            JOptionPane.showMessageDialog(null, "Monto superior al límite permitido", "Operación denegada", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean cuentaBloqueada(Cuenta cuenta) {
        if (cuenta.getEstadoCuenta() == EstadoCuenta.BLOQUEADA) {
            JOptionPane.showMessageDialog(null, "Esta cuenta está bloqueada", "Operación cancelada", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean retiroValido(Cuenta cuenta, double monto, boolean requeireAutorizacion) {
        if (!montoValido(cuenta, monto)) {
            return false;
        }
        if (cuentaBloqueada(cuenta)) {
            return false;
        }

        double montoRestante = cuenta.getSaldo() - monto;
        boolean retiroValido = true;

        TipoCuenta tipoCuenta = cuenta.getTipoCuenta();
        switch (tipoCuenta) {
            case AHORRO:
                Cuenta_Ahorro cuenta_Ahorro = (Cuenta_Ahorro) cuenta;
                if (cuentaDAO.operacionesRealizadasHoy(cuenta.getIdCuenta()) >= cuenta_Ahorro.getLimiteTransaccionesDiarios()) {
                    JOptionPane.showMessageDialog(null, "Esta cuenta a llegado a su límite de operaciones diarias", "Operación denegada", JOptionPane.WARNING_MESSAGE);
                    retiroValido = false;
                    break;
                }
                if (montoRestante < 0) {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente", "Operación denegada", JOptionPane.WARNING_MESSAGE);
                    retiroValido = false;
                    break;
                }
                break;
            case CORRIENTE:
                Cuenta_Corriente cuenta_Corriente = (Cuenta_Corriente) cuenta;
                if (montoRestante < cuenta_Corriente.getLimiteSobregiro()) {
                    JOptionPane.showMessageDialog(null, "Ha alcanzado su límite máximo de saldo en sobregiro");
                    retiroValido = false;
                    break;
                }
                break;
            case MANCOMUNADA:
                if (requeireAutorizacion && !autorizaciónMancomunada(cuenta)) {
                    retiroValido = false;
                    break;
                }

                if (montoRestante < 0) {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                    retiroValido = false;
                    break;
                }
                break;
        }

        return retiroValido;
    }

    private boolean depositoValido(Cuenta cuenta, double monto, boolean requiereValidacionLimiteDiario) {
        if (!montoValido(cuenta, monto)) {
            return false;
        }
        if (cuentaBloqueada(cuenta)) {
            return false;
        }

        boolean esValido = true;

        if (cuenta.getTipoCuenta() == TipoCuenta.AHORRO && requiereValidacionLimiteDiario) {
            Cuenta_Ahorro cuentaAhorro = (Cuenta_Ahorro) cuenta;
            if (cuentaDAO.operacionesRealizadasHoy(cuenta.getIdCuenta()) >= cuentaAhorro.getLimiteTransaccionesDiarios()) {
                JOptionPane.showMessageDialog(null, "Esta cuenta a llegado a su límite de operaciones diarias", "Operación denegada", JOptionPane.WARNING_MESSAGE);
                esValido = false;
            }
        }

        return esValido;
    }

    private boolean transferenciaValida(Cuenta cuentaOrigen, Cuenta cuentaDestino, double montoRetiro, double montoDeposito) {
        if (cuentaOrigen.getIdCuenta() == cuentaDestino.getIdCuenta()) {
            JOptionPane.showMessageDialog(null, "Una cuenta no puede transferirse a si misma", "Operación denegada", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (cuentaBloqueada(cuentaOrigen) || cuentaBloqueada(cuentaDestino)) {
            return false;
        }

        if (cuentaOrigen.getTipoCuenta() == TipoCuenta.MANCOMUNADA) {
            if (!autorizaciónMancomunada(cuentaOrigen)) {
                return false;
            }
        }

        if (!retiroValido(cuentaOrigen, montoRetiro, false)) {
            return false;
        }
        if (!depositoValido(cuentaDestino, montoDeposito, false)) {
            return false;
        }

        return true;
    }

    private boolean autorizaciónMancomunada(Cuenta cuenta) {
        List<Cliente> titulares = clienteDAO.buscarClientesPorIdCuenta(cuenta.getIdCuenta());

        JCheckBox[] checks = new JCheckBox[titulares.size()];
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Todos los titulares deben confirmar la operación"));
        panel.add(new JLabel("================================================"));
        panel.add(Box.createVerticalStrut(10));

        for (int i = 0; i < titulares.size(); i++) {
            checks[i] = new JCheckBox(titulares.get(i).getNombres() + " " + titulares.get(i).getApellidos());
            panel.add(checks[i]);
        }

        panel.add(new JLabel("================================================"));

        int respuesta = JOptionPane.showConfirmDialog(null, panel, "Autorización de los titulares", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (respuesta != JOptionPane.OK_OPTION) {
            return false;
        }

        for (JCheckBox check : checks) {
            if (!check.isSelected()) {
                JOptionPane.showMessageDialog(null, "Todos los titulares deben confirmar la operación.\nOperación cancelada", "Operación cancelada", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private boolean cuentaExiste(Cuenta cuenta) {
        if (cuenta == null) {
            JOptionPane.showMessageDialog(null, "La cuenta ingresada no existe", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public void mostrarVoucher(int idOperacion){
        Operacion operacion = operacionDAO.buscarOperacionPorId(idOperacion);
        Voucher voucher = new Voucher(operacion);
        voucher.imprimirVoucher();
    }
    
    public void generarReporte(String nombre, Formato formato, boolean ascendente, int criterioOrden, int criterioFiltrar, String textoFiltrado){
        Reporte nuevoReporte = null;
        switch(formato){
            case PDF :
                nuevoReporte = new ReportePdf(nombre, TipoReporte.OPERACIONES);
                break;
            case EXCEL :
                nuevoReporte = new ReporteExcel(nombre, TipoReporte.OPERACIONES);
                break;
            case HTML :
                nuevoReporte = new ReporteHtml(nombre, TipoReporte.OPERACIONES);
                break;
        }
        List<Object[]> lista = operacionDAO.listarOperaciones(ascendente, criterioOrden, criterioFiltrar, textoFiltrado);
        nuevoReporte.crearReporte(lista);
    }
    //====================================================================================================================================
    //===============================================LÓGICA PARA LISTAR===================================================================
    @Override
    public void listar(DefaultTableModel dtm, boolean ascendente, int criterioOrden, int criterioFiltrado, String textoFiltrado) {
        operacionDAO.listarOperaciones(dtm, ascendente, criterioOrden, criterioFiltrado, textoFiltrado);
    }
    
    public void filtrarModelCuenta(String texto, DefaultListModel<String> modeloLista) {
        String numeroBuscado = texto.trim();

        if (numeroBuscado.isEmpty() || !numeroBuscado.matches("[0-9]+")) {
            modeloLista.clear();
            return;
        }

        Long numeroCuenta = Long.parseLong(numeroBuscado);
        modeloLista.clear();

        List<String> titulares = clienteDAO.filtrarNombresTitularesSegunNumeroCuenta(numeroCuenta);
        for (String titulare : titulares) {
            modeloLista.addElement(titulare);
        }
    }
    
    public void filtrarModelDNI(String dni, DefaultListModel<String> modeloResultadosDNI) {
        String dniBuscado = dni.trim();
        modeloResultadosDNI.clear();

        if (dniBuscado.isEmpty() || !dniBuscado.matches("[0-9]+")) {
            modeloResultadosDNI.clear();
            return;
        }

        int Dni = Integer.parseInt(dni);
        modeloResultadosDNI.clear();

        List<String> titulares = clienteDAO.filtrarNombresClientesPorDni(Dni);
        for (String titulare : titulares) {
            modeloResultadosDNI.addElement(titulare);
        }
    }
    
    public void ponerTipoDeCuentaSiExiste(String txtNumeroCuenta, JLabel labelTipoCuenta) {
        String texto = txtNumeroCuenta.trim();

        if (texto.isEmpty() || !texto.matches("\\d+") || !txtNumeroCuenta.matches("[0-9]{10}")) {
            labelTipoCuenta.setText("");
            return;
        }
        Long numeroCuenta = Long.valueOf(texto);
        Cuenta cuenta = cuentaDAO.buscarCuentaPorNumeroCuenta(numeroCuenta);
        if (cuenta != null) {
            labelTipoCuenta.setText(cuenta.getTipoCuenta().toString());
        } else {
            labelTipoCuenta.setText("");
        }  
    }
}
