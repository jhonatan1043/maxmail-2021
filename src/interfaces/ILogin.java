/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import models.Login;

/**
 *
 * @author Programador 1
 */
public interface ILogin {
    
    public Login login(Login login);
    
    public boolean restaurarMail(Login login);
    
}
