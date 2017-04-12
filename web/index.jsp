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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>Arrive</title>
</head>
<body>
<header>
  <div class="title card">
    <h1>Arrive</h1>
  </div>
</header>
<div class="login-form">
  <form action="login.do" method="post">
    <div>
      <input type="text" placeholder="ID" class="input login-form__input" name="id" required autofocus>
    </div>
    <div>
      <input type="password" placeholder="Password" class="input login-form__input" name="password" required>
    </div>
    <button type="submit" class="btn btn-lg login-form-signin">SIGN IN</button>
    <div class="login-form-sns">
      <button type="button" class="btn btn-sm login-form-facebook"><i class="fa fa-facebook"></i> Sign in with Facebook
      </button>
      <button type="button" class="btn btn-sm login-form-google"><i class="fa fa-google"></i> Sign in with Google
      </button>
      <button type="button" class="btn btn-sm login-form-twitter"><i class="fa fa-twitter"></i> Sign in with Twitter
      </button>
    </div>
    <a href="#signup-modal" id="signup__a" class="login-form-signup">You don't have an account? Join now!</a>
  </form>

  <div id="signup-modal" class="modal">
    <div class="modal-dialog">
      <i class="fa fa-close modal-dialog-close" id="modal-close"></i>
      <div class="modal-header">
        <div class="modal-dialog-title">
          <h3>Welcome!</h3>
        </div>
      </div>
      <form action="signup.do" method="post">
        <div class="modal-body">
          <div>
            <input type="text" placeholder="ID" class="input signup-form__input" id="signup-form-id" name="id" required autofocus>
            <input type="password" placeholder="Password" id="signup-form-password" class="input signup-form__input" name="password" required>
            <div class="input-group">
              <input type="password" placeholder="Confirm password" id="signup-form-confirm" class="input signup-form__input" required>
              <i class="fa fa-times input-group-addon"></i>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-lg signup-form-signup">SIGN UP</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="js/open_modal.js"></script>
<script src="js/modal.js"></script>
</body>
</html>