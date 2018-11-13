/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jsp.mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ludovic
 */
public class ServletControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        String code = request.getParameter("code");
        String taux = request.getParameter("taux");
        String action = request.getParameter("action");
        String erreur = "";
        
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        
        // Si on demande un ajout
        if ("ADD".equals(action)) {
            try{
                dao.addDiscount_Code(code, Float.parseFloat(taux));
            }
            catch (Exception s){
                dao.updateDiscount_Code(code, Float.parseFloat(taux));
            }
        } 
        // Si on demande la suppression
        if ("DELETE".equals(action)) {
            try {
                dao.deleteDiscount_Code(code);
            }
            catch (Exception s){
               erreur = "Impossible de supprimer " + code + ", ce code est utilisé.";
            }
        } 
        
        List<Entity> Discount = dao.ListOfDiscount();
        
        request.setAttribute("Discounts", Discount);
        request.setAttribute("Error",erreur);
        
        request.getRequestDispatcher("Vue.jsp").forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletControl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletControl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
