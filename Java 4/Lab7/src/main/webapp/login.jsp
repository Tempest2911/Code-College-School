<!-- src/main/webapp/login.jsp -->
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Login</title></head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <label>Username: <input type="text" name="username" /></label><br/>
        <label>Password: <input type="password" name="password" /></label><br/>
        <button type="submit">Login</button>
        <div style="color:red">${error}</div>
    </form>
</body>
</html>