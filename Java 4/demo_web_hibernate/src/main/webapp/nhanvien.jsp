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
            gap: 40px; /* khoảng cách giữa các bảng */
        }


        table {
            border-collapse: collapse;
            width: auto;
        }

        th, td {
            padding: 8px 12px;
            border: 1px solid #999;
            text-align: left;
        }


        .gluten-free {
            background-color: #d1ffd1; /* xanh nhẹ nhìn "healthy" */
            font-weight: bold;
        }
    </style>
</head>
<body>


<%--    <div>--%>
<%--        <h1>List of Cupcake</h1>--%>
<%--        <table>--%>
<%--            <!-- bảng cupcake -->--%>
<%--            <tr>--%>
<%--                <th>Flavor</th>--%>
<%--                <th>Frosting</th>--%>
<%--                <th>Price</th>--%>
<%--                <th>BakedOn</th>--%>
<%--            </tr>--%>
<%--            <c:forEach items="${dsCupCake}" var="cake">--%>
<%--                <tr--%>
<%--                        <c:if test="${cake.isGlutenFree == 1}">--%>
<%--                            class="gluten-free"--%>
<%--                        </c:if>--%>
<%--                >--%>
<%--                    <td>${cake.flavor}</td>--%>
<%--                    <td>${cake.frosting}</td>--%>
<%--                    <td>${cake.price}</td>--%>
<%--                    <td>${cake.bakedOn}</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>
<%--    </div>--%>

<%--    <div>--%>
<%--        <h1>List of RetroGame</h1>--%>
<%--        <table>--%>
<%--            <!-- bảng cupcake -->--%>
<%--            <tr>--%>
<%--                <th>Title</th>--%>
<%--                <th>Console</th>--%>
<%--                <th>ReleaseYear</th>--%>
<%--                <th>IsMultiplayer</th>--%>
<%--                <th>Rating</th>--%>
<%--            </tr>--%>
<%--            <c:forEach items="${dsRetroGame}" var="retroGame">--%>
<%--                <tr--%>
<%--                        <c:if test="${retroGame.isMultiplayer == 1}">--%>
<%--                            class="gluten-free"--%>
<%--                        </c:if>--%>
<%--                >--%>
<%--                    <td>${retroGame.title}</td>--%>
<%--                    <td>${retroGame.console}</td>--%>
<%--                    <td>${retroGame.releaseYear}</td>--%>
<%--                    <td>${retroGame.isMultiplayer}</td>--%>
<%--                    <td>${retroGame.rating}</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </table>--%>
<%--    </div>--%>

    <div>
        <h1>List of TalentPet</h1>
        <table>
            <!-- bảng cupcake -->
            <tr>
                <th>Name</th>
                <th>Species</th>
                <th>TrickPerformed</th>
                <th>Score</th>
                <th>IsFinalist</th>
            </tr>
            <c:forEach items="${dsTalentPet}" var="talent">
                <tr>
                    <td>${talent.name}</td>
                    <td>${talent.species}</td>
                    <td>${talent.trickPerformed}</td>
                    <td>${talent.score}</td>
                    <td>${talent.isFinalist}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>



