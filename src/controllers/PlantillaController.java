/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoPlantilla;
import generals.Contans;
import generals.ValidButtonSystem;
import generals.ValidControlsSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Plantilla;
import views.VBusqueda;
import views.jPlantilla;
import views.jprincipal;

/**
 *
 * @author Programador 1
 */
public final class PlantillaController implements ActionListener {

    private final jPlantilla viewPlantilla;
    private final jprincipal viewPrincipal;
    DaoPlantilla daoPlantilla = new DaoPlantilla();
    Plantilla plantilla = new Plantilla();
    BusquedaController busquedaC;

    public PlantillaController(jPlantilla viewPlantilla, jprincipal viewPrincipal) {
        this.viewPlantilla = viewPlantilla;
        this.viewPrincipal = viewPrincipal;
        initEvent();
        ValidControlsSystem.disableControls(viewPlantilla.jLayeredPane1);
        viewPlantilla.btnBuscar.setEnabled(true);
        viewPlantilla.btnNew.setEnabled(true);
    }

    public void initEvent() {
        viewPlantilla.btnNew.addActionListener(this);
        viewPlantilla.btnCancel.addActionListener(this);
        viewPlantilla.btnEdit.addActionListener(this);
        viewPlantilla.btnDelete.addActionListener(this);
        viewPlantilla.btnBuscar.addActionListener(this);
        viewPlantilla.btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.viewPlantilla.btnNew) {
            ValidControlsSystem.enabledControls(viewPlantilla.jLayeredPane1);
            ValidButtonSystem.disableButton(viewPlantilla.pnlButton);
            limpiarControl();
            viewPlantilla.btnSave.setEnabled(true);
            viewPlantilla.btnCancel.setEnabled(true);

        }

        if (e.getSource() == this.viewPlantilla.btnCancel) {
            ValidControlsSystem.disableControls(viewPlantilla.jLayeredPane1);
            ValidButtonSystem.disableButton(viewPlantilla.pnlButton);
            limpiarControl();
            viewPlantilla.btnNew.setEnabled(true);
            viewPlantilla.btnBuscar.setEnabled(true);
        }

        if (e.getSource() == this.viewPlantilla.btnSave) {
            if (validarCampos()) {
                loadPlantilla();
                boolean respuesta;

                if (plantilla.getIdPlantilla() == 0) {
                    respuesta = daoPlantilla.save(plantilla);
                } else {
                    respuesta = daoPlantilla.update(plantilla);
                }

                if (respuesta) {
                    ValidControlsSystem.disableControls(viewPlantilla.jLayeredPane1);
                    ValidButtonSystem.enabledButton(viewPlantilla.pnlButton);
                    viewPlantilla.btnSave.setEnabled(false);
                    viewPlantilla.btnCancel.setEnabled(false);
                    JOptionPane.showMessageDialog(viewPlantilla, "¡Se ha Registrado con Exito!");
                }
            } else {
                JOptionPane.showMessageDialog(viewPlantilla, "¡Recuerda que todos los campos son Obligatorios!");
            }
        }

        if (e.getSource() == this.viewPlantilla.btnBuscar) {
            VBusqueda busqueda = new VBusqueda(viewPrincipal, true);
            busquedaC = new BusquedaController(busqueda, viewPlantilla, Contans.QUERY_PLANTILLA_BUSQUEDA, createColumns());
            busqueda.setVisible(true);
            if (System.getProperty("id") != null || "".equals(System.getProperty("id"))) {
                loadRegistroParam(Integer.parseInt(System.getProperty("id")));
            }
        }

        if (e.getSource() == viewPlantilla.btnEdit) {
            int resp = JOptionPane.showConfirmDialog(viewPlantilla, "¿Esta seguro de Editar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp != 1) {
                ValidControlsSystem.enabledControls(viewPlantilla.jLayeredPane1);
                ValidButtonSystem.disableButton(viewPlantilla.pnlButton);
                viewPlantilla.btnSave.setEnabled(true);
                viewPlantilla.btnCancel.setEnabled(true);
            }
        }

        if (e.getSource() == viewPlantilla.btnDelete) {
            int resp = JOptionPane.showConfirmDialog(viewPlantilla, "¿Esta seguro de Eliminar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp != 1) {
                if (daoPlantilla.delete(plantilla)) {
                    ValidButtonSystem.disableButton(viewPlantilla.pnlButton);
                    limpiarControl();
                    viewPlantilla.btnNew.setEnabled(true);
                    viewPlantilla.btnBuscar.setEnabled(true);
                    JOptionPane.showMessageDialog(viewPlantilla, "¡Eliminado con Exito!");
                }
            }
        }
    }

    public void loadPlantilla() {
        plantilla.setNombre(viewPlantilla.txtNombre.getText());
        plantilla.setPlantilla(viewPlantilla.txtPlantilla.getText());
    }

    private ArrayList<String> createColumns() {
        ArrayList<String> listColumns = new ArrayList<>();
        listColumns.add("Id");
        listColumns.add("Nombre");
        return listColumns;
    }

    public void loadRegistroParam(int id) {
        plantilla = daoPlantilla.getRegPlantilla(id);
        viewPlantilla.txtNombre.setText(plantilla.getNombre());
        viewPlantilla.txtPlantilla.setText(plantilla.getPlantilla());
        System.setProperty("id", "");
        ValidButtonSystem.enabledButton(viewPlantilla.pnlButton);
        viewPlantilla.btnSave.setEnabled(false);
        viewPlantilla.btnCancel.setEnabled(false);

    }

    public void limpiarControl() {
        plantilla = new Plantilla();
        viewPlantilla.txtNombre.setText("");
        viewPlantilla.txtPlantilla.setText("");
    }

    public boolean validarCampos() {
        return !("".equals(viewPlantilla.txtNombre.getText())
                || "".equals(viewPlantilla.txtPlantilla.getText()));
    }

}
