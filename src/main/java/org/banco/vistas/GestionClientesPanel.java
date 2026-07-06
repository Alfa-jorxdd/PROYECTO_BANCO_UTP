package org.banco.vistas;

import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.banco.enums.Formato;
import org.banco.logica.mantenimiento.MantenimientoCliente;

public final class GestionClientesPanel extends javax.swing.JPanel {

    private final DefaultTableModel dtm;
    private final MantenimientoCliente mc;
    private boolean ascendente = true;

    public GestionClientesPanel() {
        initComponents();
        dtm = (DefaultTableModel) tClientes.getModel();
        mc = new MantenimientoCliente();

        listarClientesTabla();
        initStyles();
        
        txtBsucarPor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listarClientesTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listarClientesTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
    }

    //Inicializa todos los estilos Flatlaf
    private void initStyles() {
        tClientes.setShowVerticalLines(true);
    }

    //Llena toda la tabla de cuentas llamando al metodo listar de la clase Mantenimiento Cliente
    private void listarClientesTabla() {
        mc.listar(dtm
                , ascendente
                , boxOrdenarPor.getSelectedIndex()
                , boxBuscarPor.getSelectedIndex()
                , txtBsucarPor.getText());
    }

    private boolean camposValidos() {
        boolean esValido = !txtNombre.getText().trim().isEmpty()
                && !txtApellidos.getText().trim().isEmpty()
                && !txtDNI.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty()
                && !txtCorreo.getText().trim().isEmpty();
        if (esValido) {
            return esValido;
        } else {
            JOptionPane.showMessageDialog(null, "Campos inválidos. Por favor, llene todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
            return esValido;
        }
    }

    private boolean formulariosValidos() {
        if (!txtNombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+") || !txtApellidos.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            JOptionPane.showMessageDialog(null, "Los nombres y apellidos solo puede contener letras", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!txtDNI.getText().matches("[0-9]{8}")) {
            JOptionPane.showMessageDialog(null, "El DNI solo puede contener 8 dígitos numéricos", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!txtTelefono.getText().matches("9[0-9]{8}")) {
            JOptionPane.showMessageDialog(null, "Número de teléfono inválido", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!txtCorreo.getText().matches("^[a-zA-Z0-9._%+\\-]+@(gmail|hotmail|yahoo|outlook)\\.com$")) {
            JOptionPane.showMessageDialog(null, "Correo electrónico inválido", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtDNI.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        btnAgregar_Actualizar.setText("Agregar");
    }

    private void enviarDatosMC() {
        mc.setDatosCliente(txtNombre.getText().trim(),
                txtApellidos.getText().trim(),
                Integer.parseInt(txtDNI.getText().trim()),
                Integer.parseInt(txtTelefono.getText().trim()),
                txtCorreo.getText().trim());
    }

    private void agregar() {
        if (camposValidos()) {
            if (!formulariosValidos()) {
                return;
            }
            enviarDatosMC();
            mc.agregar();
        }
    }

    private void actualizar() {
        if (camposValidos()) {
            if (!formulariosValidos()) {
                return;
            }

            mc.setIdCliente((int) dtm.getValueAt(tClientes.getSelectedRow(), 0));
            enviarDatosMC();
            mc.actualizar();
        }
    }

    private void eliminar() {
        if (tClientes.getSelectedRow() != -1) {
            int filaSeleccionada = tClientes.getSelectedRow();
            int idCliente = (int) tClientes.getValueAt(filaSeleccionada, 0);

            mc.setIdCliente(idCliente);
            mc.eliminar();

            dtm.removeRow(filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, elija un cliente primero", "Error al eliminar", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tClientes = new javax.swing.JTable();
        txtDNI = new javax.swing.JTextField();
        btnAgregar_Actualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnReporte = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBsucarPor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        boxBuscarPor = new javax.swing.JComboBox<>();
        boxOrdenarPor = new javax.swing.JComboBox<>();
        btnAscDesc = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(799, 435));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombres:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 20));

        jLabel2.setText("DNI: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 190, -1));

        tClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellidos", "DNI", "Telefono", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tClientes);
        if (tClientes.getColumnModel().getColumnCount() > 0) {
            tClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
            tClientes.getColumnModel().getColumn(1).setPreferredWidth(40);
            tClientes.getColumnModel().getColumn(2).setPreferredWidth(40);
            tClientes.getColumnModel().getColumn(3).setPreferredWidth(30);
            tClientes.getColumnModel().getColumn(4).setPreferredWidth(60);
            tClientes.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 140, 780, 230));
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 130, -1));

        btnAgregar_Actualizar.setText("Agregar");
        btnAgregar_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar_ActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 90, 30));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 90, 30));

        jLabel3.setText("Teléfono:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 190, -1));

        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        jPanel1.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 90, 30));

        jLabel4.setText("Correo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 50, -1));
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 210, -1));
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 190, -1));

        jLabel5.setText("Apellidos:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 60, 20));
        jPanel1.add(txtBsucarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 180, -1));

        jLabel6.setText("Buscar cliente:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        boxBuscarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombres", "Apellidos", "DNI", "Teléfono", "Correo" }));
        jPanel1.add(boxBuscarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 110, -1));

        boxOrdenarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombres", "Apellidos", "DNI", "Teléfono", "Correo" }));
        boxOrdenarPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxOrdenarPorActionPerformed(evt);
            }
        });
        jPanel1.add(boxOrdenarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 110, -1));

        btnAscDesc.setText("Desc");
        btnAscDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscDescActionPerformed(evt);
            }
        });
        jPanel1.add(btnAscDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, -1));

        jLabel7.setText("Buscar por:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, -1, -1));

        jLabel8.setText("Ordenar por:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //==============================EVENTOS===============================
    private void btnAgregar_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar_ActualizarActionPerformed
        if (!camposValidos() || !formulariosValidos()) {
            return;
        }
        
        if (btnAgregar_Actualizar.getText().equals("Agregar")) {
            agregar();
        } else {
            actualizar();
        }
        limpiarFormulario();
        listarClientesTabla();
    }//GEN-LAST:event_btnAgregar_ActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tClientesMouseClicked
        if (evt.getClickCount() == 2) {
            txtNombre.setText(dtm.getValueAt(tClientes.getSelectedRow(), 1).toString());
            txtApellidos.setText(dtm.getValueAt(tClientes.getSelectedRow(), 2).toString());
            txtDNI.setText(dtm.getValueAt(tClientes.getSelectedRow(), 3).toString());
            txtTelefono.setText(dtm.getValueAt(tClientes.getSelectedRow(), 4).toString());
            txtCorreo.setText(dtm.getValueAt(tClientes.getSelectedRow(), 5).toString());
            
            btnAgregar_Actualizar.setText("Actualizar");
        }
    }//GEN-LAST:event_tClientesMouseClicked

    private void btnAscDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscDescActionPerformed
        ascendente = !ascendente;
        btnAscDesc.setText(ascendente ? "Asc" : "Desc");
        listarClientesTabla();
    }//GEN-LAST:event_btnAscDescActionPerformed

    private void boxOrdenarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxOrdenarPorActionPerformed
        listarClientesTabla();
    }//GEN-LAST:event_boxOrdenarPorActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarFormulario();
        txtBsucarPor.requestFocus();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        Window ventanaPadre = SwingUtilities.getWindowAncestor(this);
        ReporteDialog reporteDialog = new ReporteDialog((JFrame) ventanaPadre, true);
        
        if (reporteDialog.isConfirmado()) {
            String nombre = reporteDialog.getNombreArchivo();
            Formato formato = reporteDialog.getFormato();
            mc.generarReporte(
                    nombre
                    , formato
                    , ascendente
                    , boxOrdenarPor.getSelectedIndex()
                    , boxBuscarPor.getSelectedIndex()
                    , txtBsucarPor.getText()
            );
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxBuscarPor;
    private javax.swing.JComboBox<String> boxOrdenarPor;
    private javax.swing.JButton btnAgregar_Actualizar;
    private javax.swing.JButton btnAscDesc;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tClientes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBsucarPor;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    public JTable getTablaCliente() {
        return tClientes;
    }
}
