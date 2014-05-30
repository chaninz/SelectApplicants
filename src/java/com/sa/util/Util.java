/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.util;

import com.sa.model.User;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CHANINZ
 */
public class Util {
    
    public static String ADD_USER_SUCCESS = "Add user successful.";
    public static String ADD_QUESTION_SUCCESS = "Add question successful.";
    public static String EDIT_QUESTION_SUCCESS = "Edit question successful.";
    public static String EDIT_USER_SUCCESS = "Update user successful.";
    public static String EDIT_MARK_SUCCESS = "Edit mark successful.";
    public static String EDIT_OVERVIEW_SUCCESS = "Edit overview successful.";
    public static String EMAIL_EXIST = "Email is exist.";
    public static String EMAIL_DISPLAYNAME_EXIST = "Email and Display name are exist.";
    public static String DISPLAYNAME_EXIST = "Display name is exist.";
    public static String INCOMPLETED_FIELD = "Incompleted field";
    public static String INVALID_USER_PASS = "Invalid username or password";
    public static String PASSWORDS_NOT_MATCH = "These passwords don't match.";
    public static String QUESTION_EXIST = "Question no is exist.";
    public static String REGIS_CLOSED = "Registration Closed";
    public static String REGIS_SUCCESS = "Register successful.";
    public static String UNKNOW_APP = "Unknown applicant";
    public static String UNKNOW_USER = "Unknown user";
    
    // Check for is field empty
    public static boolean isFieldEmpty(String field) {
        boolean result = false;
        if (field == null || field.isEmpty()) {
            result = true;
        }
        return result;
    }

    public static boolean isNumber(String field) {
        boolean result = true;
        try {
            Integer.parseInt(field);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    
    public static Object getSessionAttribute(HttpServletRequest request, String name) {
        Object result = null;
        HttpSession session = request.getSession();
        result = session.getAttribute(name);
        return result;
    }

    public static User getCurrentUser(HttpServletRequest request) {
        User result = null;
        if (Util.getSessionAttribute(request, "user") != null) {
            User u = (User)getSessionAttribute(request, "user");
            result = User.findById(u.getUserId());    
        } else if (Util.getCookieValue("user_id", request) != null) {
            result = User.findById(Integer.parseInt(Util.getCookieValue("user_id", request)));
            HttpSession session = request.getSession();
            session.setAttribute("user", result);
        }
        return result;
    }
    
    private static boolean hasCookies(HttpServletRequest request) {
        boolean hasCookies = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            hasCookies = true;
        return hasCookies;
    }
    
    public static String getCookieValue(String name, HttpServletRequest request) {
        String value = null;
        if (!hasCookies(request))
            return null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(name)) {
                value = cookies[i].getValue();
            }
        }
        return value;
    }
    
    public static void addCookie(String name, String value, int age, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(age);
        response.addCookie(cookie);
    }
    
    public static String toUTF8(String text) {
        String result = null;
        try {
            result = new String(text.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException ex) {
            result = null;
        }
        return result;
    }
}
