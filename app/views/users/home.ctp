
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
	echo '<li><span class="more">- </span>'.$row['ebayuserid'].'</li>';
	echo '<li><a href="/users/getsellerlist/'.$row['ebayuserid'].'" target="_blank">getsellerlist</a></li>';
}
?>
</ul>
<br>


<a href="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc">add new account</a>

<iframe name="posttarget" width="130" height="100" src="/blank.html" style="display:none;"></iframe>
<div id="debug" style="background-color:#ffffcc; margin-right:10px; margin-top:10px; border:1px solid #9999aa;">
</div>
<a href="file://localhost/var/www/dev.xboo.st/app/tmp/apilogs" target="apilogs">apilogs</a>

</div>

<div id="content">

<div style="float:left; margin:10px;">
<input type="button" value="All"    onclick="chkall();">
<input type="button" value="Reset"  onclick="unchkall();">
<input type="button" value="List"   onclick="additems();">
<input type="button" value="Copy"   onclick="copyitems();">
<input type="button" value="Update" onclick="update();">
<input type="button" value="Delete" onclick="delete();">
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
	<input type="hidden" name="limit"  value="50" size="2">
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
	</td>
</tr>
</tbody>
<tbody>
<tr>
	<td colspan="8" align="center" style="height:100px;">
		Loading Item Data ...
	</td>
</tr>
</tbody>
</table>

</div>

<div style="clear:both;"></div>



<div id="detailtemplate">
<div class="detail">
	
<ul class="tabNav">
<li><a href="#">Detail</a></li>
<li><a href="#">Description</a></li>
<li><a href="#">Pictures</a></li>
</ul>
	 
<div class="tabContainer">
<div class="tab">
<table class="detail">
  <tbody>
	<tr>
	  <td>Title</td>
	  <td><input name="Title" type="text" size="60"></td>
	</tr>
	<tr>
	  <td>SubTitle</td>
	  <td><input name="SubTitle" type="text" size="60"></td>
	</tr>
	<tr>
	  <td>Category</td>
	  <td class="category"></td>
	</tr>
	<tr>
	  <td>Start Price</td>
	  <td><input name="StartPrice" type="text" size="10"></td>
	</tr>
	<tr>
	  <td>Quantity</td>
	  <td><input name="Quantity" type="text" size="5"></td>
	</tr>
	<tr>
	  <td>Listing Type</td>
	  <td>
		<select name="ListingType">
		<option value="Chinese">Online Auction</option>  
		<option value="FixedPriceItem">Fixed Price</option>
		<option value="LeadGeneration">Classified Ad</option>
		</select>
	  </td>
	</tr>
	<tr>
	  <td>Listing Duration</td>
	  <td class="duration"></td>
	</tr>
	<tr>
	  <td>Payment Methods</td>
	  <td class="paymentmethod"></td>
	</tr>
  </tbody>
</table>
</div>
<div class="tab">
  <div class="description">
	<textarea name="description" cols="100" rows="10"></textarea>
	<a class="wysiwyg">RTE</a>
  </div>
</div>
<div class="tab">
  <div class="pictures">

	<form method="post" action="/users/upload" target="posttarget" enctype="multipart/form-data">
	<table>
	<tr><?
	for ($i=1; $i<=12; $i++) {
		 echo '<td>';
		 echo '<div class="picdiv">';
		 echo '<img class="PictureDetails_PictureURL PD_PURL_'.$i.'" src="/img/noimage.jpg">';
		 echo '</div>';
		 echo '<input type="file" name="PD_PURL_'.$i.'" size="5">';
		 echo '</td>';
		 
		 if ($i == 6) echo '</tr><tr>';
	}
	?></tr>
	</table>
	</form>
	
  </div>
</div>
</div>
	 
<div style="clear:both;"></div>
<div style="text-align:center;">
	<input type="button" class="edit"   value="Edit">
	<input type="button" class="copy"   value="Copy">
	<input type="button" class="delete" value="Delete">
	<input type="button" class="update" value="Update">
	<input type="button" class="cancel" value="Cancel">
</div>

</div>
</div>
