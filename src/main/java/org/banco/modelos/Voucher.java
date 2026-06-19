package org.banco.modelos;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.banco.enums.TipoOperacion;

public class Voucher {

    private RegistroOperacion registroOperacion;
    private Banco banco;

    public Voucher(RegistroOperacion registroOperacion, Banco banco) {
        this.registroOperacion = registroOperacion;
        this.banco = banco;
    }

    public void imprimirVoucher() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                  "╔══════════════════════════════╗\n"
                + "║           VOUCHER            ║\n"
                + "╠══════════════════════════════╣\n"
                + "║ ID Operación  :  %-12d║\n"
                + "║ Fecha         :  %-12s║\n"
                + "╠══════════════════════════════╣\n",
                registroOperacion.getIdOperacion(),
                registroOperacion.getFechaOperacion()));

        TipoOperacion operacion = registroOperacion.getOperacion();
        Cuenta cuentaOrigen = banco.buscarCuentaPorId(registroOperacion.getIdCuentarOrigen());
        Cuenta cuentaDestino = banco.buscarCuentaPorId(registroOperacion.getIdCuentaDestino());
        switch (operacion) {
            case DEPOSITO:
                sb.append("║===========DEPOSITO===========║\n");
                sb.append(String.format(
                          "║ N° cuenta     :  %-12d║\n"
                        + "║ DNI           :  %-12d║\n"
                        + "║ Monto         :  %-2s%-10.2f║\n"
                        + "╚══════════════════════════════╝",
                         cuentaOrigen.getNumeroCuenta(),
                         registroOperacion.getDni(),
                         registroOperacion.getMoneda().getSimbolo(),
                         registroOperacion.getMonto())
                );
                break;
            case RETIRO:
                sb.append("║============RETIRO============║\n");
                sb.append(String.format(
                          "║ N° cuenta env.:  %-12d║\n"
                        + "║ DNI           :  %-12d║\n"
                        + "║ Monto         :  %-2s%-10.2f║\n"
                        + "╚══════════════════════════════╝",
                         cuentaOrigen.getNumeroCuenta(),
                         registroOperacion.getDni(),
                         registroOperacion.getMoneda().getSimbolo(),
                         registroOperacion.getMonto())
                );
                break;
            case TRANSFERENCIA_ENVIADA:
                sb.append("║=====TRANSFERENCIA ENVIADA====║\n");
                sb.append(String.format(
                          "║ N° cuenta ori :  %-12d║\n"
                        + "║ N° cuenta des :  %-12d║\n"
                        + "║ DNI           :  %-12d║\n"
                        + "║ Monto         :  %-2s%-10.2f║\n"
                        + "╚══════════════════════════════╝",
                         cuentaOrigen.getNumeroCuenta(),
                         cuentaDestino.getNumeroCuenta(),
                         registroOperacion.getDni(),
                         registroOperacion.getMoneda().getSimbolo(),
                         registroOperacion.getMonto())
                );
                break;
            case TRANSFERENCIA_RECIBIDA:
                sb.append("║====TRANSFERENCIA RECIBIDA====║\n");
                sb.append(String.format(
                          "║ N° cuenta ori :  %-12d║\n"
                        + "║ N° cuenta des :  %-12d║\n"
                        + "║ DNI           :  %-12d║\n"
                        + "║ Monto         :  %-2s%-10.2f║\n"
                        + "╚══════════════════════════════╝",
                         cuentaOrigen.getNumeroCuenta(),
                         cuentaDestino.getNumeroCuenta(),
                         registroOperacion.getDni(),
                         registroOperacion.getMoneda().getSimbolo(),
                         registroOperacion.getMonto())
                );
                break;    

            case CONSULTA:
                sb.append("║============CONSULTA==========║\n");
                sb.append(String.format(
                          "║ N° cuenta     :  %-12d║\n"
                        + "║ Monto         :  %-2s%-10.2f║\n"
                        + "╚══════════════════════════════╝",
                         cuentaOrigen.getNumeroCuenta(),
                         cuentaOrigen.getMoneda().getSimbolo(),
                         cuentaOrigen.getSaldo()) 
                );
                break;
        }
        
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
}
