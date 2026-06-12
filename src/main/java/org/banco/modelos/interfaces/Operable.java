package org.banco.modelos.interfaces;

import org.banco.modelos.enums.Moneda;

public interface Operable {
    void depositar(long numeroCuenta, double monto, Moneda monedaOperacion);
    void retirar(long numeroCuenta, double monto, Moneda monedaOperacion);
    void consultar();
    void transferir();
}
