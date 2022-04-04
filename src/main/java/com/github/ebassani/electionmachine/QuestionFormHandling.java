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

        QuestionDao var = null;
        try {
            var = new QuestionDao();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String text = request.getParameter("question");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (text == null) {
                var.deleteQuestion(id);
                response.getWriter().println("question deleted");
            } else {
                var.updateQuestion(text, id);
                response.getWriter().println("question updated");
            }
        } catch (Exception e) {
            if (text != null) {
                var.createQuestion(text);
                response.getWriter().println("question created");
            } else {
                response.getWriter().println("Nothing happened");
            }
        }

//        try {
//            response.getWriter().println(var.getQuestionWithId(2).getQuestion());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            Question[] array = var.getQuestions();
//            for (int i=0;i<array.length;i++) {
//                response.getWriter().println(array[i].getQuestion());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
