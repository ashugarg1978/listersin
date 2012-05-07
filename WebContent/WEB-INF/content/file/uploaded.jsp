<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<script language="javascript">
id        = '<s:property value="%{#parameters.id}"/>';
fileindex = <s:property value="%{#parameters.fileindex}"/>;
filename  = '/itemimage/${savedfilename}';
itemrow   = $('tbody#'+id, window.parent.document);

$('img.PD_PURL_'+fileindex, itemrow).attr('src', filename);
$('input[name="mod.PictureDetails.PictureURL.'+fileindex+'"]', itemrow).val('http://ebaytool.jp'+filename);
</script>
</head>
<body style="font-size:10px;">

<nobr>
<s:property value="%{#parameters.id}"/><br/>
<s:property value="%{#parameters.fileindex}"/><br/>
${savedfilename}<br>
</nobr>

</body>
</html>
