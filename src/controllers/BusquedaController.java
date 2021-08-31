/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoBusqueda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import views.VBusqueda;

/**
 *
 * @author Poseidon
 */
public class BusquedaController implements ActionListener, KeyListener {

    VBusqueda viewBusqueda;
    DefaultTableModel modelo = new DefaultTableModel();
    String query;
    int numColumns;
    ArrayList<Object[]> listData;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewBusqueda.btnBusqueda) {
            this.clearRows();
            this.cargarGrillaBusqueda();
        }
        if (e.getSource() == viewBusqueda.btnAgregar) {
            returnValue();
        }
    }

    private void initEvent() {
        viewBusqueda.btnBusqueda.addActionListener(this);
        viewBusqueda.btnAgregar.addActionListener(this);
        viewBusqueda.txtBusqueda.addKeyListener(this);
    }

    public BusquedaController(
            VBusqueda viewBusqueda,
            Object formController,
            String query,
            ArrayList<String> listColumns) {

        this.query = query;
        this.numColumns = listColumns.size();
        this.viewBusqueda = viewBusqueda;
        this.initEvent();
        this.loadColumns(listColumns);
        this.cargarGrillaBusqueda();
    }

    private void loadColumns(ArrayList<String> listColumns) {
        for (int i = 0; i < listColumns.size(); i++) {
            modelo.addColumn(listColumns.get(i));
        }
        viewBusqueda.tbBusqueda.setModel(modelo);
    }

    private void cargarGrillaBusqueda() {
        DaoBusqueda busqueda = new DaoBusqueda();
        listData = busqueda.seachData(viewBusqueda.txtBusqueda.getText(),
                query,
                numColumns, viewBusqueda.tbBusqueda);
        listData.forEach((listData1) -> {
            modelo.addRow(listData1);
        });
    }

    private void clearRows() {
        modelo.setRowCount(0);
    }

    public void returnValue() {
        int index = viewBusqueda.tbBusqueda.getSelectedRow();
        Object id = viewBusqueda.tbBusqueda.getValueAt(index, 0);
        System.setProperty("id", (String) id);
        viewBusqueda.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == viewBusqueda.txtBusqueda) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                this.clearRows();
                this.cargarGrillaBusqueda();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
