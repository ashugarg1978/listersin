#!/usr/local/bin/php
<?
require_once '/var/www/dev.xboo.st/webroot/task/Mail/mimeDecode.php';
require_once '/var/www/dev.xboo.st/webroot/task/define.php';
require_once '/var/www/dev.xboo.st/webroot/task/functions.php';

while ( !feof( STDIN ) ) {
	$line = trim( fgets( STDIN ) );
	$lineall .= $line."\n";
}
$input = $lineall;

$params['include_bodies'] = true;
$params['decode_bodies']  = true;
$params['decode_headers'] = true;

$decoder = new Mail_mimeDecode($input);
$structure = $decoder->decode($params);


mb_detect_order("ASCII, JIS, UTF-8, EUC-JP, SJIS");

$subject = mb_convert_encoding
	(mb_decode_mimeheader($structure->headers['subject']), "EUC-JP", mb_detect_order());

$con = getMasterConnection();

$sql = "INSERT INTO taskmaster (title) VALUES ('" . mysql_real_escape_string($subject) . "');";

$result = mysql_query( $sql, $con );
if ( $result ) {
	
	mysql_query("commit");
	writeLog("INSERT OK\n$sql");
	
} else {
	
	mysql_query("rollback");
	writeLog("INSERT NG\n$sql");
	
}

?>
