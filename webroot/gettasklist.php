<?
mb_language('ja');
mb_internal_encoding("EUC-JP");
require_once("../../conf/global.conf");

$xml = "<?xml version=\"1.0\" encoding=\"Shift_JIS\"?>\n"
	. "<root>\n"
	. getTaskList()
	. "</root>";

	
header("Content-Type: application/xml; charset=Shift_JIS");
header("Expires: Mon, 26 Jul 1997 05:00:00 GMT");                                 
header("Last-Modified: " . gmdate("D, d M Y H:i:s") . " GMT");
header("Cache-Control: no-store, no-cache, must-revalidate");
header("Cache-Control: post-check=0, pre-check=0", false);
header("Pragma: no-cache");

print mb_convert_encoding( $xml, "Shift_JIS", "EUC-JP" );

exit;

function getTaskList () {
	
	for ( $i=1; $i<=10; $i++) {
		$xml .= "<task"
			. " id=\"".$i."\""
			. " category=\"PC版\""
			. " priority=\"Ｂ\""
			. " status=\"渡辺待ち\""
			. " mod_date=\"2006/12/08 11:11:11\""
			. ">"
			. "<title>あいうえお</title>"
			. "</task>\n";
	}
	
	return $xml;
}

?>
