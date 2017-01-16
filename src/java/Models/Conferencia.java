/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.MySQLAccess;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author Administrador
 */
public class Conferencia {
    private int id;
    private String fecha;
    private String nombre;
    private String descripcion;

    private MySQLAccess connection;
    
    public Conferencia(){
        
    }

    public Conferencia(int id, String fecha, String nombre, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    

    
    public ArrayList<Conferencia> getConferencias(){
        ArrayList<Conferencia> listConferencias = new ArrayList();
        try {
            String query = "SELECT id, nombre, fecha, descripcion FROM conferencias";
            this.connection = new MySQLAccess();
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
    
    public JsonObject getConferenciasJson(){
        //Obtengo el ArrayList contodas las conferencias de la base de datos
        Conferencia c = new Conferencia();
        ArrayList<Conferencia> listaConferencias = c.getConferencias();
        //Comienzo a crear el JsonObject
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        
        for(Conferencia conferencia : listaConferencias){
            //Crea un JsonObject por cada conferencia
            JsonObjectBuilder conferenciaBuilder = Json.createObjectBuilder();
            JsonObject conferenciaJson = conferenciaBuilder.add("id", conferencia.getId())
                    .add("nombre", conferencia.getNombre() != null ? conferencia.getNombre() : "")
                    .add("fecha", conferencia.getFecha()!= null ? conferencia.getFecha() : "")
                    .add("descripcion", conferencia.getDescripcion()!= null ? conferencia.getDescripcion() : "")
                    .build();
            
            //Agrego cada planta al array
            arrayBuilder.add(conferenciaJson);
        }
        //Añade el array de JsonObject a un JsonObject
        JsonObject conferencias = rootBuilder.add("conferencias", arrayBuilder).build();
        return conferencias;
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
