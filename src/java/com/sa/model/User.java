/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.model;

import com.sa.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class User {

    private int userId;
    private String email;
    private String password;
    private String displayname;
    private int type;

    public User() {
    }

    public User(int userId, String email, String password, String displayname, int type) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.displayname = displayname;
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static boolean addUser(String email, String password, String displayname, int type) {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (email, password, displayname, type) "
                    + "VALUES (?, ?, ?, ?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, displayname);
            ps.setInt(4, type);
            result = ps.executeUpdate() == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static User login(String email, String password) {
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * "
                + "FROM users WHERE email = \"" + email + "\" AND password = \"" + password + "\"");
        User result = null;
        try {
            if (rs.next()) {
                result = new User();
                toObject(rs, result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean deleteUser(int userId) {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE user_id = ?");
            ps.setInt(1, userId);
            result = ps.executeUpdate() == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public boolean editUser() {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE users SET email = ?, password = ? ,"
                    + "displayname = ?, type = ? WHERE user_id = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, displayname);
            ps.setInt(4, type);
            ps.setInt(5, userId);
            result = ps.executeUpdate() == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean editPassword(String email, String newPassword) {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE users SET password = ? WHERE email = ?");
            ps.setString(1, newPassword);
            ps.setString(2, email);
            result = ps.executeUpdate() == 1 ? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean editType(String email, int newType) {
        boolean result;
        Connection con = ConnectionAgent.connect();
        int i = Database.update(con, "UPDATE users SET type = \"" + newType + "\" WHERE email = + \"" + email + "\"");
        result = i == 0 ? false : true;
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int isExists(String email, String displayname) {
        return isExists(email, displayname, 0);
    }

    public static int isExists(String email, String displayname, int userId) {
        int result;
        boolean emailEq = false;
        boolean displayEq = false;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT email, displayname from users WHERE (displayname "
                + "= \"" + displayname + "\" OR email = \"" + email + "\")" + (userId == 0 ? "" : ("AND user_id != " + userId)));
        try {
            while (rs.next()) {
                if (rs.getString("email").equalsIgnoreCase(email)) {
                    emailEq = true;
                }
                if (rs.getString("displayname").equalsIgnoreCase(displayname)) {
                    displayEq = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (emailEq && displayEq) {
            result = 3;
        } else if (displayEq) {
            result = 2;
        } else if (emailEq) {
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    public static boolean isEmailExists(String email) {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT email from users WHERE email = \"" + email + "\"");
        try {
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean isDisplayNameExists(String displayname) {
        boolean result = false;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT email from users WHERE displayname = \"" + displayname + "\"");
        try {
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static List<User> find(String columnName, String keyword) {
        List<User> result = new ArrayList();
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM users WHERE " + columnName + " LIKE \"" + keyword + "\"");
        try {
            while (rs.next()) {
                User a = new User();
                User.toObject(rs, a);
                result.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static User findById(int userId) {
        User result = null;
        Connection con = ConnectionAgent.connect();
        ResultSet rs = Database.query(con, "SELECT * FROM users WHERE user_id = \"" + userId + "\"");
        try {
            if (rs.next()) {
                result = new User();
                toObject(rs, result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // close connection
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    private static void toObject(ResultSet rs, User u) {
        try {
            u.setUserId(rs.getInt("user_id"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setDisplayname(rs.getString("displayname"));
            u.setType(rs.getInt("type"));
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", email=" + email + ", password=" + password + ", displayname=" + displayname + ", type=" + type + '}';
    }
}
