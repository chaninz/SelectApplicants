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
public class EditQuestion extends HttpServlet {

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

        // Retrieve value from form in editQuestion.jsp.
        String questionId = request.getParameter("questionId");
        String pageNo = request.getParameter("pageNo");
        String qst = request.getParameter("question");
        String fullMark = request.getParameter("fullMark");
        
        // Set default value to 0 (fails)
        String msg = "";
        int msgType = 0;
        // Check if value complete.
        if (!Util.isFieldEmpty(questionId) 
                && Util.isNumber(questionId)
                && !Util.isFieldEmpty(pageNo)
                && !Util.isFieldEmpty(qst)
                && !Util.isFieldEmpty(fullMark)) {

            qst = Util.toUTF8(qst);
            
            // Update user to database.
            Question editedQuestion = new Question(Integer.parseInt(questionId), Integer.parseInt(pageNo), qst, Integer.parseInt(fullMark));
            int result = editedQuestion.editQuestion();
            switch (result) {
                case -1 :
                    msg = Util.QUESTION_EXIST;
                    break;
                case 0 :
                    msg = "Error";
                    break;
                case 1 :
                    msg = Util.EDIT_QUESTION_SUCCESS;
                    msgType = 1;
                    break;
            }
            
            request.setAttribute("editedQuestion", editedQuestion);
            request.setAttribute("msg", msg);
            request.setAttribute("msgType", msgType);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/editQuestion.jsp").forward(request, response);
        // Check if has questionId and it is available.
        } else if (!Util.isFieldEmpty(questionId) && Util.isNumber(questionId)) {

            // Retrieve user from database with questionId
            Question editedQuestion = Question.findById(Integer.parseInt(questionId));

            // If user is exist, forward user to display in editQuestion.jsp.
            if (editedQuestion != null) {
                request.setAttribute("editedQuestion", editedQuestion);
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/editQuestion.jsp").forward(request, response);
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
