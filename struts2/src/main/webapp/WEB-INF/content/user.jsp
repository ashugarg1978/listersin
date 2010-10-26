<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ebaytool.jp</title>
<link rel="stylesheet" type="text/css" href="/css/ebay.css">
<link rel="stylesheet" type="text/css" href="/js/jwysiwyg/jquery.wysiwyg.css">
<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/jwysiwyg/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="/js/jquery.scrollTo-min.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>

</head>
<body>

${json}

<div id="container">

<div id="loading"><s:text name="loading"/></div>

<div id="toolbar">
<div style="border:1px solid #999999; background-color:#dadada; margin-bottom:10px; text-align:center; font-weight:bold; border-radius:5px; -moz-border-radius:5px; padding:10px; font-size:16px; margin-right:10px;"><a href="/">ebaytool.jp</a></div>

<div style="font-size:11px; margin-right:10px;">
<b>${user.email}</b><br>
<div style="text-align:right;"><a href="/logout"><s:text name="signout"/></a></div>
</div>

<br>

<ul class="accounts">
<li class="allitems">
<a href="" class="allitems"><s:text name="allitems"/></a>
</li>
<li>
<ul class="accountaction">
<li><img src="/icon/02/10/37.png"> <a href="" class="scheduled"><s:text name="scheduled"/></a></li>
<li><img src="/icon/04/10/02.png"> <a href="" class="active"   ><s:text name="active"   /></a></li>
<li><img src="/icon/02/10/50.png"> <a href="" class="sold"     ><s:text name="sold"     /></a></li>
<li><img src="/icon/04/10/10.png"> <a href="" class="unsold"   ><s:text name="unsold"   /></a></li>
<li><img src="/icon/04/10/10.png"> <a href="" class="saved"    ><s:text name="saved"    /></a></li>
<li><img src="/icon/04/10/09.png"> <a href="" class="trash"    ><s:text name="trash"    /></a></li>
</ul>
</li>
</ul>

<a href="https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame=Yoshihiro_Watan-Yoshihir-1b29-4-nstdpc"><s:text name="add new account"/></a>

<br><br>
post target<br>
<iframe name="posttarget" width="130" height="100" src="blank.html"
style="border:1px solid gray;"></iframe>

<a href="file://localhost/var/www/dev.xboo.st/app/tmp/apilogs" target="apilogs">apilogs</a>
<a href="http://sandbox.ebay.com/" target="sandbox">sandbox</a>
<br><br><br>

<div id="msg"></div>

</div>


<div id="content">

<div id="bulkbuttons" style="float:left; margin:10px;">
<input  type="button" class="checkall"     value="<s:text name="checkall"/>"
><input type="button" class="checkallpage" value="<s:text name="checkallpage"/>"
><input type="button" class="uncheckall"   value="<s:text name="uncheckall"/>">&nbsp;
<input  type="button" class="edit"         value="<s:text name="edit"/>"
><input type="button" class="copy"         value="<s:text name="copy"/>"
><input type="button" class="delete"       value="<s:text name="delete"/>">&nbsp;
<input  type="button" class="relist"       value="<s:text name="relist"/>"
><input type="button" class="revise"       value="<s:text name="revise"/>"
><input type="button" class="end"          value="<s:text name="end"/>">
</div>

<div id="paging"></div>

<div style="clear:both;"></div>

<table id="items" class="items">
<thead>
<tr>
	<th></th>
	<th align="left"><s:text name="title"/></th>
	<th align="left"><s:text name="account"/></th>
	<th align="left"><s:text name="itemid"/></th>
	<th align="right"><s:text name="now"/></th>
	<th align="right"><s:text name="end"/></th>
	<th align="left"><s:text name="pict"/></th>
</tr>
</thead>

<tr id="filter">
<td></td>
<td>
	<input type="text" name="Title" size="10">
	<input type="text" name="offset"  value="0"  size="2">
	<input type="text" name="limit"   value="20" size="2">
	<input type="text" name="selling" value="active" size="10" >
	<input type="text" name="sort"    value="ListingDetails_EndTime" size="10" >
</td>
<td>
	<select name="UserID" onchange="filter();">
	<option value="">User ID</option>
	</select>
</td>
<td><input type="text" name="ItemID" size="6"></td>
<td></td>
<td></td>
<td></td>
</tr>

<tbody id="rowtemplate" class="itemrow">
<tr class="row1">
	<td><input type="checkbox" name="id[]"></td>
	<td><div style="min-width:100px; overflow:hidden; white-space:nowrap;"><a href="" class="Title"></a></div></td>
	<td class="UserID"></td>
	<td><a href="" class="ItemID" target="_blank"></a></td>
	<td class="price"></td>
	<td class="EndTime"></td>
	<td align="center" valign="middle"><img class="PictureURL" height="20"></td>
</tr>
<tr class="row2"><td colspan="7"></td></tr>
</tbody>

<tbody id="rowloading">
<tr>
	<td colspan="8" align="center" style="height:200px;">
		<s:text name="Loading Item Data ..."/>
	</td>
</tr>
</tbody>

</table>

</div>

<div style="clear:both;"></div>



<div id="detailtemplate">
<div class="detail">
	
<ul class="tabNav">
<li class="current"><a href="#"><s:text name="detail"/></a></li>
<li><a href="#"><s:text name="description"/></a></li>
<li><a href="#"><s:text name="Pictures"/></a></li>
<li><a href="#"><s:text name="Shipping"/></a></li>
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
	  <td><s:text name="title"/></td>
	  <td><input name="Title" type="text" size="60"></td>
	</tr>
	<tr>
	  <td><s:text name="subtitle"/></td>
	  <td><input name="SubTitle" type="text" size="60"></td>
	</tr>
	<tr>
	  <td><s:text name="Site"/></td>
	  <td>
		<select name="Site"></select>
	  </td>
	</tr>
	<tr>
	  <td><s:text name="Category"/></td>
	  <td class="category">
		<select class="category"></select>
	  </td>
	</tr>
	<tr>
	  <td><s:text name="Start Price"/></td>
	  <td><input name="StartPrice" type="text" size="10"></td>
	</tr>
	<tr>
	  <td><s:text name="Quantity"/></td>
	  <td><input name="Quantity" type="text" size="5"></td>
	</tr>
	<tr>
	  <td><s:text name="Listing Type"/></td>
	  <td>
		<select name="ListingType">
		<option value="Chinese">Online Auction</option>  
		<option value="FixedPriceItem">Fixed Price</option>
		<option value="LeadGeneration">Classified Ad</option>
		</select>
	  </td>
	</tr>
	<tr>
	  <td><s:text name="Listing Duration"/></td>
	  <td class="duration"></td>
	</tr>
	<tr>
	  <td><s:text name="Payment Methods"/></td>
	  <td class="paymentmethod"></td>
	</tr>
	<tr>
	  <td><s:text name="schedule"/></td>
	  <td><input name="schedule" type="" size="15"></td>
	</tr>
  </tbody>
</table>
</div>
<div class="tab">
	<div class="description">
		<textarea name="Description" cols="100" rows="10"></textarea>
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
	<?
	for ($i=1; $i<=12; $i++) {
		 echo '<input type="text" name="PictureDetails[PictureURL]['.$i.']" size="40"><br>';
	}
	?>
  </div>
</div>
<div class="tab">
  <div class="shipping">
	<table class="detail">
	  <tbody>
		<tr>
		  <td><s:text name="Shipping type"/></td>
		  <td class="shippingtype_domestic">
	 
			<select name="ShippingDetails[ShippingType][domestic]">
			  <option value="Flat">Flat: same cost to all buyers</option>
			  <option value="Calculated">Calculated: Cost varies by buyer location</option>
			  <option value="Freight">Freight: large items over 150 lbs.</option>
			  <option value="NoShipping">No shipping: Local pickup only</option>
			</select>
			
		  </td>
		</tr>
		<tr>
		  <td><s:text name="Package type"/></td>
		  <td class="shippingpackage">
		  	<select name="ShippingPackage"></select>
			<input name="" type="checkbox">Irregular package
		  </td>
		</tr>
		<tr>
		  <td><s:text name="Dimensions"/></td>
		  <td class="dimensions">
			<input name="" type="text" size="3">in. x
			<input name="" type="text" size="3">in. x
			<input name="" type="text" size="3">in.
		  </td>
		</tr>
		<tr>
		  <td><s:text name="Weight"/></td>
		  <td class="weight">
			
		  </td>
		</tr>
		<tr>
		  <td><s:text name="Services"/></td>
		  <td class="shippingservice">
			
			<div class="ShippingService">
			<select name="ShippingService[1]" class="ShippingService"></select>
			<b><s:text name="Cost"/></b>
			<input name="ShippingCost[1]" type="text" size="5">
			<input name="ShippingFree" value="1" type="checkbox">Free shipping
			</div>
			
			<div class="ShippingService">
			<select name="ShippingService[2]" class="ShippingService"></select>
			<b><s:text name="Cost"/></b>
			<input name="ShippingCost[2]" type="text" size="5">
			<a href="" class="ShippingService2">Remove service</a>
			</div>
			
			<div class="ShippingService">
			<select name="ShippingService[2]" class="ShippingService"></select>
			<b><s:text name="Cost"/></b>
			<input name="ShippingCost[2]" type="text" size="5">
			<a href="" class="ShippingService3">Remove service</a>
			</div>
			
		  </td>
		</tr>
		<tr>
		  <td><s:text name="Handling time"/></td>
		  
		  </td>
		</tr>
		<tr>
		  <td colspan="2" style="text-align:left;">
			International shipping
		  </td>
		</tr>
		<tr>
		  <td><s:text name="Shipping type"/></td>
		  <td class="shippingtype_international">
		  	
			<select name="ShippingDetails_ShippingType[international]">
			  <option value="Flat">Flat: same cost to all buyers</option>
			  <option value="Calculated">Calculated: Cost varies by buyer location</option>
			  <option value="NoShipping">No international shipping</option>
			</select>
			
		  </td>
		</tr>
		<tr>
		  <td><s:text name="Services"/></td>
		  <td class="intlshippingservice">
			
			
		  </td>
		</tr>
	  </tbody>
	</table>
  </div>
</div>
</div>

</div>
</div>

<div id="debug"></div>

</div>

</body>
</html>
