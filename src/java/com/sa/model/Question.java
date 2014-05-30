/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.model;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
public class Question {

    private int questionId;
    private int pageNo;
    private String question;
    private int fullMark;

    public Question() {
    }

    public Question(int questionId, int pageNo, String question, int fullMark) {
        this.questionId = questionId;
        this.pageNo = pageNo;
        this.question = question;
        this.fullMark = fullMark;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getFullMark() {
        return fullMark;
    }

    public void setFullMark(int fullMark) {
        this.fullMark = fullMark;
    }

    public static int addQuestion(int questionId, int pageNo, String question, int fullMark) {
        int result = 0;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO questions (question_id, page_no, question, full_mark) "
                    + "VALUES (?, ?, ?, ?)");
            ps.setInt(1, questionId);
            ps.setInt(2, pageNo);
            ps.setString(3, question);
            ps.setInt(4, fullMark);
            result = ps.executeUpdate() == 1 ? 1 : 0;
        } catch (MySQLIntegrityConstraintViolationException ex) {
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int editQuestion() {
        int result = 0;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE questions SET "
                    + "page_no = ?, question = ?, full_mark = ? WHERE question_id = ?");
            ps.setInt(1, pageNo);
            ps.setString(2, question);
            ps.setInt(3, fullMark);
            ps.setInt(4, questionId);
            result = ps.executeUpdate() == 1 ? 1 : 0;
        } catch (MySQLIntegrityConstraintViolationException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean deleteQuestion(int questionId) {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM questions WHERE question_id = ?");
            ps.setInt(1, questionId);
            result = ps.executeUpdate() == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static int getSumFullMark() {
        int result = 0;
        Connection con = ConnectionAgent.connect();
        try {
            ResultSet rs = Database.query(con, "SELECT SUM(full_mark) FROM questions");
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public static List<Integer> getQuestionIds() {
        List<Integer> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT question_id FROM questions");
        try {
            while (rs.next()) {
                result.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static int getAmountOfQuestion() {
        int result = 0;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT COUNT(*) FROM questions");
        try {
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public static List<Question> getQuestions() {
        List<Question> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM questions");
        try {
            while (rs.next()) {
                Question q = new Question();
                Question.toObject(rs, q);
                result.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public static List<Question> find(String columnName, String keyword) {
        List<Question> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM questions WHERE " + columnName + " LIKE \"" + keyword + "\"");
        try {
            while (rs.next()) {
                Question q = new Question();
                Question.toObject(rs, q);
                result.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static Question findById(int questionId) {
        Question result = null;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM questions WHERE question_id = \"" + questionId + "\"");
        try {
            if (rs.next()) {
                result = new Question();
                toObject(rs, result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    private static void toObject(ResultSet rs, Question q) {
        try {
            q.setQuestionId(rs.getInt("question_id"));
            q.setPageNo(rs.getInt("page_no"));
            q.setQuestion(rs.getString("question"));
            q.setFullMark(rs.getInt("full_mark"));
        } catch (SQLException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
