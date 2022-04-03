package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.Database;
import freemarker.template.Configuration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(
        name = "Login",
        urlPatterns = {"/login"}
)
public class Login extends HttpServlet {

    Database db = Database.getInstance();
    Configuration cfg = FMConfiguration.getInstance();

    public Login() throws Exception {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Show Login page
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        cfg.getTemplate("templates/login.ftl");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {
            ResultSet result = db.statement.executeQuery("SELECT * FROM questions");
            while (result.next()) {
                System.out.println(result.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}