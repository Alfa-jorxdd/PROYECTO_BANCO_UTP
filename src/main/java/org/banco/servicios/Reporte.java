package org.banco.servicios;

import org.banco.enums.Formato;
import org.banco.enums.TipoReporte;

import java.util.List;

public abstract class Reporte {
    
    private String nombre;
    private Formato formato;
    private TipoReporte tipoReporte;
    
    public Reporte(String nombre, Formato formato, TipoReporte tipoReporte){
        this.nombre = nombre;
        this.formato = formato;
        this.tipoReporte = tipoReporte;
        
    }

    protected String[] obtenerEncabezados() {
        return switch (getTipoReporte()) {
            case CLIENTES -> new String[]{"ID", "Nombres", "Apellidos", "DNI", "Teléfono", "Correo"};
            case CUENTAS -> new String[]{"ID", "Tipo", "Estado", "N° Cuenta", "Titular", "Saldo", "Moneda"};
            case OPERACIONES ->
                    new String[]{"ID", "Cuenta Origen", "Cuenta Destino", "Dni", "Monto", "Moneda", "Fecha", "Operacion"};
        };
    }
    
    public abstract void crearReporte(List<Object[]> lista);

    public String getNombre() {
        return nombre;
    }

    public Formato getFormato() {
        return formato;
    }

    public TipoReporte getTipoReporte() {
        return tipoReporte;
    }
}
