/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import Models.Conferencia;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ivan Mera
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
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
        /*Conferencia c = new Conferencia();
        ArrayList<Conferencia> listaConferencias = c.getConferencias();
        request.setAttribute("conferencias", listaConferencias);
        request.getRequestDispatcher("conferencias.jsp").forward(request, response);
        */
        
        //Conexion a la base de datos
        this.connection = new MySQLAccess();
        this.connection.connection();
               
        //Query para guardar el usuario en la DB
        if(request.getParameter("inputAction").compareTo("1") == 0){
            createConferencia(request);
        }

        
        if(request.getParameter("inputAction").compareTo("2") == 0){
            deleteConferencia(request);
            String id = request.getParameter("id");
            System.out.println("\n\n\n" + id + "\n\n\n");
        }
        
        //Redireccionamiento
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("conferencias.jsp");
    }
    
    //Funcion para crear conferencias
    private void createConferencia(HttpServletRequest req){
        String nombre = req.getParameter("inputNombreConferencia");
        String fecha = req.getParameter("inputDateConferencia");
        String desc = req.getParameter("inputDescConferencia");
        try {
            this.connection.write("INSERT INTO conferencias (nombre,descripcion,fecha) VALUES ('" + nombre + "','" + desc + "','" + fecha + "');");
            this.connection.closeConnection();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void readConferencia(HttpServletRequest req){
//        String q = "SELECT * FROM conferencias";
//        ArrayList<Conferencia> listConferencias = new ArrayList();
//        try{
//            this.connection.write(q);
//            ResultSet rs = connection.query(q);
//            while(rs.next()){
//                Conferencia conf = new Conferencia(rs.getInt("id")  ,rs.getString("fecha"), rs.getString("nombre"), rs.getString("descripcion"));
//                listConferencias.add(conf);
//            }
//            this.connection.closeConnection();
//        } catch (SQLException e) {
//            Logger.getLogger(Conferencia.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return listConferencias;
    req.setAttribute("conferencias", Conferencia.getConferencias());
    }
    
    private void deleteConferencia(HttpServletRequest req){
        String id = req.getParameter("id");
        String query = "DELETE FROM conferencias WHERE id = " + id + ";";
        try {
            this.connection.write(query);
            this.connection.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
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
