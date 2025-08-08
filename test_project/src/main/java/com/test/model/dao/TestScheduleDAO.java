package com.test.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import com.test.model.to.TestInfoTO;
import com.test.model.to.TestScheduleTO;


public class TestScheduleDAO {
    private String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertRecord(TestScheduleTO data) {
        try {
            String query = "insert into testschedule(scheduleid,fusername,susername,testdate,testid) values(?,?,?,?,?)";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setInt(1, data.getScheduleid());
            stmnt.setString(2, data.getFusername());
            stmnt.setString(3, data.getSusername());
            stmnt.setDate(4, data.getTestdate());
            stmnt.setInt(5, data.getTestid());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean updateRecord(TestScheduleTO data) {
        try {
            String query = "update testschedule set fusername=?,susername=?,testdate=?,testid=? where scheduleid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, data.getFusername());
            stmnt.setString(2, data.getSusername());
            stmnt.setDate(3, data.getTestdate());
            stmnt.setInt(4, data.getTestid());
            stmnt.setInt(5, data.getScheduleid());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean deleteRecord(Integer scheduleid) {
        try {
            String query = "delete from testschedule where scheduleid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setInt(1, scheduleid);
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public TestScheduleTO getRecord(Integer scheduleid) {
        try {
            String query = "select fusername,susername,testdate,testid from testschedule where scheduleid=?";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setInt(1, scheduleid);
            TestScheduleTO result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new TestScheduleTO();
                result.setScheduleid(rs.getInt("scheduleid"));
                result.setFusername(rs.getString("fusername"));
                result.setSusername(rs.getString("susername"));
                result.setTestdate(rs.getDate("testdate"));
                result.setTestid(rs.getInt("testid"));
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }

  

    public List<TestScheduleTO> getAllRecord() {
        try {
            String query = "SELECT ts.scheduleid,ts.fusername,ts.susername,t.testname,ts.testdate from testschedule ts join testinfo t on t.testid=ts.testid;";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            List<TestScheduleTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    TestScheduleTO res = new TestScheduleTO();
                    res.setScheduleid(rs.getInt("scheduleid"));
                    res.setFusername(rs.getString("fusername"));
                    res.setSusername(rs.getString("susername"));
                    res.setTestname(rs.getString("testname"));
                    res.setTestdate(rs.getDate("testdate"));

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

    public List<TestScheduleTO> getAlltestscheduled(String susername) {
        try {
            String query = "select testname from testinfo where testid in(select testid from testschedule where susername=? and testdate=curdate());";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setString(1,susername);
            List<TestScheduleTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                   TestScheduleTO res = new TestScheduleTO();
                    res.setScheduleid(rs.getInt("scheduleid"));
                    res.setFusername(rs.getString("fusername"));
                    res.setSusername(rs.getString("susername"));
                    res.setTestname(rs.getString("testname"));
                    res.setTestdate(rs.getDate("testdate"));
                    result.add(res);
                } while (rs.next());
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            ex.printStackTrace();
            return null;
        }
    }
}
