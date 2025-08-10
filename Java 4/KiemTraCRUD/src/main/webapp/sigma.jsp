<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BAKA</title>
</head>
<body>

<h2>Add new</h2>
<form id="destForm" action="/destination/add" method="post" onsubmit="return validateForm()">
    Name: <input type="text" name="name" id="name"> <br>
    Country: <input type="text" name="country" id="country"> <br>
    Budget Est: <input type="text" name="budgetEst" id="budgetEst"> <br>
    Is Visited:
    Có: <input type="radio" name="isVisited" value="true" checked>
    Không: <input type="radio" name="isVisited" value="false">
    <br>
    <button type="submit">Save</button>
</form>

<form action="/destination/soft" method="get">
    <button type="submit">Soft</button>
</form>

<h2>Table</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Country</th>
        <th>Budget Est</th>
        <th>Is Visited</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${danhSach}" var="sp">
        <tr>
            <td>${sp.id}</td>
            <td>${sp.name}</td>
            <td>${sp.country}</td>
            <td>${sp.budgetEst}</td>
            <td>${sp.isVisited == "true" ? "Co" : "Ko"}</td>
            <td>
                <a href="/destination/viewUpdate?id=${sp.id}">View Update</a>
                <a href="/destination/delete?id=${sp.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="/destination/search" method="get" id="searchForm">
    <h2>Search</h2> <br>
    <input type="text" name="name" placeholder="Enter search term">
    <button type="submit">Search</button>

</form>

</body>

<script>
    function validateForm() {
        let name = document.getElementById("name").value.trim();
        let country = document.getElementById("country").value.trim();
        let budgetEst = document.getElementById("budgetEst").value.trim();

        if (name === "" || name === null || country === "" || country === null || budgetEst === "" || budgetEst === null) {
            alert("Please fill in all fields.");
            return false;
        }
        if (budgetEst <= 0) {
            alert("Budget Est must be greater than 0.");
            return false;
        }

        return true;
    }
</script>
</html>