<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 7/29/2025
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bigger Digger Nigger</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'listBooks' || action == 'available' || action == 'by-category'}">
        <h1>MEMBERS</h1>
        <c:if test="${not empty dsbooks}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>ISBN</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Publisher</th>
                    <th>PublishYear</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    <th>CreatedAt</th>
                </tr>
                <c:forEach var="book" items="${dsbooks}">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.isbn}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.publisher}</td>
                        <td>${book.publishYear}</td>
                        <td>${book.category}</td>
                        <td>${book.quantity}</td>
                        <td>${book.createdAt}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </c:when>

    <c:when test="${action == 'detail'}">
        <h1>Book Detail</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <td>${book.id}</td>
            </tr>
            <tr>
                <th>ISBN</th>
                <td>${book.isbn}</td>
            </tr>
            <tr>
                <th>Title</th>
                <td>${book.title}</td>
            </tr>
            <tr>
                <th>Author</th>
                <td>${book.author}</td>
            </tr>
            <tr>
                <th>Publisher</th>
                <td>${book.publisher}</td>
            </tr>
            <tr>
                <th>Publish Year</th>
                <td>${book.publishYear}</td>
            </tr>
            <tr>
                <th>Category</th>
                <td>${book.category}</td>
            </tr>
            <tr>
                <th>Quantity</th>
                <td>${book.quantity}</td>
            </tr>
            <tr>
                <th>Created At</th>
                <td>${book.createdAt}</td>
            </tr>
        </table>
        <h3>History borrow</h3>
        <c:if test="${not empty borrowRecords}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Member ID</th>
                    <th>Book</th>
                    <th>Borrow Date</th>
                    <th>Due Date</th>
                    <th>Return Date</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="history" items="${borrowRecords}">
                    <tr>
                        <td>${history.id}</td>
                        <td>${history.member.id}</td>
                        <td>${history.book.id}</td>
                        <td>${history.borrowDate}</td>
                        <td>${history.dueDate}</td>
                        <td>${history.returnDate}</td>
                        <td>${history.status}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </c:when>

    <c:when test="${action == 'listMembers' || action == 'by-status' || action == 'active'}">
        <h1>MEMBERS</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>MemberCode</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Date of Birth</th>
                <th>Membership Date</th>
                <th>Status</th>
            </tr>
            <c:forEach var="members" items="${dsMembers}">
                <tr>
                    <td>${members.id}</td>
                    <td>${members.memberCode}</td>
                    <td>${members.fullName}</td>
                    <td>${members.email}</td>
                    <td>${members.phone}</td>
                    <td>${members.dob}</td>
                    <td>${members.membershipDate}</td>
                    <td>${members.status}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>

    <c:when test="${action == 'detailMember'}">
        <h1>Member Detail</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <td>${member.id}</td>
            </tr>
            <tr>
                <th>Member Code</th>
                <td>${member.memberCode}</td>
            </tr>
            <tr>
                <th>Full Name</th>
                <td>${member.fullName}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${member.email}</td>
            </tr>
            <tr>
                <th>Phone</th>
                <td>${member.phone}</td>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <td>${member.dob}</td>
            </tr>
            <tr>
                <th>Membership Date</th>
                <td>${member.membershipDate}</td>
            </tr>
            <tr>
                <th>Status</th>
                <td>${member.status}</td>
            </tr>
        </table>
        <h3>Borrowed Books</h3>
        <c:if test="${not empty borrowBooks}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>ISBN</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Publisher</th>
                    <th>Publish Year</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    <th>Created At</th>
                </tr>
                <c:forEach var="borrowrecord" items="${borrowBooks}">
                    <tr>
                        <td>${borrowrecord.book.id}</td>
                        <td>${borrowrecord.book.isbn}</td>
                        <td>${borrowrecord.book.title}</td>
                        <td>${borrowrecord.book.author}</td>
                        <td>${borrowrecord.book.publisher}</td>
                        <td>${borrowrecord.book.publishYear}</td>
                        <td>${borrowrecord.book.category}</td>
                        <td>${borrowrecord.book.quantity}</td>
                        <td>${borrowrecord.book.createdAt}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </c:when>

    <c:when test="${action == 'overdue'}">
        <h3>Overdue</h3>
        <c:if test="${not empty records}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Member ID</th>
                    <th>Book</th>
                    <th>Borrow Date</th>
                    <th>Due Date</th>
                    <th>Return Date</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="borrowRecord" items="${records}">
                    <tr>
                        <td>${borrowRecord.id}</td>
                        <td>${borrowRecord.member.id}</td>
                        <td>${borrowRecord.book.id}</td>
                        <td>${borrowRecord.borrowDate}</td>
                        <td>${borrowRecord.dueDate}</td>
                        <td>${borrowRecord.returnDate}</td>
                        <td>${borrowRecord.status}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </c:when>

    <c:when test="${action == 'statistics'}">
        <h2>Statistics by Category</h2>
        <table border="1">
            <tr>
                <th>Category</th>
                <th>Book Count</th>
            </tr>
            <c:forEach var="stat" items="${stats}">
                <tr>
                    <td>${stat.category}</td>
                    <td>${stat.count}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/books">Back to list</a>
    </c:when>

    <c:when test="${action == 'top-attendance'}">
        <h1>Top 5 Members by Attendance</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>MemberCode</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Date of Birth</th>
                <th>Membership Date</th>
                <th>Status</th>
                <th>Attendance Count</th>
            </tr>
            <c:forEach items="${topBorrow}" var="row">
                <c:set var="members" value="${row[0]}"/>
                <c:set var="count" value="${row[1]}"/>
                <tr>
                    <td>${members.id}</td>
                    <td>${members.memberCode}</td>
                    <td>${members.fullName}</td>
                    <td>${members.email}</td>
                    <td>${members.phone}</td>
                    <td>${members.dob}</td>
                    <td>${members.membershipDate}</td>
                    <td>${members.status}</td>
                    <td>${count}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/members">Back to list</a>
    </c:when>

    <c:when test="${action == 'borrow-form'}">
        <h2>Borrow a Book</h2>
        <c:if test="${not empty error}">
            <p style="color:red">${error}</p>
        </c:if>
        <form action="${pageContext.request.contextPath}/borrow" method="post">
            <label>Member ID: <input type="number" name="memberId" required></label><br>
            <label>Book ID: <input type="number" name="bookId" required></label><br>
            <label>Borrow Date: <input type="date" name="borrowDate" required></label><br>
            <label>Due Date: <input type="date" name="dueDate" required></label><br>
            <button type="submit">Borrow</button>
        </form>
        <a href="${pageContext.request.contextPath}/books">Back to list</a>
    </c:when>



</c:choose>
</body>
</html>
