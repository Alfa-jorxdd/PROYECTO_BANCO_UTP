package org.banco;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopBar extends JPanel {

    private int xMouse, yMouse;

    private JLabel labelBtnExit;
    private JPanel panelBtnExit;
    private JFrame ventana;

    private Cursor handCursor;

    public TopBar(JFrame ventana, Color colorDefault) {

        handCursor = new Cursor(Cursor.HAND_CURSOR);

        this.ventana = ventana;

        //Panel TopBar
        this.setLayout(null);
        this.setBounds(0, 0, 800, 30);
        this.setCursor(handCursor);
        //Panel botón exit
        panelBtnExit = new JPanel();
        panelBtnExit.setBounds(747, 0, 57, 30);
        panelBtnExit.setCursor(handCursor);
        //Label botón exit
        labelBtnExit = new JLabel("X");
        labelBtnExit.setBounds(0, 0, 57, 30);
        labelBtnExit.setHorizontalAlignment(JLabel.CENTER);
        labelBtnExit.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        labelBtnExit.setForeground(Color.ORANGE);
        
        panelBtnExit.add(labelBtnExit);
        this.add(panelBtnExit);
        
        enableDrag();
        btnExitFuncion(labelBtnExit, colorDefault);

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
