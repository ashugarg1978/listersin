<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/js/jquery-1.6.1.min.js"></script>
<script language="javascript">
<!--

$('#PD_PURL_<s:property value="%{#parameters.id}"/>_<s:property value="%{#parameters.fileindex}"/>', window.parent.document).attr('src', '/itemimage/${savedfilename}');

//window.parent.document.getElementById('PD_PURL_<s:property value="%{#parameters.id}"/>_<s:property value="%{#parameters.fileindex}"/>').src = '/itemimage/${savedfilename}';

itemrow = $('tbody#<s:property value="%{#parameters.id}"/>', window.parent.document);
$('input[name="PictureDetails.PictureURL.<s:property value="%{#parameters.fileindex}"/>"]', itemrow).val('http://ebaytool.jp/itemimage/${savedfilename}');

$('#PD_PURL_<s:property value="%{#parameters.id}"/>_<s:property value="%{#parameters.fileindex}"/>', window.parent.document).attr('src', '/itemimage/${savedfilename}');


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
