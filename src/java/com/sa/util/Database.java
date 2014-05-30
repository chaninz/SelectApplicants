/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sa.model.ConnectionAgent;

/**
 *
 * @author CHANINZ
 */
public class Database {

//    public static ResultSet query(String sql) {
//        ResultSet rs = null;
//        try {
//            Connection con = ConnectionAgent.connect();
//            PreparedStatement stm = con.prepareStatement(sql);
//            rs = stm.executeQuery();
//        } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return rs;
//    }

        public static ResultSet query(Connection con, String sql) {
        ResultSet rs = null;
        try {
            //con = ConnectionAgent.connect();
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public static int update(Connection con, String sql) {
        int result = 0;
        //Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            result = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
