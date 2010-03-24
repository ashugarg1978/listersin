<?
require_once("ParseParameter.php");
require_once("functions.php");
require_once("define.php");

$params = parseParameter();

// 検索フォームのみの出力
if ( $params["action"] == "searchform" ) {
	
	$formhtml = file_get_contents( $params["tbl"] . ".tmpl" );
	$formhtml = str_replace("[disabled]", "", $formhtml);
	$formhtml = str_replace("[rowid]", "--", $formhtml);
	foreach ( $select as $name => $val ) {
		$formhtml = str_replace("[".$name."]", getOptionTag($select[$name], ""), $formhtml);
	}
	$formhtml = preg_replace("/\[[^\]]+\]/", "", $formhtml);

	setExpires();
	
	print '<table cellspacing="0" cellpadding="3" class="bottom-left-right" bgcolor="#f6f6f6">';
	print mb_convert_encoding($formhtml, "UTF-8", "EUC-JP");
	print '</table>';
	
	exit;
	
}



$formhtml = file_get_contents( $params["tbl"] . ".tmpl" );
$formhtml = str_replace("[disabled]", "disabled", $formhtml);

$con = getMasterConnection();

if ( $params["limit"] == "" ) {
	$params["limit"] = 10;
}
if ( $params["offset"] == "" ) {
	$params["offset"] = 0;
}

// 検索条件クエリの組み立て
$tmparr = array();
foreach ( $params as $name => $val ) {
	if ( preg_match("/^--_/", $name) ) {
		
		$name = str_replace("--_", "", $name);
		if ( preg_match("/^[\d]+$/", $val) ) {
			array_push( $tmparr, $name." = '".$val."'");
		} else {
			array_push( $tmparr, $name." LIKE '%".$val."%'");
		}
	}
}
if ( count($tmparr) > 0 ) {
	$searchsql = " WHERE " . implode(" AND ", $tmparr);
}

$sql = str_replace("[searchsql]", $searchsql, $sqldef[$params["tbl"]])
	. " limit " . $params["limit"] . " offset " . $params["offset"] . ";";

$paging = getPagingLink
	( $con, $sql, $params["offset"], $params["limit"],
	  "<a href=\"#\" onclick=\"getData"
	  . "('edit.php','list1','&tbl=".$params["tbl"]."&limit=[limit]&offset=[offset]'+searchparam);"
	  . "return false;\">[page]</a>");

$rs = mysql_query( $sql, $con );
$count = mysql_num_rows( $rs );

for ( $i=0; $i<$count; $i++ ) {
	$item = mysql_fetch_assoc($rs);
	
	foreach ( $item as $name => $val ) {
		if ( isset( $select[$name] ) ) {
			$item[$name] = getOptionTag( $select[$name], $item[$name] );
		}
	}
	
	$html .= str_replace
		( array_map("addTag", array_keys($item)), array_values($item), $formhtml );
}

setExpires();

print mb_convert_encoding
	('<font face="verdana" size="2">ページ選択：' . $paging . '</font><br>', "UTF-8", "EUC-JP");
print '<table cellspacing="0" cellpadding="3" class="bottom-left-right" bgcolor="#f6f6f6">';
print mb_convert_encoding($html, "UTF-8", "EUC-JP");
print '</table>';
print mb_convert_encoding
	('<font face="verdana" size="2">ページ選択：' . $paging . '</font><br>', "UTF-8", "EUC-JP");

exit;

?>
