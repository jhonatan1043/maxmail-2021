/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import generals.Conexion;
import generals.Contans;
import interfaces.IAsignarUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UsuarioAsignar;

/**
 *
 * @author Programador 1
 */
public class DaoUsuarioAsignar implements IAsignarUsuario {

    @Override
    public ArrayList<Object[]> getRegUsuarioCorreo(int idUsuario, int idPlantilla) {
        Conexion cnx = new Conexion();
        ArrayList<Object[]> list = new ArrayList<>();
        Object[] fila;

        ResultSet result;
        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_LOAD_USUARIO_CORREO)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setInt(2, idPlantilla);

            result = preparedStatement.executeQuery();

            while (result.next()) {
                fila = new Object[3];
                fila[0] = result.getInt(1);
                fila[1] = result.getString(2);
                fila[2] = result.getString(3);
                list.add(fila);
            }

            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean save(UsuarioAsignar usuarioAsignar) {
        Conexion cnx = new Conexion();
        try {

            PreparedStatement insert = cnx.connection().prepareStatement("");
            for (int i = 0; i < usuarioAsignar.getModelo().getRowCount(); i++) {
                insert.setInt(1, usuarioAsignar.getIdUsuario());
                insert.setInt(2, usuarioAsignar.getIdPlantilla());
                insert.setInt(3, (int) usuarioAsignar.getModelo().getValueAt(i, 0));
                insert.executeUpdate();
            }

            cnx.connection().close();
        } catch (SQLException ex1) {
            Logger.getLogger(DaoUsuarioAsignar.class.getName()).log(Level.SEVERE, null, ex1);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(UsuarioAsignar usuarioAsignar) {
        try {
            if (delete(usuarioAsignar)) {
                Conexion cnx = new Conexion();
                PreparedStatement insert = cnx.connection().prepareStatement(Contans.QUERY_INSERT_USUARIO_CORREO);
                for (int i = 0; i < usuarioAsignar.getModelo().getRowCount(); i++) {
                    insert.setInt(1, usuarioAsignar.getIdUsuario());
                    insert.setInt(2, usuarioAsignar.getIdPlantilla());
                    insert.setInt(3, (int) usuarioAsignar.getModelo().getValueAt(i, 0));
                    insert.executeUpdate();
                }
                cnx.connection().close();
            }
        } catch (SQLException ex1) {
            Logger.getLogger(DaoUsuarioAsignar.class.getName()).log(Level.SEVERE, null, ex1);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(UsuarioAsignar usuarioAsignar) {
        Conexion cnx = new Conexion();
        try {

            PreparedStatement delete = cnx.connection().prepareStatement(Contans.QUERY_DELETE_USUARIO_CORREO);
            delete.setInt(1, usuarioAsignar.getIdUsuario());
            delete.setInt(2, usuarioAsignar.getIdPlantilla());
            delete.executeUpdate();

            cnx.connection().close();
        } catch (SQLException ex1) {
            Logger.getLogger(DaoUsuarioAsignar.class.getName()).log(Level.SEVERE, null, ex1);
            return false;
        }

        return true;
    }

    @Override
    public Object[] getParametro(int idParametro) {
        Object[] list = new Object[3];
        Conexion cnx = new Conexion();
        ResultSet result;

        try (PreparedStatement preparedStatement = cnx.connection().prepareStatement(Contans.QUERY_LOAD_PARAMETRO)) {
            preparedStatement.setInt(1, idParametro);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                list[0] = idParametro;
                list[1] = result.getObject(1);
                list[2] = result.getObject(2);
            }
            result.close();
            cnx.connection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoParametro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
