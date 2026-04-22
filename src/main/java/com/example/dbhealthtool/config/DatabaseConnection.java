package com.example.dbhealthtool.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            String host = ConfigLoader.get("db.host");
            String port = ConfigLoader.get("db.port");
            String dbName = ConfigLoader.get("db.name");
            String username = ConfigLoader.get("db.username");
            String password = ConfigLoader.get("db.password");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

            return DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}