<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 7/27/2025
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Members</title>
</head>
<body>
<c:choose>
<c:when test="${action == 'listMembers' || action == 'active' || action =='detail'}">
    <h1>List of Members</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Member Code</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Date Of Birth</th>
            <th>Join Date</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${dsMembers}" var="member">
            <tr>
                <td>${member.id}</td>
                <td>${member.memberCode}</td>
                <td>${member.fullName}</td>
                <td>${member.email}</td>
                <td>${member.phone}</td>
                <td>${member.dob}</td>
                <td>${member.joinDate}</td>
                <td>${member.status}</td>
                    <%--        <td>--%>
                    <%--            <a href="members/detail?id=${member.id}">Detail</a> |--%>
                    <%--            <a href="members/update?id=${member.id}">Edit</a> |--%>
                    <%--            <a href="members/delete?id=${member.id}" onclick="return confirm('Delete?')">Delete</a>--%>
                    <%--        </td>--%>
            </tr>
        </c:forEach>
    </table>
</c:when>

<c:when test="${action== 'listClasses' || action == 'category' || action == 'available'}">
    <h1>List of Classes</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Class Name</th>
            <th>Trainer ID</th>
            <th>Category</th>
            <th>Schedule Time</th>
            <th>Max Participants</th>
        </tr>
        <c:forEach items="${dsClasses}" var="classes">
            <tr>
                <td>${classes.id}</td>
                <td>${classes.className}</td>
                <td>${classes.trainer.id}</td>
                <td>${classes.category}</td>
                <td>${classes.scheduleTime}</td>
                <td>${classes.maxParticipants}</td>
            </tr>
        </c:forEach>
    </table>
</c:when>

<c:when test="${action == 'classesDetail'}">
    <h2>Class Information</h2>
    <c:forEach items="${classInfo}" var="classes">
        <p><strong>ID:</strong> ${classes.id}</p>
        <p><strong>Name:</strong> ${classes.className}</p>
        <p><strong>Category:</strong> ${classes.category}</p>
        <p><strong>Max Participants:</strong> ${classes.maxParticipants}</p>
    </c:forEach>

    <h2>Registered Members</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Member Code</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Date Of Birth</th>
            <th>Join Date</th>
            <th>Status</th>
        </tr>
        <c:forEach items="${dsMembers}" var="member">
            <tr>
                <td>${member.id}</td>
                <td>${member.memberCode}</td>
                <td>${member.fullName}</td>
                <td>${member.email}</td>
                <td>${member.phone}</td>
                <td>${member.dob}</td>
                <td>${member.joinDate}</td>
                <td>${member.status}</td>

            </tr>
        </c:forEach>
    </table>
</c:when>

<c:when test="${action == 'statistics'}">
    <h2>Statistics: Classes by Category</h2>
    <table border="1">
        <tr>
            <th>Category</th>
            <th>Number of Classes</th>
        </tr>
        <c:forEach var="row" items="${stats}">
            <tr>
                <td>${row[0]}</td>
                <td>${row[1]}</td>
            </tr>
        </c:forEach>
    </table>
</c:when>

<c:when test="${action == 'top-attendance'}">
    <h1>Top 5 Members by Attendance</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Member Code</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Date Of Birth</th>
            <th>Join Date</th>
            <th>Status</th>
            <th>Attendance Count</th>
        </tr>
        <c:forEach items="${topMembers}" var="row">
            <c:set var="m" value="${row[0]}"/>
            <c:set var="count" value="${row[1]}"/>
            <tr>
                <td>${m.id}</td>
                <td>${m.memberCode}</td>
                <td>${m.fullName}</td>
                <td>${m.email}</td>
                <td>${m.phone}</td>
                <td>${m.dob}</td>
                <td>${m.joinDate}</td>
                <td>${m.status}</td>
                <td>${count}</td>
            </tr>
        </c:forEach>
    </table>
</c:when>

<c:when test="${action == 'register'}">
<h2>Đăng ký lớp tập</h2>
    <form method="post" action="${pageContext.request.contextPath}/register">
        Member ID: <input type="number" name="memberId" required><br>
        Class ID: <input type="number" name="classId" required><br>
        <input type="submit" value="Register">
    </form>
    </c:when>
    </c:choose>
</body>
</html>
