/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Programador 1
 */
public class Parametro {

    private int idParametro;
    private String nombre;
    private String remitente;
    private String pass;
    private int idServidor;
    private String servidor;
    private int puerto;
    private boolean auth;
    private boolean seguridad;
    private int numEmail;
    private int numMinEmail;
    private String nombrePerfil;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isSeguridad() {
        return seguridad;
    }

    public void setSeguridad(boolean seguridad) {
        this.seguridad = seguridad;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public int getNumEmail() {
        return numEmail;
    }

    public void setNumEmail(int numEmail) {
        this.numEmail = numEmail;
    }

    public int getNumMinEmail() {
        return numMinEmail;
    }

    public void setNumMinEmail(int numMinEmail) {
        this.numMinEmail = numMinEmail;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }
    
    
    
}
