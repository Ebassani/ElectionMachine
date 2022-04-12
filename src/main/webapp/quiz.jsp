<%@ page import="com.github.ebassani.electionmachine.data.QuestionDao" %>
<%@ page import="com.github.ebassani.electionmachine.data.model.Question" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.github.ebassani.electionmachine.data.model.Region" %>
<%@ page import="com.github.ebassani.electionmachine.data.RegionDao" %>
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
<form method="post" action="${pageContext.request.contextPath}/quizz">

    <div class="questions">
        <%
            try {
                Question[] array = QuestionDao.getQuestions();
                for (Question question : array) {

                    String q = question.getQuestion();
                    int n = question.getId();
        %>
        <div class="question">
            <div class="statement"><% out.print(q); %></div>
            <div class="decision">
                <div class="agree">Agree</div>
                <div class="options">
                    <div class="option-agree"><input type="radio" required value="1" name="choice<% out.print(n);%>"></div>
                    <div class="option-agree"><input type="radio" required value="2" name="choice<% out.print(n);%>"></div>
                    <div class="option-agree"><input type="radio" required value="3" name="choice<% out.print(n);%>"></div>
                    <div class="neutral"><input type="radio" required value="4" name="choice<% out.print(n);%>"></div>
                    <div class="option-disagree"><input type="radio" required value="5" name="choice<% out.print(n);%>">
                    </div>
                    <div class="option-disagree"><input type="radio" required value="6" name="choice<% out.print(n);%>">
                    </div>
                    <div class="option-disagree"><input type="radio" required value="7" name="choice<% out.print(n);%>">
                    </div>
                </div>
                <div class="disagree">Disagree</div>
            </div>
        </div>
        <div class="divider"></div>
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
                <%
                    try
                    {
                        Region[] array = RegionDao.getRegions().toArray(new Region[0]);
                        for(Region region: array){
                            String r = region.getRegion();


                %>
                <option value="<%out.print(r); %>"><%out.print(r); %></option>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                %>

            </select>
        </div>
        <input type="submit" value="Submit" class="submit-button">
    </div>

</form>
</body>
</html>