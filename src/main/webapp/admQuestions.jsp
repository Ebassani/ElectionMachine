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
    <title>Title</title>
    <link rel="stylesheet" href="/style/question-management.css">
    <script src="/js/question-management.js"></script>
</head>
<body>
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
<h2>Questions</h2>
<button onclick="toVisible('create')">Add question</button>

<div>
    <%
        for (int i = 0; i < questions.length; i++) {
    %>
    <div class="questions">
        <%
            out.print(questions[i].getQuestion());
        %>
        <div>
            <button onclick="getQuestion('<%out.print(questions[i].getId());%>','<%out.print(questions[i].getQuestion());%>')">
                Edit
            </button>
            <button onclick="delQuestion('<%out.print(questions[i].getId());%>')">Delete</button>
        </div>
    </div>
    <% } %>
</div>

<div id="edit" class="popup hidden">
    <h3>Edit question</h3>
    <form method='post' action='/questionHandler'>
        <input type="hidden" id='q_id' name='id' value=''>
        <input type="text" class="border" id='question' name='question' placeholder='Your question here' value=''>
        <div>
            <input type="submit">
            <button onclick="toHidden('edit')" type='button'>Cancel</button>
        </div>
    </form>
</div>

<div id="delete" class="popup hidden">
    <h3>Are you sure you want to delete this question?</h3>
    <form method='post' action='/questionHandler'>
        <input type="hidden" id='id' name='id' value=''>
        <input type="submit" value='YES'>
        <button onclick="toHidden('delete')" type='button'>NO</button>
    </form>
</div>

<div id="create" class="popup hidden">
    <h3>Write the new question here:</h3>
    <form method='post' action='/questionHandler'>
        <input class="border" required type="text" name='question'>
        <div>
            <input type="submit">
            <button onclick="toHidden('create')" type='button'>Cancel</button>
        </div>
    </form>
</div>

<div id="overlay" class="page-overlay hidden"></div>

</body>
</html>