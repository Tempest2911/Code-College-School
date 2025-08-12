<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Quản lý yêu cầu mượn sách</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: #f4f6f8;
            padding: 20px;
        }
        h2 {
            color: #333;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        h2 a {
            font-size: 14px;
            color: #007bff;
            text-decoration: none;
        }
        h2 a:hover {
            text-decoration: underline;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        thead {
            background-color: #007bff;
            color: white;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
        }
        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tbody tr:hover {
            background-color: #eef3ff;
        }
        .status-pending {
            color: #ff9800;
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
        button {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            color: white;
            cursor: pointer;
            font-size: 14px;
        }
        .btn-approve {
            background-color: #28a745;
        }
        .btn-approve:hover {
            background-color: #218838;
        }
        .btn-reject {
            background-color: #dc3545;
        }
        .btn-reject:hover {
            background-color: #c82333;
        }
        form {
            display: inline-block;
        }
    </style>
</head>
<body>

<h2>
    Danh sách yêu cầu mượn sách
    <a href="${pageContext.request.contextPath}/admin/homeadmin.jsp">← Quay lại trang chủ</a>
</h2>

<table>
    <thead>
    <tr>
        <th>Tên sinh viên</th>
        <th>Tên sách</th>
        <th>Ngày gửi yêu cầu</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="req" items="${requests}">
        <tr>
            <td>${req.user.name}</td>
            <td>${req.book.title}</td>
            <td>
                <fmt:formatDate value="${req.requestDateAsDate}" pattern="HH:mm dd/MM/yyyy" />
            </td>
            <td>
                <c:choose>
                    <c:when test="${req.status == 'PENDING'}"><span class="status-pending">⏳ PENDING</span></c:when>
                    <c:when test="${req.status == 'APPROVED'}"><span class="status-approved">✔️ APPROVED</span></c:when>
                    <c:when test="${req.status == 'REJECTED'}"><span class="status-rejected">❌ REJECTED</span></c:when>
                    <c:otherwise>${req.status}</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:if test="${req.status == 'PENDING'}">
                    <form method="post" action="${pageContext.request.contextPath}/admin/borrow_requests">
                        <input type="hidden" name="requestId" value="${req.id}" />
                        <input type="hidden" name="action" value="approve" />
                        <button type="submit" class="btn-approve">Phê duyệt</button>
                    </form>
                    <form method="post" action="${pageContext.request.contextPath}/admin/borrow_requests">
                        <input type="hidden" name="requestId" value="${req.id}" />
                        <input type="hidden" name="action" value="reject" />
                        <button type="submit" class="btn-reject">Từ chối</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
