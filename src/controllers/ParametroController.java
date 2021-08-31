/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoParametro;
import dao.DaoServidor;
import generals.Combos;
import generals.Contans;
import generals.ValidButtonSystem;
import generals.ValidControlsSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Parametro;
import views.VBusqueda;
import views.jParametros;
import views.jprincipal;

public final class ParametroController implements ActionListener {

    private final jParametros viewParametro;
    private final jprincipal viewPrincipal;
    DaoParametro daoParametro = new DaoParametro();
    Parametro parametro = new Parametro();
    BusquedaController busquedaC;

    public ParametroController(jParametros viewParametro, jprincipal viewPrincipal) {
        this.viewParametro = viewParametro;
        this.viewPrincipal = viewPrincipal;
        ValidControlsSystem.disableControls(viewParametro.jLayeredPane1);
        loadComboServidor();
        initEvent();
        viewParametro.btnBuscar.setEnabled(true);
        viewParametro.btnNew.setEnabled(true);
    }

    public void loadComboServidor() {
        Combos combo = new Combos();
        combo.setSqlConsult(Contans.QUERY_SERVIDOR);
        try {
            combo.setCombo(viewParametro.comboServidor);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initEvent() {
        viewParametro.btnNew.addActionListener(this);
        viewParametro.btnCancel.addActionListener(this);
        viewParametro.btnEdit.addActionListener(this);
        viewParametro.btnDelete.addActionListener(this);
        viewParametro.btnBuscar.addActionListener(this);
        viewParametro.btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.viewParametro.btnNew) {
            ValidControlsSystem.enabledControls(viewParametro.jLayeredPane1);
            ValidButtonSystem.disableButton(viewParametro.pnlButton);
            limpiarControl();
            viewParametro.btnSave.setEnabled(true);
            viewParametro.btnCancel.setEnabled(true);

        }

        if (e.getSource() == this.viewParametro.btnCancel) {
            ValidControlsSystem.disableControls(viewParametro.jLayeredPane1);
            ValidButtonSystem.disableButton(viewParametro.pnlButton);
            limpiarControl();
            viewParametro.btnNew.setEnabled(true);
            viewParametro.btnBuscar.setEnabled(true);
        }

        if (e.getSource() == this.viewParametro.btnSave) {
            if (validarCampos()) {
                loadParametro();
                boolean respuesta;

                if (parametro.getIdParametro() == 0) {
                    respuesta = daoParametro.save(parametro);
                } else {
                    respuesta = daoParametro.update(parametro);
                }

                if (respuesta) {
                    ValidControlsSystem.disableControls(viewParametro.jLayeredPane1);
                    ValidButtonSystem.enabledButton(viewParametro.pnlButton);
                    viewParametro.btnSave.setEnabled(false);
                    viewParametro.btnCancel.setEnabled(false);
                    JOptionPane.showMessageDialog(viewParametro, "¡Se ha Registrado con Exito!");
                }
            } else {
                JOptionPane.showMessageDialog(viewParametro, "¡Recuerda que todos los campos son Obligatorios!");
            }
        }

        if (e.getSource() == this.viewParametro.btnBuscar) {
            VBusqueda busqueda = new VBusqueda(viewPrincipal, true);
            busquedaC = new BusquedaController(busqueda, viewParametro, Contans.QUERY_PARAMETRO_BUSQUEDA, createColumns());
            busqueda.setVisible(true);
            if (System.getProperty("id") != null || "".equals(System.getProperty("id"))) {
                loadRegistroParam(Integer.parseInt(System.getProperty("id")));
            }
        }

        if (e.getSource() == viewParametro.btnEdit) {
            int resp = JOptionPane.showConfirmDialog(viewParametro, "¿Esta seguro de Editar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp != 1) {
                ValidControlsSystem.enabledControls(viewParametro.jLayeredPane1);
                ValidButtonSystem.disableButton(viewParametro.pnlButton);
                viewParametro.btnSave.setEnabled(true);
                viewParametro.btnCancel.setEnabled(true);
            }
        }

        if (e.getSource() == viewParametro.btnDelete) {
            int resp = JOptionPane.showConfirmDialog(viewParametro, "¿Esta seguro de Eliminar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp != 1) {
                if (daoParametro.delete(parametro)) {
                    ValidButtonSystem.disableButton(viewParametro.pnlButton);
                    limpiarControl();
                    viewParametro.btnNew.setEnabled(true);
                    viewParametro.btnBuscar.setEnabled(true);
                    JOptionPane.showMessageDialog(viewParametro, "¡Eliminado con Exito!");
                }
            }
        }
    }

    public void loadParametro() {
        DaoServidor daoServidor = new DaoServidor();
        parametro.setNombre(viewParametro.textDescripcion.getText());
        parametro.setRemitente(viewParametro.txtRemitente.getText());
        parametro.setIdServidor(daoServidor.getValue(viewParametro.comboServidor.getSelectedItem().toString()));
        parametro.setPass(viewParametro.txtPass.getText());
        parametro.setAuth(true);
        parametro.setSeguridad(true);
        parametro.setNumEmail((int) viewParametro.spNumEmail.getValue());
        parametro.setNombrePerfil(viewParametro.textNombrePerfil.getText());
    }

    private ArrayList<String> createColumns() {
        ArrayList<String> listColumns = new ArrayList<>();
        listColumns.add("Id");
        listColumns.add("Nombre");
        listColumns.add("Remitente");
        return listColumns;
    }

    public void loadRegistroParam(int id) {

        parametro = daoParametro.getRegParametro((int) id);
        viewParametro.textDescripcion.setText(parametro.getNombre());
        viewParametro.txtRemitente.setText(parametro.getRemitente());
        viewParametro.txtPass.setText(parametro.getPass());
        viewParametro.comboServidor.setSelectedIndex(parametro.getIdServidor());
        viewParametro.checkSeguridad.setSelected(parametro.isSeguridad());
        viewParametro.jCheckBox1.setSelected(parametro.isAuth());
        viewParametro.spNumEmail.setValue(parametro.getNumEmail());
        viewParametro.textNombrePerfil.setText(parametro.getNombrePerfil());
        System.setProperty("id", "");
        ValidButtonSystem.enabledButton(viewParametro.pnlButton);
        viewParametro.btnSave.setEnabled(false);
        viewParametro.btnCancel.setEnabled(false);

    }

    public void limpiarControl() {
        parametro = new Parametro();
        viewParametro.textDescripcion.setText("");
        viewParametro.txtRemitente.setText("");
        viewParametro.txtPass.setText("");
        viewParametro.comboServidor.setSelectedIndex(0);
        viewParametro.checkSeguridad.setSelected(true);
        viewParametro.jCheckBox1.setSelected(true);
        viewParametro.spNumEmail.setValue(0);
    }

    public boolean validarCampos() {
        return !("".equals(viewParametro.textDescripcion.getText())
                || "".equals(viewParametro.txtRemitente.getText())
                || "".equals(viewParametro.txtPass.getSelectedText())
                || viewParametro.comboServidor.getSelectedIndex() == 0
                || viewParametro.spNumEmail.getValue() == "");
    }

}
