/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Programador 1
 */
public class Querys {

    Conexion cnx = new Conexion();

    public Querys() {
    }

    public ArrayList<String> queryComboReturn(String sqlConsult) throws ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(sqlConsult)) {
            ResultSet result = preparedStatement.executeQuery();

            list.add(Contans.SELECTING);

            while (result.next()) {
                list.add(result.getString(2));
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return list;

    }

    public ArrayList<String> queryComboSubComponenteReturn(String sqlConsult) throws ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(sqlConsult)) {
            ResultSet result = preparedStatement.executeQuery();

            list.add(Contans.SELECTING);

            while (result.next()) {
                list.add(result.getInt(1) + "|" + result.getString(2) + "|" + result.getString(3));
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return list;

    }

    public ResultSet queryListComponente(String sqlConsult) {
        ResultSet result = null;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(sqlConsult)) {
            result = preparedStatement.executeQuery();
        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
        return result;
    }
}
