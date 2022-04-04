package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.data.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

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

    public static void editUser(int id, User newUser) throws SQLException {
        db.statement.executeUpdate("UPDATE users " +
                "SET email='" + newUser.getEmail() +"'," +
                "is_admin='" + (newUser.isAdmin() ? "1" : "0") + "'," +
                "is_candidate='" + (newUser.isCandidate() ? "1" : "0") + "'," +
                "names='" + newUser.getNames() + "'," +
                "surnames='" + newUser.getSurnames() + "'," +
                "region='" + newUser.getRegion() + "'," +
                "age='" + newUser.getAge() + "' " +
                "WHERE id='" + id + "'");
    }

    public static void addUser(User user) throws SQLException {
        db.statement.executeQuery("INSERT INTO users (email, password_hash, is_admin, is_candidate, names, surnames, region, age) VALUES(" +
                "email='" + user.getEmail() +"'," +
                "password_hash='"+user.getPasswordHash()+"',"+
                "is_admin='" + (user.isAdmin() ? "1" : "0") + "'," +
                "is_candidate='" + (user.isCandidate() ? "1" : "0") + "'," +
                "names='" + user.getNames() + "'," +
                "surnames='" + user.getSurnames() + "'," +
                "region='" + user.getRegion() + "'," +
                "age='" + user.getAge() + "' )");
    }
}
