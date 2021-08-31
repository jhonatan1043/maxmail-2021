/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

import javax.swing.JButton;

/**
 *
 * @author Programador 1
 */
public class ValidButtonSystem {
    
    public static void  disableButton(javax.swing.JPanel panel){
        
        for (int i = 0; i < panel.getComponentCount(); i++){
           ((JButton)panel.getComponent(i)).setEnabled(false);
        }
    }
    
        public static void  enabledButton(javax.swing.JPanel panel){
        
        for (int i = 0; i < panel.getComponentCount(); i++){
           ((JButton)panel.getComponent(i)).setEnabled(true);
        }
    }
}
