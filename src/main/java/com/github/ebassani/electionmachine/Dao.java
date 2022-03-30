package com.github.ebassani.electionmachine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

    Connection conn;
    Statement statement;
    static final String ADDRESS = "jdbc:mysql://localhost:3308/";
    static final String DB_NAME = "gamedb";
    static final String DB_USER = "appuser";
    static final String DB_PASSWORD = "bussi69";

    public Dao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = java.sql.DriverManager.getConnection(ADDRESS + DB_NAME, DB_USER, DB_PASSWORD);
            statement = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCat(String breed, String weight) {
        try {
            statement.executeUpdate("insert into gametable(breed, weight) values('" + breed + "', " + weight + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectAllCats() throws SQLException {
        return statement.executeQuery("select * from gametable");
    }

}