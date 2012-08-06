<nobr>
<?php

$output = shell_exec("tail -n 50 /usr/local/httpd/logs/listers.in-access_log");

echo str_replace("\n", "<br/>", $output);

?>
</nobr>