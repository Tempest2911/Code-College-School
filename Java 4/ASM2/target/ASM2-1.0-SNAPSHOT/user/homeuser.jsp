<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trang người dùng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f6f8;
            color: #333;
        }

        h2 {
            color: #2c3e50;
        }

        a {
            color: #3498db;
            text-decoration: none;
            margin-right: 15px;
        }

        a:hover {
            text-decoration: underline;
        }

        hr {
            margin: 20px 0;
        }

        form {
            margin-bottom: 15px;
        }

        input[type="text"] {
            padding: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 250px;
        }

        button {
            padding: 6px 12px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #2980b9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .empty-message {
            text-align: center;
            color: #888;
            font-style: italic;
        }
    </style>
</head>
<body>
<h2>Chào mừng bạn: ${currentUser.name}</h2>

<p>Đây là trang dành cho sinh viên (user).</p>

<a href="${pageContext.request.contextPath}/user/books">Xem danh sách sách</a>
<a href="${pageContext.request.contextPath}/user/borrow_requests">Yêu cầu mượn của tôi</a>
<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>

<hr>

<h3>Danh sách sách</h3>

<form method="get" action="${pageContext.request.contextPath}/user/books">
    <input type="text" name="keyword" placeholder="Tìm kiếm theo tiêu đề hoặc tác giả" value="${param.keyword}" />
    <button type="submit">Tìm kiếm</button>
</form>

<table>
    <thead>
    <tr>
        <th>Tiêu đề</th>
        <th>Tác giả</th>
        <th>Số lượng còn</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty books}">
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.quantity}</td>
                    <td>
                        <c:choose>
                            <c:when test="${book.quantity > 0}">
                                <form method="post"
                                      action="${pageContext.request.contextPath}/user/borrow_requests"
                                      style="margin:0;"
                                      onsubmit="this.querySelector('button').disabled = true;">
                                    <input type="hidden" name="bookId" value="${book.id}" />
                                    <button type="submit">Mượn</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                Hết sách
                            </c:otherwise>
                        </c:choose>
                    </td>

                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="4" class="empty-message">Không có sách nào.</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
<div style="margin-top: 15px; text-align: center;">
    <c:forEach var="i" begin="1" end="${totalPages}">
        <c:choose>
            <c:when test="${i == currentPage}">
                <span style="font-weight: bold; color: red;">${i}</span>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/user/books?page=${i}&keyword=${param.keyword}">${i}</a>
            </c:otherwise>
        </c:choose>
        &nbsp;
    </c:forEach>
</div>


</body>
</html>
