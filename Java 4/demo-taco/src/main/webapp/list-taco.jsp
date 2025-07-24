<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 7/24/2025
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="box">
        <h1>List of TACO</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Email</th>
            </tr>
            <c:forEach items="${dsSimpleUser}" var="simpleUser">
                <tr>
                    <td>${simpleUser.id}</td>
                    <td>${simpleUser.username}</td>
                    <td>${simpleUser.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
