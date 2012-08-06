<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script language="javascript">
	
var id = '<s:property value="%{#parameters.id}"/>';

var files = new Array();
<s:iterator value="savedfilename">
files.push('<s:property />');
</s:iterator>

window.parent.addimage('<s:property value="%{#parameters.id}"/>', files);

</script>
</head>
<body>
</body>
</html>
