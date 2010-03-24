<?
require_once("functions.php");
require_once("define.php");

while ( list ($key, $value) = each($_POST) ) {
	$params[$key] = mb_convert_encoding(trim($value), "EUC-JP", "UTF-8");
	//$params[$key] = mb_convert_kana( trim($value), "ka" );
}

$con = getMasterConnection();

$sql = "UPDATE " . $params["tbl"]
	. " SET " . $params["colname"] . " = '" . mysql_real_escape_string($params["colvalue"]) . "'"
	. " WHERE " . $params["idname"] . " = " . $params["idvalue"] . ";";

writeLog($sql);

$result = mysql_query( $sql, $con );
if ( $result ) {
	
	mysql_query("commit");
	
	$sql = "SELECT " . $params["colname"] . " FROM " . $params["tbl"]
		. " WHERE " . $params["idname"] . " = " . $params["idvalue"];
	$rs = mysql_query( $sql, $con );
	$item = mysql_fetch_array($rs);
	
	$xml = $item[$params["colname"]];
	
} else {
	
	mysql_query("rollback");
	
}

header("Content-Type: application/xml; charset=Shift_JIS");
setExpires();

print mb_convert_encoding( $xml, "Shift_JIS", "EUC-JP" );

exit;

?>
