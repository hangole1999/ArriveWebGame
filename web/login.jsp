<%--
  Created by IntelliJ IDEA.
  User: dsm_025
  Date: 2017-03-29
  Time: 오후 4:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="login.do" method="post">
    <fieldset>
        <label for="id">아이디 : </label>
        <input type="text" name="id" id="id"/>
        <label for="password">패스워드 : </label>
        <input type="password" name="password" id="password"/>
        <input type="submit">
    </fieldset>
</form>
</body>
</html>
