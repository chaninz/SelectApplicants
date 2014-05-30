/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.Mark;
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
public class AddMark extends HttpServlet {

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
        
//        // Check if user object is available in session.
//        if (Util.getCurrentUser(request) == null) {
//            request.setAttribute("msg", "Please Login");
//            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
//            return;
//        // Check if has not value from query string.
//        } else if (!request.getParameterNames().hasMoreElements()) {
//            //getServletContext().getRequestDispatcher("/addOverview.jsp").forward(request, response);
//            return;
//        }
        
        // Retrieve value from a form in addOverview.jsp.
        String wipId = request.getParameter("wipId");
        String questionId = request.getParameter("question");
        String mark = request.getParameter("mark");
        String reason = request.getParameter("reason");
        String userId = Integer.toString(Util.getCurrentUser(request).getUserId());
        
        // Check if has wipId, questionId, mark, reason, userId and it is available.
        if (!Util.isFieldEmpty(wipId)
                && Util.isNumber(wipId)
                && !Util.isFieldEmpty(questionId)
                && Util.isNumber(questionId)
                && !Util.isFieldEmpty(mark)
                && Util.isNumber(mark)
                && !Util.isFieldEmpty(reason)
                && !Util.isFieldEmpty(userId)) {
            
            reason = Util.toUTF8(reason);
            //mark = mark.trim();
            // Add mark to database.
            int result = Mark.addMark(Integer.parseInt(wipId), Integer.parseInt(questionId), Integer.parseInt(userId), Integer.parseInt(mark), reason);
            
            //request.setAttribute("question", question);
            if (result == 1) {
                request.setAttribute("msg", "Add mark successful.");
                request.setAttribute("msgType", "1");
            } else if (result == -1 ){
                request.setAttribute("msg", "WIP ID or Question no is not available.");
                request.setAttribute("msgType", "-1");
            }
            
            getServletContext().getRequestDispatcher("/ViewMark?wipId=" + wipId + "&question=" + questionId).forward(request, response);
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
