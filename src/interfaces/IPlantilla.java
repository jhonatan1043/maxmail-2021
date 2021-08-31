/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import models.Plantilla;

/**
 *
 * @author Programador 1
 */
public interface IPlantilla {

    public Plantilla getPlantilla(int idPlantilla);
    
    public Plantilla getRegPlantilla(int idPlantilla);

    public boolean save(Plantilla idSubcomponente);

    public boolean update(Plantilla idSubcomponente);

    public boolean delete(Plantilla idSubcomponente);

    public int getValue(String name);
}
