package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.data.model.Answer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao {

    static Database db;

    static {
        try {
            db = Database.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that adds an answer to the database, requires an Answer object
     */
    public static void addAnswer(Answer answer) throws SQLException {
        PreparedStatement statement = db.conn.prepareStatement(
                "INSERT INTO answers(question_id, user_id, value) VALUE (?,?,?)"
        );
        statement.setInt(1, answer.getQuestionId());
        statement.setInt(2, answer.getUserId());
        statement.setInt(3, answer.getValue());
        statement.executeUpdate();
    }

    public static void editAnswerValue(int questionId, int userId, int newValue) throws SQLException {
        PreparedStatement statement = db.conn.prepareStatement(
                "UPDATE answers SET value=? WHERE user_id=? AND question_id=?"
        );
        statement.setInt(1, newValue);
        statement.setInt(2, userId);
        statement.setInt(3, questionId);
        statement.executeUpdate();
    }

    /**
     * This function searches the database for answers that were answered by the user referred
     * in the parameters and returns them in the form of a List<Answer>
     */
    public static List<Answer> getUserAnswers(int id) throws SQLException {
        ArrayList<Answer> answers = new ArrayList<>();

        ResultSet rs = db.conn.createStatement().executeQuery("SELECT * FROM answers WHERE" +
                " user_id ='" + id + "'");

        while (rs.next()){
            Answer answer = new Answer();
            answer.setUserId(id);
            answer.setQuestionId(rs.getInt("question_id"));
            answer.setValue(rs.getInt("value"));
            answers.add(answer);
        }

        return answers;
    }

    /**
     * This function requires 2 Lists of Answer for a comparison, the function checks the similarities between
     * both and return a number between 0 and 100. 100 being 2 identical sets of answers and 0
     * being 2 sets of completely opposite answers
     */
    public static int compareAnswers(List<Answer> uAnswers, List<Answer> cAnswers) {
        float diff=0;

        for (Answer answer : uAnswers){
            for (Answer candAnswers : cAnswers){
                if (answer.getQuestionId() == candAnswers.getQuestionId()){
                    diff+= 6-Math.abs(answer.getValue() - (candAnswers.getValue()));
                    break;
                }
            }
        }
        return Math.round(diff / (uAnswers.size()*6) * 100);
    }
}
