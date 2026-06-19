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
import org.banco.enums.EstadoCuenta;
import org.banco.modelos.Banco;
import org.banco.modelos.Cuenta;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;
import org.banco.enums.TipoOperacion;
import org.banco.interfaces.Operable;
import org.banco.modelos.Cliente;
import org.banco.modelos.Cuenta_Ahorro;
import org.banco.modelos.Cuenta_Corriente;
import org.banco.modelos.RegistroOperacion;
import org.banco.modelos.Voucher;

public class MantenimientoOperacion implements Operable {

    private Banco banco;

    public MantenimientoOperacion(Banco banco) {
        this.banco = banco;
    }

    @Override
    public void depositar(long numeroCuenta, double monto, Moneda monedaOperacion, int DNI) {
        //Obtener datos esenciales
        Cuenta cuenta = banco.buscarCuentaPorNumeroCuenta(numeroCuenta);
        if (!cuentaExiste(cuenta)) {
            return;
        }

        double montoDepositar = montoSegunMoneda(monto, monedaOperacion, cuenta);
        //Validar el deposito
        if (!depositoValido(cuenta, montoDepositar)) {
            return;
        }
        //Realizar el deposito
        cuenta.setSaldo(montoDepositar);
        if (cuenta.getTipoCuenta() == TipoCuenta.AHORRO) {
            Cuenta_Ahorro cuenta_Ahorro = (Cuenta_Ahorro) cuenta;
            cuenta_Ahorro.aumentarContador();
        }

        //Generar registro de la operaciión y VOUCHER
        int[] idCuenta = {cuenta.getIdCuenta()};

        RegistroOperacion nuevaOperacion = banco.agregarListaOperaciones(idCuenta, DNI, TipoOperacion.DEPOSITO, montoDepositar, monedaOperacion);
        Voucher voucherOperacion = new Voucher(nuevaOperacion, banco);
        voucherOperacion.imprimirVoucher();
    }

    @Override
    public void retirar(long numeroCuenta, double monto, Moneda monedaOperacion, int DNI) {

        Cuenta cuenta = banco.buscarCuentaPorNumeroCuenta(numeroCuenta);
        if (!cuentaExiste(cuenta)) {
            return;
        }
        double montoRetirar = montoSegunMoneda(monto, monedaOperacion, cuenta);
        System.out.println(montoRetirar);
        //Validar el retiro
        if (!retiroValido(cuenta, montoRetirar, true)) {
            return;
        }
        //Realizar retiro
        cuenta.setSaldo(-montoRetirar);
        if (cuenta.getTipoCuenta() == TipoCuenta.AHORRO) {
            Cuenta_Ahorro cuenta_Ahorro = (Cuenta_Ahorro) cuenta;
            cuenta_Ahorro.aumentarContador();
        }
        //Generar registro de la operacion y VOUCHER
        int[] idCuenta = {cuenta.getIdCuenta()};

        RegistroOperacion nuevaOperacion = banco.agregarListaOperaciones(idCuenta, DNI, TipoOperacion.RETIRO, montoRetirar, monedaOperacion);
        Voucher voucherOperacion = new Voucher(nuevaOperacion, banco);
        voucherOperacion.imprimirVoucher();
    }

    @Override
    public void consultar(long numeroCuenta) {
        Cuenta cuenta = banco.buscarCuentaPorNumeroCuenta(numeroCuenta);
        if (!cuentaExiste(cuenta)) {
            return;
        }
        RegistroOperacion nuevaOperacion = banco.agregarListaOperaciones(cuenta.getIdCuenta(), TipoOperacion.CONSULTA);
        Voucher voucher = new Voucher(nuevaOperacion, banco);
        voucher.imprimirVoucher();
    }

    @Override
    public void transferir(long numeroCuentaOrigen, long numeroCuentaDestino, double monto, Moneda monedaOperacion, int DNI) {
        Cuenta cuentaOrigen = banco.buscarCuentaPorNumeroCuenta(numeroCuentaOrigen);
        Cuenta cuentaDestino = banco.buscarCuentaPorNumeroCuenta(numeroCuentaDestino);
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
        cuentaOrigen.setSaldo(-montoRetiro);
        if (cuentaOrigen.getTipoCuenta() == TipoCuenta.AHORRO) {
            Cuenta_Ahorro cuenta_Ahorro = (Cuenta_Ahorro) cuentaOrigen;
            cuenta_Ahorro.aumentarContador();
        }

        cuentaDestino.setSaldo(montoDeposito);

        int[] idCuentas = {cuentaOrigen.getIdCuenta(), cuentaDestino.getIdCuenta()};
        RegistroOperacion operacionTransferenciaEnviada = banco.agregarListaOperaciones(idCuentas, DNI, TipoOperacion.TRANSFERENCIA_ENVIADA, montoRetiro, monedaOperacion);
        RegistroOperacion operacionTransferenciaRecibida = banco.agregarListaOperaciones(idCuentas, DNI, TipoOperacion.TRANSFERENCIA_RECIBIDA, montoDeposito, monedaOperacion);

        Voucher vaucherTransferenciaEnv = new Voucher(operacionTransferenciaEnviada, banco);
        vaucherTransferenciaEnv.imprimirVoucher();
        Voucher voucherTransferenciaRec = new Voucher(operacionTransferenciaRecibida, banco);
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
                if (cuenta_Ahorro.getContador() >= cuenta_Ahorro.getLimiteTransaccionesDiarios()) {
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

    private boolean depositoValido(Cuenta cuenta, double monto) {
        if (!montoValido(cuenta, monto)) {
            return false;
        }
        if (cuentaBloqueada(cuenta)) {
            return false;
        }

        boolean esValido = true;

        if (cuenta.getTipoCuenta() == TipoCuenta.AHORRO) {
            Cuenta_Ahorro cuenta_Ahorro = (Cuenta_Ahorro) cuenta;
            if (cuenta_Ahorro.getContador() >= cuenta_Ahorro.getLimiteTransaccionesDiarios()) {
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
        if (!depositoValido(cuentaDestino, montoDeposito)) {
            return false;
        }

        return true;
    }

    private boolean autorizaciónMancomunada(Cuenta cuenta) {
        Cliente[] titulares = banco.buscarClientesPorIdCuenta(cuenta.getIdCuenta());

        JCheckBox[] checks = new JCheckBox[titulares.length];
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Todos los titulares deben confirmar la operación"));
        panel.add(new JLabel("================================================"));
        panel.add(Box.createVerticalStrut(10));

        for (int i = 0; i < titulares.length; i++) {
            checks[i] = new JCheckBox(titulares[i].getNombres() + " " + titulares[i].getApellidos());
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
    //====================================================================================================================================
    //===============================================LÓGICA PARA LISTAR===================================================================
    @Override
    public void listar(DefaultTableModel dtm, boolean ascendente, int criterioOrden, int criterioFiltrado, String textoFiltrado) {
        List<List<Object[]>> bloque = construirBloque();
        bloque = filtrar(bloque, criterioFiltrado, textoFiltrado);
        
        Comparator<List<Object[]>> comparador = null;
        switch (criterioOrden){
            case 0 : 
                comparador = (o1, o2) -> Integer.compare((int)o1.get(0)[0], (int)o2.get(0)[0]); break;
            case 1 :
                comparador = (o1, o2) -> o1.get(0)[1].toString().compareTo(o2.get(0)[1].toString()); break;
            case 2 :    
                comparador = (o1, o2) -> o1.get(0)[2].toString().compareTo(o2.get(0)[2].toString()); break;
        }
        bloque.sort(ascendente ? comparador : comparador.reversed());
        
        dtm.setRowCount(0);
        for (List<Object[]> elementos : bloque) {
            for (Object[] fila : elementos) {
                dtm.addRow(fila);
            }
        }
    }
    
    private List<List<Object[]>> filtrar(List<List<Object[]>> bloque, int criterioFiltrar, String textoFiltrar){
        if (textoFiltrar.trim().isEmpty()) {
            return bloque;
        }
        List<List<Object[]>> bloqueFiltrado = new ArrayList<>();
        String texto = textoFiltrar.toLowerCase().trim();
        
        for (List<Object[]> elementos : bloque) {
            boolean coincide = false;
            for (Object[] fila : elementos) {
                if (fila[criterioFiltrar].toString().toLowerCase().contains(texto)) {
                    coincide = true;
                    break;
                }
            }
            if (coincide) bloqueFiltrado.add(elementos);
        }
        return bloqueFiltrado;
    }
    
    private List<List<Object[]>> construirBloque(){
        List<List<Object[]>> bloque = new ArrayList<>();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", new Locale("es", "PE"));
        
        for (int i = 0; i < banco.getOperaciones().length - 1; i++) {
            List<Object[]> elementos = new ArrayList<>();
            Object[] fila = new Object[3];
            fila[0] = banco.getOperaciones()[i].getIdOperacion();
            LocalDateTime fecha = banco.getOperaciones()[i].getFechaOperacion();
            fila[1] = formato.format(fecha);
            fila[2] = banco.getOperaciones()[i].getOperacion();
            elementos.add(fila);
            bloque.add(elementos);
        }
        return bloque;
    }
    
    public void filtrarModelCuenta(String texto, DefaultListModel<String> modeloResultadosCuenta) {
        String numeroBuscado = texto.trim();
        modeloResultadosCuenta.clear();

        if (numeroBuscado.isEmpty() || !numeroBuscado.matches("[0-9]+")) {
            return;
        }

        for (int i = 0; i < banco.getCuentas().length - 1; i++) {
            String cuentaTxt = String.valueOf(banco.getCuentas()[i].getNumeroCuenta());
            
            if (cuentaTxt.contains(numeroBuscado)) {
                Cliente[] titulares = banco.buscarClientesPorIdCuenta(banco.getCuentas()[i].getIdCuenta());
                for (Cliente titulare : titulares) {
                    String primerNombre = titulare.getNombres().split(" ")[0];
                    String primerApellido = titulare.getApellidos().split(" ")[0];
                    modeloResultadosCuenta.addElement(primerNombre + " " + primerApellido + " - " + banco.getCuentas()[i].getNumeroCuenta());
                }
            }
        }
    }

    public void cargarModelosConClientes(DefaultListModel<String> modelo) {
        modelo.removeAllElements();
        for (int i = 0; i < banco.getClientes().length - 1; i++) {
            Cliente c = banco.getClientes()[i];
            modelo.addElement(c.getNombres() + " " + c.getApellidos());
        }
    }
    
    public void filtrarModelDNI(String dni, DefaultListModel<String> modeloResultadosDNI) {
        String dniBuscado = dni.trim();
        modeloResultadosDNI.clear();

        if (dniBuscado.isEmpty() || !dniBuscado.matches("[0-9]+")) {
            return;
        }

        for (int i = 0; i < banco.getClientes().length - 1; i++) {
            String dniTxt = String.valueOf(banco.getClientes()[i].getDni());
            String primerNombre = banco.getClientes()[i].getNombres().split(" ")[0];
            String primerApellido = banco.getClientes()[i].getApellidos().split(" ")[0];

            if (dniTxt.contains(dniBuscado)) {
                modeloResultadosDNI.addElement(primerNombre + " " + primerApellido + " - " + dniTxt);
            }
        }
    }
    
    public void ponerTipoDeCuentaSiExiste(String txtNumeroCuenta, JLabel labelTipoCuenta) {
        String texto = txtNumeroCuenta.trim();

        if (texto.isEmpty() || !texto.matches("\\d+")) {
            labelTipoCuenta.setText("");
            return;
        }
        Long numeroCuenta = Long.valueOf(texto);
        Cuenta cuenta = banco.buscarCuentaPorNumeroCuenta(numeroCuenta);
        if (cuenta != null) {
            labelTipoCuenta.setText(cuenta.getTipoCuenta().toString());
        } else {
            labelTipoCuenta.setText("");
        }  
    }
}
