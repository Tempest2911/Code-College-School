<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:choose>
        <c:when test="${action == 'hien-thi'}">
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
        </c:when>


    </c:choose>
</body>
</html>
