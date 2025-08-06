<%--
  Created by IntelliJ IDEA.
  User: drago
  Date: 8/5/2025
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload File</title>
</head>
<body>
<input type="file" id="fileInput">
<button onclick="uploadFile()">Upload</button>

<script>
    function uploadFile() {
        var file = document.getElementById("fileInput").files[0];
        var formData = new FormData();
        formData.append("file", file);

        fetch("upload", {
            method: "POST",
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log("File info received:");
                console.log("Name:", data.name);
                console.log("Type:", data.type);
                console.log("Size:", data.size + " bytes");
            })
            .catch(error => console.error("Upload failed:", error));
    }
</script>
</body>
</html>

