<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Gym</title>
</head>
<body>
<c:choose>

    <c:when test="${action == 'list'}">
        <h2>Gym Member List</h2>
        <c:if test="${not empty dsGymMember}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Member Code</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Date of Birth</th>
                    <th>Join Date</th>
                    <th>Status</th>
<%--                    <th>Actions</th>--%>
                </tr>
                <c:forEach var="gym" items="${dsGymMember}">
                    <tr>
                        <td>${gym.id}</td>
                        <td>${gym.memberCode}</td>
                        <td>${gym.fullName}</td>
                        <td>${gym.email}</td>
                        <td>${gym.phone}</td>
                        <td>${gym.dob}</td>
                        <td>${gym.joinDate}</td>
                        <td>${gym.status}</td>
<%--                        <td>--%>
<%--                            <a href="gym/detail?id=${gym.id}">Detail</a> |--%>
<%--                            <a href="gym/update?id=${gym.id}">Edit</a> |--%>
<%--                            <a href="gym/delete?id=${gym.id}" onclick="return confirm('Delete?')">Delete</a>--%>
<%--                        </td>--%>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty dsGymMember}">
            <p>No members found.</p>
        </c:if>
<%--        <a href="gym/add">Add Member</a>--%>
    </c:when>

    <c:when test="${action == 'detail'}">
        <h2>Gym Member Detail</h2>
        <table border="1">
            <tr><th>ID</th><td>${member.id}</td></tr>
            <tr><th>Member Code</th><td>${member.memberCode}</td></tr>
            <tr><th>Full Name</th><td>${member.fullName}</td></tr>
            <tr><th>Email</th><td>${member.email}</td></tr>
            <tr><th>Phone</th><td>${member.phone}</td></tr>
            <tr><th>Date of Birth</th><td>${member.dob}</td></tr>
            <tr><th>Join Date</th><td>${member.joinDate}</td></tr>
            <tr><th>Status</th><td>${member.status}</td></tr>
        </table>
        <h3>Class Registrations</h3>
        <c:if test="${not empty registrations}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Class</th>
                    <th>Category</th>
                    <th>Schedule</th>
                    <th>Trainer</th>
                    <th>Register Date</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="reg" items="${registrations}">
                    <tr>
                        <td>${reg.id}</td>
                        <td>${reg.className}</td>
                        <td>${reg.category}</td>
                        <td>${reg.scheduleTime}</td>
                        <td>${reg.trainerName}</td>
                        <td>${reg.registerDate}</td>
                        <td>${reg.attendanceStatus}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty registrations}">
            <p>No class registrations found.</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/gym">Back to list</a>
    </c:when>

    <c:when test="${action == 'add'}">
        <h2>Add Gym Member</h2>
        <form method="post" action="${pageContext.request.contextPath}/gym/add">
            <label>Member Code: <input name="memberCode" required></label><br>
            <label>Full Name: <input name="fullName" required></label><br>
            <label>Email: <input name="email"></label><br>
            <label>Phone: <input name="phone"></label><br>
            <label>Date of Birth: <input type="date" name="dob"></label><br>
            <label>Join Date: <input type="date" name="joinDate"></label><br>
            <label>Status: <input name="status"></label><br>
            <button type="submit">Add</button>
        </form>
        <a href="${pageContext.request.contextPath}/gym">Back to list</a>
    </c:when>

    <c:when test="${action == 'update'}">
        <h2>Update Gym Member</h2>
        <form method="post" action="${pageContext.request.contextPath}/gym/update">
            <input type="hidden" name="id" value="${member.id}">
            <label>Member Code: <input name="memberCode" value="${member.memberCode}" required></label><br>
            <label>Full Name: <input name="fullName" value="${member.fullName}" required></label><br>
            <label>Email: <input name="email" value="${member.email}"></label><br>
            <label>Phone: <input name="phone" value="${member.phone}"></label><br>
            <label>Date of Birth: <input type="date" name="dob" value="${member.dob}"></label><br>
            <label>Join Date: <input type="date" name="joinDate" value="${member.joinDate}"></label><br>
            <label>Status: <input name="status" value="${member.status}"></label><br>
            <button type="submit">Update</button>
        </form>
        <a href="${pageContext.request.contextPath}/gym">Back to list</a>
    </c:when>
</c:choose>
</body>
</html>
