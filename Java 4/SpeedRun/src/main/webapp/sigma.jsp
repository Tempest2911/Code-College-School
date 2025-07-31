<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 7/31/2025
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer List</title>
    <c:if test="${not empty dscustomers}">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Fist name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>City</th>
                <th>Country</th>
                <th>Postal Code</th>
                <th>Date of Birth</th>
                <th>Created at</th>
                <th>Is Active</th>
                <th>Gender</th>
                <th>Notes</th>
            </tr>
            <c:forEach var="custom" items="${dscustomers}">
                <tr>
                    <td>${custom.id}</td>
                    <td>${custom.firstName}</td>
                    <td>${custom.lastName}</td>
                    <td>${custom.email}</td>
                    <td>${custom.phoneNumber}</td>
                    <td>${custom.address}</td>
                    <td>${custom.city}</td>
                    <td>${custom.country}</td>
                    <td>${custom.postalCode}</td>
                    <td>${custom.dateOfBirth}</td>
                    <td>${custom.createdAt}</td>
                    <td>${custom.isActive}</td>
                    <td>${custom.gender}</td>
                    <td>${custom.notes}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</head>
<body>

</body>
</html>
