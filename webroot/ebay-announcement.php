<?php
$xml = simplexml_load_file('/var/www/listers.in/data/ebay.xml');
?><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eBay Announcement - ListersIn</title>
<link rel="stylesheet" type="text/css" href="/css/index.css">
<link rel="shortcut icon" href="/img/favicon.png">
</head>
<body>

<div id="container">
  
  <div id="header" class="clearfix">
	  <div id="logo">
	    <a href="/"><img src="/img/logo.png" alt="ListersIn" /></a>
	  </div>
  </div>
  
  <div id="content" class="clearfix">
		
	  <div id="leftpane">
	    <h2>eBay Annoucement</h2>
      
      <div style="margin:10px 0;">
        Following information is copied from RSS feed of
        <a href="http://announcements.ebay.com/" target="_blank">eBay Announcement Page</a>.
        <br/>
        eBay sellers should check this information.
      </div>
      
	    <ul class="blog">
				<?php foreach ($xml->channel->item as $item) { ?>
		      <li>
			      <a href="<?php echo $item->link; ?>" target="_blank">
							<?php echo $item->title; ?>
						</a>
						<div style="text-align:right; color:#aaa;">
							<?php echo $item->pubDate; ?>
						</div>
						<p><?php echo $item->content; ?></p>
		      </li>
				<?php } ?>
	    </ul>
		</div>
		
	  <div id="rightpane">
		</div>
		
	</div>
	
	<div id="footer">
		ListersIn - eBay Listing Software
	</div>
	
</div><!-- container -->

</body>
</html>
