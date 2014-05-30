/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sa.util.Util;

/**
 *
 * @author CHANINZ
 */
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Check if user object is available in session.
        if (Util.getCurrentUser(request) != null) {
            response.sendRedirect("Home");
            return;
        }
        
        if (getServletContext().getInitParameter("Register").equals("no")) {
            request.setAttribute("msg", Util.REGIS_CLOSED);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
        
        // Check if has not value from query string.
        if (!request.getParameterNames().hasMoreElements()) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
            return;
        }
        
        // Retrieve value from form in register.jsp.
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String displayName = request.getParameter("displayname");
        
        if (!Util.isFieldEmpty(email)
                && !Util.isFieldEmpty(password)
                && !Util.isFieldEmpty(confirmPassword)
                && !Util.isFieldEmpty(displayName)) {
            
            switch (User.isExists(email, displayName)) {
                case 0:
                    if (password.equals(confirmPassword)) {
                        // Add user to database.
                        User.addUser(email, password, displayName, Integer.parseInt(getServletContext().getInitParameter("defaultType")));
                        request.setAttribute("msg", Util.REGIS_SUCCESS);
                        request.setAttribute("msgType", 1);
                        //response.sendRedirect("Login");
                        //return;
                    } else {
                        request.setAttribute("msg", Util.PASSWORDS_NOT_MATCH);
                    }
                    break;
                case 1: request.setAttribute("msg", Util.EMAIL_EXIST);
                    break;
                case 2: request.setAttribute("msg", Util.DISPLAYNAME_EXIST);
                    break;
                case 3: request.setAttribute("msg", Util.EMAIL_DISPLAYNAME_EXIST);
                    break;
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
        // If some field is incomplete.
        } else {
            request.setAttribute("msg", Util.INCOMPLETED_FIELD);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
