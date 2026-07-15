package org.banco.vistas;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.banco.servicios.mantenimiento.MantenimientoOperacion;
import org.banco.enums.Moneda;

public class Operacion_TransferenciaPanel extends javax.swing.JPanel {

    private final DefaultListModel<String> modeloResultadosCuentaOri = new DefaultListModel<>();
    private final DefaultListModel<String> modeloResultadosCuentaDest = new DefaultListModel<>();
    
    private final MantenimientoOperacion mo;
    private final OperacionPanel operacionPanel;

    public Operacion_TransferenciaPanel(OperacionPanel operacionPanel) {
        initComponents();
        InitStyles();
        this.operacionPanel = operacionPanel;
        mo = new MantenimientoOperacion();

        listNumCuentaOrigen.setModel(modeloResultadosCuentaOri);
        listNumCuentaDestino.setModel(modeloResultadosCuentaDest);


        txtNumCuentaOrigen.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                mo.filtrarModelCuenta(txtNumCuentaOrigen.getText(), modeloResultadosCuentaOri);
                mo.ponerTipoDeCuentaSiExiste(txtNumCuentaOrigen.getText().trim(), labelTipoCuenta1);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mo.filtrarModelCuenta(txtNumCuentaOrigen.getText(), modeloResultadosCuentaOri);
                mo.ponerTipoDeCuentaSiExiste(txtNumCuentaOrigen.getText().trim(), labelTipoCuenta1);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtNumCuentaDestino.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                mo.filtrarModelCuenta(txtNumCuentaDestino.getText(), modeloResultadosCuentaDest);
                mo.ponerTipoDeCuentaSiExiste(txtNumCuentaDestino.getText().trim(), labelTipoCuenta2);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mo.filtrarModelCuenta(txtNumCuentaDestino.getText(), modeloResultadosCuentaDest);
                mo.ponerTipoDeCuentaSiExiste(txtNumCuentaDestino.getText().trim(), labelTipoCuenta2);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        
    }
    
    private void InitStyles() {
        labelDeposito.putClientProperty("FlatLaf.styleClass", "h0");
        btnRetirar.putClientProperty(FlatClientProperties.STYLE, "arc: 30");
    }
    
    private boolean formulariosLlenos(){
        boolean hayFormulariosVacios = txtNumCuentaOrigen.getText().trim().isEmpty() 
                || txtNumCuentaDestino.getText().trim().isEmpty() 
                || txtMonto.getText().trim().isEmpty() 
                || txtDNI.getText().trim().isEmpty()
                || boxTipoMoneda.getSelectedIndex() == 0;
        if (hayFormulariosVacios) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean formulariosValidos(){
        if (!txtNumCuentaOrigen.getText().matches("^[0-9]{10}$") 
                || !txtNumCuentaDestino.getText().matches("^[0-9]{10}$")) {
            JOptionPane.showMessageDialog(null, "Números de cuenta inválidos", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (!txtMonto.getText().trim().matches("^[0-9]+([.][0-9]{1,2})?$") 
                || Double.parseDouble(txtMonto.getText().trim()) <= 0) {
            JOptionPane.showMessageDialog(null, "Monto inválido. Solo debe contener números positivos y máximo 2 dígitos decimales", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!txtDNI.getText().trim().matches("^[0-9]{8}$")) {
            JOptionPane.showMessageDialog(null, "DNI inválido");
            return false;
        }
        return true;
    }
    
    private void limpiarFormularios(){
        txtNumCuentaOrigen.setText("");
        txtNumCuentaDestino.setText("");
        txtDNI.setText("");
        txtMonto.setText("");
        boxTipoMoneda.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        panelTitulos = new javax.swing.JPanel();
        labelDeposito = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnRetirar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtNumCuentaOrigen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listNumCuentaOrigen = new javax.swing.JList<>();
        txtDNI = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        boxTipoMoneda = new javax.swing.JComboBox<>();
        labelMoneda = new javax.swing.JLabel();
        labelTipoCuenta1 = new javax.swing.JLabel();
        labelNumCuentaOrigen = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labelNumCuentaDestino = new javax.swing.JLabel();
        txtNumCuentaDestino = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listNumCuentaDestino = new javax.swing.JList<>();
        txtMonto = new javax.swing.JTextField();
        labelMonto = new javax.swing.JLabel();
        labelTipoCuenta2 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(518, 396));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDeposito.setText("Transferencia");

        jLabel4.setText("Transfiere fondos de una cuenta a otra");

        javax.swing.GroupLayout panelTitulosLayout = new javax.swing.GroupLayout(panelTitulos);
        panelTitulos.setLayout(panelTitulosLayout);
        panelTitulosLayout.setHorizontalGroup(
            panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        btnRetirar.setText("Confirmar transferencia");
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 318, 518, 80));

        jPanel3.setAlignmentX(0.01F);
        jPanel3.setAlignmentY(0.01F);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtNumCuentaOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 220, 30));

        listNumCuentaOrigen.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listNumCuentaOrigenValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listNumCuentaOrigen);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 220, 37));
        jPanel3.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 207, 32));

        jLabel1.setText("DNI:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        boxTipoMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NINGUNA", "SOL", "DOLAR" }));
        jPanel3.add(boxTipoMoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 113, -1));

        labelMoneda.setText("Moneda:");
        jPanel3.add(labelMoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, -1));
        jPanel3.add(labelTipoCuenta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 2, 120, 20));

        labelNumCuentaOrigen.setText("Cuenta: (Origen)");
        jPanel3.add(labelNumCuentaOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 194, 24));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 253, 230));

        labelNumCuentaDestino.setText("Cuenta: (Destino)");

        listNumCuentaDestino.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listNumCuentaDestinoValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listNumCuentaDestino);

        labelMonto.setText("Monto");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNumCuentaDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(labelNumCuentaDestino)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(labelTipoCuenta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtMonto)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelNumCuentaDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTipoCuenta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumCuentaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(labelMonto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        bg.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 90, 250, 230));

        add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 518, 398));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    
    //======================EVENTOS==========================
    
    private void listNumCuentaOrigenValueChanged(javax.swing.event.ListSelectionEvent evt) {                                              
        if (!evt.getValueIsAdjusting()) {
            String seleccionLista = listNumCuentaOrigen.getSelectedValue();
            if (seleccionLista != null) {
                String cuentaSeleccionada = seleccionLista.split(" - ")[1];
                txtNumCuentaOrigen.setText(cuentaSeleccionada);
            }
        }
    }

    private void listNumCuentaDestinoValueChanged(javax.swing.event.ListSelectionEvent evt) {                                     
        if (!evt.getValueIsAdjusting()) {
            String seleccionLista = listNumCuentaDestino.getSelectedValue();
            if (seleccionLista != null) {
                String cuentaSeleccionado = seleccionLista.split(" - ")[1];
                txtNumCuentaDestino.setText(cuentaSeleccionado);
            }
        }
    }

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if (!formulariosLlenos()) return;
        if (!formulariosValidos()) return;

        long numCuentaOrigen = Long.parseLong(txtNumCuentaOrigen.getText().trim());
        double monto = Double.parseDouble(txtMonto.getText().trim());
        long numCuentaDestino = Long.parseLong(txtNumCuentaDestino.getText().trim());
        Moneda monedaOperacion = Moneda.values()[boxTipoMoneda.getSelectedIndex() - 1];
        int DNI = Integer.parseInt(txtDNI.getText().trim());
        
        mo.transferir(numCuentaOrigen, numCuentaDestino, monto, monedaOperacion, DNI);
        
        limpiarFormularios();
        operacionPanel.getDashboard().actualizarDatosDelSistema();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JComboBox<String> boxTipoMoneda;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDeposito;
    private javax.swing.JLabel labelMoneda;
    private javax.swing.JLabel labelMonto;
    private javax.swing.JLabel labelNumCuentaDestino;
    private javax.swing.JLabel labelNumCuentaOrigen;
    private javax.swing.JLabel labelTipoCuenta1;
    private javax.swing.JLabel labelTipoCuenta2;
    private javax.swing.JList<String> listNumCuentaDestino;
    private javax.swing.JList<String> listNumCuentaOrigen;
    private javax.swing.JPanel panelTitulos;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNumCuentaDestino;
    private javax.swing.JTextField txtNumCuentaOrigen;
    // End of variables declaration//GEN-END:variables
}
