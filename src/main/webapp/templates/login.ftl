<#-- @ftlvariable name="error" type="java.lang.String" -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<form action="/login" method="post">
    <label for="email">Email</label><input type="email" name="email" id="email">
    <label for="password">Password</label><input type="password" name="password" id="password">
    <input type="submit" value="Login"/>
</form>
<span>${error}</span>

</body>
</html>