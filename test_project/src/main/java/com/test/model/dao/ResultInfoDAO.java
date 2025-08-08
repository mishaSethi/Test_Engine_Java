package com.test.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.test.model.to.ResultInfoTO;

public class ResultInfoDAO {

    private String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertRecord(ResultInfoTO data) {
        try {
            String query = "insert into resultinfo(resultid,resultdate,username,testid,totalquestion,attemptquestion,rightquestion,obtainedmarks) values(?,curdate(),?,?,?,?,?,?)";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setInt(1, data.getResultid());
            stmnt.setString(2, data.getUsername());
            stmnt.setInt(3, data.getTestid());
            stmnt.setInt(4, data.getTotalquestion());
            stmnt.setInt(5, data.getAttempquestion());
            stmnt.setInt(6, data.getRightquestion());
            stmnt.setInt(7, data.getObtainedmarks());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean updateRecord(ResultInfoTO data) {
        try {
            String query = "update resultinfo set resultdate=?,username=?,testid=?,totalquestion=?,attemptquestion=?,rightquestion=?,obtainedmarks=? where resultid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setTimestamp(1, data.getResultdate());
            stmnt.setString(2, data.getUsername());
            stmnt.setInt(3, data.getTestid());
            stmnt.setInt(4, data.getTotalquestion());
            stmnt.setInt(5, data.getAttempquestion());
            stmnt.setInt(6, data.getRightquestion());
            stmnt.setInt(7, data.getObtainedmarks());
            stmnt.setInt(8, data.getResultid());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean deleteRecord(Integer resultid) {
        try {
            String query = "delete from resultinfo where resultid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setInt(1, resultid);
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public ResultInfoTO getRecord(Integer resultid) {
        try {
            String query = "select resultdate,username,testid,totalquestion,attemptquestion,rightquestion,obtainedmarks from resultinfo where resultid=?";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setInt(1, resultid);
            ResultInfoTO result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ResultInfoTO();
                result.setResultid(rs.getInt("resultid"));
                result.setResultdate(rs.getTimestamp("resultdate"));
                result.setUsername(rs.getString("username"));
                result.setTestid(rs.getInt("testid"));
                result.setTotalquestion(rs.getInt("totalquestion"));
                result.setAttempquestion(rs.getInt("attemptquestion"));
                result.setRightquestion(rs.getInt("rightquestion"));
                result.setObtainedmarks(rs.getInt("obtainedmarks"));
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }

    public List<ResultInfoTO> getAllRecord() {
        try {
            String query = "select resultid,resultdate,username,testid,totalquestion,attemptquestion,rightquestion,obtainedmarks from resultinfo";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            List<ResultInfoTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    ResultInfoTO res = new ResultInfoTO();
                    res.setResultid(rs.getInt("resultid"));
                    res.setResultdate(rs.getTimestamp("resultdate"));
                    res.setUsername(rs.getString("username"));
                    res.setTestid(rs.getInt("testid"));
                    res.setTotalquestion(rs.getInt("totalquestion"));
                    res.setAttempquestion(rs.getInt("attemptquestion"));
                    res.setRightquestion(rs.getInt("rightquestion"));
                    res.setObtainedmarks(rs.getInt("obtainedmarks"));
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
    
    public List<ResultInfoTO> getAllRecord(String username) {
        try {
            String query = "select resultid,resultdate,resultinfo.username as username,resultinfo.testid as testid,resultinfo.totalquestion as totalquestion,attemptquestion,rightquestion,obtainedmarks,testname ";
            query += " from resultinfo ";
            query += " join testinfo ";
            query += " on resultinfo.testid = testinfo.testid ";
            query += " where resultinfo.username = ?";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setString(1,username);
            List<ResultInfoTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    ResultInfoTO res = new ResultInfoTO();
                    res.setResultid(rs.getInt("resultid"));
                    res.setResultdate(rs.getTimestamp("resultdate"));
                    res.setUsername(rs.getString("username"));
                    res.setTestid(rs.getInt("testid"));
                    res.setTotalquestion(rs.getInt("totalquestion"));
                    res.setAttempquestion(rs.getInt("attemptquestion"));
                    res.setRightquestion(rs.getInt("rightquestion"));
                    res.setObtainedmarks(rs.getInt("obtainedmarks"));
                    res.setTestname(rs.getString("testname"));
                    result.add(res);
                } while (rs.next());
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            System.out.println(ex);
            return null;
        }
    }
}
