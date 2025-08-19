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
        <form action="/phieugiamgia/add" method="post" onsubmit="return validateForm()">
            ID: <input type="text" name="id" id="id"><br>
            Ma: <input type="text" name="ma" id="ma"><br>
            Loai Phieu Giam Gia:
            <select name="loaiPhieu">
                <c:forEach var="cv" items="${listSP}">
                    <option value="${cv.id}">${cv.ten}</option>
                </c:forEach>
            </select><br>
            Ten: <input type="text" name="ten" id="ten"><br>
            So Luong: <input type="number" name="soLuong" id="soLuong"><br>
                <%--            Giới Tính:--%>
                <%--            <input type="radio" name="gioiTinh" value="Nam" checked> Nam--%>
                <%--            <input type="radio" name="gioiTinh" value="Nữ"> Nữ--%>
                <%--            <br>--%>
            <button type="submit">Add</button>
        </form>

        <form action="/phieugiamgia/search" method="get" id="searchForm">
            <h2>Search</h2> <br>
            <input type="text" name="keyword" placeholder="Enter search term">
            <button type="submit">Search</button>
        </form>

        <form action="/phieugiamgia/soft" method="get">
            <button type="submit">Soft</button>
        </form>

        <h2>Table</h2>
        <table border="1">
            <tr>
                <th>#</th>
                <th>Ma Phieu</th>
                <th>Ma Loai Phieu</th>
                <th>Ten Phieu</th>
                <th>Ten Loai Phieu</th>
                <th>So Luong</th>
                <th>Hanh Dong</th>
            </tr>

            <c:forEach items="${danhSach}" var="sp">
                <tr>
                    <td>${sp.id}</td>
                    <td>${sp.ma}</td>
                    <td>${sp.loaiPhieu.ma}</td>
                    <td>${sp.ten}</td>
                    <td>${sp.loaiPhieu.ten}</td>
                    <td>${sp.soLuong}</td>
                    <td>
                        <a href="/phieugiamgia/viewUpdate?id=${sp.id}">View Update</a>
                        <a href="/phieugiamgia/delete?id=${sp.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/phieugiamgia/hien-thi?page=${page-1}" ${page <= 1 ? 'style="pointer-events:none;opacity:0.5;"' : ''}>Prev</a>
        Page ${page} of ${totalPages}
        <a href="/phieugiamgia/hien-thi?page=${page+1}" ${page >= totalPages ? 'style="pointer-events:none;opacity:0.5;"' : ''}>Next</a>
    </c:when>

    <c:when test="${action == 'update'}">
        <h2>Update</h2>
        <form action="/phieugiamgia/update" method="post" onsubmit="return validateForm()">
            ID: <input type="text" name="id" id="id" value="${sp.id}" readonly><br>
            Ma: <input type="text" name="ma" id="ma" value="${sp.ma}"><br>
            Loai Phieu Giam Gia:
            <select name="loaiPhieu">
                <c:forEach var="cv" items="${listSP}">
                    <option value="${cv.id}" ${cv.id == sp.loaiPhieu.id ? 'selected' : ''}>${cv.ten}</option>
                </c:forEach>
            </select><br>
            Ten: <input type="text" name="ten" id="ten" value="${sp.ten}"><br>
            So Luong: <input type="number" name="soLuong" id="soLuong" value="${sp.soLuong}"><br>
                <%--            Giới Tính:--%>
                <%--            <input type="radio" name="gioiTinh" value="Nam" ${sp.gioiTinh eq 'Nam' ? 'checked' : ''}> Nam--%>
                <%--            <input type="radio" name="gioiTinh" value="Nữ" ${sp.gioiTinh eq 'Nữ' ? 'checked' : ''}> Nữ--%>
                <%--            <br>--%>

            <button type="submit">Update</button>

        </form>
    </c:when>
</c:choose>
</body>

<script>
    function validateForm() {
        const id = document.getElementById("id").value;
        const ma = document.getElementById("ma").value;
        const ten = document.getElementById("ten").value;
        const soLuong = document.getElementById("soLuong").value;

        if (!ma || !ten || !soLuong) {
            alert("Vui lòng điền đầy đủ thông tin.");
            return false;
        }

        if (isNaN(soLuong) || soLuong <= 0) {
            alert("Số lượng phải là một số dương.");
            return false;
        }
        if (id && isNaN(id)) {
            alert("ID phải là một số.");
            return false;
        }

        //tên ko được chứa ký tự đặc biệt, số
        const regex = /^[a-zA-Z\s]+$/;
        if (!regex.test(ten)) {
            alert("Tên không được chứa ký tự đặc biệt hoặc số.");
            return false;
        }

        return true;
    }
</script>
<script>
    function validateForm() {
        const id = document.getElementById("id").value;
        const ma = document.getElementById("ma").value;
        const ten = document.getElementById("ten").value;
        const soLuong = document.getElementById("soLuong").value;

        if (!ma || !ten || !soLuong) {
            alert("Vui lòng điền đầy đủ thông tin.");
            return false;
        }

        if (isNaN(soLuong) || soLuong <= 0) {
            alert("Số lượng phải là một số dương.");
            return false;
        }
        if (id && isNaN(id)) {
            alert("ID phải là một số.");
            return false;
        }

        //tên ko được chứa ký tự đặc biệt, số
        const regex = /^[a-zA-Z\s]+$/;
        if (!regex.test(ten)) {
            alert("Tên không được chứa ký tự đặc biệt hoặc số.");
            return false;
        }

        return true;
    }
</script>


</html>
