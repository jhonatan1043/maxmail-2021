/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import generals.Conexion;
import generals.Contans;
import interfaces.IUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

/**
 *
 * @author Programador 1
 */
public class DaoUsuario implements IUsuario {

    @Override
    public Usuario getUsuario(int idUsuario) {
        Conexion cnx = new Conexion();
        Usuario usuario = new Usuario();
        ResultSet result;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_LOAD_USUARIO)) {
            preparedStatement.setInt(1, idUsuario);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                usuario.setUsuario(result.getString(1));
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    @Override
    public Usuario getRegUsuario(int idUsuario) {
        Conexion cnx = new Conexion();
        Usuario usuario = new Usuario();

        ResultSet result;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_REGISTRO_USUARIO)) {
            preparedStatement.setInt(1, idUsuario);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                usuario.setIdUsuario(idUsuario);
                usuario.setUsuario(result.getString(1));
                usuario.setPass(result.getString(2));
                usuario.setPermiso(result.getInt(3));
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    @Override
    public boolean save(Usuario usuario) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_USUARIO_INSERT,
                    Statement.RETURN_GENERATED_KEYS)) {
                psmt.setString(1, usuario.getUsuario());
                psmt.setString(2, usuario.getPass());
                psmt.setInt(3, usuario.getPermiso());
                int affectedRows = psmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("¡No es posible Guardar!");
                }
                ResultSet generatedKeys = psmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    usuario.setIdUsuario(generatedKeys.getInt(1));
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
    public boolean update(Usuario usuario) {
        boolean result = false;
        Conexion cnx = new Conexion();
        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_USUARIO_UPDATE)) {
                psmt.setString(1, usuario.getUsuario());
                psmt.setString(2, usuario.getPass());
                psmt.setInt(3, usuario.getPermiso());
                psmt.setInt(4, usuario.getIdUsuario());
                
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
    public boolean delete(Usuario usuario) {
        boolean result = false;
        Conexion cnx = new Conexion();

        try {
            try (PreparedStatement psmt = cnx.connection().prepareStatement(Contans.QUERYS_USUARIO_DELETE)) {
                psmt.setInt(1, usuario.getIdUsuario());
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

        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_GET_USUARIO_VALUE)) {
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

}
