<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đăng nhập hệ thống</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }

        .login-container {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(12px);
            border: 1px solid rgba(255,255,255,0.2);
            max-width: 420px;
            width: 100%;
            padding: 40px 45px;
            border-radius: 16px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.3);
            animation: fadeIn 0.6s ease;
        }

        h2 {
            margin-bottom: 25px;
            text-align: center;
            color: #fff;
            font-size: 26px;
            font-weight: 700;
            letter-spacing: 1px;
        }

        label {
            font-weight: 500;
            display: block;
            margin-top: 15px;
            color: #f0f0f0;
            font-size: 14px;
        }

        input[type=email], input[type=password] {
            width: 100%;
            padding: 12px 14px;
            margin-top: 6px;
            border: none;
            border-radius: 8px;
            background: rgba(255, 255, 255, 0.85);
            box-sizing: border-box;
            font-size: 14px;
            transition: all 0.2s ease;
        }

        input:focus {
            outline: none;
            background: white;
            box-shadow: 0 0 8px rgba(102, 126, 234, 0.7);
        }

        .error-message {
            color: #ffbaba;
            background: rgba(255, 0, 0, 0.15);
            padding: 10px;
            margin-top: 15px;
            border-radius: 6px;
            text-align: center;
            font-size: 14px;
        }

        .button-group {
            margin-top: 30px;
            display: flex;
            gap: 12px;
        }

        button {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 8px;
            color: white;
            font-size: 15px;
            font-weight: 500;
            cursor: pointer;
            transition: transform 0.15s ease, background 0.3s ease;
        }

        .btn-login {
            background: linear-gradient(135deg, #667eea, #764ba2);
        }

        .btn-login:hover {
            background: linear-gradient(135deg, #5a6fd6, #6a3d94);
            transform: scale(1.03);
        }

        .btn-register {
            background: linear-gradient(135deg, #28a745, #20c997);
        }

        .btn-register:hover {
            background: linear-gradient(135deg, #218838, #17a589);
            transform: scale(1.03);
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-15px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Đăng nhập hệ thống</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required placeholder="Nhập email" />

        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required placeholder="Nhập mật khẩu" />

        <div class="button-group">
            <button type="submit" class="btn-login">Đăng nhập</button>
            <button type="button" class="btn-register"
                    onclick="window.location.href='${pageContext.request.contextPath}/register'">Đăng ký</button>
        </div>
    </form>

    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>
</div>

</body>
</html>
