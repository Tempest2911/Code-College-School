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

    <c:choose>
        <c:when test="${action == 'listProducts' || action == 'drinks' || action == 'outStocks' || action == 'prepare-expire' || action == 'searchProducts'}">
            <h1>List Products</h1>
            <table border="1">
            <c:if test="${not empty products}">
                <tr>
                    <th>id</th>
                    <th>productName</th>
                    <th>category</th>
                    <th>supplier</th>
                    <th>quantityInStock</th>
                    <th>unitPric</th>
                    <th>reorderLevel</th>
                    <th>discontinued</th>
                    <th>description</th>
                    <th>manufactureDate</th>
                    <th>expiryDate</th>
                    <th>createdAt</th>
                    <th>barcode</th>


                </tr>
                <c:forEach items="${products}" var="mb">
                    <tr>
                        <td>${mb.id}</td>
                        <td>${mb.productName}</td>
                        <td>${mb.category}</td>
                        <td>${mb.supplier}</td>
                        <td>${mb.quantityInStock}</td>
                        <td>${mb.unitPrice}</td>
                        <td>${mb.reorderLevel}</td>
                        <td>${mb.discontinued}</td>
                        <td>${mb.description}</td>
                        <td>${mb.manufactureDate}</td>
                        <td>${mb.expiryDate}</td>
                        <td>${mb.createdAt}</td>
                        <td>${mb.barcode}</td>
                    </tr>

                </c:forEach>
                </table>
            </c:if>
        </c:when>
    </c:choose>


</head>
<body>

</body>
</html>
