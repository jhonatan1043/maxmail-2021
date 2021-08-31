/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

import dao.DaoParametro;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Parametro;
import views.jEnvioSeleccionado;
import views.jMensajeriaEnviar;

/**
 *
 * @author Programador 1
 */
public class Email {

    DaoParametro daoParametro = new DaoParametro();
    int conteo = 0;
    int contMaxLink = Contans.MAXIMO_LINK;
    int contInterno;

    public void envioEmail(jMensajeriaEnviar viewMensajeria,
            Thread thread) throws MessagingException {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String plantilla;
                Parametro parametro;
                ArrayList<String> lists;
                int count = viewMensajeria.jtEmail.getRowCount();
                String strDateFormat = "dd MMM yyyy hh:mm";
                SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

                plantilla = viewMensajeria.txtPlantilla.getText().replace("$$params", viewMensajeria.txtLink.getText());
                plantilla = plantilla.replace("$$fecha", objSDF.format(viewMensajeria.dtFecha.getDate()));

                for (int f = 0; f < viewMensajeria.jtEmailEnviar.getRowCount(); f++) {

                    ckeckCorreo(viewMensajeria);
                    viewMensajeria.jtEmailEnviar.setValueAt(true, f, 1);
                    parametro = loadParametro(viewMensajeria, viewMensajeria.jtEmailEnviar.getValueAt(f, 0).toString());
                    lists = listarMain(viewMensajeria);

                    if (conteo <= count - 1) {
                        try {

                            enviar(parametro,
                                    lists,
                                    plantilla,
                                    viewMensajeria, f);

                            Thread.sleep(100);

                        } catch (InterruptedException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        break;
                    }
                }
                validTerminado(viewMensajeria, count);
            }
        });
        thread.start();
    }

    public void envioEmailSeleccionado(jEnvioSeleccionado viewMensajeria,
            Thread thread) throws MessagingException {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String plantilla;
                Parametro parametro;
                ArrayList<String> lists;
                int count = viewMensajeria.jtEmail.getRowCount();
                String strDateFormat = "dd MMM yyyy hh:mm";
                SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

                plantilla = viewMensajeria.txtPlantilla.getText().replace("$$params", viewMensajeria.txtLink.getText());
                plantilla = plantilla.replace("$$fecha", objSDF.format(viewMensajeria.dtFecha.getDate()));

                for (int f = 0; f < viewMensajeria.jtEmailEnviar.getRowCount(); f++) {

                    ckeckCorreo(viewMensajeria);
                    viewMensajeria.jtEmailEnviar.setValueAt(true, f, 1);
                    parametro = loadParametro(viewMensajeria, viewMensajeria.jtEmailEnviar.getValueAt(f, 0).toString());
                    lists = listarMain(viewMensajeria);

                    if (conteo <= count - 1) {
                        try {

                            enviar(parametro,
                                    lists,
                                    plantilla,
                                    viewMensajeria, f);

                            Thread.sleep(100);

                        } catch (InterruptedException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        break;
                    }
                }
                validTerminado(viewMensajeria, count);
            }
        });
        thread.start();
    }

    public void enviar(Parametro parametro,
            ArrayList<String> listDestanatario,
            String cuerpo,
            jMensajeriaEnviar viewMensajeria, int fila) {

        DefaultTableModel modeloEmail = (DefaultTableModel) viewMensajeria.jtEmail.getModel();
        DefaultTableModel modeloEnviado = (DefaultTableModel) viewMensajeria.jtEmailEnviado.getModel();
        DefaultTableModel modeloError = (DefaultTableModel) viewMensajeria.jtEmailNoEnviado.getModel();
        Properties props = System.getProperties();
        contInterno = 0;
        props.put("mail.smtp.host", parametro.getServidor());
        props.put("mail.smtp.user", parametro.getRemitente());
        props.put("mail.smtp.clave", parametro.getPass());
        props.put("mail.smtp.auth", true);    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", true); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", parametro.getPuerto());

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message = mimeMessage(listDestanatario, props, cuerpo, viewMensajeria, parametro);

            Transport transport;
            transport = session.getTransport("smtp");
            transport.connect(parametro.getServidor(), parametro.getRemitente(), parametro.getPass());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            viewMensajeria.jprogressSistem.setMaximum(message.getSize());

            for (Address allRecipient : message.getAllRecipients()) {
                modeloEnviado.addRow(new String[]{allRecipient.toString()});
                conteo++;
                contInterno++;
                daoParametro.disminuirCantidad(parametro);
                viewMensajeria.lnNumEnviado.setText(String.valueOf(modeloEnviado.getRowCount()));
                viewMensajeria.jSpCantidad.setValue((int) viewMensajeria.jSpCantidad.getValue() - 1);
                viewMensajeria.lCorreoProcesados.setText(Contans.CORREO_PROCESADOS + String.valueOf(conteo));
                viewMensajeria.jprogressSistem.setValue(contInterno);
                viewMensajeria.jbEmail.setText("enviando " + viewMensajeria.jprogressSistem.getValue() + "%");
                if (conteo > message.getAllRecipients().length - 1) {
                    for (int i = message.getAllRecipients().length - 1; i > 0 - 1; i--) {
                        modeloEmail.removeRow(i);
                    }
                    viewMensajeria.jprogressSistem.setValue(0);
                }
            }

            viewMensajeria.jtEmailEnviar.setValueAt(true, fila, 2);
        } catch (MessagingException ex) {
            modeloError.addRow(new String[]{viewMensajeria.lCorreoRemitente.getText().replace(Contans.CORREO_REMITENTE, ""),
                ex.getMessage()});
            viewMensajeria.lnNumNoEnviado.setText(String.valueOf(modeloError.getRowCount()));
            viewMensajeria.jtEmailEnviar.setValueAt(false, fila, 2);
        }
    }
    
        public void enviar(Parametro parametro,
            ArrayList<String> listDestanatario,
            String cuerpo,
            jEnvioSeleccionado viewMensajeria, int fila) {

        DefaultTableModel modeloEmail = (DefaultTableModel) viewMensajeria.jtEmail.getModel();
        DefaultTableModel modeloEnviado = (DefaultTableModel) viewMensajeria.jtEmailEnviado.getModel();
        DefaultTableModel modeloError = (DefaultTableModel) viewMensajeria.jtEmailNoEnviado.getModel();
        Properties props = System.getProperties();
        contInterno = 0;
        props.put("mail.smtp.host", parametro.getServidor());
        props.put("mail.smtp.user", parametro.getRemitente());
        props.put("mail.smtp.clave", parametro.getPass());
        props.put("mail.smtp.auth", true);    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", true); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", parametro.getPuerto());

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message = mimeMessage(listDestanatario, props, cuerpo, viewMensajeria, parametro);

            Transport transport;
            transport = session.getTransport("smtp");
            transport.connect(parametro.getServidor(), parametro.getRemitente(), parametro.getPass());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            viewMensajeria.jprogressSistem.setMaximum(message.getSize());

            for (Address allRecipient : message.getAllRecipients()) {
                modeloEnviado.addRow(new String[]{allRecipient.toString()});
                conteo++;
                contInterno++;
                daoParametro.disminuirCantidad(parametro);
                viewMensajeria.lnNumEnviado.setText(String.valueOf(modeloEnviado.getRowCount()));
                viewMensajeria.jSpCantidad.setValue((int) viewMensajeria.jSpCantidad.getValue() - 1);
                viewMensajeria.lCorreoProcesados.setText(Contans.CORREO_PROCESADOS + String.valueOf(conteo));
                viewMensajeria.jprogressSistem.setValue(contInterno);
                viewMensajeria.jbEmail.setText("enviando " + viewMensajeria.jprogressSistem.getValue() + "%");
                if (conteo > message.getAllRecipients().length - 1) {
                    for (int i = message.getAllRecipients().length - 1; i > 0 - 1; i--) {
                        modeloEmail.removeRow(i);
                    }
                    viewMensajeria.jprogressSistem.setValue(0);
                }
            }

            viewMensajeria.jtEmailEnviar.setValueAt(true, fila, 2);
        } catch (MessagingException ex) {
            modeloError.addRow(new String[]{viewMensajeria.lCorreoRemitente.getText().replace(Contans.CORREO_REMITENTE, ""),
                ex.getMessage()});
            viewMensajeria.lnNumNoEnviado.setText(String.valueOf(modeloError.getRowCount()));
            viewMensajeria.jtEmailEnviar.setValueAt(false, fila, 2);
        }
    }

    public void enviarSistema(String servidor,
            String remitente,
            String Pass, int port,
            String asunto,
            String cuerpo,
            String destanatario,
            boolean auth,
            boolean seguridad) throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", servidor);
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", Pass);
        props.put("mail.smtp.auth", auth);    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", seguridad); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destanatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html; charset=utf-8");

            try (Transport transport = session.getTransport("smtp")) {
                transport.connect(servidor, remitente, Pass);
                transport.sendMessage(message, message.getAllRecipients());

            }
        } catch (AddressException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(Email.class
                    .getName()).log(Level.SEVERE, null, ex);

        }

    }

    private Parametro loadParametro(jMensajeriaEnviar viewMensajeria, String name) {
        Parametro parametro = daoParametro.getParametro(daoParametro.getValue(name));
        viewMensajeria.lCorreoRemitente.setText(Contans.CORREO_REMITENTE + parametro.getRemitente());
        viewMensajeria.jSpCantidad.setValue(parametro.getNumMinEmail());
        return parametro;
    }

    private ArrayList<String> listarMain(jMensajeriaEnviar viewMensajeria) {
        ArrayList<String> listCorreo = new ArrayList<>();
        int count = Integer.parseInt(viewMensajeria.jSpCantidad.getValue().toString());
        for (int i = 0; i < count; i++) {
            if (viewMensajeria.jtEmail.getRowCount() > i) {
                listCorreo.add(viewMensajeria.jtEmail.getValueAt(i, 0).toString());
            }
        }
        return listCorreo;
    }

    private void ckeckCorreo(jMensajeriaEnviar viewMensajeria) {
        for (int i = 0; i < viewMensajeria.jtEmailEnviar.getRowCount(); i++) {
            viewMensajeria.jtEmailEnviar.setValueAt(false, i, 1);
        }
    }

    private String cambiarLink(jMensajeriaEnviar viewMensajeria) {
        String plantilla = "";
        String resultado = JOptionPane.showInputDialog(null, "CAMBIA EL LINK PARA CONTINUAR.");

        if (!"".equals(resultado)) {
            viewMensajeria.txtLink.setText(resultado);
            plantilla = viewMensajeria.txtPlantilla.getText().replace("$$params", viewMensajeria.txtLink.getText());
        } else {
            cambiarLink(viewMensajeria);
        }

        return plantilla;
    }

    private void validTerminado(jMensajeriaEnviar viewMensajeria, int count) {
        ckeckCorreo(viewMensajeria);
        viewMensajeria.jbCancelar.setEnabled(false);
        viewMensajeria.jComboPlantilla.setEnabled(true);
        viewMensajeria.txtLink.setEnabled(true);
        viewMensajeria.txtAsunto.setEnabled(true);
        viewMensajeria.jbEmail.setEnabled(true);
        viewMensajeria.btnCargar.setEnabled(true);
        viewMensajeria.btnExportExcel.setEnabled(true);
        int restante = count - conteo;
        viewMensajeria.lnNum.setText("N° " + restante);
        viewMensajeria.jSpCantidad.setValue(0);
        viewMensajeria.jbEmail.setText("Enviar Email");
        JOptionPane.showMessageDialog(null, "Procesamiento Terminado \n enviados: "
                + viewMensajeria.jtEmailEnviado.getRowCount() + "\n correos remitente con errores: "
                + viewMensajeria.jtEmailNoEnviado.getRowCount());
    }

    private MimeMessage mimeMessage(ArrayList<String> listDestanatario,
            Properties props,
            String cuerpo,
            jMensajeriaEnviar viewMensajeria,
            Parametro parametro) throws AddressException, MessagingException {

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        contInterno = 0;
        viewMensajeria.jprogressSistem.setMaximum(listDestanatario.size());

        for (String destinatario : listDestanatario) {

            if (conteo == contMaxLink) {
                contMaxLink = contMaxLink + Contans.MAXIMO_LINK;
                cuerpo = cambiarLink(viewMensajeria);
            }
                   
            try {
                message.setFrom(new InternetAddress(parametro.getRemitente(),  parametro.getNombre()));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            }
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            contInterno++;

            viewMensajeria.jprogressSistem.setValue(contInterno);
            viewMensajeria.jbEmail.setText("Preparando " + viewMensajeria.jprogressSistem.getValue() + "%");
        }

        viewMensajeria.jprogressSistem.setValue(0);
        viewMensajeria.jbEmail.setText("Conectando...");

        message.setSubject(viewMensajeria.txtAsunto.getText());
        message.setContent(cuerpo, "text/html; charset=utf-8");

        return message;
    }
    
        private Parametro loadParametro(jEnvioSeleccionado viewMensajeria, String name) {
        Parametro parametro = daoParametro.getParametro(daoParametro.getValue(name));
        viewMensajeria.lCorreoRemitente.setText(Contans.CORREO_REMITENTE + parametro.getRemitente());
        viewMensajeria.jSpCantidad.setValue(parametro.getNumMinEmail());
        //viewMensajeria.jprogressSistem.setMaximum((int) viewMensajeria.jSpCantidad.getValue());
        return parametro;
    }

    private ArrayList<String> listarMain(jEnvioSeleccionado viewMensajeria) {
        ArrayList<String> listCorreo = new ArrayList<>();
        int count = Integer.parseInt(viewMensajeria.jSpCantidad.getValue().toString());
        for (int i = 0; i < count; i++) {
            if (viewMensajeria.jtEmail.getRowCount() > i) {
                listCorreo.add(viewMensajeria.jtEmail.getValueAt(i, 0).toString());
            }
        }
        return listCorreo;
    }

    private void ckeckCorreo(jEnvioSeleccionado viewMensajeria) {
        for (int i = 0; i < viewMensajeria.jtEmailEnviar.getRowCount(); i++) {
            viewMensajeria.jtEmailEnviar.setValueAt(false, i, 1);
        }
    }

    private String cambiarLink(jEnvioSeleccionado viewMensajeria) {
        String plantilla = "";
        String resultado = JOptionPane.showInputDialog(null, "CAMBIA EL LINK PARA CONTINUAR.");

        if (!"".equals(resultado)) {
            viewMensajeria.txtLink.setText(resultado);
            plantilla = viewMensajeria.txtPlantilla.getText().replace("$$params", viewMensajeria.txtLink.getText());
        } else {
            cambiarLink(viewMensajeria);
        }

        return plantilla;
    }

    private void validTerminado(jEnvioSeleccionado viewMensajeria, int count) {
        ckeckCorreo(viewMensajeria);
        viewMensajeria.jbCancelar.setEnabled(false);
        viewMensajeria.jComboPlantilla.setEnabled(true);
        viewMensajeria.txtLink.setEnabled(true);
        viewMensajeria.txtAsunto.setEnabled(true);
        viewMensajeria.jbEmail.setEnabled(true);
        viewMensajeria.btnCargar.setEnabled(true);
        viewMensajeria.btnExportExcel.setEnabled(true);
        int restante = count - conteo;
        viewMensajeria.lnNum.setText("N° " + restante);
        viewMensajeria.jSpCantidad.setValue(0);
        viewMensajeria.jbEmail.setText("Enviar Email");
        JOptionPane.showMessageDialog(null, "Procesamiento Terminado \n enviados: "
                + viewMensajeria.jtEmailEnviado.getRowCount() + "\n correos remitente con errores: "
                + viewMensajeria.jtEmailNoEnviado.getRowCount());
    }

    private MimeMessage mimeMessage(ArrayList<String> listDestanatario,
            Properties props,
            String cuerpo,
            jEnvioSeleccionado viewMensajeria,
            Parametro parametro) throws AddressException, MessagingException {

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        contInterno = 0;
        viewMensajeria.jprogressSistem.setMaximum(listDestanatario.size());

        for (String destinatario : listDestanatario) {

            if (conteo == contMaxLink) {
                contMaxLink = contMaxLink + Contans.MAXIMO_LINK;
                cuerpo = cambiarLink(viewMensajeria);
            }
            
            try {
                message.setFrom(new InternetAddress(parametro.getRemitente(), parametro.getNombrePerfil()));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            contInterno++;
            viewMensajeria.jprogressSistem.setValue(contInterno);
            viewMensajeria.jbEmail.setText("Preparando " + viewMensajeria.jprogressSistem.getValue() + "%");
        }

        viewMensajeria.jprogressSistem.setValue(0);
        viewMensajeria.jbEmail.setText("Conectando...");

        message.setSubject(viewMensajeria.txtAsunto.getText());
        message.setContent(cuerpo, "text/html; charset=utf-8");

        return message;
    }


}
