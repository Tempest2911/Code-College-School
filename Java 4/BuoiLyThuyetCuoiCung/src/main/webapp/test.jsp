<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 8/7/2025
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nigger</title>
</head>
<body>
   <c:choose>
       <c:when test="${action == 'listTables' || action == 'add' || action == 'search'}">


           <!-- Add Form -->
           <h2>Add New Attendance</h2>
           <form action="/attendance/create" method="post">
               <label for="studentName">Student Name:</label>
               <input type="text" id="studentName" name="studentName" required><br><br>

               <label for="classDate">Class Date:</label>
               <input type="date" id="classDate" name="classDate" required><br><br>

               <label for="status">Status:</label>
               <select id="status" name="status">
                   <option value="Present">Present</option>
                   <option value="Absent">Absent</option>
               </select><br><br>

               <input type="submit" value="Add Attendance">
           </form>

           <h2>List</h2>
           <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Date</th>
                    <th>Status</th>
                </tr>

               <c:forEach var="dsStudent" items="${dsStudent}">
                <tr>
                    <td>${dsStudent.id}</td>
                    <td>${dsStudent.studentName}</td>
                    <td>${dsStudent.classDate}</td>
                    <td>${dsStudent.status}</td>
                    <td>
                        <a href="/attendance/delete?id=${dsStudent.id}">Delete</a>
                    </td>
                    <td>
                        <a href="/attendance/update?id=${dsStudent.id}">Detail</a>
                    </td>
                </tr>
               </c:forEach>
           </table>
       </c:when>

       <c:when test="${action == 'update'}">
           <h1>Update Attendance</h1>
           <form action="/attendance/update" method="post">
               <input type="hidden" name="id" value="${student.id}" />

               <label for="studentName">Student Name:</label>
               <input type="text" id="studentName" name="studentName" value="${student.studentName}" required><br><br>

               <label for="classDate">Class Date:</label>
               <input type="date" id="classDate" name="classDate" value="${student.classDate}" required><br><br>

               <label for="status">Status:</label>
               <select id="status" name="status">
                   <option value="Present" ${student.status == 'Present' ? 'selected' : ''}>Present</option>
                   <option value="Absent" ${student.status == 'Absent' ? 'selected' : ''}>Absent</option>
               </select><br><br>

               <input type="submit" value="Update Attendance">
           </form>
       </c:when>

   </c:choose>
</body>
</html>
