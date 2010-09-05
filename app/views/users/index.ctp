<div id="toolbar">
<div style="border:1px solid #999999; background-color:#dadada; margin-bottom:10px; text-align:center; font-weight:bold; border-radius:5px; -moz-border-radius:5px; padding:10px; font-size:16px; margin-right:10px;"><a href="/">ebaytool.jp</a></div>

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
