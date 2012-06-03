<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListersIn - eBay listing software</title>
<link rel="stylesheet" type="text/css" href="/css/ebay.css">
<link rel="shortcut icon" href="/img/favicon.png">
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>
</head>
<body>

<div id="container-white">
  
  <div id="header">
	<div id="logo">
	  <a href="/"><img src="/img/logo.png" /></a>
	</div>
  </div>
  
  <div id="content960">
	
	<div style="margin:20px; float:left;">
	  <div>
		<h1>ListersIn - eBay listing software</h1>
	  </div>
	  <ul>
		<li>This service is a listing software for eBay sellers.</li>
		<li>This service is BETA version.</li>
		<li>Manage multiple ebay accounts.</li>
		<li>Simple and easy user interface.</li>
		<li>Currently free plan only.</li>
		<li><a href="http://forum.listers.in/">Forum about this service is here.</a></li>
	  </ul>
	  
	  <div id="risknotice">
		** IMPORTANT NOTICE **
		<ul>
		  <li>This listing tool is under development.</li>
		  <li>There may be problems with eBay API call.</li>
		  <li>Your eBay account may be suspended by eBay.</li>
		  <li>Please be careful about using this seller software.</li>
		  <li>If you're interested, please try this software and leave your comment <a href="http://forum.listers.in/">here</a> :)</li>
		</ul>
	  </div>
	  
	  <!--
	  todo: signup tutorial
	  -->


<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
<script>
new TWTR.Widget({
  version: 2,
  type: 'profile',
  rpp: 4,
  interval: 30000,
  width: 400,
  height: 200,
  theme: {
    shell: {
      background: '#666666',
      color: '#ffffff'
    },
    tweets: {
      background: '#ffffff',
      color: '#333333',
      links: '#009900'
    }
  },
  features: {
    scrollbar: true,
    loop: false,
    live: false,
    behavior: 'all'
  }
}).render().setUser('ListersIn').start();
</script>
	  
	</div>
	
	<div style="float:right;">
	  
	  <div id="signinbox">
		
		<div style="margin-bottom:10px; font-weight:bold;">
		  Sign in
		</div>
		
		<form method="post" action="/">
		  <label for="email">Email</label><br />
		  <input type="text" id="email" name="email" size="40" value=""><br /><br />
		  
		  <label for="password">Password</label><br />
		  <input type="password" id="password" name="password" size="40" value=""><br /><br />
		  
		  <button>Sign in</button><br /><br />
		  
		  <a href="#">Forgot password ?</a>
		</form>
		
	  </div>
	  
	  <div id="signupbox">
		
		<div style="margin-bottom:10px; font-weight:bold;">
		  Sign up
		</div>
		
		<form method="post" action="/json/register">
		  Email<br />
		  <input type="text" name="email" size="40"><br /><br />
		  
		  Password<br />
		  <input type="text" name="password" size="40"><br /><br />
		  
		  Confirm password<br />
		  <input type="text" name="password2" size="40"><br /><br />
		  
		  <button>Sign up</button>
		</form>
		
	  </div>
	  
	</div>	  
	
	<div style="clear:both;"></div>
	
  </div>
  
  <div style="clear:both;"></div>

</div>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-32099440-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

</body>
</html>
