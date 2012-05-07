<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListersIn - Listing tool for eBay sellers.</title>
<link rel="stylesheet" type="text/css" href="/css/ebay.css">
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>
</head>
<body>

<div id="container">

  <div id="header">
	<div id="logo"><a href="/">ListersIn</a></div>
  </div>
  
  <div id="content">
	
	<div id="signinbox">
	  <form method="post" action="/">
		<label for="email">Email</label>
		<input type="text" id="email" name="email" size="20" value=""><br />
		<label for="password">Password</label>
		<input type="password" id="password" name="password" size="20" value=""><br />
		<input type="submit" value="Login">
	  </form>
	</div>
	
	<div style="margin:10px; clear:both;">
	  <b>Service description</b><br><br>
	  <ul>
		<li style="color:red;">This service is under construction.</li>
		<li>Mailer like user interface.</li>
		<li>Tablet friendly user interface.</li>
		<li>Manage multiple ebay accounts.</li>
	  </ul>
	</div>
	
	<div style="margin:10px; clear:both;">
	  <b>New user registration</b><br><br>
	  
	  <form method="post" action="/json/register">
		Email
		<input type="text" name="email" size="20"><br>
		Password
		<input type="text" name="password" size="20"><br>
		Re-type password
		<input type="text" name="password2" size="20"><br>
		<button id="register">Register</button>
	  </form>
	  
	</div>
	
  </div>
  
  <div style="clear:both;"></div>

</div>

</body>
</html>
