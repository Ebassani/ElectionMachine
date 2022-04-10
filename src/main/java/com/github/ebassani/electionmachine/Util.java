package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.Database;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
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

    public static Integer isSomebodyloggedIn(HttpSession session) {
        return (Integer) session.getAttribute("user_id");
    }

    public static boolean isAdmin(int id) throws SQLException {
        PreparedStatement statement = db.conn.prepareStatement("SELECT * from users where id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        rs.next();

        return rs.getBoolean("is_admin");
    }

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
//
//    /**
//     * Read the object from Base64 string.
//     */
//    public static Object fromString(String s) throws IOException,
//            ClassNotFoundException {
//        byte[] data = Base64.getDecoder().decode(s);
//        ObjectInputStream ois = new ObjectInputStream(
//                new ByteArrayInputStream(data));
//        Object o = ois.readObject();
//        ois.close();
//        return o;
//    }
//
//    /**
//     * Write the object to a Base64 string.
//     */
//    public static String toString(Serializable o) throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(o);
//        oos.close();
//        return Base64.getEncoder().encodeToString(baos.toByteArray());
//    }
}
