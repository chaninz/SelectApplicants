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
public class AddUser extends HttpServlet {

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

//        /*// Check if user object is available in session.
//         if (Util.getCurrentUser(request) == null) {
//         request.setAttribute("msg", "Please Login");
//         getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
//         return;
//         // Check if has not value from query string.
//         } else */ if (!request.getParameterNames().hasMoreElements()) {
//            getServletContext().getRequestDispatcher("/addUser.jsp").forward(request, response);
//            return;
//        }

        // Retrieve value from form in addUser.jsp.
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String displayname = request.getParameter("displayname");
        String type = request.getParameter("type");
        // Set default value to 0 (fails)
        int msgType = 0;
        String msg = "";
        // Check if value complete.
        if (!Util.isFieldEmpty(email)
                && !Util.isFieldEmpty(password)
                && !Util.isFieldEmpty(confirmPassword)
                && !Util.isFieldEmpty(displayname)
                && !Util.isFieldEmpty(type)) {

            switch (User.isExists(email, displayname)) {
                case 0:
                    if (password.equals(confirmPassword)) {
                        // Add user to database.
                        User.addUser(email, password, displayname, Integer.parseInt(type));
                        msg = Util.ADD_USER_SUCCESS;
                        msgType = 1;
                    } else {
                        msg = Util.PASSWORDS_NOT_MATCH;
                    }
                    break;
                case 1:
                    msg = Util.EMAIL_EXIST;
                    break;
                case 2:
                    msg = Util.DISPLAYNAME_EXIST;
                    break;
                case 3:
                    msg = Util.EMAIL_DISPLAYNAME_EXIST;
                    break;
            }
            request.setAttribute("msgType", msgType);
            request.setAttribute("msg", msg);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(request, response);
            // If some field is incomplete.
        } else if (!request.getParameterNames().hasMoreElements()) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", Util.INCOMPLETED_FIELD);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(request, response);
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
