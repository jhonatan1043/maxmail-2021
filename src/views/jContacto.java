/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JOptionPane;

/**
 *
 * @author Programador 1
 */
public class jContacto extends javax.swing.JInternalFrame {

    /**
     * Creates new form jContacto
     */
    public jContacto() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jAdministracionfw = new javax.swing.JLabel();
        btnCargar = new javax.swing.JButton();
        btnGenerar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        comboServidor = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTCorreo = new javax.swing.JTable();
        jprogressSistem = new javax.swing.JProgressBar();
        jLbInform = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setClosable(true);
        setTitle("Administracion de carga");
        setOpaque(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLayeredPane1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jAdministracionfw.setBackground(new java.awt.Color(0, 0, 0));
        jAdministracionfw.setFont(new java.awt.Font("Garamond", 1, 36)); // NOI18N
        jAdministracionfw.setForeground(new java.awt.Color(255, 255, 255));
        jAdministracionfw.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jAdministracionfw.setText("Administracion de FW");
        jLayeredPane1.add(jAdministracionfw, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 70));

        btnCargar.setBackground(new java.awt.Color(0, 0, 0));
        btnCargar.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnCargar.setForeground(new java.awt.Color(255, 255, 255));
        btnCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon-entrar.png"))); // NOI18N
        btnCargar.setText("Cargar");
        btnCargar.setToolTipText("");
        btnCargar.setContentAreaFilled(false);
        btnCargar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCargar.setOpaque(true);
        jLayeredPane1.add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 120, -1));

        btnGenerar.setBackground(new java.awt.Color(0, 0, 0));
        btnGenerar.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon-generar.png"))); // NOI18N
        btnGenerar.setText("Generar");
        btnGenerar.setContentAreaFilled(false);
        btnGenerar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGenerar.setOpaque(true);
        jLayeredPane1.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 120, -1));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Seleccionar Servidor:");
        jLabel9.setOpaque(true);
        jLayeredPane1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 129, 39));

        comboServidor.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        comboServidor.setBorder(null);
        jLayeredPane1.add(comboServidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 160, -1));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        jTCorreo.setBackground(new java.awt.Color(0, 0, 0));
        jTCorreo.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jTCorreo.setForeground(new java.awt.Color(255, 255, 255));
        jTCorreo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Correos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTCorreo);
        if (jTCorreo.getColumnModel().getColumnCount() > 0) {
            jTCorreo.getColumnModel().getColumn(0).setResizable(false);
        }

        jLayeredPane1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 650, 275));

        jprogressSistem.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jprogressSistem.setForeground(new java.awt.Color(51, 255, 0));
        jprogressSistem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLayeredPane1.add(jprogressSistem, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 530, 27));

        jLbInform.setBackground(new java.awt.Color(0, 0, 0));
        jLbInform.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jLbInform.setForeground(new java.awt.Color(255, 255, 255));
        jLbInform.setText("PROGRESO:");
        jLbInform.setAutoscrolls(true);
        jLbInform.setOpaque(true);
        jLayeredPane1.add(jLbInform, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 90, 27));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        if (!this.btnGenerar.isEnabled()) {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            JOptionPane.showMessageDialog(null, "¡Espere que termine el proceso para salir!");
        }else{
           this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }       
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCargar;
    public javax.swing.JButton btnGenerar;
    public javax.swing.JComboBox<String> comboServidor;
    private javax.swing.JLabel jAdministracionfw;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JLabel jLbInform;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTCorreo;
    public javax.swing.JProgressBar jprogressSistem;
    // End of variables declaration//GEN-END:variables
}