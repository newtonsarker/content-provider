package com.fortumo.bahrain.dao;

import org.h2.tools.DeleteDbFiles;

import java.sql.*;

public class DBConnection {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/fortumo;AUTO_SERVER=TRUE";
    private static final String USER = "newton";
    private static final String PASS = "newton";

    static {
        DeleteDbFiles.execute("~", "fortumo", true);
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean executeStatement(String sql) {
        boolean flag = Boolean.FALSE;
        Connection connection = DBConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            connection.commit();
            flag = Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        Connection connection = DBConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }


}
