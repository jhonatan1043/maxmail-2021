/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import models.Login;
import views.jLogin;
import views.jprincipal;

/**
 *
 * @author Programador 1
 */
public class LoginController implements ActionListener, KeyListener {

    jLogin viewLogin;
    DaoLogin daoLogin = new DaoLogin();
    Login login = new Login();

    public LoginController(jLogin viewLogin) {
        this.viewLogin = viewLogin;
        initEvent();
    }

    private void initEvent() {
        viewLogin.btnAcess.addActionListener(this);
        viewLogin.txtUsuario.addKeyListener(this);
        viewLogin.txtPass.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewLogin.btnAcess) {
            acceso();
        }
    }

    private void acceso() {
        if (validControls()) {
            loadLogin();

            login = daoLogin.login(login);

            if (login.getIdUsuario() != 0) {
                if (daoLogin.restaurarMail(login)) {
                    JOptionPane.showMessageDialog(null, "¡Bienvenido de nuevo " + viewLogin.txtUsuario.getText() + "!");
                }
                jprincipal viewPrincipal = new jprincipal(login, viewLogin);
                viewPrincipal.setVisible(true);
                viewLogin.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrecta");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Recuerda que todos los campos son obligatorios");
        }
    }

    private boolean validControls() {
        return !("".equals(viewLogin.txtUsuario.getText()) || "".equals(viewLogin.txtPass.getText()));
    }

    private void loadLogin() {
        login.setUsuario(viewLogin.txtUsuario.getText());
        login.setPass(viewLogin.txtPass.getText());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getSource() == viewLogin.txtUsuario) {

            if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                viewLogin.txtPass.requestFocus();
            }
        }

        if (e.getSource() == viewLogin.txtPass) {
            if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                viewLogin.txtUsuario.requestFocus();
                acceso();
            }
        }
    }
}
