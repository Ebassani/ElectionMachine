<%@ page import="com.github.ebassani.electionmachine.data.QuestionDao" %>
<%@ page import="com.github.ebassani.electionmachine.data.model.Question" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="style/style.css">
    <title>Title</title>
</head>
<body>
<%
    QuestionDao var = null;
    try {
        var = new QuestionDao();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<form method="post" action="/quizz">

    <div class="question">
        <%
            try {
                assert var != null;
                Question[] array = var.getQuestions();
                for (Question question : array) {

                    String q = question.getQuestion();
                    int n = question.getId();
        %>
        <div class="statement" name="statement"><% out.print(q); %></div>
        <div class="decision">
            <div class="agree">Agree</div>
            <div class="options">
                <div class="option-agree"><input type="radio" required value="1" name="choice<% out.print(n);%>"></div>
                <div class="option-agree"><input type="radio" required value="2" name="choice<% out.print(n);%>"></div>
                <div class="option-agree"><input type="radio" required value="3" name="choice<% out.print(n);%>"></div>
                <div class="neutral"><input type="radio" required value="4" name="choice<% out.print(n);%>"></div>
                <div class="option-disagree"><input type="radio" required value="5" name="choice<% out.print(n);%>"></div>
                <div class="option-disagree"><input type="radio" required value="6" name="choice<% out.print(n);%>"></div>
                <div class="option-disagree"><input type="radio" required value="7" name="choice<% out.print(n);%>"></div>
            </div>
            <div class="disagree">Disagree</div>
        </div>

        <%
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>

        <div class="age-region">
            <label class="age-region-text">Insert your Age</label>
            <input type="number" name="age" value="Your age" required min="18" max="110">
            <label for="region" class="age-region-text">Choose a Region:</label>
            <select name="region" id="region" required>
                <option value="Ahvenamaa">Ahvenamaa</option>
                <option value="Etelä-Karjala">Etelä-Karjala</option>
                <option value="Etelä-Pohjanmaa">Etelä-Pohjanmaa</option>
                <option value="Kainuu">Kainuu</option>
                <option value="Kanta-Häme">Kanta-Häme</option>
                <option value="Keski-Pohjanmaa">Keski-Pohjanmaa</option>
                <option value="Keski-Suomi">Keski-Suomi</option>
                <option value="Kymenlaakso">Kymenlaakso</option>
                <option value="Lappi">Lappi</option>
                <option value="Päijät-Häme">Päijät-Häme</option>
                <option value="Pirkanmaa">Pirkanmaa</option>
                <option value="Pohjanmaa">Pohjanmaa</option>
                <option value="Pohjois-Karjala">Pohjois-Karjala</option>
                <option value="Pohjois-Pohjanmaa">Pohjois-Pohjanma</option>
                <option value="Pohjois-Savo">Pohjois-Savo</option>
                <option value="Satakunta">Satakunta</option>
                <option value="Uusimaa">Uusimaa</option>
                <option value="Varsinais-Suomi">Varsinais-Suomi</option>

            </select>
        </div>
        <input type="submit" value="Submit" class="submit-button">
    </div>

</form>
</body>
</html>