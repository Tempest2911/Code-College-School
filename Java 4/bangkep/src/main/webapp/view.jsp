<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/18/2025
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
  Remember using value for update
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <form action="/search">
        Ten nhan vien: <input type="text" name="ten">
        <button>Search</button>
    </form>
</div>
<a href="/addView">Add</a>
<table>
    <tr>
        <th>ID</th>
        <th>Ho ten</th>
        <th>Chuc vu</th>
        <th>Luong</th>
        <th>Trang thai</th>
        <th>Ten phong ban</th>
    </tr>
    <c:forEach var="var" items="${nv}" varStatus="i">
        <tr>
            <td>${var.id}</td>
            <td>${var.hoten}</td>
            <td>${var.chucvu}</td>
            <td>${var.luong}</td>
            <td>${var.dangLamViec}</td>
            <td>${var.phongBan.ten}</td>
            <td>
                <a href="/editView?id=${var.id}">Edit</a>
                <a href="/delete?id=${var.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
