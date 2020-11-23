<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/11/18
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="account/findAll2">测试查询</a>

    <form action="account/save" method="post">
        姓名：<input type="text" name="name"><br>
        金额：<input type="text" name="money">
        <input type="submit" value="保存">
    </form>
</body>
</html>
