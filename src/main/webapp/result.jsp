<%@ page import="com.github.ebassani.electionmachine.data.model.User" %>
<%@ page import="java.util.List" %>
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
    List<User> candidates = (List<User>) request.getSession().getAttribute("array");
    for (int i = 0;i<candidates.size() && i<5;i++){
        out.println(candidates.get(i).getNames());
    }
%>

</body>
</html>
