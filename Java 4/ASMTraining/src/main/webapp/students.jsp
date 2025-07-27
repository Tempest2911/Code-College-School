<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<html>
<head>
    <title>Student Management</title>
</head>
<body>
<c:choose>

    <c:when test="${action == 'list' || action == 'major' || action == 'high-gpa' || action == 'by-company' || action == 'no-internship'}">
        <h2>Student List</h2>
        <c:if test="${not empty dsStudent}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Student Code</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Date of Birth</th>
                    <th>Gender</th>
                    <th>Major</th>
                    <th>GPA</th>
                    <th>Created At</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="student" items="${dsStudent}">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.studentCode}</td>
                        <td>${student.fullName}</td>
                        <td>${student.email}</td>
                        <td>${student.phone}</td>
                        <td>${student.dob}</td>
                        <td>${student.gender}</td>
                        <td>${student.major}</td>
                        <td>${student.gpa}</td>
                        <td>${student.createdAt}</td>
                        <td>
                            <a href="students/detail?id=${student.id}">Detail</a> |
                            <a href="students/update?id=${student.id}">Edit</a> |
                            <a href="students/delete?id=${student.id}" onclick="return confirm('Delete?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty dsStudent}">
            <p>No students found.</p>
        </c:if>
        <a href="students/add">Add Student</a>
    </c:when>


    <c:when test="${action == 'detail'}">
        <h2>Student Detail</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <td>${student.id}</td>
            </tr>
            <tr>
                <th>Student Code</th>
                <td>${student.studentCode}</td>
            </tr>
            <tr>
                <th>Full Name</th>
                <td>${student.fullName}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${student.email}</td>
            </tr>
            <tr>
                <th>Phone</th>
                <td>${student.phone}</td>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <td>${student.dob}</td>
            </tr>
            <tr>
                <th>Gender</th>
                <td>${student.gender}</td>
            </tr>
            <tr>
                <th>Major</th>
                <td>${student.major}</td>
            </tr>
            <tr>
                <th>GPA</th>
                <td>${student.gpa}</td>
            </tr>
            <tr>
                <th>Created At</th>
                <td>${student.createdAt}</td>
            </tr>
        </table>
        <h3>Internships</h3>
        <c:if test="${not empty internships}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>StudentID</th>
                    <th>Company</th>
                    <th>Position</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Supervisor</th>
                    <th>Feedback</th>
                    <th>Score</th>
                    <th>Created At</th>
                </tr>
                <c:forEach var="intern" items="${internships}">
                    <tr>
                        <td>${intern.id}</td>
                        <td>${intern.student.id}</td>
                        <td>${intern.companyName}</td>
                        <td>${intern.position}</td>
                        <td>${intern.startDate}</td>
                        <td>${intern.endDate}</td>
                        <td>${intern.supervisorName}</td>
                        <td>${intern.feedback}</td>
                        <td>${intern.score}</td>
                        <td>${intern.createdAt}</td>
                    </tr>
                </c:forEach>
            </table>

        </c:if>
        <c:if test="${empty internships}">
            <p>No internships found.</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/students">Back to list</a>

    </c:when>


    <c:when test="${action == 'statistics'}">
        <h2>Statistics by Major</h2>
        <table border="1">
            <tr>
                <th>Major</th>
                <th>Student Count</th>
            </tr>
            <c:forEach var="stat" items="${stats}">
                <tr>
                    <td>${stat.major}</td>
                    <td>${stat.count}</td>
                </tr>
            </c:forEach>
        </table>
          <a href="${pageContext.request.contextPath}/students">Back to list</a>
    </c:when>



    <c:when test="${action == 'add'}">
        <h2>Add Student</h2>
        <form method="post" action="${pageContext.request.contextPath}/students/add">

        <label>Student Code: <input name="studentCode" required></label><br>
            <label>Full Name: <input name="fullName" required></label><br>
            <label>Email: <input name="email"></label><br>
            <label>Phone: <input name="phone"></label><br>
            <label>Date of Birth: <input type="date" name="dob"></label><br>
            <label>Gender: <input name="gender"></label><br>
            <label>Major: <input name="major"></label><br>
            <label>GPA: <input type="number" step="0.01" name="gpa"></label><br>
            <button type="submit">Add</button>
        </form>
          <a href="${pageContext.request.contextPath}/students">Back to list</a>
    </c:when>


    <c:when test="${action == 'update'}">
        <h2>Update Student</h2>
        <form method="post" action="${pageContext.request.contextPath}/students/update">
            <input type="hidden" name="id" value="${student.id}">
            <label>Student Code: <input name="studentCode" value="${student.studentCode}" required></label><br>
            <label>Full Name: <input name="fullName" value="${student.fullName}" required></label><br>
            <label>Email: <input name="email" value="${student.email}"></label><br>
            <label>Phone: <input name="phone" value="${student.phone}"></label><br>
            <label>Date of Birth: <input type="date" name="dob" value="${student.dob}"></label><br>
            <label>Gender: <input name="gender" value="${student.gender}"></label><br>
            <label>Major: <input name="major" value="${student.major}"></label><br>
            <label>GPA: <input type="number" step="0.01" name="gpa" value="${student.gpa}"></label><br>
            <button type="submit">Update</button>
        </form>
          <a href="${pageContext.request.contextPath}/students">Back to list</a>
    </c:when>
</c:choose>
</body>
</html>