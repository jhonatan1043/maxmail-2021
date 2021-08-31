/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import models.Parametro;

/**
 *
 * @author Programador 1
 */
public interface IParametro {

    public Parametro getParametro(int idParametro);

    public Parametro getRegParametro(int idParametro);

    public boolean save(Parametro parametro);

    public boolean update(Parametro parametro);

    public boolean delete(Parametro parametro);

    public int getValue(String name);

    public ArrayList<String[]> listar();

    public ArrayList<String[]> listarParametro(int idUsuario, int idPlantilla);

    public boolean disminuirCantidad(Parametro parametro);
}
