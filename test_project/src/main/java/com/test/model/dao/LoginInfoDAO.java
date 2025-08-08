package com.test.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.test.model.to.LoginInfoTO;

public class LoginInfoDAO {

    private String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertRecord(LoginInfoTO data) {
        try {
            String query = "insert into logininfo(username,password,rolename,lastlogin,emailid) values(?,?,?,?,?)";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, data.getUsername());
            stmnt.setString(2, data.getPassword());
            stmnt.setString(3, data.getRolename());
            stmnt.setTimestamp(4, data.getLastlogin());
            stmnt.setString(5, data.getEmailid());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public LoginInfoTO checkUser(String username) {
        try {
            String query = "select username , password, rolename ,emailid, lastlogin from logininfo where username=?";
            PreparedStatement stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setString(1, username);
            LoginInfoTO result = null;
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new LoginInfoTO();
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("password"));
                result.setRolename(rs.getString("rolename"));
                result.setLastlogin(rs.getTimestamp("lastlogin"));
                result.setEmailid(rs.getString("emailid"));
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }

    public boolean updateLastLogin(String username) {
        try {
            String query = "update logininfo set lastlogin = sysdate() where username = ?";
            PreparedStatement stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setString(1, username);
            boolean result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean updateRecord(LoginInfoTO data) {
        try {
            String query = "update logininfo set password=?,rolename=?,lastlogin=?,emailid=? where username=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, data.getPassword());
            stmnt.setString(2, data.getRolename());
            stmnt.setTimestamp(3, data.getLastlogin());
            stmnt.setString(4, data.getEmailid());
            stmnt.setString(5, data.getUsername());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean deleteRecord(String username) {
        try {
            String query = "delete from logininfo where username=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, username);
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public LoginInfoTO getRecord(String username) {
        try {
            String query = "select password,rolename,lastlogin,emailid from logininfo where username=?";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setString(1, username);
            LoginInfoTO result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new LoginInfoTO();
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("password"));
                result.setRolename(rs.getString("rolename"));
                result.setLastlogin(rs.getTimestamp("lastlogin"));
                result.setEmailid(rs.getString("emailid"));
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }

    public List<LoginInfoTO> getAllRecord() {
        try {
            String query = "select username,password,rolename,lastlogin,emailid from logininfo";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            List<LoginInfoTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    LoginInfoTO res = new LoginInfoTO();
                    res.setUsername(rs.getString("username"));
                    res.setPassword(rs.getString("password"));
                    res.setRolename(rs.getString("rolename"));
                    res.setLastlogin(rs.getTimestamp("lastlogin"));
                    res.setEmailid(rs.getString("emailid"));
                    result.add(res);
                } while (rs.next());
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }

    public List<LoginInfoTO> getAllstudents() {
        try {
            String query = "select username from logininfo where rolename='student'";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            List<LoginInfoTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    LoginInfoTO res = new LoginInfoTO();
                    res.setUsername(rs.getString("username"));

                    result.add(res);
                } while (rs.next());
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }
}
