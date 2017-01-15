/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.MySQLAccess;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Conferencia {
    private int id;
    private String fecha;
    private String nombre;
    private String descripcion;

    private static MySQLAccess connection;
    
    public Conferencia(){
        
    }

    public Conferencia(int id, String fecha, String nombre, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    

    
    public static ArrayList<Conferencia> getConferencias(){
        ArrayList<Conferencia> listConferencias = new ArrayList();
        try {
            String query = "SELECT id, nombre, fecha, descripcion FROM conferencias";
            //MySQLAccess connection;
            connection = new MySQLAccess();
            connection.connection();            
            ResultSet rs = connection.query(query);
            while(rs.next()){
                Conferencia conf = new Conferencia(rs.getInt("id")  ,rs.getString("fecha"), rs.getString("nombre"), rs.getString("descripcion"));
                listConferencias.add(conf);
            }
            connection.closeConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listConferencias;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
}
