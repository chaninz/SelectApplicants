/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.Applicant;
import com.sa.model.MarkSummary;
import com.sa.model.Question;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sa.util.Util;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CHANINZ
 */
public class ViewApplicant extends HttpServlet {

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

        // Check if has wipId and it is available.
        if (!Util.isFieldEmpty(wipId) && Util.isNumber(wipId)) {

            // Retrieve applicant from database with wipId
            Applicant applicant = Applicant.findById(Integer.parseInt(wipId));
            //List<Application> applications = Application.find("wip_id", wipId);
            List<Question> questions = Question.getQuestions();
            Map<Integer, MarkSummary> ms = MarkSummary.getSummaryById(Integer.parseInt(wipId));
            // If applicant is exist, forward applicant to display in viewApplicant.jsp.
            if (applicant != null && questions != null) {
                applicant.setImgThumb(getServletContext().getInitParameter("profileImagePath") + applicant.getImgThumb());
                request.setAttribute("sumFullMark", Question.getSumFullMark());
                request.setAttribute("ms", ms);
                request.setAttribute("applicant", applicant);
                request.setAttribute("questions", questions);
                request.setAttribute("pagePerApplication", getServletContext().getInitParameter("pagePerApplication"));
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/viewApplicant.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", Util.UNKNOW_APP);
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
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
