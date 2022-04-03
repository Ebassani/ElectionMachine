package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.Database;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

        if (req.getSession().getAttribute("user_id") != null) {
            resp.sendRedirect("index.html");
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
            ResultSet rs = db.statement.executeQuery("SELECT COUNT(*) FROM users WHERE email='"
                    + request.getParameter("email") + "' AND password_hash='"
            + request.getParameter("password") + "'");
            rs.next();
            int matches = rs.getInt(1);
            if (matches == 1) {
                // login
                rs = db.statement.executeQuery("SELECT * FROM users WHERE email='"
                        + request.getParameter("email") + "' AND password_hash='"
                        + request.getParameter("password") + "'");
                rs.next();
                int userId = rs.getInt("id");
                HttpSession session = request.getSession();
                session.setAttribute("user_id", userId);

                response.sendRedirect("index.html");
            } else {
                // send back to login page
                response.sendRedirect("/login?error=Incorrect username or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}