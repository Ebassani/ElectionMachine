package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.QuestionDao;
import com.github.ebassani.electionmachine.data.model.Question;

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

        out.print("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>AddQuestion</title>" +
                "    <link rel=\"stylesheet\" href=\"/style/question-management.css\">" +
                "    <script src=\"/js/question-management.js\"></script>" +
                "</head>" +
                "<body>");

        QuestionDao qm= null;
        try {
            qm = new QuestionDao();
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

        out.print("<button onclick=\"toVisible('create')\">Add question</button>");

        out.print("<div>");
        for (int i=0;i<questions.length;i++){
            out.print("<div name=\"question "+i+" \">");
            out.print(questions[i].getQuestion());
            out.print("<button onclick=\"getQuestion" +
                    "('"+questions[i].getId()+"',\'"+questions[i].getQuestion()+"\')\">Edit</button>");
            out.print("<button onclick=\"delQuestion('"+questions[i].getId()+"')\">Delete</button>");
            out.print("</div>");
        }
        out.print("</div>");

        out.print("<div id='edit' class=\"popup hidden\">"+
                "<form method='post' action='/questionHandler'>" +
                "<input type=\"hidden\" id='q_id' name='id' value=''>"+
                "<input type=\"text\" id='question' name='question' " +
                "placeholder='Your question here' value=''>" +
                "<input type=\"submit\">" +
                "<button onclick=\"toHidden('edit')\" type='button'>Cancel</button>"+
                "</form>"+
                "</div>");

        out.print("<div id=\"delete\" class=\"popup hidden\">" +
                "<h3>Are you sure you want to delete this question?</h3>"+
                "<form method='post' action='/questionHandler'>" +
                "<input type=\"hidden\" id='id' name='id' value=''>"+
                "<input type=\"submit\" value='YES'>" +
                "<button onclick=\"toHidden('delete')\" type='button'>NO</button>"+
                "</form>"+
                "</div>");

        out.print("<div id=\"create\" class=\"popup hidden\">" +
                "<h3>Write the new question here:</h3>"+
                "<form method='post' action='/questionHandler'>" +
                "<input type=\"text\" name='question'>"+
                "<input type=\"submit\">" +
                "<button onclick=\"toHidden('create')\" type='button'>Cancel</button>" +
                "</form>"+
                "</div>");

        out.print("</body>" +
                "</html>");
    }
}
