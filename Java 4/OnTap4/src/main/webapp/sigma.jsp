<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 8/16/2025
  Time: 10:21 AM
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
                <form action="/sanpham/add" method="post" onsubmit="return validateForm()">
                    Ma: <input type="text" name="ma" id="ma"><br>
                    Loai SP:
                    <select name="idSp">
                        <c:forEach var="cv" items="${listSP}">
                            <option value="${cv.id}">${cv.ten}</option>
                        </c:forEach>
                    </select><br>
                    Ten: <input type="text" name="ten" id="ten"><br>
                    Mo ta: <input type="text" name="mota" id="mota"><br>

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

                <br>
                <h2>Table</h2>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Ma</th>
                        <th>Ten</th>
                        <th>Ma loai SP</th>
                        <th>Ten loai SP</th>
                        <th>Mo ta</th>
                        <th>Hanh Dong</th>
                    </tr>
                    <c:forEach items="${danhSach}" var="sp">
                        <tr>
                            <td>${sp.id}</td>
                            <td>${sp.ma}</td>
                            <td>${sp.ten}</td>
                            <td>${sp.idLoaiSp.ma}</td>
                            <td>${sp.idLoaiSp.ten}</td>
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

     <c:when test="${action == 'update'}">
             <h2>Add</h2>
             <form action="/sanpham/update" method="post" onsubmit="return validateForm()">
                 ID: <input type="text" name="id" id="id" value="${sp.id}" readonly> <br>
                 Ma: <input type="text" name="ma" id="ma" value="${sp.ma}"><br>
                 Loai SP:
                 <select name="idSp">
                     <c:forEach var="cv" items="${listSP}">
                         <option value="${cv.id}" ${sv.id == sp.idLoaiSp.ma ? 'selected' : ''}>${cv.ten}</option>
                     </c:forEach>
                 </select><br>
                 Ten: <input type="text" id="ten" name="ten" value="${sp.ten}"><br>

                 Mo ta: <input type="text" id="mota" name="mota" value="${sp.mota}"><br><br>

                 <button type="submit">Update</button>
             </form>
         </c:when>


</c:choose>
</body>
<script>
    function validateForm() {
        const ma = document.getElementById("ma").value;
        const ten = document.getElementById("ten").value;
        const mota = document.getElementById("mota").value;

        if (!ma || !ten || !mota) {
            alert("Vui lòng điền đầy đủ thông tin.");
            return false;
        }
        return true;
    }
</script>
</html>
