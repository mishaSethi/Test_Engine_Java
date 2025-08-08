package com.test.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.test.model.to.SectionInfoTO;
import com.test.model.to.TestInfoTO;

public class SectionInfoDAO {

    private String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertRecord(SectionInfoTO data) {
        try {
            String query = "insert into sectioninfo(sectionid,sectionname,subjectid) values(?,?,?)";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, data.getSectionid());
            stmnt.setString(2, data.getSectionname());
            stmnt.setString(3, data.getSubjectid());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean updateRecord(SectionInfoTO data) {
        try {
            String query = "update sectioninfo set sectionname=?,subjectid=? where sectionid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, data.getSectionname());
            stmnt.setString(2, data.getSubjectid());
            stmnt.setString(3, data.getSectionid());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean deleteRecord(String sectionid) {
        try {
            String query = "delete from sectioninfo where sectionid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, sectionid);
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public SectionInfoTO getRecord(String sectionid) {
        try {
            String query = "select sectionname,subjectid from sectioninfo where sectionid=?";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setString(1, sectionid);
            SectionInfoTO result = null;
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new SectionInfoTO();
                result.setSectionid(rs.getString("sectionid"));
                result.setSectionname(rs.getString("sectionname"));
                result.setSubjectid(rs.getString("subjectid"));
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }

    public List<SectionInfoTO> getAllRecord() {
        try {
            String query = "select sectionid,sectionname,subjectid from sectioninfo";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            List<SectionInfoTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    SectionInfoTO res = new SectionInfoTO();
                    res.setSectionid(rs.getString("sectionid"));
                    res.setSectionname(rs.getString("sectionname"));
                    res.setSubjectid(rs.getString("subjectid"));
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

    public List<SectionInfoTO> getAllRecord(String testid) {
        try {
            String query = "select * from sectioninfo where subjectid=(select subjectid from testinfo where testid=?)";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(testid));
            List<SectionInfoTO> result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    SectionInfoTO res = new SectionInfoTO();
                    res.setSectionid(rs.getString("sectionid"));
                    res.setSectionname(rs.getString("sectionname"));
                    res.setSubjectid(rs.getString("subjectid"));
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
