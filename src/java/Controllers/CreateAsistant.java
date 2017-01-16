/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
@WebServlet(name = "CreateAsistant", urlPatterns = {"/CreateAsistant"})
public class CreateAsistant extends HttpServlet {
    private MySQLAccess connection;
    private int lastWrite;
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
        
        this.connection = new MySQLAccess();
        this.connection.connection();
        this.createAsistant(request);
        
        JsonObject json = getConfirmation();
        PrintWriter writer = response.getWriter();
        writer.print(json);
    }
    
    public JsonObject getConfirmation(){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject confirmationJson = builder.add("Result", 1).build();
        return confirmationJson;
    }
    
    private boolean createAsistant(HttpServletRequest req){
        String id = req.getParameter("inputAsistantId");
        String name = req.getParameter("inputAsistantName");
        String last = req.getParameter("inputAsistantLast");
        String email = req.getParameter("inputAsistantEmail");
        String confId = req.getParameter("inputConferenciaId");
        try {
            this.connection.write("INSERT INTO usuarios (id,nombre,apellido,email) VALUES ('" + id + "','" + name + "','" + last + "','" + email + "');");
            this.connection.write("INSERT INTO confasist (conf_id,asist_id) VALUES ('" + confId + "','" + id + "');");
            this.connection.closeConnection();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
