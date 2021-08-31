/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author Poseidon
 */
public interface IBusqueda {
    public ArrayList<Object[]> seachData(String txtSeach, String query, int numColumns, JTable table );
}
