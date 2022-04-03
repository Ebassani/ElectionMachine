package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.Database;

import java.sql.*;
import java.util.ArrayList;

public class QuestionManagement {

    Database db = Database.getInstance();

    public QuestionManagement() throws Exception {
        try {
             Database.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function that creates a question, requires the text.
    public void createQuestion(String text) {
        try {
            db.statement.executeUpdate("INSERT INTO questions(text) VALUES('" + text + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function that edits a question, changes the text on the question that has it's ID informed
    public void updateQuestion(String text, int id) {
        try {
            db.statement.executeUpdate("UPDATE questions SET text='" + text + "' WHERE id='" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function that deletes the question of whose ID was put in the parameter
    public void deleteQuestion(int id) {
        try {
            db.statement.executeUpdate("DELETE FROM questions WHERE id='" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function returns an array that contains all the questions that can be used to print questions
    public String[] printQuestions() throws SQLException {
        ResultSet resultSet = db.statement.executeQuery("SELECT * from questions");

        ArrayList<String> questions = new ArrayList<String>();
        while (resultSet.next()){
            questions.add(resultSet.getString("text"));
        }
        String[] q= new String[questions.size()];
        for (int i=0;i< q.length;i++){
            q[i]=questions.get(i);
        }
        return q;
    }

    public int[] questionIds()throws SQLException {
        ResultSet resultSet = db.statement.executeQuery("SELECT * from questions");

        ArrayList<Integer> ids = new ArrayList<>();
        while (resultSet.next()){
            ids.add(Integer.parseInt(resultSet.getString("id")));
        }
        int[] id= new int[ids.size()];
        for (int i=0;i< id.length;i++){
            id[i]=ids.get(i);
        }
        return id;
    }

    // Returns a question based on the id informed by the parameter
    public String getQuestion(int id) throws SQLException {
        String question = "The question with id " + id + " does not exist!";
        ResultSet resultSet = db.statement.executeQuery("SELECT * from questions " +
                "where id='" + id + "'");
        while (resultSet.next()) {
            question = resultSet.getString("text");
        }
        return question;
    }
}
