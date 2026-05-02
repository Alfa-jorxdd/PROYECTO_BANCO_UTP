package org.banco;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.banco.modelo.Banco;
import org.banco.views.Dashboard;

public class Main {
    public static void main(String[] args) {
        FlatArcDarkOrangeIJTheme.setup();
        UIManager.put("Component.arc", 0);
        
        Banco banco = new Banco();
        
        Dashboard pantalla = new Dashboard(banco);
        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
        
        try {
            banco.cargarClientes();
            JOptionPane.showMessageDialog(null, "Los clientes se han cargado con éxito ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar CLientes " + e);
        }
        
        
        
    }
}
