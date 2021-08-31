/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoPlantilla;
import dao.DaoUsuario;
import dao.DaoUsuarioAsignar;
import generals.Combos;
import generals.Contans;
import generals.ValidButtonSystem;
import generals.ValidControlsSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.UsuarioAsignar;
import views.VBusqueda;
import views.jUsuarioCorreo;
import views.jprincipal;

public final class UsuarioAsignarController implements ActionListener {

    private final jUsuarioCorreo viewUsuarioCorreo;
    private final jprincipal viewPrincipal;
    DaoUsuarioAsignar daoUsuarioAsignar = new DaoUsuarioAsignar();
    UsuarioAsignar usuarioAsignar = new UsuarioAsignar();
    DefaultTableModel modelo;
    DaoPlantilla daoPlantilla = new DaoPlantilla();
    DaoUsuario daoUsuario = new DaoUsuario();
    int idUsuario = 0;
    int idPlantilla = 0;

    public UsuarioAsignarController(jUsuarioCorreo viewUsuarioCorreo, jprincipal viewPrincipal) {
        this.viewUsuarioCorreo = viewUsuarioCorreo;
        this.viewPrincipal = viewPrincipal;
        modelo = (DefaultTableModel) viewUsuarioCorreo.tbCorreos.getModel();
        initEvent();
        loadComboUsuario();
        loadComboPlantilla();
    }

    public void initEvent() {
        viewUsuarioCorreo.btnDelete.addActionListener(this);
        viewUsuarioCorreo.btnSave.addActionListener(this);
        viewUsuarioCorreo.comboPlantilla.addActionListener(this);
        viewUsuarioCorreo.comboUsuario.addActionListener(this);
        viewUsuarioCorreo.btnAgregar.addActionListener(this);
        viewUsuarioCorreo.btnQuitar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.viewUsuarioCorreo.btnSave) {

            if (validarCampos()) {
                boolean respuesta;
                loadUsuarioCorreo();
                if (usuarioAsignar.getModelo().getRowCount() > 0) {
                    respuesta = daoUsuarioAsignar.update(usuarioAsignar);
                } else {
                    respuesta = daoUsuarioAsignar.save(usuarioAsignar);
                }
                if (respuesta) {
                    JOptionPane.showMessageDialog(viewUsuarioCorreo, "¡Se ha Registrado con Exito!");
                }
            } else {
                JOptionPane.showMessageDialog(viewUsuarioCorreo, "¡Recuerda que todos los campos son Obligatorios!");
            }
        }

        if (e.getSource() == viewUsuarioCorreo.btnDelete) {
            JOptionPane.showMessageDialog(viewUsuarioCorreo, "¡MODO DE EVALUACION!");
            /* int resp = JOptionPane.showConfirmDialog(viewUsuarioCorreo, "¿Esta seguro de eliminar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp != 1) {
                if (daoUsuarioAsignar.delete(usuarioAsignar)) {
                    ValidButtonSystem.disableButton(viewUsuarioCorreo.pnlButton);
                    JOptionPane.showMessageDialog(viewUsuarioCorreo, "¡ Eliminado con Exito !");
                }
            }*/
        }

        if (e.getSource() == viewUsuarioCorreo.btnAgregar) {
            if (viewUsuarioCorreo.comboUsuario.getSelectedIndex() == 0
                    || viewUsuarioCorreo.comboPlantilla.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(viewUsuarioCorreo, "¡Selecciona el usuario y la plantilla!");
            } else {
                VBusqueda viewBusqueda = new VBusqueda(viewPrincipal, true);
                BusquedaController busquedaC = new BusquedaController(viewBusqueda,
                        viewUsuarioCorreo,
                        Contans.QUERY_USUARIO_PARAMETRO_BUSQUEDA,
                        createColumns());
                viewBusqueda.setVisible(true);
                loadParametro(Integer.parseInt(System.getProperty("id")));
            }
        }

        if (e.getSource() == viewUsuarioCorreo.btnQuitar) {
            modelo.removeRow(viewUsuarioCorreo.tbCorreos.getSelectedRow());
        }

        if (e.getSource() == viewUsuarioCorreo.comboPlantilla) {
            if (viewUsuarioCorreo.comboPlantilla.getSelectedIndex() > 0) {
                idPlantilla = daoPlantilla.getValue(viewUsuarioCorreo.comboPlantilla.getSelectedItem().toString());
                idUsuario = daoUsuario.getValue(viewUsuarioCorreo.comboUsuario.getSelectedItem().toString());
                loadCorreos(idUsuario, idPlantilla);
            }
        }

        if (e.getSource() == viewUsuarioCorreo.comboUsuario) {
            if (viewUsuarioCorreo.comboUsuario.getSelectedIndex() > 0) {
                idPlantilla = daoPlantilla.getValue(viewUsuarioCorreo.comboPlantilla.getSelectedItem().toString());
                idUsuario = daoUsuario.getValue(viewUsuarioCorreo.comboUsuario.getSelectedItem().toString());
                loadCorreos(idUsuario, idPlantilla);
            }
        }
    }

    public void loadComboUsuario() {
        Combos combo = new Combos();
        combo.setSqlConsult(Contans.QUERY_USUARIO);
        try {
            combo.setCombo(viewUsuarioCorreo.comboUsuario);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadComboPlantilla() {
        Combos combo = new Combos();
        combo.setSqlConsult(Contans.QUERY_PLANTILLA);
        try {
            combo.setCombo(viewUsuarioCorreo.comboPlantilla);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadCorreos(int idUsuario, int idPlantilla) {
        quitarFilaJtable(viewUsuarioCorreo.tbCorreos);
        ArrayList<Object[]> list = daoUsuarioAsignar.getRegUsuarioCorreo(idUsuario, idPlantilla);

        list.forEach((list1) -> {
            modelo.addRow(list1);
        });

    }

    private void loadUsuarioCorreo() {
        usuarioAsignar.setIdUsuario(daoUsuario.getValue(viewUsuarioCorreo.comboUsuario.getSelectedItem().toString()));
        usuarioAsignar.setIdPlantilla(daoPlantilla.getValue(viewUsuarioCorreo.comboPlantilla.getSelectedItem().toString()));
        usuarioAsignar.setModelo((DefaultTableModel) viewUsuarioCorreo.tbCorreos.getModel());
    }

    private void loadParametro(int id) {
        if (id != 0) {
            Object[] list = daoUsuarioAsignar.getParametro(id);
            modelo.addRow(list);
            System.setProperty("id", "");
        }
    }

    public void quitarFilaJtable(JTable table) {
        DefaultTableModel modeloEl = (DefaultTableModel) table.getModel();

        for (int i = table.getRowCount() - 1; i > 0 - 1; i--) {
            modeloEl.removeRow(i);
        }

    }

    private ArrayList<String> createColumns() {
        ArrayList<String> listColumns = new ArrayList<>();
        listColumns.add("Id");
        listColumns.add("Nombre");
        listColumns.add("email");
        return listColumns;
    }

    public boolean validarCampos() {
        return !(viewUsuarioCorreo.comboUsuario.getSelectedIndex() == 0
                || viewUsuarioCorreo.comboPlantilla.getSelectedIndex() == 0
                || viewUsuarioCorreo.tbCorreos.getRowCount() == 0);
    }

}
