<%--
  Created by IntelliJ IDEA.
  User: hojak
  Date: 2017-04-04
  Time: 오후 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<form action="signup.do" method="post">
    e-mail : <input type="text" name="email" maxlength="25"><br>
    암호 : <input type="password" name="pwd" maxlength="8"> <br>
    <input type="submit" value="저장">
</form>
</body>
</html>