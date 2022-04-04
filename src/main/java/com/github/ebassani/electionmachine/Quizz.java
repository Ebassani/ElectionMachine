package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.data.QuestionDao;
import com.github.ebassani.electionmachine.data.model.Question;
import j2html.tags.specialized.TbodyTag;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static j2html.TagCreator.*;

@WebServlet(
        name = "Quizz",
        urlPatterns = {"/quizz"}
)
public class Quizz extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        QuestionDao var = null;
        try {
            var = new QuestionDao();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"content-type\" content=\"application/xhtml+xml; charset=UTF-8\"/>\n" +
                "    <link rel=\"stylesheet\" href=\"style/style.css\">\n" +
                "    <title>Quizz</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"post\" action=\"/quizz\">\n" + "<div class=\"question\">\n");

        int n = 0;

        try{
            Question[] array = var.getQuestions();
            for (int i=0;i<array.length;i++) {

              String q = array[i].getQuestion();


               out.print(
                       "    <div class=\"statement\" name=\"statement\">"+q+"</div>\n" +
                       "    <div class=\"decision\">\n" +
                       "        <div class=\"agree\">Agree</div>\n" +
                       "        <div class=\"options\">\n" +
                       "            <div class=\"option-agree\"> <input type=\"radio\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-agree\"> <input type=\"radio\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-agree\"> <input type=\"radio\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"neutral\"><input type=\"radio\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-disagree\"><input type=\"radio\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-disagree\"><input type=\"radio\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-disagree\"><input type=\"radio\" name=\"choice"+n+"\"></div>\n" +
                       "        </div>\n" +
                       "        <div class=\"disagree\">Disagree</div>\n" +
                       "    </div>");
               n++;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        out.print("<input type=\"submit\" value=\"Submit\" class=\"submit-button\">\n" +
                "</div>\n" +
                "\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
  }
}