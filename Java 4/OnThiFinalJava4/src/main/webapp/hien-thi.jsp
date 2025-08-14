<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguyenvandan
  Date: 13/08/2025
  Time: 4:40 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sach san pham</title>
</head>
<body>
    <h1>Quan ly san pham</h1>
    <form action="/san-pham/add" method="post">
        <p>Ma san pham: <input type="text" name="ma" value="${sanPham.ma}"></p>
        <p>Ma loai san pham:
            <select name="idloaisp">
                <c:forEach items="${loaiSanPham}" var="lsp">
                    <option value="${lsp.id}" label="${lsp.ten}" ${lsp.id == sanPham.loaisp.id ? "selected" : ""}></option>
                </c:forEach>
            </select>
        </p>
        <p>Ten san pham: <input type="text" name="ten" value="${sanPham.ten}"></p>
        <p>Mo ta san pham: <input type="text" name="mota" value="${sanPham.mota}"></p>
        <button>Add</button>
    </form>

<table border="1">
    <thead>
    <tr>
        <th>Id San pham</th>
        <th>Ma san pham</th>
        <th>Ten san pham</th>
        <th>Mo ta</th>
        <th>Ma loai san pham</th>
        <th>Ten loai san pham</th>
        <th>Hanh dong</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="l">
        <tr>
            <td>${l[1].id}</td>
            <td>${l[1].ma}</td>
            <td>${l[1].ten}</td>
            <td>${l[1].mota}</td>
            <td>${l[0].ma}</td>
            <td>${l[0].ten}</td>
            <td>
                <a href="/san-pham/detail?id=${l[1].id}">Detail</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <a href="/san-pham/hien-thi?page=${page-1}">Prew</a>
    <a href="/san-pham/hien-thi?page=${page+1}">Next</a>
</body>
</html>
