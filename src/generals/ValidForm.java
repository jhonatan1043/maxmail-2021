/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

/**
 *
 * @author Programador 1
 */
public class ValidForm {

    public static void centeForm(javax.swing.JInternalFrame form,
            javax.swing.JDesktopPane desktop) {

        int width = desktop.getWidth() - form.getWidth();
        int height = desktop.getHeight() - form.getHeight();

        form.setLocation(width / 2, height / 2);

    }
}
