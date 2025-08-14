<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Danh sách yêu cầu mượn sách</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        .message {
            text-align: center;
            font-weight: bold;
            margin-bottom: 15px;
        }
        .message.success {
            color: #28a745;
        }
        .message.error {
            color: #dc3545;
        }
        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
            background: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        th {
            background-color: #007bff;
            color: white;
            padding: 12px;
            text-align: left;
            font-size: 16px;
        }
        td {
            padding: 10px 12px;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f1f7ff;
        }
        .status-pending {
            color: #fd7e14;
            font-weight: bold;
        }
        .status-approved {
            color: #28a745;
            font-weight: bold;
        }
        .status-rejected {
            color: #dc3545;
            font-weight: bold;
        }
        .back-btn {
            display: block;
            width: 200px;
            margin: 25px auto;
            text-align: center;
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            text-decoration: none;
            transition: background 0.3s;
        }
        .back-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h2>📚 Danh sách yêu cầu mượn sách của bạn</h2>

<c:if test="${not empty message}">
    <div class="message success">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="message error">${error}</div>
</c:if>

<table>
    <thead>
    <tr>
        <th>Tiêu đề sách</th>
        <th>Tác giả</th>
        <th>Ngày mượn</th>
        <th>Trạng thái</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty requests}">
            <c:forEach var="req" items="${requests}">
                <tr>
                    <td>${req.book.title}</td>
                    <td>${req.book.author}</td>
                    <td>
                        <fmt:formatDate value="${req.requestDateAsDate}" pattern="HH:mm dd/MM/yyyy" />
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${req.status == 'PENDING'}">
                                <span class="status-pending">⏳ PENDING</span>
                            </c:when>
                            <c:when test="${req.status == 'APPROVED'}">
                                <span class="status-approved">✔ APPROVED</span>
                            </c:when>
                            <c:when test="${req.status == 'REJECTED'}">
                                <span class="status-rejected">❌ REJECTED</span>
                            </c:when>
                            <c:otherwise>
                                <span>${req.status}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="3" style="text-align:center; color:#666;">📭 Bạn chưa gửi yêu cầu mượn sách nào.</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<a class="back-btn" href="${pageContext.request.contextPath}/user/books">⬅ Quay lại trang chủ</a>
</body>
</html>
