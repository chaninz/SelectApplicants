/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.Question;
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
public class AddQuestion extends HttpServlet {

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
//            getServletContext().getRequestDispatcher("/addQuestion.jsp").forward(request, response);
//            return;
//        }
        
        // Retrieve value from form in addQuestion.jsp.
        String questionId = request.getParameter("questionId");
        String pageNo = request.getParameter("pageNo");
        String question = request.getParameter("question");
        String fullMark = request.getParameter("fullMark");
        
        // Set default value to 0 (fails)
        String msg = "";
        int msgType = 0;
        // Check if value complete.
        if (!Util.isFieldEmpty(questionId)
                && !Util.isFieldEmpty(pageNo)
                && !Util.isFieldEmpty(question)
                && !Util.isFieldEmpty(fullMark)) {
            
            question = Util.toUTF8(question);
            
            // Add question to database.
            int result = Question.addQuestion(Integer.parseInt(questionId), Integer.parseInt(pageNo), question, Integer.parseInt(fullMark));
            switch (result) {
                case -1 :
                    msg = Util.QUESTION_EXIST;
                    break;
                case 0 :
                    msg = "Error";
                    break;
                case 1 :
                    msg = Util.ADD_QUESTION_SUCCESS;
                    msgType = 1;
                    break;
            }
            
            request.setAttribute("question", question);
            request.setAttribute("msg", msg);
            request.setAttribute("msgType", msgType);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/addQuestion.jsp").forward(request, response);
        // If some field is incomplete.
        } else if (!request.getParameterNames().hasMoreElements()) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/addQuestion.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", Util.INCOMPLETED_FIELD);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/addQuestion.jsp").forward(request, response);
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
