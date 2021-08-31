/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

import java.util.ArrayList;

/**
 *
 * @author Programador 1
 */
public class GenerarColumns {

    public static ArrayList<String> setCreateColumns(int index) {
        ArrayList<String> listColumns = new ArrayList<>();
        listColumns.add("Codigo");
        listColumns.add("ComponenteMayor");
        listColumns.add("Logitud");
        listColumns.add("Ubicacion");
        listColumns.add("Comentario");
        return listColumns;
    }
}
