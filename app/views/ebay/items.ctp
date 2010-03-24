<a href="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc">eBayアカウントを登録する</a>

<br><br>

<table class="items">
<tr>
<th>操作</th>
<th>ID</th>
<th>Item ID</th>
<th>タイトル</th>
</tr>
<?
foreach ($items as $i => $arr) {
	$row = $arr['items'];
	echo '<tr>';
	echo '<td><a href="/ebay/submit/'.$row['itemid'].'">出品</a></td>';
	echo '<td>'.$row['itemid'].'</td>';
	echo '<td>'.$row['ebayitemid'].'</td>';
	echo '<td>'.$row['title'].'</td>';
	echo '</tr>';
}
?>
</table>
