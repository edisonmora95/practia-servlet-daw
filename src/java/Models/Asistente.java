/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.MainServlet;
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
import javax.servlet.http.HttpServletRequest;

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
    
    private static MySQLAccess connection;
    
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
            
            //SELECT * FROM conf_asist ca INNER JOIN usuarios u ON ca.asist_id = u.id WHERE ca.conf_id = 1;
            String query = "SELECT u.id, u.nombre, u.apellido, u.email FROM confAsist ca INNER JOIN usuarios u ON ca.asist_id = u.id WHERE ca.conf_id = " + idConferencia +";";

            this.connection = new MySQLAccess();
            this.connection.connection();
            //this.connection.write(query);
            ResultSet rs = connection.query(query);
            while(rs.next()){
                //Conferencia conf = new Conferencia(rs.getInt("id")  ,rs.getString("fecha"), rs.getString("nombre"), rs.getString("descripcion"));
                Asistente asistente = 
                        new Asistente(
                                rs.getString("id"), rs.getString("nombre"), 
                                rs.getString("apellido"), rs.getString("email")
                        );
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
    
    public static boolean deleteAsistente(HttpServletRequest req){
        try {
            String id = req.getParameter("tdCedula");
            String confID = req.getParameter("aux");
            String query1 = "SELECT ca_id FROM confAsist WHERE asist_id =" + id + " AND conf_id =" + confID + ";";
            connection.connection();
            ResultSet aux = connection.query(query1);
            String query = "DELETE FROM confAsist WHERE ca_id=" + aux + ";";
            connection.write(query);
            connection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean updateAsistente(HttpServletRequest req){
        String id = req.getParameter("id");
        String nombre = req.getParameter("inputAsistantName");
        String apellido = req.getParameter("inputAsistantLast");
        String mail = req.getParameter("inputAsistantEmail");        
        
        String query = "UPDATE usuarios" +
                    " SET nombre = '" + nombre + "'," +
                    " apellido = '" + apellido + "'," +
                    " email = '" + mail + "'" +
                    " WHERE id = '" + id + "';";
        try {
            //connection = new MySQLAccess();
            connection.connection();
            connection.write(query);
            connection.closeConnection();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
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
