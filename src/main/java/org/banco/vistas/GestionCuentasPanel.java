package org.banco.vistas;

import java.awt.Window;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;

import org.banco.logica.mantenimiento.MantenimientoCuenta;
import org.banco.modelos.Cliente;
import org.banco.enums.EstadoCuenta;
import org.banco.enums.Formato;
import org.banco.enums.Moneda;
import org.banco.enums.TipoCuenta;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GestionCuentasPanel extends javax.swing.JPanel {

    private final MantenimientoCuenta mcu;
    private final DefaultListModel<String> modeloTitulares = new DefaultListModel<>();
    private final DefaultTableModel dtm;

    private boolean ascendente = true;

    private boolean habilitarActualizar = false;

    public GestionCuentasPanel() {
        initComponents();
        initStyles();

        dtm = (DefaultTableModel) tCuentas.getModel();

        mcu = new MantenimientoCuenta();

        listarCuentasTabla();
        btnAscDesc.setText("Desc");

        txtBuscarTitular.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarModel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarModel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        listTitulares.setModel(modeloTitulares);

        modeloTitulares.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                verificarEsMancomunada();
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                verificarEsMancomunada();
            }

            @Override
            public void contentsChanged(ListDataEvent e) {

            }
        });

        txtBuscarCuenta.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listarCuentasTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listarCuentasTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void initStyles() {
        tCuentas.setShowVerticalLines(true);
    }

    //Desactiva el JComboBox de TipoCuenta si hay más de un elemento en el modelTitulares
    //También verifica que, si el JComboBox ya ha estado desahibitado, entonces que no lo vuelva a activar
    private void verificarEsMancomunada() {
        if (modeloTitulares.size() > 1) {
            boxTpoCuenta.setSelectedIndex(3);
            boxTpoCuenta.setEnabled(false);
        } else {
            if (habilitarActualizar) {
                boxTpoCuenta.setSelectedIndex(1);
                boxTpoCuenta.setEnabled(false);
            } else {
                boxTpoCuenta.setEnabled(true);
                boxTpoCuenta.setSelectedIndex(0);
            }
        }
    }

    //Encuentra coincidencias con los elementos del modeloLista
    private void filtrarModel() {
        String texto = txtBuscarTitular.getText().trim();
        DefaultListModel<String> resultados = mcu.filtrarTitulares(texto);
        listResultados.setModel(resultados);
    }

    //Devuelte true si todos los campos no están vacíos
    private boolean camposValidos() {
        boolean esValido = modeloTitulares.getSize() != 0
                && boxTpoCuenta.getSelectedIndex() != 0
                && boxEstado.getSelectedIndex() != 0
                && boxMoneda.getSelectedIndex() != 0;

        if (esValido) {
            return esValido;
        } else {
            JOptionPane.showMessageDialog(null, "Campos inválidos. Por favor, llene todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
            return esValido;
        }
    }

    //Llena toda la tabla de cuentas llamando al metodo listar de la clase Mantenimiento Cuenta
    private void listarCuentasTabla() {
        mcu.listar(dtm, ascendente, boxOrdenarPor.getSelectedIndex(), boxBuscarPor.getSelectedIndex(), txtBuscarCuenta.getText());
    }

    //Limpia todos los campos del formulario
    private void limpiarFormulario() {
        modeloTitulares.removeAllElements();
        boxTpoCuenta.setSelectedIndex(0);
        boxEstado.setSelectedIndex(0);
        boxMoneda.setSelectedIndex(0);
        txtBuscarTitular.setText("");
        btnAgregar_Actualizar.setText("Agregar");
    }

    //Agrega o actualiza la lista de cuenta y cliente_cuenta según los campos en el formulario
    private void enviarDatosMC() {
        EstadoCuenta estado = EstadoCuenta.values()[boxEstado.getSelectedIndex() - 1];
        Moneda moneda = Moneda.values()[boxMoneda.getSelectedIndex() - 1];

        mcu.setDatosCuenta(TipoCuenta.values()[boxTpoCuenta.getSelectedIndex() - 1], estado, moneda);
        mcu.recibirIds(modeloTitulares);
    }

    private void agregar() {
        enviarDatosMC();
        mcu.agregar();
    }

    private void actualizar() {
        mcu.setIdCuenta((int) dtm.getValueAt(tCuentas.getSelectedRow(), 0));
        enviarDatosMC();
        mcu.actualizar();
    }

    private void eliminar() {
        if (tCuentas.getSelectedRow() != -1) {

            int filaSeleccionada = tCuentas.getSelectedRow();
            int idCuenta = (int) dtm.getValueAt(filaSeleccionada, 0);

            mcu.setIdCuenta(idCuenta);
            mcu.eliminar();

            listarCuentasTabla();

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, elija una cuenta primero", "Error al eliminar", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCuentas = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnAgregar_Actualizar = new javax.swing.JButton();
        boxTpoCuenta = new javax.swing.JComboBox<>();
        boxEstado = new javax.swing.JComboBox<>();
        btnReporte = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        boxMoneda = new javax.swing.JComboBox<>();
        txtBuscarTitular = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listResultados = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listTitulares = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnEliminarTitular = new javax.swing.JButton();
        txtBuscarCuenta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        boxBuscarPor = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        boxOrdenarPor = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnAscDesc = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(799, 435));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Tipo Cuenta:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 80, 20));

        jLabel2.setText("Estado:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, -1, 20));

        tCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo ", "Estado", "N° cuenta", "Titular", "Saldo", "Moneda"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tCuentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tCuentas);
        if (tCuentas.getColumnModel().getColumnCount() > 0) {
            tCuentas.getColumnModel().getColumn(0).setResizable(false);
            tCuentas.getColumnModel().getColumn(0).setPreferredWidth(2);
            tCuentas.getColumnModel().getColumn(1).setResizable(false);
            tCuentas.getColumnModel().getColumn(1).setPreferredWidth(50);
            tCuentas.getColumnModel().getColumn(2).setPreferredWidth(30);
            tCuentas.getColumnModel().getColumn(3).setResizable(false);
            tCuentas.getColumnModel().getColumn(3).setPreferredWidth(10);
            tCuentas.getColumnModel().getColumn(4).setPreferredWidth(200);
            tCuentas.getColumnModel().getColumn(5).setResizable(false);
            tCuentas.getColumnModel().getColumn(6).setResizable(false);
            tCuentas.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 130, 780, 240));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 90, 30));

        btnAgregar_Actualizar.setText("Agregar");
        btnAgregar_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar_ActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 90, 30));

        boxTpoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Niguno", "Ahorro", "Corriente", "Mancomunada" }));
        jPanel1.add(boxTpoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 100, 20));

        boxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "ACTIVA", "BLOQUEADA" }));
        jPanel1.add(boxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        jPanel1.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 90, 30));

        jLabel4.setText("Moneda:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, -1, -1));

        boxMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "SOL", "DOLAR" }));
        jPanel1.add(boxMoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 100, -1));
        jPanel1.add(txtBuscarTitular, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 190, -1));

        listResultados.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listResultadosValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listResultados);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, 60));

        jScrollPane3.setViewportView(listTitulares);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 180, 60));

        jLabel5.setText("Agregar Titular");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel6.setText("Titulares agregados");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        btnEliminarTitular.setText("Eliminar titular");
        btnEliminarTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTitularActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarTitular, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 110, -1));
        jPanel1.add(txtBuscarCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 180, -1));

        jLabel3.setText("Buscar cuenta:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        boxBuscarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Tipo", "Estado", "N°cuenta", "Saldo", "Moneda" }));
        jPanel1.add(boxBuscarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 110, -1));

        jLabel7.setText("Buscar por:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, -1, -1));

        boxOrdenarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Tipo", "Estado", "N°cuenta", "Saldo", "Moneda" }));
        boxOrdenarPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxOrdenarPorActionPerformed(evt);
            }
        });
        jPanel1.add(boxOrdenarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 110, -1));

        jLabel8.setText("Ordenar por:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        btnAscDesc.setText("Asc");
        btnAscDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscDescActionPerformed(evt);
            }
        });
        jPanel1.add(btnAscDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, -1));

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //======================EVENTOS==========================
    //Elimina una cuenta seleccionada tanto de la lista de cuentas como de la tabla
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    //Agregar el elemento seleccionado de la listaResultados a la listaTiturares
    private void listResultadosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listResultadosValueChanged

        if (!evt.getValueIsAdjusting()) {
            String seleccionLista = listResultados.getSelectedValue();
            if (seleccionLista != null) {
                seleccionLista = seleccionLista.trim().toLowerCase();
                for (int i = 0; i < modeloTitulares.size(); i++) {
                    String elementoTitular = modeloTitulares.getElementAt(i).trim().toLowerCase();
                    if (seleccionLista.equals(elementoTitular)) {
                        JOptionPane.showMessageDialog(null, "No se puede repetir el mismo titular", "ERROR", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                modeloTitulares.addElement(listResultados.getSelectedValue().trim());
            }
        }
    }//GEN-LAST:event_listResultadosValueChanged

    //Eliminar el elemento seleccionado de la listaTitulares
    private void btnEliminarTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTitularActionPerformed
        int indice = listTitulares.getSelectedIndex();
        if (indice != -1) {
            if (boxTpoCuenta.getSelectedIndex() != 3){
                modeloTitulares.removeElementAt(indice);
            }
        }
    }//GEN-LAST:event_btnEliminarTitularActionPerformed

    //Agrega o actualiza tanto la lista de cuentas como la tabla
    private void btnAgregar_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar_ActualizarActionPerformed
        if (!camposValidos()) {
            return;
        }
        if (btnAgregar_Actualizar.getText().equals("Agregar")) {
            agregar();
        } else {
            actualizar();
        }

        txtBuscarTitular.setEnabled(true);
        limpiarFormulario();
        listarCuentasTabla();
    }//GEN-LAST:event_btnAgregar_ActualizarActionPerformed

    //Agrega los datos de una cuenta seleccionada a los campos existentes para su posterior actualización
    private void tCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tCuentasMouseClicked
        if (evt.getClickCount() == 2) {
            modeloTitulares.removeAllElements();

            int idCuenta = (int) dtm.getValueAt(tCuentas.getSelectedRow(), 0);
            List<Cliente> clientes = mcu.buscarTitularesCuenta(idCuenta);
            for (Cliente cliente : clientes) {
                modeloTitulares.addElement(cliente.getIdCliente() + ". " + cliente.getNombres() + " " + cliente.getApellidos());
            }

            TipoCuenta tipoCuenta = TipoCuenta.valueOf(dtm.getValueAt(tCuentas.getSelectedRow(), 1).toString());
            int indexTipoCuenta = tipoCuenta.ordinal() + 1;
            boxTpoCuenta.setSelectedIndex(indexTipoCuenta);
            boxTpoCuenta.setEnabled(false);

            EstadoCuenta estado = EstadoCuenta.valueOf(dtm.getValueAt(tCuentas.getSelectedRow(), 2).toString());
            int indexEstado = estado.ordinal() + 1;
            boxEstado.setSelectedIndex(indexEstado);

            Moneda moneda = Moneda.valueOf(dtm.getValueAt(tCuentas.getSelectedRow(), 6).toString());
            int indexMoneda = moneda.ordinal() + 1;
            boxMoneda.setSelectedIndex(indexMoneda);

            if (tipoCuenta != TipoCuenta.MANCOMUNADA){
                txtBuscarTitular.setEnabled(false);
            }

            btnAgregar_Actualizar.setText("Actualizar");
        }
    }//GEN-LAST:event_tCuentasMouseClicked

    private void btnAscDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscDescActionPerformed
        ascendente = !ascendente;
        btnAscDesc.setText(ascendente ? "Asc" : "Desc");
        listarCuentasTabla();
    }//GEN-LAST:event_btnAscDescActionPerformed

    private void boxOrdenarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxOrdenarPorActionPerformed
        listarCuentasTabla();
    }//GEN-LAST:event_boxOrdenarPorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpiarFormulario();
        boxTpoCuenta.setEnabled(true);
        boxTpoCuenta.setSelectedIndex(0);
        txtBuscarTitular.setEnabled(true);
        txtBuscarCuenta.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        Window ventanaPadre = SwingUtilities.getWindowAncestor(this);
        ReporteDialog reporteDialog = new ReporteDialog((JFrame) ventanaPadre, true);
        
        if (reporteDialog.isConfirmado()) {
            String nombre = reporteDialog.getNombreArchivo();
            Formato formato = reporteDialog.getFormato();
            mcu.generarReporte(
                    nombre
                    , formato
                    , ascendente
                    , boxOrdenarPor.getSelectedIndex()
                    , boxBuscarPor.getSelectedIndex()
                    , txtBuscarCuenta.getText()
            );
        }
    }//GEN-LAST:event_btnReporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxBuscarPor;
    private javax.swing.JComboBox<String> boxEstado;
    private javax.swing.JComboBox<String> boxMoneda;
    private javax.swing.JComboBox<String> boxOrdenarPor;
    private javax.swing.JComboBox<String> boxTpoCuenta;
    private javax.swing.JButton btnAgregar_Actualizar;
    private javax.swing.JButton btnAscDesc;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarTitular;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listResultados;
    private javax.swing.JList<String> listTitulares;
    private javax.swing.JTable tCuentas;
    private javax.swing.JTextField txtBuscarCuenta;
    private javax.swing.JTextField txtBuscarTitular;
    // End of variables declaration//GEN-END:variables
}
