<%@ page import="com.github.ebassani.electionmachine.data.model.User" %>
<%@ page import="com.github.ebassani.electionmachine.data.model.Answer" %>
<%@ page import="com.github.ebassani.electionmachine.data.AnswerDao" %>
<%@ page import="com.github.ebassani.electionmachine.data.UserDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.*" %>
<%@ page import="com.github.ebassani.electionmachine.Util" %>
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

    Integer id= 0;

    try {
        id = (Integer) request.getAttribute("id");
    } catch (Exception e) {
        response.sendRedirect("/");
    }

    out.println(id);


    List<Answer> answers = null;
    try {
        answers = AnswerDao.getUserAnswers(id);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    List<User> users = null;

    try {
        users = UserDao.getCandidates();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    assert users != null;
    for (User user : users) {
        try {
            assert answers != null;
            int diff= AnswerDao.compareAnswers(answers,AnswerDao.getUserAnswers(user.getId()));
            user.setDiffSum(diff);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    users.sort(new Comparator<User>() {
        @Override
        public int compare(User u1, User u2) {
            return u1.getDiffSum() - (u2.getDiffSum());
        }
    });

    for (int i=0;i<5 && i<users.size();i++){
        out.println(users.get(i).getNames()+" "+users.get(i).getId()+" " + users.get(i).getDiffSum());
    }
%>

</body>
</html>
