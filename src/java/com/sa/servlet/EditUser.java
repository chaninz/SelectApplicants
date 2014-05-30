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
public class EditUser extends HttpServlet {

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
//        if (Util.getCurrentUser(request) == null) {
//            request.setAttribute("msg", "Please Login");
//            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
//            return;
//        }

        // Retrieve value from form in editUser.jsp.
        String userId = request.getParameter("userId");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String displayname = request.getParameter("displayname");
        String type = request.getParameter("type");

        // Set default value to 0 (fails)
        String msg = "";
        int msgType = 0;
        // Check if value complete.
        if (!Util.isFieldEmpty(userId)
                && Util.isNumber(userId)
                && !Util.isFieldEmpty(email)
                && !Util.isFieldEmpty(password)
                && !Util.isFieldEmpty(confirmPassword)
                && !Util.isFieldEmpty(displayname)
                && !Util.isFieldEmpty(type)
                && Util.isNumber(type)) {

            User editedUser = new User(Integer.parseInt(userId), email, password, displayname, Integer.parseInt(type));

            switch (User.isExists(email, displayname, Integer.parseInt(userId))) {
                case 0:
                    if (password.equals(confirmPassword)) {

                        // Update user to database.
                        //editedUser = new User(Integer.parseInt(userId), email, password, displayname, Integer.parseInt(type));
                        editedUser.editUser();
                        //if updated user and current are alike then update to session too.
                        User currentUser = Util.getCurrentUser(request);
                        if (currentUser.getUserId() == Integer.parseInt(userId)) {
                            request.getSession().setAttribute("user", editedUser);
                        }
                        
                        msg = "Update user successful.";
                        msgType = 1;
                    } else {
                        msg = "These passwords don't match.";
                    }
                    break;
                case 1:
                    msg = "Email is exist.";
                    break;
                case 2:
                    msg = "Display name is exist.";
                    break;
                case 3:
                    msg = "Email and Display name are exist.";
                    break;
            }

            // Logout user if updated user and current are alike.
//            User currentUser = Util.getCurrentUser(request);
//            if (currentUser.getUserId() == Integer.parseInt(userId)) {
//                //getServletContext().getRequestDispatcher("/Logout").forward(request, response);
//                request.getSession().setAttribute("user", editedUser);
//                return;
//            }

//            response.sendRedirect("ViewUsers");
//            request.setAttribute("user", editedUser);
            request.setAttribute("editedUser", editedUser);
            request.setAttribute("msg", msg);
            request.setAttribute("msgType", msgType);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(request, response);

            // Check if has userId and it is available.
        } else if (!Util.isFieldEmpty(userId)
                && Util.isFieldEmpty(email)
                && Util.isFieldEmpty(password)
                && Util.isFieldEmpty(confirmPassword)
                && Util.isFieldEmpty(displayname)
                && Util.isFieldEmpty(type)) {

            // Retrieve user from database with userId
            User user = User.findById(Integer.parseInt(userId));

            // If user is exist, forward user to display in editUser.jsp.
            if (user != null) {
                request.setAttribute("editedUser", user);
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", Util.UNKNOW_USER);
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }

            // If some field is incomplete.
        } else {
            request.setAttribute("msg", Util.INCOMPLETED_FIELD);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(request, response);
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
