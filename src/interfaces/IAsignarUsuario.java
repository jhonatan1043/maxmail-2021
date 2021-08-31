/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import models.UsuarioAsignar;

public interface IAsignarUsuario {

    public ArrayList<Object[]> getRegUsuarioCorreo(int idUsuario, int idPlantilla);

    public Object[] getParametro(int idParametro);

    public boolean save(UsuarioAsignar usuarioAsignar);

    public boolean update(UsuarioAsignar usuarioAsignar);

    public boolean delete(UsuarioAsignar usuarioAsignar);
}
