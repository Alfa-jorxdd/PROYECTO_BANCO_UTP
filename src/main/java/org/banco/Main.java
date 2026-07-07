package org.banco;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import javax.swing.UIManager;

import org.banco.vistas.Dashboard;

public class Main {
    public static void main(String[] args) {
        FlatArcDarkOrangeIJTheme.setup();
        UIManager.put("Component.arc", 0);

        Dashboard pantalla = new Dashboard();
    }
}