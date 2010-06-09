

<div id="toolbar">
<div style="border:1px solid #999999; background-color:#dadada; margin-bottom:10px; text-align:center; font-weight:bold; border-radius:5px; -moz-border-radius:5px; padding:10px; font-size:16px; margin-right:10px;"><a href="/">ebaytool.jp</a></div>

<div style="font-size:11px; margin-right:10px;">
<b><?= $user['User']['email'] ?></b><br>
<div style="text-align:right;"><a href="/users/logout"><?= __('Sign out') ?></a></div>
</div>

<br>

<ul id="selling" class="accounts">
<li><a href="">Scheduled</a></li>
<li><a href="">Active</a></li>
<li><a href="">Sold</a></li>
<li><a href="">Unsold</a></li>
<li><a href="">Other</a></li>
</ul>

<br>

eBay Accounts<br>
<ul class="accounts">
<?
foreach ($accounts as $accountid => $row) {
	echo '<li><span class="more">▼</span>'.$row['ebayuserid'].'</li>';
}
?>
</ul>
<br>


<a href="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc">add new account</a>

<div id="debug" style="background-color:#ffffcc; margin-right:10px; margin-top:10px; border:1px solid #9999aa;"></div>

</div>

<div id="content">

<div style="float:left; margin:10px;">
<input type="button" value="全て"     onclick="chkall();">
<input type="button" value="リセット" onclick="unchkall();">
<input type="button" value="出品"   onclick="additems();">
<input type="button" value="コピー" onclick="copyitems();">
<input type="button" value="更新"   onclick="update();">
<input type="button" value="削除"   onclick="delete();">
</div>

<div id="paging"></div>

<div style="clear:both;"></div>

<table id="items" class="items">
<thead>
<tr>
	<th></th>
	<th>ID</th>
	<th><?= __('Pict') ?></th>
	<th><?= __('Title') ?></th>
	<th><?= __('Account') ?></th>
	<th><?= __('eBay Item ID') ?></th>
	<th align="right"><?= __('Price') ?></th>
	<th align="right"><?= __('End') ?></th>
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
	<input type="text" name="title" size="10">
	<input type="hidden" name="offset" value="0"  size="2">
	<input type="hidden" name="limit"  value="30" size="2">
	<input type="hidden" name="selling" size="10" value="active">
</td>
<td>
	<input type="accountid" name="title" size="10">
<? if (false) { ?>
	<select name="accountid" onchange="filter();">
	<option value=""></option>
	<?
	foreach ($accounts as $accountid => $row) {
		echo '<option value="'.$accountid.'">'.$row['ebayuserid'].'</option>';
	}	
	?>
	</select>
<? } ?>
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
	<td><div style="min-width:100px; overflow:hidden; white-space:nowrap;"><a href="" class="Title"></a></div></td>
	<td class="ebayuserid"></td>
	<td><a href="" class="ItemID" target="_blank"></a></td>
	<td class="StartPrice"></td>
	<td class="ListingDetails_EndTime"></td>
</tr>
<tr class="row2">
	<td colspan="8">
	<div class="detail">
	
	<ul class="tabNav">
		<li class="current"><a href="#">Detail</a></li>
		<li><a href="#">Description</a></li>
		<li><a href="#">Pictures</a></li>
	</ul>
	 
	<div class="tabContainer">
		<div class="tab current">

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
	  <td>
		開始価格<br>
		<input name="StartPrice" type="text" size="10">
	  </td>
	</tr>
	<tr>
	  <td>
		ListingDuration<br>
		<input name="ListingDuration" type="text" size="10">
	  </td>
	</tr>
	</table>

		</div>
		<div class="tab">
			<textarea name="description" cols="100" rows="10"></textarea>
			<a class="wysiwyg">RTE</a>
		</div>
		<div class="tab">
			<img class="PictureDetails_PictureURL"><br>
			<input type="file" name="PictureDetails_PictureURL">
		</div>
	</div>
	 
	<table class="detail">
	<tbody>
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
		No Item Data Found.
	</td>
</tr>
</tbody>
</table>

</div>

<div style="clear:both;"></div>