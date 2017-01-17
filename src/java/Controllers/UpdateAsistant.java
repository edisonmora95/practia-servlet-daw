/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Asistente;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author chini
 */
@WebServlet(name = "UpdateAsistant", urlPatterns = {"/UpdateAsistant"})
public class UpdateAsistant extends HttpServlet {
private MySQLAccess connection;
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
        /*response.setContentType("application/json");
        
        this.connection = new MySQLAccess();
        this.connection.connection();
        
        this.updateAsistente(request);
        
        JsonObject json = getConfirmation();
        PrintWriter writer = response.getWriter();
        writer.print(json);*/
        response.setContentType("text/html;charset=UTF-8");
        String msj;
        if (Asistente.updateAsistente(request)){
            msj = "Se modifico el registro.";
        } else {
            msj = "Error al modificar.";
        }
        String json = new Gson().toJson("Mensaje: "+msj);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    public JsonObject getConfirmation(){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject confirmationJson = builder.add("Result", 1).build();
        return confirmationJson;
    }
    
    private void updateAsistente(HttpServletRequest req){
        String id = req.getParameter("id");
        String name = req.getParameter("inputAsistantName");
        String last = req.getParameter("inputAsistantLast");
        String email = req.getParameter("inputAsistantEmail");
        
        String query = "UPDATE usuarios" +
                    " SET nombre = '" + name + "'," +
                    " apellido = '" + last + "'," +
                    " email = '" + email + "'" +
                    " WHERE id = '" + id + "';";
        
        try {
            this.connection.write(query);            
            this.connection.closeConnection();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
