package org.banco.comparadores.cuentas;

import java.util.Comparator;
import org.banco.modelos.Cuenta;

public class CompararPorTipo implements Comparator<Cuenta>{

    @Override
    public int compare(Cuenta o1, Cuenta o2) {
        return o1.getTipoCuenta().toString().compareTo(o2.getTipoCuenta().toString());
    }
    
}
