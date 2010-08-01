<div id="loading"><?= __('Loading...') ?></div>

<div id="toolbar">
<div style="border:1px solid #999999; background-color:#dadada; margin-bottom:10px; text-align:center; font-weight:bold; border-radius:5px; -moz-border-radius:5px; padding:10px; font-size:16px; margin-right:10px;"><a href="/">ebaytool.jp</a></div>

<div style="font-size:11px; margin-right:10px;">
<b><?= $user['User']['email'] ?></b><br>
<div style="text-align:right;"><a href="/users/logout"><?= __('Sign out') ?></a></div>
</div>

<br>

<ul id="selling" class="accounts">
<li><a href="" class="all"      ><?= __('All Selling') ?></a></li>
<li><a href="" class="scheduled"><?= __('Scheduled')   ?></a></li>
<li><a href="" class="active"   ><?= __('Active')      ?></a></li>
<li><a href="" class="sold"     ><?= __('Sold')        ?></a></li>
<li><a href="" class="unsold"   ><?= __('Unsold')      ?></a></li>
<li><a href="" class="saved"    ><?= __('Saved')       ?></a></li>
<li><a href="" class="deleted"  ><?= __('Trash')       ?></a></li>
</ul>

<ul class="accounts">
<? foreach ($accounts as $accountid => $row) { ?>
<li>
<a href="#" class="accountaction"><?= $row['ebayuserid'] ?></a>
<ul class="accountaction">
	<li><a href=""><?= __('All Selling') ?></a></li>
	<li><a href=""><?= __('Scheduled')   ?></a></li>
	<li><a href=""><?= __('Active')      ?></a></li>
	<li><a href=""><?= __('Sold')        ?></a></li>
	<li><a href=""><?= __('Unsold')      ?></a></li>
	<li><a href=""><?= __('Saved')       ?></a></li>
	<li><a href=""><?= __('Trash')       ?></a></li>
	<li><a href="/users/getsellerlist/<?= $row['ebayuserid'] ?>" target="import">import</a></li>
</ul>
</li>
<? } ?>
</ul>

<a href="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc"><?= __('add new account') ?></a>

<iframe name="posttarget" width="130" height="100" src="/blank.html"></iframe>

<a href="file://localhost/var/www/dev.xboo.st/app/tmp/apilogs" target="apilogs">apilogs</a>
<a href="http://sandbox.ebay.com/" target="sandbox">sandbox</a>
<br><br><br>


</div>


<div id="content">

<div id="bulkbuttons" style="float:left; margin:10px;">
<input  type="button" class="checkall"     value="<?= __('Select all') ?>"
><input type="button" class="checkallpage" value="<?= __('Select all pages') ?>"
><input type="button" class="uncheckall"   value="<?= __('Clear selection') ?>">&nbsp;
<input  type="button" class="relist" value="<?= __('(Re)List') ?>"
><input type="button" class="revise" value="<?= __('Revise') ?>"
><input type="button" class="end"    value="<?= __('End') ?>">&nbsp;
<input  type="button" class="edit"   value="<?= __('Edit') ?>"
><input type="button" class="copy"   value="<?= __('Copy') ?>"
><input type="button" class="delete" value="<?= __('Delete') ?>">
</div>

<div id="paging"></div>

<div style="clear:both;"></div>

<table id="items" class="items">
<thead>
<tr>
	<th></th>
	<th align="left">ID</th>
	<th align="left"><?= __('Pict') ?></th>
	<th align="left"><?= __('Title') ?></th>
	<th align="left"><?= __('Account') ?></th>
	<th align="left"><?= __('eBay Item ID') ?></th>
	<th align="right"><?= __('Price') ?></th>
	<th align="right"><?= __('End') ?></th>
</tr>
</thead>

<tr id="filter">
<td></td>
<td><input type="text" name="id" size="4"></td>
<td></td>
<td>
	<input type="text" name="title" size="10">
	<input type="hidden" name="offset" value="0"  size="2">
	<input type="hidden" name="limit"  value="50" size="2">
	<input type="hidden" name="selling" size="10" value="active">
	<input type="text" name="sort" size="10" value="ListingDetails_EndTime">
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
<td><input type="text" name="ItemID" size="6"></td>
<td></td>
<td></td>
</tr>

<tbody id="rowtemplate" class="itemrow">
<tr class="row1">
	<td><input type="checkbox" name="id[]"></td>
	<td class="id"></td>
	<td align="center" valign="middle"><img class="PictureDetails_PictureURL" height="20"></td>
	<td><div style="min-width:100px; overflow:hidden; white-space:nowrap;"><a href="" class="Title"></a></div></td>
	<td class="ebayuserid"></td>
	<td><a href="" class="ItemID" target="_blank"></a></td>
	<td class="StartPrice"></td>
	<td class="ListingDetails_EndTime"></td>
</tr>
<tr class="row2"><td colspan="8"></td></tr>
</tbody>

<tbody id="rowloading">
<tr>
	<td colspan="8" align="center" style="height:200px;">
		<?= __('Loading Item Data ...') ?>
	</td>
</tr>
</tbody>

</table>

</div>

<div style="clear:both;"></div>



<div id="detailtemplate">
<div class="detail">
	
<ul class="tabNav">
<li class="current"><a href="#"><?= __('Detail') ?></a></li>
<li><a href="#"><?= __('Description') ?></a></li>
<li><a href="#"><?= __('Pictures') ?></a></li>
<li><a href="#"><?= __('Shipping') ?></a></li>
</ul>

<ul class="editbuttons">
<li><a href="" class="edit"  >Edit</a></li>
<li><a href="" class="copy"  >Copy</a></li>
<li><a href="" class="delete">Delete</a></li>
<li><a href="" class="relist">(Re)List</a></li>
<li><a href="" class="revise">Revise</a></li>
<li><a href="" class="end"   >End</a></li>
<li style="display:none;"><a href="" class="save">Save</a></li>
<li style="display:none;"><a href="" class="cancel">Cancel</a></li>
</ul>
 
<div class="tabContainer">
<div class="tab current">
<table class="detail">
  <tbody>
	<tr>
	  <td><?= __('Title') ?></td>
	  <td><input name="Title" type="text" size="60"></td>
	</tr>
	<tr>
	  <td><?= __('SubTitle') ?></td>
	  <td><input name="SubTitle" type="text" size="60"></td>
	</tr>
	<tr>
	  <td><?= __('Site') ?></td>
	  <td>
		<select name="Site"><?
		foreach ($hash as $sitestr => $tmp) {
			echo '<option value="'.$sitestr.'">'.$sitestr.'</option>';
	 	}
	 	?></select>
	  </td>
	</tr>
	<tr>
	  <td><?= __('Category') ?></td>
	  <td class="category">
		<select class="category"></select>
	  </td>
	</tr>
	<tr>
	  <td><?= __('Start Price') ?></td>
	  <td><input name="StartPrice" type="text" size="10"></td>
	</tr>
	<tr>
	  <td><?= __('Quantity') ?></td>
	  <td><input name="Quantity" type="text" size="5"></td>
	</tr>
	<tr>
	  <td><?= __('Listing Type') ?></td>
	  <td>
		<select name="ListingType">
		<option value="Chinese">Online Auction</option>  
		<option value="FixedPriceItem">Fixed Price</option>
		<option value="LeadGeneration">Classified Ad</option>
		</select>
	  </td>
	</tr>
	<tr>
	  <td><?= __('Listing Duration') ?></td>
	  <td class="duration"></td>
	</tr>
	<tr>
	  <td><?= __('Payment Methods') ?></td>
	  <td class="paymentmethod"></td>
	</tr>
	<tr>
	  <td><?= __('schedule') ?></td>
	  <td><input name="schedule" type="" size="15"></td>
	</tr>
  </tbody>
</table>
</div>
<div class="tab">
	<div class="description">
		<textarea name="description" cols="100" rows="10"></textarea>
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
<div class="tab">
  <div class="shipping">
	<table class="detail">
	  <tbody>
		<tr>
		  <td><?= __('Shipping type') ?></td>
		  <td class="shippingtype">
	  		
			<select name="ShippingType">
			  <option value="Flat">Flat: same cost to all buyers</option>
			  <option value="Calculated">Calculated: Cost varies by buyer location</option>
			  <option value="Freight">Freight: large items over 150 lbs.</option>
			  <option value="NoShipping">No shipping: Local pickup only</option>
			</select>
			
		  </td>
		</tr>
		<tr>
		  <td><?= __('Package type') ?></td>
		  <td class="shippingpackage">
		  	<select name="ShippingPackage"></select>
			<input name="" type="checkbox">Irregular package
		  </td>
		</tr>
		<tr>
		  <td><?= __('Dimensions') ?></td>
		  <td class="dimensions">
			<input name="" type="text" size="3">in. x
			<input name="" type="text" size="3">in. x
			<input name="" type="text" size="3">in.
		  </td>
		</tr>
		<tr>
		  <td><?= __('Weight') ?></td>
		  <td class="weight">
			
		  </td>
		</tr>
		<tr>
		  <td><?= __('Services') ?></td>
		  <td class="shippingservice">
			
			<div class="ShippingService">
			<select name="ShippingService[1]" class="ShippingService"></select>
			<b><?= __('Cost') ?></b>
			<input name="ShippingCost[1]" type="text" size="5">
			<input name="ShippingFree" value="1" type="checkbox">Free shipping
			</div>
			
			<div class="ShippingService">
			<select name="ShippingService[2]" class="ShippingService"></select>
			<b><?= __('Cost') ?></b>
			<input name="ShippingCost[2]" type="text" size="5">
			<a href="" class="ShippingService2">Remove service</a>
			</div>
			
			<div class="ShippingService">
			<select name="ShippingService[2]" class="ShippingService"></select>
			<b><?= __('Cost') ?></b>
			<input name="ShippingCost[2]" type="text" size="5">
			<a href="" class="ShippingService3">Remove service</a>
			</div>
			
		  </td>
		</tr>
		<tr>
		  <td><?= __('Handling time') ?></td>
		  
		  </td>
		</tr>
	  </tbody>
	</table>
  </div>
</div>
</div>

</div>
</div>

<? // todo: check script tag trends, no need <!-- --> ??? ?>
<script>
var hash;
hash = <?= json_encode($hash) ?>;
</script>

<div id="debug"></div>
