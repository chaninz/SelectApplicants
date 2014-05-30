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
public class Mark {

    private int wipId;
    private int questionId;
    private int userId;
    private int mark;
    private String reason;
    private java.sql.Timestamp timestamp;
    private User user;

    public int getWipId() {
        return wipId;
    }

    public void setWipId(int wipId) {
        this.wipId = wipId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.sql.Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public static int addMark(int wipId, int questionId, int userId, int mark, String reason) {
        int result = 0;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO marks (wip_id, question_id, "
                    + "user_id, mark, reason) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, wipId);
            ps.setInt(2, questionId);
            ps.setInt(3, userId);
            ps.setInt(4, mark);
            ps.setString(5, reason);
            result = ps.executeUpdate() == 1 ? 1 : 0;
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            result = -1;
        } catch (SQLException ex) {
            Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean deleteMark(int wipId, int questionId) {
        boolean result;
        Connection con = ConnectionAgent.connect();
        int i = Database.update(con, "DELETE FROM applications WHERE wip_id = \"" + wipId + "\""
                + "AND question_id = \"" + questionId + "\"");
        result = i == 0 ? false : true;
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean editMark() {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE marks SET mark = ?, reason = ? "
                    + "WHERE wip_id = ? AND question_id = ? AND user_id = ?");
            ps.setInt(1, mark);
            ps.setString(2, reason);
            ps.setInt(3, wipId);
            ps.setInt(4, questionId);
            ps.setInt(5, userId);
            result = ps.executeUpdate() == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public static List<Mark> find(String columnName, String keyword) {
        List<Mark> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM marks JOIN users ON "
                + "users.user_id = marks.user_id WHERE " + columnName + " LIKE \"" + keyword + "\"");
        try {
            while (rs.next()) {
                Mark m = new Mark();
                Mark.toObject(rs, m);
                result.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public static List<Mark> findByQuestionAndWipId(int wipId, int questionId) {
        List<Mark> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM marks JOIN users ON "
                + "users.user_id = marks.user_id WHERE wip_id = \"" + wipId + "\" "
                + "AND question_id = \"" + questionId + "\" ORDER BY timestamp");
        try {
            while (rs.next()) {
                Mark m = new Mark();
                Mark.toObject(rs, m);
                result.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public static Mark findByUser(int wipId, int questionId, int userId) {
        Mark result = null;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM marks JOIN users ON "
                + "users.user_id = marks.user_id WHERE wip_id = \"" + wipId + "\" "
                + "AND question_id = \"" + questionId + "\" AND marks.user_id = \"" + userId + "\"");
        try {
            if (rs.next()) {
                result = new Mark();
                Mark.toObject(rs, result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    private static void toObject(ResultSet rs, Mark m) {
        User u = new User();
        try {
            m.setWipId(rs.getInt("wip_id"));
            m.setQuestionId(rs.getInt("question_id"));
            m.setUserId(rs.getInt("user_id"));
            m.setMark(rs.getInt("mark"));
            m.setReason(rs.getString("reason"));
            m.setTimestamp(rs.getTimestamp("timestamp"));
            
            u.setUserId(rs.getInt("user_id"));
            u.setDisplayname(rs.getString("displayname"));
            
            m.setUser(u);
        } catch (SQLException ex) {
            Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString() {
        return "Mark{" + "wipId=" + wipId + ", questionId=" + questionId + ", userId=" + userId + ", mark=" + mark + ", reason=" + reason + ", timestamp=" + timestamp + '}';
    }
}
