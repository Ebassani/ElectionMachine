<%@ page import="com.github.ebassani.electionmachine.data.QuestionDao" %>
<%@ page import="com.github.ebassani.electionmachine.data.model.Question" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.github.ebassani.electionmachine.data.model.Region" %>
<%@ page import="com.github.ebassani.electionmachine.data.RegionDao" %>
<%@ page import="com.github.ebassani.electionmachine.Util" %>
<%@ page import="com.github.ebassani.electionmachine.data.model.Answer" %>
<%@ page import="com.github.ebassani.electionmachine.data.AnswerDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.github.ebassani.electionmachine.data.model.User" %>
<%@ page import="com.github.ebassani.electionmachine.data.UserDao" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    // check if the candidate is logged in
    int uid = 0;
    if (request.getSession().getAttribute("user_id") != null) {
        uid = (int) request.getSession().getAttribute("user_id");
        boolean isCandidate;
        try {
            isCandidate = Util.isCandidate(uid);
            if (!isCandidate) {
                response.sendRedirect("/index.jsp");
            }
        } catch (SQLException ignored) {
        }
    } else {
        response.sendRedirect("/login");
    }
%>
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
<form method="post" action="${pageContext.request.contextPath}/save-user">
    <div class="questions">
        <%
            try {
                Question[] array = QuestionDao.getQuestions();
                List<Answer> answers = AnswerDao.getUserAnswers(uid);

                if (answers.size() != array.length) response.sendRedirect("/quiz.jsp");

                for (Question question : array) {
                    for (Answer answer : answers) {
                        if (answer.getQuestionId() == question.getId()) {
                            String q = question.getQuestion();
                            int n = question.getId();
                            int v = answer.getValue();
                            System.out.println(v);
        %>
        <div class="question">
            <div class="statement"><% out.print(q); %></div>
            <div class="decision">
                <div class="agree">Agree</div>
                <div class="options">
                    <div class="option-agree"><input <% if (v==1) out.print("checked"); %> type="radio" required
                                                                                           value="1"
                                                                                           name="choice<% out.print(n);%>">
                    </div>
                    <div class="option-agree"><input <% if (v==2) out.print("checked"); %> type="radio" required
                                                                                           value="2"
                                                                                           name="choice<% out.print(n);%>">
                    </div>
                    <div class="option-agree"><input <% if (v==3) out.print("checked"); %> type="radio" required
                                                                                           value="3"
                                                                                           name="choice<% out.print(n);%>">
                    </div>
                    <div class="neutral"><input <% if (v==4) out.print("checked"); %> type="radio" required value="4"
                                                                                      name="choice<% out.print(n);%>">
                    </div>
                    <div class="option-disagree"><input <% if (v==5) out.print("checked"); %> type="radio" required
                                                                                              value="5"
                                                                                              name="choice<% out.print(n);%>">
                    </div>
                    <div class="option-disagree"><input <% if (v==6) out.print("checked"); %> type="radio" required
                                                                                              value="6"
                                                                                              name="choice<% out.print(n);%>">
                    </div>
                    <div class="option-disagree"><input <% if (v==7) out.print("checked"); %> type="radio" required
                                                                                              value="7"
                                                                                              name="choice<% out.print(n);%>">
                    </div>
                </div>
                <div class="disagree">Disagree</div>
            </div>
        </div>
        <div class="divider"></div>
        <%
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>

        <div class="age-region">
            <label class="age-region-text">Insert your Age</label>
            <%
                User candidate = UserDao.getUser(uid);
            %>
            <input type="number" name="age" value="<% out.print(candidate.getAge()); %>" required min="18" max="110">
            <label for="region" class="age-region-text">Choose a Region:</label>
            <select name="region" id="region" required>
                <%
                    try {
                        Region[] array = RegionDao.getRegions().toArray(new Region[0]);
                        for (Region region : array) {
                            String r = region.getRegion();


                %>
                <option <% if (Objects.equals(candidate.getRegion(), region.getRegion())) out.print("selected"); %>
                        value="<%out.print(r); %>"><%out.print(r); %></option>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                %>

            </select>
        </div>
        <input type="submit" value="Save" class="submit-button">
    </div>

</form>
</body>
</html>