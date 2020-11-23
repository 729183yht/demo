<%--
  Created by IntelliJ IDEA.
  User: Y
  Date: 2020/11/8
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <script src="js/jquery.min.js"></script>
    <script>
       $(function () {
           $("#butten").click(function () {
                $.ajax({
                    url:"user/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"于慧涛","password":"123123","age":"300"}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        alert(data);
                        alert(data.username);
                        alert(data.age);
                        alert(data.password)
                    }
                });
           });
       });
    </script>
<body>
<a href="user/testString">返回字符串</a>

<a href="/user/testVoid">返回Void</a>

<button id="butten">发送异步请求</button>
</body>
</html>
