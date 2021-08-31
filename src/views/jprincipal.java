/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.ContactoController;
import controllers.MensajeriaController;
import controllers.MensajeriaSelecController;
import controllers.ParametroController;
import controllers.PlantillaController;
import controllers.ServidorController;
import controllers.UsuarioAsignarController;
import controllers.UsuarioController;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import generals.ValidForm;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import models.Login;

/**
 *
 * @author Programador 1
 */
public final class jprincipal extends javax.swing.JFrame {

    jLogin viewLogin;
    Login login;

    public jprincipal(Login login, jLogin viewLogin) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setIconImage(new ImageIcon(getClass().getResource("/img/$.png")).getImage());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Desktop.setSize(screenSize.width, screenSize.height);
        this.login = login;
        this.setTitle("MAXMAIL -- USUARIO: " + viewLogin.txtUsuario.getText());
        this.viewLogin = viewLogin;
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        Desktop = new javax.swing.JDesktopPane();
        Creditos = new javax.swing.JLabel();
        version = new javax.swing.JLabel();
        Fondo_Principal = new javax.swing.JLabel();
        versionCD = new javax.swing.JLabel();
        Perido = new javax.swing.JLabel();
        tiempo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jSoftware = new javax.swing.JMenu();
        jCerrarSesion = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jSalir = new javax.swing.JMenuItem();
        jConfiguracion = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jOpciones = new javax.swing.JMenu();
        jMParametro = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jUsuarios = new javax.swing.JMenu();
        jMasignarCorreo = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMUsuario = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMPlantilla = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMServidor = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMSubirFw = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMMensajeria = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMSeleccionado = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");

        Creditos.setBackground(new java.awt.Color(0, 0, 0));
        Creditos.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        Creditos.setForeground(new java.awt.Color(255, 255, 255));
        Creditos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Creditos.setText("  Derechos Reservado. ZeusBlack - Poseidon © ");
        Creditos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Creditos.setOpaque(true);

        version.setBackground(new java.awt.Color(0, 0, 0));
        version.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        version.setForeground(new java.awt.Color(255, 255, 255));
        version.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        version.setText("Version de desarrollo actual:");
        version.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        version.setOpaque(true);

        Fondo_Principal.setBackground(new java.awt.Color(0, 0, 0));
        Fondo_Principal.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        Fondo_Principal.setForeground(new java.awt.Color(255, 255, 255));
        Fondo_Principal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fondo_Principal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/matrix2.gif"))); // NOI18N
        Fondo_Principal.setOpaque(true);

        versionCD.setBackground(new java.awt.Color(0, 0, 0));
        versionCD.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        versionCD.setForeground(new java.awt.Color(255, 255, 255));
        versionCD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        versionCD.setText("1.2.7");
        versionCD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        versionCD.setOpaque(true);

        Perido.setBackground(new java.awt.Color(0, 0, 0));
        Perido.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        Perido.setForeground(new java.awt.Color(255, 255, 255));
        Perido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Perido.setText("Periodo:");
        Perido.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Perido.setOpaque(true);

        tiempo.setBackground(new java.awt.Color(255, 0, 0));
        tiempo.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        tiempo.setForeground(new java.awt.Color(255, 255, 255));
        tiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tiempo.setText("Ilimitado");
        tiempo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tiempo.setOpaque(true);

        Desktop.setLayer(Creditos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Desktop.setLayer(version, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Desktop.setLayer(Fondo_Principal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Desktop.setLayer(versionCD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Desktop.setLayer(Perido, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Desktop.setLayer(tiempo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
            .addGroup(DesktopLayout.createSequentialGroup()
                .addComponent(Creditos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(version, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(versionCD, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Perido, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesktopLayout.createSequentialGroup()
                .addComponent(Fondo_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Creditos)
                    .addComponent(version)
                    .addComponent(versionCD)
                    .addComponent(Perido)
                    .addComponent(tiempo)))
        );

        Fondo_Principal.getAccessibleContext().setAccessibleParent(jLayeredPane1);

        jLayeredPane1.setLayer(Desktop, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N

        jSoftware.setText("Software");
        jSoftware.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N

        jCerrarSesion.setBackground(new java.awt.Color(255, 255, 255));
        jCerrarSesion.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jCerrarSesion.setText("Cerrar Sesion");
        jCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCerrarSesionActionPerformed(evt);
            }
        });
        jSoftware.add(jCerrarSesion);
        jSoftware.add(jSeparator7);

        jSalir.setBackground(new java.awt.Color(255, 255, 255));
        jSalir.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jSalir.setText("Salir");
        jSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSalirActionPerformed(evt);
            }
        });
        jSoftware.add(jSalir);

        jMenuBar1.add(jSoftware);

        jConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
        jConfiguracion.setText("Configuracion");
        jConfiguracion.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jConfiguracion.add(jSeparator1);

        jOpciones.setText("Opciones");
        jOpciones.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N

        jMParametro.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jMParametro.setText("Soportes");
        jMParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMParametroActionPerformed(evt);
            }
        });
        jOpciones.add(jMParametro);
        jOpciones.add(jSeparator2);

        jUsuarios.setText("Usuarios");
        jUsuarios.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N

        jMasignarCorreo.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jMasignarCorreo.setText("Enlazar");
        jMasignarCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMasignarCorreoActionPerformed(evt);
            }
        });
        jUsuarios.add(jMasignarCorreo);
        jUsuarios.add(jSeparator6);

        jMUsuario.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jMUsuario.setText("Crear Usuario");
        jMUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMUsuarioActionPerformed(evt);
            }
        });
        jUsuarios.add(jMUsuario);

        jOpciones.add(jUsuarios);
        jOpciones.add(jSeparator3);

        jMPlantilla.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jMPlantilla.setText("Plantillas");
        jMPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMPlantillaActionPerformed(evt);
            }
        });
        jOpciones.add(jMPlantilla);
        jOpciones.add(jSeparator5);

        jMServidor.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jMServidor.setText("Servidores");
        jMServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMServidorActionPerformed(evt);
            }
        });
        jOpciones.add(jMServidor);

        jConfiguracion.add(jOpciones);
        jConfiguracion.add(jSeparator9);

        jMSubirFw.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jMSubirFw.setText("Subir FW");
        jMSubirFw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMSubirFwActionPerformed(evt);
            }
        });
        jConfiguracion.add(jMSubirFw);

        jMenuBar1.add(jConfiguracion);

        jMenu2.setText("Mensajeria");
        jMenu2.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N

        jMMensajeria.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jMMensajeria.setText("Envio Masivo");
        jMMensajeria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMMensajeriaActionPerformed(evt);
            }
        });
        jMenu2.add(jMMensajeria);
        jMenu2.add(jSeparator4);

        jMenuItem1.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jMenuItem1.setText("Envio Programado");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);
        jMenu2.add(jSeparator8);

        jMSeleccionado.setFont(new java.awt.Font("Garamond", 1, 14)); // NOI18N
        jMSeleccionado.setText("Envio Seleccionado");
        jMSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMSeleccionadoActionPerformed(evt);
            }
        });
        jMenu2.add(jMSeleccionado);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMMensajeriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMMensajeriaActionPerformed
        if (login.getPermiso() == 2 || login.getPermiso() == 1) {
            jMensajeriaEnviar viewMensajeria = new jMensajeriaEnviar();
            MensajeriaController mensajeriaController = new MensajeriaController(viewMensajeria, this, login);
            this.Desktop.add(viewMensajeria);
            ValidForm.centeForm(viewMensajeria, Desktop);
            viewMensajeria.show();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene permiso para realizar esta acción", "MAXMAIL", JOptionPane.QUESTION_MESSAGE);
        }
    }//GEN-LAST:event_jMMensajeriaActionPerformed

    private void jMParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMParametroActionPerformed
        if (login.getPermiso() == 2 || login.getPermiso() == 1) {
            jParametros viewParametro = new jParametros();
            ParametroController parametroController = new ParametroController(viewParametro, this);
            this.Desktop.add(viewParametro);
            ValidForm.centeForm(viewParametro, Desktop);
            viewParametro.show();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene permiso para realizar esta acción", "MAXMAIL", JOptionPane.QUESTION_MESSAGE);
        }
    }//GEN-LAST:event_jMParametroActionPerformed

    private void jMServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMServidorActionPerformed
        if (login.getPermiso() == 1) {
            jServidores viewServidor = new jServidores();
            ServidorController servidorController = new ServidorController(viewServidor, this);
            this.Desktop.add(viewServidor);
            ValidForm.centeForm(viewServidor, Desktop);
            viewServidor.show();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene permiso para realizar esta acción", "MAXMAIL", JOptionPane.QUESTION_MESSAGE);
        }
    }//GEN-LAST:event_jMServidorActionPerformed

    private void jMPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMPlantillaActionPerformed
        if (login.getPermiso() == 1) {
            jPlantilla viewPlantilla = new jPlantilla();
            PlantillaController plantillaController = new PlantillaController(viewPlantilla, this);
            this.Desktop.add(viewPlantilla);
            ValidForm.centeForm(viewPlantilla, Desktop);
            viewPlantilla.show();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene permiso para realizar esta acción", "MAXMAIL", JOptionPane.QUESTION_MESSAGE);
        }
    }//GEN-LAST:event_jMPlantillaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(null, "En Construccion");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMasignarCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMasignarCorreoActionPerformed
        if (login.getPermiso() == 2 || login.getPermiso() == 1) {
            jUsuarioCorreo viewUsuarioCorreo = new jUsuarioCorreo();
            UsuarioAsignarController usuarioAsignarController = new UsuarioAsignarController(viewUsuarioCorreo, this);
            this.Desktop.add(viewUsuarioCorreo);
            ValidForm.centeForm(viewUsuarioCorreo, Desktop);
            viewUsuarioCorreo.show();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene permiso para realizar esta acción", "MAXMAIL", JOptionPane.QUESTION_MESSAGE);
        }
    }//GEN-LAST:event_jMasignarCorreoActionPerformed

    private void jMUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMUsuarioActionPerformed
        if (login.getPermiso() == 1) {
            jUsuario viewUsuario = new jUsuario();
            UsuarioController plantillaController = new UsuarioController(viewUsuario, this);
            this.Desktop.add(viewUsuario);
            ValidForm.centeForm(viewUsuario, Desktop);
            viewUsuario.show();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene permiso para realizar esta acción", "MAXMAIL", JOptionPane.QUESTION_MESSAGE);
        }
    }//GEN-LAST:event_jMUsuarioActionPerformed

    private void jCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCerrarSesionActionPerformed
        int resultado = JOptionPane.showConfirmDialog(this, "¿Desea cerrar la sesion del programa?", "MAXMAIL", JOptionPane.YES_NO_OPTION);
        if (resultado == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            viewLogin.setVisible(true);
            viewLogin.txtPass.setText("");
        }
    }//GEN-LAST:event_jCerrarSesionActionPerformed

    private void jSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSalirActionPerformed
        int resultado = JOptionPane.showConfirmDialog(this, "¿Desea salir del programa?", "MAXMAIL", JOptionPane.YES_NO_OPTION);
        if (resultado == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jSalirActionPerformed

    private void jMSeleccionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMSeleccionadoActionPerformed
        if (login.getPermiso() == 2 || login.getPermiso() == 1) {
            jEnvioSeleccionado viewMensajeria = new jEnvioSeleccionado();
            MensajeriaSelecController mensajeriaController = new MensajeriaSelecController(viewMensajeria, this, login);
            this.Desktop.add(viewMensajeria);
            ValidForm.centeForm(viewMensajeria, Desktop);
            viewMensajeria.show();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene permiso para realizar esta acción", "MAXMAIL", JOptionPane.QUESTION_MESSAGE);
        }
    }//GEN-LAST:event_jMSeleccionadoActionPerformed

    private void jMSubirFwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMSubirFwActionPerformed
        if (login.getPermiso() == 2 || login.getPermiso() == 1) {
            jContacto viewContacto = new jContacto();
            ContactoController contactoController = new ContactoController(viewContacto, this);
            this.Desktop.add(viewContacto);
            ValidForm.centeForm(viewContacto, Desktop);
            viewContacto.show();
        } else {
            JOptionPane.showMessageDialog(null, "No tiene permiso para realizar esta acción", "MAXMAIL", JOptionPane.QUESTION_MESSAGE);
        }
        /**
         * Agregar boton de cargar txt como el que esta en @'mesajeria',
         * utilizar una grilla, Jtable boton generar .vcf al momento de crear el
         * contacto tener en cuenta la plantilla BEGIN:VCARD VERSION:3.0
         * N:x;x;;; FN:x x EMAIL;TYPE=INTERNET;TYPE=HOME:edward.ch@gmail.com
         * END:VCARD abrir la carpeta
         */
    }//GEN-LAST:event_jMSubirFwActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jprincipal(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Creditos;
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JLabel Fondo_Principal;
    private javax.swing.JLabel Perido;
    private javax.swing.JMenuItem jCerrarSesion;
    private javax.swing.JMenu jConfiguracion;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenuItem jMMensajeria;
    private javax.swing.JMenuItem jMParametro;
    private javax.swing.JMenuItem jMPlantilla;
    private javax.swing.JMenuItem jMSeleccionado;
    private javax.swing.JMenuItem jMServidor;
    private javax.swing.JMenuItem jMSubirFw;
    private javax.swing.JMenuItem jMUsuario;
    private javax.swing.JMenuItem jMasignarCorreo;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jOpciones;
    private javax.swing.JMenuItem jSalir;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenu jSoftware;
    private javax.swing.JMenu jUsuarios;
    private javax.swing.JLabel tiempo;
    private javax.swing.JLabel version;
    private javax.swing.JLabel versionCD;
    // End of variables declaration//GEN-END:variables
}
