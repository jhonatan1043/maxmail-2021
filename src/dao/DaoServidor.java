/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import generals.Conexion;
import generals.Contans;
import interfaces.IServidor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Servidor;

/**
 *
 * @author Programador 1
 */
public class DaoServidor implements IServidor {

    @Override
    public boolean save(Servidor servidor) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_SERVIDOR_INSERT,
                    Statement.RETURN_GENERATED_KEYS)) {
                psmt.setString(1, servidor.getNombre());
                psmt.setString(2, servidor.getServidor());
                psmt.setInt(3, servidor.getPuerto());

                int affectedRows = psmt.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("¡No es posible Guardar!");
                }

                ResultSet generatedKeys = psmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    servidor.setIdServidor(generatedKeys.getInt(1));
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
    public boolean update(Servidor servidor) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_SERVIDOR_UPDATE)) {
                psmt.setString(1, servidor.getNombre());
                psmt.setString(2, servidor.getServidor());
                psmt.setInt(3, servidor.getPuerto());
                psmt.setInt(4, servidor.getIdServidor());
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
    public boolean delete(Servidor servidor) {
        boolean result = false;
        Conexion cnx = new Conexion();

        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_SERVIDOR_DELETE)) {
                psmt.setInt(1, servidor.getIdServidor());
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

        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_SERVIDOR_VALUE)) {
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
    public Servidor getRegServidor(int idServidor) {
        Conexion cnx = new Conexion();
        Servidor servidor = new Servidor();

        ResultSet result;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_REGISTRO_SERVIDOR)) {
            preparedStatement.setInt(1, idServidor);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                servidor.setIdServidor(idServidor);
                servidor.setNombre(result.getString(1));
                servidor.setServidor(result.getString(2));
                servidor.setPuerto(result.getInt(3));
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servidor;
    }
}
