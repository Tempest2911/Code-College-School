<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Quản lý sách</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        a {
            color: #2575fc;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        .back-link {
            display: inline-block;
            margin-bottom: 15px;
            color: #555;
            font-weight: 500;
        }
        .error {
            background: #ffe6e6;
            color: #d93025;
            padding: 10px;
            border-radius: 6px;
            margin-bottom: 15px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        th {
            background: #2575fc;
            color: white;
            padding: 12px;
            text-align: left;
        }
        td {
            padding: 10px;
            border-bottom: 1px solid #eee;
        }
        tr:hover {
            background: #f1f7ff;
        }
        .action-links a {
            margin-right: 10px;
            font-weight: 500;
        }
        .action-links a.edit {
            color: #007bff;
        }
        .action-links a.delete {
            color: #d93025;
        }
        h3 {
            margin-top: 30px;
            color: #333;
        }
        form {
            background: white;
            padding: 20px;
            border-radius: 8px;
            max-width: 450px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            margin-top: 10px;
        }
        label {
            font-weight: 500;
            margin-top: 10px;
            display: block;
        }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 6px;
            transition: border-color 0.3s;
        }
        input:focus {
            border-color: #2575fc;
            outline: none;
        }
        button {
            background: #2575fc;
            color: white;
            padding: 10px 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 500;
            margin-top: 15px;
        }
        button:hover {
            background: #1a5ed1;
        }
        .cancel-btn {
            margin-left: 10px;
            color: #555;
        }
    </style>
</head>
<body>

<h2>📚 Quản lý sách</h2>

<a class="back-link" href="${pageContext.request.contextPath}/admin/homeadmin.jsp">← Quay lại trang chủ Admin</a>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tiêu đề</th>
        <th>Tác giả</th>
        <th>Số lượng</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.quantity}</td>
            <td class="action-links">
                <a class="edit" href="${pageContext.request.contextPath}/admin/books?action=edit&id=${book.id}">✏ Sửa</a>
                <a class="delete" href="${pageContext.request.contextPath}/admin/books?action=delete&id=${book.id}"
                   onclick="return confirm('Bạn có chắc muốn xóa sách này?');">🗑 Xóa</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty books}">
        <tr>
            <td colspan="5" style="text-align:center; color:#888;">Chưa có sách nào.</td>
        </tr>
    </c:if>
    </tbody>
</table>

<h3>
    <c:choose>
        <c:when test="${not empty editBook}">✏ Chỉnh sửa sách</c:when>
        <c:otherwise>➕ Thêm sách mới</c:otherwise>
    </c:choose>
</h3>

<form method="post" action="${pageContext.request.contextPath}/admin/books">
    <input type="hidden" name="id" value="${editBook.id}"/>

    <label for="title">Tiêu đề:</label>
    <input type="text" id="title" name="title" value="${editBook.title}" required/>

    <label for="author">Tác giả:</label>
    <input type="text" id="author" name="author" value="${editBook.author}" required/>

    <label for="quantity">Số lượng:</label>
    <input type="number" id="quantity" name="quantity" min="0" value="${editBook.quantity}" required/>

    <button type="submit">
        <c:choose>
            <c:when test="${not empty editBook}">Cập nhật</c:when>
            <c:otherwise>Thêm mới</c:otherwise>
        </c:choose>
    </button>

    <c:if test="${not empty editBook}">
        <a class="cancel-btn" href="${pageContext.request.contextPath}/admin/books">Hủy</a>
    </c:if>
</form>

</body>
</html>
