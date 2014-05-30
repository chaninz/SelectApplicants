/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.model;

import com.sa.util.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHANINZ
 */
public class MarkSummary {

    private int wipId;
    private double sumMark;
    private double avgMark;
    private Question question;
    private Overview overview;
    private Applicant applicant;

    public int getWipId() {
        return wipId;
    }

    public void setWipId(int wipId) {
        this.wipId = wipId;
    }

    public double getSumMark() {
        return sumMark;
    }

    public void setSumMark(double sumMark) {
        this.sumMark = sumMark;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Overview getOverview() {
        return overview;
    }

    public void setOverview(Overview overview) {
        this.overview = overview;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
    
    public static Map<Integer, MarkSummary> getSummaryById(int wipId) {
        Map<Integer, MarkSummary> result = new TreeMap<>();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT wip_id, questions.question_id, questions.question, questions.full_mark, AVG(mark) AS avg_mark "
                + "FROM marks JOIN questions ON marks.question_id = questions.question_id "
                + "WHERE wip_id = \"" + wipId + "\" GROUP BY wip_id, question_id");
        try {
            while (rs.next()) {
                MarkSummary m = new MarkSummary();
                toObjectAVG(rs, m);
                result.put(m.getQuestion().getQuestionId(), m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkSummary.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MarkSummary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static List<MarkSummary> getSummary(int start, int amount) {
        List<MarkSummary> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT avgmarks.wip_id, is_pass, reason, SUM(avg_mark) AS sum_mark "
                + "FROM (SELECT wip_id, question_id, AVG(mark) AS avg_mark FROM marks GROUP BY wip_id, question_id) AS avgmarks "
                + "JOIN overviews ON overviews.wip_id = avgmarks.wip_id "
                + "GROUP BY wip_id ORDER BY is_pass asc, sum_mark desc LIMIT " + amount + " OFFSET " + start);
        try {
            while (rs.next()) {
                MarkSummary m = new MarkSummary();
                toObjectSUM(rs, m);
                result.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkSummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static List<MarkSummary> getSummaryWithDetails(int isPass) {
        // pass = 1, alternate = 2, fail = 3, all = 0
        List<MarkSummary> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT avgmarks.wip_id, is_pass, reason, SUM( avg_mark ) AS sum_mark, firstname_th, lastname_th, nickname, school_name, mobile_tel, religion "
                + "FROM (SELECT wip_id, question_id, AVG(mark) AS avg_mark FROM marks GROUP BY wip_id, question_id) AS avgmarks "
                + "JOIN overviews ON overviews.wip_id = avgmarks.wip_id "
                + "JOIN applicants ON applicants.wip_id = overviews.wip_id "
                + (isPass == 0 ? "" : "WHERE is_pass = " + isPass + " ")
                + "GROUP BY wip_id ORDER BY is_pass asc, sum_mark desc");
        try {
            while (rs.next()) {
                MarkSummary m = new MarkSummary();
                Overview o = new Overview();
                Applicant a = new Applicant();
                m.setWipId(rs.getInt("wip_id"));
                m.setSumMark(rs.getDouble("sum_mark"));
                o.setIsPass(rs.getInt("is_pass"));
                o.setReason(rs.getString("reason"));
                a.setFirstnameTh(rs.getString("firstname_th"));
                a.setLastnameTh(rs.getString("lastname_th"));
                a.setNickname(rs.getString("nickname"));
                a.setSchoolName(rs.getString("school_name"));
                a.setMobileTel(rs.getString("mobile_tel"));
                a.setReligion(rs.getString("religion"));
                m.setApplicant(a);
                m.setOverview(o);
                result.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkSummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static List<MarkSummary> getSummaryWithDetails() {
        return getSummaryWithDetails(0);
    }

    
    public static List<MarkSummary> getSummary(int isPass, int start, int amount) {
        // pass = 1, alternate = 2, fail = 3, all = 0
        List<MarkSummary> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT avgmarks.wip_id, is_pass, reason, SUM(avg_mark) AS sum_mark "
                + "FROM (SELECT wip_id, question_id, AVG(mark) AS avg_mark FROM marks GROUP BY wip_id, question_id) AS avgmarks "
                + "JOIN overviews ON overviews.wip_id = avgmarks.wip_id "
                + (isPass == 0 ? "" : "WHERE is_pass = " + isPass + " ")
                + "GROUP BY wip_id ORDER BY is_pass asc, sum_mark desc LIMIT " + amount + " OFFSET " + start);
        try {
            while (rs.next()) {
                MarkSummary m = new MarkSummary();
                toObjectSUM(rs, m);
                result.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkSummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int getSummarySize(int isPass) {
        // pass = 1, alternate = 2, fail = 3, all = 0
        //System.out.println("isPass = " + isPass);
        int result = 0;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT COUNT(*) FROM (SELECT COUNT(*), avgmarks.wip_id, is_pass, reason, SUM(avg_mark) AS sum_mark "
                + "FROM (SELECT wip_id, question_id, AVG(mark) AS avg_mark FROM marks GROUP BY wip_id, question_id) AS avgmarks "
                + "JOIN overviews ON overviews.wip_id = avgmarks.wip_id "
                + (isPass == 0 ? "" : "WHERE is_pass = " + isPass + " ")
                + "GROUP BY wip_id) AS summary");
        try {
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkSummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("result = " + result);
        return result;
    }

    private static void toObjectAVG(ResultSet rs, MarkSummary m) {
        Question q = new Question();
        try {
            m.setWipId(rs.getInt("wip_id"));
            m.setAvgMark(rs.getDouble("avg_mark"));
            q.setQuestionId(rs.getInt("question_id"));
            q.setQuestion(rs.getString("question"));
            q.setFullMark(rs.getInt("full_mark"));
            m.setQuestion(q);
        } catch (SQLException ex) {
        }
    }

    private static void toObjectSUM(ResultSet rs, MarkSummary m) {
        Overview o = new Overview();
        try {
            m.setWipId(rs.getInt("wip_id"));
            m.setSumMark(rs.getDouble("sum_mark"));
            o.setIsPass(rs.getInt("is_pass"));
            o.setReason(rs.getString("reason"));
            m.setOverview(o);
        } catch (SQLException ex) {
        }
    }

    @Override
    public String toString() {
        return "MarkSummary{" + "wipId=" + wipId + ", sumMark=" + sumMark + ", avgMark=" + avgMark + ", question=" + question + ", overview=" + overview + '}';
    }
}
