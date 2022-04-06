package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.data.model.Answer;
import com.github.ebassani.electionmachine.data.model.User;

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
        db.statement.executeUpdate("INSERT INTO answers(question_id, user_id, value) VALUE (" +
                "'" + answer.getQuestionId() + "'," +
                "'" + answer.getUserId() + "'," +
                "'" + answer.getValue() + "') ");
    }
}
