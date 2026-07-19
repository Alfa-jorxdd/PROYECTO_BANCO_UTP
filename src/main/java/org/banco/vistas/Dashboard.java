package org.banco.vistas;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.banco.config.ConexionSQLServer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

public final class Dashboard extends javax.swing.JFrame {

    private final TopBar topBar;

    private final Principal principal;
    private final GestionClientesPanel clientes;
    private final GestionCuentasPanel cuentas;
    private final OperacionPanel operaciones;

    public Dashboard() {
        initComponents();
        initStyles();

        topBar = new TopBar(this);

        int widthPanel = 1120;
        int heightPanel = 30;
        bg.add(topBar, new AbsoluteConstraints(0, 0, widthPanel, heightPanel));
        bg.setComponentZOrder(topBar, 1);

        this.setVisible(true);
        this.setLocationRelativeTo(null);

        mostrarPanel(new Principal(), "Inicio");
        mostrarFecha();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ConexionSQLServer.getInstancia().cerrarConexion();
        }));

        principal = new Principal();
        clientes = new GestionClientesPanel(this);
        cuentas = new GestionCuentasPanel(this);
        operaciones = new OperacionPanel(this);
    }

    private void mostrarPanel(JPanel panel, String titulo) {
        panel.setSize(799, 435);
        panel.setLocation(0, 0);

        panelMain.removeAll();
        panelMain.add(panel, BorderLayout.CENTER);
        panelMain.revalidate();
        panelMain.repaint();

        txtTitulo.setText(titulo);
    }

    private void initStyles() {
        panelOptions.putClientProperty(FlatClientProperties.STYLE_CLASS, "arc: 50");
        panelDashboard.putClientProperty(FlatClientProperties.STYLE_CLASS, "arc: 50");
        txtBienvenida.putClientProperty(FlatClientProperties.STYLE_CLASS, "h0");
        txtTitulo.putClientProperty(FlatClientProperties.STYLE_CLASS, "h0");
        txtFecha.putClientProperty(FlatClientProperties.STYLE_CLASS, "h3");
        
        FlatSVGIcon pricipalIcon = new FlatSVGIcon("svg/dashboard.svg", 16, 16);
        FlatSVGIcon clienteIcon = new FlatSVGIcon("svg/client.svg", 16, 16);
        FlatSVGIcon cuentaIcon = new FlatSVGIcon("svg/account.svg", 16, 16);
        FlatSVGIcon operacionIcon = new FlatSVGIcon("svg/card.svg", 16, 16);

        Color colorNaranja = UIManager.getColor("Component.focusColor");
        
        pricipalIcon.setColorFilter(new FlatSVGIcon.ColorFilter(colorOriginal -> colorNaranja));
        clienteIcon.setColorFilter(new FlatSVGIcon.ColorFilter(colorOriginal -> colorNaranja));
        cuentaIcon.setColorFilter(new FlatSVGIcon.ColorFilter(colorOriginal -> colorNaranja));
        operacionIcon.setColorFilter(new FlatSVGIcon.ColorFilter(colorOriginal -> colorNaranja));
        
        //Botones
        btnInicio.setIcon(pricipalIcon);
        btnGestionClientes.setIcon(clienteIcon);
        btnGestionCuentas.setIcon(cuentaIcon);
        btnOperaciones.setIcon(operacionIcon);
    }

    private void mostrarFecha() {
        LocalDate fechaDeHoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("es", "PE"));
        txtFecha.setText(formato.format(fechaDeHoy));
    }

    public void irADeposito(String numeroCuenta) {
        mostrarPanel(operaciones, "Operaciones");
        operaciones.irDeposito(numeroCuenta);
    }
    
    public void irRetiro(String numeroCuenta) {
        mostrarPanel(operaciones, "Operaciones");
        operaciones.irRetiro(numeroCuenta);
    }
    
    public void irConsulta(String numeroCuenta) {
        mostrarPanel(operaciones, "Operaciones");
        operaciones.irConsulta(numeroCuenta);
    }
    
    public void actualizarDatosDelSistema(){
        cuentas.cargarListaCuentas();
        principal.cargarDatosWidgets();
        operaciones.getRegistroOperacionesPanel().cargarOperacionesTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        bg = new javax.swing.JPanel();
        panelDashboard = new javax.swing.JPanel();
        panelMain = new javax.swing.JPanel();
        panelTitulos = new javax.swing.JPanel();
        txtBienvenida = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JLabel();
        panelOptions = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnGestionClientes = new javax.swing.JButton();
        btnGestionCuentas = new javax.swing.JButton();
        panelLogo = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        btnOperaciones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 450));

        bg.setBackground(new java.awt.Color(228, 137, 0));
        bg.setPreferredSize(new java.awt.Dimension(800, 450));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDashboard.setBackground(new java.awt.Color(221, 221, 221));

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 799, Short.MAX_VALUE)
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );

        txtBienvenida.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtBienvenida.setText("¡Bienvenido!    > ");

        txtFecha.setText("30 abr. 2026");

        txtTitulo.setText("Si");

        javax.swing.GroupLayout panelTitulosLayout = new javax.swing.GroupLayout(panelTitulos);
        panelTitulos.setLayout(panelTitulosLayout);
        panelTitulosLayout.setHorizontalGroup(
            panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulosLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(txtBienvenida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTitulosLayout.setVerticalGroup(
            panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitulosLayout.createSequentialGroup()
                .addGroup(panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTitulosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTitulosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDashboardLayout = new javax.swing.GroupLayout(panelDashboard);
        panelDashboard.setLayout(panelDashboardLayout);
        panelDashboardLayout.setHorizontalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDashboardLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addComponent(panelTitulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelDashboardLayout.setVerticalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDashboardLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelTitulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        bg.add(panelDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 860, 630));

        panelOptions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInicio.setText("Inicio");
        btnInicio.setToolTipText("");
        btnInicio.setBorder(null);
        btnInicio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        panelOptions.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, 40));

        btnGestionClientes.setText("Gestionar Clientes");
        btnGestionClientes.setBorder(null);
        btnGestionClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionClientesActionPerformed(evt);
            }
        });
        panelOptions.add(btnGestionClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 210, 40));

        btnGestionCuentas.setText("Gestionar Cuentas");
        btnGestionCuentas.setBorder(null);
        btnGestionCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionCuentasActionPerformed(evt);
            }
        });
        panelOptions.add(btnGestionCuentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 210, 40));

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LogoBankIntermedio.png"))); // NOI18N

        javax.swing.GroupLayout panelLogoLayout = new javax.swing.GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        panelOptions.add(panelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 180, 180));

        btnOperaciones.setText("Operaciones");
        btnOperaciones.setBorder(null);
        btnOperaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperacionesActionPerformed(evt);
            }
        });
        panelOptions.add(btnOperaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 210, 40));

        bg.add(panelOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 230, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        mostrarPanel(principal, "Inicio");
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnGestionClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionClientesActionPerformed
        mostrarPanel(clientes, "Gestión de Clientes");
    }//GEN-LAST:event_btnGestionClientesActionPerformed

    private void btnGestionCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionCuentasActionPerformed
        mostrarPanel(cuentas, "Gestión de Cuentas");
    }//GEN-LAST:event_btnGestionCuentasActionPerformed

    private void btnOperacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperacionesActionPerformed
        mostrarPanel(operaciones, "Operaciones");
    }//GEN-LAST:event_btnOperacionesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnGestionClientes;
    private javax.swing.JButton btnGestionCuentas;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnOperaciones;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelOptions;
    private javax.swing.JPanel panelTitulos;
    private javax.swing.JLabel txtBienvenida;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}
