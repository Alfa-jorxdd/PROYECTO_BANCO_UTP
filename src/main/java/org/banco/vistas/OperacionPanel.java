package org.banco.vistas;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class OperacionPanel extends javax.swing.JPanel {
    
    private Dashboard dashboard;

    private final Operacion_ConsultaPanel consultaPanel;
    private final Operacion_DepositoPanel depositoPanel;
    private final Operacion_RetiroPanel retiroPanel;
    private final Operacion_TransferenciaPanel transferenciaPanel;
    private final Operacion_RegistroOperacionesPanel registroOperacionesPanel;
    
    public OperacionPanel(Dashboard dashboard) {
        initComponents();
        
        this.dashboard = dashboard;
        consultaPanel = new Operacion_ConsultaPanel(this);
        depositoPanel = new Operacion_DepositoPanel(this);
        retiroPanel = new Operacion_RetiroPanel(this);
        transferenciaPanel = new Operacion_TransferenciaPanel(this);
        registroOperacionesPanel = new Operacion_RegistroOperacionesPanel();
        
        MostarPanel(depositoPanel);
    }

    public void initStyles(){

    }

    private void MostarPanel(JPanel panel){
        panel.setSize(518, 396);
        panel.setLocation(0,0);

        panelMain.removeAll();
        panelMain.add(panel, BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();
    }
    
    public void irDeposito(String numeroCuenta){
        depositoPanel.ingresarNumeroCuenta(numeroCuenta);
        MostarPanel(depositoPanel);
    }
    
    public void irRetiro(String numeroCuenta){
        retiroPanel.ingresarNumeroCuenta(numeroCuenta);
        MostarPanel(retiroPanel);
    }
    
    public void irConsulta(String numeroCuenta){
        consultaPanel.ingresarNumeroCuenta(numeroCuenta);
        MostarPanel(consultaPanel);
    }
    
    public void irTransferencia(String numeroCuenta){
        MostarPanel(transferenciaPanel);
    }
    
    public void irOperaciones(String numeroCuenta){
        MostarPanel(registroOperacionesPanel);
    }

    public Dashboard getDashboard() {
        return dashboard;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnDeposito = new javax.swing.JPanel();
        btnDepositar = new javax.swing.JButton();
        btnRetiro = new javax.swing.JButton();
        btnTransferencia = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnRegistroOperaciones = new javax.swing.JButton();
        panelMain = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(799, 435));

        btnDeposito.setBackground(new java.awt.Color(41, 46, 51));

        btnDepositar.setText("Depósito");
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        btnRetiro.setText("Retiro");
        btnRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetiroActionPerformed(evt);
            }
        });

        btnTransferencia.setText("Transferencia");
        btnTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferenciaActionPerformed(evt);
            }
        });

        btnConsulta.setText("Consulta");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        btnRegistroOperaciones.setText("Registro de opraciones");
        btnRegistroOperaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroOperacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnDepositoLayout = new javax.swing.GroupLayout(btnDeposito);
        btnDeposito.setLayout(btnDepositoLayout);
        btnDepositoLayout.setHorizontalGroup(
            btnDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDepositoLayout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addGroup(btnDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTransferencia, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(btnRetiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDepositar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistroOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        btnDepositoLayout.setVerticalGroup(
            btnDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDepositoLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnDepositar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistroOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMain.setBackground(new java.awt.Color(68, 72, 86));

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addComponent(btnDeposito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferenciaActionPerformed
        MostarPanel(transferenciaPanel);
    }//GEN-LAST:event_btnTransferenciaActionPerformed

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        MostarPanel(depositoPanel);
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void btnRetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiroActionPerformed
        MostarPanel(retiroPanel);
    }//GEN-LAST:event_btnRetiroActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        MostarPanel(consultaPanel);
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnRegistroOperacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroOperacionesActionPerformed
        MostarPanel(registroOperacionesPanel);
    }//GEN-LAST:event_btnRegistroOperacionesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JPanel btnDeposito;
    private javax.swing.JButton btnRegistroOperaciones;
    private javax.swing.JButton btnRetiro;
    private javax.swing.JButton btnTransferencia;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelMain;
    // End of variables declaration//GEN-END:variables
}
