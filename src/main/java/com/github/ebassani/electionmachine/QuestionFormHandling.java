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

        QuestionDao dao = null;
        try {
            dao = new QuestionDao();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String text = request.getParameter("question");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (text == null) {
                dao.deleteQuestion(id);
                response.getWriter().println("question deleted");
                response.sendRedirect("/admQuestions.jsp");
            } else {
                dao.updateQuestion(text, id);
                response.getWriter().println("question updated");
                response.sendRedirect("/admQuestions.jsp");
            }
        } catch (Exception e) {
            if (text != null) {
                dao.createQuestion(text);
                response.getWriter().println("question created");
                response.sendRedirect("/admQuestions.jsp");
            } else {
                response.getWriter().println("Nothing happened");
            }
        }

    }
}
