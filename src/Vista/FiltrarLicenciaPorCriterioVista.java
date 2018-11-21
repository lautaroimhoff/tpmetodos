/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import ControladoresGestores.FiltrarLicenciaPorCriterioControlador;
import ControladoresGestores.MenuPrincipalControlador;
import Entity.Licencia;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author tinch
 */
public class FiltrarLicenciaPorCriterioVista extends javax.swing.JFrame {

    public FiltrarLicenciaPorCriterioVista() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Buscar licencias vigentes por criterio");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        donanteGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        nombreEditText = new javax.swing.JTextField();
        nombreLabel = new javax.swing.JLabel();
        apellidoEditText = new javax.swing.JTextField();
        apellidoLabel = new javax.swing.JLabel();
        dniEditText = new javax.swing.JTextField();
        dniLabel = new javax.swing.JLabel();
        nroEditText = new javax.swing.JTextField();
        claseLabel = new javax.swing.JLabel();
        donanteLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLicencias = new javax.swing.JTable();
        nroLabel = new javax.swing.JLabel();
        grupoLabel = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cbGrupoSanguineoTitular = new javax.swing.JComboBox<>();
        cbRHTitular = new javax.swing.JComboBox<>();
        claseCombo = new javax.swing.JComboBox<>();
        donanteSiButton = new javax.swing.JRadioButton();
        donanteNoButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Buscar Licencias");

        nombreLabel.setText("Nombre:");

        apellidoLabel.setText("Apellido:");

        dniLabel.setText("DNI:");

        claseLabel.setText("Clase:");

        donanteLabel.setText("Donante:");

        tablaLicencias.setAutoCreateRowSorter(true);
        tablaLicencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Tipo Licencia", "Fecha Vencimiento", "Grupo/Factor Sanguineo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLicencias.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tablaLicencias);
        tablaLicencias.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        nroLabel.setText("Nro. Licencia:");

        grupoLabel.setText("Grupo Sanguineo:");

        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel13.setText("Factor RH:");

        cbGrupoSanguineoTitular.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "A", "B", "AB", "O" }));
        cbGrupoSanguineoTitular.setPreferredSize(new java.awt.Dimension(35, 22));
        cbGrupoSanguineoTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGrupoSanguineoTitularActionPerformed(evt);
            }
        });

        cbRHTitular.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "+", "-" }));

        claseCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "A", "B", "C", "D", "E", "F", "G" }));
        claseCombo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        donanteGroup.add(donanteSiButton);
        donanteSiButton.setText("Sí");
        donanteSiButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        donanteGroup.add(donanteNoButton);
        donanteNoButton.setText("No");
        donanteNoButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(nombreLabel)
                                                    .addComponent(dniLabel))
                                                .addGap(18, 18, 18))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(claseLabel)
                                                .addGap(31, 31, 31)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(nombreEditText, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dniEditText, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                                            .addComponent(claseCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, 319, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(grupoLabel)
                                            .addComponent(apellidoLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(nroLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(donanteLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(apellidoEditText, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nroEditText, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbGrupoSanguineoTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbRHTitular, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(donanteSiButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(donanteNoButton)))))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(348, 348, 348))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(nombreEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidoLabel)
                    .addComponent(apellidoEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dniLabel)
                    .addComponent(dniEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nroEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nroLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grupoLabel)
                    .addComponent(jLabel13)
                    .addComponent(cbGrupoSanguineoTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbRHTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claseLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(donanteSiButton)
                        .addComponent(donanteNoButton))
                    .addComponent(donanteLabel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        ArrayList<Object> criterios = new ArrayList<Object>(8);
        
        criterios.add(0);
        criterios.add(1);
        criterios.add(2);
        criterios.add(3);
        criterios.add(4);
        criterios.add(5);
        criterios.add(6);
        criterios.add(7);

        criterios.set(0, this.nombreEditText.getText());
        criterios.set(1, this.apellidoEditText.getText());
        criterios.set(2, this.dniEditText.getText());
        criterios.set(3, this.nroEditText.getText());
        criterios.set(4, this.cbGrupoSanguineoTitular.getSelectedItem());
        criterios.set(5, this.cbRHTitular.getSelectedItem());
        criterios.set(6, this.claseCombo.getSelectedItem());
        criterios.set(7, this.donanteSiButton.isSelected());

        ArrayList<Licencia> lista = new ArrayList<>();
        lista = FiltrarLicenciaPorCriterioControlador.buscarPorCriterios(criterios);
        
        DefaultTableModel model = (DefaultTableModel) this.tablaLicencias.getModel();
        int sizeModel = model.getRowCount();
	    if(sizeModel !=0){
                model.setRowCount(0);
            }
        
        for (int i = 0; i < lista.size(); i++) {
            String grupoFactor = lista.get(i).getTitular().getGruposanguineo().getGruposanguineo() + lista.get(i).getTitular().getGruposanguineo().getFactor();
            Object[] fila = new Object[]{
                lista.get(i).getTitular().getNumerodocumento(),
                lista.get(i).getTitular().getNombre(),
                lista.get(i).getTitular().getApellido(),
                lista.get(i).getClaselicencia().getClaselicencia(),
                lista.get(i).getFechavencimiento(),
                grupoFactor
            };
            model.addRow(fila);
        }
       
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.setVisible(false);
        MenuPrincipalVista menuPrincipalVista = new MenuPrincipalVista();
        MenuPrincipalControlador menuPrincipalControlador = new MenuPrincipalControlador(menuPrincipalVista);
        menuPrincipalVista.conectaControlador(menuPrincipalControlador);
        menuPrincipalControlador.iniciar();
        menuPrincipalVista.setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void cbGrupoSanguineoTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGrupoSanguineoTitularActionPerformed

    }//GEN-LAST:event_cbGrupoSanguineoTitularActionPerformed
  
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FiltrarLicenciaPorCriterioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FiltrarLicenciaPorCriterioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FiltrarLicenciaPorCriterioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FiltrarLicenciaPorCriterioVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FiltrarLicenciaPorCriterioVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoEditText;
    private javax.swing.JLabel apellidoLabel;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnVolver;
    public javax.swing.JComboBox<String> cbGrupoSanguineoTitular;
    public javax.swing.JComboBox<String> cbRHTitular;
    private javax.swing.JComboBox<String> claseCombo;
    private javax.swing.JLabel claseLabel;
    private javax.swing.JTextField dniEditText;
    private javax.swing.JLabel dniLabel;
    private javax.swing.ButtonGroup donanteGroup;
    private javax.swing.JLabel donanteLabel;
    private javax.swing.JRadioButton donanteNoButton;
    private javax.swing.JRadioButton donanteSiButton;
    private javax.swing.JLabel grupoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nombreEditText;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JTextField nroEditText;
    private javax.swing.JLabel nroLabel;
    private javax.swing.JTable tablaLicencias;
    // End of variables declaration//GEN-END:variables
}
