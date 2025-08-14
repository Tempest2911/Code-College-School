<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trang quản trị Admin</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #007bff;
            color: white;
            padding: 15px 30px;
            font-size: 20px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        main {
            max-width: 800px;
            margin: 40px auto;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        p {
            color: #555;
            margin-bottom: 30px;
        }
        .menu {
            display: flex;
            justify-content: center;
            gap: 20px;
            flex-wrap: wrap;
        }
        .card {
            background: white;
            border-radius: 10px;
            padding: 20px;
            width: 220px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            text-align: center;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
        }
        .card a {
            text-decoration: none;
            color: white;
            display: inline-block;
            margin-top: 15px;
            padding: 10px 15px;
            border-radius: 6px;
            background-color: #007bff;
            font-weight: bold;
            transition: background-color 0.2s ease;
        }
        .card a:hover {
            background-color: #0056b3;
        }
        .logout {
            margin-top: 30px;
        }
        .logout a {
            text-decoration: none;
            color: white;
            display: inline-block;
            margin-top: 15px;
            padding: 10px 15px;
            border-radius: 6px;
            background-color: #ff0000;
            font-weight: bold;
            transition: background-color 0.2s ease;
        }
        .logout a:hover {
            background-color: #a71d2a;
        }
    </style>
</head>
<body>

<header>
    Xin chào ${currentUser.name}
</header>

<main>
    <h2>Trang quản trị hệ thống</h2>
    <p>Chọn chức năng bạn muốn quản lý</p>

    <div class="menu">
        <div class="card">
            <h3>📚 Quản lý sách</h3>
            <p>Thêm, sửa, xóa và tìm kiếm sách trong thư viện</p>
            <a href="${pageContext.request.contextPath}/admin/books">Vào quản lý</a>
        </div>

        <div class="card">
            <h3>📄 Quản lý mượn</h3>
            <p>Xem và duyệt các yêu cầu mượn sách của sinh viên</p>
            <a href="${pageContext.request.contextPath}/admin/borrow_requests">Vào quản lý</a>
        </div>
    </div>

    <div class="logout">
        <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
    </div>
</main>

</body>
</html>
