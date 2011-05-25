<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ebaytool.jp</title>
<link rel="stylesheet" type="text/css" href="/css/ebay.css">
<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>

</head>
<body>

<div id="container">

<div id="toolbar">
<div id="logo"><a href="/">ebaytool.jp</a></div>

<div style="font-size:11px; margin-right:10px;">
  <form method="post" action="/">
	Email<br />
	<input type="text" name="email" size="15" value=""><br />
	Password<br />
	<input type="password" name="password" size="15" value=""><br />
	<input type="submit" value="Login">
  </form>
</div>

</div>

<div id="content">

<div style="margin:10px;">
<b>Service description</b><br><br>
brabrabra....<br>
brabrabra....<br>
brabrabra....<br>
</div>

<div style="margin:10px;">
<b>New user registration</b><br><br>

<form method="post" action="/register">
Email
<input type="text" name="email" size="20"><br>
Password
<input type="text" name="password" size="20"><br>
Re-type password
<input type="text" name="password2" size="20"><br>
<input type="submit" value="Register">
</form>

</div>

</div>

</div>

</body>
</html>
