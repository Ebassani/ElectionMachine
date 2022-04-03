package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.Database;
import com.github.ebassani.electionmachine.data.UserDao;
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
        name = "Candidates",
        urlPatterns = {"/candidates"}
)
public class Candidates extends HttpServlet {

    Database db = Database.getInstance();
    Configuration cfg = FMConfiguration.getInstance();

    public Candidates() throws Exception {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Show the candidates page
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        Template tmp = cfg.getTemplate("candidates.ftl");

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("candidates", UserDao.getUsers());
            tmp.process(root, resp.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}