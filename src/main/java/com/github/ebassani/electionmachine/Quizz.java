package com.github.ebassani.electionmachine;
import com.github.ebassani.electionmachine.data.AnswerDao;
import com.github.ebassani.electionmachine.data.Database;
import com.github.ebassani.electionmachine.data.QuestionDao;
import com.github.ebassani.electionmachine.data.UserDao;
import com.github.ebassani.electionmachine.data.model.Answer;
import com.github.ebassani.electionmachine.data.model.Question;
import com.github.ebassani.electionmachine.data.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(
        name = "Quizz",
        urlPatterns = {"/quizz"}
)
public class Quizz extends HttpServlet {

    Database db = Database.getInstance();

    public Quizz() throws Exception {}

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

        try{
            assert var != null;
            Question[] array = var.getQuestions();
            for (Question question : array) {

                String q = question.getQuestion();
                int n = question.getId();


                out.print(
                        "    <div class=\"statement\" name=\"statement\">" + q + "</div>\n" +
                                "    <div class=\"decision\">\n" +
                                "        <div class=\"agree\">Agree</div>\n" +
                                "        <div class=\"options\">\n" +
                                "            <div class=\"option-agree\"> <input type=\"radio\" required value=\"0\" name=\"choice" + n + "\"></div>\n" +
                                "            <div class=\"option-agree\"> <input type=\"radio\" required value=\"1\" name=\"choice" + n + "\"></div>\n" +
                                "            <div class=\"option-agree\"> <input type=\"radio\" required value=\"2\" name=\"choice" + n + "\"></div>\n" +
                                "            <div class=\"neutral\"><input type=\"radio\" required value=\"3\" name=\"choice" + n + "\"></div>\n" +
                                "            <div class=\"option-disagree\"><input type=\"radio\" required value=\"4\" name=\"choice" + n + "\"></div>\n" +
                                "            <div class=\"option-disagree\"><input type=\"radio\" required value=\"5\" name=\"choice" + n + "\"></div>\n" +
                                "            <div class=\"option-disagree\"><input type=\"radio\" required value=\"6\" name=\"choice" + n + "\"></div>\n" +
                                "        </div>\n" +
                                "        <div class=\"disagree\">Disagree</div>\n" +
                                "    </div>");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        out.print("    <div class=\"age-region\">\n" +
                "        <label class=\"age-region-text\">Insert your Age</label>\n" +
                "    <input type=\"number\" name=\"age\" value=\"Your age\" required min=\"18\" max=\"110\">\n" +
                "    <label for=\"region\" class=\"age-region-text\">Choose a Region:</label>\n" +
                "    <select name=\"region\" id=\"region\" required>\n" +
                "        <option value=\"Ahvenanmaa\">Ahvenanmaa</option>\n" +
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        try {
            id = UserDao.addAnonUser(req.getParameter("region"), req.getParameter("age"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Enumeration<String> tempParamNames = req.getParameterNames();
        ArrayList<String> paramNames = new ArrayList<>();
        while (tempParamNames.hasMoreElements()) {
            paramNames.add(tempParamNames.nextElement());
        }

        List<Integer> choices = paramNames.stream()
                .filter(param -> param.startsWith("choice"))
                .map(param -> Integer.valueOf(param.substring(6))).collect(Collectors.toList());

        for (int choice: choices) {
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



//            answer.setUserId(id);
//
//            assert questionDao != null;
//            Question[] array = questionDao.getQuestions();
//
//
//                for(int i = 1; i<= array.length ; i++){
//                    String value = req.getParameter("choice" + i);
//
//                       answer.setValue(Integer.parseInt(value));
//                       answer.setQuestionId(i);
//                       AnswerDao.addAnswer(answer);
//
//
//                }


    }
}