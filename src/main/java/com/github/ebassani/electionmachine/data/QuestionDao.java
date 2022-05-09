package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.data.model.Question;
import com.github.ebassani.electionmachine.data.model.User;

import java.sql.PreparedStatement;
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

    /**
     * This function gets a String text and generates, in the database, a question with it.
     * It also creates a neutral answers for all candidates,
     * since they should have already answered the form
     */
    public static void createQuestion(String text) {
        try {
            PreparedStatement statement = db.conn.prepareStatement("INSERT INTO questions (text) VALUES (?)");
            statement.setString(1, text);
            statement.executeUpdate();
            Question[] questions= getQuestions();
            int lQuestionId = questions[questions.length-1].getId();
            List<User> users = UserDao.getUsers();
            for (User u : users){
                if (u.isCandidate()){
                    int userId = u.getId();
                    statement = db.conn.prepareStatement(
                            "INSERT INTO answers(question_id, user_id, value) VALUES (?,?,?)"
                    );
                    statement.setInt(1, lQuestionId);
                    statement.setInt(2, userId);
                    statement.setInt(3, 4);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that edits a question. It changes the text on the question that has its ID informed
     */
    public static void updateQuestion(String text, int id) {
        try {
            PreparedStatement statement = db.conn.prepareStatement("UPDATE questions SET text=? WHERE id=?");
            statement.setString(1, text);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that deletes the question of whose ID was put in the parameter
     * @param id
     */
    public static void deleteQuestion(int id) {
        try {
            db.conn.createStatement().executeUpdate("DELETE FROM answers WHERE question_id='" + id + "'");
            db.conn.createStatement().executeUpdate("DELETE FROM questions WHERE id='" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that goes through the database, saves all the questions in an array and return this array
     */
    public static Question[] getQuestions() throws SQLException {

        ResultSet resultSet = db.conn.createStatement().executeQuery("SELECT * FROM questions");

        ArrayList<Question> questions = new ArrayList<>();
        while (resultSet.next()){
            String question= resultSet.getString("text");
            int id = Integer.parseInt(resultSet.getString("id"));
            questions.add(new Question(id,question));
        }

        return questions.toArray(new Question[0]);
    }

    /**
     * Returns a question based on the id informed by the parameter
     */
    public static Question getQuestionWithId(int id) throws SQLException {
        String question = "The question with id " + id + " does not exist!";
        ResultSet resultSet = db.conn.createStatement().executeQuery("SELECT * FROM questions WHERE id='" + id + "'");
        while (resultSet.next()) {
            question = resultSet.getString("text");
        }
        return new Question(id, question);
    }
}
