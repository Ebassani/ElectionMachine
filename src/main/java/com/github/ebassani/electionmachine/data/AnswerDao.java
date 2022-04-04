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

    public static List<User> getUsers() throws SQLException {
        ResultSet rs = db.statement.executeQuery("SELECT * FROM users");
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPasswordHash(rs.getString("password_hash"));
            user.setAdmin(rs.getBoolean("is_admin"));
            user.setCandidate(rs.getBoolean("is_candidate"));
            user.setNames(rs.getString("names"));
            user.setSurnames(rs.getString("surnames"));
            user.setRegion(rs.getString("region"));
            user.setAge(rs.getInt("age"));
            users.add(user);
        }
        return users;
    }

    public static void addAnswer(Answer answer) throws SQLException {
        db.statement.executeUpdate("INSERT INTO answers(question_id, user_id, value) VALUE () ");
    }
}
