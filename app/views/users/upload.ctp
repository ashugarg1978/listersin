<html>
<head>
<script language="javascript">
<!--
<? foreach ($arrurl as $i => $url) { ?>
window.parent.document.getElementById('PD_PURL_<?= $id.'_'.$i ?>').src = '<?= $url ?>';
<? } ?>
//-->
</script>
</head>
<body>
<pre><?= print_r($arrurl,1) ?></pre>
</body>
</html>
