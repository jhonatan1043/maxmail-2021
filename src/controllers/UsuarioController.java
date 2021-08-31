/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoUsuario;
import generals.Contans;
import generals.ValidButtonSystem;
import generals.ValidControlsSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Usuario;
import views.VBusqueda;
import views.jUsuario;

import views.jprincipal;

/**
 *
 * @author Programador 1
 */
public final class UsuarioController implements ActionListener {

    private final jUsuario viewUsuario;
    private final jprincipal viewPrincipal;
    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = new Usuario();
    BusquedaController busquedaC;

    public UsuarioController(jUsuario viewUsuario, jprincipal viewPrincipal) {
        this.viewUsuario = viewUsuario;
        this.viewPrincipal = viewPrincipal;
        initEvent();
        ValidControlsSystem.disableControls(viewUsuario.jLayeredPane1);
        viewUsuario.btnBuscar.setEnabled(true);
        viewUsuario.btnNew.setEnabled(true);
    }

    public void initEvent() {
        viewUsuario.btnNew.addActionListener(this);
        viewUsuario.btnCancel.addActionListener(this);
        viewUsuario.btnEdit.addActionListener(this);
        viewUsuario.btnDelete.addActionListener(this);
        viewUsuario.btnBuscar.addActionListener(this);
        viewUsuario.btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewUsuario.btnNew) {
            ValidControlsSystem.enabledControls(viewUsuario.jLayeredPane1);
            ValidButtonSystem.disableButton(viewUsuario.pnlButton);
            limpiarControl();
            viewUsuario.btnSave.setEnabled(true);
            viewUsuario.btnCancel.setEnabled(true);

        }

        if (e.getSource() == this.viewUsuario.btnCancel) {
            ValidControlsSystem.disableControls(viewUsuario.jLayeredPane1);
            ValidButtonSystem.disableButton(viewUsuario.pnlButton);
            limpiarControl();
            viewUsuario.btnNew.setEnabled(true);
            viewUsuario.btnBuscar.setEnabled(true);
        }

        if (e.getSource() == this.viewUsuario.btnSave) {
            if (validarCampos()) {
                loadUsuario();
                boolean respuesta;

                if (usuario.getIdUsuario() == 0) {
                    respuesta = daoUsuario.save(usuario);
                } else {
                    respuesta = daoUsuario.update(usuario);
                }

                if (respuesta) {
                    ValidControlsSystem.disableControls(viewUsuario.jLayeredPane1);
                    ValidButtonSystem.enabledButton(viewUsuario.pnlButton);
                    viewUsuario.btnSave.setEnabled(false);
                    viewUsuario.btnCancel.setEnabled(false);
                    JOptionPane.showMessageDialog(viewUsuario, "¡Se ha Registrado con Exito!");
                }
            } else {
                JOptionPane.showMessageDialog(viewUsuario, "¡Recuerda que todos los campos son Obligatorios!");
            }
        }

        if (e.getSource() == this.viewUsuario.btnBuscar) {
            VBusqueda busqueda = new VBusqueda(viewPrincipal, true);
            busquedaC = new BusquedaController(busqueda, viewUsuario, Contans.QUERY_USUARIO_BUSQUEDA, createColumns());
            busqueda.setVisible(true);
            if (System.getProperty("id") != null || "".equals(System.getProperty("id"))) {
                loadRegistroParam(Integer.parseInt(System.getProperty("id")));
            }
        }

        if (e.getSource() == viewUsuario.btnEdit) {
            int resp = JOptionPane.showConfirmDialog(viewUsuario, "¿Esta seguro de Editar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp != 1) {
                ValidControlsSystem.enabledControls(viewUsuario.jLayeredPane1);
                ValidButtonSystem.disableButton(viewUsuario.pnlButton);
                viewUsuario.btnSave.setEnabled(true);
                viewUsuario.btnCancel.setEnabled(true);
            }
        }

        if (e.getSource() == viewUsuario.btnDelete) {
            int resp = JOptionPane.showConfirmDialog(viewUsuario, "¿Esta seguro de Eliminar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp != 1) {
                if (daoUsuario.delete(usuario)) {
                    ValidButtonSystem.disableButton(viewUsuario.pnlButton);
                    limpiarControl();
                    viewUsuario.btnNew.setEnabled(true);
                    viewUsuario.btnBuscar.setEnabled(true);
                    JOptionPane.showMessageDialog(viewUsuario, "¡Eliminado con Exito!");
                }
            }
        }
    }

    public void loadUsuario() {
        usuario.setUsuario(viewUsuario.txtUsuario.getText());
        usuario.setPass(viewUsuario.txtPassword.getText());
        usuario.setPermiso(viewUsuario.comboPermiso.getSelectedIndex());
    }

    private ArrayList<String> createColumns() {
        ArrayList<String> listColumns = new ArrayList<>();
        listColumns.add("Id");
        listColumns.add("Nombre");
        return listColumns;
    }

    public void loadRegistroParam(int id) {
        usuario = daoUsuario.getRegUsuario(id);
        viewUsuario.txtUsuario.setText(usuario.getUsuario());
        viewUsuario.txtPassword.setText(usuario.getPass());
        viewUsuario.comboPermiso.setSelectedIndex(usuario.getPermiso());
        System.setProperty("id", "");
        ValidButtonSystem.enabledButton(viewUsuario.pnlButton);
        viewUsuario.btnSave.setEnabled(false);
        viewUsuario.btnCancel.setEnabled(false);

    }

    public void limpiarControl() {
        usuario = new Usuario();
        viewUsuario.txtUsuario.setText("");
        viewUsuario.txtPassword.setText("");
        viewUsuario.comboPermiso.setSelectedIndex(0);
    }

    public boolean validarCampos() {
        return !("".equals(viewUsuario.txtUsuario.getText())
                || "".equals(viewUsuario.txtPassword.getText())
                || viewUsuario.comboPermiso.getSelectedIndex() == 0);
    }

}
