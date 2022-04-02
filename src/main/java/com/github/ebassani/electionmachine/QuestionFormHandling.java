package com.github.ebassani.electionmachine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "addQ",
        urlPatterns = {"/addq"}
)

public class QuestionFormHandling extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        QuestionManagement var =  new QuestionManagement();

        String text = request.getParameter("question");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (text==null){
                var.deleteQuestion(id);
                response.getWriter().println("question deleted");
            }
            else {
                var.updateQuestion(text,id);
                response.getWriter().println("question updated");
            }
        } catch (Exception e){
            if (text != null){
                var.createQuestion(text);
                response.getWriter().println("question created");
            }
            else {
                response.getWriter().println("Nothing happened");
            }
        }
    }
}
