package com.dailylist.dailylist.controller;

import java.sql.*;

class DatabaseConnection {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/dailyplanner";
    private static final String username = "root";
    private static final String password = "ELG;qqNW9ThpRlz.yPPK";
    private static Connection connection;

    DatabaseConnection(){
        connectToDatabase();
    }

    private void connectToDatabase(){
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
