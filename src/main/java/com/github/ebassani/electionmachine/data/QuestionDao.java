package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.data.model.Question;
import com.github.ebassani.electionmachine.data.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {

    static Database db;

    static {
        try {
            db = Database.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function that creates a question, requires the text.
    public static void createQuestion(String text) {
        try {
            db.statement.executeUpdate("INSERT INTO questions(text) VALUES('" + text + "')");
            Question[] questions= getQuestions();
            int lQuestionId = questions[questions.length-1].getId();
            List<User> users = UserDao.getUsers();
            for (User u : users){
                if (u.isCandidate()){
                    int userId = u.getId();
                    db.statement.executeUpdate("INSERT INTO answers(question_id, user_id, value) " +
                            "VALUES('" + lQuestionId + "','"+ userId +"','"+ 4 +"') ");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function that edits a question, changes the text on the question that has its ID informed
    public static void updateQuestion(String text, int id) {
        try {
            db.statement.executeUpdate("UPDATE questions SET text='" + text + "' WHERE id='" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function that deletes the question of whose ID was put in the parameter
    public static void deleteQuestion(int id) {
        try {
            db.statement.executeUpdate("DELETE FROM answers WHERE question_id='" + id + "'");
            db.statement.executeUpdate("DELETE FROM questions WHERE id='" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function returns an array that contains all the questions that can be used to print questions
    public static Question[] getQuestions() throws SQLException {

        ResultSet resultSet = db.statement.executeQuery("SELECT * from questions");

        ArrayList<Question> questions = new ArrayList<>();
        while (resultSet.next()){
            String question= resultSet.getString("text");
            int id = Integer.parseInt(resultSet.getString("id"));
            questions.add(new Question(id,question));
        }

        return questions.toArray(new Question[0]);
    }

    //

    /**
     * Returns a question based on the id informed by the parameter
     */
    public static Question getQuestionWithId(int id) throws SQLException {
        String question = "The question with id " + id + " does not exist!";
        ResultSet resultSet = db.statement.executeQuery("SELECT * from questions " +
                "where id='" + id + "'");
        while (resultSet.next()) {
            question = resultSet.getString("text");
        }

        return new Question(id, question);
    }
}
