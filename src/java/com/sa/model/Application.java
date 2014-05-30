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
public class Application {

    private int wipId;
    private int page;
    private String url;
    private String answer;
    private Question question;

    public int getWipId() {
        return wipId;
    }

    public void setWipId(int wipId) {
        this.wipId = wipId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public static boolean addApplication(int wipId, int page, String url, String answer) {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO applications (wip_id, page, url, answer) "
                    + "VALUES (?, ?, ?, ?)");
            ps.setInt(1, wipId);
            ps.setInt(2, page);
            ps.setString(3, url);
            ps.setString(4, answer);
            result = ps.executeUpdate() == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean deleteApplication(int wipId, int page) {
        boolean result;
        Connection con = ConnectionAgent.connect();
        int i = Database.update(con, "DELETE FROM applications WHERE wip_id = \"" + wipId + "\""
                + "AND page = \"" + page + "\"");
        result = i == 0 ? false : true;
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static boolean editApplication(int wipId, int page, String url) {
        boolean result;
        Connection con = ConnectionAgent.connect();
        int i = Database.update(con, "UPDATE applications SET wip_id = \"" + wipId + "\", page = \"" + page + "\", "
                + "url = \"" + url + "\" WHERE wip_id = + \"" + wipId + "\"");
        result = i == 0 ? false : true;
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean isApplicationExists(int wipId, int questionNo) {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT url from applications WHERE wip_id = \"" + wipId + "\" AND "
                + "question_no = \"" + questionNo + "\"");
        try {
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public static List<Application> find(String columnName, String keyword) {
        List<Application> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM applications WHERE " + columnName + " LIKE \"" + keyword + "\"");
        try {
            while (rs.next()) {
                Application a = new Application();
                toObject(rs, a);
                result.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public static Application findById(int wipId, int questionId) {
        Application result = null;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM applications JOIN questions ON "
                + "applications.question_id = questions.question_id WHERE wip_id = \"" + wipId + "\" "
                + "AND questions.question_id = \"" + questionId + "\"");
        try {
            if (rs.next()) {
                result = new Application();
                toObject(rs, result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
//    public static List<Integer> getQuestionIdsByWipId(int wipId) {
//        List<Integer> result = new ArrayList();
//        Connection con = ConnectionAgent.connect();
//        ResultSet rs = Database.query(con, "SELECT question_id FROM questions");
//        try {
//            while (rs.next()) {
//                result.add(rs.getInt(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            // close connection
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return result;
//    }
    
    private static void toObject(ResultSet rs, Application a) {
        Question q = new Question();
        try {
            a.setWipId(rs.getInt("wip_id"));
            //a.setPage(rs.getInt("page"));
            //a.setUrl(rs.getString("url"));
            a.setAnswer(rs.getString("answer"));
            q.setQuestion(rs.getString("question"));
            q.setQuestionId(rs.getInt("question_id"));
            q.setFullMark(rs.getInt("full_mark"));
            a.setQuestion(q);
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString() {
        return "Application{" + "wipId=" + wipId + ", page=" + page + ", url=" + url + '}';
    }
}
