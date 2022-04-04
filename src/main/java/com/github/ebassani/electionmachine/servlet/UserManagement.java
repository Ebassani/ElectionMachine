package com.github.ebassani.electionmachine.servlet;

import com.github.ebassani.electionmachine.FMConfiguration;
import com.github.ebassani.electionmachine.data.Database;
import com.github.ebassani.electionmachine.data.UserDao;
import com.github.ebassani.electionmachine.data.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        name = "UserManagement",
        urlPatterns = {"/user-management"}
)
public class UserManagement extends HttpServlet {

    Database db = Database.getInstance();
    Configuration cfg = FMConfiguration.getInstance();

    public UserManagement() throws Exception {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Show the candidates page
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        Template tmp = cfg.getTemplate("user-management.ftl");

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("users", UserDao.getUsers());
            tmp.process(root, resp.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // the post method edits users

        // do the work
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            User user = new User();

            user.setNames(request.getParameter("names"));
            user.setSurnames(request.getParameter("surnames"));
            user.setAdmin(Boolean.parseBoolean(request.getParameter("admin")));
            user.setCandidate(!Boolean.parseBoolean(request.getParameter("admin")));
            user.setAge(Integer.parseInt(request.getParameter("age")));
            user.setRegion(request.getParameter("region"));

            UserDao.editUser(id, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // redirect back to the user management page
        response.sendRedirect("/user-management");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // deletes candidates
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // creates candidates
    }
}