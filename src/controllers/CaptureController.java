/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoCaptureWeb;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import views.jCapture;

/**
 *
 * @author Poseidon
 */
public final class CaptureController {

    jCapture viewCapture;
    DaoCaptureWeb daoCaptureWeb = new DaoCaptureWeb();
    DefaultTableModel modelo;

    public CaptureController(jCapture viewCapture) {
        this.viewCapture = viewCapture;
        modelo = (DefaultTableModel) viewCapture.jTCapture.getModel();
        loadCapture();
    }

    public void loadCapture() {
        ArrayList<Object[]> list = daoCaptureWeb.listarCapturasWeb();

        list.forEach((list1) -> {
            modelo.addRow(list1);
        });

    }
}
