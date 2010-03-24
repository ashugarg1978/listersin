//---------------------------------------
// 変数定義
//---------------------------------------
var ajax = false;
var ajax2 = false;
var htmlcode = "";
var beforevalue = "";
var searchparam = "";
var tablename = "";
var idname = "";

//---------------------------------------
// AJAXオブジェクト生成
//---------------------------------------
if ( window.XMLHttpRequest ) {
	ajax = new XMLHttpRequest();
	ajax2 = new XMLHttpRequest();
} else if ( window.ActiveXObject ) {
	try {
		ajax = new ActiveXObject("Msxml2.XMLHTTP");
		ajax2 = new ActiveXObject("Msxml2.XMLHTTP");
	} catch( e ) {
		ajax = new ActiveXObject("Microsoft.XMLHTTP");
		ajax2 = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

//---------------------------------------
//
//---------------------------------------
function getData ( url, elm, postdata ) {
	
	if ( postdata != "" ) {
		ajax.open("POST", url);
		ajax.setRequestHeader("Content-Type" , "application/x-www-form-urlencoded");
	} else {
		ajax.open("GET", url);
	}
	
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			
			htmlcode = ajax.responseText;
			document.all(elm).innerHTML = htmlcode;
			
		}
	}
	
	if ( postdata != "" ) {
		ajax.send(postdata);
	} else {
		ajax.send(null);
	}
	
}

function getData2 ( url, elm ) {
	
	ajax2.open("GET", url);
	ajax2.onreadystatechange = function() {
		if (ajax2.readyState == 4 && ajax2.status == 200) {
			
			htmlcode = ajax2.responseText;
			document.all(elm).innerHTML = htmlcode;
			
		}
	}
	ajax2.send(null);
	
}


//---------------------------------------
// フォームのフォーカス挙動
//---------------------------------------
function fenable ( elm ) {
	beforevalue = elm.value;
	elm.style.backgroundColor = "yellow";
	return;
}

function fdisable ( elm ) {

	if ( elm.value == beforevalue || elm.name.match(/^--/) ) {
		// 値が変わらなかった場合と、検索フォームの場合は処理無し
		elm.style.backgroundColor = "#ffffff";
		return;
	}
	elm.style.backgroundColor = "pink";
	
	idval = elm.name.replace( /_.+$/, "" );
	colname = elm.name.replace( /^[^_]+_/, "" );
							   
	postdata = "&tbl=" + tablename
		+ "&idname=" + idname
		+ "&idvalue=" + idval
		+ "&colname=" + colname
		+ "&colvalue=" + encodeURI(elm.value);
	
	ajax.open("POST", "update.php");
	ajax.setRequestHeader("Content-Type" , "application/x-www-form-urlencoded");
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			
			elm.value = ajax.responseText;
			elm.style.backgroundColor = "#ffffff";
			
		}
	}
	ajax.send(postdata);
	
}

function init ( tbl, idnm ) {
	
	tablename = tbl;
	idname = idnm;
	setSearchParam();

	getData("edit.php", "list1", "&tbl=" + tablename + "&limit=15&offset=0");
	getData2("edit.php?tbl=" + tablename + "&action=searchform", "search");

}

function nclk( elm, target ) {

	if ( document.all(target).style.display == "none" ) {
		
		elm.src = "o.gif";
		document.all(target).style.display = "";

	} else {
		
		elm.src = "c.gif";
		document.all(target).style.display = "none";
		
	}
}

function doSearch() {

	setSearchParam();

	getData("edit.php", "list1", "&tbl=" + tablename + "&limit=15" + searchparam);

}

function setSearchParam () {
	
	searchparam = "";

	lng = document.searchform.elements.length
	for ( var i=0; i<lng; i++ ) {

		name = document.searchform.elements[i].name;
		val  = document.searchform.elements[i].value;

		if ( name.match(/^--/) && val != "" ) {
			searchparam += "&" + name + "=" + encodeURI(val);
		}
	}
	
}
