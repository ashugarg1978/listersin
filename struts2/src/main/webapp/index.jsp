<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(str2)</title>
<link rel="stylesheet" type="text/css" href="css/ebay.css">
<link rel="stylesheet" type="text/css" href="js/jwysiwyg/jquery.wysiwyg.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dump.js"></script>
<script type="text/javascript" src="js/jwysiwyg/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="js/jquery.scrollTo-min.js"></script>
<script type="text/javascript" src="js/ebay.js"></script>

</head>
<body>

<div id="container">
<div id="toolbar">
<div style="border:1px solid #999999; background-color:#dadada; margin-bottom:10px; text-align:center; font-weight:bold; border-radius:5px; -moz-border-radius:5px; padding:10px; font-size:16px; margin-right:10px;"><a href="/ebaytool">str2</a></div>

<div style="font-size:11px; margin-right:10px;">
<?
$session->flash('auth');
echo $form->create('User', array('action' => 'login'));
echo $form->input('email');
echo $form->input('password');
echo $form->end('Login');
?>
</div>



</div>

<div id="content">

msg=[<s:property value="msg"/>]

<div style="margin:10px;">
<b>Service description</b><br><br>
brabrabra....<br>
brabrabra....<br>
brabrabra....<br>
</div>

<div style="margin:10px;">
<b>New user registration</b><br><br>
<?
echo $form->create('User',array('action'=>'register'));
echo $form->input('email');
echo $form->input('password');
echo $form->end('Register');
?>
</div>

</div>
<div id="debug"></div>

</div>


</body>
</html>
