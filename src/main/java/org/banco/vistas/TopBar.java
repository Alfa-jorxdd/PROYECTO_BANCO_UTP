package org.banco.vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public final class TopBar extends JPanel {

    private int xMouse, yMouse;

    private final JLabel labelBtnExit;
    private final JPanel panelBtnExit;
    private final JFrame ventana;
    private final Color color;
    private final Cursor handCursor;

    public TopBar(JFrame ventana) {

        color = UIManager.getColor("Panel.background");
        
        handCursor = new Cursor(Cursor.HAND_CURSOR);

        this.ventana = ventana;

        //Panel TopBar
        this.setLayout(null);
        this.setBounds(0, 0, 1120, 30);
        this.setCursor(handCursor);
        this.setBackground(color);
        //Panel botón exit
        panelBtnExit = new JPanel();
        panelBtnExit.setBounds(1120 - 57, 0, 57, 30);
        panelBtnExit.setCursor(handCursor);
        panelBtnExit.setBackground(color);
        //Label botón exit
        labelBtnExit = new JLabel("X");
        labelBtnExit.setBounds(0, 0, 57, 30);
        labelBtnExit.setHorizontalAlignment(JLabel.CENTER);
        labelBtnExit.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        labelBtnExit.setForeground(Color.ORANGE);
        
        panelBtnExit.add(labelBtnExit);
        this.add(panelBtnExit);
        
        enableDrag();
        btnExitFuncion(labelBtnExit, color);

    }

    public void enableDrag() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        };
        this.addMouseListener(mouseAdapter);

        MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                ventana.setLocation(e.getXOnScreen() - xMouse, e.getYOnScreen() - yMouse);
            }

        };
        this.addMouseMotionListener(mouseMotionAdapter);
    }
    
    public void btnExitFuncion(JLabel labelBntExit, Color colorDefault){
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelBtnExit.setBackground(Color.RED);
                labelBntExit.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelBtnExit.setBackground(colorDefault);
                labelBntExit.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }  
        };
        panelBtnExit.addMouseListener(mouseAdapter);
        
    }
}
