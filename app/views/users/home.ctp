imp:<?
foreach ($accounts as $accountid => $row) {
	print '<a href="/users/getsellerlist/'.$row['ebayuserid'].'" target="_blank">'
		. $row['ebayuserid']. '</a> ';
}
?><br>
upd:<?
foreach ($accounts as $accountid => $row) {
	print '<a href="/users/getsellerlist/'.$accountid.'" target="_blank">'
		. $row['ebayuserid']. '</a> ';
}
?>
<a href="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc">regacc</a>

<br><br>

<div>
<input type="button" value="全て" onclick="chkall();">
<input type="button" value="リセット" onclick="unchkall();">

<input type="button" value="出品"   onclick="additems();">
<input type="button" value="コピー" onclick="copyitems();">
<input type="button" value="更新"   onclick="update();">
<input type="button" value="削除"   onclick="delete();">

</div>

<div id="paging"></div>


<table id="items" class="items">

<thead>
<tr>
	<th></th>
	<th>ID</th>
	<th>画像</th>
	<th>タイトル</th>
	<th>eBayアカウント</th>
	<th>eBay商品ID</th>
	<th>終了日</th>
	<th>開始価格</th>
</tr>
</thead>

<tr id="filter">
<td>
</td>
<td>
	<input type="text" name="itemid" size="4">
</td>
<td></td>
<td>
	<input type="text" name="title" size="30">
	<input type="text" name="offset" value="0"  size="2">
	<input type="text" name="limit"  value="30" size="2">
</td>
<td>
	<select name="accountid" onchange="filter();">
	<option value=""></option>
	<?
	foreach ($accounts as $accountid => $row) {
		echo '<option value="'.$accountid.'">'.$row['ebayuserid'].'</option>';
	}	
	?>
	</select>
</td>
<td>
	<input type="text" name="startprice" size="6">
</td>
<td></td>
<td></td>
</tr>

<tbody id="rowtemplate" class="itemrow">
<tr class="row1">
	<td><input type="checkbox" name="id[]"></td>
	<td class="id"></td>
	<td><img class="PictureDetails_PictureURL" height="20"></td>
	<td><a href="" class="Title"></a></td>
	<td class="ebayuserid"></td>
	<td><a href="" class="ItemID" target="_blank"></a></td>
	<td class="ListingDetails_EndTime"></td>
	<td class="StartPrice"></td>
</tr>
<tr class="row2">
	<td colspan="8">
	<div class="detail">
	
	<table class="detail">
	<tbody>
	<tr>
	  <td width="100">
		タイトル<br>
		<input name="Title" type="text" size="60">
	  </td>
	</tr>
	<tr>
	  <td>
		カテゴリ<br>
		<input name="PrimaryCategory_CategoryName" type="text" size="60">
	  </td>
	</tr>
	<tr>
	  <td>説明<br>
	  <iframe class="description"></iframe>
	  </td>
	</tr>
	<tr>
	  <td>画像<br>
		<img class="PictureDetails_PictureURL"><br>
		<input type="file" name="PictureDetails_PictureURL">
	  </td>
	</tr>
	<tr>
	  <td>
		開始価格<br>
		<input name="StartPrice" type="text" size="10">
	  </td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" class="edit"   value="編集する">
		<input type="button" class="copy"   value="コピーする">
		<input type="button" class="delete" value="削除する">
		<input type="button" class="update" value="更新する">
		<input type="button" class="cancel" value="キャンセル">
		</td>
	</tr>
	</tbody>
	</table>
	
	</div>
	</td>
</tr>
</tbody>
<tbody>
<tr>
	<td colspan="8" align="center" style="height:100px;">
		商品情報を読み込んでいます・・・
	</td>
</tr>
</tbody>
</table>

<div style="width=300px;" id="debug"></div>

<div id="editform">


<textarea class="description" name="description" cols="90" rows="5"></textarea>
<div class="categoryname">
category select form
</div>
</div>
