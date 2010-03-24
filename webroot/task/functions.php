<?
function writeLog ( $str ) {
	
	$handle = fopen( "/var/www/dev.xboo.st/logs/edit.log", "ab" ); // ab
	fwrite( $handle, $str . "\n");
	fclose( $handle );
	
}

function getMasterConnection() {
	
	global $MASTERHOST, $MASTERUSER, $MASTERPASSWD, $MASTERDBNAME;
	$con = null;
	
	$con = mysql_connect($MASTERHOST,$MASTERUSER,$MASTERPASSWD);
	
	if ( $con ) {
		$result = mysql_select_db($MASTERDBNAME);
		if ( $result ) {
			return $con;
		}
	}
	
	ob_clean();
}

function getPagingLink ( $con, $sql, $offset, $limit, $linkstr ) {
	
	$sql = "SELECT COUNT(*) AS cnt FROM (" . preg_replace( "/limit.+$/", "", $sql ) . ") a;";
	
	$rs = mysql_query( $sql, $con );
	$item = mysql_fetch_assoc($rs);
	$count = $item["cnt"];
	if ( $count > $limit ) {
		for ( $i=0; ($i*$limit)<$count; $i++ ) {

			$tmplinkstr = $linkstr;
			$tmpoffset = $i * $limit;
			$tmppnum = $i+1;
			
			$tmplinkstr = str_replace( "[offset]", $tmpoffset, $tmplinkstr);
			$tmplinkstr = str_replace( "[limit]", $limit, $tmplinkstr);
			$tmplinkstr = str_replace( "[page]", $i+1, $tmplinkstr);
			
			if ( $offset == ($i*$limit) ) {
				
				$result .= "<b>" . $tmplinkstr . "</b> ";
				
			} else {
				
				$result .= $tmplinkstr . " ";
				
			}
			
		}
	}

	return $result;
}

function addTag ( $str ) {
	return "[" . $str . "]";
}

function getOptionTag ( $arr, $selectedvalue ) {
	
	$str = "";
	if ( $selectedvalue == "" ) {
		$str .= "<option value=\"\"></option>\n";
	}
	foreach ( $arr as $value => $caption ) {
		$str .= "<option value=\"" . $value . "\"";
		if ( $value == $selectedvalue && $selectedvalue != "" ) {
			$str .= " selected";
		}
		$str .= ">" . htmlspecialchars($caption) . "</option>\n";
	}

	return $str;
}

function setExpires () {
	
	header("Expires: Mon, 26 Jul 1997 05:00:00 GMT");                                 
	header("Last-Modified: " . gmdate("D, d M Y H:i:s") . " GMT");
	header("Cache-Control: no-store, no-cache, must-revalidate");
	header("Cache-Control: post-check=0, pre-check=0", false);
	header("Pragma: no-cache");
	
}

?>
