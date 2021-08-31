package controllers;

import dao.DaoParametro;
import dao.DaoPlantilla;
import generals.Combos;
import generals.Contans;
import generals.Email;
import generals.FileTxt;
import generals.GeneralExcel;
import generals.ValidControlsSystem;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Login;
import models.Parametro;
import models.Plantilla;
import models.Servidor;
import views.jEnvioSeleccionado;
import views.jprincipal;

public final class MensajeriaSelecController implements ActionListener, MouseListener, ItemListener {
    private final jEnvioSeleccionado viewMensajeria;
    private final jprincipal viewPrincipal;
    Email email;
    Plantilla plantilla;
    Servidor servidor;
    Parametro parametro;
    DaoPlantilla daoPlantilla = new DaoPlantilla();
    DaoParametro daoParametro = new DaoParametro();
    Thread thread;
    Login login;
    Calendar fecha = Calendar.getInstance();
    ArrayList<DefaultTableModel> listModelo;

    public MensajeriaSelecController(jEnvioSeleccionado viewMensajeria, jprincipal viewPrincipal, Login login) {
        this.viewMensajeria = viewMensajeria;
        this.viewPrincipal = viewPrincipal;
        ValidControlsSystem.disableControls(viewMensajeria.jLayeredPane1);
        viewMensajeria.btnCargar.setEnabled(true);
        viewMensajeria.dtFecha.setDate(fecha.getTime());
        initEvent();
        this.login = login;
        loadComboPlantilla();
    }

    public void limpiarTodo() {
        viewMensajeria.jComboPlantilla.setSelectedIndex(0);
        viewMensajeria.txtAsunto.setText("");
        viewMensajeria.txtLink.setText("");
        viewMensajeria.txtPlantilla.setText("");
        viewMensajeria.jSpCantidad.setValue(0);
        quitarFilaJtable(viewMensajeria.jtEmail);
        quitarFilaJtable(viewMensajeria.jtEmailEnviado);
        quitarFilaJtable(viewMensajeria.jtEmailNoEnviado);
        quitarFilaJtable(viewMensajeria.jtEmailEnviar);
        viewMensajeria.lCorreoRemitente.setText(Contans.CORREO_REMITENTE);
        viewMensajeria.lnNum.setText("N°");
        viewMensajeria.lnNumEnviado.setText("N°");
        viewMensajeria.lnNumNoEnviado.setText("N°");
    }

    public void quitarFilaJtable(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();

        for (int i = table.getRowCount() - 1; i > 0 - 1; i--) {
            modelo.removeRow(i);
        }

    }

    public void initEvent() {
        viewMensajeria.jbEmail.addActionListener(this);
        viewMensajeria.jbCancelar.addActionListener(this);
        viewMensajeria.btnCargar.addActionListener(this);
        viewMensajeria.jComboPlantilla.addActionListener(this); 
        viewMensajeria.btnExportExcel.addActionListener(this);
        viewMensajeria.btnExportExcel1.addActionListener(this);
        // eventos del mouse 
        viewMensajeria.jComboPlantilla.addMouseListener(this);
        viewMensajeria.txtLink.addMouseListener(this);
        viewMensajeria.jSpCantidad.addMouseListener(this);
       
    }

    public void loadComboPlantilla() {
        Combos combo = new Combos();
        combo.setSqlConsult(Contans.QUERY_PLANTILLA);
        try {
            combo.setCombo(viewMensajeria.jComboPlantilla);
        } catch (ClassNotFoundException ex) {
            mensajeDelSistema(ex.getMessage(), Contans.ID_MENSAJE_ERROR);
            Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listaCorreoEnviar(int idPlantilla) {
        quitarFilaJtable(viewMensajeria.jtEmailEnviar);
        ArrayList<String[]> listParametro = daoParametro.listarParametro(login.getIdUsuario(),
                idPlantilla);
        DefaultTableModel modelo = (DefaultTableModel) viewMensajeria.jtEmailEnviar.getModel();
        listParametro.forEach((list) -> {
            modelo.addRow(list);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewMensajeria.btnCargar) {
            try {
                limpiarTodo();
                FileTxt file = new FileTxt();
                file.openFile(viewPrincipal, viewMensajeria);
                viewMensajeria.lnNum.setText("N°" + viewMensajeria.jtEmail.getRowCount());
                if (viewMensajeria.jtEmail.getRowCount() > 0) {
                    ckeckCorreo();
                    viewMensajeria.jComboPlantilla.setEnabled(true);
                    viewMensajeria.txtAsunto.setEnabled(true);
                    viewMensajeria.txtLink.setEnabled(true);
                    viewMensajeria.jbEmail.setEnabled(true);
                    viewMensajeria.dtFecha.setEnabled(true);
                }
            } catch (IOException ex) {
                mensajeDelSistema(ex.getMessage(), Contans.ID_MENSAJE_ERROR);
                JOptionPane.showMessageDialog(null, ex.getMessage());
                Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == viewMensajeria.jbEmail) {
            if (viewMensajeria.jComboPlantilla.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "RECUERDA SELECCIONAR LA PLANTILLA");
            } else if ("".equals(viewMensajeria.txtAsunto.getText())) {
                JOptionPane.showMessageDialog(null, "RECUERDA QUE EL ASUNTO ES OBLIGATORIO");
            } else if ("".equals(viewMensajeria.txtLink.getText())) {
                JOptionPane.showMessageDialog(null, "RECUERDA QUE EL LINK ES OBLIGATORIO");
            } else if (viewMensajeria.jtEmailEnviar.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "CONFIGURA ALGUN SOPORTE PARA ESTA PLANTILA ANTES DE ENVIAR");
            } else if (viewMensajeria.dtFecha.getDate() == null) {
                JOptionPane.showMessageDialog(null, "RECUERDA QUE LA FECHA NO PUEDE ESTAR VACIA");
                viewMensajeria.dtFecha.setDate(fecha.getTime());
            } else {
                try {
                    email = new Email();

                    ValidControlsSystem.disableControls(viewMensajeria.jLayeredPane1);
                    viewMensajeria.jbCancelar.setEnabled(true);
                    email.envioEmailSeleccionado(viewMensajeria, thread);

                } catch (MessagingException ex) {
                    mensajeDelSistema(ex.getMessage(), Contans.ID_MENSAJE_ERROR);
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (e.getSource() == viewMensajeria.jbCancelar) {
            int resultado = JOptionPane.showConfirmDialog(null, "¿Esta seguro de Cancelar el envio?", "MAXMAIL", JOptionPane.YES_NO_OPTION);

            if (resultado == JOptionPane.YES_OPTION) {
                thread.suspend();
                viewMensajeria.jprogressSistem.setValue(0);
                viewMensajeria.jComboPlantilla.setEnabled(true);
                viewMensajeria.txtLink.setEnabled(true);
                viewMensajeria.txtAsunto.setEnabled(true);
                viewMensajeria.jbEmail.setEnabled(true);
                viewMensajeria.btnCargar.setEnabled(true);
                viewMensajeria.jbCancelar.setEnabled(false);
                viewMensajeria.dtFecha.setEnabled(true);
                JOptionPane.showMessageDialog(null, "¡Envio Cancelado con Exito!");
            }
        }

        if (e.getSource() == viewMensajeria.jComboPlantilla) {
            if (viewMensajeria.jComboPlantilla.getSelectedIndex() > 0) {
                loadPlantilla(daoPlantilla.getValue(viewMensajeria.jComboPlantilla.getSelectedItem().toString()));
            }
        }
        
        if (e.getSource() == viewMensajeria.btnExportExcel) {
            this.listModelo = new ArrayList<>();
            listModelo.add((DefaultTableModel) viewMensajeria.jtEmailNoEnviado.getModel());
            GeneralExcel.GeneraExcel(System.getProperty("java.io.tmpdir"), listModelo, "MaxMailErrores");
        }
        if (e.getSource() == viewMensajeria.btnExportExcel1) {
            this.listModelo = new ArrayList<>();
            listModelo.add((DefaultTableModel) viewMensajeria.jtEmailEnviado.getModel());
            GeneralExcel.GeneraExcel(System.getProperty("java.io.tmpdir"), listModelo, "MaxMailEnviado");
        }
    }

    private void mensajeDelSistema(String mensaje, int TipoMensaje) {

        String msj = "Mensajes del Sistema: " + mensaje;
        viewMensajeria.txtMensajeSistema.setText(msj);

        if (Contans.ID_MENSAJE_ERROR == TipoMensaje) {
            viewMensajeria.txtMensajeSistema.setBackground(Color.red);
        }
    }

    private void ckeckCorreo() {
        for (int i = 0; i < viewMensajeria.jtEmail.getRowCount(); i++) {
            viewMensajeria.jtEmail.setValueAt(true, i, 1);
        }
    }

    private void loadPlantilla(int idPlantilla) {
        plantilla = daoPlantilla.getPlantilla(idPlantilla);
        viewMensajeria.txtPlantilla.setText(plantilla.getPlantilla());
        listaCorreoEnviar(idPlantilla);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == viewMensajeria.jComboPlantilla) {
            mensajeDelSistema("SELECCIONADO PARA LA ELECCION DE LA PLANTILLA", Contans.ID_MENSAJE_NORMAL);
        }
        if (e.getSource() == viewMensajeria.jSpCantidad) {
            mensajeDelSistema("CANTIDAD TOTAL DE LOS CORREOS A ENVIAR", Contans.ID_MENSAJE_NORMAL);
        }
        if (e.getSource() == viewMensajeria.txtAsunto) {
            mensajeDelSistema("OPCIONAL. ASUNTO PARA LOS MENSAJES", Contans.ID_MENSAJE_NORMAL);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

}
