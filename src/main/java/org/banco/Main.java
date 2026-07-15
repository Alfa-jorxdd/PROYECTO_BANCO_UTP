package org.banco;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import javax.swing.UIManager;

import org.banco.vistas.Dashboard;

public class Main {
    public static void main(String[] args) {
        FlatArcDarkOrangeIJTheme.setup();
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("Table.alternateRowColor", UIManager.getColor("Panel.background"));

        Dashboard pantalla = new Dashboard();
    }
}