/* store rows data */
var rowsdata = new Array();
//var hash;

/* initialize */
$(document).bind({
	ready: function(event) {
		
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
		
		$('ul.accounts > li > ul:first').slideToggle('fast');
		$('a.active', $('ul.accountaction:first')).click();
		
		//setTimeout('autoclick()', 3000);
		//setTimeout("$('ul.editbuttons > li > a.save', 'div.detail').click()", 5000);
		
		setInterval(refresh, 2000);
		
		//setTimeout(autoclick, 3000);
		
		return;
	}
});

function bindevents()
{
	$(window).resize(resizediv);
	
	$('a.Title').live('click', clickTitle);
	
	$('select[name=Site]').live('change', changeSite);
	$('select.category').live('change', changeCategory);
	
	$('ul.editbuttons > li > a.edit',   'div.detail').live('click', clickEdit);
	$('ul.editbuttons > li > a.save',   'div.detail').live('click', clickSave);
	$('ul.editbuttons > li > a.cancel', 'div.detail').live('click', clickCancel);
	$('ul.editbuttons > li > a.delete', 'div.detail').live('click', clickDelete);
	$('ul.editbuttons > li > a.copy',   'div.detail').live('click', clickCopy);
	
	/* Bulk Buttons */
	$('div#bulkbuttons > input').live('click', function() {
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
	$('ul.accounts > li > a').live('click', function() {
		
		if ($(this).closest('li').attr('class') == 'allitems'
			&& $('ul', $(this).parent().next()).css('display') == 'block') {
			// don't collapse navi
		} else {
			$('ul', $(this).parent().next()).slideToggle('fast');
		}
		
		userid = $(this).attr('class');
		$('select[name=UserID]').val(userid);
		$('input[name=selling]').val('allitems');
		$('input[name=offset]').val(0);
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
	
	$('ul.tabNav a').live('click', function() {
		id = $(this).closest('tbody').attr('id');
		var curIdx = $(this).parent().prevAll().length + 1;
		$(this).parent().parent().children('.current').removeClass('current');
		$(this).parent().addClass('current');
		$('div.tabContainer', 'tbody#'+id).children('.current').hide();
		$('div.tabContainer', 'tbody#'+id).children('.current').removeClass('current');
		$('div.tabContainer', 'tbody#'+id).children('div:nth-child('+curIdx+')').show();
		$('div.tabContainer', 'tbody#'+id).children('div:nth-child('+curIdx+')').addClass('current');
		
		return false;
	});
	
	
	$('ul.accounts > li > ul > li > a').live('click', function() {
		
		v = $(this).attr('class');
		userid = $(this).parent().parent().attr('class').replace(/^accountaction /, '');
		$('input[name=selling]').val(v);
		$('input[name=offset]').val(0);
		$('select[name=UserID]').val(userid);
		if (v == 'unsold' || v == 'sold' || v == 'allitems') {
			$('input[name=sort]').val('ListingDetails_EndTime DESC');
		} else {
			$('input[name=sort]').val('ListingDetails_EndTime');
		}
		items();
		$('ul.accounts li').removeClass('tabselected');
		$(this).closest('li').addClass('tabselected');
		
		return false;
	});
	
	
	/* Paging */
	$('#paging > a').live('click', function() {
		limit = $('input[name=limit]').val();
		if ($(this).html() == '>>') {
			offset = ($('input[name=offset]').val()-0) + (limit-0);
		} else {
			offset = ($(this).html() - 1) * limit;
		}
		$('input[name=offset]').val(offset);
		items();
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
		var postdata = $('input[type=text]', td).serialize();
		$.post('/json/getproductsearchresults',
			   postdata,
			   function(data) {
				   var families = data.json.result.ProductSearchResult.AttributeSet.ProductFamilies;
				   $.each(families, function(i, o) {
					   var divtag = $('<div/>');
					   $(divtag).append($('<img/>').attr('src', o.ParentProduct['@stockPhotoURL']));
					   $(divtag).append(o.ParentProduct['@title']+'<br/>');
					   $(divtag).append(o.ParentProduct['@productID']+'<br/>');
					   $(td).append(divtag);
				   });
			   },
			   'json');
	});
	
    //jQuery('div#loading').ajaxStart(function() {jQuery(this).show();});
    //jQuery('div#loading').ajaxStop( function() {jQuery(this).hide();});
}	

/* auto click for debug */
function autoclick()
{
	itemid = '110089979385';
	
	$.ajaxSetup({async: false});
	$('input[class=filter][name=ItemID]').val(itemid);
	$('a.allitems').click();
	id = $('a.Title:lt(2):last').closest('tbody.itemrow').attr('id');
	$('a.Title', 'tbody#'+id).click();
	$('a.edit', 'tbody#'+id).click();
	$('a.save', 'tbody#'+id).click();
	
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
		if (value == '') return;
		if (value == null) return;
		if (namev.length == 0) return; // todo: fix ext.categorypath.0 bug
		
		if (namev.length == 1) {
			
			if (namev[0] == '') return;
			
			// todo: build array. ex:PaymentMethods
			if (accum[namev[0]] != undefined) {
				if ($.isArray(accum[namev[0]])) {
					accum[namev[0]].push(value);
				} else {
					tmpvalue = accum[namev[0]];
					accum[namev[0]] = [tmpvalue];
					accum[namev[0]].push(value);
				}
			} else {
				accum[namev[0]] = value;
			}
			
		} else {
			
			if (namev[1] == 0) {
				namev = [namev[0]].concat(namev.slice(2));
			} else if (namev[1] == 1) {
				if (accum[namev[0]][namev[1]] == null) {
					tmpvalue = accum[namev[0]];
					accum[namev[0]] = [tmpvalue];
				}
			}
			
			if (accum[namev[0]] == null) {
				accum[namev[0]] = {};
			}
			
			$('div#debug').append(namev.length+' : '+namev+' : '+value+'<hr/>');
			add(accum[namev[0]], namev.slice(1), value);
		}
	}; 
	
	this.each(function() {
		if ($(this).attr('name') != undefined) {
			add(accum, $(this).attr('name').split('.'), $(this).val());
		}
	});
	
	return accum;
};


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
	ulorg = $('ul.accounts').clone();
	
	$.getJSON('/json/summary', function(data) {
		
		$('ul.accounts > li.allitems > a.allitems').append(' ('+data.json.alluserids.allitems+')');
		$.each(data.json.alluserids, function(k, v) {
			$('ul.accounts > li > ul.accountaction a.'+k).append(' ('+v+')');
		});
		
		$.each(data.json, function(k, o) {
			if (k == 'alluserids') return;
			ul = ulorg.clone();
			$('a.allitems', ul).attr('class', k).html(k+' ('+o.allitems+')');
			$('ul.accountaction', ul).attr('class', 'accountaction '+k);
			$.each(o, function(j, v) {
				$('a.'+j, ul).append(' ('+v+')');
			});
			$('ul.accounts').append(ul.html());
		});
		
	});
	
	return;
}

/* list items */
function items()
{
	$.post('/json/items',
		   $('input.filter, select.filter').serialize(),
		   function(data) {
			   dump(data.json);
			   paging(data.json.cnt);
			   $('tbody:gt(2)', 'table#items').remove();
			   if (data.cnt == 0) {
				   $('tbody#rowloading > tr > td').html('No item data found.');
				   $('tbody#rowloading').show();
				   return;
			   }
			   $('tbody#rowloading').hide();
			   
			   var tmpids = new Array();
			   $.each(data.json.items, function(idx, row) {
				   dom = getrow(idx, row);
				   $('#items').append(dom);
				   //rowsdata[row['id']] = row;
				   //tmpids.push(row['id']);
				   rowsdata[idx] = row;
				   tmpids.push(idx);
			   });
		   },
		   'json');
	
	// todo: get detail of each items
	/*
	$.post('/json/users/items/',
		   $('input, select', '#filter').serialize(),
		   function(data) {
		   },
		   'json');
	*/
	
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
	}
	if (pictstr != '') {
		$('img.PictureURL', dom).attr('src', pictstr);
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
	
	if (row.ext.SellingStatus) {
		if (row.ext.SellingStatus.ListingStatus == 'Active') {
			st = $('<img/>').attr('src', '/icon/04/10/02.png').css('margin-right', '5px');
		} else if (row.ext.SellingStatus.ListingStatus == 'Completed') {
			st = $('<img/>').attr('src', '/icon/04/10/10.png').css('margin-right', '5px');
		} else {
			st = $(row.ext.SellingStatus.ListingStatus);
		}
		$('a.Title', dom).before(st);
	} else {
		st = $('<img/>').attr('src', '/icon/04/10/10.png').css('margin-right', '5px');
		$('a.Title', dom).before(st);
	}
	
	if (row.ext.errors) {
		$.each(row.ext.errors, function(k, v) {
			if (v != '') {
				$('a.Title', dom).after('<span class="error">'+v.LongMessage+'</span>');
				$('a.Title', dom).after('<br>');
			}
		});
	}
	
	return dom;
	
	
	
	if (row.PictureDetails.PictureURL) {
		$('img.PictureDetails_PictureURL', dom)
			.attr('src', row['PictureDetails_PictureURL'])
			.css('max-width', '20px')
			.css('max-height','20px');
	} else {
		$('img.PictureDetails_PictureURL', dom).remove();
	}
	
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
	
	// fill select option tags
	setoptiontags($('select[name=Country]',  detail), hash[site]['CountryDetails'],  null);
	setoptiontags($('select[name=Currency]', detail), hash[site]['CurrencyDetails'], null);
	
	// Categories
	var tmppath = row.ext.categorypath.slice(0);
	tmppath.unshift(0);
	var tmppds = getcategorypulldowns(site, tmppath);
	$('select[name="PrimaryCategory.CategoryID"]', detail).parent().html(tmppds);
	
	var category = hash[site]['Categories']['c'+row.ext.categorypath[row.ext.categorypath.length-2]]['c'+row.PrimaryCategory.CategoryID];
	
	// CategoryFeatures
	var conditions = category['CategoryFeatures']['ConditionValues']['Condition'];
	for (i in conditions) {
		var value = conditions[i]['ID'];
		var label = conditions[i]['DisplayName'];
		var optiontag = $('<option/>').val(value).html(label);
		$('select[name=ConditionID]', detail).append(optiontag);
	}
	
	// Category2CS
	if (category['Category2CS']) {
		$('input[name="ProductSearch.CharacteristicSetIDs.ID"]', detail)
			.val(category.Category2CS.CharacteristicsSets.AttributeSetID);
		
		$('input[name="ProductSearch.QueryKeywords"]', detail)
			.parent()
			.append('<pre>'+$.dump(category['Category2CS'])+'</pre>');
	}
	
	setItemSpecificsForms(row);
	
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
	
	/* description */
	// todo: check html5 srcdoc attribute
	iframe = $('<iframe/>')
		.attr('class', 'description')
		.attr('src', 'about:blank');
	
	iframe.load(function() {
		$(this).contents().find('body').append(row.Description);
	});
	$('textarea[name=Description]', detail).replaceWith(iframe);
	
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

	/*
	if (row.ShippingDetails.ShippingServiceOptions) {
		sdsso = row.ShippingDetails.ShippingServiceOptions;
		_sdsso = 'ShippingDetails.ShippingServiceOptions';
		$.each(arrayize(sdsso), function(i, o) {
			dsp(row, _sdsso+'.'+i+'.ShippingServiceCost.#text');
			dsp(row, _sdsso+'.'+i+'.ShippingServiceCost.@currencyID');
			dspv(row, _sdsso+'.'+i+'.ShippingService',
				 hash[row.Site]['ShippingServiceDetails'][o.ShippingService]['Description']);
		});
	}
	
	
	if (row.ShippingDetails.InternationalShippingServiceOption) {
		sdisso = row.ShippingDetails.InternationalShippingServiceOption;
		_sdisso = 'ShippingDetails.InternationalShippingServiceOption';
		$.each(arrayize(sdisso), function(i, o) {
			dsp(row, _sdisso+'.'+i+'.ShippingServiceCost.#text');
			dsp(row, _sdisso+'.'+i+'.ShippingServiceCost.@currencyID');
			dspv(row, _sdisso+'.'+i+'.ShippingService',
				 hash[row.Site]['ShippingServiceDetails'][o.ShippingService]['Description']);
		});
	}
	*/
	
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
	w = $('div#container').width()-179;
	h = $('body').height() - 10;
	
	$('div#content').width(w);
	$('table#items').width(w);
	$('a.Title').parent().width(w-600);
	$('div.tabContainer').width(w-32);
	
	//$('table#items').css('min-height', h+'px');
	
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
	
	$.getJSON('/json/gc2?site='+site+'&path='+path.join('.'),
			  function(data) {
				  
				  hash[site]['Categories'] = data.json.gc2.Categories;
				  
				  var tmppds = getcategorypulldowns(site, path);
				  $('select[name="PrimaryCategory.CategoryID"]', '#'+id).parent().html(tmppds);
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
	
	fval(dom, item, 'Title');
	fval(dom, item, 'SubTitle');
	fval(dom, item, 'StartPrice.@currencyID');
	fval(dom, item, 'StartPrice.#text');
	fval(dom, item, 'ListingDetails.BestOfferAutoAcceptPrice.@currencyID');
	fval(dom, item, 'ListingDetails.BestOfferAutoAcceptPrice.#text');
	fval(dom, item, 'ListingDetails.MinimumBestOfferPrice.@currencyID');
	fval(dom, item, 'ListingDetails.MinimumBestOfferPrice.#text');
	fval(dom, item, 'Quantity');
	fval(dom, item, 'Site');
	fval(dom, item, 'ListingType');
	fval(dom, item, 'BuyItNowPrice.@currencyID');
	fval(dom, item, 'BuyItNowPrice.#text');
	fval(dom, item, 'BuyerGuaranteePrice.@currencyID');
	fval(dom, item, 'BuyerGuaranteePrice.#text');
	fval(dom, item, 'PostalCode');
	fval(dom, item, 'Location');
	
	/* Dimensions */
	_sdcsr = 'ShippingDetails.CalculatedShippingRate';
	fval(dom, item, _sdcsr+'.PackageLength.@unit');
	fval(dom, item, _sdcsr+'.PackageLength.#text');
	fval(dom, item, _sdcsr+'.PackageLength.@measurementSystem');
	fval(dom, item, _sdcsr+'.PackageWidth.@unit');
	fval(dom, item, _sdcsr+'.PackageWidth.#text');
	fval(dom, item, _sdcsr+'.PackageWidth.@measurementSystem');
	fval(dom, item, _sdcsr+'.PackageDepth.@unit');
	fval(dom, item, _sdcsr+'.PackageDepth.#text');
	fval(dom, item, _sdcsr+'.PackageDepth.@measurementSystem');
	fval(dom, item, _sdcsr+'.WeightMajor.@unit');
	fval(dom, item, _sdcsr+'.WeightMajor.#text');
	fval(dom, item, _sdcsr+'.WeightMajor.@measurementSystem');
	fval(dom, item, _sdcsr+'.WeightMinor.@unit');
	fval(dom, item, _sdcsr+'.WeightMinor.#text');
	fval(dom, item, _sdcsr+'.WeightMinor.@measurementSystem');
	
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
	
	dump(postdata);
	return;
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
	
	//dump(postdata);
	//return false;
	
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
	showbuttons(detail, 'edit,copy,delete');
	
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
	$('div.detail', '#'+id).slideToggle('fast');
	
	$.post('/json/item',
		   'id='+id,
		   function(data) {
			   item = data.json.item;
			   dump(item);
			   
			   hash[item.Site]['Categories'] = data.json.Categories;
			   //preloadcategoryfeatures(item.Site, item.PrimaryCategory.CategoryID);
			   //preloadcategory(item.Site, item.ext.categorypath);
			   
			   getdetail(item);
			   showformvalues(item);
			   
			   $('td:nth-child(2)', '#'+id).fadeIn('fast');
			   
			   //preloadshippingtype(item.Site);
			   
			   rowsdata[id] = item;
			   
			   //$.scrollTo('tbody#'+id, {duration:800, axis:'y', offset:0});
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

function paging(cnt)
{
	var limit;
	var offset;
	
	limit  = $('input[name=limit]').val() - 0;
	offset = $('input[name=offset]').val() - 0;
	
	html = (offset+1)+' - ';
	if (offset+limit >= cnt) {
		html += cnt;
	} else {
		html += (offset+limit);
	}
	html += ' of '+cnt+'&nbsp;';
	
	if (cnt > limit) {
		for (i=0; i<(cnt/limit); i++) {
			if (offset/limit < i-5 || i+5 < offset/limit) continue;
			if (offset == i*limit) {
				html += '<a href="" style="background-color:#cccccc;">'+(i+1)+'</a>';
			} else {
				html += '<a href="">'+(i+1)+'</a>';
			}
		}
		if (offset+limit<cnt) {
			html += '<a href="">&lt;&lt;</a>';
		}
	}
	
	$('#paging').html(html);
	
	return;
}

function showbuttons(detail, buttons)
{
	buttons = 'a.'+buttons.replace(/,/g, ',a.');
	
	ulbtn = $('ul.editbuttons', detail);
	$('ul.editbuttons > li', detail).hide();
	$(buttons, ulbtn).parent().show();
	
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
	site = $('select[name=Site]', '#'+id).val();
	type = $('select[name="ShippingDetails.ShippingType.domestic"]', '#'+id).val();
	intltype = $('select[name="ShippingDetails.ShippingType.international"]', '#'+id).val();
	
	select = $('<select/>').append($('<option/>'));
	intl = $('<select/>').append($('<option/>'));
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
	_sdsso = 'ShippingDetails.ShippingServiceOptions';
	_sdisso = 'ShippingDetails.InternationalShippingServiceOptions';
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
	var detail = $('div.detail', '#'+item.id);
	
	/* text */
	$.each($('table.detail input[type=text]', detail), function(i, form) {
		var formname = $(form).attr('name');
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		try {
			eval("tmpvalue = item"+formname);
			//var tmpvalue = item[formname];
			
			if (tmpvalue == null) tmpvalue = '';
			
			htmlencoded = $('<div/>').text(tmpvalue+'[T]').html();
			$(form).replaceWith(htmlencoded);
		} catch (err) {
			//$(detail).prepend('ERR: '+err.description+'<br />');
		}
	});
	
	/* select */
	$.each($('table.detail select', detail), function(i, form) {
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
			//$(detail).before('ERR: '+formname+' '+err+'<br />');
		}
	});
	
	return;
}

function fillformvalues(item)
{
	var detail = $('div.detail', '#'+item.id);
	
	$.each($('input, select, textarea', detail), function(i, form) {
		var formname = $(form).attr('name');
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		
		try {
			eval("var tmpvalue = item"+formname);
			$(form).val(tmpvalue);
		} catch (err) {
			//$(detail).prepend('ERR: '+err.description+'<br />');
		}
	});
	
	return;
}

function setItemSpecificsForms(item)
{
	var detail = $('div.detail', '#'+item.id);
	
	var categoryid = item.PrimaryCategory.CategoryID;
	var parentid = item.ext.categorypath[item.ext.categorypath.length-2];
	var category = hash[item.Site]['Categories']['c'+parentid]['c'+categoryid];
	
	var recommkey = new Array();
	var specificskey = new Array();
	var specifics = item.ItemSpecifics.NameValueList;
	var recomm = category['CategorySpecifics']['NameRecommendation'];
	
	for (i in recomm) {
		recommkey[recomm[i].Name] = i;
	}
	
	for (i in specifics) {
		var recommref = recomm[recommkey[specifics[i].Name]];
		specificskey[specifics[i].Name] = i;
		
		var trtag = $('<tr />');
		var tdtag = $('<td />');
		
		var inputtag = $('<input />')
			.attr('type', 'hidden')
			.attr('Name', 'ItemSpecifics.NameValueList.'+i+'.Name');
		
		$(tdtag).append(inputtag);
		$(tdtag).append(specifics[i].Name);
		$(trtag).append(tdtag);
		
		var tdtag = setItemSpecificsFormValue(recommref, specifics);
		
		$(trtag).append(tdtag);
		$('table.ItemSpecifics', detail).append(trtag);
	}
	
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
	$('td.ItemSpecifics', detail).append('<pre>'+$.dump(recomm)+'</pre>');
	
}

function setItemSpecificsFormValue(recommref, specifics)
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
		
		var tabletag = $('<table/>').addClass('checkboxes');
		var trtag = $('<tr />');
		
		for (j in recommref.ValueRecommendation) {
			if (j > 0 && j % 3 == 0) {
				$(tabletag).append(trtag);
				trtag = $('<tr />');
			}
			var tdtag2 = $('<td />');
			var checkboxtag = $('<input/>')
				.attr('Name', 'ItemSpecifics.NameValueList.'+i+'.Value')
				.attr('type', 'checkbox')
				.val(recommref.ValueRecommendation[j].Value);
			$(tdtag2).append(checkboxtag);
			$(tdtag2).append(recommref.ValueRecommendation[j].Value);
			$(trtag).append(tdtag2);
		}
		$(tabletag).append(trtag);
		$(tdtag).append(tabletag);
		
	} else {
		
		$(tdtag).append('<pre>'+$.dump(recommref)+'</pre>');
		
	}
	
	return tdtag;
}

