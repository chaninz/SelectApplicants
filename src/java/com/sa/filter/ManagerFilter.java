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

/**
 *
 * @author CHANINZ
 */
public class ManagerFilter implements Filter {
    
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
        
        if (!(Util.getCurrentUser(req).getType() == 1 || Util.getCurrentUser(req).getType() == 2)) {
            request.setAttribute("msg", "You are not an admin or manager.");
            filterConfig.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
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
