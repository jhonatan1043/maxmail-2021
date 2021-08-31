/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import generals.Conexion;
import generals.Contans;
import interfaces.IPlantilla;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Plantilla;

/**
 *
 * @author Programador 1
 */
public class DaoPlantilla implements IPlantilla {

    @Override
    public Plantilla getPlantilla(int idPlantilla) {
        Conexion cnx = new Conexion();
        Plantilla plantilla = new Plantilla();

        ResultSet result;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_PLANTILLA)) {
            preparedStatement.setInt(1, idPlantilla);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                plantilla.setPlantilla(result.getString(1));
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantilla;
    }

    @Override
    public boolean save(Plantilla plantilla) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_PLANTILLA_INSERT,
                    Statement.RETURN_GENERATED_KEYS)) {
                psmt.setString(1, plantilla.getNombre());
                psmt.setString(2, plantilla.getPlantilla());

                int affectedRows = psmt.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("¡No es posible Guardar!");
                }

                ResultSet generatedKeys = psmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    plantilla.setIdPlantilla(generatedKeys.getInt(1));
                }

                cnx.connection().close();
                psmt.close();
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update(Plantilla plantilla) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_PLANTILLA_UPDATE)) {
                psmt.setString(1, plantilla.getNombre());
                psmt.setString(2, plantilla.getPlantilla());
                psmt.setInt(3, plantilla.getIdPlantilla());
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
    public boolean delete(Plantilla plantilla) {
        boolean result = false;
        Conexion cnx = new Conexion();

        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_PLANTILLA_DELETE)) {
                psmt.setInt(1, plantilla.getIdPlantilla());
                int affectedRows = psmt.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("¡No es posible ELIMINAR!");
                }

                result = true;
                psmt.close();
                cnx.connection().close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getValue(String name) {
        Conexion cnx = new Conexion();
        ResultSet result;
        int value = 0;

        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_PLANTILLA_VALUE)) {
            preparedStatement.setString(1, name);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                value = result.getInt(1);
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    @Override
    public Plantilla getRegPlantilla(int idPlantilla) {
        Conexion cnx = new Conexion();
        Plantilla plantilla = new Plantilla();

        ResultSet result;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_REGISTRO_PLANTILLA)) {
            preparedStatement.setInt(1, idPlantilla);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                plantilla.setIdPlantilla(idPlantilla);
                plantilla.setNombre(result.getString(1));
                plantilla.setPlantilla(result.getString(2));
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plantilla;
    }

}
