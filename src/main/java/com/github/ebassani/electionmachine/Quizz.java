package com.github.ebassani.electionmachine;
import com.github.ebassani.electionmachine.data.Database;
import com.github.ebassani.electionmachine.data.QuestionDao;
import com.github.ebassani.electionmachine.data.model.Question;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;




@WebServlet(
        name = "Quizz",
        urlPatterns = {"/quizz"}
)
public class Quizz extends HttpServlet {

    Database db = Database.getInstance();

    public Quizz() throws Exception {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        QuestionDao var = null;


        try {
            var = new  QuestionDao ();
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

        int n = 1;

        try{
            Question[] array = var.getQuestions();
            for (int i=0;i<array.length;i++) {

              String q = array[i].getQuestion();


               out.print(
                       "    <div class=\"statement\" name=\"statement\">"+q+"</div>\n" +
                       "    <div class=\"decision\">\n" +
                       "        <div class=\"agree\">Agree</div>\n" +
                       "        <div class=\"options\">\n" +
                       "            <div class=\"option-agree\"> <input type=\"radio\" value=\"0\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-agree\"> <input type=\"radio\" value=\"1\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-agree\"> <input type=\"radio\" value=\"2\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"neutral\"><input type=\"radio\" value=\"3\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-disagree\"><input type=\"radio\" value=\"4\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-disagree\"><input type=\"radio\" value=\"5\" name=\"choice"+n+"\"></div>\n" +
                       "            <div class=\"option-disagree\"><input type=\"radio\" value=\"6\" name=\"choice"+n+"\"></div>\n" +
                       "        </div>\n" +
                       "        <div class=\"disagree\">Disagree</div>\n" +
                       "    </div>");
               n++;
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }

        out.print("<div class=\"age-region\">\n" +
                "        <label class=\"age-region-text\">Insert your Age</label>\n" +
                "    <input type=\"number\" value=\"Your age\" required min=\"18\" max=\"110\">\n" +
                "    <label for=\"regions\" class=\"age-region-text\">Choose a Region:</label>\n" +
                "    <select name=\"regions\" id=\"regions\" required>\n" +
                "        <option value=\"Ahvenamaa\">Ahvenamaa</option>\n" +
                "        <option value=\"Etelä-Karjala\">Etelä-Karjala</option>\n" +
                "        <option value=\"Etelä-Pohjanmaa\">Etelä-Pohjanmaa</option>\n" +
                "        <option value=\"Kainuu\">Kainuu</option>\n" +
                "        <option value=\"Kanta-Häme\">Kanta-Häme</option>\n" +
                "        <option value=\"Keski-Pohjanmaa\">Keski-Pohjanmaa</option>\n" +
                "        <option value=\"Keski-Suomi\">Keski-Suomi</option>\n" +
                "        <option value=\"Kymenlaakso\">Kymenlaakso</option>\n" +
                "        <option value=\"Lappi\">Lappi</option>\n" +
                "        <option value=\"Päijät-Häme\">Päijät-Häme</option>\n" +
                "        <option value=\"Pirkanmaa\">Pirkanmaa</option>\n" +
                "        <option value=\"Pohjanmaa\">Pohjanmaa</option>\n" +
                "        <option value=\"Pohjois-Karjala\">Pohjois-Karjala</option>\n" +
                "        <option value=\"Pohjois-Pohjanmaa\">Pohjois-Pohjanma</option>\n" +
                "        <option value=\"Pohjois-Savo\">Pohjois-Savo</option>\n" +
                "        <option value=\"Satakunta\">Satakunta</option>\n" +
                "        <option value=\"Uusimaa\">Uusimaa</option>\n" +
                "        <option value=\"Varsinais-Suomi\">Varsinais-Suomi</option>\n" +
                "\n" +
                "    </select>\n" +
                "    </div>\n" +
                "    <input type=\"submit\" value=\"Submit\" class=\"submit-button\">\n" +
                "</div>\n" +
                "\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");


  }

}