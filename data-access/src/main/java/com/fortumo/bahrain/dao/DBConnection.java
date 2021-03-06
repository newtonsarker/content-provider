package com.fortumo.bahrain.dao;

import org.h2.tools.DeleteDbFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnection {

    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./fortumo;AUTO_SERVER=TRUE";
    private static final String USER = "newton";
    private static final String PASS = "newton";

    static {
        DeleteDbFiles.execute("~", "fortumo", true);
    }

    public static void createTables() {
        try {
            DBConnection.executeStatement("CREATE TABLE IF NOT EXISTS PaymentNotification ( PaymentNotificationID IDENTITY PRIMARY KEY, MessageID VARCHAR(50) NULL, Operator VARCHAR(50) NULL, Receiver INT NULL, Sender VARCHAR(50) NULL, Text VARCHAR(50) NULL, MsgTime TIMESTAMP NULL, IsProcessed BOOLEAN NULL )");
            DBConnection.executeStatement("CREATE TABLE IF NOT EXISTS ContentRequest ( RequestID IDENTITY PRIMARY KEY, TransactionID VARCHAR(50) NULL, MessageID VARCHAR(50) NULL, Keyword VARCHAR(50) NULL, Message VARCHAR(50) NULL )");
            DBConnection.executeStatement("CREATE TABLE IF NOT EXISTS ContentResponse ( ResponseID IDENTITY PRIMARY KEY, TransactionID VARCHAR(50) NULL, MessageID VARCHAR(50) NULL, StatusCode INT NULL, ResponseText VARCHAR(200) NULL, Receiver VARCHAR(50) NULL, Operator VARCHAR(50) NULL, IsDelivered BOOLEAN NULL, DeliveredMessage VARCHAR(200) NULL )");
        } catch (Exception e) {
            logger.error("Failed to create tables!", e);
        }
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("Could not load JDBC Driver " + JDBC_DRIVER, e);
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Failed create database connection to H2 database " + DB_URL, e);
        }
        return conn;
    }

    public static boolean executeStatement(String sql) throws Exception {
        logger.info("Executing sql: " + sql);
        boolean flag = Boolean.FALSE;
        Connection connection = DBConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            connection.commit();
            flag = Boolean.TRUE;
        } catch (Exception e) {
            logger.error("Failed to execute statement - " + sql, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Failed to close database connection", e);
            }
        }
        return flag;
    }

    public static List<Map<String, Object>> executeQuery(String sql) throws Exception {
        logger.info("Executing sql: " + sql);
        List<Map<String, Object>> result = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs != null) {

                List<String> columnNames = new ArrayList<>();
                ResultSetMetaData metaData = rs.getMetaData();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    columnNames.add(metaData.getColumnName(i+1));
                }

                while (rs.next()) {
                    Map<String, Object> rowMap = new HashMap<>();
                    for (String columnName : columnNames) {
                        rowMap.put(columnName, rs.getObject(columnName));
                    }
                    result.add(rowMap);
                }

            }
            connection.commit();
        } catch (Exception e) {
            logger.error("Failed to execute query - " + sql, e);
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Failed to close database connection", e);
            }
        }
        return result;
    }


}
