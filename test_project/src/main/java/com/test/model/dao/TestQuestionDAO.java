package com.test.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.test.model.to.TestQuestionTO;

public class TestQuestionDAO {

    private String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertRecord(TestQuestionTO data) {
        try {
            String query = "insert into testquestion(questionid,questiontext,optiona,optionb,optionc,optiond,answer,sectionid,testid,marks) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setInt(1, data.getQuestionid());
            stmnt.setString(2, data.getQuestiontext());
            stmnt.setString(3, data.getOptiona());
            stmnt.setString(4, data.getOptionb());
            stmnt.setString(5, data.getOptionc());
            stmnt.setString(6, data.getOptiond());
            stmnt.setString(7, data.getAnswer());
            stmnt.setString(8, data.getSectionid());
            stmnt.setInt(9, data.getTestid());
            stmnt.setInt(10, data.getMarks());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean updateRecord(TestQuestionTO data) {
        try {
            String query = "update testquestion set questiontext=?,optiona=?,optionb=?,optionc=?,optiond=?,answer=?,sectionid=?,testid=?,marks=? where questionid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, data.getQuestiontext());
            stmnt.setString(2, data.getOptiona());
            stmnt.setString(3, data.getOptionb());
            stmnt.setString(4, data.getOptionc());
            stmnt.setString(5, data.getOptiond());
            stmnt.setString(6, data.getAnswer());
            stmnt.setString(7, data.getSectionid());
            stmnt.setInt(8, data.getTestid());
            stmnt.setInt(9, data.getMarks());
            stmnt.setInt(10, data.getQuestionid());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean deleteRecord(Integer questionid) {
        try {
            String query = "delete from testquestion where questionid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setInt(1, questionid);
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public TestQuestionTO getRecord(Integer questionid) {
        try {
            String query = "select questiontext,optiona,optionb,optionc,optiond,answer,sectionid,testid,marks from testquestion where questionid=?";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setInt(1, questionid);
            TestQuestionTO result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new TestQuestionTO();
                result.setQuestionid(rs.getInt("questionid"));
                result.setQuestiontext(rs.getString("questiontext"));
                result.setOptiona(rs.getString("optiona"));
                result.setOptionb(rs.getString("optionb"));
                result.setOptionc(rs.getString("optionc"));
                result.setOptiond(rs.getString("optiond"));
                result.setAnswer(rs.getString("answer"));
                result.setSectionid(rs.getString("sectionid"));
                result.setTestid(rs.getInt("testid"));
                result.setMarks(rs.getInt("marks"));
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }

    public List<TestQuestionTO> getAllRecord() {
        try {
            String query = "select questionid,questiontext,optiona,optionb,optionc,optiond,answer,sectionid,testid,marks from testquestion";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            List<TestQuestionTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    TestQuestionTO res = new TestQuestionTO();
                    res.setQuestionid(rs.getInt("questionid"));
                    res.setQuestiontext(rs.getString("questiontext"));
                    res.setOptiona(rs.getString("optiona"));
                    res.setOptionb(rs.getString("optionb"));
                    res.setOptionc(rs.getString("optionc"));
                    res.setOptiond(rs.getString("optiond"));
                    res.setAnswer(rs.getString("answer"));
                    res.setSectionid(rs.getString("sectionid"));
                    res.setTestid(rs.getInt("testid"));
                    res.setMarks(rs.getInt("marks"));
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

    public List<TestQuestionTO> getAllRecordbytestid(int testid) {
        try {
            String query = "select * from testquestion  where testid=?";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            List<TestQuestionTO> result = null;
            stmt.setInt(1, testid);
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    TestQuestionTO res = new TestQuestionTO();
                    res.setQuestionid(rs.getInt("questionid"));
                    res.setQuestiontext(rs.getString("questiontext"));
                    res.setOptiona(rs.getString("optiona"));
                    res.setOptionb(rs.getString("optionb"));
                    res.setOptionc(rs.getString("optionc"));
                    res.setOptiond(rs.getString("optiond"));
                    res.setAnswer(rs.getString("answer"));
                    res.setSectionid(rs.getString("sectionid"));
                    res.setTestid(rs.getInt("testid"));
                    res.setMarks(rs.getInt("marks"));
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
