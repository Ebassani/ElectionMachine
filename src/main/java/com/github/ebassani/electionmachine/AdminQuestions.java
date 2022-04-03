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

        out.print("<div>");
        for (int i=0;i<questions.length;i++){
            out.print("<div>");
            out.print(questions[i].getQuestion());
            out.print("<button id=\"butt\" onclick=\"getQuestion" +
                    "("+questions[i].getId()+",\'"+questions[i].getQuestion()+"\')\">Edit</button>");
            out.print("<button>Delete</button>");
            out.print("</div>");
        }
        out.print("</div>");

        out.print("<div id=\"hey\" class=\"popup hidden\">"+
                "<form>" +
                "<input type=\"hidden\" id='id' name='id' value=''>"+
                "<input type=\"text\" id='question' name='question' " +
                "placeholder='Your question here' value=''>" +
                "<input type=\"submit\">"+
                "</form>"+
                "</div>");

        out.print("</div>");


        out.print("</body>" +
                "<script>" +
                "function change() {" +
                "    document.getElementById(\"butt\").innerHTML = \"hello\"" +
                "}" +
                "function getQuestion(number,question) {" +
                "    document.getElementById(\"question\").value = question" +
//                "    document.getElementById(\"id\").value = number" +
                "}" +
                "</script>" +
                "</html>");
    }
}
