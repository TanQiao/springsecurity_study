<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/12
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/Module/j_spring_security_check" method="post">
  姓名：<input type="text" name="j_username"/><br/>
  密码：<input type="password" name="j_password"/><br/>
  <input type="submit" value="提交">
</form>
</body>
</html>
