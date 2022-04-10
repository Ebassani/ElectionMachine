package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.data.model.Answer;
import com.github.ebassani.electionmachine.data.model.User;

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

    public static void addAnswer(Answer answer) throws SQLException {
        PreparedStatement statement = db.conn.prepareStatement(
                "INSERT INTO answers(question_id, user_id, value) VALUE (?,?,?)"
        );
        statement.setInt(1, answer.getQuestionId());
        statement.setInt(2, answer.getUserId());
        statement.setInt(3, answer.getValue());
        statement.executeUpdate();
    }

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

    public static int compareAnswers(List<Answer> uAnswers, List<Answer> cAnswers) {
        int diff=0;

        for (Answer answer : uAnswers){
            for (Answer candAnswers : cAnswers){
                if (answer.getQuestionId() == candAnswers.getQuestionId()){
                    diff+=Math.abs(answer.getValue() - candAnswers.getValue());
                    break;
                }
            }
        }

        return diff;
    }
}
