<?php
    //$session->flash('auth');
    echo $form->create('Ebay', array('action' => 'login'));
    echo $form->input('username');
    echo $form->input('password');
    echo $form->end('Login');
?>
