package com.github.ebassani.electionmachine.servlet;

import com.github.ebassani.electionmachine.data.AnswerDao;
import com.github.ebassani.electionmachine.data.UserDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(
        name = "SaveUser",
        urlPatterns = {"/save-user"}
)
public class SaveUser extends HttpServlet {

    public SaveUser() {}

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = 0;

        if (req.getSession().getAttribute("user_id") != null) {
            id = (int) req.getSession().getAttribute("user_id");
        } else {
            try {
                id = UserDao.addAnonUser(req.getParameter("region"), req.getParameter("age"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Enumeration<String> tempParamNames = req.getParameterNames();
        ArrayList<String> paramNames = new ArrayList<>();
        while (tempParamNames.hasMoreElements()) {
            paramNames.add(tempParamNames.nextElement());
        }

        List<Integer> choices = paramNames.stream()
                .filter(param -> param.startsWith("choice"))
                .map(param -> Integer.valueOf(param.substring(6))).collect(Collectors.toList());

        for (int choice : choices) {
            int value = Integer.parseInt(req.getParameter("choice" + choice));
            try {
                AnswerDao.editAnswerValue(choice, id, value);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        UserDao.editUserAge(id, Integer.parseInt(req.getParameter("age")));
        UserDao.editUserRegion(id, req.getParameter("region"));

        resp.sendRedirect("/candidateQuestions.jsp");
    }
}