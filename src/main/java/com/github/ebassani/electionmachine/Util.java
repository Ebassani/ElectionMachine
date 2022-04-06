package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.Database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {

    static Database db;

    static {
        try {
            db = Database.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isAdmin(int id) throws SQLException {
        ResultSet rs = db.statement.executeQuery("SELECT * from users where id='" + id + "'");

        while (rs.next()) {
            if (rs.getBoolean("is_admin")) {
                return true;
            }
        }
        return false;
    }

    public static String hashPassword(String password) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/<>+-*".toCharArray();
        StringBuilder sb = new StringBuilder();
        MessageDigest msgDgst = null;
        try {
            msgDgst = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert msgDgst != null;
        byte[] bytes = msgDgst.digest(password.getBytes(StandardCharsets.UTF_8));
        for (Byte b : bytes) {
//            System.out.println(Byte.toUnsignedInt(b));
//            System.out.println(Byte.toUnsignedInt(b) % chars.length);
            int result = Byte.toUnsignedInt(b) % chars.length;
            sb.append(chars[result]);
        }

        return sb.toString();
    }
}
