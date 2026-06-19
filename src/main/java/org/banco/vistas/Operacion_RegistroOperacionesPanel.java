package org.banco.vistas;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.banco.logica.mantenimiento.MantenimientoOperacion;
import org.banco.modelos.Banco;

public class Operacion_RegistroOperacionesPanel extends javax.swing.JPanel {

    private Banco banco;
    private DefaultTableModel dtm = new DefaultTableModel();
    
    private boolean ascendente = true;
    private MantenimientoOperacion mo;

    public Operacion_RegistroOperacionesPanel(Banco banco) {
        initComponents();
        InitStyles();

        this.banco = banco;
        mo = new MantenimientoOperacion(banco);
        
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
        tOperaciones.setShowVerticalLines(true);
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
                .addContainerGap(10, Short.MAX_VALUE))
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

        bg.add(panelTitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

        add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 518, 398));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnAscDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscDescActionPerformed
        ascendente = !ascendente;
        btnAscDesc.setText(ascendente ? "Asc" : "Desc");
        listarOperacionesTabla();
    }//GEN-LAST:event_btnAscDescActionPerformed
    //======================EVENTOS==========================
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JComboBox<String> boxBuscarPor;
    private javax.swing.JComboBox<String> boxOrdenarPor;
    private javax.swing.JButton btnAscDesc;
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
