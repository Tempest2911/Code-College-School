<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BAKA</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'hien-thi'}">
        <h2>Add new</h2>
        <form action="/sanpham/add" method="post" onsubmit="return validateForm()">
            Mã: <input type="text" name="ma" id="ma"><br>
            Loại sản phẩm:
            <select name="idLoaiSp">
                <c:forEach var="loai" items="${listSP}">
                    <option value="${loai.id}">${loai.ten}</option>
                </c:forEach>
            </select><br>
            Tên: <input type="text" id="ten" name="ten"><br>
            Mô tả: <input type="text" id="mota" name="mota"><br>

            <button type="submit">Add</button>
        </form>

        <form action="/sanpham/search" method="get" id="searchForm">
            <h2>Search</h2> <br>
            <input type="text" name="keyword" placeholder="Enter search term">
            <button type="submit">Search</button>
        </form>

        <form action="/sanpham/soft" method="get">
            <button type="submit">Soft</button>
        </form>

        <h2>Table</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Ma SP</th>
                <th>Ma Loai SP</th>
                <th>Ten Loai SP</th>
                <th>Ten</th>
                <th>MoTa</th>
            </tr>
            <c:forEach items="${danhSach}" var="sp">
                <tr>
                    <td>${sp.id}</td>
                    <td>${sp.ma}</td>
                    <td>${sp.idLoaiSp.ma}</td>
                    <td>${sp.idLoaiSp.ten}</td>
                    <td>${sp.ten}</td>
                    <td>${sp.mota}</td>
                    <td>
                        <a href="/sanpham/viewUpdate?id=${sp.id}">Detail</a>
                        <a href="/sanpham/delete?id=${sp.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/sanpham/hien-thi?page=${page-1}" ${page <= 1 ? 'style="pointer-events:none;opacity:0.5;"' : ''}>Prev</a>
        Page ${page} of ${totalPages}
        <a href="/sanpham/hien-thi?page=${page+1}" ${page >= totalPages ? 'style="pointer-events:none;opacity:0.5;"' : ''}>Next</a>
    </c:when>

    <c:when test="${action == 'hien-thi-update'}">
        <form action="/sanpham/update" method="post" onsubmit="return validateForm()">
            ID:<input type="text" name="id" value="${sp.id}" readonly> <br>
            Mã: <input type="text" name="ma" id="ma" value="${sp.ma}"><br>
            Loại sản phẩm:
            <select name="idLoaiSp">
                <c:forEach var="loai" items="${listSP}">
                    <option value="${loai.id}" ${loai.id == sp.idLoaiSp.id ? 'selected': ''}>${loai.ten}</option>
                </c:forEach>
            </select><br>
            Tên: <input type="text" id="ten" name="ten" value="${sp.ten}"><br>
            Mô tả: <input type="text" id="mota" name="mota" value="${sp.mota}"><br>

            <button type="submit">Update</button>
        </form>
    </c:when>

    <c:when test="${action == 'login'}">
        <h2>Login</h2>
        <form action="/login" method="post">
            <i>${error}</i> <br>
            Username: <input type="text" name="username"> <br>
            Password: <input type="password" name="password"> <br>
            <button type="submit">Login</button>
        </form>
    </c:when>
</c:choose>
</body>

<script>
    function validateForm() {
        let ten = document.getElementById("ten").value.trim();
        let mota = document.getElementById("mota").value.trim();

        if (ten === "" || ten === null || mota === "" || mota === null) {
            alert("Please fill in all fields.");
            return false;
        }
        return true;
    }
</script>
</html>
