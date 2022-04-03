package com.github.ebassani.electionmachine;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import static j2html.TagCreator.*;

@WebServlet(
        name = "AdmQuestions",
        urlPatterns = {"/admquestion"}
)

public class AdminQuestions extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        QuestionManagement qm= null;
        try {
            qm = new QuestionManagement();
        } catch (Exception e) {

        }

        Question[] questions=null;
        try {
            questions = qm.getQuestions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.print(body(
                h1("Questions")
        ).render());

        out.print("<div>");
        for (int i=0;i<questions.length;i++){
            out.print("<div>");
            out.print(questions[i].getQuestion());
            out.print("<button>Edit</button>");
            out.print("<button>Delete</button>");
            out.print("</div>");
        }
        out.print("</div>");
    }
}
