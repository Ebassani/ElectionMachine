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
    public Question[] getQuestions() throws SQLException {

        ResultSet resultSet = db.statement.executeQuery("SELECT * from questions");

        ArrayList<Question> questions = new ArrayList<>();
        while (resultSet.next()){
            String question= resultSet.getString("text");
            int id = Integer.parseInt(resultSet.getString("id"));
            questions.add(new Question(id,question));
        }
        Question[] q= new Question[questions.size()];
        for (int i=0;i< q.length;i++){
            q[i]=questions.get(i);
        }
        return q;
    }

    // Returns a question based on the id informed by the parameter
    public Question getQuestionWithId(int id) throws SQLException {
        String question = "The question with id " + id + " does not exist!";
        ResultSet resultSet = db.statement.executeQuery("SELECT * from questions " +
                "where id='" + id + "'");
        while (resultSet.next()) {
            question = resultSet.getString("text");
        }

        return new Question(id, question);
    }
}
