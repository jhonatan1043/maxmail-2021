/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import generals.Conexion;
import generals.Contans;
import interfaces.IQueryMail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Poseidon
 */
public class DaoCaptureWeb implements IQueryMail {

    @Override
    public ArrayList<Object[]> listarCapturasWeb() {
        Conexion cnx = new Conexion();
        ArrayList<Object[]> listCapture = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_CAPTURE);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Object[] fila = new Object[5];
                fila[0] = result.getObject(1);
                fila[1] = result.getObject(2);
                fila[2] = result.getObject(3);
                fila[3] = result.getObject(4);
                fila[4] = result.getObject(5);
                listCapture.add(fila);
            }

            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCapture;
    }

}
