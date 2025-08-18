<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 8/16/2025
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Login</h2>
<form action="/login" method="post">
    <i>${error}</i> <br>
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <button type="submit">Login</button>
</form>
</body>
</html>
