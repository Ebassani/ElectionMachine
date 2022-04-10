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
}
