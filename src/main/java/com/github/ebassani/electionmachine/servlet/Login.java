package com.github.ebassani.electionmachine.servlet;

import com.github.ebassani.electionmachine.FMConfiguration;
import com.github.ebassani.electionmachine.Util;
import com.github.ebassani.electionmachine.data.Database;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        System.out.println(Util.hashPassword("lol"));

        if (req.getSession().getAttribute("user_id") != null) {
            resp.sendRedirect("/index.html");
        }

        Template tmp = cfg.getTemplate("login.ftl");

        try {
            Map<String, Object> root = new HashMap<>();
            String error = Objects.toString(req.getParameter("error"), "");
            root.put("error", error);
            tmp.process(root, resp.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {
            String hashedPassword = Util.hashPassword(request.getParameter("password"));
            PreparedStatement statement = db.conn.prepareStatement(
                    "SELECT COUNT(*) FROM users WHERE email=? AND password_hash=?"
            );
            statement.setString(1, request.getParameter("email"));
            statement.setString(2, hashedPassword);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int matches = rs.getInt(1);
            if (matches == 1) {
                // login
                statement = db.conn.prepareStatement("SELECT * FROM users WHERE email=? AND password_hash=?");
                statement.setString(1, request.getParameter("email"));
                statement.setString(2, hashedPassword);
                rs = statement.executeQuery();
                rs.next();
                int userId = rs.getInt("id");
                boolean userAdmin = rs.getBoolean("is_admin");
                HttpSession session = request.getSession();
                session.setAttribute("user_id", userId);

                if (userAdmin) response.sendRedirect("/user-management");
                else response.sendRedirect("index.html");
            } else {
                // send back to login page
                response.sendRedirect("/login?error=Incorrect username or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}