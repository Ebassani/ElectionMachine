package com.github.ebassani.electionmachine.data;

import com.github.ebassani.electionmachine.data.model.User;

import java.sql.PreparedStatement;
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
        ResultSet rs = db.conn.createStatement().executeQuery("SELECT * FROM users");
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

    public static List<User> getCandidates() throws SQLException {
        ResultSet rs = db.conn.createStatement().executeQuery("SELECT * FROM users WHERE is_candidate=1");
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
        PreparedStatement statement = db.conn.prepareStatement(
                "UPDATE users SET is_admin=?, is_candidate=?, names=?, surnames=?, region=?, age=? WHERE id=?"
        );

        statement.setBoolean(1, newUser.isAdmin());
        statement.setBoolean(2, newUser.isCandidate());
        statement.setString(3, newUser.getNames());
        statement.setString(4, newUser.getSurnames());
        statement.setString(5, newUser.getRegion());
        statement.setInt(6, newUser.getAge());
        statement.setInt(7, id);

        statement.executeUpdate();
    }

    public static int addUser(User user) throws  SQLException {
        PreparedStatement statement = db.conn.prepareStatement(
                "INSERT INTO users (email, password_hash, is_admin, is_candidate, names, surnames, region, age) " +
                        "VALUES (?,?,?,?,?,?,?,?)"
        );
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPasswordHash());
        statement.setBoolean(3, user.isAdmin());
        statement.setBoolean(4, user.isCandidate());
        statement.setString(5, user.getNames());
        statement.setString(6, user.getSurnames());
        statement.setString(7, user.getRegion());
        statement.setInt(8, user.getAge());
        statement.executeUpdate();
        ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID();");
        rs.next();
        return rs.getInt(1);
    }

    public static int addAnonUser(String region, String age) throws  SQLException {
        PreparedStatement statement = db.conn.prepareStatement(
                "INSERT INTO users (email, password_hash, is_admin, is_candidate, names, surnames, region, age) " +
                        "VALUES (NULL, NULL, 0, 0, NULL, NULL, ?, ?)"
        );
        statement.setString(1, region);
        statement.setString(2, age);
        statement.executeUpdate();

        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public static void removeUser(int id) throws SQLException {
        db.conn.createStatement().executeUpdate("DELETE FROM answers WHERE user_id='" + id + "'");
        db.conn.createStatement().executeUpdate("DELETE FROM users WHERE id='" + id + "'");
    }

    public static boolean existsWithEmail(String email) throws SQLException {
        PreparedStatement statement = db.conn.prepareStatement("SELECT COUNT(*) FROM users WHERE email=?");
        statement.setString(1, email);
        ResultSet rs = statement.executeQuery();
        rs.next();
        System.out.println(rs.getInt(1));
        return rs.getInt(1) >= 1;
    }
}