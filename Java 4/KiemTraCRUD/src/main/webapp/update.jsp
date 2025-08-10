<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 8/9/2025
  Time: 11:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nigger Update</title>
</head>
<body>
<h2>Update</h2>
<form action="/destination/update" method="post" onsubmit="return validateForm()">
    ID: <input type="text" name="id" value="${destination.id}" readonly> <br>
    Name: <input type="text" name="name" id="name" value="${destination.name}"> <br>
    Country: <input type="text" name="country" id="country" value="${destination.country}"> <br>
    Budget Est: <input type="text" name="budgetEst" id="budgetEst" value="${destination.budgetEst}"> <br>
    Is Visited:
    Co: <input type="radio" name="isVisited" value="true" ${destination.isVisited == "true" ? "checked" : ""}>
    Ko: <input type="radio" name="isVisited" value="false" ${destination.isVisited == "false" ? "checked" : ""}>
    <br>
    <button type="submit">Update</button>
</form>

</body>
<script>
    function validateForm(){
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
