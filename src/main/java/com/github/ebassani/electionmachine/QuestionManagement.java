package com.github.ebassani.electionmachine;

import java.sql.*;

public class QuestionManagement {

    Connection conn;
    Statement statement;

    // Local database in Eduardo's computer, probably going to be changed later.
    static final String ADDRESS = "jdbc:mysql://localhost:3306/";
    static final String DB_NAME = "electionMachine";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Bussi69!";


    public QuestionManagement() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = java.sql.DriverManager.getConnection(ADDRESS + DB_NAME, DB_USER, DB_PASSWORD);
            statement = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function that creates a question, requires the text.
    public void createQuestion(String text) {
        try {
            statement.executeUpdate("INSERT INTO questions(message) VALUES('" + text + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function that edits a question, changes the text on the question that has it's ID informed
    public void updateQuestion(String text, int id) {
        try {
            statement.executeUpdate("UPDATE questions SET message='" + text + "' WHERE id='"+ id +"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function that deletes the question of whose ID was put in the parameter
    public void deleteQuestion(int id) {
        try {
            statement.executeUpdate("DELETE FROM questions WHERE id='"+ id +"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function returns a string that can be used to print questions
    public String printQuestions() throws SQLException {
        String questions="";
        ResultSet resultSet = statement.executeQuery("SELECT * from questions");
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) questions+=(": ");
                String columnValue = resultSet.getString(i);
                questions+=(rsmd.getColumnName(i) + " " + columnValue);
            }
        }
        return questions;
    }
}
