/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.Application;
import com.sa.model.Mark;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sa.util.Util;

/**
 *
 * @author CHANINZ
 */
public class ViewMark extends HttpServlet {

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

        // Retrieve value from form in query string.
        String wipId = request.getParameter("wipId");
        String questionId = request.getParameter("question");
        String userId = Integer.toString(Util.getCurrentUser(request).getUserId());
        
        // Check if has wipId, question, userId and it is available.
        if (!Util.isFieldEmpty(wipId)
                && Util.isNumber(wipId)
                && !Util.isFieldEmpty(questionId)
                && Util.isNumber(questionId)
                && !Util.isFieldEmpty(userId)
                && Util.isNumber(userId)) {

            // Retrieve marks from database with wipId and question
            List<Mark> marks = Mark.findByQuestionAndWipId(Integer.parseInt(wipId), Integer.parseInt(questionId));
            Mark myMark = Mark.findByUser(Integer.parseInt(wipId), Integer.parseInt(questionId), Integer.parseInt(userId));
            Application application = Application.findById(Integer.parseInt(wipId), Integer.parseInt(questionId));
            
            request.setAttribute("application", application);
            request.setAttribute("marks", marks);
            request.setAttribute("myMark", myMark);
            // If marks is exist, forward marks to display in viewMark.jsp.
            if (marks != null) {
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewMark.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewMark.jsp").forward(request, response);
            }

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
