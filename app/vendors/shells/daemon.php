<?php
App::import('Core', 'Controller');
App::import('Controller', 'Api');

class DaemonShell extends Shell {
	
	var $uses = null;
	
	function main()
	{
		$funcname = $this->args[0];
		error_log('daemon.php : funcname = '.$funcname);
		
		$arg = null;
		if (isset($this->args[1])) $arg = $this->args[1];
		
		$api = new ApiController();
		$api->constructClasses();
		$api->$funcname($arg);
		
		return;
	}
}

?>
