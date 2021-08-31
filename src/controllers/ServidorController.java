/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoServidor;
import generals.Contans;
import generals.ValidButtonSystem;
import generals.ValidControlsSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Servidor;
import views.VBusqueda;
import views.jServidores;
import views.jprincipal;

/**
 *
 * @author Programador 1
 */
public final class ServidorController implements ActionListener {

    private final jServidores viewServidor;
    private final jprincipal viewPrincipal;
    DaoServidor daoServidor = new DaoServidor();
    Servidor servidor = new Servidor();
    BusquedaController busquedaC;

    public ServidorController(jServidores viewServidor, jprincipal viewPrincipal) {
        this.viewServidor = viewServidor;
        this.viewPrincipal = viewPrincipal;
        initEvent();
        ValidControlsSystem.disableControls(viewServidor.jLayeredPane1);
        viewServidor.btnBuscar1.setEnabled(true);
        viewServidor.btnNew1.setEnabled(true);
    }

    public void initEvent() {
        viewServidor.btnNew1.addActionListener(this);
        viewServidor.btnCancel1.addActionListener(this);
        viewServidor.btnEdit1.addActionListener(this);
        viewServidor.btnDelete1.addActionListener(this);
        viewServidor.btnBuscar1.addActionListener(this);
        viewServidor.btnSave1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewServidor.btnNew1) {
            ValidControlsSystem.enabledControls(viewServidor.jLayeredPane1);
            ValidButtonSystem.disableButton(viewServidor.pnlButton1);
            limpiarControl();
            viewServidor.btnSave1.setEnabled(true);
            viewServidor.btnCancel1.setEnabled(true);

        }

        if (e.getSource() == this.viewServidor.btnCancel1) {
            ValidControlsSystem.disableControls(viewServidor.jLayeredPane1);
            ValidButtonSystem.disableButton(viewServidor.pnlButton1);
            limpiarControl();
            viewServidor.btnNew1.setEnabled(true);
            viewServidor.btnBuscar1.setEnabled(true);
        }

        if (e.getSource() == this.viewServidor.btnSave1) {
            if (validarCampos()) {
                loadServidor();
                boolean respuesta;

                if (servidor.getIdServidor() == 0) {
                    respuesta = daoServidor.save(servidor);
                } else {
                    respuesta = daoServidor.update(servidor);
                }

                if (respuesta) {
                    ValidControlsSystem.disableControls(viewServidor.jLayeredPane1);
                    ValidButtonSystem.enabledButton(viewServidor.pnlButton1);
                    viewServidor.btnSave1.setEnabled(false);
                    viewServidor.btnCancel1.setEnabled(false);
                    JOptionPane.showMessageDialog(viewServidor, "¡Se ha Registrado con Exito!");
                }
            } else {
                JOptionPane.showMessageDialog(viewServidor, "¡Recuerda que todos los campos son Obligatorios!");
            }
        }

        if (e.getSource() == this.viewServidor.btnBuscar1) {
            VBusqueda busqueda = new VBusqueda(viewPrincipal, true);
            busquedaC = new BusquedaController(busqueda, viewServidor, Contans.QUERY_SERVIDOR_BUSQUEDA, createColumns());
            busqueda.setVisible(true);
            if (System.getProperty("id") != null || "".equals(System.getProperty("id"))) {
                loadRegistroParam(Integer.parseInt(System.getProperty("id")));
            }
        }

        if (e.getSource() == viewServidor.btnEdit1) {
            int resp = JOptionPane.showConfirmDialog(viewServidor, "¿Esta seguro de Editar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp != 1) {
                ValidControlsSystem.enabledControls(viewServidor.jLayeredPane1);
                ValidButtonSystem.disableButton(viewServidor.pnlButton1);
                viewServidor.btnSave1.setEnabled(true);
                viewServidor.btnCancel1.setEnabled(true);
            }
        }

        if (e.getSource() == viewServidor.btnDelete1) {
            int resp = JOptionPane.showConfirmDialog(viewServidor, "¿Esta seguro de Eliminar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp != 1) {
                if (daoServidor.delete(servidor)) {
                    ValidButtonSystem.disableButton(viewServidor.pnlButton1);
                    limpiarControl();
                    viewServidor.btnNew1.setEnabled(true);
                    viewServidor.btnBuscar1.setEnabled(true);
                    JOptionPane.showMessageDialog(viewServidor, "¡Eliminado con Exito!");
                }
            }
        }
    }

    public void loadServidor() {
        servidor.setNombre(viewServidor.txtNombre.getText());
        servidor.setServidor(viewServidor.txtServidor.getText());
        servidor.setPuerto((int) viewServidor.spPuerto.getValue());
    }

    private ArrayList<String> createColumns() {
        ArrayList<String> listColumns = new ArrayList<>();
        listColumns.add("Id");
        listColumns.add("Nombre");
        return listColumns;
    }

    public void loadRegistroParam(int id) {
        servidor = daoServidor.getRegServidor(id);
        viewServidor.txtNombre.setText(servidor.getNombre());
        viewServidor.txtServidor.setText(servidor.getServidor());
        viewServidor.spPuerto.setValue(servidor.getPuerto());
        System.setProperty("id", "");
        ValidButtonSystem.enabledButton(viewServidor.pnlButton1);
        viewServidor.btnSave1.setEnabled(false);
        viewServidor.btnCancel1.setEnabled(false);

    }

    public void limpiarControl() {
        servidor = new Servidor();
        viewServidor.txtNombre.setText("");
        viewServidor.txtServidor.setText("");
        viewServidor.spPuerto.setValue(0);
    }

    public boolean validarCampos() {
        return !("".equals(viewServidor.txtNombre.getText())
                || "".equals(viewServidor.txtServidor.getText()) || "".equals(viewServidor.spPuerto.getValue()));
    }

}
