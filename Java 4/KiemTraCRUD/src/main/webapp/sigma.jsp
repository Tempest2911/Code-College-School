<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Destination Manager</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'listDestination' || action == 'add'}">

        <!-- Add Form -->
        <h2>Add New Destination</h2>
        <form action="${pageContext.request.contextPath}/destination/add" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required><br><br>

            <label for="country">Country:</label>
            <select id="country" name="country">
                <option value="VN">Việt Nam</option>
                <option value="singapore">Singapore</option>
                <option value="France">Pháp</option>
            </select><br><br>

            <label for="budgetEst">Budget Est:</label>
            <input type="number" id="budgetEst" name="budgetEst" required><br><br>

            <label for="isVisited">Visited:</label>
            <input type="checkbox" id="isVisited" name="isVisited" value="true"><br><br>

            <input type="submit" value="Add Destination">
        </form>

        <!-- List -->
        <h2>List</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Country</th>
                <th>Budget Est</th>
                <th>Is Visited</th>
                <th>Actions</th>
            </tr>

            <c:forEach var="destination" items="${destination}">
                <tr>
                    <td>${destination.id}</td>
                    <td>${destination.name}</td>
                    <td>${destination.country}</td>
                    <td>${destination.budgetEst}</td>
                    <td>${destination.isVisited}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/destination/delete?id=${destination.id}">Delete</a> |
                        <a href="${pageContext.request.contextPath}/destination/update?id=${destination.id}">Detail</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>

    <c:when test="${action == 'update'}">
        <h1>Update Destination</h1>
        <form action="${pageContext.request.contextPath}/destination/update" method="post">
            <input type="hidden" name="id" value="${destination.id}">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${destination.name}" required><br><br>

            <label for="country">Country:</label>
            <select id="country" name="country">
                <option value="VN" ${destination.country == 'VN' ? 'selected' : ''}>Việt Nam</option>
                <option value="singapore" ${destination.country == 'singapore' ? 'selected' : ''}>Singapore</option>
                <option value="France" ${destination.country == 'France' ? 'selected' : ''}>Pháp</option>
            </select><br><br>

            <label for="budgetEst">Budget Est:</label>
            <input type="number" id="budgetEst" name="budgetEst" value="${destination.budgetEst}" required><br><br>

            <label for="isVisited">Visited:</label>
            <input type="checkbox" id="isVisited" name="isVisited" value="true"
                ${destination.isVisited ? 'checked' : ''}><br><br>

            <input type="submit" value="Update Destination">
        </form>
    </c:when>
</c:choose>
</body>
</html>