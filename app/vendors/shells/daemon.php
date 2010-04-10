<?php
App::import('Core', 'Controller');
App::import('Controller', 'Users');

class DaemonShell extends Shell {
	
	var $uses = null;
	
	function main()
	{
		print $this->args[0]."\n";
		
		$ids = explode(',', $this->args[0]);
		
		$users = new UsersController();
		$users->constructClasses();
		$users->additems($ids);
		
		return;
	}
}

?>
