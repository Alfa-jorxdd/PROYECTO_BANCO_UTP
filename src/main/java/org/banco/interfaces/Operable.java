package org.banco.interfaces;

import javax.swing.table.DefaultTableModel;
import org.banco.enums.Moneda;

public interface Operable {
    void depositar(long numeroCuenta, double monto, Moneda monedaOperacion, int DNI);
    void retirar(long numeroCuenta, double monto, Moneda monedaOperacion, int DNI);
    void consultar(long numeroCuenta);
    void transferir(long numeroCuentaOrigen, long numeroCuentaDestino, double monto, Moneda monedaOperacion, int DNI);
    void listar(DefaultTableModel dtm, boolean ascendente, int criterioOrden, int criterioFiltrado, String textoFiltrado);
}
