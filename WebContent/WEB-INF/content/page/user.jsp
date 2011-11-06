<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ebaytool.jp</title>
<link rel="stylesheet" type="text/css" href="/css/ebay.css">
<link rel="stylesheet" type="text/css" href="/js/jwysiwyg/jquery.wysiwyg.css">
<script type="text/javascript" src="/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/jwysiwyg/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="/js/jquery.scrollTo-min.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>
</head>
<body>

<div id="container">

<div id="loading"><s:text name="loading"/></div>

<div id="toolbar">
<div id="logo"><a href="/">ebaytool.jp</a></div>

<div style="font-size:11px; margin-right:10px;">
<b>${user.email}</b><br>
<div style="text-align:right;">
  <a href="/page/logout"><s:text name="signout"/></a>
</div>
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

<a href="/page/addaccount"><s:text name="addnewaccount"/></a>

<div id="debugseparator"></div>

<div id="importform">

<select name="UserID">
<option value="">User ID</option>
<s:iterator value="user.userids.keySet">
  <option><s:property /></option>
</s:iterator>
</select><br>

<select name="daterange">
  <option value="Start">StartTime</option>
  <option value="End">EndTime</option>
</select>

<input type="text" name="from" size="12" value=""><br>
<input type="text" name="to"   size="12" value=""><br>
<input type="button" value="Import">

</div>

<div id="debugseparator"></div>

<table id="hiddenforms">
<tr>
  <td>offset</td>
  <td><input type="text" class="filter" name="offset" value="0" size="3"></td>
</tr>
<tr>
  <td>limit</td>
  <td><input type="text" class="filter" name="limit" value="20" size="3"></td>
</tr>
<tr>
  <td>selling</td>
  <td><input type="text" class="filter" name="selling" value="active" size="13"></td>
</tr>
<tr>
  <td>sort</td>
  <td><input type="text" class="filter" name="sort" value="ListingDetails_EndTime" size="13"></td>
</tr>
<tr>
  <td>allpages</td>
  <td><input type="checkbox" class="filter" name="allpages" value="1"></td>
</tr>
</table>

<br>

<iframe name="posttarget" src="blank.html"></iframe>

<a href="file://localhost/var/www/dev.xboo.st/app/tmp/apilogs" target="apilogs">apilogs</a>
<a href="http://sandbox.ebay.com/" target="sandbox">sandbox</a>
<br><br><br>

<a href="#" onclick="preloadcategoryfeatures('US', 31387); return false;">features</a>


<div id="msg"></div>

</div>


<div id="content">

<div id="bulkbuttons" style="float:left; margin:10px;">
<input type="button" class="checkall"     value="<s:text name="checkall"/>">
<input type="button" class="checkallpage" value="<s:text name="checkallpage"/>">
<input type="button" class="uncheckall"   value="<s:text name="uncheckall"/>">
<input type="button" class="edit"         value="<s:text name="edit"/>">
<input type="button" class="copy"         value="<s:text name="copy"/>">
<input type="button" class="delete"       value="<s:text name="delete"/>">
<input type="button" class="add"          value="<s:text name="add"/>">
<input type="button" class="relist"       value="<s:text name="relist"/>">
<input type="button" class="revise"       value="<s:text name="revise"/>">
<input type="button" class="end"          value="<s:text name="end"/>">
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
  <input type="text" class="filter" name="Title" value="" size="40">
</td>
<td>
  <select class="filter" name="UserID" onchange="filter();">
	<option value="">User ID</option>
	<s:iterator value="user.userids.keySet">
	  <option><s:property /></option>
	</s:iterator>
  </select>
</td>
<td><input type="text" class="filter" name="ItemID" size="10"></td>
<td></td>
<td></td>
<td></td>
</tr>

<tbody id="rowtemplate" class="itemrow">
<tr class="row1">
  <td><input type="checkbox" name="id"></td>
  <td><div class="titlewrap"><a href="" class="Title"></a><div class="labelwrap"></div></div></td>
  <td class="UserID"></td>
  <td><a href="" class="ItemID" target="_blank"></a></td>
  <td class="price"></td>
  <td class="EndTime"></td>
  <td align="center" valign="middle"><img class="PictureURL" height="15"></td>
</tr>
<tr class="row2"><td colspan="7"></td></tr>
</tbody>

<tbody id="rowloading">
<tr>
  <td colspan="8" align="center" style="height:200px;">
	<s:text name="LoadingItemData"/>
  </td>
</tr>
</tbody>

</table>

</div>

<div style="clear:both;"></div>



<div id="detailtemplate">
<div class="detail">
	
<ul class="tabNav">
<li class="current"><a href="#"><s:text name="Category"/></a></li>
<li><a href="#"><s:text name="Title"/></a></li>
<li><a href="#"><s:text name="Pictures"/></a></li>
<li><a href="#"><s:text name="Description"/></a></li>
<li><a href="#"><s:text name="Price"/></a></li>
<li><a href="#"><s:text name="Payment"/></a></li>
<li><a href="#"><s:text name="Shipping"/></a></li>
<li><a href="#"><s:text name="Other"/></a></li>
</ul>

<ul class="editbuttons">
<li><a href="#" class="edit">Edit</a></li>
<li><a href="#" class="copy">Copy</a></li>
<li><a href="#" class="delete">Delete</a></li>
<li><a href="#" class="add">Add</a></li>
<li><a href="#" class="relist">ReList</a></li>
<li><a href="#" class="revise">Revise</a></li>
<li><a href="#" class="end">End</a></li>
<li style="display:none;"><a href="#" class="save">Save</a></li>
<li style="display:none;"><a href="#" class="cancel">Cancel</a></li>
</ul>
 
<div class="tabContainer">

<div class="tab current">
<table class="detail">
<tbody>
<tr>
  <td><s:text name="Category"/></td>
  <td class="category">
	<select name="PrimaryCategory.CategoryID"></select>
  </td>
</tr>
<tr>
  <td><s:text name="ProductDetails"/></td>
  <td></td>
</tr>
</tbody>
</table>
</div>

<div class="tab">
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
  <td><s:text name="Condition"/></td>
  <td><select name="ConditionID"></select></td>
</tr>
<tr>
  <td><s:text name="UPC"/></td>
  <td></td>
</tr>
<tr>
  <td><s:text name="ItemSpecifics"/></td>
  <td class="ItemSpecifics"></td>
</tr>
</tbody>
</table>
</div>

<div class="tab">
  <div class="pictures">
	
	<form method="post" action="/file/upload" target="posttarget" enctype="multipart/form-data">
	<table>
	<tr>
	  <s:iterator value="{0,1,2,3,4,5}">
		<td>
		  <div class="picdiv">
			<img class="PictureDetails_PictureURL PD_PURL_<s:property />" src="/img/noimage.jpg">
		  </div>
		  <input type="file" name="<s:property />" size="5">
		</td>
	  </s:iterator>
	</tr>
	<tr>
	  <s:iterator value="{6,7,8,9,10,11}">
		<td>
		  <div class="picdiv">
			<img class="PictureDetails_PictureURL PD_PURL_<s:property />" src="/img/noimage.jpg">
		  </div>
		  <input type="file" name="<s:property />" size="5">
		</td>
	  </s:iterator>
	</tr>
	</table>
	</form>
	
	<s:iterator value="{0,1,2,3,4,5,6,7,8,9,10,11}">
	  <input type="text" name="PictureDetails.PictureURL" size="40"><br>
	</s:iterator>
  </div>
</div>

<div class="tab">
  <div class="description">
	<textarea name="Description" cols="100" rows="10"></textarea>
  </div>
</div>


<div class="tab">
<table class="detail">
<tbody>
<tr>
  <td><s:text name="StartPrice"/></td>
  <td>
	<input name="StartPrice.@currencyID" type="text" size="3">
	<input name="StartPrice.#text" type="text" size="10">
  </td>
</tr>
<tr>
  <td><s:text name="BestOfferAutoAcceptPrice"/></td>
  <td>
	<input name="ListingDetails.BestOfferAutoAcceptPrice.@currencyID" type="text" size="3">
	<input name="ListingDetails.BestOfferAutoAcceptPrice.#text" type="text" size="10">
  </td>
</tr>
<tr>
  <td><s:text name="MinimumBestOfferPrice"/></td>
  <td>
	<input name="ListingDetails.MinimumBestOfferPrice.@currencyID" type="text" size="3">
	<input name="ListingDetails.MinimumBestOfferPrice.#text" type="text" size="10">
  </td>
</tr>
<tr>
  <td><s:text name="Quantity"/></td>
  <td><input name="Quantity" type="text" size="5"></td>
</tr>
<tr>
  <td><s:text name="ListingType"/></td>
  <td>
	<select name="ListingType">
	  <option value="Chinese">Online Auction</option>  
	  <option value="FixedPriceItem">Fixed Price</option>
	  <option value="LeadGeneration">Classified Ad</option>
	</select>
  </td>
</tr>
<tr>
  <td><s:text name="ListingDuration"/></td>
  <td><select name="ListingDuration"></select></td>
</tr>
<tr>
  <td><s:text name="BuyItNowPrice"/></td>
  <td>
	<input name="BuyItNowPrice.@currencyID" type="text" size="3">
	<input name="BuyItNowPrice.#text" type="text" size="10">
  </td>
</tr>
<tr>
  <td><s:text name="BuyerGuaranteePrice"/></td>
  <td>
	<input name="BuyerGuaranteePrice.@currencyID" type="text" size="3">
	<input name="BuyerGuaranteePrice.#text" type="text" size="10">
  </td>
</tr>
</tbody>
</table>
</div>

<div class="tab">
<table class="detail">
<tbody>
<tr>
  <td><s:text name="PaymentMethods"/></td>
  <td class="paymentmethod">
  </td>
</tr>
<tr>
  <td><s:text name="AutoPay"/></td>
  <td>
	<input name="AutoPay" type="checkbox" value="true">[AutoPay]
  </td>
</tr>
</tbody>
</table>
</div>


<div class="tab">
<div class="shipping">
<table class="detail">
<tbody>
<tr>
  <td><s:text name="Shippingtype"/></td>
  <td class="shippingtype_domestic">
	<select name="ShippingDetails.ShippingType.domestic">
	  <option value="Flat">Flat: same cost to all buyers</option>
	  <option value="Calculated">Calculated: Cost varies by buyer location</option>
	  <option value="Freight">Freight: large items over 150 lbs.</option>
	  <option value="NoShipping">No shipping: Local pickup only</option>
	</select>
  </td>
</tr>
<tr>
  <td><s:text name="Packagetype"/></td>
  <td>
	<select name="<s:text name="_SDCSR"/>.ShippingPackage"></select>
	<input name="<s:text name="_SDCSR"/>.ShippingIrregular" type="checkbox">Irregular package
  </td>
</tr>
<tr>
  <td><s:text name="Dimensions"/></td>
  <td class="dimensions">
	<input  name="<s:text name="_SDCSR"/>.PackageLength.#text" type="text" size="3">
	<input  name="<s:text name="_SDCSR"/>.PackageLength.@unit" type="text" size="3">
	<select name="<s:text name="_SDCSR"/>.PackageLength.@measurementSystem">
	  <option value="English">English</option>
	  <option value="Metric">Metric</option>
	</select>
	x
	<input  name="<s:text name="_SDCSR"/>.PackageWidth.#text"  type="text" size="3">
	<input  name="<s:text name="_SDCSR"/>.PackageWidth.@unit"  type="text" size="3">
	<select name="<s:text name="_SDCSR"/>.PackageWidth.@measurementSystem">
	  <option value="English">English</option>
	  <option value="Metric">Metric</option>
	</select>
	x
	<input  name="<s:text name="_SDCSR"/>.PackageDepth.#text"  type="text" size="3">
	<input  name="<s:text name="_SDCSR"/>.PackageDepth.@unit"  type="text" size="3">
	<select name="<s:text name="_SDCSR"/>.PackageDepth.@measurementSystem">
	  <option value="English">English</option>
	  <option value="Metric">Metric</option>
	</select>
  </td>
</tr>
<tr>
  <td><s:text name="Weight"/></td>
  <td class="weight">
	<input  name="<s:text name="_SDCSR"/>.WeightMajor.#text" type="text" size="3">
	<input  name="<s:text name="_SDCSR"/>.WeightMajor.@unit" type="text" size="3">
	<select name="<s:text name="_SDCSR"/>.WeightMajor.@measurementSystem">
	  <option value="English">English</option>
	  <option value="Metric">Metric</option>
	</select>
	
	<input name="<s:text name="_SDCSR"/>.WeightMinor.#text" type="text" size="3">
	<input name="<s:text name="_SDCSR"/>.WeightMinor.@unit" type="text" size="3">
	<select name="<s:text name="_SDCSR"/>.WeightMinor.@measurementSystem">
	  <option value="English">English</option>
	  <option value="Metric">Metric</option>
	</select>
  </td>
</tr>
<tr>
  <td><s:text name="Services"/></td>
  <td>
	<s:iterator value="{0,1,2,3}" status="rowstatus">
	  <div class="ShippingService">
		<input name="<s:text name="_SDSSO"/>.<s:property />.ShippingServicePriority"
			   type="text" size="1" value="<s:property value="#rowstatus.count"/>">
		
		<select name="<s:text name="_SDSSO"/>.<s:property />.ShippingService"
				class="ShippingService"></select>
		
		<s:text name="Cost"/>
		<input name="<s:text name="_SDSSO"/>.<s:property />.ShippingServiceCost.@currencyID"
			   type="text" size="5">
		<input name="<s:text name="_SDSSO"/>.<s:property />.ShippingServiceCost.#text"
			   type="text" size="5">
		
		<s:if test="%{#rowstatus.index == 0}">
		  <input name="<s:text name="_SDSSO"/>.<s:property />.FreeShipping"
				 value="true" type="checkbox">Free shipping
		</s:if>
		
	  </div>
	</s:iterator>
  </td>
</tr>
<tr>
  <td><s:text name="Handlingtime"/></td>
  <td><select name="DispatchTimeMax"></select></td>
</tr>
<tr>
  <td colspan="2" style="text-align:left;">
	International shipping
  </td>
</tr>
<tr>
  <td><s:text name="Shippingtype"/></td>
  <td class="shippingtype_international">
	
	<select name="ShippingDetails.ShippingType.international">
	  <option value="Flat">Flat: same cost to all buyers</option>
	  <option value="Calculated">Calculated: Cost varies by buyer location</option>
	  <option value="NoShipping">No international shipping</option>
	</select>
	
  </td>
</tr>
<tr>
  <td><s:text name="Services"/></td>
  <td class="intlshippingservice">
	<s:iterator value="{0,1,2,3,4}" status="rowstatus">
	  <div class="ShippingService">
		<input name="<s:text name="_SDISSO"/>.<s:property />.ShippingServicePriority"
			   type="text" size="1" value="<s:property value="#rowstatus.count"/>">
		
		<select name="<s:text name="_SDISSO"/>.<s:property />.ShippingService"
				class="ShippingService"></select>
		<s:text name="Cost"/>
		<input name="<s:text name="_SDISSO"/>.<s:property />.ShippingServiceCost.@currencyID"
			   type="text" size="5">
		<input name="<s:text name="_SDISSO"/>.<s:property />.ShippingServiceCost.#text"
			   type="text" size="5">
		
	  </div>
	</s:iterator>
  </td>
</tr>
<tr>
  <td><s:text name="Shipto"/></td>
  <td class="shipto">
  </td>
</tr>
<tr>
  <td><s:text name="PostalCode"/></td>
  <td>
	<input type="text" name="PostalCode" size="10" />
  </td>
</tr>
<tr>
  <td><s:text name="Location"/></td>
  <td>
	<input type="text" name="Location" size="10" />
  </td>
</tr>
</tbody>
</table>
</div>
</div><!-- tab -->

<div class="tab">
<table class="detail">
<tbody>
<tr>
  <td><s:text name="Site"/></td>
  <td><select name="Site"></select></td>
</tr>
<tr>
  <td><s:text name="Country"/></td>
  <td><select name="Country"></select></td>
</tr>
<tr>
  <td><s:text name="Currency"/></td>
  <td><select name="Currency"></select></td>
</tr>
</tbody>
</table>
</div>


</div>

</div>
</div>

<div style="clear:both;"></div>

<div id="debug"></div>

</div>

</body>
</html>
