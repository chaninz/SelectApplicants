/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sa.model.User;
import com.sa.util.Util;

/**
 *
 * @author CHANINZ
 */
public class Login extends HttpServlet {

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
        HttpSession session = request.getSession();

        // Retrieve value from form in login.jsp.
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String reqUri = request.getParameter("reqUri");
        String remember = request.getParameter("remember");
        request.setAttribute("reqUri", reqUri);

        // Check if user object is available in session.
        if (Util.getCurrentUser(request) != null) {
            response.sendRedirect("Home");
            return;
        }
        /*if (Util.getCurrentUser(request) != null) {
         response.sendRedirect("Home");
        
         // Check if fields are empty.
         } else */
        if (!Util.isFieldEmpty(email)
                && !Util.isFieldEmpty(password)) {

            User user = User.login(email, password);

            // If receives user from login, put it into session.
            if (user != null) {
                reqUri = reqUri.isEmpty() ? "Home" : reqUri.substring(reqUri.indexOf("/") + 1);
                session.setAttribute("user", user);
                if (remember != null) {
                    Util.addCookie("user_id", user.getUserId() + "", 60 * 60 * 24 * 30 * 365, response);
                }
                response.sendRedirect(reqUri);
            } else {
                request.setAttribute("msg", Util.INVALID_USER_PASS);
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            }

        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
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
