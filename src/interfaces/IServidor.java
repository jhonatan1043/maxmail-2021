/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import models.Servidor;

/**
 *
 * @author Programador 1
 */
public interface IServidor {

    public Servidor getRegServidor(int idServidor);

    public boolean save(Servidor idServidor);

    public boolean update(Servidor idServidor);

    public boolean delete(Servidor idSServidor);

    public int getValue(String name);
}
