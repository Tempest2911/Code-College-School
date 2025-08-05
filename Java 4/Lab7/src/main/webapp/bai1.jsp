<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>AJAX Nhân Viên</title>
</head>
<body>
<h2>Thông tin nhân viên</h2>
<button onclick="layThongTinNhanVien()">Lấy thông tin</button>

<div id="ketQua"></div>

<script>
    function layThongTinNhanVien() {
        fetch("nhanVien", {
            method: "POST"
        })
            .then(response => response.json())
            .then(data => {
                console.log("JSON từ servlet:", data);
            })
    }
</script>
</body>
</html>