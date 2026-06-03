package org.banco.modelos.interfaces;

import javax.swing.table.DefaultTableModel;

public interface Gestionable {
    void agregar();
    void eliminar();
    void actualizar();
    void listar(DefaultTableModel dtm, Object[] obj);
}
