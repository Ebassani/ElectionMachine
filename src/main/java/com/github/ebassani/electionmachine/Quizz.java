package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.AnswerDao;
import com.github.ebassani.electionmachine.data.UserDao;
import com.github.ebassani.electionmachine.data.model.Answer;
import com.github.ebassani.electionmachine.data.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@WebServlet(
        name = "Quizz",
        urlPatterns = {"/quizz"}
)
public class Quizz extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        int id = 0;

        if (req.getSession().getAttribute("user_id") != null) {
            id = (int) req.getSession().getAttribute("user_id");
        } else {
            try {
                id = UserDao.addAnonUser(req.getParameter("region"), req.getParameter("age"));
                req.getSession().setAttribute("user_id", id);
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
            Answer answer = new Answer();
            answer.setValue(value);
            answer.setUserId(id);
            answer.setQuestionId(choice);
            try {
                AnswerDao.addAnswer(answer);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("/result.jsp");

    }
}