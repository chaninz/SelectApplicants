/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.User;
import com.sa.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CHANINZ
 */
public class EditProfile extends HttpServlet {

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

        // Retrieve value from form in editUser.jsp.
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String displayname = request.getParameter("displayname");

        // Retrieve current user from session
        User user = (User) Util.getSessionAttribute(request, "user");
        int userId = user.getUserId();
        int type = user.getType();

        // Set default value to 0 (fails)
        String msg = "";
        int msgType = 0;

        if (!Util.isFieldEmpty(email)
                && !Util.isFieldEmpty(password)
                && !Util.isFieldEmpty(confirmPassword)
                && !Util.isFieldEmpty(displayname)) {

            User editedUser = new User(userId, email, password, displayname, type);

            switch (User.isExists(email, displayname, userId)) {
                case 0:
                    if (password.equals(confirmPassword)) {

                        // Update user to database.
                        user.editUser();
                        request.getSession().setAttribute("user", editedUser);
                        msg = Util.EDIT_USER_SUCCESS;
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

            request.setAttribute("editedUser", user);
            request.setAttribute("msg", msg);
            request.setAttribute("msgType", msgType);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp").forward(request, response);
            // Retrieve current from session then forward to editUser.jsp
        } else if (Util.isFieldEmpty(email)
                && Util.isFieldEmpty(password)
                && Util.isFieldEmpty(confirmPassword)
                && Util.isFieldEmpty(displayname)) {

            // If user is exist, forward user to display in editUser.jsp.
                //request.setAttribute("editedUser", user);
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp").forward(request, response);

            // If some field is incomplete.
        } else {
            request.setAttribute("msg", Util.INCOMPLETED_FIELD);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp").forward(request, response);
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
