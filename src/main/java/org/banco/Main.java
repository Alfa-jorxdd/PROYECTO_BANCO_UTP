package org.banco;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.banco.config.ConexionSQLServer;
import org.banco.modelos.Banco;
import org.banco.vistas.Dashboard;

public class Main {
    public static void main(String[] args) {
        FlatArcDarkOrangeIJTheme.setup();
        UIManager.put("Component.arc", 0);

        Dashboard pantalla = new Dashboard();
    }
}
