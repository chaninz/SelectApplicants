/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.model;

import java.sql.Connection;
import com.sa.util.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHANINZ
 */
public class Applicant {

    private int wipId;
    private String citizenshipId;
    private String firstnameTh;
    private String lastnameTh;
    private String nickname;
    private String gender;
    private String dob;
    private String religion;
    private String tel;
    private String mobileTel;
    private String email;
    private String schoolName;
    private String educationLevel;
    private String educationPlan;
    private String gpax;
    private String congenitalDisease;
    private String allergicMedicine;
    private String allergicFood;
    private String allergicThing;
    private String computerSkill;
    private String activities;
    private String province;
    private String imgThumb;
    private String status;
    private String statusSec;

    public Applicant() {
    }

    public int getWipId() {
        return wipId;
    }

    public void setWipId(int wipId) {
        this.wipId = wipId;
    }

    public String getCitizenshipId() {
        return citizenshipId;
    }

    public void setCitizenshipId(String citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    public String getFirstnameTh() {
        return firstnameTh;
    }

    public void setFirstnameTh(String firstnameTh) {
        this.firstnameTh = firstnameTh;
    }

    public String getLastnameTh() {
        return lastnameTh;
    }

    public void setLastnameTh(String lastnameTh) {
        this.lastnameTh = lastnameTh;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getEducationPlan() {
        return educationPlan;
    }

    public void setEducationPlan(String educationPlan) {
        this.educationPlan = educationPlan;
    }

    public String getGpax() {
        return gpax;
    }

    public void setGpax(String gpax) {
        this.gpax = gpax;
    }

    public String getCongenitalDisease() {
        return congenitalDisease;
    }

    public void setCongenitalDisease(String congenitalDisease) {
        this.congenitalDisease = congenitalDisease;
    }

    public String getAllergicMedicine() {
        return allergicMedicine;
    }

    public void setAllergicMedicine(String allergicMedicine) {
        this.allergicMedicine = allergicMedicine;
    }

    public String getAllergicFood() {
        return allergicFood;
    }

    public void setAllergicFood(String allergicFood) {
        this.allergicFood = allergicFood;
    }

    public String getAllergicThing() {
        return allergicThing;
    }

    public void setAllergicThing(String allergicThing) {
        this.allergicThing = allergicThing;
    }

    public String getComputerSkill() {
        return computerSkill;
    }

    public void setComputerSkill(String computerSkill) {
        this.computerSkill = computerSkill;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }
    
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getImgThumb() {
        return imgThumb;
    }

    public void setImgThumb(String imgThumb) {
        this.imgThumb = imgThumb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusSec() {
        return statusSec;
    }

    public void setStatusSec(String statusSec) {
        this.statusSec = statusSec;
    }
    
    public static List<Applicant> find(String columnName, String keyword) {
        List<Applicant> result = new ArrayList<>();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM applicants WHERE " + columnName + " LIKE \"" + keyword + "\"");
        try {
            while (rs.next()) {
                Applicant a = new Applicant();
                Applicant.toObject(rs, a);
                result.add(a);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Applicant findById(int wipId) {
        Applicant result = null;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM applicants WHERE wip_id = \"" + wipId + "\"");
        try {
            if (rs.next()) {
                result = new Applicant();
                toObject(rs, result);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int getSize(int status, int userId) {
        // evaluated = 1, not yet = 2, all = 0
        int result = 0;
        Connection con = ConnectionAgent.connect();
        StringBuilder sql = new StringBuilder();

        if (status == 1) {
            sql.append("SELECT COUNT(*) FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"1\" AS status "
                    + "FROM applicants "
                    + "JOIN marks ON applicants.wip_id = marks.wip_id "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, applicants.wip_id) AS checked");
        } else if (status == 2) {
            sql.append("SELECT COUNT(*) FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"2\" AS status "
                    + "FROM applicants "
                    + "WHERE wip_id NOT IN ("
                    + "SELECT wip_id "
                    + "FROM marks "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, wip_id)) AS unchecked");
        } else {
            sql.append("SELECT COUNT(*) FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"1\" AS status "
                    + "FROM applicants "
                    + "JOIN marks ON applicants.wip_id = marks.wip_id "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, applicants.wip_id "
                    + "UNION ALL "
                    + "SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"1\" AS status "
                    + "FROM applicants "
                    + "WHERE wip_id NOT IN ("
                    + "SELECT wip_id "
                    + "FROM marks "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, wip_id)) AS allapp");
        }
        
        ResultSet rs = Database.query(con, sql.toString());

        try {
            if (rs.next()) {
                result = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
//    public static int getSize(int status) {
//        // evaluated = 1, not yet = 2, all = 0
//        int result = 0;
//        Connection con = ConnectionAgent.connect();
//        ResultSet rs = Database.query(con, "SELECT COUNT(*) FROM applicants");
//        try {
//            if (rs.next()) {
//                result = rs.getInt(1);
//            }
//            con.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }

    public static List<Applicant> getApplicants(int start, int amount) {
        List<Applicant> result = new ArrayList<>();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM applicants LIMIT " + amount + " OFFSET " + start);
        try {
            while (rs.next()) {
                Applicant a = new Applicant();
                Applicant.toObject(rs, a);
                result.add(a);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static List<Applicant> getApplicantsWithStatus(int status, int userId, int start, int amount) {
        // evaluated = 1, not yet = 2, all = 0
        List<Applicant> result = new ArrayList<>();
        Connection con = ConnectionAgent.connect();
        StringBuilder sql = new StringBuilder();

        if (status == 1) {
            sql.append("SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"1\" AS status "
                    + "FROM applicants "
                    + "JOIN marks ON applicants.wip_id = marks.wip_id "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, applicants.wip_id "
                    + "ORDER BY applicants.wip_id");
        } else if (status == 2) {
            sql.append("SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"2\" AS status "
                    + "FROM applicants "
                    + "WHERE wip_id NOT IN ("
                    + "SELECT wip_id "
                    + "FROM marks "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, wip_id "
                    + "ORDER BY applicants.wip_id)");
        } else {
            sql.append("SELECT * FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"1\" AS status "
                    + "FROM applicants "
                    + "JOIN marks ON applicants.wip_id = marks.wip_id "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, applicants.wip_id "
                    + "UNION ALL "
                    + "SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"2\" AS status "
                    + "FROM applicants "
                    + "WHERE wip_id NOT IN ("
                    + "SELECT wip_id "
                    + "FROM marks "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, wip_id)) AS allapp "
                    + "ORDER BY wip_id");
        }

        sql.append(" LIMIT " + amount + " OFFSET " + start);

        ResultSet rs = Database.query(con, sql.toString());
        try {
            while (rs.next()) {
                Applicant a = new Applicant();
                a.setWipId(rs.getInt("wip_id"));
                a.setFirstnameTh(rs.getString("firstname_th"));
                a.setLastnameTh(rs.getString("lastname_th"));
                a.setNickname(rs.getString("nickname"));
                a.setGender(rs.getString("gender").equalsIgnoreCase("m") ? "ชาย" : "หญิง");
                a.setEducationLevel(rs.getString("education_level"));
                a.setSchoolName(rs.getString("school_name"));
                a.setStatus(rs.getString("status"));
                result.add(a);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static List<Applicant> getApplicantsWithStatus(int userId, int start, int amount) {
        return getApplicantsWithStatus(0, userId, start, amount);
    }
    
    public static List<Applicant> searchApplicantsById(int wipId, int userId, int start, int amount) {
        List<Applicant> result = new ArrayList<>();
        Connection con = ConnectionAgent.connect();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"1\" AS status "
                    + "FROM applicants "
                    + "JOIN marks ON applicants.wip_id = marks.wip_id "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, applicants.wip_id "
                    + "UNION ALL "
                    + "SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"2\" AS status "
                    + "FROM applicants "
                    + "WHERE wip_id NOT IN ("
                    + "SELECT wip_id "
                    + "FROM marks "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, wip_id)) AS allapp "
                    + "WHERE wip_id = " + wipId + " "
                    + "ORDER BY wip_id");
        
        sql.append(" LIMIT " + amount + " OFFSET " + start);

        ResultSet rs = Database.query(con, sql.toString());
        try {
            while (rs.next()) {
                Applicant a = new Applicant();
                a.setWipId(rs.getInt("wip_id"));
                a.setFirstnameTh(rs.getString("firstname_th"));
                a.setLastnameTh(rs.getString("lastname_th"));
                a.setNickname(rs.getString("nickname"));
                a.setGender(rs.getString("gender").equalsIgnoreCase("m") ? "ชาย" : "หญิง");
                a.setEducationLevel(rs.getString("education_level"));
                a.setSchoolName(rs.getString("school_name"));
                a.setStatus(rs.getString("status"));
                result.add(a);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static int getSearchApplicantsByIdSize(int wipId, int userId) {
        int result = 0;
        Connection con = ConnectionAgent.connect();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"1\" AS status "
                    + "FROM applicants "
                    + "JOIN marks ON applicants.wip_id = marks.wip_id "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, applicants.wip_id "
                    + "UNION ALL "
                    + "SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"2\" AS status "
                    + "FROM applicants "
                    + "WHERE wip_id NOT IN ("
                    + "SELECT wip_id "
                    + "FROM marks "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, wip_id)) AS allapp "
                    + "WHERE wip_id = " + wipId + " "
                    + "ORDER BY wip_id");
        
        ResultSet rs = Database.query(con, sql.toString());

        try {
            if (rs.next()) {
                result = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static List<Applicant> searchApplicantsByKeyword(String keyword, int userId, int start, int amount) {
        List<Applicant> result = new ArrayList<>();
        Connection con = ConnectionAgent.connect();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"1\" AS status "
                    + "FROM applicants "
                    + "JOIN marks ON applicants.wip_id = marks.wip_id "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, applicants.wip_id "
                    + "UNION ALL "
                    + "SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"2\" AS status "
                    + "FROM applicants "
                    + "WHERE wip_id NOT IN ("
                    + "SELECT wip_id "
                    + "FROM marks "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, wip_id)) AS allapp "
                    + "WHERE firstname_th LIKE \"%" + keyword + "%\" OR lastname_th LIKE \"%" + keyword + "%\" OR nickname LIKE \"%" + keyword + "%\" OR school_name LIKE \"%" + keyword + "%\" "
                    + "ORDER BY wip_id");
        
        sql.append(" LIMIT " + amount + " OFFSET " + start);

        ResultSet rs = Database.query(con, sql.toString());
        try {
            while (rs.next()) {
                Applicant a = new Applicant();
                a.setWipId(rs.getInt("wip_id"));
                a.setFirstnameTh(rs.getString("firstname_th"));
                a.setLastnameTh(rs.getString("lastname_th"));
                a.setNickname(rs.getString("nickname"));
                a.setGender(rs.getString("gender").equalsIgnoreCase("m") ? "ชาย" : "หญิง");
                a.setEducationLevel(rs.getString("education_level"));
                a.setSchoolName(rs.getString("school_name"));
                a.setStatus(rs.getString("status"));
                result.add(a);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static int getSearchApplicantsByKeywordSize(String keyword, int userId) {
        int result = 0;
        Connection con = ConnectionAgent.connect();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"1\" AS status "
                    + "FROM applicants "
                    + "JOIN marks ON applicants.wip_id = marks.wip_id "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, applicants.wip_id "
                    + "UNION ALL "
                    + "SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, education_level, school_name, \"2\" AS status "
                    + "FROM applicants "
                    + "WHERE wip_id NOT IN ("
                    + "SELECT wip_id "
                    + "FROM marks "
                    + "WHERE user_id = " + userId + " "
                    + "GROUP BY user_id, wip_id)) AS allapp "
                    + "WHERE firstname_th LIKE \"%" + keyword + "%\" OR lastname_th LIKE \"%" + keyword + "%\" OR nickname LIKE \"%" + keyword + "%\" OR school_name LIKE \"%" + keyword + "%\" "
                    + "ORDER BY wip_id");
        
        ResultSet rs = Database.query(con, sql.toString());

        try {
            if (rs.next()) {
                result = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static List<Applicant> getApplicantsWithProgress(int amountOfQuestions, int status, int start, int amount) {
        // complete = 1, incomplete = 2, all = 0
        List<Applicant> result = new ArrayList<>();
        Connection con = ConnectionAgent.connect();
        StringBuilder sql = new StringBuilder();

        if (status == 1) {
            sql.append("SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, IFNULL(overviews_status.status, 0) AS o_status , IFNULL(marks_status.status, 0) AS m_status "
                    + "FROM applicants "
                    + "LEFT OUTER JOIN (SELECT wip_id, COUNT(DISTINCT question_id) AS no_of_checked_question, CASE "
                        + "WHEN COUNT(DISTINCT question_id) = " + amountOfQuestions + " "
                        + "THEN \"1\" "
                        + "ELSE \"0\" "
                        + "END AS status "
                        + "FROM marks "
                        + "GROUP BY wip_id) AS marks_status ON applicants.wip_id = marks_status.wip_id "
                    + "LEFT OUTER JOIN (SELECT wip_id, \"1\" AS status "
                        + "FROM overviews) AS overviews_status ON applicants.wip_id = overviews_status.wip_id "
                    + "HAVING m_status = 1 AND o_status = 1");
        } else if (status == 2) {
            sql.append("SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, IFNULL(overviews_status.status, 0) AS o_status , IFNULL(marks_status.status, 0) AS m_status "
                    + "FROM applicants "
                    + "LEFT OUTER JOIN (SELECT wip_id, COUNT(DISTINCT question_id) AS no_of_checked_question, CASE "
                        + "WHEN COUNT(DISTINCT question_id) = " + amountOfQuestions + " "
                        + "THEN \"1\" "
                        + "ELSE \"0\" "
                        + "END AS status "
                        + "FROM marks "
                        + "GROUP BY wip_id) AS marks_status ON applicants.wip_id = marks_status.wip_id "
                    + "LEFT OUTER JOIN (SELECT wip_id, \"1\" AS status "
                        + "FROM overviews) AS overviews_status ON applicants.wip_id = overviews_status.wip_id "
                    + "HAVING m_status = 0 OR o_status = 0");
        } else {
            sql.append("SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, IFNULL(overviews_status.status, 0) AS o_status , IFNULL(marks_status.status, 0) AS m_status "
                    + "FROM applicants "
                    + "LEFT OUTER JOIN (SELECT wip_id, COUNT(DISTINCT question_id) AS no_of_checked_question, CASE "
                        + "WHEN COUNT(DISTINCT question_id) = " + amountOfQuestions + " "
                        + "THEN \"1\" "
                        + "ELSE \"0\" "
                        + "END AS status "
                        + "FROM marks "
                        + "GROUP BY wip_id) AS marks_status ON applicants.wip_id = marks_status.wip_id "
                    + "LEFT OUTER JOIN (SELECT wip_id, \"1\" AS status "
                        + "FROM overviews) AS overviews_status ON applicants.wip_id = overviews_status.wip_id");
        }

        sql.append(" LIMIT " + amount + " OFFSET " + start);

        ResultSet rs = Database.query(con, sql.toString());
        try {
            while (rs.next()) {
                Applicant a = new Applicant();
                a.setWipId(rs.getInt("wip_id"));
                a.setFirstnameTh(rs.getString("firstname_th"));
                a.setLastnameTh(rs.getString("lastname_th"));
                a.setNickname(rs.getString("nickname"));
                a.setGender(rs.getString("gender").equalsIgnoreCase("m") ? "ชาย" : "หญิง");
                a.setStatus(rs.getString("o_status"));
                a.setStatusSec(rs.getString("m_status"));
                result.add(a);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static List<Applicant> getApplicantsWithProgress(int amountOfQuestions, int start, int amount) {
        return getApplicantsWithProgress(amountOfQuestions, 0, start, amount);
    }
    
    public static int getApplicantsWithSize(int amountOfQuestions, int status) {
        // evaluated = 1, not yet = 2, all = 0
        int result = 0;
        Connection con = ConnectionAgent.connect();
        StringBuilder sql = new StringBuilder();

        if (status == 1) {
            sql.append("SELECT COUNT(*) FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, IFNULL(overviews_status.status, 0) AS o_status , IFNULL(marks_status.status, 0) AS m_status "
                    + "FROM applicants "
                    + "LEFT OUTER JOIN (SELECT wip_id, COUNT(DISTINCT question_id) AS no_of_checked_question, CASE "
                        + "WHEN COUNT(DISTINCT question_id) = " + amountOfQuestions + " "
                        + "THEN \"1\" "
                        + "ELSE \"0\" "
                        + "END AS status "
                        + "FROM marks "
                        + "GROUP BY wip_id) AS marks_status ON applicants.wip_id = marks_status.wip_id "
                    + "LEFT OUTER JOIN (SELECT wip_id, \"1\" AS status "
                        + "FROM overviews) AS overviews_status ON applicants.wip_id = overviews_status.wip_id "
                    + "HAVING m_status = 1 AND o_status = 1) AS progress");
        } else if (status == 2) {
            sql.append("SELECT COUNT(*) FROM (SELECT applicants.wip_id, firstname_th, lastname_th, nickname, gender, IFNULL(overviews_status.status, 0) AS o_status , IFNULL(marks_status.status, 0) AS m_status "
                    + "FROM applicants "
                    + "LEFT OUTER JOIN (SELECT wip_id, COUNT(DISTINCT question_id) AS no_of_checked_question, CASE "
                        + "WHEN COUNT(DISTINCT question_id) = " + amountOfQuestions + " "
                        + "THEN \"1\" "
                        + "ELSE \"0\" "
                        + "END AS status "
                        + "FROM marks "
                        + "GROUP BY wip_id) AS marks_status ON applicants.wip_id = marks_status.wip_id "
                    + "LEFT OUTER JOIN (SELECT wip_id, \"1\" AS status "
                        + "FROM overviews) AS overviews_status ON applicants.wip_id = overviews_status.wip_id "
                    + "HAVING m_status = 0 OR o_status = 0) AS progress");
        } else {
            sql.append("SELECT COUNT(*)  "
                    + "FROM applicants "
                    + "LEFT OUTER JOIN (SELECT wip_id, COUNT(DISTINCT question_id) AS no_of_checked_question, CASE "
                        + "WHEN COUNT(DISTINCT question_id) = " + amountOfQuestions + " "
                        + "THEN \"1\" "
                        + "ELSE \"0\" "
                        + "END AS status "
                        + "FROM marks "
                        + "GROUP BY wip_id) AS marks_status ON applicants.wip_id = marks_status.wip_id "
                    + "LEFT OUTER JOIN (SELECT wip_id, \"1\" AS status "
                        + "FROM overviews) AS overviews_status ON applicants.wip_id = overviews_status.wip_id");
        }
        
        ResultSet rs = Database.query(con, sql.toString());

        try {
            if (rs.next()) {
                result = rs.getInt(1);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
    
    public static void toObject(ResultSet rs, Applicant a) {
        try {
            a.setWipId(rs.getInt("wip_id"));
            a.setCitizenshipId(rs.getString("citizenship_id"));
            a.setFirstnameTh(rs.getString("firstname_th"));
            a.setLastnameTh(rs.getString("lastname_th"));
            a.setNickname(rs.getString("nickname"));
            a.setGender(rs.getString("gender").equalsIgnoreCase("m") ? "ชาย" : "หญิง");
            a.setDob(rs.getString("dob"));
            a.setReligion(rs.getString("religion"));
            a.setTel(rs.getString("tel"));
            a.setMobileTel(rs.getString("mobile_tel"));
            a.setEmail(rs.getString("email"));
            a.setSchoolName(rs.getString("school_name"));
            a.setEducationLevel(rs.getString("education_level"));
            a.setEducationPlan(rs.getString("education_plan"));
            a.setGpax(rs.getString("gpax"));
            a.setCongenitalDisease(rs.getString("congenital_disease"));
            a.setAllergicMedicine(rs.getString("allergic_medicine"));
            a.setAllergicFood(rs.getString("allergic_food"));
            a.setAllergicThing(rs.getString("allergic_thing"));
            a.setComputerSkill(rs.getString("computer_skill"));
            a.setActivities(rs.getString("activities"));
            a.setProvince(rs.getString("province"));
            a.setImgThumb(rs.getString("imgthumb"));
        } catch (SQLException ex) {
            Logger.getLogger(Applicant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString() {
        return "Applicant{" + "wipId=" + wipId + ", citizenshipId=" + citizenshipId + ", firstnameTh=" + firstnameTh + ", lastnameTh=" + lastnameTh + ", nickname=" + nickname + ", dob=" + dob + ", religion=" + religion + ", tel=" + tel + ", mobileTel=" + mobileTel + ", email=" + email + ", schoolName=" + schoolName + ", educationLevel=" + educationLevel + ", educationPlan=" + educationPlan + ", gpax=" + gpax + ", congenitalDisease=" + congenitalDisease + ", allergicMedicine=" + allergicMedicine + ", allergicFood=" + allergicFood + ", allergicThing=" + allergicThing + ", computerSkill=" + computerSkill + ", province=" + province + ", imgThumb=" + imgThumb + '}';
    }
}
