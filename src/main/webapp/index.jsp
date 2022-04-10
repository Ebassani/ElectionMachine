<%@ page import="com.github.ebassani.electionmachine.Util" %>
<%@ page import="java.sql.SQLException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8"/>
    <link rel="stylesheet" href="style/style.css">
    <title>Main Page</title>
</head>
<body>
<div class="main">
    <a href="<c:url value="/quiz.jsp"/>" class="link-start">Start Quiz</a>
    <div class="buttons">
        <%
            if (Util.isSomebodyloggedIn(request.getSession()) == null) {
        %>
        <div class="buttons__login"><a href="<c:url value="/login"/>">Login</a></div>
        <%
        } else {
            try {
                if (Util.isAdmin(Util.isSomebodyloggedIn(request.getSession()))) {
        %>
        <div class="buttons__logout"><a href="<c:url value="/logout"/>">Logout</a></div>
        <div class="buttons__users"><a href="<c:url value="/user-management"/>">Users</a></div>
        <div class="buttons__questions"><a href="<c:url value="/admQuestions.jsp"/>">Questions</a></div>
        <%
        } else {
        %>
        <div class="buttons__logout"><a href="<c:url value="/logout"/>">Logout</a></div>
        <%
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>
    </div>
</div>
</body>
</html>