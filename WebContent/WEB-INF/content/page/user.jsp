<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ListersIn - eBay Listing Software</title>
<link rel="stylesheet" type="text/css" href="/css/ebay.css">
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="/css/ebay-lte-ie8.css" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="/js/jwysiwyg/jquery.wysiwyg.css">
<link rel="shortcut icon" href="/img/favicon.png">
<script type="text/javascript" src="/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.dump.js"></script>
<script type="text/javascript" src="/js/jwysiwyg/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="/js/jquery.scrollTo-min.js"></script>
<script type="text/javascript" src="/js/jquery.sortable.min.js"></script>
<script type="text/javascript" src="/js/ebay.js"></script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-32099440-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</head>
<body>

<div id="container">
  
  <div id="loading"><s:text name="loading"/></div>
  <div id="message"></div>
  
  <div id="header">
	<div id="logo">
	  <a href="/"><img src="/img/logo.png" /></a>
	</div>
	
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
	  <button class="btnright"><s:text name="search"/></button>
	  
	  <button id="settingsbutton"><s:text name="settings"/></button>
	  
	  <div id="headderrightbuttons">
		<a href="#" id="showhelp">Help</a>
		<a id="signout" href="/page/logout"><s:text name="signout"/></a>
	  </div>
	  
	  <div style="clear:both;"></div>
	</div>
	
  </div>

<div id="toolbar">
  
  <button class="newitem"><s:text name="addnewitem"/></button>
  
  <ul class="accounts">
	<li class="allitems"><s:text name="allitems"/></li>
	<li class="itemstatuses">
	  <ul class="accountaction">
		<li class="scheduled"><img src="/icon/02/10/37.png"><s:text name="scheduled"/></li>
		<li class="active"   ><img src="/icon/04/10/02.png"><s:text name="active"   /></li>
		<li class="sold"     ><img src="/icon/02/10/50.png"><s:text name="sold"     /></li>
		<li class="unsold"   ><img src="/icon/04/10/10.png"><s:text name="unsold"   /></li>
		<li class="unanswered"><img src="/icon/04/10/10.png"><s:text name="unanswered" /></li>
		<li class="saved"    ><img src="/icon/04/10/10.png"><s:text name="saved"    /></li>
	  </ul>
	</li>
  </ul>
  
  <div id="filteroption">
	<a href="#" id="checkduplicateitems">Check duplicate items</a><br/><br/>
	<a href="http://forum.listers.in/" target="_blank">Forum</a><br/>
	<a href="http://listers.in/blog/" target="_blank">Blog</a><br/>
  </div>
  
  <div id="risknotice">
	** NOTICE **<br/>
	This app is BETA.<br/>
	There may be bugs.<br/>
	Please be careful.<br/>
  </div>
  
  <a href="#" id="toggledebug">DEBUG</a><br/>
  <div id="log"></div>
  
  <input type="hidden" class="filter" name="offset"   value="0" />
  <input type="hidden" class="filter" name="limit"    value="20" />
  <input type="hidden" class="filter" name="selling"  value="active" />
  <input type="hidden" class="filter" name="sort"     value="ListingDetails_EndTime" />
  <input type="hidden" class="filter" name="option"   value="" />
  <input type="hidden" class="filter" name="allpages" value="" />
  <input type="hidden" class="filter" name="UserID" />
  
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
		  <th>Email</th>
		  <td>
			<div id="settings-email"></div>
		  </td>
		</tr>
		<tr>
		  <th>Status</th>
		  <td>
			<div id="settings-status"></div>
		  </td>
		</tr>
		<tr>
		  <th>Expiration</th>
		  <td>
			<div id="settings-expiration"></div>
		  </td>
		</tr>
		<tr>
		  <th>Item limit</th>
		  <td>
			<div id="settings-itemlimit"></div>
		  </td>
		</tr>
		<tr>
		  <th>Language</th>
		  <td>
			<select name="Language">
			  <option value="English">English</option>
			</select>
		  </td>
		</tr>
		<tr>
		  <th>Time zone</th>
		  <td>
			<select name="TimeZone">
			</select>
		  </td>
		</tr>
		<tr>
		  <th>Cancel account</th>
		  <td>
			<a id="cancelaccountlink" href="/page/cancelaccount">Cancel account</a>
		  </td>
		</tr>
		<tr>
		  <th>eBay accounts</th>
		  <td>
			<table id="setting_ebay_accounts">
			</table>
			
			<button class="addebayaccount">
			  <s:text name="addebayaccount"/>
			</button>
		  </td>
		</tr>
	  </tbody>
	</table>
  </div>
  
  <div id="help">
	
	If you don't add eBay account to ListersIn yet, please read following instruction.<br/>
	<br/>
	
	<span style="font-weight:bold;">How to add your eBay account to ListersIn.</span><br/>
	<ol>
	  <li>
		Click "<s:text name="addebayaccount"/>" button.
		<button class="addebayaccount">
		  <s:text name="addebayaccount"/>
		</button>
	  </li>
	  <li>
		You will be redirected to eBay sign in page.<br/>
		Please sign in to eBay with the account which you want to add to ListersIn.<br/>
	  </li>
	  <li>
		Click "I agree" button.<br/>
	  </li>
	  <li>
		You will be back to ListersIn site, and the eBay account will be shown at left side.
	  </li>
	</ol>
	
	If you have any problems, please send an email to
	<a href="mailto:admin@listers.in">admin@listers.in</a> :)<br/>
	Or please post your comment to <a href="http://forum.listers.in/" target="_blank">this forum</a>.(You can post without forum account)<br/>
  </div>
  
  <div id="debug"></div>
  
</div>

<div style="clear:both;"></div>


<div id="detailtemplate">
  <div class="detail">
	
	<ul class="tabNav">
	  <li class="current"><s:text name="Setting"/></li>
	  <li><s:text name="CategoryAndTitle"/></li>
	  <li><s:text name="Pictures"/></li>
	  <li><s:text name="Description"/></li>
	  <li><s:text name="Price"/></li>
	  <li><s:text name="Payment"/></li>
	  <li><s:text name="Shipping"/></li>
	  <li><s:text name="Other"/></li>
	  <li><s:text name="Messages"/></li>
	  <li><s:text name="All"/></li>
	</ul>
	
	<div class="editbuttons">
	  <button class="edit"><s:text name="edit"/></button>
	  <button class="save btnleft" style="display:none;"><s:text name="save"/></button>
	  <button class="cancel btnright" style="display:none;"><s:text name="cancel"/></button>
	</div>
	
	<div class="tabContainer">
	  
	  <div class="tab current">
		<div class="tabtitle">
		  Settings for this item
		</div>
		<table class="detail">
		  <tbody>
			<tr>
			  <th><s:text name="UserID"/></th>
			  <td><select name="UserID"></select></td>
			</tr>
			<tr>
			  <th><s:text name="Site"/></th>
			  <td><select name="mod.Site"></select></td>
			</tr>
			<tr>
			  <th><s:text name="Currency"/></th>
			  <td><select name="mod.Currency"></select></td>
			</tr>
			<tr>
			  <th><s:text name="AutoRelist"/></th>
			  <td>
				<select name="setting.autorelist.enabled">
				  <option value="off">OFF</option>
				  <option value="on">ON</option>
				</select>
				
				<br/>
				
				<input type="checkbox" value="true"
					   name="setting.autorelist.addbestoffer"
					     id="_id.setting.autorelist.addbestoffer" />
				<label  for="_id.setting.autorelist.addbestoffer">
				  Enable best offer option when auto relist.
				</label>
                
				<br/>
			  </td>
			</tr>
			<tr>
			  <th><s:text name="Schedule"/></th>
			  <td>
                <input type="datetime-local" name="setting.schedule" />
              </td>
			</tr>
		  </tbody>
		</table>
	  </div><!-- tab -->
	  
	  <div class="tab">
		<div class="tabtitle">
		  Categories where your listing will appear
		</div>
		<table class="detail">
		  <tbody>
			<tr>
			  <th><s:text name="Category"/></th>
			  <td>
				<select class="category" name="mod.PrimaryCategory.CategoryID"></select>
			  </td>
			</tr>
			<tr>
			  <th><s:text name="ProductDetails"/></th>
			  <td>
				
				<div class="productsearchform">
				  
				  <span class="CharacteristicsSetsName"></span>
				  <input name="ProductSearch.QueryKeywords"
						 type="text" size="30" class="remove"/>
				  <button class="GetProductSearchResults">Search</button>
				  <div class="productsearchmessage"></div>
				  <input name="ProductSearch.CharacteristicSetIDs.ID"
						 type="hidden" class="remove"/>
				  
				  <br />
				  <div style="clear:both;"></div>
				  
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
				  
				  <input name="mod.ProductListingDetails.ProductID"
						 type="text" size="15" placeholder="Product ID" />
				  <input name="mod.ProductListingDetails.ProductReferenceID"
						 type="text" size="15" placeholder="Product Reference ID" />
				  <br />
				  
				  <br/>
				  
				  <input type="checkbox" value="true"
						 name="mod.ProductListingDetails.IncludePrefilledItemInformation"
						   id="_id.ProductListingDetails.IncludePrefilledItemInformation" />
				  <label  for="_id.ProductListingDetails.IncludePrefilledItemInformation">
					Include the following product information in your listing
				  </label>
				  
				  <br/>
				  
				  <input type="checkbox" value="true"
						 name="mod.ProductListingDetails.IncludeStockPhotoURL"
						   id="_id.ProductListingDetails.IncludeStockPhotoURL" />
				  <label  for="_id.ProductListingDetails.IncludeStockPhotoURL">
					Include Stock Photo
				  </label>
				  
				  <br/>
				  
				  <input type="checkbox" value="true"
						 name="mod.ProductListingDetails.UseStockPhotoURLAsGallery"
						   id="_id.ProductListingDetails.UseStockPhotoURLAsGallery" />
				  <label  for="_id.ProductListingDetails.UseStockPhotoURLAsGallery">
					Use Stock Photo As Gallery
				  </label>
				  
				  <br/>
				  
				</div>
			  </td>
			</tr>
		  </tbody>
		</table>
		
		<div class="tabtitle">
		  Help buyers find your item with a great title
		</div>
		<table class="detail">
		  <tbody>
			<!--
				todo: "List multiple variations of this item in one listing"
			  -->
			<tr>
			  <th><s:text name="title"/></th>
			  <td>
				<input name="mod.Title" type="text" size="80"/><br/>
				<input type="checkbox" value="BoldTitle"
					   name="mod.ListingEnhancement"
					     id="_id.ListingEnhancement" />
				<label  for="_id.ListingEnhancement">
				  Attract buyers' attention by making the title of your listing appear in Bold
				</label>
			  </td>
			</tr>
			<tr>
			  <th><s:text name="subtitle"/></th>
			  <td><input name="mod.SubTitle" type="text" size="60"/></td>
			</tr>
			<tr>
			  <th><s:text name="Condition"/></th>
			  <td><select name="mod.ConditionID"></select></td>
			</tr>
			<tr>
			  <th><s:text name="ItemSpecifics"/></th>
			  <td class="ItemSpecifics">
				<table class="ItemSpecifics">
				</table>
				<a href="#" class="addis">Add Item Specifics</a>
			  </td>
			</tr>
		  </tbody>
		</table>
	  </div>
	  
	  <div class="tab">
		<div class="tabtitle">
		  Bring your item to life with pictures
		</div>
		<div class="pictures">
		  
		  <ul class="pictures clearfix">
			<li class="template">
			  <div>
				<img src="/img/noimage.jpg" />
			  </div>
			  <a href="#" class="deletepicture">Delete</a>
			</li>
		  </ul>
		  
		  <form method="post" action="/file/upload"
				target="posttarget" enctype="multipart/form-data">
            Add images
			<input type="file" name="multiplefile" multiple="multiple"/>
		  </form>
		  
		</div>
		
		<table class="detail">
		  <tbody>
			<tr>
			  <th>GalleryType</th>
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
		<div class="tabtitle">
		  Describe the item you're selling
		</div>
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
			  <th><s:text name="SelectTheme"/></th>
			  <td>
				<select name="ListingDesigner.GroupID">
				  <option value=""></option>
				</select>
			  </td>
			</tr>
			<tr>
			  <th><s:text name="SelectDesign"/></th>
			  <td>
				<select name="mod.ListingDesigner.ThemeID">
				  <option value=""></option>
				</select>
			  </td>
			</tr>
			<tr>
			  <th><s:text name="HitCounter"/></th>
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
		<div class="tabtitle">
		  Choose how you'd like to sell your item
		</div>
		<table class="detail">
		  <tbody>
			<tr>
			  <th><s:text name="ListingType"/></th>
			  <td>
				<select name="mod.ListingType">
				  <option value="Chinese">Online Auction</option>  
				  <option value="FixedPriceItem">Fixed Price</option>
				  <option value="LeadGeneration">Classified Ad</option>
				</select>
			  </td>
			</tr>
			<tr>
			  <th><s:text name="StartPrice"/></th>
			  <td>
				<input name="mod.StartPrice.@currencyID"
					   type="text" size="3" class="aslabel">
				<input name="mod.StartPrice.#text" type="text" size="10">
			  </td>
			</tr>
			<tr>
			  <th><s:text name="BestOffer"/></th>
			  <td>
				<input type="checkbox" value="true"
					   name="mod.BestOfferDetails.BestOfferEnabled"
					     id="_id.BestOfferDetails.BestOfferEnabled" />
				<label  for="_id.BestOfferDetails.BestOfferEnabled">
				  Allow buyers to send you their Best Offers for your consideration
				</label>
			  </td>
			</tr>
			<tr>
			  <th><s:text name="BestOfferAutoAcceptPrice"/></th>
			  <td>
				<input name="mod.ListingDetails.BestOfferAutoAcceptPrice.@currencyID"
					   type="text" size="3" class="aslabel">
				<input name="mod.ListingDetails.BestOfferAutoAcceptPrice.#text"
					   type="text" size="10">
			  </td>
			</tr>
			<tr>
			  <th><s:text name="MinimumBestOfferPrice"/></th>
			  <td>
				<input name="mod.ListingDetails.MinimumBestOfferPrice.@currencyID"
					   type="text" size="3" class="aslabel">
				<input name="mod.ListingDetails.MinimumBestOfferPrice.#text"
					   type="text" size="10">
			  </td>
			</tr>
			<tr>
			  <th><s:text name="Quantity"/></th>
			  <td><input name="mod.Quantity" type="text" size="5"></td>
			</tr>
			<tr>
			  <th><s:text name="LotSize"/></th>
			  <td>
				<!-- todo: disable if CategoryArray.Category.LSD is true. -->
				<input name="mod.LotSize" type="text" size="5">
			  </td>
			</tr>
			<tr>
			  <th><s:text name="ListingDuration"/></th>
			  <td>
				<select name="mod.ListingDuration"></select>
				<br/>
				<input type="radio" value="0"
					   name="ScheduleTime.radio"
					     id="_id.ScheduleTime.radio.0" />
				<label  for="_id.ScheduleTime.radio.0">
				  Start listing immediately
				</label>
				<br/>
				<input type="radio" value="1"
					   name="ScheduleTime.radio"
					     id="_id.ScheduleTime.radio.1" />
				<label  for="_id.ScheduleTime.radio.1">
				  Schedule start time ($0.10)
				</label>
				
				<select name="ScheduleTime.date">
				  <option value="">Date</option>
				</select>
				<select name="ScheduleTime.hour">
				  <option value="">Hour</option>
				  <s:iterator begin="0" end="23">
					<option value="<s:property />"><s:property /></option>
				  </s:iterator>
				</select>
				<select name="ScheduleTime.minute">
				  <option value="">Minute</option>
				  <s:iterator begin="00" end="59">
					<option value="<s:property />"><s:property /></option>
				  </s:iterator>
				</select>
				PDT
				
			  </td>
			</tr>
			<tr>
			  <th><s:text name="PrivateListing"/></th>
			  <td>
				<input type="checkbox" value="true"
					   name="mod.PrivateListing"
					     id="_id.PrivateListing" />
				<label  for="_id.PrivateListing">
				  Allow buyers to remain anonymous to other eBay users
				</label>
			  </td>
			</tr>
			<tr>
			  <th><s:text name="BuyItNowPrice"/></th>
			  <td>
				<input name="mod.BuyItNowPrice.@currencyID"
					   type="text" size="3" class="aslabel">
				<input name="mod.BuyItNowPrice.#text" type="text" size="10">
			  </td>
			</tr>
			<tr>
			  <th><s:text name="BuyerGuaranteePrice"/></th>
			  <td>
				<input name="mod.BuyerGuaranteePrice.@currencyID"
					   type="text" size="3" class="aslabel">
				<input name="mod.BuyerGuaranteePrice.#text" type="text" size="10">
			  </td>
			</tr>
		  </tbody>
		</table>
	  </div>

	  <div class="tab">
		<div class="tabtitle">
		  Decide how you'd like to be paid
		</div>
		<table class="detail">
		  <tbody>
			<tr>
			  <th><s:text name="PaymentMethods"/></th>
			  <td class="paymentmethod">
			  </td>
			</tr>
		  </tbody>
		</table>
	  </div>


	  <div class="tab">
		<div class="tabtitle">
		  Give buyers shipping details
		</div>
		<div class="shipping">
		  <table class="detail">
			<tbody>
			  <tr>
				<td colspan="2" class="tab_subtitle">
				  U.S. shipping
				</td>
			  </tr>
			  <tr>
				<th><s:text name="Shippingtype"/></th>
				<td>
				  <select name="ShippingDetails.ShippingType.domestic">
					<option value="Flat">Flat: same cost to all buyers</option>
					<option value="Calculated">Calculated: Cost varies by buyer location</option>
					<option value="Freight">Freight: large items over 150 lbs.</option>
					<option value="NoShipping" selected="selected">No shipping: Local pickup only</option>
				  </select>
				</td>
			  </tr>
			</tbody>
			<tbody class="shippingmainrows">
			  <tr class="packagetype">
				<th><s:text name="Packagetype"/></th>
				<td>
				  <select name="mod.<s:text name="_SDCSR"/>.ShippingPackage"></select>
				  <input type="checkbox"
						 name="mod.<s:text name="_SDCSR"/>.ShippingIrregular"
						   id="_id.<s:text name="_SDCSR"/>.ShippingIrregular" />
				  <label  for="_id.<s:text name="_SDCSR"/>.ShippingIrregular">
					Irregular package
				  </label>
				</td>
			  </tr>
			  <tr class="dimensions">
				<th><s:text name="Dimensions"/></th>
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
				<th><s:text name="Weight"/></th>
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
				<th><s:text name="Services"/></th>
				<td>
				  <div class="ShippingService0">
					<input name="mod.<s:text name="_SDSSO"/>.0.ShippingServicePriority"
						   type="text" size="1" value="1" />
					<select name="mod.<s:text name="_SDSSO"/>.0.ShippingService"
							class="ShippingService">
					</select>
					
					<input name="mod.<s:text name="_SDSSO"/>.0.ShippingServiceCost.@currencyID"
						   type="text" size="3" class="aslabel" />
					<input name="mod.<s:text name="_SDSSO"/>.0.ShippingServiceCost.#text"
						   type="text" size="5" placeholder="Cost" />
					
					<input value="true" type="checkbox"
						   name="mod.<s:text name="_SDSSO"/>.0.FreeShipping"
						     id="_id.<s:text name="_SDSSO"/>.0.FreeShipping" />
					<label  for="_id.<s:text name="_SDSSO"/>.0.FreeShipping">
					  Free shipping
					</label>
					<a href="#" class="removesso">Remove service</a>
				  </div>
				  <a href="#" class="addsso">Offer additional service</a>
				</td>
			  </tr>
			  <tr class="handlingtime">
				<th><s:text name="Handlingtime"/></th>
				<td><select name="mod.DispatchTimeMax"></select></td>
			  </tr>
			  <tr>
				<th><s:text name="Options"/></th>
				<td>
				  <input type="checkbox" value="true"
						 name="mod.GetItFast"
						   id="_id.GetItFast" />
				  <label  for="_id.GetItFast">
					GetItFast
				  </label>
				</td>
			  </tr>
			  <tr>
				<td colspan="2" class="tab_subtitle">
				  International shipping
				</td>
			  </tr>
			  <tr>
				<th><s:text name="Shippingtype"/></th>
				<td>
				  <select name="ShippingDetails.ShippingType.international">
					<option value="Flat">Flat: same cost to all buyers</option>
					<option value="Calculated">Calculated: Cost varies by buyer location</option>
					<option value="NoShipping" selected="selected">No international shipping</option>
				  </select>
				</td>
			  </tr>
			</tbody>
			<tbody class="internationalshippingmainrows">
			  <tr>
				<th><s:text name="Services"/></th>
				<td>
				  <div class="ShippingService0">
					<input name="mod.<s:text name="_SDISSO"/>.0.ShippingServicePriority"
						   type="text" size="1" value="1">
					<select name="mod.<s:text name="_SDISSO"/>.0.ShippingService"
							class="ShippingService">
					</select>
					
					<s:text name="Cost"/>
					<input name="mod.<s:text name="_SDISSO"/>.0.ShippingServiceCost.@currencyID"
						   type="text" size="3" class="aslabel">
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
				<td colspan="2" class="tab_subtitle">
				  Item location
				</td>
			  </tr>
			  <tr>
				<th><s:text name="Country"/></th>
				<td><select name="mod.Country"></select></td>
			  </tr>
			  <tr>
				<th><s:text name="PostalCode"/></th>
				<td>
				  <input type="text" name="mod.PostalCode" size="10" />
				</td>
			  </tr>
			  <tr>
				<th><s:text name="Location"/></th>
				<td>
				  <input type="text" name="mod.Location" size="10" />
				</td>
			  </tr>
			</tbody>
		  </table>
		</div>
	  </div><!-- tab -->

	  <div class="tab">
		<div class="tabtitle">
		  Other things you'd like buyers to know
		</div>
		<table class="detail">
		  <tbody>
			<tr>
			  <th><s:text name="BuyerRequirements"/></th>
			  <td>
				<input type="checkbox" value="true"
					   name="mod.BuyerRequirementDetails.LinkedPayPalAccount"
					     id="_id.BuyerRequirementDetails.LinkedPayPalAccount">
				<label  for="_id.BuyerRequirementDetails.LinkedPayPalAccount">
				  Don't have a PayPal account
				</label>
				
				<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="mod.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.checkbox"
					     id="_id.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.checkbox">
				<label  for="_id.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.checkbox">
				  Have received
				</label>
				<select name="mod.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.Count">
				  <option value=""></option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				</select>
				<label  for="_id.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.checkbox">
				  Unpaid item case(s) within
				</label>
				<select name="mod.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.Period">
				  <option value=""></option>
				  <option value="Days_30">1</option>
				  <option value="Days_180">6</option>
				  <option value="Days_360">12</option>
				</select>
				<label  for="_id.BuyerRequirementDetails.MaximumUnpaidItemStrikesInfo.checkbox">
				  month(s)
				</label>
				
				<br/>
				
				<input type="checkbox" value="true"
					   name="mod.BuyerRequirementDetails.ShipToRegistrationCountry"
					     id="_id.BuyerRequirementDetails.ShipToRegistrationCountry">
				<label  for="_id.BuyerRequirementDetails.ShipToRegistrationCountry">
				  Have a primary shipping address in countries that I don't ship to
				</label>
				
				<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="BuyerRequirementDetails.MaximumBuyerPolicyViolations.checkbox"
					     id="_id.BuyerRequirementDetails.MaximumBuyerPolicyViolations.checkbox">
				<label  for="_id.BuyerRequirementDetails.MaximumBuyerPolicyViolations.checkbox">
				  Have
				</label>
				<select name="mod.BuyerRequirementDetails.MaximumBuyerPolicyViolations.Count">
				  <option value=""></option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				  <option value="6">6</option>
				  <option value="7">7</option>
				</select>
				<label  for="_id.BuyerRequirementDetails.MaximumBuyerPolicyViolations.checkbox">
				  Policy violation report(s) within
				</label>
				<select name="mod.BuyerRequirementDetails.MaximumBuyerPolicyViolations.Period">
				  <option value=""></option>
				  <option value="Days_30">1</option>
				  <option value="Days_180">6</option>
				</select>
				<label  for="_id.BuyerRequirementDetails.MaximumBuyerPolicyViolations.checkbox">
				  month(s)
				</label>
				
				<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="mod.BuyerRequirementDetails.MinimumFeedbackScore.checkbox"
					     id="_id.BuyerRequirementDetails.MinimumFeedbackScore.checkbox">
				<label  for="_id.BuyerRequirementDetails.MinimumFeedbackScore.checkbox">
				  Have a feedback score equal to or lower than
				</label>
				<select name="mod.BuyerRequirementDetails.MinimumFeedbackScore">
				  <option value=""></option>
				  <option value="-1">-1</option>
				  <option value="-2">-2</option>
				  <option value="-3">-3</option>
				</select>
				
				<br/>
				
				<input type="checkbox" value="true" class="remove"
					   name="mod.BuyerRequirementDetails.MaximumItemRequirements.checkbox"
					     id="_id.BuyerRequirementDetails.MaximumItemRequirements.checkbox">
				<label  for="_id.BuyerRequirementDetails.MaximumItemRequirements.checkbox">
				  Have bid on or bought my items within the last 10 days and met my limit of
				</label>
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
				</select>
				
				<br/>
				
				&nbsp;&nbsp;&nbsp;
				
	<input type="checkbox" value="true" class="remove"
		   name="mod.BuyerRequirementDetails.MaximumItemRequirements.MinimumFeedbackScore.checkbox"
		     id="_id.BuyerRequirementDetails.MaximumItemRequirements.MinimumFeedbackScore.checkbox">
	<label  for="_id.BuyerRequirementDetails.MaximumItemRequirements.MinimumFeedbackScore.checkbox">
	  Only apply this block to buyers who have a feedback score equal to or lower than
	</label>
	<select name="mod.BuyerRequirementDetails.MaximumItemRequirements.MinimumFeedbackScore">
	  <option value=""></option>
	  <option value="5">5</option>
	  <option value="4">4</option>
	  <option value="3">3</option>
	  <option value="2">2</option>
	  <option value="1">1</option>
	  <option value="0">0</option>
	</select>
	
	<br/>
	
			  </td>
			</tr>
			<tr>
			  <th><s:text name="SalesTax"/></th>
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
			  <th><s:text name="ReturnPolicy"/></th>
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
			  <th><s:text name="AdditionalCheckoutInstructions"/></th>
			  <td>
				<textarea name="mod.ShippingDetails.PaymentInstructions"
						  cols="60" rows="3"></textarea>
			  </td>
			</tr>
		  </tbody>
		</table>
	  </div><!-- tab -->
	  
	  <div class="tab">
		<div class="tabtitle">
		  Messages
		</div>
        
        <div class="question-block question-template clearfix">
          <ul class="question-ul clearfix">
            <li class="question-status"></li>
            <li class="question-date"></li>
            <li class="question-sender"></li>
            <li class="question-parent"></li>
          </ul>
          <div class="question-body"></div>
          <textarea></textarea>
		  <button>Respond</button>
        </div>
        
		<div class="buyer-template clearfix">
          <div class="buyer-userid"></div>
		  <div class="buyer-information"></div>
		  <div class="buyer-sendmessage">
			<textarea></textarea>
			<button>Send message</button>
		  </div>
		</div>
        
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

var scheduledays = ${initjson.scheduledays};

//var summary;
//summary = ${initjson.summary};
</script>

</body>
</html>
