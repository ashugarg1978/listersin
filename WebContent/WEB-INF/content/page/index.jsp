<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListersIn - <s:text name="eBayListingSoftware"/></title>

<meta name="description" content="ListersIn is an eBay listing software. Simple, easy and Gmail like user interface. Try free demo account before sign up!">
<link rel="stylesheet" type="text/css" href="/css/ebay.css">
<link rel="shortcut icon" href="/img/favicon.png">
<script type="text/javascript" src="/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>
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
</head>
<body>

<div id="container-white">
  
  <div id="header">
	  <div id="logo">
	    <a href="/"><img src="/img/logo.png" alt="ListersIn" /></a>
	  </div>
  </div>
  
  <div id="content960">
	  
	  <div style="margin:20px; float:left;">
	    <div style="margin-bottom:10px;">
		    <h1>ListersIn - <s:text name="eBayListingSoftware"/></h1>
	    </div>
      
      <h2>Features</h2>
	    <ul class="features">
		    <li>Manage multiple eBay accounts.</li>
		    <li>Upload 12 pictures for one item to eBay server at once.</li>
		    <li>Simple and easy user interface.</li>
		    <li>Find duplicate items by title.</li>
        <li>FREE.</li>
 	    </ul>
	    
      <h2>Notice</h2>
	    <ul class="notice">
		    <li>This listing tool is under development. There still may be some bugs.</li>
		    <li>Please be careful about using this seller software.</li>
		    <li>Please list to "Test Auction" category before you create actual listings.</li>
      </ul>
      
      <h2>Feedback</h2>
	    <ul class="feedback">
		    <li>We would like to hear from you!</li>
		    <li>
          Please post your comments and suggestions to the 
          <a href="http://forum.listers.in/" target="_blank">forum</a>.
        </li>
		    <li>Or send an email to admin [at] listers.in.</li>
      </ul>
	    
	    <h2>Screenshot</h2>
	    <a href="/img/screenshot.png" target="_blank">
		    <img id="screenshot" src="/img/screenshot.png" />
	    </a>
	    <div>
		    <a href="/img/screenshot.png" target="_blank">Click to enlarge</a>
	    </div>
	    <h2>Blog</h2>
	    <ul class="blog">
		    <s:iterator value="feed.entries">
		      <li>
			      <a href="${link}">${title}</a> ${publishedDate}
			      <p>${description.value}</p>
		      </li>
		    </s:iterator>
	    </ul>
	    
	    <!--
	        todo: signup tutorial
	      -->
      
	  </div>
	  
	  <div style="float:right;">
	    
	    <div id="signinbox">
		    
		    <div style="margin-bottom:10px; font-weight:bold;">
          <s:text name="SignIn"/>
		    </div>
		    
		    <form method="post" action="/">
		      <label for="email"><s:text name="Email"/></label><br />
		      <input type="text" id="email" name="email" size="40" value=""><br />
          <s:text name="DemoAccount"/>: demo@listers.in<br/>
          <br />
		      
		      <label for="password"><s:text name="Password"/></label><br />
		      <input type="password" id="password" name="password" size="40" value=""><br />
          <s:text name="DemoAccount"/>: demo<br/>
          <br />
		      
		      <button><s:text name="SignIn"/></button>
		      
		      <a href="#">Forgot password ?</a>
		    </form>
		    
	    </div>
	    
	    <div id="signupbox">
		    
		    <div style="margin-bottom:10px; font-weight:bold;">
          <s:text name="SignUp"/>
		    </div>
		    <div id="signupmessage"></div>
		    
		    <form method="post" action="/json/register">
		      <s:text name="Email"/><br />
		      <input type="text" name="email" size="40"><br /><br />
		      
		      <s:text name="Password"/><br />
		      <input type="text" name="password" size="40"><br /><br />
		      
		      <s:text name="ConfirmPassword"/><br />
		      <input type="text" name="password2" size="40"><br /><br />
		      
		      <button><s:text name="SignUp"/></button>
		    </form>
		    
	    </div>

	  <div id="twitter">
	  
		<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
		<script>
  	    new TWTR.Widget({
  	      version: 2,
  	      type: 'profile',
  	      rpp: 4,
  	      interval: 30000,
  	      width: 362,
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
	  
	</div>	  
	
	<div style="clear:both;"></div>
	
  </div>
  
  <div style="clear:both;"></div>

</div>

</body>
</html>
