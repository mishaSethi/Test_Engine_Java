package com.test.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * This class is responsible for establishing connection between Java Application with database
 * This class is a singleton class which force only one Sql Connection must be exist for all applications
 */
public class DataConnection {
    private static Connection con = null;

    private DataConnection() {
    }

    public static Connection getConnection() {
        try {
            if (con == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                // These credentials based upon the system where this database reside
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_engine_pro", "root", "");
            }
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            con = null;
        } catch (SQLException ex) {

        }
    }
}
