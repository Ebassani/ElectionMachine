<#-- @ftlvariable name="user" type="com.github.ebassani.electionmachine.data.model.User" -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<form action="/login" method="post">
    <label for="username">Username</label><input type="text" name="username" id="username">
    <label for="password">Password</label><input type="password" name="password" id="password">
    <input type="submit" value="Login"/>${user.names}
</form>

</body>
</html>