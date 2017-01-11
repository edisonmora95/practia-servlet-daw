/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Asistente {
    private String cedula;
    private String nombre;
    private String apellido;
    private int confer;
    private String correo;
    
    public Asistente(){
    }
    
    public Asistente(String cedula, String nombre, String apellido, int confer, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.confer = confer;
        this.correo = correo;
    }
    
    public static List<Asistente> getAsistente() {
        ArrayList<Asistente> lista = new ArrayList<>();
        lista.add(new Asistente("0921558411", "Pepe", "Portilla",1,"portillap@yopmail.com"));
        return lista;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getConferencia() {
        return confer;
    }

    public void setConferencia(int conferencia) {
        this.confer = conferencia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
