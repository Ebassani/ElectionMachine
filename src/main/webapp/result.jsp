<%@ page import="com.github.ebassani.electionmachine.data.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.github.ebassani.electionmachine.data.model.Answer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.github.ebassani.electionmachine.data.AnswerDao" %>
<%@ page import="com.github.ebassani.electionmachine.data.UserDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Comparator" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Result</title>
</head>
<body>
<%
    List<Answer> answers = AnswerDao.getUserAnswers((int) request.getSession().getAttribute("user_id"));

    List<User> users = null;

    try {
        users = UserDao.getCandidates();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    for (User user : users) {
        try {
            int diff= AnswerDao.compareAnswers(answers,AnswerDao.getUserAnswers(user.getId()));
            user.setDiffSum(diff);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    users.sort(Comparator.comparingInt(User::getDiffSum));

    for (int i=0;i<5 && i<users.size();i++){
        out.println(users.get(i));
    }
%>

</body>
</html>
