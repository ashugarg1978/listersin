<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListersIn - Yet another eBay listing tool.</title>
<link rel="stylesheet" type="text/css" href="/css/ebay.css">
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>
</head>
<body>

<div id="container-white">
  
  <div id="header">
	<div id="logo"><a href="/">ListersIn</a></div>
  </div>
  
  <div id="content960">
	
	<div style="margin:20px; float:left;">
	  <div style="font-size:1.5em;">
		<span style="font-weight:bold;">ListersIn</span> - Yet another eBay listing tool.
	  </div>
	  <ul>
		<li>This service is under construction...</li>
		<li>Free.</li>
		<li>Simple user interface.</li>
		<li>Manage multiple ebay accounts.</li>
	  </ul>
	</div>
	
	<div style="float:right;">
	  
	  <div id="signinbox">
		<div style="margin-bottom:10px;">
		  Sign in
		</div>
		<form method="post" action="/">
		  
		  <label for="email">Email</label><br />
		  <input type="text" id="email" name="email" size="40" value=""><br />
		  
		  <br />
		  
		  <label for="password">Password</label><br />
		  <input type="password" id="password" name="password" size="40" value=""><br />
		  
		  <br />
		  
		  <input type="submit" value="Sign in">
		  
		</form>
	  </div>
	  
	  <div id="signupbox">
		<div style="margin-bottom:10px;">
		  Sign up
		</div>
		
		<form method="post" action="/json/register">
		  
		  Email<br />
		  <input type="text" name="email" size="40"><br />
		  
		  <br />
		  
		  Password<br />
		  <input type="text" name="password" size="40"><br />
		  
		  <br />
		  
		  Confirm password<br />
		  <input type="text" name="password2" size="40"><br />
		  
		  <br />
		  
		  <button id="register">Sign up</button>
		  
		</form>
	  </div>
	</div>	  
	
	<div style="clear:both;"></div>
	
  </div>
  
  <div style="clear:both;"></div>

</div>

</body>
</html>
