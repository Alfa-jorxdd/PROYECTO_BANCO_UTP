package org.banco.vistas;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;
import org.banco.mantenimiento.MantenimientoCuenta;
import org.banco.modelos.Banco;
import org.banco.modelos.Cliente;
import org.banco.modelos.enums.EstadoCuenta;
import org.banco.modelos.enums.Moneda;
import org.banco.modelos.enums.TipoCuenta;

public class GestionCuentasPanel extends javax.swing.JPanel {

    private Banco banco;
    private MantenimientoCuenta mc;
    private DefaultListModel<String> modeloResultados = new DefaultListModel<>();
    private DefaultListModel<String> modeloLista = new DefaultListModel<>();
    private DefaultListModel<String> modeloTitulares = new DefaultListModel<>();
    private final DefaultTableModel dtm;
    private final Object[] obj = new Object[7];

    private boolean habilitarActualizar = false;

    public GestionCuentasPanel(Banco banco) {
        initComponents();
        initStyles();

        dtm = (DefaultTableModel) tCuentas.getModel();

        this.banco = banco;
        mc = new MantenimientoCuenta(banco);

        cargarModeloLista();
        listResultados.setModel(modeloResultados);

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

        listarCuentasTabla();
    }
    
    private void initStyles() {
        tCuentas.setShowVerticalLines(true);
    }
    //Llena modeloLista con todos los nombres y apellidos de cada cliente
    private void cargarModeloLista() {
        modeloLista.removeAllElements();
        for (int i = 0; i < banco.getClientes().length - 1; i++) {
            Cliente c = banco.getClientes()[i];
            modeloLista.addElement(c.getNombres() + " " + c.getApellidos());
        }
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
        String texto = txtBuscarTitular.getText().trim().toLowerCase();
        modeloResultados.clear();
        for (int i = 0; i < modeloLista.size(); i++) {
            String elemento = modeloLista.getElementAt(i).toLowerCase();
            String Elemento = modeloLista.getElementAt(i);
            if (elemento.contains(texto)) {
                modeloResultados.addElement(Elemento);
            }
        }
    }
    //Devuelte true si todos los campos no están vacíos
    private boolean camposValidos() {
        boolean esValido = modeloTitulares.getSize() != 0
                && boxTpoCuenta.getSelectedIndex() != 0
                && boxEstado.getSelectedIndex() != 0
                && boxMoneda.getSelectedIndex() != 0;

        return esValido;
    }
    //Habilita o deshabilita los botones de agregar, eliminar y reporte
    private void habilitarBotones(boolean operador) {
        btnAgregar.setEnabled(operador);
        btnEliminar.setEnabled(operador);
        btnReporte.setEnabled(operador);
    }
    //Llena toda la tabla de cuentas llamando al metodo listar de la clase Mantenimiento Cuenta
    private void listarCuentasTabla() {
        mc.listar(dtm, obj);
    }
    //Limpia todos los campos del formulario
    private void limpiarFormulario() {
        modeloTitulares.removeAllElements();
        boxTpoCuenta.setSelectedIndex(0);
        boxEstado.setSelectedIndex(0);
        boxMoneda.setSelectedIndex(0);
    }
    
    //Agrega o actualiza la lista de cuenta y cliente_cuenta según los campos en el formulario
    private void AgregarOActualizar() {
        EstadoCuenta estado = EstadoCuenta.values()[boxEstado.getSelectedIndex() - 1];
        Moneda moneda = Moneda.values()[boxMoneda.getSelectedIndex() - 1];

        mc.setDatosCuenta(TipoCuenta.values()[boxTpoCuenta.getSelectedIndex() - 1], estado, moneda);
        if (modeloTitulares.getSize() > 1) {
            int[] idClientes = new int[modeloTitulares.getSize()];
            for (int i = 0; i < idClientes.length; i++) {
                idClientes[i] = banco.buscarIdClientePorNombre(modeloTitulares.getElementAt(i));
            }
            mc.setIdClientes(idClientes);
        } else {
            int[] idCliente = new int[1];
            idCliente[0] = banco.buscarIdClientePorNombre(modeloTitulares.firstElement());
            mc.setIdClientes(idCliente);
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
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
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
        btnEditar = new javax.swing.JButton();
        btnEliminarTitular = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(799, 435));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Tipo Cuenta:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 80, 20));

        jLabel2.setText("Estado:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, 20));

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
        jScrollPane1.setViewportView(tCuentas);
        if (tCuentas.getColumnModel().getColumnCount() > 0) {
            tCuentas.getColumnModel().getColumn(0).setResizable(false);
            tCuentas.getColumnModel().getColumn(0).setPreferredWidth(2);
            tCuentas.getColumnModel().getColumn(1).setResizable(false);
            tCuentas.getColumnModel().getColumn(1).setPreferredWidth(50);
            tCuentas.getColumnModel().getColumn(2).setPreferredWidth(30);
            tCuentas.getColumnModel().getColumn(3).setResizable(false);
            tCuentas.getColumnModel().getColumn(4).setPreferredWidth(120);
            tCuentas.getColumnModel().getColumn(5).setResizable(false);
            tCuentas.getColumnModel().getColumn(6).setResizable(false);
            tCuentas.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 130, 780, 300));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 90, 30));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 90, 30));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 90, 30));

        boxTpoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Niguno", "Ahorro", "Corriente", "Mancomunada" }));
        jPanel1.add(boxTpoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 120, 20));

        boxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "ACTIVA", "BLOQUEADA" }));
        jPanel1.add(boxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 120, -1));

        btnReporte.setText("Reporte");
        jPanel1.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 90, 30));

        jLabel4.setText("Moneda:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, -1, -1));

        boxMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "SOL", "DOLAR" }));
        jPanel1.add(boxMoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 120, -1));
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

        jLabel5.setText("Buscar Titular");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel6.setText("Titulares agregados");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, 20));

        btnEliminarTitular.setText("Eliminar titular");
        btnEliminarTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTitularActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarTitular, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 100, 110, -1));

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
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (tCuentas.getSelectedRow() != -1) {
            
            int filaSeleccionada = tCuentas.getSelectedRow();
            int idCuenta = (int) dtm.getValueAt(filaSeleccionada, 0);
            
            mc.setIdCuenta(idCuenta);
            mc.eliminar();
            
            listarCuentasTabla();
            JOptionPane.showMessageDialog(null, "Cuenta elimiinada con éxito");
            
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, elija una cuenta primero", "Error al eliminar", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    
    //Agrega una cuenta a la lista de cuentas y agrega relaciones a la lista cliente_cuenta
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (camposValidos() && !habilitarActualizar) {

            AgregarOActualizar();
            mc.agregar();

            limpiarFormulario();

            listarCuentasTabla();
            JOptionPane.showMessageDialog(null, "Cuenta agregada exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, llena todos los campos", "Error al agregar cuenta", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

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
            modeloTitulares.removeElementAt(indice);
        }
    }//GEN-LAST:event_btnEliminarTitularActionPerformed
    
    //Llena el formulario con la fila seleccionada
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tCuentas.getSelectedRow() != -1) {

            habilitarActualizar = true;
            
            modeloTitulares.removeAllElements();

            int idCuenta = (int) dtm.getValueAt(tCuentas.getSelectedRow(), 0);
            Cliente[] clientes = banco.buscarClientesPorIdCuenta(idCuenta);
            if (clientes.length > 1) {
                for (int i = 0; i < clientes.length; i++) {
                    modeloTitulares.addElement(clientes[i].getNombres() + " " + clientes[i].getApellidos());
                }
            } else {
                modeloTitulares.addElement(clientes[0].getNombres() + " " + clientes[0].getApellidos());
            }

            boxTpoCuenta.setSelectedIndex(1);
            boxTpoCuenta.setEnabled(false);

            EstadoCuenta estado = (EstadoCuenta) dtm.getValueAt(tCuentas.getSelectedRow(), 2);
            int indexEstado = estado.ordinal() + 1;
            boxEstado.setSelectedIndex(indexEstado);

            Moneda moneda = (Moneda) dtm.getValueAt(tCuentas.getSelectedRow(), 6);
            int indexMoneda = moneda.ordinal() + 1;
            boxMoneda.setSelectedIndex(indexMoneda);

            habilitarBotones(false);

        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila", "Error al actuakizar", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed
    
    //Actualiza la lista de cuentas junto a la lista de cliente_cuenta
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (habilitarActualizar && camposValidos()) {
            mc.setIdCuenta((int) dtm.getValueAt(tCuentas.getSelectedRow(), 0));

            AgregarOActualizar();
            mc.actualizar();

            limpiarFormulario();
            
            habilitarActualizar = false;
            habilitarBotones(true);
            listarCuentasTabla();
            
            JOptionPane.showMessageDialog(null, "Cuenta actualziada exitosamente");

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, edite un campo primero", "Error al editar", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxEstado;
    private javax.swing.JComboBox<String> boxMoneda;
    private javax.swing.JComboBox<String> boxTpoCuenta;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarTitular;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listResultados;
    private javax.swing.JList<String> listTitulares;
    private javax.swing.JTable tCuentas;
    private javax.swing.JTextField txtBuscarTitular;
    // End of variables declaration//GEN-END:variables
}
