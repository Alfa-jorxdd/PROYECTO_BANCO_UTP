package org.banco.interfaces;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public interface Gestionable {
    void agregar();
    void eliminar();
    void actualizar();
    void listar(DefaultTableModel dtm, boolean ascendente, int criterioOrden, int criterioFiltrado, String textoFiltrado);
}
