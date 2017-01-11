/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Conferencia {
    private String fecha;
    private String nombre;
    private String descripcion;
    
    public Conferencia(){
    }
    
    public Conferencia(String nombre, String fecha, String descripcion) {
        this.nombre = nombre;
        this.fecha = fecha;        
        this.descripcion = descripcion;
    }
    
    public static List<Conferencia> getConferencia() {
        ArrayList<Conferencia> lista = new ArrayList<>();
        lista.add(new Conferencia("Tendencias Web", "06/06/2017", "Ultimas tendencias de la web"));
        lista.add(new Conferencia("Seguridad de la Informacion", "12/06/2017", "Seguridad Informatica"));
        lista.add(new Conferencia("Economia Popular", "18/06/2017", "Economia"));
        return lista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    
        
}
