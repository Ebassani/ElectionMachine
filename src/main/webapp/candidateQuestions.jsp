<%@
        page import="com.github.ebassani.electionmachine.data.QuestionDao,
                     com.github.ebassani.electionmachine.data.model.Question,
                     java.sql.SQLException,
                     com.github.ebassani.electionmachine.Util"
%>
<%@ page import="java.util.Objects" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Questions</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/question-management.css">
    <script src="${pageContext.request.contextPath}/js/question-management.js"></script>
</head>
<body>

<div class="header">
    <div class="left">
        <a href=""><h1 style="color: snow">Questions</h1></a>
    </div>
    <div class="right">
        <a class="nav-element" href="${pageContext.request.contextPath}/logout">Log Out</a>
    </div>
</div>

<%

    if (request.getSession().getAttribute("user_id") != null) {
        int uId = (int) request.getSession().getAttribute("user_id");
        boolean isCandidate;
        try {
            isCandidate = Util.isCandidate(uId);
            if (!isCandidate) {
                response.sendRedirect("/index.jsp");
            }
        } catch (SQLException ignored) {}
    } else {
        response.sendRedirect("/login");
    }

    Question[] questions = null;
    try {
        questions = QuestionDao.getQuestions();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<%
    for (int i = 0; i < Objects.requireNonNull(questions).length; i++) {
%>
<div>

    <div class="questions">
        <p class="text"><%out.print(questions[i].getQuestion());%></p>
        <div>
            <button class="button"onclick="getQuestion('<%out.print(questions[i].getId());%>','<%out.print(questions[i].getQuestion());%>')">
                Edit
            </button>
    </div>
</div>
                <% } %>
<div id="edit" class="dialog popup hidden">
    <h3>Edit question</h3>
    <form method='post' action='${pageContext.request.contextPath}/questionHandler'>
        <input type="hidden" id='q_id' name='id' value=''>
        <input type="text" class="border" id='question' name='question' placeholder='Your question here' value=''>
        <div>
            <input class="button" type="submit"  value="Submit" >
            <button class="button" onclick="toHidden('edit')" type='button'>Cancel</button>
        </div>
    </form>
</div>



<div onclick="toHidden('edit')" id="overlay" class="overlay hidden"></div>

</body>
</html>