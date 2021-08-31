/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import generals.Conexion;
import interfaces.IBusqueda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author Poseidon
 */
public class DaoBusqueda implements IBusqueda {

    Conexion cnx = new Conexion();

    @Override
    public ArrayList<Object[]> seachData(String txtSeach,
            String queryBusqueda, int numColumns, JTable table) {

        String stringSeaching = queryBusqueda + txtSeach + "%';";
        ResultSet result;
        ArrayList<Object[]> listData = new ArrayList<>();
        
//        table.setDefaultRenderer(Object.class, new Render());
//        JButton btnAgregar = new JButton("Agregar");

        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(stringSeaching)) {

            result = preparedStatement.executeQuery();

            while (result.next()) {
                String[] list = new String[numColumns];
                for (int i = 1; i - 1 < numColumns; i++) {
                    list[i-1] =  result.getObject(i).toString();
                }
                listData.add(list);
            }

            result.close();
        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }

        return listData;
    }

}
