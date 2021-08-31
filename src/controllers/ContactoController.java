/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import generals.Combos;
import generals.Contans;
import generals.FileTxt;
import generals.ValidControlsSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import views.jContacto;
import views.jprincipal;

/**
 *
 * @author edwar
 */
public final class ContactoController implements ActionListener {

    private final jContacto viewContacto;
    private final jprincipal viewPrincipal;

    public ContactoController(jContacto viewContacto, jprincipal viewPrincipal) {
        this.viewContacto = viewContacto;
        this.viewPrincipal = viewPrincipal;
        loadComboServidor();
        initEvent();
    }

    public void loadComboServidor() {
        Combos combo = new Combos();
        combo.setSqlConsult(Contans.QUERY_SERVIDOR);
        try {
            combo.setCombo(viewContacto.comboServidor);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initEvent() {
        viewContacto.btnCargar.addActionListener(this);
        viewContacto.btnGenerar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewContacto.btnCargar) {
            try {
                FileTxt file = new FileTxt();
                quitarFilaJtable(viewContacto.jTCorreo);
                file.openFile(viewPrincipal, viewContacto);
            } catch (IOException ex) {
                Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == viewContacto.btnGenerar) {
            if (validator()) {
                FileTxt file = new FileTxt();
                ValidControlsSystem.disableControls(viewContacto.jLayeredPane1);
                file.createFileVCF(viewContacto);
            } else {
                JOptionPane.showMessageDialog(viewContacto, "¡Recuerda que todos los campos son Obligatorios!");
            }
        }

    }

    private boolean validator() {
        if (viewContacto.jTCorreo.getRowCount() == 0) {
            return false;
        } else {
            return viewContacto.comboServidor.getSelectedIndex() != 0;
        }
    }

    public void quitarFilaJtable(JTable table) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();

        for (int i = table.getRowCount() - 1; i > 0 - 1; i--) {
            modelo.removeRow(i);
        }

    }

}
