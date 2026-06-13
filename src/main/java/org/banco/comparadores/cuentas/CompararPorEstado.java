package org.banco.comparadores.cuentas;

import java.util.Comparator;
import org.banco.modelos.Cuenta;

public class CompararPorEstado implements Comparator<Cuenta> {

    @Override
    public int compare(Cuenta o1, Cuenta o2) {
        return o1.getEstadoCuenta().toString().compareTo(o2.getEstadoCuenta().toString());
    }
    
}
