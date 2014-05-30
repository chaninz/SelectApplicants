/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.model;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHANINZ
 */
public class Test {
    public static void main(String[] args) {
        /*Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM profile");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                for (int i = 1; i <= 34; i++) {
                    System.out.print(rs.getString(i) + " | ");
                }
                System.out.println();
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }*/
//        ResultSet re = Database.query("SELECT wip_id, firstname_th, lastname_th FROM profile");
//        try {
//            re.next();
//            for (int i = 1; i <= 3; i++) {
//
//                System.out.print(re.getString(i) + " | ");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println();
//        User u = User.login("chaninz@live.com", "ninninnin");
//        System.out.println(u);
//        System.out.println(User.isEmailDuplicate("chaninz@live.com"));
//        System.out.println(User.isDisplayNameDuplicate("chaninz"));
//        System.out.println(User.isExists("chaninz@live.com","oramor"));
        //System.out.println(Applicant.findById(59636));
//        List<Applicant> ls = Applicant.find("citizenship_id", "____________1");
//        for (Applicant applicant : ls) {
//            System.out.println(applicant);
//        }
        
        //System.out.println(User.changePassword("chaninz@live.com", "ninninnin"));
        //System.out.println(Question.addQuestion(6, "น้องชื่ออะไร", 1, 15));
//        Question q1 = new Question(2, 1, "ใครคือเจ้าของ Google.com",  50);
//        System.out.println(q1.editQuestion());
//        System.out.println(Question.deleteQuestion(3));
//        System.out.println(Question.deleteQuestion(4));
        //System.out.println(User.changeType("chaninz@live.com", 1));
//        System.out.println(Application.addApplication(51123, 2, "chaninz.com/pic2.jpg"));
//        System.out.println(Application.addApplication(51124, 1, "chaninz.com/pic1.jpg"));
//        System.out.println(Application.deleteApplication(51123, 1));
//        System.out.println(Application.editApplication(51124, 2, "chaninz.com/pic3.jpg"));
        //System.out.println(Application.isApplicationExists(51124, 1));
        System.out.println(Mark.addMark(50413, 1, 1, 10, "ชอบ"));
        //System.out.println(Mark.editMark(50412, 1, 1, 20, "ฮามากก"));
        //System.out.println(Overview.addOverview(50556, 0, "โอเคอยู่ มาจากต่างจังหวัด", 1));
        //System.out.println(Overview.deleteOverview(50556));
        //System.out.println(Overview.editOverview(50412, 0, "ไม่ได้เรื่อง", 1));
        //System.out.println(User.deleteUser(6));
    }
}
