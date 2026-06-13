package org.banco.comparadores.cuentas;

import java.util.Comparator;
import org.banco.modelos.Cuenta;

public class CompararPorNumCuenta implements Comparator<Cuenta>{

    @Override
    public int compare(Cuenta o1, Cuenta o2) {
        return Long.compare(o1.getNumeroCuenta(), o2.getNumeroCuenta());
    }
    
}
