<%--
  Created by IntelliJ IDEA.
  User: nguyenvandan
  Date: 13/08/2025
  Time: 9:56 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/login" method="post">
    <i>${eroi}</i>
    <p>Username: <input type="text" name="username"></p>
    <p>Password: <input type="text" name="password"></p>
    <button>Login</button>
</form>
</body>
</html>
