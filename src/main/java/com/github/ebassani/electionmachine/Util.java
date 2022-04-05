package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.Database;

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
}
