
<? if (isset($user['User']['email'])) { ?>

<a href="/users/logout">logout</a>

<? } else { ?>

<div style="border:1px solid gray; padding:5px; float:left;">
<?
$session->flash('auth');
echo $form->create('User', array('action' => 'login'));
echo $form->input('email');
echo $form->input('password');
echo $form->end('Login');
?>
</div>


<div style="border:1px solid gray; padding:5px; float:left;">
<?
echo $form->create('User',array('action'=>'register'));
echo $form->input('email');
echo $form->input('password');
echo $form->end('Register');
?>
</div>

<? } ?>
