/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Asistente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edison
 */
public class AsistentesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        
        
        String idConferencia = request.getParameter("idConferencia");
        System.out.println("El id ingresado es:" + idConferencia);
        //Asistente a = new Asistente();
        //JsonObject json = a.getAsistentesJson(idConferencia);
        JsonObject json = getAsistentesJson(idConferencia);
        PrintWriter writer = response.getWriter();
        writer.print(json);
        
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
