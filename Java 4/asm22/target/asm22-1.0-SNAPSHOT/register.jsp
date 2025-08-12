<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đăng ký tài khoản mới</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .register-container {
            background: white;
            width: 100%;
            max-width: 400px;
            padding: 35px 40px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.15);
            animation: fadeIn 0.5s ease-in-out;
        }
        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(-20px);}
            to {opacity: 1; transform: translateY(0);}
        }
        h2 {
            margin-bottom: 25px;
            text-align: center;
            color: #333;
        }
        label {
            font-weight: 500;
            display: block;
            margin-top: 15px;
            color: #555;
        }
        input[type=text], input[type=email], input[type=password] {
            width: 100%;
            padding: 10px 12px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 15px;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }
        input:focus {
            border-color: #2575fc;
            box-shadow: 0 0 5px rgba(37,117,252,0.4);
            outline: none;
        }
        .error-message {
            color: #d93025;
            margin-top: 15px;
            text-align: center;
            font-size: 14px;
        }
        button {
            width: 100%;
            margin-top: 25px;
            padding: 12px;
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            font-weight: 600;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 10px rgba(0,0,0,0.15);
        }
        p {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
        }
        a {
            color: #2575fc;
            font-weight: 500;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>Đăng ký tài khoản mới</h2>

    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/register">
        <label>Họ tên:</label>
        <input type="text" name="name" placeholder="Nhập họ và tên" required/>

        <label>Email:</label>
        <input type="email" name="email" placeholder="Nhập email" required/>

        <label>Mật khẩu:</label>
        <input type="password" name="password" placeholder="Nhập mật khẩu" required/>

        <button type="submit">Đăng ký</button>
    </form>

    <p>Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập tại đây</a></p>
</div>
</body>
</html>
