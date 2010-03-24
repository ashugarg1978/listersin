//---------------------------------------
// 変数定義
//---------------------------------------
var ajax = false;
var htmlcode = "";

//---------------------------------------
// AJAXオブジェクト生成
//---------------------------------------
if ( window.XMLHttpRequest ) {
	ajax = new XMLHttpRequest();
	ajaxp = new XMLHttpRequest();
} else if ( window.ActiveXObject ) {
	try {
		ajax = new ActiveXObject("Msxml2.XMLHTTP");
		ajaxp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch( e ) {
		ajax = new ActiveXObject("Microsoft.XMLHTTP");
		ajaxp = new ActiveXObject("Microsoft.XMLHTTP");
	}
}


function getTaskList() {

	ajax.open( "GET", "gettasklist.php" );
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			var xml = ajax.responseXML;
		
			var xslt = new ActiveXObject("Microsoft.XMLDOM");
			xslt.async = false;
			xslt.load("tasklist.xsl");
			
			htmlcode = xml.transformNode(xslt);
			htmlcode = htmlcode.replace( /^.+?\?>/, "" );

			document.all("list1").outerHTML = htmlcode;
		}
	}
	ajax.send(null);
	
}
