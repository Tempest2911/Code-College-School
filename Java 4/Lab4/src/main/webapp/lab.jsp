<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 7/17/2025
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>List of Nigger</title>
    <style>
        .container {
            display: flex;
            flex-wrap: wrap;
            gap: 40px;
            justify-content: center;
        }

        table {
            border-collapse: collapse;
        }

        th, td {
            padding: 8px 12px;
            border: 1px solid cyan;
            text-align: center;
        }

        h1 {
            text-align: center;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="box">
        <h1>List of Simple User</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Email</th>
            </tr>
            <c:forEach items="${dsSimpleUser}" var="simpleUser">
                <tr>
                    <td>${simpleUser.id}</td>
                    <td>${simpleUser.username}</td>
                    <td>${simpleUser.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="box">
        <h1>List of Contact</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Fullname</th>
                <th>Email</th>
                <th>Birth Date</th>
            </tr>
            <c:forEach items="${dsContact}" var="contact">
                <tr>
                    <td>${contact.id}</td>
                    <td>${contact.fullName}</td>
                    <td>${contact.email}</td>
                    <td>${contact.birthDate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="box">
        <h1>List of Product</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>In Stock</th>
                <th>Created At</th>
            </tr>
            <c:forEach items="${dsProduct}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.inStock}</td>
                    <td>${product.formattedCreatedAt}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="box">
        <h1>List of Task</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Status</th>
                <th>Due Date</th>
            </tr>
            <c:forEach items="${dsTask}" var="task">
                <tr>
                    <td>${task.id}</td>
                    <td>${task.title}</td>
                    <td>${task.description}</td>
                    <td>${task.status}</td>
                    <td>${task.dueDate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="box">
        <h1>List of News letter Subscriber</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Subscribed</th>
                <th>Subscribed At</th>
            </tr>
            <c:forEach items="${dsNewsletterSubscriber}" var="newsletterSubscriber">
                <tr>
                    <td>${newsletterSubscriber.id}</td>
                    <td>${newsletterSubscriber.email}</td>
                    <td>
                        <c:choose>
                            <c:when test="${newsletterSubscriber.subscribed}">
                                Yes
                            </c:when>
                            <c:otherwise>
                                No
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${newsletterSubscriber.formattedCreatedAt}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>



