/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sa.util.Database;

/**
 *
 * @author CHANINZ
 */
public class Overview {

    private int wipId;
    private int isPass;
    private String reason;
    private int userId;
    private String timestamp;
    private java.sql.Timestamp timestamp1;
    private Applicant applicant;
    private User user;

    public int getWipId() {
        return wipId;
    }

    public void setWipId(int wipId) {
        this.wipId = wipId;
    }

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public java.sql.Timestamp getTimestamp1() {
        return timestamp1;
    }

    public void setTimestamp1(java.sql.Timestamp timestamp1) {
        this.timestamp1 = timestamp1;
    }
    
    
    
    public static boolean addOverview(int wipId, int isPass, String reason, int userId) {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO overviews (wip_id, "
                    + "is_pass, reason, user_id) VALUES (?, ?, ?, ?)");
            ps.setInt(1, wipId);
            ps.setInt(2, isPass);
            ps.setString(3, reason);
            ps.setInt(4, userId);
            result = ps.executeUpdate() == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean deleteOverview(int wipId) {
        boolean result;
        Connection con = ConnectionAgent.connect();
        int i = Database.update(con, "DELETE FROM overviews WHERE wip_id = \"" + wipId + "\"");
        result = i == 0 ? false : true;
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean editOverview() {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE overviews SET is_pass = ?, reason = ? ,"
                    + "user_id = ? WHERE wip_id = ?");
            ps.setInt(1, isPass);
            ps.setString(2, reason);
            ps.setInt(3, userId);
            ps.setInt(4, wipId);
            result = ps.executeUpdate() == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public static Overview findById(int wipId) {
        Overview result = null;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM overviews JOIN users ON "
                + "users.user_id = overviews.user_id WHERE overviews.wip_id = \"" + wipId + "\"");
        try {
            if (rs.next()) {
                result = new Overview();
                toObject(rs, result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    private static void toObject(ResultSet rs, Overview o) {
        User u = new User();
        try {
            o.setWipId(rs.getInt("wip_id"));
            o.setIsPass(rs.getInt("is_pass"));
            o.setReason(rs.getString("reason"));
            u.setUserId(rs.getInt("user_id"));
            u.setDisplayname(rs.getString("displayname"));
            o.setTimestamp(rs.getString("timestamp"));
            o.setTimestamp1(rs.getTimestamp("timestamp"));
            o.setUser(u);
        } catch (SQLException ex) {
            Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString() {
        return "Overview{" + "wipId=" + wipId + ", isPass=" + isPass + ", reason=" + reason + ", userId=" + userId + ", timestamp=" + timestamp + '}';
    }
}
