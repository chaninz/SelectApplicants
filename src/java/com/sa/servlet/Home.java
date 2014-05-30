/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.Applicant;
import com.sa.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sa.util.Util;

/**
 *
 * @author CHANINZ
 */
public class Home extends HttpServlet {

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
//        HttpSession session = request.getSession();
//        if (session.getAttribute("user") == null) {
//            //getServletContext().getRequestDispatcher("/Login").forward(request, response);
//            response.sendRedirect("Login");
//            return;
//        }

        // Retrieve value from form in query string.
        String page = request.getParameter("page");
        int start = 0;
        int amount = Integer.parseInt(getServletContext().getInitParameter("applicantsPerPage"));
        
        String filter = request.getParameter("filter");
        int filterNum = 0;
        
        // Retrieve user id form current user in session.
        int userId = ((User) Util.getSessionAttribute(request, "user")).getUserId();

        // Check if has page and it is available.
        if (!Util.isFieldEmpty(page) && Util.isNumber(page)) {
            start = Integer.parseInt(page) * amount - amount;
        }

        List<Applicant> appList = null;

        // Check if has filter and it is available.
        if (!Util.isFieldEmpty(filter)) {

            if (filter.equalsIgnoreCase("checked")) {
                filterNum = 1;
                appList = Applicant.getApplicantsWithStatus(filterNum, userId, start, amount);
            } else if (filter.equalsIgnoreCase("unchecked")) {
                filterNum = 2;
                appList = Applicant.getApplicantsWithStatus(filterNum, userId, start, amount);
            } else {
                appList = Applicant.getApplicantsWithStatus(userId, start, amount);
            }
        } else {
            appList = Applicant.getApplicantsWithStatus(userId, start, amount);
        }
        
         // Retrieve size of applicants list
        int appListSize = Applicant.getSize(filterNum, userId);
        
        request.setAttribute("appList", appList);
        request.setAttribute("appListSize", appListSize);
        request.setAttribute("currentPage", page);
        request.setAttribute("amountOfPages", (int) Math.ceil(appListSize / (amount * 1.0)));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
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
