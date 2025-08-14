<%--
  Created by IntelliJ IDEA.
  User: nguyenvandan
  Date: 13/08/2025
  Time: 10:14 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Kiểm tra API SinhVien</h2>

<script>
    fetch('/api/sinh-vien/get-all')
        .then(response => response.json()) // Chuyển dữ liệu trả về từ API thành JSON object
        .then(data => {
            console.log("Danh sách sinh viên:", data); // In dữ liệu ra console
        })
        .catch(error => {
            console.error("Lỗi khi gọi API:", error); // Bắt lỗi nếu fetch thất bại
        });
</script>
</body>
</html>
