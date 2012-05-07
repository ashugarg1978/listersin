<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listers.in</title>
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
  <div id="message"></div>
  
  <div id="header">
	<div id="logo"><a href="/">ListersIn</a></div>
	
	<div id="headerupper">
	  <div id="headerupper_right">
		${user.email}
	  </div>
	</div>
	
	<div id="bulkbuttons">
	  
	  <button class="checkall btnleft"><s:text name="checkall"/></button>
	  <!--button class="checkallpage btncenter"><s:text name="checkallpage"/></button-->
	  <button class="uncheckall btnright"><s:text name="uncheckall"/></button>
	  
	  <!--button class="edit btnleft"><s:text name="edit"/></button-->
	  <button class="copy btnleft"><s:text name="copy"/></button>
	  <button class="delete btnright"><s:text name="delete"/></button>
	  
	  <button class="add btnleft"><s:text name="add"/></button>
	  <button class="relist btncenter"><s:text name="relist"/></button>
	  <button class="revise btncenter"><s:text name="revise"/></button>
	  <button class="verifyadditem btncenter"><s:text name="verify"/></button>
	  <button class="end btnright"><s:text name="end"/></button>
	  
	  <input id="filtertitle" type="text" class="filter" name="Title" value="" size="20">
	  <button class="end btnright"><s:text name="search"/></button>
	  
	  <button id="settingsbutton"><s:text name="settings"/></button>
	  
	  <a id="signout" href="/page/logout"><s:text name="signout"/></a>
	  
	  <div style="clear:both;"></div>
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
		<li class="setting"  ><img src="/icon/01/10/41.png"><s:text name="setting"  /></li>
	  </ul>
	</li>
  </ul>
  
  <button id="addaccount"><s:text name="addnewaccount"/></button>

  <br/><br/>
  <a href="#" id="toggledebug">DEBUG</a>
  <div id="log"></div>
  
  <input type="hidden" class="filter" name="offset" value="0"/>
  <input type="hidden" class="filter" name="limit" value="20"/>
  <input type="hidden" class="filter" name="selling" value="active"/>
  <input type="hidden" class="filter" name="sort" value="ListingDetails_EndTime"/>
  <input type="hidden" class="filter" name="allpages" value=""/>
  <input type="hidden" class="filter" name="UserID"/>
  
  <iframe name="posttarget" src="/blank.html"></iframe>
  
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
		  <a href="#" class="ItemID" target="_blank"></a>
		</td>
		<td class="price"></td>
		<td class="EndTime"></td>
		<td class="PictureURL">
		  <div>
			<img class="PictureURL" src="/img/gray.png"/>
		  </div>
		</td>
	  </tr>
	  <tr class="row2">
		<td colspan="8">
		</td>
	  </tr>
	</tbody>
	
	<tbody id="rowloading">
	  <tr>
		<td colspan="8" align="center">
		  <s:text name="LoadingItemData"/>
		</td>
	  </tr>
	</tbody>
	
  </table>
  
  <div id="settings">
	<table class="detail">
	  <tbody>
		<tr>
		  <td>Language</td>
		  <td>
			<select name="Language">
			  <option value="English">English</option>
			</select>
		  </td>
		</tr>
		<tr>
		  <td>TimeZone</td>
		  <td>
			<select name="TimeZone">
			</select>
		  </td>
		</tr>
	  </tbody>
	</table>
  </div>
  
  <div id="ebayaccountsetting">
	<div id="ebayaccountsettingtarget"></div>
	<table class="detail">
	  <tbody>
		<tr>
		  <td>
			Items
		  </td>
		  <td>
			<button id="import">Import items from eBay</button>
		  </td>
		</tr>
		<tr>
		  <td>Token</td>
		  <td>
			<button>Update token</button>
		  </td>
		</tr>
		<tr>
		  <td>
			Account
		  </td>
		  <td>
			<button>Delete from ListersIn</button>
		  </td>
		</tr>
	  </tbody>
	</table>	  
  </div>
  
  
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
	  <li><s:text name="Setting"/></li>
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
				<select class="category" name="mod.PrimaryCategory.CategoryID"></select>
			  </td>
			</tr>
			<tr>
			  <td><s:text name="ProductDetails"/></td>
			  <td>
				
				<div class="productsearchform">
				  
				  <span class="CharacteristicsSetsName"></span>
				  <input name="ProductSearch.QueryKeywords"
						 type="text" size="30" class="remove"/>
				  <input name="ProductSearch.CharacteristicSetIDs.ID"
						 type="hidden" class="remove"/>
				  <button class="GetProductSearchResults">Search</button>
				  
				  <input name="mod.ProductListingDetails.ProductID" type="hidden"/>
				  
				  <br/>
				  
				  <input type="checkbox" value="true"
						 name="mod.ProductListingDetails.IncludePrefilledItemInformation"/>
				  Include the following product information in your listing
				  
				  <br/>
				  
				  <input type="checkbox" value="true"
						 name="mod.ProductListingDetails.IncludeStockPhotoURL"/>
				  Include Stock Photo
				  
				  <br/>
				  
				  <input type="checkbox" value="true"
						 name="mod.ProductListingDetails.UseStockPhotoURLAsGallery"/>
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
			  <td>
				<input name="mod.Title" type="text" size="60"/><br/>
				<input type="checkbox" name="mod.ListingEnhancement" value="BoldTitle"/>
				Attract buyers' attention by making the title of your listing appear in Bold
			  </td>
			</tr>
			<tr>
			  <td><s:text name="subtitle"/></td>
			  <td><input name="mod.SubTitle" type="text" size="60"/></td>
			</tr>
			<tr>
			  <td><s:text name="Condition"/></td>
			  <td><select name="mod.ConditionID"></select></td>
			</tr>
			<tr>
			  <td><s:text name="ItemSpecifics"/></td>
			  <td class="ItemSpecifics">
				<form method="post" id="APIForm" name="APIForm" class="apiform"
					  onsubmit="apiformsubmit();return false;">
				  
				  <div class="ProductSellingPages">
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
		  
		  <form method="post" action="/file/upload" target="posttarget"
				enctype="multipart/form-data">
			<table>
			  <tr>
				<s:iterator value="{0,1,2,3,4,5}">
				  <td>
					<div class="picdiv">
					  <img class="PictureDetails_PictureURL PD_PURL_<s:property />"
						   src="/img/noimage.jpg"/>
					</div>
					<input type="file" name="<s:property />"/>
				  </td>
				</s:iterator>
			  </tr>
			  <tr>
				<s:iterator value="{6,7,8,9,10,11}">
				  <td>
					<div class="picdiv">
					  <img class="PictureDetails_PictureURL PD_PURL_<s:property />"
						   src="/img/noimage.jpg"/>
					</div>
					<input type="file" name="<s:property />"/>
				  </td>
				</s:iterator>
			  </tr>
			</table>
		  </form>
		  
		  <s:iterator value="{0,1,2,3,4,5,6,7,8,9,10,11}">
			<input type="hidden" name="mod.PictureDetails.PictureURL.<s:property />"/>
		  </s:iterator>
		</div>
		
		<table class="detail">
		  <tbody>
			<tr>
			  <td>
				GalleryType
			  </td>
			  <td>
				<select name="mod.PictureDetails.GalleryType">
				  <option value=""></option>
				  <option value="Gallery">Gallery</option>
				  <option value="Plus">Plus</option>
				</select>
			  </td>
			</tr>
		  </tbody>
		</table>
	  </div>
	  
	  <div class="tab">
		<div class="description">
		  <textarea name="mod.Description" cols="100" rows="10"></textarea>
		</div>

		<table class="detail">
		  <tbody>
			<tr>
			  <td colspan="2">
				<s:text name="ListingDesigner"/>
			  </td>
			</tr>
			<tr>
			  <td><s:text name="SelectTheme"/></td>
			  <td>
				<select name="ListingDesigner.GroupID">
				  <option value=""></option>
				</select>
			  </td>
			</tr>
			<tr>
			  <td><s:text name="SelectDesign"/></td>
			  <td>
				<select name="mod.ListingDesigner.ThemeID">
				  <option value=""></option>
				</select>
			  </td>
			</tr>
			<tr>
			  <td><s:text name="HitCounter"/></td>
			  <td>
				<select name="mod.HitCounter">
				  <option value=""></option>
				  <option value="BasicStyle">BasicStyle</option>
				  <option value="GreenLED">GreenLED</option>
				  <option value="Hidden">Hidden</option>
				  <option value="HiddenStyle">HiddenStyle</option>
				  <option value="HonestyStyle">HonestyStyle</option>
				  <option value="NoHitCounter">NoHitCounter</option>
				  <option value="RetroStyle">RetroStyle</option>
				</select>
			  </td>
			</tr>
		  </tbody>
		</table>
	  </div>
	  
	  
	  <div class="tab">
		<table class="detail">
		  <tbody>
			<tr>
			  <td><s:text name="StartPrice"/></td>
			  <td>
				<input name="mod.StartPrice.@currencyID" type="text" size="3">
				<input name="mod.StartPrice.#text" type="text" size="10">
			  </td>
			</tr>
			<tr>
			  <td><s:text name="BestOfferAutoAcceptPrice"/></td>
			  <td>
				<input name="mod.ListingDetails.BestOfferAutoAcceptPrice.@currencyID"
					   type="text" size="3">
				<input name="mod.ListingDetails.BestOfferAutoAcceptPrice.#text"
					   type="text" size="10">
			  </td>
			</tr>
			<tr>
			  <td><s:text name="MinimumBestOfferPrice"/></td>
			  <td>
				<input name="mod.ListingDetails.MinimumBestOfferPrice.@currencyID"
					   type="text" size="3">
				<input name="mod.ListingDetails.MinimumBestOfferPrice.#text"
					   type="text" size="10">
			  </td>
			</tr>
			<tr>
			  <td><s:text name="Quantity"/></td>
			  <td><input name="mod.Quantity" type="text" size="5"></td>
			</tr>
			<tr>
			  <td><s:text name="LotSize"/></td>
			  <td>
				<!-- todo: disable if CategoryArray.Category.LSD is true. -->
				<input name="mod.LotSize" type="text" size="5">
			  </td>
			</tr>
			<tr>
			  <td><s:text name="ListingType"/></td>
			  <td>
				<select name="mod.ListingType">
				  <option value="Chinese">Online Auction</option>  
				  <option value="FixedPriceItem">Fixed Price</option>
				  <option value="LeadGeneration">Classified Ad</option>
				</select>
			  </td>
			</tr>
			<tr>
			  <td><s:text name="ListingDuration"/></td>
			  <td>
				<select name="mod.ListingDuration"></select>
				<br/>
				- Start listing immediately<br/>
				- Schedule start time ($0.10)
			  </td>
			</tr>
			<tr>
			  <td><s:text name="PrivateListing"/></td>
			  <td>
				<input type="checkbox" name="mod.PrivateListing" value="true"/>
				Allow buyers to remain anonymous to other eBay users
			  </td>
			</tr>
			<tr>
			  <td><s:text name="BuyItNowPrice"/></td>
			  <td>
				<input name="mod.BuyItNowPrice.@currencyID" type="text" size="3">
				<input name="mod.BuyItNowPrice.#text" type="text" size="10">
			  </td>
			</tr>
			<tr>
			  <td><s:text name="BuyerGuaranteePrice"/></td>
			  <td>
				<input name="mod.BuyerGuaranteePrice.@currencyID" type="text" size="3">
				<input name="mod.BuyerGuaranteePrice.#text" type="text" size="10">
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
		  </tbody>
		</table>
	  </div>


	  <div class="tab">
		<div class="shipping">
		  <table class="detail">
			<tbody>
			  <tr>
				<td><s:text name="Shippingtype"/></td>
				<td>
				  <select name="ShippingDetails.ShippingType.domestic">
					<option value="Flat">Flat: same cost to all buyers</option>
					<option value="Calculated">Calculated: Cost varies by buyer location</option>
					<option value="Freight">Freight: large items over 150 lbs.</option>
					<option value="NoShipping">No shipping: Local pickup only</option>
				  </select>
				</td>
			  </tr>
			</tbody>
			<tbody class="shippingmainrows">
			  <tr class="packagetype">
				<td><s:text name="Packagetype"/></td>
				<td>
				  <select name="mod.<s:text name="_SDCSR"/>.ShippingPackage"></select>
				  <input name="mod.<s:text name="_SDCSR"/>.ShippingIrregular" type="checkbox">
				  Irregular package
				</td>
			  </tr>
			  <tr class="dimensions">
				<td><s:text name="Dimensions"/></td>
				<td class="dimensions">
				  <input  name="mod.<s:text name="_SDCSR"/>.PackageLength.#text"
						  type="text" size="3">
				  <input  name="mod.<s:text name="_SDCSR"/>.PackageLength.@unit"
						  type="text" size="3">
				  <select name="mod.<s:text name="_SDCSR"/>.PackageLength.@measurementSystem">
					<option value=""></option>
					<option value="English">English</option>
					<option value="Metric">Metric</option>
				  </select>
				  x
				  <input  name="mod.<s:text name="_SDCSR"/>.PackageWidth.#text"
						  type="text" size="3">
				  <input  name="mod.<s:text name="_SDCSR"/>.PackageWidth.@unit"
						  type="text" size="3">
				  <select name="mod.<s:text name="_SDCSR"/>.PackageWidth.@measurementSystem">
					<option value=""></option>
					<option value="English">English</option>
					<option value="Metric">Metric</option>
				  </select>
				  x
				  <input  name="mod.<s:text name="_SDCSR"/>.PackageDepth.#text"
						  type="text" size="3">
				  <input  name="mod.<s:text name="_SDCSR"/>.PackageDepth.@unit"
						  type="text" size="3">
				  <select name="mod.<s:text name="_SDCSR"/>.PackageDepth.@measurementSystem">
					<option value=""></option>
					<option value="English">English</option>
					<option value="Metric">Metric</option>
				  </select>
				</td>
			  </tr>
			  <tr class="weight">
				<td><s:text name="Weight"/></td>
				<td class="weight">
				  <input  name="mod.<s:text name="_SDCSR"/>.WeightMajor.#text"
						  type="text" size="3">
				  <input  name="mod.<s:text name="_SDCSR"/>.WeightMajor.@unit"
						  type="text" size="3">
				  <select name="mod.<s:text name="_SDCSR"/>.WeightMajor.@measurementSystem">
					<option value=""></option>
					<option value="English">English</option>
					<option value="Metric">Metric</option>
				  </select>
				  
				  <input name="mod.<s:text name="_SDCSR"/>.WeightMinor.#text"
						 type="text" size="3">
				  <input name="mod.<s:text name="_SDCSR"/>.WeightMinor.@unit"
						 type="text" size="3">
				  <select name="mod.<s:text name="_SDCSR"/>.WeightMinor.@measurementSystem">
					<option value=""></option>
					<option value="English">English</option>
					<option value="Metric">Metric</option>
				  </select>
				</td>
			  </tr>
			  <tr class="services">
				<td><s:text name="Services"/></td>
				<td>
				  <div class="ShippingService0">
					<input name="mod.<s:text name="_SDSSO"/>.0.ShippingServicePriority"
						   type="text" size="1">
					<select name="mod.<s:text name="_SDSSO"/>.0.ShippingService"
							class="ShippingService">
					</select>
					
					<s:text name="Cost"/>
					<input name="mod.<s:text name="_SDSSO"/>.0.ShippingServiceCost.@currencyID"
						   type="text" size="5">
					<input name="mod.<s:text name="_SDSSO"/>.0.ShippingServiceCost.#text"
						   type="text" size="5">
					
					<input name="mod.<s:text name="_SDSSO"/>.0.FreeShipping"
						   value="true" type="checkbox">Free shipping
					
					<a href="#" class="removesso">Remove service</a>
				  </div>
				  <a href="#" class="addsso">Offer additional service</a>
				</td>
			  </tr>
			  <tr class="handlingtime">
				<td><s:text name="Handlingtime"/></td>
				<td><select name="mod.DispatchTimeMax"></select></td>
			  </tr>
			  <tr>
				<td><s:text name="Options"/></td>
				<td>
				  <input type="checkbox" name="mod.GetItFast" value="true"/>
				  GetItFast
				</td>
			  </tr>
			  <tr>
				<td colspan="2" style="text-align:left;">
				  International shipping
				</td>
			  </tr>
			  <tr>
				<td><s:text name="Shippingtype"/></td>
				<td>
				  <select name="ShippingDetails.ShippingType.international">
					<option value="Flat">Flat: same cost to all buyers</option>
					<option value="Calculated">Calculated: Cost varies by buyer location</option>
					<option value="NoShipping">No international shipping</option>
				  </select>
				</td>
			  </tr>
			</tbody>
			<tbody class="internationalshippingmainrows">
			  <tr>
				<td><s:text name="Services"/></td>
				<td>
				  <div class="ShippingService0">
					<input name="mod.<s:text name="_SDISSO"/>.0.ShippingServicePriority"
						   type="text" size="1">
					<select name="mod.<s:text name="_SDISSO"/>.0.ShippingService"
							class="ShippingService">
					</select>
					
					<s:text name="Cost"/>
					<input name="mod.<s:text name="_SDISSO"/>.0.ShippingServiceCost.@currencyID"
						   type="text" size="5">
					<input name="mod.<s:text name="_SDISSO"/>.0.ShippingServiceCost.#text"
						   type="text" size="5">
					
					<a href="#" class="removesso">Remove service</a>
					<br />

					<s:text name="Shipto"/>
					<div class="ShipToLocation"></div>
					
				  </div>
				  <a href="#" class="addsso">Offer additional service</a>
				</td>
			  </tr>
			</tbody>
			<tbody>
			  <tr>
				<td><s:text name="PostalCode"/></td>
				<td>
				  <input type="text" name="mod.PostalCode" size="10" />
				</td>
			  </tr>
			  <tr>
				<td><s:text name="Location"/></td>
				<td>
				  <input type="text" name="mod.Location" size="10" />
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
			  <td><s:text name="BuyerRequirements"/></td>
			  <td>
				<input type="checkbox" value="true"
					   name="mod.BuyerRequirementDetails.LinkedPayPalAccount">
				Don't have a PayPal account<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="mod.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.checkbox">
				Have received
				<select name="mod.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.Count">
				  <option value=""></option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				</select>
				Unpaid item case(s) within
				<select name="mod.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.Period">
				  <option value=""></option>
				  <option value="Days_30">1</option>
				  <option value="Days_180">6</option>
				  <option value="Days_360">12</option>
				</select>
				month(s)<br/>
				
				<input type="checkbox" value="true"
					   name="mod.BuyerRequirementDetails.ShipToRegistrationCountry">
				Have a primary shipping address in countries that I don't ship to<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="BuyerRequirementDetails.MaximumBuyerPolicyViolations.checkbox">
				Have
				<select name="mod.BuyerRequirementDetails.MaximumBuyerPolicyViolations.Count">
				  <option value=""></option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				  <option value="6">6</option>
				  <option value="7">7</option>
				</select>
				Policy violation report(s) within
				<select name="mod.BuyerRequirementDetails.MaximumBuyerPolicyViolations.Period">
				  <option value=""></option>
				  <option value="Days_30">1</option>
				  <option value="Days_180">6</option>
				</select>
				month(s)<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="mod.BuyerRequirementDetails.MinimumFeedbackScore.checkbox">
				Have a feedback score equal to or lower than
				<select name="mod.BuyerRequirementDetails.MinimumFeedbackScore">
				  <option value=""></option>
				  <option value="-1">-1</option>
				  <option value="-2">-2</option>
				  <option value="-3">-3</option>
				</select><br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="mod.BuyerRequirementDetails.MaximumItemRequirements.checkbox">
				Have bid on or bought my items within the last 10 days and met my limit of
				<select name="mod.BuyerRequirementDetails.MaximumItemRequirements.MaximumItemCount">
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
					   name="mod.BuyerRequirementDetails.MaximumItemRequirements.MinimumFeedbackScore.checkbox">
				Only apply this block to buyers who have a feedback score equal to or lower than
				<select name="mod.BuyerRequirementDetails.MaximumItemRequirements.MinimumFeedbackScore">
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
				<select name="mod.SalesTax.ShippingIncludedInTax">
				  <option value=""></option>
				</select>
				<input type="text" name="mod.SalesTax.SalesTaxPercent">%<br/>
				
				<input type="checkbox" value="true"
					   name="mod.BuyerRequirementDetails.y"/>
				Also apply to shipping & handling costs
			  </td>
			</tr>
			<tr>
			  <td><s:text name="ReturnPolicy"/></td>
			  <td>
				<select name="mod.ReturnPolicy.ReturnsAcceptedOption">
				  <option value=""></option>
				</select>
				<br/>
				
				After receiving the item, your buyer should contact you within:
				<select name="mod.ReturnPolicy.ReturnsWithinOption">
				  <option value=""></option>
				</select>
				<br/>
				
				Refund will be given as
				<select name="mod.ReturnPolicy.RefundOption">
				  <option value=""></option>
				</select>
				<br/>
				
				Return shipping will be paid by
				<select name="mod.ReturnPolicy.ShippingCostPaidByOption">
				  <option value=""></option>
				</select>
				<br/>
				
				Additional return policy details<br/>
				<textarea name="mod.ReturnPolicy.Description" cols="60" rows="3"></textarea>
				
			  </td>
			</tr>
			<tr>
			  <td><s:text name="AdditionalCheckoutInstructions"/></td>
			  <td>
				<textarea name="mod.ShippingDetails.PaymentInstructions"
						  cols="60" rows="3"></textarea>
			  </td>
			</tr>
		  </tbody>
		</table>
	  </div>
	  
	  <div class="tab">
		<table class="detail">
		  <tbody>
			<tr>
			  <td><s:text name="UserID"/></td>
			  <td><select name="org.Seller.UserID"></select></td>
			</tr>
			<tr>
			  <td><s:text name="Site"/></td>
			  <td><select name="mod.Site"></select></td>
			</tr>
			<tr>
			  <td><s:text name="Country"/></td>
			  <td><select name="mod.Country"></select></td>
			</tr>
			<tr>
			  <td><s:text name="Currency"/></td>
			  <td><select name="mod.Currency"></select></td>
			</tr>
		  </tbody>
		</table>
	  </div><!-- tab -->
	  
	</div>

  </div>

</div>

<div style="clear:both;"></div>

</div>

<script>
var hash;
hash = ${initjson.hash};

var timezoneids = ${initjson.timezoneids};

//var summary;
//summary = ${initjson.summary};
</script>

</body>
</html>
