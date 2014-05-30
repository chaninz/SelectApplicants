/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.Applicant;
import com.sa.model.Question;
import com.sa.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CHANINZ
 */
public class Progress extends HttpServlet {

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
//            response.sendRedirect("Login");
//            return;
//        }

        // Retrieve value from form in query string.
        String filter = request.getParameter("filter");
        int filterNum = 0;
        
        String page = request.getParameter("page");
        int start = 0;
        int amount = Integer.parseInt(getServletContext().getInitParameter("applicantsPerPage"));
        int amountOfQuestion = Question.getAmountOfQuestion();
        //1;
        List<Applicant> applicantsList = null;

        // Check if has page and it is available.
        if (!Util.isFieldEmpty(page) && Util.isNumber(page)) {
            start = Integer.parseInt(page) * amount - amount;
        }

        // Check if has filter and it is available.
        if (!Util.isFieldEmpty(filter)) {

            if (filter.equalsIgnoreCase("complete")) {
                filterNum = 1;
                applicantsList = Applicant.getApplicantsWithProgress(amountOfQuestion, filterNum, start, amount);
            } else if (filter.equalsIgnoreCase("incomplete")) {
                filterNum = 2;
                applicantsList = Applicant.getApplicantsWithProgress(amountOfQuestion, filterNum, start, amount);
            } else {
                applicantsList = Applicant.getApplicantsWithProgress(amountOfQuestion, start, amount);
            }
        } else {
            applicantsList = Applicant.getApplicantsWithProgress(amountOfQuestion, start, amount);
        }

        // Retrieve size of applicant list
        int applicantSize = Applicant.getApplicantsWithSize(amountOfQuestion, filterNum);
        
        // Forward result to summary.jsp
        request.setAttribute("currentPage", page);
        request.setAttribute("amountOfPages", (int) Math.ceil(applicantSize / (amount * 1.0)));
        request.setAttribute("applicantSize", applicantSize);
        request.setAttribute("applicantsList", applicantsList);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/progress.jsp").forward(request, response);
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
