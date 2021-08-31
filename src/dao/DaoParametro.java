/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import generals.Conexion;
import generals.Contans;
import interfaces.IParametro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Parametro;

/**
 *
 * @author Programador 1
 */
public class DaoParametro implements IParametro {

    @Override
    public Parametro getParametro(int idParametro) {
        Conexion cnx = new Conexion();
        Parametro parametro = new Parametro();

        ResultSet result;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_PARAMETRO)) {
            preparedStatement.setInt(1, idParametro);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                parametro.setIdParametro(idParametro);
                parametro.setRemitente(result.getString(1));
                parametro.setPass(result.getString(2));
                parametro.setServidor(result.getString(3));
                parametro.setPuerto(result.getInt(4));
                parametro.setNumEmail(result.getInt(5));
                parametro.setNumMinEmail(result.getInt(6));
                parametro.setAuth(result.getBoolean(7));
                parametro.setSeguridad(result.getBoolean(8));
                parametro.setNombre(result.getString(9));
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parametro;
    }

    @Override
    public boolean save(Parametro parametro) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_PARAMETRO_INSERT,
                    Statement.RETURN_GENERATED_KEYS)) {
                psmt.setString(1, parametro.getNombre());
                psmt.setString(2, parametro.getRemitente());
                psmt.setString(3, parametro.getPass());
                psmt.setInt(4, parametro.getIdServidor());
                psmt.setBoolean(5, parametro.isAuth());
                psmt.setBoolean(6, parametro.isSeguridad());
                psmt.setInt(7, parametro.getNumEmail());
                psmt.setInt(8, parametro.getNumEmail());
                psmt.setString(9, parametro.getNombrePerfil());
                int affectedRows = psmt.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("¡No es posible Guardar este cambio!");
                }

                ResultSet generatedKeys = psmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    parametro.setIdParametro(generatedKeys.getInt(1));
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
    public boolean update(Parametro parametro) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_PARAMETRO_UPDATE)) {
                psmt.setString(1, parametro.getNombre());
                psmt.setString(2, parametro.getRemitente());
                psmt.setString(3, parametro.getPass());
                psmt.setInt(4, parametro.getIdServidor());
                psmt.setBoolean(5, parametro.isAuth());
                psmt.setBoolean(6, parametro.isSeguridad());
                psmt.setInt(7, parametro.getNumEmail());
                psmt.setInt(8, parametro.getNumEmail());
                psmt.setString(9, parametro.getNombrePerfil());
                psmt.setInt(10, parametro.getIdParametro());
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
    public boolean delete(Parametro parametro) {
        boolean result = false;
        Conexion cnx = new Conexion();

        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_PARAMETRO_DELETE)) {
                psmt.setInt(1, parametro.getIdParametro());
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
    public Parametro getRegParametro(int idParametro) {
        Conexion cnx = new Conexion();
        Parametro parametro = new Parametro();

        ResultSet result;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_REGISTRO_PARAMETRO)) {
            preparedStatement.setInt(1, idParametro);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                parametro.setIdParametro(idParametro);
                parametro.setNombre(result.getString(1));
                parametro.setRemitente(result.getString(2));
                parametro.setPass(result.getString(3));
                parametro.setIdServidor(result.getInt(4));
                parametro.setAuth(result.getBoolean(5));
                parametro.setSeguridad(result.getBoolean(6));
                parametro.setNumEmail(result.getInt(7));
                parametro.setNombrePerfil(result.getString(8));
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parametro;
    }

    @Override
    public int getValue(String name) {
        Conexion cnx = new Conexion();
        ResultSet result;
        int value = 0;

        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_PARAMETRO_VALUE)) {
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
    public ArrayList<String[]> listar() {
        Conexion cnx = new Conexion();
        ArrayList<String[]> listParametro = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_PARAMETRO);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String[] fila = new String[1];
                fila[0] = result.getString(1);
                listParametro.add(fila);
            }

            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listParametro;
    }

    @Override
    public ArrayList<String[]> listarParametro(int idUsuario, int idPlantilla) {
        Conexion cnx = new Conexion();
        ArrayList<String[]> listParametro = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_PARAMETRO_USUARIO);
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setInt(2, idPlantilla);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String[] fila = new String[1];
                fila[0] = result.getString(1);
                listParametro.add(fila);
            }

            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listParametro;
    }

    @Override
    public boolean disminuirCantidad(Parametro parametro) {
        Conexion cnx = new Conexion();
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_DISMINUIR_CANTIDAD)) {
            preparedStatement.setInt(1, parametro.getIdParametro());
            preparedStatement.executeUpdate();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
