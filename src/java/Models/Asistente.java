/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.MySQLAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Administrador
 */
public class Asistente {
    private String cedula;
    private String nombre;
    private String apellido;
    //private int confer;
    private String correo;
    
    private MySQLAccess connection;
    
    public Asistente(){
    }
    
    public Asistente(String cedula, String nombre, String apellido, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        //this.confer = confer;
        this.correo = correo;
    }
    
    public static List<Asistente> getAsistente() {
        ArrayList<Asistente> lista = new ArrayList<>();
        //lista.add(new Asistente("0921558411", "Pepe", "Portilla",1,"portillap@yopmail.com"));
        return lista;
    }

    public ArrayList<Asistente> getAsistentePorConferencia(String idConferencia){
        ArrayList<Asistente> listaAsistentes = new ArrayList<Asistente>();
        try {
            int id = Integer.parseInt(idConferencia);
            String query = "SELECT u.id, u.nombre, u.apellido, u.email FROM conf_asist ca, conferencias c, usuarios u WHERE ca_id = " + idConferencia + ";";
            this.connection = new MySQLAccess();
            ResultSet rs = connection.query(query);
            while(rs.next()){
                //Conferencia conf = new Conferencia(rs.getInt("id")  ,rs.getString("fecha"), rs.getString("nombre"), rs.getString("descripcion"));
                Asistente asistente = new Asistente(Integer.toString(rs.getInt("u.id")), rs.getString("u.nombre"), rs.getString("u.apellido"), rs.getString("u.email"));
                listaAsistentes.add(asistente);
                //listaAsistentes.add(conf);
            }
            this.connection.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Asistente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAsistentes;
    }
    
    public JsonObject getAsistentesJson(String idConferencia){
        //Obtengo el ArrayList contodas los asistentes de la conferencia de la base de datos
        Asistente a = new Asistente();
        ArrayList<Asistente> listaAsistentes = a.getAsistentePorConferencia(idConferencia);
        //Comienzo a crear el JsonObject
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        
        for(Asistente asistente : listaAsistentes){
            //Crea un JsonObject por cada conferencia
            JsonObjectBuilder asistenteBuilder = Json.createObjectBuilder();
            JsonObject asistenteJson = asistenteBuilder.add("cedula", asistente.getCedula())
                    .add("nombre", asistente.getNombre() != null ? asistente.getNombre() : "")
                    .add("apellido", asistente.getApellido()!= null ? asistente.getApellido(): "")
                    .add("correo", asistente.getCorreo()!= null ? asistente.getCorreo(): "")
                    .build();
            
            //Agrego cada planta al array
            arrayBuilder.add(asistenteJson);
        }
        //Añade el array de JsonObject a un JsonObject
        JsonObject asistentes = rootBuilder.add("asistentes", arrayBuilder).build();
        return asistentes;
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
/*
    public int getConferencia() {
        return confer;
    }

    public void setConferencia(int conferencia) {
        this.confer = conferencia;
    }
*/
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
