<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script language="javascript">
<!--
window.parent.document.getElementById('PD_PURL_<s:property value="%{#parameters.id}"/>_<s:property value="%{#parameters.fileindex}"/>').src = '/itemimage/${savedfilename}';
//-->
</script>
</head>
<body style="font-size:10px;">

<nobr>
<s:property value="%{#parameters.id}"/><br>
<s:property value="%{#parameters.fileindex}"/><br>
${savedfilename}<br>
</nobr>

</body>
</html>
