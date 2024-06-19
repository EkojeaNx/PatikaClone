package com.ekojean.patikaclone.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connection = null;

    public Connection getConnection() {
        try {
            this.connection = DriverManager.getConnection(BaseConfig.DB_URL, BaseConfig.DB_USERNAME, BaseConfig.DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.connection;
    }

    public static Connection getConnect() {
        DBConnector dbConnector = new DBConnector();
        return dbConnector.getConnection();
    }
}
