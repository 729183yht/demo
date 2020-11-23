<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/11/9
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data" action="/user/fileUpLoad1">
        选择文件：<input type="file" name="upload">
        <input type="submit" value="上传">
    </form>

    <form method="post" enctype="multipart/form-data" action="/user/fileUpLoad2 ">
        选择文件：<input type="file" name="upload">
        <input type="submit" value="上传">
    </form>

    <h3>跨服务器上传</h3>
    <form method="post" enctype="multipart/form-data" action="/user/fileUpLoad3 ">
        选择文件：<input type="file" name="upload">
        <input type="submit" value="上传">
    </form>
</body>
</html>
