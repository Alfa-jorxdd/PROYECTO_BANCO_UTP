package org.banco.vistas;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.banco.logica.mantenimiento.MantenimientoOperacion;
import org.banco.modelos.Banco;
import org.banco.enums.Moneda;

public class Operacion_DepositoPanel extends javax.swing.JPanel {

    private final DefaultListModel<String> modeloResultadosCuenta = new DefaultListModel<>();
    private final DefaultListModel<String> modeloResultadosDNI = new DefaultListModel<>();

    private final MantenimientoOperacion mo;

    public Operacion_DepositoPanel() {
        initComponents();
        InitStyles();

        this.mo = new MantenimientoOperacion();

        listNumeroCuenta.setModel(modeloResultadosCuenta);
        listDNI.setModel(modeloResultadosDNI);

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

        txtDNI.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                mo.filtrarModelDNI(txtDNI.getText(), modeloResultadosDNI);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mo.filtrarModelDNI(txtDNI.getText(), modeloResultadosDNI);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

    }
    private void InitStyles() {
        labelDeposito.putClientProperty("FlatLaf.styleClass", "h0");
    }

    private boolean formulariosLlenos() {
        boolean hayFormulariosVacios = txtNumeroCuenta.getText().trim().isEmpty()
                || txtDNI.getText().trim().isEmpty()
                || txtMonto.getText().trim().isEmpty()
                || boxTipoMoneda.getSelectedIndex() == 0;
        if (hayFormulariosVacios) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean formulariosValidos() {
        if (!txtNumeroCuenta.getText().matches("^[0-9]{10}$")
                || !txtDNI.getText().matches("^[0-9]{8}$")) {
            JOptionPane.showMessageDialog(null, "Números de cuenta o DNI inválidos", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!txtMonto.getText().trim().matches("^[0-9]+([.][0-9]{1,2})?$")
                || Double.parseDouble(txtMonto.getText().trim()) <= 0) {
            JOptionPane.showMessageDialog(null, "Monto inválido. Solo debe contener números positivos y máximo 2 dígitos decimales", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void limpiarFormularios() {
        txtNumeroCuenta.setText("");
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
        btnDepositar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtNumeroCuenta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listNumeroCuenta = new javax.swing.JList<>();
        txtMonto = new javax.swing.JTextField();
        labelTipoCuenta = new javax.swing.JLabel();
        labelNumeroCuenta = new javax.swing.JLabel();
        labelMonto = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labelDNI = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listDNI = new javax.swing.JList<>();
        boxTipoMoneda = new javax.swing.JComboBox<>();
        labelMoneda = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(518, 396));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDeposito.setText("Depósito");

        jLabel4.setText("Agrega fondos a una cuenta Bancaria");

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

        btnDepositar.setText("Confirmar depósito");
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
        jPanel3.add(txtNumeroCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 220, 30));

        listNumeroCuenta.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listNumeroCuentaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listNumeroCuenta);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 220, 37));
        jPanel3.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 172, 220, 30));
        jPanel3.add(labelTipoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 22, 110, 20));

        labelNumeroCuenta.setText("Número de cuenta:");
        jPanel3.add(labelNumeroCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 194, 24));

        labelMonto.setText("Monto");
        jPanel3.add(labelMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 70, -1));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 253, 230));

        labelDNI.setText("DNI:");

        listDNI.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listDNIValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listDNI);

        boxTipoMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NINGUNA", "SOL", "DOLAR" }));

        labelMoneda.setText("Moneda:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtDNI, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                        .addComponent(labelDNI)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(labelMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(labelDNI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(labelMoneda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        bg.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 90, 250, 230));

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

    private void listDNIValueChanged(javax.swing.event.ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            String seleccionLista = listDNI.getSelectedValue();
            if (seleccionLista != null) {
                String dniSeleccionado = seleccionLista.split(" - ")[1];
                txtDNI.setText(dniSeleccionado);
            }
        }
    }

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {
        if (!formulariosLlenos()) {
            return;
        }
        if (!formulariosValidos()) {
            return;
        }

        long numeroCuenta = Long.parseLong(txtNumeroCuenta.getText().trim());
        double monto = Double.parseDouble(txtMonto.getText().trim());
        int DNI = Integer.parseInt(txtDNI.getText().trim());
        Moneda monedaOperacion = Moneda.values()[boxTipoMoneda.getSelectedIndex() - 1];

        mo.depositar(numeroCuenta, monto, monedaOperacion, DNI);

        limpiarFormularios();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JComboBox<String> boxTipoMoneda;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDNI;
    private javax.swing.JLabel labelDeposito;
    private javax.swing.JLabel labelMoneda;
    private javax.swing.JLabel labelMonto;
    private javax.swing.JLabel labelNumeroCuenta;
    private javax.swing.JLabel labelTipoCuenta;
    private javax.swing.JList<String> listDNI;
    private javax.swing.JList<String> listNumeroCuenta;
    private javax.swing.JPanel panelTitulos;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNumeroCuenta;
    // End of variables declaration//GEN-END:variables
}
