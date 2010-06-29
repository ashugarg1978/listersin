<?php
App::import('Core', 'Controller');
App::import('Controller', 'Users');

class DaemonShell extends Shell {
	
	var $uses = null;
	
	function main()
	{
		$funcname = $this->args[0];
		$arg = explode(',', $this->args[1]);
		
		$users = new UsersController();
		$users->constructClasses();
		$users->$funcname($arg);
		
		return;
	}
}

?>
