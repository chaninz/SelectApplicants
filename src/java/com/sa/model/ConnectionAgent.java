/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHANINZ
 */
public class ConnectionAgent {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //private static final String URL = "jdbc:mysql://localhost:3306/chanin_selectapp?useUnicode=true&characterEncoding=utf-8";
    private static final String URL = "jdbc:mysql://server.chaninz.com:3306/chanin_selectapp?useUnicode=true&characterEncoding=utf-8";
    private static final String USERNAME = "chanin_selectapp";
    private static final String PASSWORD = "IrWqYyGfbrA";
    
    public static Connection connect() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionAgent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
