package com.test.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.test.model.to.SubjectInfoTO;

public class SubjectInfoDAO {

    private String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertRecord(SubjectInfoTO data) {
        try {
            String query = "insert into subjectinfo(subjectid,subjectname,description) values(?,?,?)";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, data.getSubjectid());
            stmnt.setString(2, data.getSubjectname());
            stmnt.setString(3, data.getDescription());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean updateRecord(SubjectInfoTO data) {
        try {
            String query = "update subjectinfo set subjectname=?,description=? where subjectid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, data.getSubjectname());
            stmnt.setString(2, data.getDescription());
            stmnt.setString(3, data.getSubjectid());
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public boolean deleteRecord(String subjectid) {
        try {
            String query = "delete from subjectinfo where subjectid=?";
            PreparedStatement stmnt;
            stmnt = DataConnection.getConnection().prepareStatement(query);
            stmnt.setString(1, subjectid);
            boolean result = stmnt.executeUpdate() > 0;
            stmnt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return false;
        }
    }

    public SubjectInfoTO getRecord(String subjectid) {
        try {
            String query = "select subjectname,description from subjectinfo where subjectid=?";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            stmt.setString(1, subjectid);
            SubjectInfoTO result = null;
            ResultSet rs;
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new SubjectInfoTO();
                result.setSubjectid(rs.getString("subjectid"));
                result.setSubjectname(rs.getString("subjectname"));
                result.setDescription(rs.getString("description"));
            }
            rs.close();
            stmt.close();
            return result;
        } catch (Exception ex) {
            errormessage = ex.toString();
            return null;
        }
    }

    public List<SubjectInfoTO> getAllRecord() {
        try {
            String query = "select subjectid,subjectname,description from subjectinfo";
            PreparedStatement stmt;
            stmt = DataConnection.getConnection().prepareStatement(query);
            List<SubjectInfoTO> result = null;
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                do {
                    SubjectInfoTO res = new SubjectInfoTO();
                    res.setSubjectid(rs.getString("subjectid"));
                    res.setSubjectname(rs.getString("subjectname"));
                    res.setDescription(rs.getString("description"));
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
