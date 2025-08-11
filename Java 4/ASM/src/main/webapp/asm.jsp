<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 8/11/2025
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BAKA</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'login'}">
        <h1>Login</h1>
        <form action="/login" method="post">
            Email: <input type="email" name="email" required><br>
            Password: <input type="password" name="password" required><br>
            <button type="submit">Login</button>
        </form>
    </c:when>

    <c:when test="${action == 'signup'}">
            <h1>Sign Up</h1>
        <form action="/signup" method="post">
                Name: <input type="text" name="name" required><br>
                Email: <input type="email" name="email" required><br>
                Password: <input type="password" name="password" required><br>
                <button type="submit">Sign Up</button>
        </form>
    </c:when>

    <c:when test="${action == 'home' || action == 'searchAuthor' || action == 'searchTitle'}">
       <h1>List of Book</h1>

        <table border="1">
            <c:if test="${not empty books}">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>quantity</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.quantity}</td>
                        <td>
                            <a href="/attendance/delete?id=${dsStudent.id}">Borrow</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>

        <form action="/home/searchAuthor" method="get" id="searchForm">
            <h2>Search Author</h2> <br>
            <input type="text" name="name" placeholder="Enter search term">
            <button type="submit">Search</button>

        </form>

        <form action="/home/searchTitle" method="get" id="searchForm">
            <h2>Search Title</h2> <br>
            <input type="text" name="name" placeholder="Enter search term">
            <button type="submit">Search</button>

        </form>
    </c:when>
</c:choose>

</body>
</html>
