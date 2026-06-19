package org.banco.vistas;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.banco.logica.mantenimiento.MantenimientoOperacion;
import org.banco.modelos.Banco;

public class Operacion_ConsultaPanel extends javax.swing.JPanel {

    private Banco banco;
    private DefaultListModel<String> modeloListaCuenta = new DefaultListModel<>();
    private DefaultListModel<String> modeloResultadosCuenta = new DefaultListModel<>();
    
    private MantenimientoOperacion mo;

    public Operacion_ConsultaPanel(Banco banco) {
        initComponents();
        InitStyles();

        this.banco = banco;
        mo  = new MantenimientoOperacion(banco);
        
        listNumeroCuenta.setModel(modeloResultadosCuenta);

        mo.cargarModelosConClientes(modeloListaCuenta);

        txtNumeroCuenta.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                mo.filtrarModelCuenta(txtNumeroCuenta.getText(), modeloResultadosCuenta);
                mo.ponerTipoDeCuentaSiExiste(txtNumeroCuenta.getText(), labelTipoCuenta);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mo.filtrarModelCuenta(txtNumeroCuenta.getText(), modeloResultadosCuenta);
                mo.ponerTipoDeCuentaSiExiste(txtNumeroCuenta.getText(), labelTipoCuenta);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });   
    }

    private void InitStyles() {
        labelDeposito.putClientProperty("FlatLaf.styleClass", "h0");
    }
    
    private boolean numeroDeCuentaValido(){
        if (txtNumeroCuenta.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!txtNumeroCuenta.getText().trim().matches("^[0-9]{10}$")){
            JOptionPane.showMessageDialog(null, "Números de cuenta inválido", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        panelTitulos = new javax.swing.JPanel();
        labelDeposito = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnDepositar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtNumeroCuenta = new javax.swing.JTextField();
        labelNumeroCuenta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listNumeroCuenta = new javax.swing.JList<>();
        labelTipoCuenta = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(518, 396));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDeposito.setText("Consulta");

        jLabel4.setText("Visualiza cuanto saldo tienes en tu cuenta bancaria");

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

        btnDepositar.setText("Confirmar consulta");
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 318, 518, 80));

        jPanel3.setAlignmentX(0.01F);
        jPanel3.setAlignmentY(0.01F);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtNumeroCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 250, 30));

        labelNumeroCuenta.setText("Número de cuenta:");
        jPanel3.add(labelNumeroCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 120, 24));

        listNumeroCuenta.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listNumeroCuentaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listNumeroCuenta);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 250, 37));
        jPanel3.add(labelTipoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 45, 120, 20));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 520, 230));

        add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 518, 398));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents
    //======================EVENTOS==========================
    
    private void listNumeroCuentaValueChanged(javax.swing.event.ListSelectionEvent evt) {                                              
        if (!evt.getValueIsAdjusting()) {
            String seleccionLista = listNumeroCuenta.getSelectedValue();
            if (seleccionLista != null) {
                String cuentaSeleccionada = seleccionLista.split(" - ")[1];
                txtNumeroCuenta.setText(cuentaSeleccionada);
            }
        }
    }

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) { 
        if (!numeroDeCuentaValido()) return;
        long numeroCuenta = Long.parseLong(txtNumeroCuenta.getText().trim());
        mo.consultar(numeroCuenta);
        
        txtNumeroCuenta.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDeposito;
    private javax.swing.JLabel labelNumeroCuenta;
    private javax.swing.JLabel labelTipoCuenta;
    private javax.swing.JList<String> listNumeroCuenta;
    private javax.swing.JPanel panelTitulos;
    private javax.swing.JTextField txtNumeroCuenta;
    // End of variables declaration//GEN-END:variables
}
