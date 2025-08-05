<%--
  Created by IntelliJ IDEA.
  User: TaiGo
  Date: 8/5/2025
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h2>Login</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    <label for="tenTaiKhoan">Username:</label>
    <input type="text" id="tenTaiKhoan" name="tenTaiKhoan" required /><br/><br/>

    <label for="matKhau">Password:</label>
    <input type="password" id="matKhau" name="matKhau" required /><br/><br/>

    <button type="submit">Login</button>
</form>
</body>
</html>