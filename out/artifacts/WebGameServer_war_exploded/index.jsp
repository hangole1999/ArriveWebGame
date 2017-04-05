<%--
  Created by IntelliJ IDEA.
  User: Hangole
  Date: 2017-04-04
  Time: 오후 7:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
	<title>Arrive</title>
	<!-- CSS  -->
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
	<style>
	body.center-hor {
		margin: 15%;
	}
	</style>
</head>
<body class="center-hor">
	<div class="container truncate row">
		<div class="col 20 s6">
			<br>
			<br>
			<span class="blue-grey-text" style="line-height:50%;">
				<font size=30>Arrive</font><br>Just survive, Arrive.<br><br><font size = 1>Developers<br>&nbsp;&nbsp;Frontend : Kim gi hwang(30103), Cho sung bin(30118)<br>&nbsp;&nbsp;Backend : Gweon jang ho(30101), Kim jin seong(30107)</font>
			</span>
		</div>

		<div class="col 20 s6">
		  <h4 class="valign blue-grey-text">LOGIN</h4>

			<form name="informations" action="/login.do" method="post">
	      <div class="row">
	        <div class="input-field">
	          <input id="id" name="id" list="0" type="text" minlength="5" maxlength="20" length="20" required class="validate" autofocus>
	          <label for="id">Identity</label>
	        </div>
	      </div>

	      <div class="row">
	        <div class="input-field">
	          <input id="password" name="password" list="1" type="password" minlength="5" maxlength="20" length="20" required class="validate">
	          <label for="password">Password</label>
	        </div>
	      </div>

				<button class="btn waves-effect waves-light cyan left tooltipped" style="width:48%" data-position="bottom" data-delay="50" data-tooltip="Login" type="submit" name="action">
					Login
			    <i class="material-icons right">send</i>
			  </button>
			</form>

			<a href="/join" class="btn waves-effect waves-light cyan right tooltipped" style="width:48%" data-position="bottom" data-delay="50" data-tooltip="Join">
				join
				<i class="material-icons right">assignment_ind</i>
			</a>
		</div>
	</div>

	<div class="fixed-action-btn" style="bottom: 16px; top: -2px; left: 16px; width: 60px; overflow-y: auto; overflow-x: hidden;">
		<center>
				<a href="">
				<div class="card hoverable light-blue lighten-2 tooltipped" style="padding:10px; width:40px; height: 50px;" data-position="right" data-delay="50" data-tooltip="Home">
			  	<center><i class="material-icons" style="height:20px"><font color=white size=4.5>home</font></i></center>
				</div>
			</a>
		</center>
	</div>

	<!--  Scripts-->
	<script src="js/jquery.min.js"></script>
	<script src="js/materialize.min.js"></script>SS
</body>
</html>
