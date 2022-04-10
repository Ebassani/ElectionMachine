package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.QuestionDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "questionHandler",
        urlPatterns = {"/questionHandler"}
)

public class QuestionFormHandling extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String text = request.getParameter("question");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (text == null) {
                QuestionDao.deleteQuestion(id);
                response.getWriter().println("question deleted");
                response.sendRedirect("/admQuestions.jsp");
            } else {
                QuestionDao.updateQuestion(text, id);
                response.getWriter().println("question updated");
                response.sendRedirect("/admQuestions.jsp");
            }
        } catch (Exception e) {
            if (text != null) {
                QuestionDao.createQuestion(text);
                response.getWriter().println("question created");
                response.sendRedirect("/admQuestions.jsp");
            } else {
                response.getWriter().println("Nothing happened");
            }
        }

    }
}
