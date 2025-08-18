<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'hien-thi'}">
        <h2>Add</h2>
        <form action="/phongban/add" method="post" onsubmit="return validateForm()">
            ID: <input type="text" name="id" id="id"><br>
            Ma: <input type="text" name="ma" id="ma"><br>
            Ten: <input type="text" id="ten" name="ten"><br>
            Chuc Vu:
            <select name="idPb">
                <c:forEach var="cv" items="${listSP}">
                    <option value="${cv.id}">${cv.ten}</option>
                </c:forEach>
            </select><br>

            <button type="submit">Add</button>
        </form>

        <br>
        <h2>Table</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Ma</th>
                <th>Ten</th>
                <th>Loai Phong Ban</th>
                <th>Hanh Dong</th>
            </tr>
            <c:forEach items="${danhSach}" var="sp">
                <tr>
                    <td>${sp.id}</td>
                    <td>${sp.ma}</td>
                    <td>${sp.ten}</td>
                    <td>${sp.loaiPhongBan.id}</td>
                    <td>
                        <a href="/phongban/viewUpdate?id=${sp.id}">Detail</a>
                        <a href="/phongban/delete?id=${sp.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/phongban/hien-thi?page=${page-1}" ${page <= 1 ? 'style="pointer-events:none;opacity:0.5;"' : ''}>Prev</a>
        Page ${page} of ${totalPages}
        <a href="/phongban/hien-thi?page=${page+1}" ${page >= totalPages ? 'style="pointer-events:none;opacity:0.5;"' : ''}>Next</a>
    </c:when>
</c:choose>
</body>
</html>
