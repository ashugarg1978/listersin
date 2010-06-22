<html>
<head>
<script language="javascript">
<!--
<? foreach ($arrurl as $i => $url) { ?>
window.parent.document.getElementById('PD_PURL_<?= $id.'_'.($i+1) ?>').src = '<?= $url ?>';
<? } ?>
//-->
</script>
</head>
<body>
</body>
</html>
