<%@
        page import="com.github.ebassani.electionmachine.data.QuestionDao,
                     com.github.ebassani.electionmachine.data.model.Question,
                     java.sql.SQLException,
                     com.github.ebassani.electionmachine.Util"
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Questions</title>
    <link rel="stylesheet" href="/style/question-management.css">
    <script src="/js/question-management.js"></script>
</head>
<body>

<div class="header">
    <div class="left">
        <a href=""><h1 style="color: snow">Questions</h1></a>
    </div>
    <div class="right">
        <a class="nav-element" href="/user-management">Users</a>
        <a class="nav-element" href="">Log Out</a>
        <button class="button" onclick="toVisible('create')">Add question</button>
    </div>
</div>

<%
    QuestionDao qm = null;
    try {
        qm = new QuestionDao();
    } catch (Exception e) {

    }

    if (request.getSession().getAttribute("user_id") != null) {
        int uId = (int) request.getSession().getAttribute("user_id");
        boolean isAdmin;
        try {
            isAdmin = Util.isAdmin(uId);
            if (!isAdmin) {
                response.sendRedirect("/login");
            }
        } catch (SQLException e) {
        }
    } else {
        response.sendRedirect("/login");
    }

    Question[] questions = null;
    try {
        questions = qm.getQuestions();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

<div>
    <%
        for (int i = 0; i < questions.length; i++) {
    %>
    <div class="questions">
        <p class="text"><%out.print(questions[i].getQuestion());%></p>
        <div>
            <button class="button" onclick="getQuestion('<%out.print(questions[i].getId());%>','<%out.print(questions[i].getQuestion());%>')">
                Edit
            </button>
            <button class="button" onclick="delQuestion('<%out.print(questions[i].getId());%>')">Delete</button>
        </div>
    </div>
    <% } %>
</div>

<div id="edit" class="dialog popup hidden">
    <h3>Edit question</h3>
    <form method='post' action='/questionHandler'>
        <input type="hidden" id='q_id' name='id' value=''>
        <input type="text" class="border" id='question' name='question' placeholder='Your question here' value=''>
        <div>
            <input class="button" type="submit">
            <button class="button" onclick="toHidden('edit')" type='button'>Cancel</button>
        </div>
    </form>
</div>

<div id="delete" class="dialog popup hidden">
    <h3 style="margin-bottom: 1em">Are you sure you want to delete this question?</h3>
    <form method='post' action='/questionHandler'>
        <input type="hidden" id='id' name='id' value=''>
        <input class="button" type="submit" value='YES'>
        <button class="button" onclick="toHidden('delete')" type='button'>NO</button>
    </form>
</div>

<div id="create" class="dialog popup hidden">
    <h3>Write the new question here:</h3>
    <form method='post' action='/questionHandler'>
        <input class="border" required type="text" name='question'>
        <div>
            <input class="button" type="submit">
            <button class="button" onclick="toHidden('create')" type='button'>Cancel</button>
        </div>
    </form>
</div>

<div onclick="toHidden('create');toHidden('delete');toHidden('edit')" id="overlay" class="overlay hidden"></div>

</body>
</html>