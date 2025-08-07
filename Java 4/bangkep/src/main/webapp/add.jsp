<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>No more pls</title>
</head>
<body>
<h3>Add Student</h3>
<form action="/add" method="post">
    <table>
        <tr>
            <td><label>ID</label></td>
            <td><input type="text" name="id"></td>
        </tr>
        <tr>
            <td><label>Ho ten</label></td>
            <td><input type="text" name="hoten"></td>
        </tr>
        <tr>
            <td><label>Chuc vu</label></td>
            <td><input type="text" name="chucvu"></td>
        </tr>
        <tr>
            <td><label>Luong</label></td>
            <td><input type="number" name="luong"></td>
        </tr>
        <tr>
            <td><label>Trang thai</label></td>
            <td>
                <input type="radio" name="dangLamViec" value="true" checked> Dang lam viec
                <input type="radio" name="dangLamViec" value="false"> Ngung lam viec
            </td>
        </tr>
        <tr>
            <td><label>Phong ban</label></td>
            <td>
                <select name="phongBanId">
                    <c:forEach items="${listPhongBan}" var="pb">
                        <option value="${pb.id}" label="${pb.ten}"></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form>

</body>
</html>