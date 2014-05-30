/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.servlet;

import com.sa.model.Application;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.sa.util.Util;

/**
 *
 * @author CHANINZ
 */
public class UploadApplication extends HttpServlet {

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

        String wipId = request.getParameter("wipId");
        String page = request.getParameter("page");
        String msg;
        String url;
        String answer = request.getParameter("answer");

//         if (Util.isFieldEmpty(wipId)
//                    && !Util.isFieldEmpty(page)) {
//             
//             getServletContext().getRequestDispatcher("/uploadApplication.jsp").forward(request, response);
//             return;
//         }

        String filePath = getServletContext().getInitParameter("applicationUploadPath");
        File file;
        int maxFileSize = 5 * 1024 * 1024;
        int maxMemSize = 5 * 1024 * 1024;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(filePath + "temp\\"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the parameter.
            Iterator i = fileItems.iterator();

            // Loop for get parameter and assign to variable wipId and page.
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    if (fi.getFieldName().equals("wipId")) {
                        wipId = fi.getString();
                    }
                    if (fi.getFieldName().equals("page")) {
                        page = fi.getString();
                    }
                    if (fi.getFieldName().equals("answer")) {
                        answer = fi.getString();
                    }
                }
            }

            // Check if value complete.
            if (!Util.isFieldEmpty(wipId)
                    && Util.isNumber(wipId)
                    && !Util.isFieldEmpty(page)
                    && Util.isNumber(page)
                    && !Util.isFieldEmpty(answer)) {

                // Process the uploaded file items
                Iterator j = fileItems.iterator();

                while (j.hasNext()) {
                    FileItem fi = (FileItem) j.next();
                    if (!fi.isFormField()) {
                        // Get the uploaded file parameters
                        if (fi.getName().length() > 0) {
                            String fileExtension = fi.getName().substring(fi.getName().lastIndexOf(".")).toLowerCase();

                            // Check if uploaded file is a picture.
                            if (!fileExtension.equalsIgnoreCase(".png")
                                    && !fileExtension.equalsIgnoreCase(".gif")
                                    && !fileExtension.equalsIgnoreCase(".jpg")
                                    && !fileExtension.equalsIgnoreCase(".jpeg")) {
                                msg = "Your file muse be .png, .gif, .jpg, or .jpeg only!";
                                request.setAttribute("msg", msg);
                                url = "/error.jsp";
                                getServletContext().getRequestDispatcher(url).forward(request, response);
                                return;
                            }

                            // Name of uploaded file
                            String fileName = wipId + "_" + page + fileExtension;

                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();

                            String absoluteDiskPath = getServletContext().getRealPath(filePath);

                            // Write the file
                            file = new File(absoluteDiskPath, fileName);
                            fi.write(file);
                            
                            // Add file name and details of file to database.
                            Application.addApplication(Integer.parseInt(wipId), Integer.parseInt(page), fileName, answer);
                        }
                    }
                }

                // If some field is incomplete.
            } else {
                request.setAttribute("msg", "Incompleted field");
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            }

        } catch (FileUploadException ex) {
            request.setAttribute("msg", "File is oversized");
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadApplication.class.getName()).log(Level.SEVERE, null, ex);
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
