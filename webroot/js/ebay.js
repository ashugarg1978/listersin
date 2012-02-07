/* store rows data */
var rowsdata = new Array();
var hasmore = false;

/* initialize */
$(function() {
	
	resizediv();
	bindevents();
	summary();
	//gethash();
	dump(hash);
	
	// todo: same code
	$.each(hash, function(k, v) {
		var optiontag = $('<option />').val(k).html(k);
		$('select[name=Site]', $('div#detailtemplate')).append(optiontag);
	});
	
	$.ajaxSetup({async: false});
	
	$('ul.accounts > li > ul:first').slideToggle('fast');
	$('li.active', $('ul.accountaction:first')).click();
	
	if (false) {
		id = $('a.Title:lt(2):last').closest('tbody.itemrow').attr('id');
		$('a.Title', 'tbody#'+id).click();
		$('a.edit', 'tbody#'+id).click();
		$('input[name="ProductSearch.QueryKeywords"]', '#'+id).val('htc');
		$('button.GetProductSearchResults', '#'+id).click();
		$('div.product:first').click();
		$('div.ProductSellingPages select:first', '#'+id).click();
		//$('a.save', '#'+id).click();
	}
	
	//setTimeout('autoclick()', 2000);
	//setTimeout("$('ul.editbuttons > li > a.save', 'div.detail').click()", 5000);
	
	//setInterval(refresh, 2000);
	
	//setTimeout(autoclick, 3000);
	
	return;
});

function bindevents()
{
	$(window).resize(resizediv);
	
	$(window).scroll(function() {
		if (hasmore == false) return;
		
		if ($(window).scrollTop() + $(window).height() == $('body').height()) {
			$('div#loading').show();

			var offset = parseInt($('table#hiddenforms input[name=offset]').val());
			var limit  = parseInt($('table#hiddenforms input[name=limit]' ).val());
			
			$('table#hiddenforms input[name=offset]').val(offset+limit-0);
			items();
		}
	});
	
	$('tr.row1').live('click', clickTitle);
	
	$('select[name=Site]').live('change', changeSite);
	$('select.category').live('change', changeCategory);
	
	$('div.editbuttons button.edit',   'div.detail').live('click', clickEdit);
	$('div.editbuttons button.save',   'div.detail').live('click', clickSave);
	$('div.editbuttons button.cancel', 'div.detail').live('click', clickCancel);
	$('div.editbuttons button.delete', 'div.detail').live('click', clickDelete);
	$('div.editbuttons button.copy',   'div.detail').live('click', clickCopy);
	
	/* Bulk Buttons */
	$('div#bulkbuttons button').live('click', function() {
		action = $(this).attr('class');
		
		if (action == 'checkall') {
			$("input[name='id'][value!=on]").attr('checked', 'checked');
			//$("input[name='allpages']").attr('checked', '');
			$("input[name='allpages']").removeAttr('checked');
			return;
		} else if (action == 'checkallpage') {
			$("input[name='id'][value!=on]").attr('checked', 'checked');
			$("input[name='allpages']").attr('checked', 'checked');
			return;
		} else if (action == 'uncheckall') {
			//$("input[name='id'][value!=on]").attr('checked', '');
			//$("input[name='allpages']").attr('checked', '');
			$("input[name='id'][value!=on]").removeAttr('checked');
			$("input[name='allpages']").removeAttr('checked');
			return;
		}
		
		var postdata = "";
		if ($("input[name='allpages']").attr('checked')) {
			alert('apply to all pages?');
			postdata = $('input.filter, select.filter').serialize();
		} else {
			postdata = $("input[name='id'][value!=on]:checked").serialize();
		}
		
		$("input[name='id']:checked").each(function() {
			$(this).css('visibility', 'hidden');
			$(this).parent().addClass('loading');
		});
		
		$.post('/json/'+action,
			   postdata,
			   function(data) {
				   if (action == 'copy' || action == 'delete') {
					   $("td.loading").removeClass('loading');
					   $("input[name='id'][value!=on]:checked")
						   .css('visibility', '')
						   .attr('checked', '');
				   }
				   if (action == 'delete') {
					   //items();
				   }
				   dump(data);
			   });
		
		return;
	});
	
	/* Left Navi */
	$('ul.accounts > li').live('click', function() {
		
		if ($(this).attr('class') == 'allitems'
			&& $('ul', $(this).next()).css('display') == 'block') {
			// don't collapse navi
		} else {
			$('ul', $(this).next()).slideToggle('fast');
		}
		
		userid = $(this).attr('class');
		$('select[name=UserID]').val(userid);
		$('input[name=selling]').val('allitems');
		$('input[name=offset]').val(0);
		$('table#items tbody:gt(1)').remove();
		items();
		
		$('ul.accounts li').removeClass('tabselected');
		$(this).closest('li').addClass('tabselected');
		
		return false;
	});
	
	/* Picture */
    $('input:file').live('change', function() {
		
		id = $(this).closest('tbody.itemrow').attr('id');
		idform = $('<input>').attr('name', 'id').val(id);
		$(this).closest('form').append(idform);
		
		fileindex = $(this).attr('name');
		fileindexform = $('<input>').attr('name', 'fileindex').val(fileindex);
		$(this).closest('form').append(fileindexform);
		
		$(this).attr('name', 'uploadfile')
		$(this).closest('form').submit();
		$(this).closest('form')[0].reset();
		
		$(fileindexform).remove();
		$(idform).remove();
    });
    
	$('select[name=ListingType]').live('change', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		updateduration(id);
	});
	
	$('ul.tabNav li').live('click', function() {
		id = $(this).closest('tbody').attr('id');
		var curIdx = $(this).prevAll().length + 1;
		$(this).parent().children('.current').removeClass('current');
		$(this).addClass('current');
		$('div.tabContainer', 'tbody#'+id).children('.current').hide();
		$('div.tabContainer', 'tbody#'+id).children('.current').removeClass('current');
		$('div.tabContainer', 'tbody#'+id).children('div:nth-child('+curIdx+')').show();
		$('div.tabContainer', 'tbody#'+id).children('div:nth-child('+curIdx+')').addClass('current');
		
		return false;
	});
	
	
	$('ul.accountaction > li').live('click', function() {
		
		v = $(this).attr('class');
		userid = $(this).parent().attr('class').replace(/^accountaction /, '');
		
		$('input[name=selling]').val(v);
		$('input[name=offset]').val(0);
		$('select[name=UserID]').val(userid);
		if (v == 'unsold' || v == 'sold' || v == 'allitems') {
			$('input[name=sort]').val('ListingDetails_EndTime DESC');
		} else {
			$('input[name=sort]').val('ListingDetails_EndTime');
		}
		$('table#items tbody:gt(1)').remove();
		items();
		$('ul.accounts li').removeClass('tabselected');
		$(this).closest('li').addClass('tabselected');
		
		return false;
	});
	
	
	/* Editor */
	$('a.wysiwyg').live('click', function() {
		$('textarea[name=description]', '#'+id).wysiwyg('destroy');
		return false;
	});
	
	/* ShippingType */
	// todo: check all browsers can detect [domestic] selector
	$('select[name="ShippingDetails.ShippingType.domestic"]').live('change', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		sel = getshippingservice(id);
		$('td.shippingservice', '#'+id).html(sel);
		
		return;
	});
	
	/* Import */
	$('div#importform input[type=button]').live('click', function() {
		$.post('/json/import',
			   $('select, input', 'div#importform').serialize(),
			   function(data) {
				   
			   });
	});
	
	$('button.GetProductSearchResults').live('click', function() {
		var td = $(this).parent();
		var postdata = $('input[type=text],input[type=hidden]', td).serialize();
		$.post('/json/productsearchresults',
			   postdata,
			   function(data) {
				   var families = data.json.result.ProductSearchResult.AttributeSet.ProductFamilies;
				   $.each(families, function(i, o) {
					   
					   var divtag = $('div.producttemplate', td).clone().attr('class', 'product');
					   $(divtag).show();
					   //$('img', divtag).attr('src', o.ParentProduct['@stockPhotoURL']);
					   $('div.producttext', divtag).html(o.ParentProduct['@title']);
					   $('div.productid', divtag).html(o.ParentProduct['@productID']);
					   $('div.foundproducts', td).append(divtag);
				   });
				   $('div.foundproducts', td).slideDown('fast');
			   },
			   'json');
	});
	
	$('div.foundproducts div.product').live('click', function() {
		var id = $(this).closest('tbody.itemrow').attr('id');
		var productid = $('div.productid', $(this)).html();
		var title = $('div.producttext', $(this)).html();
		$('input[name="ProductListingDetails.ProductID"]', $(this).closest('td')).val(productid);
		$('input[name=Title]', '#'+id).val(title);
		$(this).closest('div.foundproducts').slideUp('fast');
		
		// todo: load frame
		$.post('/json/productsellingpages',
			   'productid='+productid+'&attributesetid='
			   +$('input[name="ProductSearch.CharacteristicSetIDs.ID"]', '#'+id).val(),
			   function(data) {
				   var htmlcode = data.json.result;
				   
				   $('table.ItemSpecifics', '#'+id).hide();
				   $('div.ProductSellingPages', '#'+id).html('');
				   
				   htmlcode = htmlcode.replace("var formName = 'APIForm';",
											   "var formName = 'APIForm"+id+"';");
				   
				   // todo: replace on server side.
				   // todo: have to trap all submit code written by eBay.
				   htmlcode = htmlcode.replace("document.forms[formName].submit();",
											   "apiformsubmit(formName);");
				   
				   htmlcode = htmlcode.replace("pagedoc.submit();",
											   "apiformsubmit(formName);");
				   
				   htmlcode = htmlcode.replace("aus_form.submit();",
											   "apiformsubmit(formName);");
				   
				   $('div.ProductSellingPages', '#'+id).append(htmlcode);
				   
				   // todo: check javascript code at last line of htmlcode.
			   },
			   'json');
	});
	
	$('a#toggledebug').live('click', function() {
		$('div#debug').toggle();
		return false;
	});
	
    //jQuery('div#loading').ajaxStart(function() {jQuery(this).show();});
    //jQuery('div#loading').ajaxStop( function() {jQuery(this).hide();});
}	

function apiformsubmit(formname)
{
	var id = formname.replace('APIForm', '');
	var postdata = $('input,select', 'form#'+formname).serialize();
	
	$.post('/json/parsesellingpage',
		   postdata,
		   function(data) {
			   var htmlcode = data.json.result;
			   
			   htmlcode = htmlcode.replace("var formName = 'APIForm';",
										   "var formName = 'APIForm"+id+"';");
			   
			   // todo: replace on server side.
			   // todo: have to trap all submit code written by eBay.
			   htmlcode = htmlcode.replace("document.forms[formName].submit();",
										   "apiformsubmit(formName);");
			   
			   htmlcode = htmlcode.replace("pagedoc.submit();",
										   "apiformsubmit(formName);");
			   
			   htmlcode = htmlcode.replace("aus_form.submit();",
										   "apiformsubmit(formName);");
			   
			   $('div.ProductSellingPages', '#'+id).html("");
			   $('div.ProductSellingPages', '#'+id).append(htmlcode);
		   },
		   'json');
	
	return false;
}

/* auto click for debug */
function autoclick()
{
	itemid = '110089979385';
	
	$.ajaxSetup({async: false});
	//$('input[class=filter][name=ItemID]').val(itemid);
	//$('a.allitems').click();
	id = $('a.Title:lt(2):last').closest('tbody.itemrow').attr('id');
	$('a.Title', 'tbody#'+id).click();
	$('a.edit', 'tbody#'+id).click();
	$('input[name="ProductSearch.QueryKeywords"]', '#'+id).val('android');
	$('button.GetProductSearchResults', '#'+id).click();
	$('div.product:first').click();
	//$('a.save', 'tbody#'+id).click();
	
	return;
	
	if (id == 'rowtemplate') return;
	
	$('a.Title', 'tbody#'+id).click();
	//setTimeout("$('li > a:contains(Shipping)', '   tbody#'+id).click()", 2000);
	//setTimeout("$('ul.editbuttons > li > a.edit', 'tbody#'+id).click()", 2000);
	
	return;
}

/**
 * Convert form elements into json format.
 * http://stackoverflow.com/questions/2552836/convert-an-html-form-field-to-a-json-object-with-inner-objects 
 */
$.fn.extractObject = function() {
	var accum = {};
	
	function add(accum, namev, value) {
		if (namev.length == 0) return; // todo: fix ext.categorypath.0 bug
		
		if (namev.length == 1) {
			
			if (namev[0] == '') return;
			
			accum[namev[0]] = value;
			
		} else {
			
			if (accum[namev[0]] == null) {
				if (namev[1].match(/^[0-9]+$/)) {
					accum[namev[0]] = [];
				} else {
					accum[namev[0]] = {};
				}
			}
			
			add(accum[namev[0]], namev.slice(1), value);
		}
	}; 
	
	this.each(function() {
		if ($(this).attr('name') == undefined) return;
		if ($(this).val() == '') return;
		if ($(this).val() == null) return;
		
		add(accum, $(this).attr('name').split('.'), $(this).val());
	});
	
	return accum;
};

$.fn.extractAttrObject = function() {
	
	var accum = [];
	var existnames = {};
	
	this.each(function() {
		
		var name = $(this).attr('name');
		
		var value = $(this).val();
		
		var attribute = {};

		attribute['@attributeID'] = name;
		
		if (existnames[name] == null) {
			attribute.Value = {};
			attribute.Value.ValueID = value;
		} else {
			/*
			if ($.isArray(accum[name])) {
				accum[name].push(value);
			} else {
				var tmpvalue = accum[name];
				accum[name] = [tmpvalue];
				accum[name].push(value);
			}
			*/
		}
		existnames[name] = 1;
		
		accum.push(attribute);
	});
	
	return accum;
}


function gethash()
{
	tmp = localStorage.getItem('hash');
	if (false && tmp != undefined) {
		hash = JSON.parse(tmp);
		
		// todo: same code
		$.each(hash, function(k, v) {
			$('select[name=Site]', $('div#detailtemplate')).append('<option>'+k+'</option>');
		});
		
		return;
	}
	
	$.getJSON('/json/hash', function(data) {
		hash = data.json;
		
		// todo: same code
		$.each(hash, function(k, v) {
			$('select[name=Site]', $('div#detailtemplate')).append('<option>'+k+'</option>');
		});
		
		localStorage.setItem('hash', JSON.stringify(hash));
	});
	
	return;
	
//	$.get('/json/hash', function(data) {
//		dump(data);
//		localStorage.setItem('hashdata', data);
//		parsed = $.parseJSON(data);
//		hash = parsed.json;
//	},
//		 'html');
//	tmp = localStorage.getItem('hashdata');
	
}

function summary()
{
	var ulorg = $('ul.accounts').clone();
	
	$.getJSON('/json/summary', function(data) {
		
		$('ul.accounts > li.allitems').append(' ('+data.json.alluserids.allitems+')');
		$.each(data.json.alluserids, function(k, v) {
			$('ul.accounts > li > ul.accountaction li.'+k).append(' ('+v+')');
		});
		
		$.each(data.json, function(k, o) {
			if (k == 'alluserids') return;
			var ul = ulorg.clone();
			$('li.allitems', ul).attr('class', k).html(k+' ('+o.allitems+')');
			$('ul.accountaction', ul).attr('class', 'accountaction '+k);
			$.each(o, function(j, v) {
				$('li.'+j, ul).append(' ('+v+')');
			});
			$('ul.accounts').append(ul.html());
		});
		
	});
	
	return;
}

/* list items */
function items()
{
	$.post
	('/json/items',
	 $('input.filter, select.filter').serialize(),
	 function(data) {
		 
		 if (data.json.cnt == 0) {
			 $('tbody#rowloading > tr > td').html('No item data found.');
			 $('tbody#rowloading').show();
			 return;
		 }
		 $('tbody#rowloading').hide();
		 
		 var offset = parseInt($('table#hiddenforms input[name=offset]').val());
		 var limit  = parseInt($('table#hiddenforms input[name=limit]' ).val());
		 
		 if (data.json.cnt > offset + limit) {
			 hasmore = true;
		 } else {
			 hasmore = false;
		 }
		 
		 $.each(data.json.items, function(idx, row) {
			 rowsdata[idx] = row;
			 var dom = getrow(idx, row);
			 $('#items').append(dom);
		 });
		 
		 //$('table#items').css('min-height', h+'px');
	 },
	 'json');
}

function getrow(idx, row)
{
	var id;
	var dom;
	
	//id = row['id'];
	id = idx;
	
	dom = $('#rowtemplate').clone().attr('id', id);
	
	$.each(row, function(colname, colval) {
		
		// todo: why @ mark error?
		//if (colname.match(/\@/)) return;
		//if (colname == 'PictureDetails_PictureURL') return;
		
		$('.'+colname, dom).html(colval);
	});
	
	if (row.ListingDetails.ViewItemURL) {
		$('a.ItemID', dom).attr('href', row.ListingDetails.ViewItemURL);
	} else {
		$('a.ItemID', dom).attr('href', row.ListingDetails.ViewItemURL);
	}
	$('td.EndTime', dom).html(row.ext.endtime);
	$('td.price',   dom).html(row.ext.price);
	
	if (typeof(row.status) == 'string') {
		$('input:checkbox', dom).css('visibility', 'hidden');
		$('input:checkbox', dom).parent().addClass('loading');
	}
	$('input:checkbox', dom).val(id);
	
	/* Picture */
	var pictstr = '';
	if (typeof(row.PictureDetails.PictureURL) == 'string') {
		pictstr = row.PictureDetails.PictureURL;
	} else if (typeof(row.PictureDetails.PictureURL) == 'object') {
		pictstr = row.PictureDetails.PictureURL[0];
	} else if (typeof(row.PictureDetails.GalleryURL) == 'string') {
		pictstr = row.PictureDetails.GalleryURL;
	} else if (typeof(row.PictureDetails.GalleryURL) == 'object') {
		pictstr = row.PictureDetails.GalleryURL[0];
	}
	if (pictstr != '') {
		//$('img.PictureURL', dom).attr('src', pictstr);
		$('img.PictureURL', dom).attr('src', '/img/gray.png');
	} else {
		$('img.PictureURL', dom).remove();
	}
	
	/* Labels */
	if (typeof(row.ext) == 'object' && typeof(row.ext.labels) == 'object') {
		$.each(row.ext.labels, function(k, v) {
			$('div.labelwrap', dom).append($('<div>').attr('class', 'label').text(v));
		});
	}
	
	if (row['ext.status'] == 'listing') {
		$('input:checkbox', dom).css('visibility', 'hidden');
		$('input:checkbox', dom).parent().addClass('loading');
	}
	$('input:checkbox', dom).val(id);
	
	$('td.UserID', dom).html(row.ext.UserID);
	
	/* status icon */
	var src = '/icon/04/10/10.png';
	if (row.ext.SellingStatus) {
		if (row.ext.SellingStatus.ListingStatus == 'Active') {
			src = '/icon/04/10/02.png';
		} else if (row.ext.SellingStatus.ListingStatus == 'Completed') {
			src = '/icon/04/10/10.png';
		}
	}
	$('img.status', dom).attr('src', src);
	
	if (row.ext.errors) {
		$.each(row.ext.errors, function(k, v) {
			if (v != '') {
				$('a.Title', dom).after('<span class="error">'+v.LongMessage+'</span>');
				$('a.Title', dom).after('<br>');
			}
		});
	}
	
	return dom;
	
	
	$('a.Title', dom)
		.prepend('['+row['ShippingDetails_ShippingType']+']')
		.prepend('['+row['Site']+']');
	if (row['SellingStatus_QuantitySold'] > 0) {
		sldd = $('<div/>')
			.css('float', 'right')
			.css('position', 'absolute')
			.css('background-color', 'yellow')
			.css('color', 'red')
			.html(row['SellingStatus_QuantitySold']+' sold!');
		$('a.Title', dom).parent().prepend(sldd);
	}
	
	if (row['schedule']) {
		$('td.ListingDetails_EndTime', dom).html('<img src="/icon/02/10/03.png"> '+row['schedule']);
	}
	
	return dom;
}

// todo: consider about how to generate strings for display
// todo: if build a clone of python version, it's easy if generation is in javascript.
function getdetail(row)
{
	var id = row.id;
	var detail = $('div.detail', '#'+id);
	var site = row.Site;
	
	/* Country */
	$.each(hash[site].eBayDetails.CountryDetails, function(k, v) {
		var optiontag = $('<option/>').val(v.Country).text(v.Description);
		$('select[name=Country]', '#'+id).append(optiontag);
	});
	
	
	/* Currency */
	$.each(hash[site].eBayDetails.CurrencyDetails, function(k, v) {
		var optiontag = $('<option/>').val(v.Currency).text(v.Description);
		$('select[name=Currency]', '#'+id).append(optiontag);
	});
	
	
	/* Categories */
	var tmppath = row.ext.categorypath.slice(0);
	tmppath.unshift(0);
	var tmppds = getcategorypulldowns(site, tmppath);
	$('select[name="PrimaryCategory.CategoryID"]', detail).parent().html(tmppds);
	
	var tmppc = hash[site].Categories['c'+row.ext.categorypath[row.ext.categorypath.length-2]];
	var category = tmppc['c'+row.PrimaryCategory.CategoryID];
	
	
	/* Condition */
	var conditions = category.CategoryFeatures.ConditionValues.Condition;
	for (i in conditions) {
		var value = conditions[i].ID;
		var label = conditions[i].DisplayName;
		var optiontag = $('<option/>').val(value).html(label);
		$('select[name=ConditionID]', detail).append(optiontag);
	}
	
	
	/* Category2CS */
	if (category.Category2CS && category.Category2CS.CatalogEnabled) {
		// todo: find more than 2 Sets.
		$('input[name="ProductSearch.CharacteristicSetIDs.ID"]', detail)
			.val(category.Category2CS.CharacteristicsSets.AttributeSetID);
	} else {
		$('div.productsearchform', detail).hide();
	}
	
	setItemSpecificsForms(row);
	
	$('form[name=APIForm]', detail)
		.attr('id',   'APIForm'+id)
		.attr('name', 'APIForm'+id);
	
	
	/* ListingDuration */
	var durationsetid = null;
	for (i in category.CategoryFeatures.ListingDuration) {
		if (category.CategoryFeatures.ListingDuration[i]['@type'] == row.ListingType) {
			durationsetid = category.CategoryFeatures.ListingDuration[i]['#text'];
			break;
		}
	}
	var listingdurations =
		hash[site].CategoryFeatures.FeatureDefinitions.ListingDurations.ListingDuration;
	for (i in listingdurations) {
		if (listingdurations[i]['@durationSetID'] == durationsetid) {
			for (j in listingdurations[i].Duration) {
				var value = listingdurations[i].Duration[j];
				var optiontag = $('<option/>').val(value).html(value);
				$('select[name=ListingDuration]', detail).append(optiontag);
			}
			break;
		}
	}
	
	
	/* ShippingService */
	var dmstselect = $('<select/>').append($('<option/>'));
	var intlselect = $('<select/>').append($('<option/>'));
	
	$.each(hash[site].eBayDetails.ShippingServiceDetails, function(i, o) {
		if (o.ValidForSellingFlow != 'true') return;
		
		var dmst = row.ext.shippingtype.domestic;
		var intl = row.ext.shippingtype.international;
		
		if (parseInt(o.ShippingServiceID) < 50000) {
			
			if ($.inArray(dmst, o.ServiceType) >= 0 || o.ServiceType == dmst) {
				var optiontag = $('<option/>').val(o.ShippingService).html(o.Description);
				$(dmstselect).append(optiontag);
			}
			
		} else {
			
			if ($.inArray(intl, o.ServiceType) >= 0 || o.ServiceType == intl) {
				var optiontag = $('<option/>').val(o.ShippingService).html(o.Description);
				$(intlselect).append(optiontag);
			}
			
		}
	});
	
	var _dsso = 'ShippingDetails.ShippingServiceOptions';
	var _isso = 'ShippingDetails.InternationalShippingServiceOption';
	$('select[name="'+_dsso+'.0.ShippingService"]', '#'+id).html(dmstselect.html());
	$('select[name="'+_dsso+'.1.ShippingService"]', '#'+id).html(dmstselect.html());
	$('select[name="'+_dsso+'.2.ShippingService"]', '#'+id).html(dmstselect.html());
	$('select[name="'+_dsso+'.3.ShippingService"]', '#'+id).html(dmstselect.html());
	$('select[name="'+_isso+'.0.ShippingService"]', '#'+id).html(intlselect.html());
	$('select[name="'+_isso+'.1.ShippingService"]', '#'+id).html(intlselect.html());
	$('select[name="'+_isso+'.2.ShippingService"]', '#'+id).html(intlselect.html());
	$('select[name="'+_isso+'.3.ShippingService"]', '#'+id).html(intlselect.html());
	$('select[name="'+_isso+'.4.ShippingService"]', '#'+id).html(intlselect.html());
	
	
	return;
	
	getshippingservice(id)
	
	/* description */
	// todo: check html5 srcdoc attribute
	iframe = $('<iframe/>')
		.attr('class', 'description')
		.attr('src', 'about:blank');
	
	iframe.load(function() {
		$(this).contents().find('body').append(row.Description);
	});
	$('textarea[name=Description]', detail).replaceWith(iframe);
	
	return;
	
	/* paymentmethods */
	var pmstr = '<span style="color:#aaaaaa;">-</span>';
	if (typeof(row.PaymentMethods) == 'string') {
		pmstr = row.PaymentMethods;
	} else if (typeof(row.PaymentMethods) == 'object') {
		pmstr = row.PaymentMethods.join('<br>');
	}
	pmstr = pmstr.replace(/PayPal/, 'PayPal ('+row.PayPalEmailAddress+')');
	$('td.paymentmethod', detail).html(pmstr);
	
	$('select[name=ListingDuration]', detail)
		.replaceWith(getListingDurationLabel(row.ListingDuration));
	
	/* pictures */
	if (typeof(row.PictureDetails.PictureURL) == 'string') {
		$('img.PD_PURL_0', detail).attr('src', row.PictureDetails.PictureURL);
	} else if (typeof(row.PictureDetails.PictureURL) == 'object') {
		$.each(row.PictureDetails.PictureURL, function(i, url) {
			if (url == '') return;
			$('img.PD_PURL_'+i, detail).attr('src', url);
		});
	}
	
	// todo: arrayize if size = 1
	if (row.ItemSpecifics) {
		$.each(row.ItemSpecifics.NameValueList, function(k, v) {
			$('td.ItemSpecifics', detail).append(v.Name+' : '+v.Value+'<br />');
		});
	}
	
	/* shippingtype */
	if (row.ext.shippingtype) {
		$('td.shippingtype_domestic', detail).html(row.ext.shippingtype.domestic);
		$('td.shippingtype_international', detail).html(row.ext.shippingtype.international);
	}
	
	
	if (row.ShippingDetails.CalculatedShippingRate) {
		
		sdcsr = row.ShippingDetails.CalculatedShippingRate;
		_sdcsr = 'ShippingDetails.CalculatedShippingRate';
		
		dspv(row,
			 _sdcsr+'.ShippingPackage',
			 hash[row.Site]['ShippingPackageDetails'][sdcsr.ShippingPackage]['Description']);
		
		//if (csro.ShippingIrregular == 'true') sp += ' (Irregular package)';
		
		dsp(row, _sdcsr+'.PackageLength.#text');
		dsp(row, _sdcsr+'.PackageLength.@unit');
		dsp(row, _sdcsr+'.PackageLength.@measurementSystem');
		dsp(row, _sdcsr+'.PackageWidth.#text');
		dsp(row, _sdcsr+'.PackageWidth.@unit');
		dsp(row, _sdcsr+'.PackageWidth.@measurementSystem');
		dsp(row, _sdcsr+'.PackageDepth.#text');
		dsp(row, _sdcsr+'.PackageDepth.@unit');
		dsp(row, _sdcsr+'.PackageDepth.@measurementSystem');
		dsp(row, _sdcsr+'.WeightMajor.#text');
		dsp(row, _sdcsr+'.WeightMajor.@unit');
		dsp(row, _sdcsr+'.WeightMajor.@measurementSystem');
		dsp(row, _sdcsr+'.WeightMinor.#text');
		dsp(row, _sdcsr+'.WeightMinor.@unit');
		dsp(row, _sdcsr+'.WeightMinor.@measurementSystem');
	}
	
	dspv(row, 'DispatchTimeMax', hash[row.Site]['DispatchTimeMaxDetails'][row.DispatchTimeMax]);
	
	$('select, input', detail).replaceWith('<span style="color:#aaaaaa;">-</span>');
	
	
	
	return;
	
	

	/* preserve selected tab */
	/*
	tab = $('ul.tabNav > li.current > a', $('tbody#'+id));
	tabnum = tab.parent().prevAll().length + 1;
	$('.tabNav', detail).children('li:nth-child('+tabnum+')').addClass('current');
	$('.tabContainer', detail).children('div:nth-child('+tabnum+')').show();
	$('.tabContainer', detail).children('div:nth-child('+tabnum+')').addClass('current');
	*/
	
	if (row['PictureDetails_PictureURL']) {
		$.each(row['PictureDetails_PictureURL'], function(i, url) {
			$('img.PD_PURL_'+i, detail).attr('src', url);
		});
	}
	
	/* listingtype */
	
	$('input:file', detail).remove();
	
	/* duration */
	var ldstr = getListingDurationLabel(row['ListingDuration']);
	$('td.duration', detail).text(ldstr);
	
	/* shippingservice */
	//dump(hash[row['Site']]['ShippingType']);
	if (row['ShippingDetails_ShippingType']) {
		dmstmap = hash['shippingmap'][row['ShippingDetails_ShippingType']]['domestic'];
		intlmap = hash['shippingmap'][row['ShippingDetails_ShippingType']]['international'];
		dmst = hash[row['Site']]['ShippingType']['domestic'][dmstmap];
		intl = hash[row['Site']]['ShippingType']['international'][intlmap];
		$('td.shippingtype_domestic', detail).html(dmst);
		$('td.shippingtype_international', detail).html(intl);
	}
	
	if (row['ShippingDetails_ShippingServiceOptions']) {
		ssstr = '';
		$.each(row['ShippingDetails_ShippingServiceOptions'], function(i, o) {
			ssstr += hash[row['Site']]['ShippingServiceDetails'][o['ShippingService']+'']['Description'];
			if (o['ShippingServiceCost']) {
				ssstr += ' '+o['ShippingServiceCost@currencyID']+o['ShippingServiceCost'];
			}
			ssstr += '<br>';
		});
		$('td.shippingservice', detail).html(ssstr);
	}	
	if (row['ShippingDetails_InternationalShippingServiceOption']) {
		ssstr = '';
		$.each(row['ShippingDetails_InternationalShippingServiceOption'], function(i, o) {
			ssstr += hash[row['Site']]['ShippingServiceDetails'][o['ShippingService']+'']['Description'];
			if (o['ShippingServiceCost']) {
				ssstr += ' '+o['ShippingServiceCost@currencyID']+o['ShippingServiceCost'];
			}
			ssstr += '<br>';
		});
		$('td.intlshippingservice', detail).html(ssstr);
	}	
	
	
	$.each(row, function(colname, colval) {
		$('input[name='+colname+']', detail).replaceWith($('<div>'+colval+'</div>'));
	});
	
	return;
}

function resizediv()
{
	var w = $('div#container').width()-225;
	var h = $('body').height() - 10;
	
	$('div#content').width(w);
	$('div#header').width($('div#container').width()-40);
	//$('div#debug').width(w-20);
	//$('div#toolbar').height(h);
	$('table#items').width(w);
	$('a.Title').parent().width(w-600);
	//$('div.tabContainer').width(w-80);
	$('div#toolbar').height($(window).height()-99);
	
	return;
}

var changeCategory = function() {
	
	var id = $(this).closest('tbody.itemrow').attr('id');
	var site = $('select[name=Site]', '#'+id).val();
	
	$(this).nextAll().remove();
	$('select.category:last', '#'+id).attr('name', 'PrimaryCategory.CategoryID');
	
	var prevslct = $('select.category', '#'+id).get();
	
	var path = new Array();
	for (node in prevslct) {
		path.push(prevslct[node].value);
	}
	path.unshift(0);
	
	$.getJSON
	('/json/gc2?site='+site+'&path='+path.join('.'),
	 function(data) {
		 
		 hash[site]['Categories'] = data.json.gc2.Categories;
		 
		 var tmppds = getcategorypulldowns(site, path);
		 $('select[name="PrimaryCategory.CategoryID"]', '#'+id).parent().html(tmppds);
		 
		 var category =
			 hash[site]['Categories']['c'+path[path.length-2]]['c'+path[path.length-1]];
		 if (category.Category2CS && category.Category2CS.CatalogEnabled) {
			 
			 // todo: what is "ProductSearchPageAvailable" ?
			 
			 $('span.CharacteristicsSetsName', '#'+id)
				 .html(category.Category2CS.CharacteristicsSets.Name);
			 
			 $('input[name="ProductSearch.CharacteristicSetIDs.ID"]', '#'+id)
				 .val(category.Category2CS.CharacteristicsSets.AttributeSetID);
			 
			 $('div.productsearchform', '#'+id).show();
			 
		 } else {
			 $('div.productsearchform', '#'+id).hide();
		 }
	 });
	
	return;
}

var clickEdit = function() {
	
	id = $(this).closest('tbody.itemrow').attr('id');
	item = rowsdata[id];
	dom = $('div.detail', 'div#detailtemplate').clone().css('display', 'block');
	
	$('div.detail', 'tbody#'+id).replaceWith(dom);
	
	getdetail(item);
	fillformvalues(item);
	
	showbuttons(dom, 'save,cancel');

	
	return false;
	
	/* preserve selected tab */
	tab = $('ul.tabNav > li.current > a', $('tbody#'+id));
	tabnum = tab.parent().prevAll().length + 1;
	$('.tabNav',       dom).children('.current').removeClass('current');
	$('.tabContainer', dom).children('.current').hide();
	$('.tabContainer', dom).children('.current').removeClass('current');
	$('.tabNav',       dom).children('li:nth-child('+tabnum+')').addClass('current');
	$('.tabContainer', dom).children('div:nth-child('+tabnum+')').show();
	$('.tabContainer', dom).children('div:nth-child('+tabnum+')').addClass('current');
	
	/* Dimensions */
	_sdcsr = 'ShippingDetails.CalculatedShippingRate';
	
	if (item.Description != null) {
		$('textarea[name=Description]',   dom).val(item.Description);
	}
	
	showbuttons(dom, 'save,cancel');
	$('div.detail', 'tbody#'+id).replaceWith(dom);
	$('input[name=Title]', 'tbody#'+id).focus();
	
	// todo: compare to CKEditor
	//$('textarea[name=Description]', 'tbody#'+id).wysiwyg();
	
	/* pictures */
	for (i=0; i<=11; i++) {
		$('img.PD_PURL_'+i, dom).attr('id', 'PD_PURL_'+id+'_'+i);
	}
	if (typeof(item.PictureDetails.PictureURL) == 'string') {
		$('img.PD_PURL_0', dom).attr('src', item.PictureDetails.PictureURL);
		$('input[name="PictureDetails.PictureURL.0"]', dom).val(item.PictureDetails.PictureURL);
	} else if (typeof(item.PictureDetails.PictureURL) == 'object') {
		$.each(item.PictureDetails.PictureURL, function(i, url) {
			if (url == '') return;
			$('img.PD_PURL_'+i, dom).attr('src', url);
			$('input[name="PictureDetails.PictureURL.'+i+'"]', dom).val(url);
		});
	} 
	
	site = item.Site;
	categoryid = item.PrimaryCategory.CategoryID;
	
	/* Country */
	setoptiontags('Country',
				  hash[site]['CountryDetails'],
				  item.Country);
	
	setoptiontags('Currency',
				  hash[site]['CurrencyDetails'],
				  item.Currency);
	
	/* Handling time */
	setoptiontags('DispatchTimeMax',
				  hash[site]['DispatchTimeMaxDetails'],
				  item.DispatchTimeMax);
	
	tmpgc = hash[site]['Categories'];
	$.each(item.ext.categorypath, function(i, cid) {
		if (i == 0) {
			tmpgc = tmpgc['c'+cid];
		} else {
			tmpgc = tmpgc['children']['c'+cid];
		}
		
		if (i == item.ext.categorypath.length - 1) {
			
			$.each(tmpgc.CategoryFeatures.ConditionValues.Condition, function(j, o) {
				var option = $('<option/>').val(o.ID).text(o.DisplayName);
				if (o.ID == item.ConditionID) option.attr('selected', 'selected');
				$('select[name=ConditionID]', dom).append(option);
			});
			
			$.each(tmpgc.CategorySpecifics.NameRecommendation, function(j, o) {
				$('td.ItemSpecifics', dom).append(o.Name);
				if (o.ValueRecommendation) {
					$.each(o.ValueRecommendation, function(k, v) {
						$('td.ItemSpecifics', dom).append(v.Value+', ');
					});
				}
				$('td.ItemSpecifics', dom).append('<br />');
			});
			$('td.ItemSpecifics', dom).append
			('<pre>'+$.dump(tmpgc.CategorySpecifics.NameRecommendation)+'</pre>');
		}
	});
	
	/* Listing duration */
	setoptiontags
	('ListingDuration',
	 hash[site]['category']['features'][categoryid]['ListingDuration'][item.ListingType],
	 item.DispatchTimeMax);

	setoptiontags
	('ConditionID',
	 hash[site]['category']['features'][categoryid]['ConditionValues']['Condition'],
	 item.ConditionID);
	
	if (false) {
		/* pictures */
		for (i=0; i<=11; i++) {
			$('input:file[name=PD_PURL_'+i+']', dom).attr('name', 'PD_PURL_'+id+'_'+i);
			$('img.PD_PURL_'+i,                 dom).attr('id',   'PD_PURL_'+id+'_'+i);
		}
		
		$.each(rowsdata[id], function(colname, colval) {
			$('input:text[name='+colname+']', dom).val(colval+'');
		});
	}		
	
	/* payment method */
	tmpo = hash[site]['category']['features'][categoryid]['PaymentMethod'];
	i=0;
	$.each(tmpo, function(k, v) {
		//chk = $('<input/>').attr('name', 'PaymentMethods.'+i).attr('type', 'checkbox').val(v);
		chk = $('<input/>').attr('name', 'PaymentMethods').attr('type', 'checkbox').val(v);
		if (rowsdata[id]['PaymentMethods'].indexOf(v) >= 0) {
			chk.attr('checked', 'checked');
		}
		$('td.paymentmethod', dom).append(chk);
		$('td.paymentmethod', dom).append(v);
		if (v == 'PayPal') {
			input = $('<input />')
				.attr('type', 'text')
				.attr('name', 'PayPalEmailAddress')
				.val(rowsdata[id]['PayPalEmailAddress']);
			$('td.paymentmethod', dom).append(input);
		}
		$('td.paymentmethod', dom).append('<br />');
		i++;
	});
	
	//$('td.shippingservice', '#'+id).append(getshippingservice(id));
	
	/* ShippingDetails */
	sdsso = item.ShippingDetails.ShippingServiceOptions;
	_sdsso = 'ShippingDetails.ShippingServiceOptions';
	getshippingservice(id)
	if ($.isArray(sdsso)) {
		
		$.each(sdsso, function(i, o) {
			$('select[name="'+_sdsso+'.'+i+'.ShippingService"]', dom).val(o.ShippingService);
			if (o.ShippingServiceCost != null) {
				$('input[name="'+_sdsso+'.'+i+'.ShippingServiceCost.@currencyID"]', dom)
					.val(o.ShippingServiceCost['@currencyID']);
				
				$('input[name="'+_sdsso+'.'+i+'.ShippingServiceCost.#text"]', dom)
					.val(o.ShippingServiceCost['#text']);
			}
		});
		
	} else {
		
		$('select[name="'+_sdsso+'.0.ShippingService"]', dom).val(sdsso.ShippingService);
		if (sdsso.ShippingServiceCost != null) {
			$('input[name="'+_sdsso+'.0.ShippingServiceCost.@currencyID"]', dom)
				.val(sdsso.ShippingServiceCost['@currencyID']);
			
			$('input[name="'+_sdsso+'.0.ShippingServiceCost.#text"]', dom)
				.val(sdsso.ShippingServiceCost['#text']);
		}
	}
	
	/* ItemSpecifics */
	if (item.ItemSpecifics) {
		$.each(item.ItemSpecifics.NameValueList, function(k, v) {
			inputname = $('<input/>')
				.attr('name', 'ItemSpecifics.NameValueList.'+k+'.Name')
				.attr('type', 'text')
				.val(v.Name);
			$('td.ItemSpecifics', dom).append(inputname);
			$('td.ItemSpecifics', dom).append(' : ');
			
			$.each(arrayize(v.Value), function(i, f) {
				inputvalue = $('<input/>')
					.attr('name', 'ItemSpecifics.NameValueList.'+k+'.Value')
					.attr('type', 'text')
					.val(f);
				$('td.ItemSpecifics', dom).append(inputvalue);
			});
			
			$('td.ItemSpecifics', dom).append('<br />');
		});
	}
	
	
	
	return false;
}

var clickSave = function() {
	
	id = $(this).closest('tbody.itemrow').attr('id');
	detail = $(this).closest('div.detail');
	
	// temp remove
	$('input.remove', detail).remove();
	$('select.remove', detail).remove();
	
	// todo: varidation check
	if ($('select[name="PrimaryCategory.CategoryID"]', detail).val() == '') {
		alert('category error.');
		return false;
	}
	
	$.each($('img.PictureDetails_PictureURL', detail), function(i, imgelm) {
		imgsrc = $(imgelm).attr('src');
		if (imgsrc == '/img/noimage.jpg') {
			$("input[name='PictureDetails[PictureURL]["+(i+1)+"]']", detail).remove();
		} else {
			$("input[name='PictureDetails[PictureURL]["+(i+1)+"]']", detail).val(imgsrc);
		}
	});
	
	// todo: Why Opera can't include <select> tags?
	// todo: Don't use numeric keys that causes "NCNames cannot start with...." error.
	
	// remove forms
	_sdsso = 'ShippingDetails.ShippingServiceOptions';
	for (i = 0; i <= 3; i++) {
		if ($('select[name="'+_sdsso+'.'+i+'.ShippingService"]').val() == '') {
			$('input[name="'+_sdsso+'.'+i+'.ShippingServicePriority"]').val('');
		}
	}
	
	_sdisso = 'ShippingDetails.InternationalShippingServiceOptions';
	for (i = 0; i <= 4; i++) {
		if ($('select[name="'+_sdisso+'.'+i+'.ShippingService"]').val() == '') {
			$('input[name="'+_sdisso+'.'+i+'.ShippingServicePriority"]').val('');
		}
	}
	
	postdata = $('input[type=text], input:checked, input[type=hidden], select, textarea',
				 $(this).closest('div.detail')).extractObject();
	
	$('#debug').append('<pre>'+$.dump(postdata)+'</pre>');
	
	return false;
	
	if (false) {
		var attrdata = $('input[name^=attr], select[name^=attr], input[name^=attr][checked]',
						 $(this).closest('div.detail')).extractAttrObject();
		var attributeset = {};
		attributeset['@attributeSetID'] = 99;
		attributeset.Attribute = attrdata;
		
		//postdata.AttributeSetArray = attributeset;
		dump(attrdata);
	
		//return false;
	}
	
	/*
	$.each(postdata.ShippingDetails.ShippingServiceOptions, function(k, v) {
		if (v.ShippingService == '') {
			msg('remove sso '+k);
		} else {
			msg('do not remove sso '+k);
		}
	});
	*/
	
	postdata = JSON.stringify(postdata);
	
	// todo: escape "&" character
	postdata = postdata.replace(/&/g, 'AND');
	
	
	$.post('/json/save',
		   'id='+id+'&json='+postdata,
		   function(data) {
			   rowsdata[id] = data.json.item;
			   dump(data.json);
			   getdetail(data.json.item);
			   showbuttons(detail, 'edit,copy,delete');
		   },
		   'json');
	
	return false;
}

var clickCancel = function() {
	
	id = $(this).closest('tbody.itemrow').attr('id');
	getdetail(rowsdata[id]);
	showformvalues(rowsdata[id]);
	showbuttons('#'+id, 'edit,copy,delete');
	
	return false;
}

var clickDelete = function() {
	return false;
}

var clickCopy = function() {
	return false;
}


var changeSite = function() {
	id = $(this).closest('tbody.itemrow').attr('id');
	site = $(this).val();
	
	sel = getcategorypulldown(site, 0);
	$('td.category', '#'+id).html(sel);
	
	if (!hash[site]['category']['grandchildren'][0]) preloadcategory(site, []);
	
	return;
}

var clickTitle = function() {
	
	var id = $(this).closest('tbody').attr('id');
	
	if ($('tr.row2 td', '#'+id).html().match(/^<div/i)) {
		$('div.detail', '#'+id).slideToggle('fast');
		return false;
	}
	
	detail = $('div.detail', 'div#detailtemplate').clone();
	$('td:nth-child(2)', detail).hide();
	$('tr.row2 td', '#'+id).html(detail);
	//$('div.detail', '#'+id).slideToggle('fast');
	
	$.post('/json/item',
		   'id='+id,
		   function(data) {
			   item = data.json.item;
			   rowsdata[id] = item;
			   dump(item);
			   
			   hash[item.Site] = new Object;
			   hash[item.Site].eBayDetails = data.json.eBayDetails;
			   hash[item.Site].Categories  = data.json.Categories;
			   hash[item.Site].CategoryFeatures = data.json.CategoryFeatures;
			   
			   getdetail(item);
			   showformvalues(item);
			   $('div.productsearchform', '#'+id).remove();
			   
			   //$('td:nth-child(2)', '#'+id).fadeIn('fast');
			   $('td:nth-child(2)', '#'+id).show();
			   $('div.detail', '#'+id).show();
			   
			   $('div.pictures', '#'+id).append('<pre>'+$.dump(item.PictureDetails)+'</pre>');

			   //$.scrollTo('#'+id, {axis:'y', offset:0});
		   },
		   'json');
	
	return false;
}

function getcategorypulldown(site, categoryid)
{
	sel = $('<select class="category"/>');
	opt = $('<option/>').val('').text('');
	sel.append(opt);
	$.each(hash[site]['category']['children'][categoryid], function(i, childid) {
		str = hash[site]['category']['name'][childid];
		str += '('+childid+')';
		if (hash[site]['category']['children'][childid] != 'leaf') str += ' &gt;';
		opt = $('<option/>').val(childid).html('old|'+str);
		sel.append(opt);
	});
	
	return sel;
}

function getcategorypulldown2(site, path)
{
	ctgr = hash[site]['Categories'];
	for (i in path) {
		if (i == 0) {
			ctgr = ctgr['c'+path[i]];
		} else {
			ctgr = ctgr['children']['c'+path[i]];
		}
	}
	
	sel = $('<select class="category"/>');
	opt = $('<option/>').val('').text('');
	sel.append(opt);
	
	$.each(ctgr['children'], function(i, o) {
		str = o.CategoryName;
		cid = i.replace(/^c/, '');
		if (o.children) str += ' &gt;';
		opt = $('<option/>').val(cid).html(str);
		sel.append(opt);
	});
	
	return sel;
}

function getcategorypulldowns(site, path)
{
	wrapper = $('<div/>');
	
	for (i in path) {
		
		var categoryid = path[i];
		if (hash[site]['Categories']['c'+categoryid] == undefined) break;
		
		var selecttag = $('<select class="category"/>').attr('name', 'ext.categorypath.'+i);
		var optiontag = $('<option/>').val('').text('');
		selecttag.append(optiontag);		
		
		for (childid in hash[site]['Categories']['c'+categoryid]) {
			var child = hash[site]['Categories']['c'+categoryid][childid];
			var value = childid.replace(/^c/, '');
			var label = child.name;
			if (child.children > 0) label += ' &gt;';
			optiontag = $('<option/>').val(value).html(label);
			selecttag.append(optiontag);		
		}
		
		wrapper.append(selecttag);
	}
	
	$.each($('select', wrapper), function(i, form) {
		$(form).val(path[i+1]);
	});
	
	$('select.category:last', wrapper).attr('name', 'PrimaryCategory.CategoryID');
	
	return wrapper.children();
}

function preloadcategory2(site, path)
{
	
	$.getJSON('/json/gc2?site='+site+'&path='+path.join('.'),
			  function(data) {
				  dump(data);
				  hash[site]['Categories']['c'+path[0]]['children'] = data.json.gc2;
			  });
	
	return;
}

function preloadcategory(site, path)
{
	msg('pre:'+path);
	var npath = new Array();
	
	if (!hash[site]['category']['grandchildren'][0]) npath.push(0);
	
	$.each(path, function(i, categoryid) {
		if (hash[site]['category']['grandchildren'][categoryid]) return;
		npath.push(categoryid);
	});
	
	$.getJSON('/json/grandchildren?site='+site+'&pathstr='+npath.join('.'),
			  function(data) {
				  $.each(hash[site]['category'], function(n, a) {
					  var tmpo = $.extend({}, hash[site]['category'][n], data.json[n]);
					  hash[site]['category'][n] = tmpo;
				  });
			  });
	
	return;
}

function preloadcategoryfeatures(site, categoryid)
{
	if (hash[site]['category']['features'][categoryid]) return;

	$.ajax({
		url: '/json/categoryfeatures?site='+site+'&categoryid='+categoryid,
		async: false,
		success: function(data) {
			//dump(data);
			var tmpo = $.extend({}, hash[site]['category']['features'], data.json.features);
			hash[site]['category']['features'] = tmpo;
		}
	});
	
	/*
	$.getJSON('/json/categoryfeatures?site='+site+'&categoryid='+categoryid,
			  function(data) {
				  dump(data);
				  var tmpo = $.extend({}, hash[site]['category']['features'], data.json.features);
				  hash[site]['category']['features'] = tmpo;
			  });
	*/
	
}

function preloadshippingtype(site)
{
	if (hash[site]['ShippingType']) return;
	
	$.getJSON('/json/getShippingType/'+site,
			  function(data) {
				  hash[site]['ShippingType'] = data;
			  });
}

function refresh()
{
	loadings = $('td.loading');
	if (loadings.length <= 0) return;
	
	// todo: check firefox pseudo class .... warning
	loadings = $('td.loading > input:checkbox[name=id][value!=on]');
	dump(loadings);
	
	$.post('/json/items',
		   loadings.serialize(),
		   function(data) {
			   dump(data.json);
			   $.each(data.json.items, function(idx, row) {
				   dom = getrow(idx, row);
				   if (row.ext.status == '') {
					   //$('input:checkbox', dom).css('visibility', '').attr('checked', '');
					   $('input:checkbox', dom).css('visibility', '').removeAttr('checked');
					   $('input:checkbox', dom).parent().removeClass('loading');
					   $('tbody#'+idx).replaceWith(dom);
				   }
				   rowsdata[idx] = row;
			   });
		   },
		   'json');
	
	return;
}

function filter()
{
	$('input[name=offset]').val(0);
	items();
	return;
}

function showbuttons(detail, buttons)
{
	buttons = 'button.'+buttons.replace(/,/g, ',button.');
	
	ulbtn = $('div.editbuttons', detail);
	$('div.editbuttons button', detail).hide();
	$(buttons, ulbtn).show();
	
	return;
}

function msg(o)
{
	$('div#msg').prepend(o+'<br>');
}

function dump(o)
{
	$('div#debug').html('<pre>'+$.dump(o)+'</pre>');
}

function updateduration(id)
{
	site = $('select[name=Site]', '#'+id).val();
	listingtype = $('select[name=ListingType]', '#'+id).val();
	tmpo = hash[site]['category']['features'][categoryid]['ListingDuration'];
	
	sel = $('<select/>').attr('name', 'ListingDuration');
	$.each(rowsdata[id]['categoryfeatures']['ListingDuration'][listingtype], function(k, v) {
		opt = $('<option/>').val(k).text(v);
		sel.append(opt);
	});
	$('select[name=ListingDuration]', '#'+id).replaceWith(sel);
	
	return;
}

function getshippingservice(id)
{
	var site = $('select[name=Site]', '#'+id).val();
	var type = $('select[name="ext.shippingtype.domestic"]', '#'+id).val();
	var intltype = $('select[name="ext.shippingtype.international"]', '#'+id).val();
	
	var select = $('<select/>').append($('<option/>'));
	var intl = $('<select/>').append($('<option/>'));
	$.each(hash[site]['ShippingServiceDetails'], function(i, o) {
		if (o['ValidForSellingFlow'] != 'true') return;
		
		if (o['ShippingServiceID'] < 50000) {
			if ($.inArray(type, o['ServiceType']) >= 0 || o['ServiceType'] == type) {
				$('<option/>').val(o['ShippingService']).html(o['Description']).appendTo(select);
			}
		} else {
			if ($.inArray(type, o['ServiceType']) >= 0 || o['ServiceType'] == intltype) {
				$('<option/>').val(o['ShippingService']).html(o['Description']).appendTo(intl);
			}
		}
		
	});
	
	var _sdsso = 'ShippingDetails.ShippingServiceOptions';
	var _sdisso = 'ShippingDetails.InternationalShippingServiceOptions';
	
	$('select[name="'+_sdsso+'.0.ShippingService"]', '#'+id).html(select.html());
	$('select[name="'+_sdsso+'.1.ShippingService"]', '#'+id).html(select.html());
	$('select[name="'+_sdsso+'.2.ShippingService"]', '#'+id).html(select.html());
	$('select[name="'+_sdsso+'.3.ShippingService"]', '#'+id).html(select.html());

	$('select[name="'+_sdisso+'.0.ShippingService"]', '#'+id).html(intl.html());
	$('select[name="'+_sdisso+'.1.ShippingService"]', '#'+id).html(intl.html());
	$('select[name="'+_sdisso+'.2.ShippingService"]', '#'+id).html(intl.html());
	$('select[name="'+_sdisso+'.3.ShippingService"]', '#'+id).html(intl.html());
	$('select[name="'+_sdisso+'.4.ShippingService"]', '#'+id).html(intl.html());
	
	return;
}

function getListingDurationLabel(str)
{
	if (str == 'Days_1') {
		str = "1 Day";
	} else if (str == 'GTC') {
		str = "Good 'Til Cancelled";
	} else if (str.match(/^Days_([\d]+)$/)) {
		str = str.replace(/^Days_([\d]+)$/, "$1 Days");
	}
	return str;
}

function setoptiontags(dom, optionvalues, selectedvalue)
{
	$.each(optionvalues, function(k, v) {
		option = $('<option/>').val(k).text(v);
		if (selectedvalue == k) option.attr('selected', 'selected');
		$(dom).append(option);
	});
	
	return;
}

function arrayize(object)
{
	if ($.isArray(object)) {
		result = object;
	} else {
		result = new Array();
		result.push(object);
	}
	
	return result;
}

function dsp(item, str)
{
	jstr = "['"+str.replace(/\./g, "']['")+"']";
	try {
		eval("val = item"+jstr);
		$('input[name="'+str+'"]',  'tbody#'+item.id).replaceWith(val);
		$('select[name="'+str+'"]', 'tbody#'+item.id).replaceWith(val);
	} catch (err) {
		msg(err.description);
	}
}

function dspv(item, str, val)
{
	$('input[name="'+str+'"]',  'tbody#'+item.id).replaceWith(val);
	$('select[name="'+str+'"]', 'tbody#'+item.id).replaceWith(val);
}

function fval(dom, item, str)
{
	jstr = "['"+str.replace(/\./g, "']['")+"']";
	try {
		eval("val = item"+jstr);
		$('input[name="'+str+'"]',  dom).val(val);
		$('select[name="'+str+'"]', dom).val(val);
	} catch (err) {
		msg(err.description);
	}
}

function showformvalues(item)
{
	item.PictureDetails.PictureURL = arrayize(item.PictureDetails.PictureURL);
	
	var detail = $('div.detail', '#'+item.id);
	
	/* text */
	$.each($('input[type=text], input[type=hidden]', detail), function(i, form) {
		var formname = $(form).attr('name');
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		try {
			eval("tmpvalue = item"+formname);
			//var tmpvalue = item[formname];
			
			if (tmpvalue == null) tmpvalue = '';
			
			var htmlencoded = $('<div/>').text(tmpvalue+'[T]').html();
			$(form).replaceWith(htmlencoded);

			if ($(form).attr('name').match(/^PictureDetails.PictureURL./)) {
				var imgclass = $(form).attr('name')
					.replace(/^PictureDetails.PictureURL./, 'PD_PURL_');
				$('img.'+imgclass, detail).attr('src', tmpvalue);
			}
		} catch (err) {
			$(form).replaceWith('[E]');
			//$(detail).prepend('ERR: ['+formname+']'+err.description+'<br />');
		}
	});
	
	/* select */
	$.each($('select', detail), function(i, form) {
		var formname = $(form).attr('name');
		if (formname == null) return;
		
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		try {
			eval("var tmpvalue = item"+formname);
			//var tmpvalue = item[formname];
			if (tmpvalue == null) tmpvalue = '';
			var label = $('option[value='+tmpvalue+']', form).html();
			$(form).replaceWith(label+'[S]');
		} catch (err) {
			$(form).replaceWith('[E]');
			//$(detail).before('ERR: '+formname+' '+err+'<br />');
		}
	});

	/* checkbox */
	$.each($('input[type=checkbox]', detail), function(i, form) {
		var formname = $(form).attr('name');
		if (formname == null) return;
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		try {
			eval("var tmpvalue = item"+formname);
			
			if (typeof(tmpvalue) == 'object') {
				for (i in tmpvalue) {
					if (tmpvalue[i] == $(form).val()) {
						$(form).replaceWith('[C]');
					}
				}
			}
			
		} catch (err) {
			$(form).replaceWith('[E]');
			//$(detail).before('ERR: '+formname+' '+err+'<br />');
		}
	});
	$.each($('input[type=checkbox]', detail), function(i, form) {
		var idforlabel = $(form).attr('id');
		$(form).remove();
		$('label[for="'+idforlabel+'"]').remove();
	});
		   
	return;
}

function fillformvalues(item)
{
	var detail = $('div.detail', '#'+item.id);
	
	$.each($('input[type=text], input[type=hidden], select, textarea', detail), function(i, form) {
		var formname = $(form).attr('name');
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		
		try {
			eval("var tmpvalue = item"+formname);
			$(form).val(tmpvalue);
			
			if ($(form).attr('name').match(/^PictureDetails.PictureURL./)) {
				var imgclass = $(form).attr('name')
					.replace(/^PictureDetails.PictureURL./, 'PD_PURL_');
				$('img.'+imgclass, detail).attr('src', tmpvalue);
			}
			
		} catch (err) {
			//$(detail).prepend('ERR: '+err.description+'<br />');
		}
	});
	
	// todo: checkbox forms
	
	return;
}

function setItemSpecificsForms(item)
{
	if (item.ItemSpecifics == undefined) return;
	
	var detail = $('div.detail', '#'+item.id);
	
	var categoryid = item.PrimaryCategory.CategoryID;
	var parentid = item.ext.categorypath[item.ext.categorypath.length-2];
	var category = hash[item.Site]['Categories']['c'+parentid]['c'+categoryid];
	
	var recommkey = new Array();
	var specificskey = new Array();
	var specifics = item.ItemSpecifics.NameValueList;
	var recomm = category.CategorySpecifics.NameRecommendation;
	
	for (i in recomm) {
		recommkey[recomm[i].Name] = i;
	}
	
	/* Existing specifics */
	for (i in specifics) {
		if (specifics[i] == null) continue;
		
		var recommref = recomm[recommkey[specifics[i].Name]];
		specificskey[specifics[i].Name] = i;
		
		var trtag = $('<tr />');
		var tdtag = $('<td />');
		
		var inputtag = $('<input />')
			.attr('type', 'text')
			.attr('Name', 'ItemSpecifics.NameValueList.'+i+'.Name');
		
		$(tdtag).append(inputtag);
		$(trtag).append(tdtag);
		
		var tdtag = setItemSpecificsFormValue(item, i, recommref, specifics);
		//$(tdtag).append('<pre>'+$.dump(specifics[i])+'</pre>');
		
		$(trtag).append(tdtag);
		$('table.ItemSpecifics', detail).append(trtag);
	}
	
	/* Remaining specifics */
	/*
	var addspidx = specifics.length;
	for (i in recomm) {
		if (specificskey[recomm[i].Name] != null) continue;
		
		var trtag = $('<tr />');
		var tdtag = $('<td />');
		
		var inputtag = $('<input />')
			.attr('type', 'hidden')
			.attr('Name', 'ItemSpecifics.NameValueList.'+addspidx+'.Name')
			.val(recomm[i].Name);
		
		$(tdtag).append(inputtag);
		$(tdtag).append(recomm[i].Name);
		$(trtag).append(tdtag);
		
		var tdtag = setItemSpecificsFormValue(recomm[i], specifics);
		
		$(trtag).append(tdtag);
		$('table.ItemSpecifics', detail).append(trtag);
		
		addspidx++;
	}
	*/
	//$('td.ItemSpecifics', detail).append('<pre>'+$.dump(recomm)+'</pre>');
	
	return;
}

function setItemSpecificsFormValue(item, i, recommref, specifics)
{
	var tdtag = $('<td/>');
	
	if (recommref == null) {
		
		var inputtag = $('<input/>')
			.attr('type', 'text')
			.attr('Name', 'ItemSpecifics.NameValueList.'+i+'.Value');
		$(tdtag).append(inputtag);
		
	} else if (recommref.ValidationRules.SelectionMode == 'FreeText'
			   && recommref.ValidationRules.MaxValues == '1') {
		
		var inputtag = $('<input/>')
			.attr('type', 'text')
			.attr('Name', 'ItemSpecifics.NameValueList.'+i+'.Value');
		$(tdtag).append(inputtag);
		
		if (recommref.ValueRecommendation != null) {
			var selecttag = $('<select/>')
				.attr('Name', 'ItemSpecifics.NameValueList.'+i+'.Value.selector')
				.addClass('remove')
				.append($('<option/>').html('(select from list)'));
			
			for (j in recommref.ValueRecommendation) {
				var optiontag = $('<option/>')
					.val(recommref.ValueRecommendation[j].Value)
					.html(recommref.ValueRecommendation[j].Value);
				$(selecttag).append(optiontag);
			}
			
			$(tdtag).append(selecttag);
		}
		
	} else if (recommref.ValidationRules.SelectionMode == 'FreeText'
			   && recommref.ValidationRules.MaxValues != '1') {
		
		var divtag = $('<div/>');
		
		var checkboxidx = 0;
		
		for (j in item.ItemSpecifics.NameValueList[i].Value) {
			var value = item.ItemSpecifics.NameValueList[i].Value[j];
			
			var existinrecomm = false;
			for (k in recommref.ValueRecommendation) {
				if (recommref.ValueRecommendation[k].Value == value) {
					existinrecomm = true;
					break;
				}
			}
			if (existinrecomm == false) {
				
				var idforlabel = item.id+'.ItemSpecifics.NameValueList.'+i+'.Name.'+checkboxidx;
				
				var checkboxtag = $('<input/>')
					.attr('id', idforlabel)
					.attr('Name', 'ItemSpecifics.NameValueList.'+i+'.Value')
					.attr('type', 'checkbox')
					.val(value);
				
				var labeltag = $('<label/>')
					.attr('for', idforlabel)
					.html(value+'('+checkboxidx+')');
				
				var divtag2 = $('<div/>');
				$(divtag2).append(checkboxtag);
				$(divtag2).append(labeltag);
				$(divtag).append(divtag2);
				
				checkboxidx++;
			}
		}
		
		for (j in recommref.ValueRecommendation) {
			
			var idforlabel = item.id+'.ItemSpecifics.NameValueList.'+i+'.Name.'+checkboxidx;
			
			var checkboxtag = $('<input/>')
				.attr('id', idforlabel)
				.attr('Name', 'ItemSpecifics.NameValueList.'+i+'.Value')
				.attr('type', 'checkbox')
				.val(recommref.ValueRecommendation[j].Value);
			
			var labeltag = $('<label/>')
				.attr('for', idforlabel)
				.html(recommref.ValueRecommendation[j].Value+'('+checkboxidx+')');
			
			var divtag2 = $('<div/>');
			$(divtag2).append(checkboxtag);
			$(divtag2).append(labeltag);
			$(divtag).append(divtag2);
			
			checkboxidx++;
		}
		
		$(tdtag).append(divtag);
		$(tdtag).append('<pre>'+$.dump(specifics[i])+'</pre>');
		
	} else {
		
		$(tdtag).append('<pre>'+$.dump(recommref)+'</pre>');
		
	}
	
	return tdtag;
}

