package org.banco.vistas;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.banco.enums.Formato;
import org.banco.servicios.mantenimiento.MantenimientoOperacion;

public class Operacion_RegistroOperacionesPanel extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    
    private boolean ascendente = true;
    private final MantenimientoOperacion mo;

    public Operacion_RegistroOperacionesPanel() {
        initComponents();
        InitStyles();
        mo = new MantenimientoOperacion();
        
        dtm = (DefaultTableModel) tOperaciones.getModel();
        
        txtBuscarPor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listarOperacionesTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listarOperacionesTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //Nada xd
            }
        });
        
        listarOperacionesTabla();
    }
    
    private void listarOperacionesTabla(){
        mo.listar(dtm, ascendente, boxOrdenarPor.getSelectedIndex(), boxBuscarPor.getSelectedIndex(), txtBuscarPor.getText());
    }

    private void InitStyles() {
        labelDeposito.putClientProperty("FlatLaf.styleClass", "h0");
        
        FlatSVGIcon buscarIcon = new FlatSVGIcon("svg/search.svg", 16, 16);
        Color colorNaranja = UIManager.getColor("Component.focusColor");
        buscarIcon.setColorFilter(new FlatSVGIcon.ColorFilter(colorOriginal -> colorNaranja));
        
        //BuscarPor
        txtBuscarPor.putClientProperty("JTextField.leadingIcon", buscarIcon);
        txtBuscarPor.putClientProperty(FlatClientProperties.STYLE, "arc: 30");
        txtBuscarPor.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        
        txtBuscarPor.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, 
            "Ingrese el " + boxBuscarPor.getSelectedItem());
        
        //ComboBoxes
        boxBuscarPor.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        boxBuscarPor.addActionListener((e) -> {
            
            String nombreBusqueda = boxBuscarPor.getSelectedItem().toString();
            txtBuscarPor.putClientProperty(
                    FlatClientProperties.PLACEHOLDER_TEXT, 
                    "Ingrese " + (nombreBusqueda.equals("ID") ? 
                            " el ID" : nombreBusqueda.endsWith("a") ?
                            " la " + nombreBusqueda.toLowerCase() : nombreBusqueda.toLowerCase().endsWith("s") ? 
                            " los " + nombreBusqueda.toLowerCase() : 
                            " el " + nombreBusqueda.toLowerCase()));
            
            txtBuscarPor.repaint();
        });
        boxOrdenarPor.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        
        //Botones
        btnAscDesc.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
        btnReporte.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        panelTitulos = new javax.swing.JPanel();
        labelDeposito = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelTipoCuenta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tOperaciones = new javax.swing.JTable();
        txtBuscarPor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        boxBuscarPor = new javax.swing.JComboBox<>();
        boxOrdenarPor = new javax.swing.JComboBox<>();
        btnAscDesc = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnReporte = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(518, 396));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDeposito.setText("Registro de operaciones");

        jLabel4.setText("Visualiza la cantidad de operaciones registradas");

        javax.swing.GroupLayout panelTitulosLayout = new javax.swing.GroupLayout(panelTitulos);
        panelTitulos.setLayout(panelTitulosLayout);
        panelTitulosLayout.setHorizontalGroup(
            panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTitulosLayout.setVerticalGroup(
            panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        bg.add(panelTitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        jPanel3.setAlignmentX(0.01F);
        jPanel3.setAlignmentY(0.01F);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(labelTipoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 45, 120, 20));

        tOperaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tOperaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tOperacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tOperaciones);
        if (tOperaciones.getColumnModel().getColumnCount() > 0) {
            tOperaciones.getColumnModel().getColumn(0).setResizable(false);
            tOperaciones.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 500, 240));
        jPanel3.add(txtBuscarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 160, -1));

        jLabel1.setText("Buscar operación:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        boxBuscarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Fecha", "Tipo" }));
        jPanel3.add(boxBuscarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 90, -1));

        boxOrdenarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Fecha", "Tipo" }));
        jPanel3.add(boxOrdenarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 90, -1));

        btnAscDesc.setText("Asc");
        btnAscDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscDescActionPerformed(evt);
            }
        });
        jPanel3.add(btnAscDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, -1, -1));

        jLabel2.setText("Buscar por:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

        jLabel3.setText("Ordenar por:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, -1));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 520, 310));

        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        bg.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 80, 30));

        add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 518, 398));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnAscDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscDescActionPerformed
        ascendente = !ascendente;
        btnAscDesc.setText(ascendente ? "Asc" : "Desc");
        listarOperacionesTabla();
    }//GEN-LAST:event_btnAscDescActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        Window ventanaPadre = SwingUtilities.getWindowAncestor(this);
        ReporteDialog reporteDialog = new ReporteDialog((JFrame) ventanaPadre, true);
        
        if (reporteDialog.isConfirmado()) {
            String nombre = reporteDialog.getNombreArchivo();
            Formato formato = reporteDialog.getFormato();
            mo.generarReporte(
                    nombre
                    , formato
                    , ascendente
                    , boxOrdenarPor.getSelectedIndex()
                    , boxBuscarPor.getSelectedIndex()
                    , txtBuscarPor.getText()
            );
        }
    }
//GEN-LAST:event_btnReporteActionPerformed

    private void tOperacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tOperacionesMouseClicked
        if (evt.getClickCount() == 2) {
            mo.mostrarVoucher((int) dtm.getValueAt(tOperaciones.getSelectedRow(), 0));
        }
    }//GEN-LAST:event_tOperacionesMouseClicked
    //======================EVENTOS==========================
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JComboBox<String> boxBuscarPor;
    private javax.swing.JComboBox<String> boxOrdenarPor;
    private javax.swing.JButton btnAscDesc;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDeposito;
    private javax.swing.JLabel labelTipoCuenta;
    private javax.swing.JPanel panelTitulos;
    private javax.swing.JTable tOperaciones;
    private javax.swing.JTextField txtBuscarPor;
    // End of variables declaration//GEN-END:variables
}
