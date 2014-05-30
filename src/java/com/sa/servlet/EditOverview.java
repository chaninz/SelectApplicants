/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.Overview;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sa.util.Util;

/**
 *
 * @author CHANINZ
 */
public class EditOverview extends HttpServlet {

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
//            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
//            return;
//        // Check if has not value from query string.
//        } else
//        if (!request.getParameterNames().hasMoreElements()) {
//            getServletContext().getRequestDispatcher("/WEB-INF/jsp/addOverview.jsp").forward(request, response);
//            return;
//        }
        
        // Retrieve value from form in viewOverview.jsp.
        String wipId = request.getParameter("wipId");
        String isPass = request.getParameter("isPass");
        String reason = request.getParameter("reason");
        //String userId = request.getParameter("userId");
        String userId = Integer.toString(Util.getCurrentUser(request).getUserId());
        
        if (!Util.isFieldEmpty(wipId)
                && Util.isNumber(wipId)
                && !Util.isFieldEmpty(isPass)
                && Util.isNumber(isPass)
                && !Util.isFieldEmpty(reason)
                && !Util.isFieldEmpty(userId)
                && Util.isNumber(userId)) {
            
            reason = Util.toUTF8(reason);
            
            Overview editedOverview = new Overview();
            editedOverview.setWipId(Integer.parseInt(wipId));
            editedOverview.setIsPass(Integer.parseInt(isPass));
            editedOverview.setReason(reason);
            editedOverview.setUserId(Integer.parseInt(userId));
            
            // Update overview to database.
            editedOverview.editOverview();
            //request.setAttribute("question", question);
            //response.sendRedirect("ViewOverview?wipId=" + wipId);
            request.setAttribute("msg", Util.EDIT_OVERVIEW_SUCCESS);
            request.setAttribute("msgType", "1");
            getServletContext().getRequestDispatcher("/ViewOverview?wipId=" + wipId).forward(request, response);
        // If some field is incomplete.
        } else {
            request.setAttribute("msg", Util.INCOMPLETED_FIELD);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
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
