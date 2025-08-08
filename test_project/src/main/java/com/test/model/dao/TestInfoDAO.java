package com.test.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.test.model.to.TestInfoTO;

public class TestInfoDAO {

    private String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertRecord(TestInfoTO data) {
        try {
            String query = "insert into testinfo(testid,testname,subjectid,testlevel,totalquestion,username) values(?,?,?,?,?,?)";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setInt(1, data.getTestid());
            stmnt.setString(2, data.getTestname());
            stmnt.setString(3, data.getSubjectid());
            stmnt.setString(4, data.getTestlevel());
            stmnt.setInt(5, data.getTotalquestion());
            stmnt.setString(6, data.getUsername());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean updateRecord(TestInfoTO data) {
        try {
            String query = "update testinfo set testname=?,subjectid=?,testlevel=?,totalquestion=?,username=? where testid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, data.getTestname());
            stmnt.setString(2, data.getSubjectid());
            stmnt.setString(3, data.getTestlevel());
            stmnt.setInt(4, data.getTotalquestion());
            stmnt.setString(5, data.getUsername());
            stmnt.setInt(6, data.getTestid());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean deleteRecord(Integer testid) {
        try {
            String query = "delete from testinfo where testid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setInt(1, testid);
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public TestInfoTO getRecord(Integer testid) {
        try {
            String query = "select testname,subjectid,testlevel,totalquestion,username from testinfo where testid=?";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setInt(1, testid);
            TestInfoTO result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new TestInfoTO();
                result.setTestid(rs.getInt("testid"));
                result.setTestname(rs.getString("testname"));
                result.setSubjectid(rs.getString("subjectid"));
                result.setTestlevel(rs.getString("testlevel"));
                result.setTotalquestion(rs.getInt("totalquestion"));
                result.setUsername(rs.getString("username"));
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }

    public List<TestInfoTO> getAllRecord() {
        try {
            String query = "select testid,li.username,si.subjectname,tsi.testname,tsi.testlevel,tsi.totalquestion from testinfo tsi join logininfo li on tsi.username=li.username join subjectinfo si on tsi.subjectid=si.subjectid;";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            List<TestInfoTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    TestInfoTO res = new TestInfoTO();
                    res.setTestid(rs.getInt("testid"));
                    res.setUsername(rs.getString("username"));
                    res.setSubjectname(rs.getString("subjectname"));
                    res.setTestname(rs.getString("testname"));
                    res.setTestlevel(rs.getString("testlevel"));
                    res.setTotalquestion(rs.getInt("totalquestion"));
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

    public List<TestInfoTO> getAllRecord(String username) {
        try {
            String query = "select testid,li.username,si.subjectname,tsi.testname,tsi.testlevel,tsi.totalquestion from testinfo tsi join logininfo li on tsi.username=li.username join subjectinfo si on tsi.subjectid=si.subjectid where testid in (select testid from testschedule where susername=? and testdate=curdate() ) ";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setString(1, username);
            List<TestInfoTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    TestInfoTO res = new TestInfoTO();
                    res.setTestid(rs.getInt("testid"));
                    res.setUsername(rs.getString("username"));
                    res.setSubjectname(rs.getString("subjectname"));
                    res.setTestname(rs.getString("testname"));
                    res.setTestlevel(rs.getString("testlevel"));
                    res.setTotalquestion(rs.getInt("totalquestion"));
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
