<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ebaytool.jp</title>
<link rel="stylesheet" type="text/css" href="/css/ebay.css">
<link rel="stylesheet" type="text/css" href="/js/jwysiwyg/jquery.wysiwyg.css">
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/jwysiwyg/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="/js/jquery.scrollTo-min.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>
</head>
<body>

<div id="container">
  
  <div id="loading"><s:text name="loading"/></div>
  
  <div id="header">
	<div id="logo"><a href="/">Sandbox</a></div>
	
	<div id="useremail">
	  <span style="font-weight:bold;">${user.email}</span>
	  <a href="/page/logout"><s:text name="signout"/></a>
	</div>
	
	<div id="bulkbuttons">
	  <button class="checkall btnleft"><s:text name="checkall"/></button
	  ><button class="checkallpage btncenter"><s:text name="checkallpage"/></button
	  ><button class="uncheckall btnright"><s:text name="uncheckall"/></button>
	  
	  <button class="edit btnleft"><s:text name="edit"/></button
	  ><button class="copy btncenter"><s:text name="copy"/></button
	  ><button class="delete btnright"><s:text name="delete"/></button>
	  
	  <button class="add btnleft"><s:text name="add"/></button
	  ><button class="relist btncenter"><s:text name="relist"/></button
	  ><button class="revise btncenter"><s:text name="revise"/></button
	  ><button class="end btnright"><s:text name="end"/></button>
	  <div style="clear:both;"></div>
	</div>
	
	<div id="search">
	  <input type="text" class="filter" name="Title" value="" size="20">
	</div>
	
	<div style="float:right;">
	  <a href="#" id="toggledebug">DEBUG</a>
	</div>
  </div>

<div id="toolbar">
  
  <ul class="accounts">
	<li class="allitems"><s:text name="allitems"/></li>
	<li>
	  <ul class="accountaction">
		<li class="scheduled"><img src="/icon/02/10/37.png"><s:text name="scheduled"/></li>
		<li class="active"   ><img src="/icon/04/10/02.png"><s:text name="active"   /></li>
		<li class="sold"     ><img src="/icon/02/10/50.png"><s:text name="sold"     /></li>
		<li class="unsold"   ><img src="/icon/04/10/10.png"><s:text name="unsold"   /></li>
		<li class="saved"    ><img src="/icon/04/10/10.png"><s:text name="saved"    /></li>
		<li class="trash"    ><img src="/icon/04/10/09.png"><s:text name="trash"    /></li>
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
	  <td>
		<input type="text" class="filter" name="sort" value="ListingDetails_EndTime" size="13">
	  </td>
	</tr>
	<tr>
	  <td>allpages</td>
	  <td><input type="checkbox" class="filter" name="allpages" value="1"></td>
	</tr>
	<tr>
	  <td>user id</td>
	  <td><input type="text" class="filter" name="UserID"></td>
	</tr>
  </table>
  
  <br>
  
  <iframe name="posttarget" src="/blank.html"></iframe>
  
  <div id="msg"></div>
  
</div>

<div id="content">
  
  <table id="items" class="items">
	<tbody id="rowtemplate" class="itemrow">
	  <tr class="row1">
		<td>
		  <input type="checkbox" name="id">
		</td>
		<td>
		  <img class="status" src="/icon/04/10/10.png"/>
		</td>
		<td class="Title">
		  <div class="labelwrap"></div>
		</td>
		<td class="UserID"></td>
		<td>
		  <a href="" class="ItemID" target="_blank"></a>
		</td>
		<td class="price"></td>
		<td class="EndTime"></td>
		<td align="center" valign="middle">
		  <img class="PictureURL" height="40">
		</td>
	  </tr>
	  <tr class="row2">
		<td colspan="8">
		</td>
	  </tr>
	</tbody>
	
	<tbody id="rowloading">
	  <tr>
		<td colspan="8" align="center" style="height:200px;">
		  <s:text name="LoadingItemData"/>
		</td>
	  </tr>
	</tbody>
	
  </table>
  
  <div id="debug"></div>
  
</div>

<div style="clear:both;"></div>


<div id="detailtemplate">
  <div class="detail">
	
	<ul class="tabNav">
	  <li class="current"><s:text name="CategoryAndTitle"/></li>
	  <li><s:text name="Pictures"/></li>
	  <li><s:text name="Description"/></li>
	  <li><s:text name="Price"/></li>
	  <li><s:text name="Payment"/></li>
	  <li><s:text name="Shipping"/></li>
	  <li><s:text name="Other"/></li>
	</ul>
	
	<div class="editbuttons">
	  <button class="edit btnleft"><s:text name="edit"/></button
	  ><button class="copy btncenter"><s:text name="copy"/></button
	  ><button class="delete btnright"><s:text name="delete"/></button>
	  
	  <button class="add btnleft"><s:text name="add"/></button
	  ><button class="relist btncenter"><s:text name="relist"/></button
	  ><button class="revise btncenter"><s:text name="revise"/></button
	  ><button class="end btnright"><s:text name="end"/></button>
	  
	  <button class="save btnleft" style="display:none;"><s:text name="save"/></button
	  ><button class="cancel btncenter" style="display:none;"><s:text name="cancel"/>
		
	</div>
	
	<div class="tabContainer">

	  <div class="tab current">
		<table class="detail">
		  <tbody>
			<tr>
			  <td><s:text name="Category"/></td>
			  <td>
				<select class="category" name="PrimaryCategory.CategoryID"></select>
			  </td>
			</tr>
			<tr>
			  <td><s:text name="ProductDetails"/></td>
			  <td>
				
				<div class="productsearchform">
				  
				  <span class="CharacteristicsSetsName"></span>
				  <input name="ProductSearch.QueryKeywords" type="text" size="30" class="remove"/>
				  <input name="ProductSearch.CharacteristicSetIDs.ID" type="hidden" class="remove"/>
				  <button class="GetProductSearchResults">Search</button>
				  
				  <input name="ProductListingDetails.ProductID" type="hidden"/>
				  
				  <br/>
				  
				  <input type="checkbox" value="true"
						 name="ProductListingDetails.IncludePrefilledItemInformation"/>
				  Include the following product information in your listing
				  
				  <br/>
				  
				  <input type="checkbox" value="true"
						 name="ProductListingDetails.IncludeStockPhotoURL"/>
				  Include Stock Photo
				  
				  <br/>
				  
				  <input type="checkbox" value="true"
						 name="ProductListingDetails.UseStockPhotoURLAsGallery"/>
				  Use Stock Photo As Gallery
				  
				  <br/>
				  
				  <div class="foundproducts">
					<div class="producttemplate">
					  <div class="productimage">
						<img src=""/>
					  </div>
					  <div class="producttext"></div>
					  <div class="productid"></div>
					  <div style="clear:both;"></div>
					</div>
				  </div>
				  
				</div>
			  </td>
			</tr>
			<!--
				todo: "List multiple variations of this item in one listing"
			  -->
			<tr>
			  <td><s:text name="title"/></td>
			  <td><input name="Title" type="text" size="60"/></td>
			</tr>
			<tr>
			  <td><s:text name="subtitle"/></td>
			  <td><input name="SubTitle" type="text" size="60"/></td>
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
			  <td class="ItemSpecifics">
				<form method="post" id="APIForm" name="APIForm" class="apiform"
					  onsubmit="apiformsubmit();return false;">
				  
				  <div class="ProductSellingPages">
					<!--
						<iframe name="productselllingpages" src="/blank.html"></iframe>
						-->
				  </div>
				</form>
				<table class="ItemSpecifics">
				</table>
			  </td>
			</tr>
		  </tbody>
		</table>
	  </div>

	  <div class="tab">
		<div class="pictures">
		  
		  GalleryType
		  <select name="PictureDetails.GalleryType">
			<option value=""></option>
			<option value="Gallery">Gallery</option>
			<option value="Plus">Plus</option>
		  </select>
		  <br/>
		  
		  <form method="post" action="/file/upload" target="posttarget" enctype="multipart/form-data">
			<table>
			  <tr>
				<s:iterator value="{0,1,2,3,4,5}">
				  <td>
					<div class="picdiv">
					  <img class="PictureDetails_PictureURL PD_PURL_<s:property />" src="/img/noimage.jpg">
					</div>
					<input type="file" name="<s:property />" style="width:50px;">
				  </td>
				</s:iterator>
			  </tr>
			  <tr>
				<s:iterator value="{6,7,8,9,10,11}">
				  <td>
					<div class="picdiv">
					  <img class="PictureDetails_PictureURL PD_PURL_<s:property />" src="/img/noimage.jpg">
					</div>
					<input type="file" name="<s:property />" style="width:50px;">
				  </td>
				</s:iterator>
			  </tr>
			</table>
		  </form>
		  
		  <s:iterator value="{0,1,2,3,4,5,6,7,8,9,10,11}">
			<input type="text" name="PictureDetails.PictureURL.<s:property />" size="40"><br>
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
				  <select name="ext.shippingtype.domestic">
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
				  <input name="<s:text name="_SDCSR"/>.ShippingIrregular" type="checkbox">
				  Irregular package
				</td>
			  </tr>
			  <tr>
				<td><s:text name="Dimensions"/></td>
				<td class="dimensions">
				  <input  name="<s:text name="_SDCSR"/>.PackageLength.#text" type="text" size="3">
				  <input  name="<s:text name="_SDCSR"/>.PackageLength.@unit" type="text" size="3">
				  <select name="<s:text name="_SDCSR"/>.PackageLength.@measurementSystem">
					<option value=""></option>
					<option value="English">English</option>
					<option value="Metric">Metric</option>
				  </select>
				  x
				  <input  name="<s:text name="_SDCSR"/>.PackageWidth.#text"  type="text" size="3">
				  <input  name="<s:text name="_SDCSR"/>.PackageWidth.@unit"  type="text" size="3">
				  <select name="<s:text name="_SDCSR"/>.PackageWidth.@measurementSystem">
					<option value=""></option>
					<option value="English">English</option>
					<option value="Metric">Metric</option>
				  </select>
				  x
				  <input  name="<s:text name="_SDCSR"/>.PackageDepth.#text"  type="text" size="3">
				  <input  name="<s:text name="_SDCSR"/>.PackageDepth.@unit"  type="text" size="3">
				  <select name="<s:text name="_SDCSR"/>.PackageDepth.@measurementSystem">
					<option value=""></option>
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
					<option value=""></option>
					<option value="English">English</option>
					<option value="Metric">Metric</option>
				  </select>
				  
				  <input name="<s:text name="_SDCSR"/>.WeightMinor.#text" type="text" size="3">
				  <input name="<s:text name="_SDCSR"/>.WeightMinor.@unit" type="text" size="3">
				  <select name="<s:text name="_SDCSR"/>.WeightMinor.@measurementSystem">
					<option value=""></option>
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
					  <!--
					  <input name="<s:text name="_SDSSO"/>.<s:property />.ShippingServicePriority"
							 type="text" size="1" value="<s:property value="#rowstatus.count"/>">
					  -->
					  <select name="<s:text name="_SDSSO"/>.<s:property />.ShippingService"
							  class="ShippingService">
						<option></option>
					  </select>
					  
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
				  
				  <select name="ext.shippingtype.international">
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
					  <!--
					  <input name="<s:text name="_SDISSO"/>.<s:property />.ShippingServicePriority"
							 type="text" size="1" value="<s:property value="#rowstatus.count"/>">
					  -->
					  <select name="<s:text name="_SDISSO"/>.<s:property />.ShippingService"
							  class="ShippingService">
						<option></option>
					  </select>
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
			  <tr>
				<td><s:text name="UserID"/></td>
				<td>
				  <input type="text" name="ext.UserID" size="10" />
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
			  <td>
				<select name="Site">
				</select>
			  </td>
			</tr>
			<tr>
			  <td><s:text name="Country"/></td>
			  <td><select name="Country"></select></td>
			</tr>
			<tr>
			  <td><s:text name="Currency"/></td>
			  <td><select name="Currency"></select></td>
			</tr>
			<tr>
			  <td><s:text name="BuyerRequirements"/></td>
			  <td>
				<input type="checkbox" value="true"
					   name="BuyerRequirementDetails.LinkedPayPalAccount">
				Don't have a PayPal account<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.checkbox">
				Have received
				<select name="BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.Count">
				  <option value=""></option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				</select>
				Unpaid item case(s) within
				<select name="BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.Period">
				  <option value=""></option>
				  <option value="Days_30">1</option>
				  <option value="Days_180">6</option>
				  <option value="Days_360">12</option>
				</select>
				month(s)<br/>
				
				<input type="checkbox" value="true"
					   name="BuyerRequirementDetails.ShipToRegistrationCountry">
				Have a primary shipping address in countries that I don't ship to<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="BuyerRequirementDetails.MaximumBuyerPolicyViolations.checkbox">
				Have
				<select name="BuyerRequirementDetails.MaximumBuyerPolicyViolations.Count">
				  <option value=""></option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				  <option value="6">6</option>
				  <option value="7">7</option>
				</select>
				Policy violation report(s) within
				<select name="BuyerRequirementDetails.MaximumBuyerPolicyViolations.Period">
				  <option value=""></option>
				  <option value="Days_30">1</option>
				  <option value="Days_180">6</option>
				</select>
				month(s)<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="BuyerRequirementDetails.MinimumFeedbackScore.checkbox">
				Have a feedback score equal to or lower than
				<select name="BuyerRequirementDetails.MinimumFeedbackScore">
				  <option value=""></option>
				  <option value="-1">-1</option>
				  <option value="-2">-2</option>
				  <option value="-3">-3</option>
				</select><br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="BuyerRequirementDetails.MaximumItemRequirements.checkbox">
				Have bid on or bought my items within the last 10 days and met my limit of
				<select name="BuyerRequirementDetails.MaximumItemRequirements.MaximumItemCount">
				  <option value=""></option>
				  <option value="1">1</option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				  <option value="6">6</option>
				  <option value="7">7</option>
				  <option value="8">8</option>
				  <option value="9">9</option>
				  <option value="10">10</option>
				  <option value="25">25</option>
				  <option value="50">50</option>
				  <option value="75">75</option>
				  <option value="100">100</option>
				</select><br/>
				&nbsp;&nbsp;&nbsp;
				<input type="checkbox" value="true" class="remove"
					   name="BuyerRequirementDetails.MaximumItemRequirements.MinimumFeedbackScore.checkbox">
				Only apply this block to buyers who have a feedback score equal to or lower than
				<select name="BuyerRequirementDetails.MaximumItemRequirements.MinimumFeedbackScore">
				  <option value=""></option>
				  <option value="5">5</option>
				  <option value="4">4</option>
				  <option value="3">3</option>
				  <option value="2">2</option>
				  <option value="1">1</option>
				  <option value="0">0</option>
				</select><br/>
				
			  </td>
			</tr>
			<tr>
			  <td><s:text name="SalesTax"/></td>
			  <td>
				<select name="BuyerRequirementDetails.">
				  <option value=""></option>
				</select>
				<input type="" name="">%<br/>

				<input type="checkbox" value="true"
					   name="BuyerRequirementDetails."/>
				Also apply to shipping & handling costs
			  </td>
			</tr>
			<tr>
			  <td><s:text name="ReturnPolicy"/></td>
			  <td>
				<iput type="radio" name="" value=""/>
				Returns Accepted<br/>

				After receiving the item, your buyer should contact you within:<br/>
				
				
				<iput type="radio" name="" value=""/>
				No returns accepted
			  </td>
			</tr>
			<tr>
			  <td><s:text name="AdditionalCheckoutInstructions"/></td>
			  <td>
				<textarea name="BuyerRequirementDetails."></textarea>
			  </td>
			</tr>
		  </tbody>
		</table>
	  </div>


	</div>

  </div>

</div>

<div style="clear:both;"></div>

</div>

<script>
var hash;
hash = ${initjson.hash};
//var summary;
//summary = ${initjson.summary};
</script>

</body>
</html>
