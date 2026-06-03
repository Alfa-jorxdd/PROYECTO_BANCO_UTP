package org.banco.modelos.interfaces;

public interface Operable {
    void depositar(long numeroCuenta, double monto);
    void retirar();
    void consultar();
    void transferir();
}
