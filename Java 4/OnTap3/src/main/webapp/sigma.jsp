<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 8/15/2025
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'hien-thi'}">
        <h2>Add</h2>
        <form action="/nhanvien/add" method="post" onsubmit="return validateForm()">
            Ma: <input type="text" name="ma" id="ma"><br>
            Ten: <input type="text" id="ten" name="ten"><br>
            Ten Dem: <input type="text" id="tenDem" name="tenDem"><br>
            Ho: <input type="text" id="ho" name="ho"><br>
            Gioi Tinh:
            <select name="gioiTinh">
                <option value="true">Nam</option>
                <option value="false">Nu</option>
            </select><br>
            Dia Chi: <input type="text" id="diaChi" name="diaChi"><br>
            SDT: <input type="text" id="sdt" name="sdt"><br>
            Mat Khau: <input type="password" id="matKhau" name="matKhau"><br>
            Chuc Vu:
            <select name="idCv">
                <c:forEach var="cv" items="${listSP}">
                    <option value="${cv.id}">${cv.ten}</option>
                </c:forEach>
            </select><br>
            Trang Thai: <input type="text" id="trangThai" name="trangThai"><br><br>

            <button type="submit">Add</button>
        </form>

        <form action="/nhanvien/search" method="get" id="searchForm">
            <h2>Search</h2> <br>
            <input type="text" name="keyword" placeholder="Enter search term">
            <button type="submit">Search</button>
        </form>

        <form action="/nhanvien/soft" method="get">
            <button type="submit">Soft</button>
        </form>

        <br>
        <h2>Table</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Ma</th>
                <th>Ten</th>
                <th>Ten Dem</th>
                <th>Ho</th>
                <th>Gioi Tinh</th>
                <th>Dia Chi</th>
                <th>SDT</th>
                <th>Mat Khau</th>
                <th>id ChucVu</th>
                <th>Trang Thai</th>
                <th>Hanh Dong</th>
            </tr>
            <c:forEach items="${danhSach}" var="sp">
                <tr>
                    <td>${sp.id}</td>
                    <td>${sp.ma}</td>
                    <td>${sp.ten}</td>
                    <td>${sp.tenDem}</td>
                    <td>${sp.ho}</td>
                    <td>${sp.gioiTinh}</td>
                    <td>${sp.diaChi}</td>
                    <td>${sp.sdt}</td>
                    <td>${sp.matKhau}</td>
                    <td>${sp.idCv.id}</td>
                    <td>${sp.trangThai}</td>
                    <td>
                        <a href="/nhanvien/viewUpdate?id=${sp.id}">Detail</a>
                        <a href="/nhanvien/delete?id=${sp.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/nhanvien/hien-thi?page=${page-1}" ${page <= 1 ? 'style="pointer-events:none;opacity:0.5;"' : ''}>Prev</a>
        Page ${page} of ${totalPages}
        <a href="/nhanvien/hien-thi?page=${page+1}" ${page >= totalPages ? 'style="pointer-events:none;opacity:0.5;"' : ''}>Next</a>
    </c:when>

    <c:when test="${action == 'update'}">
        <h2>Add</h2>
        <form action="/nhanvien/update" method="post" onsubmit="return validateForm()">
            ID: <input type="text" name="id" value="${sp.id}" readonly> <br>
            Ma: <input type="text" name="ma" id="ma" value="${sp.ma}"><br>
            Ten: <input type="text" id="ten" name="ten" value="${sp.ten}"><br>
            Ten Dem: <input type="text" id="tenDem" name="tenDem" value="${sp.tenDem}"><br>
            Ho: <input type="text" id="ho" name="ho" value="${sp.ho}"><br>
            Gioi Tinh:
            <select name="gioiTinh">
                if (sp.gioiTinh) {
                <option value="true" selected>Nam</option>
                <option value="false">Nu</option>
                } else {
                <option value="true">Nam</option>
                <option value="false" selected>Nu</option>
                }
            </select><br>
            Dia Chi: <input type="text" id="diaChi" name="diaChi" value="${sp.diaChi}"><br>
            SDT: <input type="text" id="sdt" name="sdt" value="${sp.sdt}"><br>
            Mat Khau: <input type="password" id="matKhau" name="matKhau" value="${sp.matKhau}"><br>
            Chuc Vu:
            <select name="idCv">
                <c:forEach var="cv" items="${listSP}">
                    <option value="${cv.id}" ${cv.id == sp.idCv.id ? 'selected' : ''}>${cv.ten}</option>
                </c:forEach>
            </select><br>
            Trang Thai: <input type="text" id="trangThai" name="trangThai" value="${sp.trangThai}"><br><br>

            <button type="submit">Update</button>
        </form>
    </c:when>

    <c:when test="${action == 'login'}">
        <h2>Login</h2>
            <form action="/login" method="post">
                <i>${error}</i>
                Username: <input type="text" name="username"><br>
                Password: <input type="password" name="password"><br>
                <button type="submit">Login</button>
            </form>
    </c:when>
</c:choose>
</body>

<script>
    function validateForm() {
        const ma = document.getElementById("ma").value;
        const ten = document.getElementById("ten").value;
        const tenDem = document.getElementById("tenDem").value;
        const ho = document.getElementById("ho").value;
        const diaChi = document.getElementById("diaChi").value;
        const sdt = document.getElementById("sdt").value;
        const matKhau = document.getElementById("matKhau").value;

        if (!ma || !ten || !tenDem || !ho || !diaChi || !sdt || !matKhau) {
            alert("Vui lòng điền đầy đủ thông tin.");
            return false;
        }
        return true;
    }
</script>
</html>
