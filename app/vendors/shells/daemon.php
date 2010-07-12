<?php
App::import('Core', 'Controller');
App::import('Controller', 'Api');

class DaemonShell extends Shell {
	
	function main()
	{
		if (empty($this->args[0])) return;
		
		$funcname = $this->args[0];
		
		$arg = null;
		if (isset($this->args[1])) $arg = $this->args[1];
		
		error_log('daemon.php : '.$funcname.' ['.$arg.']');
		
		$api = new ApiController();
		$api->constructClasses();
		$api->$funcname($arg);
		
		return;
	}
}

?>
