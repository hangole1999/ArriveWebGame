<%--
  Created by IntelliJ IDEA.
  User: BeINone
  Date: 2017-04-06
  Time: 오후 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/main.css" rel="stylesheet">
    <title>Arrive</title>
</head>
<body>
    <header>
        <div class="title card">
            <h1>Arrive</h1>
        </div>
    </header>
    <div class="login-form">
        <form action="/login.do" method="post">
            <div>
                <div>
                    <input type="text" placeholder="ID" class="input login-form__input" required="" autofocus="">
                </div>
                <div>
                    <input type="password" placeholder="Password" class="input login-form__input" required="">
                </div>
                <button type="submit" class="btn login-form-signin">SIGN IN</button>
                <a href="/signup" class="login-form-signup">You don't have an account? Join now!</a>
            </div>
        </form>
    </div>
</body>
</html>