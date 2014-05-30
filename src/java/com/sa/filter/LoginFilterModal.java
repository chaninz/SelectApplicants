/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.filter;

import com.sa.util.Util;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CHANINZ
 */
public class LoginFilterModal implements Filter {

    private FilterConfig filterConfig = null;

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        /*StringBuilder reqUri = new StringBuilder();
        reqUri.append(req.getRequestURI().substring(req.getContextPath().length() + 1));
        reqUri.append(req.getQueryString() != null ? "?" + req.getQueryString() : "");*/
        
        String wipId = req.getParameter("wipId");
        String uri = /*req.getContextPath() + */"ViewApplicant?wipId=" + wipId;

        if (Util.getCurrentUser(req) == null) {
            //request.setAttribute("reqUri", reqUri);
            //request.setAttribute("msg", "Please sign-in before.");
            //filterConfig.getServletContext().getRequestDispatcher("/Login?reqUri=" + reqUri.toString()).forward(request, response);
            //request.setAttribute("wipId", wipId);
            request.setAttribute("uri", uri);
            filterConfig.getServletContext().getRequestDispatcher("WEB-INF/jsp/redirect.jsp").forward(request, response);
            //((HttpServletResponse)response).sendRedirect("redirect.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
