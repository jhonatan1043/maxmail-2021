/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

public interface ISeguridad {

    public boolean insertPC(String nombrePc, int codigo);

    public boolean getPc(String nombrePc);

    public boolean codigoSeguridad(String codigo, String pc);
    
    public boolean getVerificarComf(String nombrePc);

}
