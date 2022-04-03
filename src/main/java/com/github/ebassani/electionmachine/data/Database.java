package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.Constants;

import java.sql.Connection;
import java.sql.Statement;

public class Database {

    private static Database dbInstance = null;
    public Connection conn;
    public Statement statement;

    private Database() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        conn = java.sql.DriverManager.getConnection(
                "jdbc:mysql://" + Constants.DB_ADDRESS + ":" + Constants.DB_PORT + "/" + Constants.DB_DATABASE,
                Constants.DB_USERNAME,
                Constants.DB_PASSWORD
        );
        statement = conn.createStatement();
    }

    public static Database getInstance() throws Exception {
        if (dbInstance == null) dbInstance = new Database();
        return dbInstance;
    }
}
