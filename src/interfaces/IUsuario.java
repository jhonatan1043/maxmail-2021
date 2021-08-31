/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import models.Usuario;

public interface IUsuario {

    public Usuario getUsuario(int idUsuario);

    public Usuario getRegUsuario(int idUsuario);

    public boolean save(Usuario usuario);

    public boolean update(Usuario usuario);

    public boolean delete(Usuario usuario);

    public int getValue(String name);
}
