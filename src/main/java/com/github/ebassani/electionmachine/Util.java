package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.Database;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Main utility class holding all the useful methods.
 */
public class Util {

    static Database db;

    static {
        try {
            db = Database.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the id of the user that's logged in.
     */
    public static Integer isSomebodyloggedIn(HttpSession session) {
        return (Integer) session.getAttribute("user_id");
    }

    /**
     * Returns true if the user with the id is an admin.
     */
    public static boolean isAdmin(int id) throws SQLException {
        PreparedStatement statement = db.conn.prepareStatement("SELECT * from users where id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        rs.next();

        return rs.getBoolean("is_admin");
    }

    /**
     * Returns true if the user with the id is a candidate.
     */
    public static boolean isCandidate(int id) throws SQLException {
        PreparedStatement statement = db.conn.prepareStatement("SELECT * from users where id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        rs.next();

        return rs.getBoolean("is_candidate");
    }

    /**
     * Hashes the password into a String that's savable into the database.
     */
    public static String hashPassword(String password) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/<>+-*".toCharArray();
        StringBuilder sb = new StringBuilder();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert messageDigest != null;
        byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        for (Byte b : bytes) {
            int result = Byte.toUnsignedInt(b) % chars.length;
            sb.append(chars[result]);
        }

        return sb.toString();
    }
}
