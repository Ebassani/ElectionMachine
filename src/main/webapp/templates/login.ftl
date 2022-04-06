<#-- @ftlvariable name="error" type="java.lang.String" -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link rel="stylesheet" href="../style/login.css">
</head>
<body>

<div class="wrapper">
    <form action="/login" method="post">
        <label for="email">Email</label><input type="email" name="email" id="email">
        <label for="password">Password</label><input type="password" name="password" id="password">
        <input class="button" type="submit" value="Login"/>
        <span>${error}</span>
    </form>
</div>

</body>
</html>