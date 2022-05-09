package com.github.ebassani.electionmachine.servlet;

import com.github.ebassani.electionmachine.FMConfiguration;
import com.github.ebassani.electionmachine.Util;
import com.github.ebassani.electionmachine.data.RegionDao;
import com.github.ebassani.electionmachine.data.UserDao;
import com.github.ebassani.electionmachine.data.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(
        name = "UserManagement",
        urlPatterns = {"/user-management"}
)
public class UserManagement extends HttpServlet {

    Configuration cfg = FMConfiguration.getInstance();

    public UserManagement() throws Exception {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // if not logged in as admin, redirect to quiz
        if (req.getSession().getAttribute("user_id") != null) {
            int userId = (int) req.getSession().getAttribute("user_id");
            boolean isAdmin;
            try {
                isAdmin = Util.isAdmin(userId);
                if (!isAdmin) {
                    resp.sendRedirect("/index.jsp");
                }
            } catch (SQLException ignored) {}
        } else {
            resp.sendRedirect("/login");
        }

        // Show the candidates page
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        Template tmp = cfg.getTemplate("user-management.ftl");

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("users", UserDao.getUsers().stream().filter(user -> user.getEmail() != null).collect(Collectors.toList()));
            root.put("regions", RegionDao.getRegions());
            tmp.process(root, resp.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");
        if (Objects.equals(action, "edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                User user = new User();

                user.setNames(request.getParameter("names"));
                user.setSurnames(request.getParameter("surnames"));
                user.setAdmin(Objects.equals(request.getParameter("admin"), "true"));
                user.setCandidate(Objects.equals(request.getParameter("admin"), "false"));
                user.setAge(Integer.parseInt(request.getParameter("age")));
                user.setRegion(request.getParameter("region"));

                UserDao.editUser(id, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(action, "delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                UserDao.removeUser(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(action, "create")) {
            try {
                if (UserDao.existsWithEmail(request.getParameter("email"))) {
                    response.sendRedirect("/user-management");
                    return;
                }

                User user = new User();

                user.setEmail(request.getParameter("email"));
                user.setPasswordHash(Util.hashPassword(request.getParameter("password")));
                user.setNames(request.getParameter("names"));
                user.setSurnames(request.getParameter("surnames"));
                user.setAdmin(Objects.equals(request.getParameter("admin"), "true"));
                user.setCandidate(Objects.equals(request.getParameter("admin"), "false"));
                user.setAge(Integer.parseInt(request.getParameter("age")));
                user.setRegion(request.getParameter("region"));

                UserDao.addUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // redirect back to the user management page
        response.sendRedirect("/user-management");
    }
}