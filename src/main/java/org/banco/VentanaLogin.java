package org.banco;

import java.awt.Color;

public class VentanaLogin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaLogin.class.getName());

    private int xMouse, yMouse;
    
    public VentanaLogin() {
        setUndecorated(true);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        panelLogo = new javax.swing.JPanel();
        panelLogin = new javax.swing.JPanel();
        panelBara = new javax.swing.JPanel();
        panelBntExit = new javax.swing.JPanel();
        labelBtnExit = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();
        labelUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        separatorUser = new javax.swing.JSeparator();
        labelPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        separatorPassword = new javax.swing.JSeparator();
        panelBtnIngresar = new javax.swing.JPanel();
        labelBtnIngresar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLogo.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout panelLogoLayout = new javax.swing.GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        bg.add(panelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 580));

        panelLogin.setBackground(new java.awt.Color(255, 255, 255));

        panelBara.setBackground(new java.awt.Color(255, 255, 255));
        panelBara.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelBaraMouseDragged(evt);
            }
        });
        panelBara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBaraMousePressed(evt);
            }
        });

        panelBntExit.setBackground(new java.awt.Color(255, 255, 255));
        panelBntExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBntExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBntExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBntExitMouseExited(evt);
            }
        });

        labelBtnExit.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        labelBtnExit.setForeground(new java.awt.Color(0, 0, 0));
        labelBtnExit.setText("      X");
        labelBtnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelBntExitLayout = new javax.swing.GroupLayout(panelBntExit);
        panelBntExit.setLayout(panelBntExitLayout);
        panelBntExitLayout.setHorizontalGroup(
            panelBntExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBntExitLayout.createSequentialGroup()
                .addComponent(labelBtnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBntExitLayout.setVerticalGroup(
            panelBntExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBntExitLayout.createSequentialGroup()
                .addComponent(labelBtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBaraLayout = new javax.swing.GroupLayout(panelBara);
        panelBara.setLayout(panelBaraLayout);
        panelBaraLayout.setHorizontalGroup(
            panelBaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBaraLayout.createSequentialGroup()
                .addGap(0, 894, Short.MAX_VALUE)
                .addComponent(panelBntExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBaraLayout.setVerticalGroup(
            panelBaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
        );

        labelTitle.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(0, 0, 0));
        labelTitle.setText("INICIAR SESIÓN");

        labelUser.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        labelUser.setForeground(new java.awt.Color(0, 0, 0));
        labelUser.setText("USUARIO");

        txtUser.setBackground(new java.awt.Color(255, 255, 255));
        txtUser.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        txtUser.setText("Ingrese su nombre de usuario");
        txtUser.setBorder(null);

        labelPassword.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        labelPassword.setForeground(new java.awt.Color(0, 0, 0));
        labelPassword.setText("CONTRASEÑA");

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        txtPassword.setText("jPasswordField1");
        txtPassword.setBorder(null);

        panelBtnIngresar.setBackground(new java.awt.Color(51, 51, 255));

        labelBtnIngresar.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelBtnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        labelBtnIngresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBtnIngresar.setText("INGRESAR");
        labelBtnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelBtnIngresarLayout = new javax.swing.GroupLayout(panelBtnIngresar);
        panelBtnIngresar.setLayout(panelBtnIngresarLayout);
        panelBtnIngresarLayout.setHorizontalGroup(
            panelBtnIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelBtnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );
        panelBtnIngresarLayout.setVerticalGroup(
            panelBtnIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelBtnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addComponent(panelBara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBtnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(separatorPassword)
                        .addComponent(labelPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                        .addComponent(labelTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(separatorUser, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(15, 15, 15))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addComponent(panelBara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separatorUser, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separatorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(panelBtnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 199, Short.MAX_VALUE))
        );

        bg.add(panelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBaraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBaraMouseDragged
        this.setLocation(
                evt.getXOnScreen() - xMouse, 
                evt.getYOnScreen() - yMouse);
    }//GEN-LAST:event_panelBaraMouseDragged

    private void panelBntExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBntExitMouseEntered
        panelBntExit.setBackground(Color.red);
        labelBtnExit.setForeground(Color.white);
    }//GEN-LAST:event_panelBntExitMouseEntered

    private void panelBntExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBntExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_panelBntExitMouseClicked

    private void panelBntExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBntExitMouseExited
        panelBntExit.setBackground(Color.white);
        labelBtnExit.setForeground(Color.black);
    }//GEN-LAST:event_panelBntExitMouseExited

    private void panelBaraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBaraMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelBaraMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel labelBtnExit;
    private javax.swing.JLabel labelBtnIngresar;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelUser;
    private javax.swing.JPanel panelBara;
    private javax.swing.JPanel panelBntExit;
    private javax.swing.JPanel panelBtnIngresar;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JSeparator separatorPassword;
    private javax.swing.JSeparator separatorUser;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
