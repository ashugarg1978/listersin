<?php
 
function parseParameter() {
	$params = null;
	global $USER_SESSION_ID;

	if(is_array($_GET)){
		reset($_GET);
		while(list ($key, $value) = each($_GET)) {
			if(is_array($value)) {
				// 配列の場合は、trim しない
				$params[$key] = $value;
			} else {
				$params[$key] = mb_convert_kana( trim($value), "ka" );
			}
		}
	}

	if(is_array($_POST)){
		reset($_POST);
		while(list ($key, $value) = each($_POST)) {
			if(is_array($value)) {
				// 配列の場合は、trim しない
				$params[$key] = $value;
			} else {
				$params[$key] = mb_convert_kana( trim($value), "ka" );
//				$params[$key] = mb_convert_encoding(stripslashes($value), 'EUC-JP','SJIS');
			}
		}
	}
	
	return $params;
}
?>
