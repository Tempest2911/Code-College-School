<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 8/18/2025
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:when test="${action == 'login'}">
    <h2>Login</h2>
    <form action="login" method="post">
        Username: <input type="text" name="username" /><br/>
        Password: <input type="password" name="password" /><br/>
        <button type="submit">Login</button>
        <p style="color:red">${error}</p>
    </form>
</c:when>
</body>
</html>
