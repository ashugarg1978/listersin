<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>...</title>
<meta name="description" content="dev">
<meta name="keywords" content="dev">
<link href="/css/ebay.css" rel="stylesheet" type="text/css">
<link href="/css/rndbtn/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery-1.4.1.js"></script>
<script type="text/javascript" src="/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="/js/jquery.scrollTo-min.js"></script>
<script type="text/javascript" src="/js/ajaxupload.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>
</head>
<body onload="bindevents();">

<div id="header">
<div id="header_inside">
<div style="background-color:#adadad; color:white; float:left; padding:3px 20px 3px 20px; font-weight:bold;">LOGO</div>
<? if (isset($user['User']['email'])) { ?>
<a href="/users/home">home</a>
<a href="/users/logout">ログアウト</a>
[<?= $user['User']['email'] ?>]<br>
<? } else { ?>
<a href="/users/login">ログイン</a>
<? } ?>
</div>
</div>

<div id="content">
<?php echo $content_for_layout ?>
</div>

<? if (false) { ?>
<br><br><br><br>
<div class="debug">
<pre><? print_r($_SESSION); ?></pre>
<pre><? print_r($_SERVER); ?></pre>
</div>
<? } ?>

<div id="footer">
<div id="footer_inside">
ebaytool.jp
</div>
</div>

</body>
</html>
