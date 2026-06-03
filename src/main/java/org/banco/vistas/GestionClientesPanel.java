package org.banco.vistas;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.banco.mantenimiento.MantenimientoCliente;
import org.banco.modelos.Banco;

public final class GestionClientesPanel extends javax.swing.JPanel {

    private final DefaultTableModel dtm;
    private final Object[] obj = new Object[6];
    private final Banco banco;
    private final MantenimientoCliente mc;
    private boolean habilitarActualiar = false;

    public GestionClientesPanel(Banco banco) {
        initComponents();
        dtm = (DefaultTableModel) tClientes.getModel();
        this.banco = banco;

        mc = new MantenimientoCliente(this.banco);

        listarClientesTabla();
        initStyles();
    }
    //Inicializa todos los estilos Flatlaf
    private void initStyles() {
        tClientes.setShowVerticalLines(true);
    }
    //Llena toda la tabla de cuentas llamando al metodo listar de la clase Mantenimiento Cliente
    private void listarClientesTabla() {
        mc.listar(dtm, obj);
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
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualziar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnReporte = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();

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
        jScrollPane1.setViewportView(tClientes);
        if (tClientes.getColumnModel().getColumnCount() > 0) {
            tClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
            tClientes.getColumnModel().getColumn(1).setPreferredWidth(40);
            tClientes.getColumnModel().getColumn(2).setPreferredWidth(40);
            tClientes.getColumnModel().getColumn(3).setPreferredWidth(30);
            tClientes.getColumnModel().getColumn(4).setPreferredWidth(60);
            tClientes.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 140, 780, 280));
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 130, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 100, 30));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 100, 30));

        btnActualziar.setText("Actualizar");
        btnActualziar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualziarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualziar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 100, 30));

        jLabel3.setText("Teléfono:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 190, -1));

        btnReporte.setText("Reporte");
        jPanel1.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 90, 30));

        jLabel4.setText("Correo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 50, -1));
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 210, -1));
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 190, -1));

        jLabel5.setText("Apellidos:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 60, 20));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, -1, -1));

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
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (!txtNombre.getText().trim().isEmpty()
                && !txtApellidos.getText().trim().isEmpty()
                && !txtDNI.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty()
                && !txtCorreo.getText().trim().isEmpty()
                && !habilitarActualiar) {
            if (!txtNombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+") || !txtApellidos.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+") ) {
                JOptionPane.showMessageDialog(null, "Los nombres y apellidos solo puede contener letras", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!txtDNI.getText().matches("[0-9]{8}")) {
                JOptionPane.showMessageDialog(null, "El DNI solo puede contener 8 dígitos numéricos", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!txtTelefono.getText().matches("9[0-9]{8}")) {
                JOptionPane.showMessageDialog(null, "Número de teléfono inválido", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!txtCorreo.getText().matches("^[a-zA-Z0-9._%+\\-]+@(gmail|hotmail|yahoo|outlook)\\.com$")) {
                JOptionPane.showMessageDialog(null, "Correo electrónico inválido", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            mc.setDatosCliente(txtNombre.getText().trim(),
                    txtApellidos.getText().trim(),
                    Integer.parseInt(txtDNI.getText().trim()),
                    Integer.parseInt(txtTelefono.getText().trim()),
                    txtCorreo.getText().trim());
            mc.agregar();
            listarClientesTabla();

            txtNombre.setText("");
            txtApellidos.setText("");
            txtDNI.setText("");
            txtTelefono.setText("");
            txtCorreo.setText("");

            JOptionPane.showConfirmDialog(null, "Cliente agregado éxitosamente", "Cliente agregado", JOptionPane.DEFAULT_OPTION);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (tClientes.getSelectedRow() != -1) {
            int filaSeleccionada = tClientes.getSelectedRow();
            int idCliente = (int) tClientes.getValueAt(filaSeleccionada, 0);

            mc.setIdCliente(idCliente);
            mc.eliminar();

            dtm.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualziarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualziarActionPerformed
        if (habilitarActualiar) {

            mc.setIdCliente((int) dtm.getValueAt(tClientes.getSelectedRow(), 0));
            mc.setDatosCliente(txtNombre.getText().trim(),
                    txtApellidos.getText().trim(),
                    Integer.parseInt(txtDNI.getText().trim()),
                    Integer.parseInt(txtTelefono.getText().trim()),
                    txtCorreo.getText().trim());

            mc.actualizar();

            habilitarActualiar = false;

            txtNombre.setText("");
            txtApellidos.setText("");
            txtDNI.setText("");
            txtTelefono.setText("");
            txtCorreo.setText("");

            btnAgregar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnReporte.setEnabled(true);

            listarClientesTabla();

            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente");

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, edite un campo primero", "Error al editar", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnActualziarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        if (!(tClientes.getSelectedRow() == -1)) {

            habilitarActualiar = true;

            txtNombre.setText(dtm.getValueAt(tClientes.getSelectedRow(), 1).toString());
            txtApellidos.setText(dtm.getValueAt(tClientes.getSelectedRow(), 2).toString());
            txtDNI.setText(dtm.getValueAt(tClientes.getSelectedRow(), 3).toString());
            txtTelefono.setText(dtm.getValueAt(tClientes.getSelectedRow(), 4).toString());
            txtCorreo.setText(dtm.getValueAt(tClientes.getSelectedRow(), 5).toString());

            btnAgregar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnReporte.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila", "Error al actuakizar", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualziar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tClientes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    public JTable getTablaCliente() {
        return tClientes;
    }

}
