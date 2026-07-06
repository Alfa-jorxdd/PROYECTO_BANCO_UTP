package org.banco.modelos;

import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.banco.dao.CuentaDAO;
import org.banco.enums.TipoOperacion;

public class Voucher {

    private Operacion registroOperacion;
    private CuentaDAO cuentaDAO;
    private static final int ANCHO = 40;

    public Voucher(Operacion registroOperacion) {
        this.registroOperacion = registroOperacion;
        cuentaDAO = new CuentaDAO();
    }

    public void imprimirVoucher() {
        StringBuilder sb = new StringBuilder();

        //Formatear fecha yyyy-MM-dd HH:mm
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", new Locale("es", "PE"));
        sb.append(borde('╔', '╗'));
        sb.append(tituloCentrado("VOUCHER"));
        sb.append(borde('╠', '╣'));
        sb.append(linea(" ID Operación: ", String.valueOf(registroOperacion.getIdOperacion())));
        sb.append(linea(" Fecha: ", fecha.format(registroOperacion.getFechaOperacion())));
        sb.append(borde('╠', '╣'));


        TipoOperacion operacion = registroOperacion.getOperacion();

        double saldo = 0;
        String simbolo = "";
        if (operacion == TipoOperacion.CONSULTA){
            simbolo = cuentaDAO.buscarCuentaPorId(registroOperacion.getIdCuentaOrigen()).getMoneda().getSimbolo();
            saldo = cuentaDAO.buscarCuentaPorId(registroOperacion.getIdCuentaOrigen()).getSaldo();

        }
        switch (operacion) {
            case DEPOSITO:
                sb.append(tituloCentrado("DEPOSITO"));
                sb.append(linea(" N° cuenta: ", String.valueOf(registroOperacion.getNumeroCuentaOrigen())));
                sb.append(linea(" DNI: ", String.valueOf(registroOperacion.getDni())));
                sb.append(linea(" Monto: ", registroOperacion.getMoneda().getSimbolo() + String.format("%.2f", registroOperacion.getMonto())));
                break;
            case RETIRO:
                sb.append(tituloCentrado("RETIRO"));
                sb.append(linea(" N° cuenta: ", String.valueOf(registroOperacion.getNumeroCuentaOrigen())));
                sb.append(linea(" DNI: ", String.valueOf(registroOperacion.getDni())));
                sb.append(linea(" Monto: ", registroOperacion.getMoneda().getSimbolo() + String.format("%.2f", registroOperacion.getMonto())));
                break;
            case TRANSFERENCIA_ENVIADA:
                sb.append(tituloCentrado("TRANSFERENCIA ENVIADA"));
                sb.append(linea(" N° cuenta ori : ", String.valueOf(registroOperacion.getNumeroCuentaOrigen())));
                sb.append(linea(" N° cuenta des :", String.valueOf(registroOperacion.getNumeroCuentaDestino())));
                sb.append(linea(" DNI :", String.valueOf(registroOperacion.getDni())));
                sb.append(linea(" Monto : ", registroOperacion.getMoneda().getSimbolo() + String.format("%.2f", registroOperacion.getMonto())));
                break;
            case TRANSFERENCIA_RECIBIDA:
                sb.append(tituloCentrado("TRANSFERENCIA RECIBIDA"));
                sb.append(linea(" N° cuenta ori : ", String.valueOf(registroOperacion.getNumeroCuentaOrigen())));
                sb.append(linea(" N° cuenta des :", String.valueOf(registroOperacion.getNumeroCuentaDestino())));
                sb.append(linea(" DNI :", String.valueOf(registroOperacion.getDni())));
                sb.append(linea(" Monto : ", registroOperacion.getMoneda().getSimbolo() + String.format("%.2f", registroOperacion.getMonto())));
                break;

            case CONSULTA:
                sb.append(tituloCentrado("CONSULTA"));
                sb.append(linea(" N° cuenta : ", String.valueOf(registroOperacion.getNumeroCuentaOrigen())));
                sb.append(linea(" Monto :", simbolo + String.format("%.2f", saldo)));
                break;
        }

        sb.append(borde('╚', '╝'));
        construirPantalla(sb);
    }

    private void construirPantalla(StringBuilder sb){
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setBackground(null);
        textArea.setBorder(null);

        String[] opciones = {"Aceptar"};
        JOptionPane.showOptionDialog(null,
                textArea,
                "Resumen de la operacion",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);
    }

    private String linea(String etiqueta, String valor) {
        int espacios = ANCHO - etiqueta.length() - valor.length();
        if (espacios < 1) espacios = 1; // evita que se rompa si el valor es muy largo
        return "║" + etiqueta + " ".repeat(espacios) + valor + "║\n";
    }

    private String borde(char esquinaIzq, char esquinaDer) {
        return esquinaIzq + "═".repeat(ANCHO) + esquinaDer + "\n";
    }

    private String tituloCentrado(String texto) {
        int espaciosTotal = ANCHO - texto.length();
        int izq = espaciosTotal / 2;
        int der = espaciosTotal - izq;
        return "║" + " ".repeat(izq) + texto + " ".repeat(der) + "║\n";
    }

}
