/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import generals.Conexion;
import generals.Contans;
import interfaces.ISeguridad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Programador 1
 */
public class DaoSeguridad implements ISeguridad {

    @Override
    public boolean insertPC(String nombrePc, int key) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERY_INSERT_SEGURIDAD)) {
                psmt.setString(1, nombrePc);
                psmt.setInt(2, key);
                int affectedRows = psmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("¡No es posible Guardar!");
                }
                result = true;
                cnx.connection().close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean getPc(String nombrePc) {
        Conexion cnx = new Conexion();
        ResultSet result;
        boolean resultado = false;

        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_PC)) {
            preparedStatement.setString(1, nombrePc);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                if (result.getInt(1) != 0) {
                    resultado = true;
                } else {
                    resultado = false;
                }
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public boolean codigoSeguridad(String codigo, String pc) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERY_UPDATE_ACTIVO)) {
                psmt.setString(1, codigo);
                psmt.setString(2, pc);
                int affectedRows = psmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("¡No es posible Actualizar!");
                }
                result = true;
                cnx.connection().close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean getVerificarComf(String nombrePc) {
        Conexion cnx = new Conexion();
        ResultSet result;
        boolean resultado = false;

        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_VERIFICACION)) {
            preparedStatement.setString(1, nombrePc);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                if (result.getInt(1) != 0) {
                    resultado = true;
                } else {
                    resultado = false;
                }
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

}
