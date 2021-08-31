/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import generals.Conexion;
import generals.Contans;
import interfaces.ILogin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Login;

/**
 *
 * @author Programador 1
 */
public class DaoLogin implements ILogin {

    @Override
    public Login login(Login login) {
        Conexion cnx = new Conexion();
        Login log = new Login();

        ResultSet result;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_USUARIO)) {
            preparedStatement.setString(1, login.getUsuario());
            preparedStatement.setString(2, login.getPass());

            result = preparedStatement.executeQuery();

            while (result.next()) {
                log.setIdUsuario(result.getInt(1));
                log.setPermiso(result.getInt(2));
                log.setFecha(result.getDate(3));
            }

            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return log;
    }

    @Override
    public boolean restaurarMail(Login login) {
        boolean result = false;
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String fechaActual = objSDF.format(new Date());

        try {
            
            if (!login.getFecha().toString().equals(fechaActual)) {
                Conexion cnx = new Conexion();
                PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_RESTAURAR_CONTEO);
                preparedStatement.setInt(1, login.getIdUsuario());
                preparedStatement.executeQuery();
                cnx.connection().close();
                result = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
