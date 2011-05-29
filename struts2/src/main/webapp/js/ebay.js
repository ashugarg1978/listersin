/* store rows data */
var rowsdata = new Array();
var hash;

/* 
http://stackoverflow.com/questions/2552836/convert-an-html-form-field-to-a-json-object-with-inner-objects 
*/
$.fn.extractObject = function() {
	var accum = {};
	function add(accum, namev, value) {
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
			if (accum[namev[0]] == null)
				accum[namev[0]] = {};
			add(accum[namev[0]], namev.slice(1), value);
		}
	}; 
	this.each(function() {
		add(accum, $(this).attr('name').split('.'), $(this).val());
	});
	return accum;
};


/* initialize */
$(document).bind({
	ready: function(event) {
		
		resizediv();
		bindevents();
		summary();
		gethash();
		
		$('ul.accounts > li > ul:first').slideToggle('fast');
		$('a.active', $('ul.accountaction:first')).click();
		
		//setTimeout('autoclick()', 3000);
		//setTimeout("$('ul.editbuttons > li > a.save', 'div.detail').click()", 5000);
		
		setInterval(refresh, 2000);
		
		return;
	}
});

/* auto click for debug */
function autoclick()
{
	id = $('a.Title:lt(2):last').closest('tbody.itemrow').attr('id');
	
	if (id == 'rowtemplate') return;
	
	$('a.Title', 'tbody#'+id).click();
	//setTimeout("$('li > a:contains(Shipping)', '   tbody#'+id).click()", 2000);
	//setTimeout("$('ul.editbuttons > li > a.edit', 'tbody#'+id).click()", 2000);
	
	return;
}

function gethash()
{
	$.getJSON('/hash', function(data) {
		hash = data.json;
		$.each(hash, function(k, v) {
			$('select[name=Site]', $('div#detailtemplate')).append('<option>'+k+'</option>');
		});
		
	});
	
	return;
	
//	$.get('/hash', function(data) {
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
	
	$.getJSON('/summary', function(data) {
		
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
	$.post('/items',
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
	$.post('/users/items/',
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
	
	if (row.SellingStatus) {
		if (row.SellingStatus.ListingStatus == 'Active') {
			st = $('<img/>').attr('src', '/icon/04/10/02.png').css('margin-right', '5px');
		} else if (row.SellingStatus.ListingStatus == 'Completed') {
			st = $('<img/>').attr('src', '/icon/04/10/10.png').css('margin-right', '5px');
		} else {
			st = $(row.SellingStatus.ListingStatus);
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
	id = row.id;
	detail = $('div.detail', '#'+id);
    
	$('input[name=Title]',    detail).replaceWith(row.Title);
	$('input[name=SubTitle]', detail).replaceWith(row.SubTitle);
	$('input[name=Quantity]', detail).replaceWith(row.Quantity);
	$('input[name=StartPrice.#text]', detail).replaceWith(row.StartPrice['#text']);
	
	$('select[name=Site]',    detail).replaceWith(row.Site);
	tmp = $('select[name=ListingType] > option[value='+row.ListingType+']', detail).text();
	$('select[name=ListingType]', detail).replaceWith(tmp);
	
	/* paymentmethods */
	var pmstr = '<span style="color:#aaaaaa;">-</span>';
	if (typeof(row.PaymentMethods) == 'string') {
		pmstr = row.PaymentMethods;
	} else if (typeof(row.PaymentMethods) == 'object') {
		pmstr = row.PaymentMethods.join('<br>');
	}
	pmstr = pmstr.replace(/PayPal/, 'PayPal ('+row.PayPalEmailAddress+')');
	$('td.paymentmethod', detail).html(pmstr);
	
	$('td.duration', detail).text(getListingDurationLabel(row.ListingDuration));
	
	// todo: display categoryname from categorypath2
	//$('td.category', detail).html(row.PrimaryCategory.CategoryName.replace(/:/g, ' &gt; '));
	
	$('select, input', detail).replaceWith('<span style="color:#aaaaaa;">-</span>');
	
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
	
	/* shippingtype */
	if (row.ext.shippingtype) {
		$('td.shippingtype_domestic', detail).html(row.ext.shippingtype.domestic);
		$('td.shippingtype_international', detail).html(row.ext.shippingtype.international);
	}
	if (row.ShippingDetails.ShippingServiceOptions) {
		sso = '';
		$.each(row.ShippingDetails.ShippingServiceOptions, function(i, o) {
			sso += hash[row.Site]['ShippingServiceDetails'][o.ShippingService]['Description'];
			sso += '<br>';
		});
		$('td.shippingservice', detail).html(sso);
	}
	
	if (row.ShippingDetails.InternationalShippingServiceOption) {
		isso = '';
		$.each(row.ShippingDetails.InternationalShippingServiceOption, function(i, o) {
			isso += hash[row.Site]['ShippingServiceDetails'][o.ShippingService]['Description'];
			if (o.ShippingServiceCost) {
				isso += ' '+o['ShippingServiceCost@currencyID']+o.ShippingServiceCost;
			}
			if (typeof(o.ShipToLocation == 'string')) {
				isso += '<br>Ship to ' + o.ShipToLocation;
			} else if (typeof(o.ShipToLocation == 'object')) {
				isso += '<br>Ship to ' + o.ShipToLocation.join(' / ');
			}
			isso += '<br>';
		});
		$('td.intlshippingservice', detail).html(isso);
	}
	
	if (row.ShippingDetails.CalculatedShippingRate) {
		csro = row.ShippingDetails.CalculatedShippingRate;
		
		sp = csro.ShippingPackage;
		if (csro.ShippingIrregular == 'true') sp += ' (Irregular package)';
		$('td.shippingpackage', detail).html(sp);
		
		dm = csro.PackageLength + csro['PackageLength@unit']
			+ ' x ' + csro.PackageWidth + csro['PackageWidth@unit']
			+ ' x ' + csro.PackageDepth + csro['PackageDepth@unit'];
		$('td.dimensions', detail).html(dm);
		
		weight = csro.WeightMajor + csro['WeightMajor@unit']
			+ ' ' + csro.WeightMinor + csro['WeightMinor@unit'];
		$('td.weight', detail).html(weight);
	}
	
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
	
	/* dimensions */
	dmstr = '';
	alert(row['ShippingDetails_CalculatedShippingRate_PackageDepth']);
	if (row['ShippingDetails_CalculatedShippingRate_PackageDepth']) {
		dmstr += row['ShippingDetails_CalculatedShippingRate_PackageDepth']
			+ row['ShippingDetails_CalculatedShippingRate_PackageDepth@unit'] + ' x ';
	}
	if (row['ShippingDetails_CalculatedShippingRate_PackageLength']) {
		dmstr += row['ShippingDetails_CalculatedShippingRate_PackageLength']
			+ row['ShippingDetails_CalculatedShippingRate_PackageLength@unit'] + ' x ';
	}
	if (row['ShippingDetails_CalculatedShippingRate_PackageWidth']) {
		dmstr += row['ShippingDetails_CalculatedShippingRate_PackageWidth']
			+ row['ShippingDetails_CalculatedShippingRate_PackageWidth@unit'];
	}
	$('td.dimensions', detail).html(dmstr);
	
	
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

function bindevents()
{
	/* Window Resize */
	$(window).resize(function() {
		resizediv();
	});
	
	/* Bulk Buttons */
	$('div#bulkbuttons > input').live('click', function() {
		action = $(this).attr('class');
		
		if (action == 'checkall') {
			$("input[name='id'][value!=on]").attr('checked', 'checked');
			$("input[name='allpages']").attr('checked', '');
			return;
		} else if (action == 'checkallpage') {
			$("input[name='id'][value!=on]").attr('checked', 'checked');
			$("input[name='allpages']").attr('checked', 'checked');
			return;
		} else if (action == 'uncheckall') {
			$("input[name='id'][value!=on]").attr('checked', '');
			$("input[name='allpages']").attr('checked', '');
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
		
		$.post('/'+action,
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
    
	/* Site */
	$('select[name=Site]').live('change', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		site = $(this).val();
		
		sel = getcategorypulldown(site, 0);
		$('td.category', '#'+id).html(sel);
		
		if (!hash[site]['category']['grandchildren'][0]) preloadcategory(site, []);
		
		return;
	});
	
	/* Category */
	$('select.category').live('change', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		site = $('select[name=Site]', '#'+id).val();
		
		$(this).nextAll().remove();
		msg(hash[site]['category']['children'][$(this).val()]);
		if (hash[site]['category']['children'][$(this).val()] != 'leaf') {
			preloadcategory(site, [$(this).val()]);
			sel = getcategorypulldown(site, $(this).val());
			$('td.category', '#'+id).append(sel);
		}
		$('select.category',      '#'+id).attr('name', '');
		$('select.category:last', '#'+id).attr('name', 'PrimaryCategory.CategoryID');
		
		return;
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
	
	/* Title */
	$('a.Title').live('click', function() {
		
		var id = $(this).closest('tbody').attr('id');
		
		if (!$('tr.row2 td', '#'+id).html().match(/^<div/i)) {
			
			detail = $('div.detail', 'div#detailtemplate').clone();
			$('td:nth-child(2)', detail).hide();
			$('tr.row2 td', '#'+id).html(detail);
			$('div.detail', '#'+id).slideToggle('fast');
			
			$.post('/item',
				   'id='+id,
				   function(data) {
					   dump(data.json.item);
					   getdetail(data.json.item);
					   $('td:nth-child(2)', '#'+id).fadeIn('fast');
					   
					   preloadcategory(data.json.item.Site, data.json.item.ext.categorypath);
					   preloadcategoryfeatures(data.json.item.Site,
											   data.json.item.PrimaryCategory.CategoryID);
					   
					   //preloadshippingtype(data.Site);
					   rowsdata[id] = data.json.item;
					   
					   //$.scrollTo('tbody#'+id, {duration:800, axis:'y', offset:0});
				   },
				   'json');
		} else {	
			$('div.detail', '#'+id).slideToggle('fast');
		}
		
		return false;
	});
	
	/* Paging */
	$('#paging > a').live('click', function() {
		limit = $('input[name=limit]').val();
		if ($(this).html() == '＞') {
			offset = ($('input[name=offset]').val()-0) + (limit-0);
		} else {
			offset = ($(this).html() - 1) * limit;
		}
		$('input[name=offset]').val(offset);
		items();
		return false;
	});
	
	/* Edit */
	$('ul.editbuttons > li > a.edit', 'div.detail').live('click', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		item = rowsdata[id];
		dom = $('div.detail', 'div#detailtemplate').clone().css('display', 'block');
		
	    /* preserve selected tab */
	    tab = $('ul.tabNav > li.current > a', $('tbody#'+id));
	    tabnum = tab.parent().prevAll().length + 1;
		$('.tabNav', dom).children('.current').removeClass('current');
		$('.tabContainer', dom).children('.current').hide();
		$('.tabContainer', dom).children('.current').removeClass('current');
	    $('.tabNav', dom).children('li:nth-child('+tabnum+')').addClass('current');
	    $('.tabContainer', dom).children('div:nth-child('+tabnum+')').show();
	    $('.tabContainer', dom).children('div:nth-child('+tabnum+')').addClass('current');
		
		$('input[name=Title]',            dom).val(item.Title);
		$('input[name=SubTitle]',         dom).val(item.SubTitle);
		$('input[name=StartPrice.#text]', dom).val(item.StartPrice['#text']);
		$('input[name=Quantity]',         dom).val(item.Quantity);
		$('select[name=Site]',            dom).val(item.Site);
		$('select[name=ListingType]',     dom).val(item.ListingType);
		
		if (item.Description != null) {
			$('textarea[name=Description]',   dom).val(item.Description);
		}
		
		showbuttons(dom, 'save,cancel');
		$('div.detail', 'tbody#'+id).replaceWith(dom);
	    $('input[name=Title]', 'tbody#'+id).focus();
		
		// todo: compare to CKEditor
		$('textarea[name=Description]', 'tbody#'+id).wysiwyg();
		
		/* category selector */
		$('td.category', dom).html(getcategorypulldowns(item.Site, item.ext.categorypath));
		$('select.category:last', dom).attr('name', 'PrimaryCategory.CategoryID');
		
		/* pictures */
		for (i=0; i<=11; i++) {
			$('img.PD_PURL_'+i, dom).attr('id', 'PD_PURL_'+id+'_'+i);
		}
		if (typeof(item.PictureDetails.PictureURL) == 'string') {
			$('img.PD_PURL_0', dom).attr('src', item.PictureDetails.PictureURL);
			$('input[name=PictureDetails.PictureURL.0]', dom).val(item.PictureDetails.PictureURL);
		} else if (typeof(item.PictureDetails.PictureURL) == 'object') {
			$.each(item.PictureDetails.PictureURL, function(i, url) {
				if (url == '') return;
				$('img.PD_PURL_'+i, dom).attr('src', url);
				$('input[name=PictureDetails.PictureURL.'+i+']', dom).val(url);
			});
		} 
		
		site = item.Site;
		categoryid = item.PrimaryCategory.CategoryID;
		
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
		
		/* listing duration */
		tmpo = hash[site]['category']['features'][categoryid]['ListingDuration'];
		sel = $('<select/>').attr('name', 'ListingDuration');
		$.each(tmpo[rowsdata[id]['ListingType']], function(k, v) {
			opt = $('<option/>').val(k).text(v);
			if (rowsdata[id]['ListingDuration'] == k) opt.attr('selected', 'selected');
			sel.append(opt);
		});
		$('td.duration', dom).html(sel);
		
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
			$('td.paymentmethod', dom).append(v+'<br>');
			i++;
		});
		
//		$('td.shippingservice', '#'+id).append(getshippingservice(id));
		
		return false;
	});
	
	/* Save */
	$('ul.editbuttons > li > a.save', 'div.detail').live('click', function() {
		
		id = $(this).closest('tbody.itemrow').attr('id');
		detail = $(this).closest('div.detail');
		
		// todo: varidation check
		if ($('select[name=PrimaryCategory.CategoryID]', detail).val() == '') {
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
		
		postdata = $('input:text, input:checked, input:hidden, select, textarea',
					 $(this).closest('div.detail')).extractObject();
		postdata = JSON.stringify(postdata);
		//dump(postdata);
		//return false;
		
		$.post('/save',
			   'id='+id+'&json='+postdata,
			   function(data) {
				   rowsdata[id] = data.json.item;
				   dump(data.json);
				   alert(data.json.item.Title);
				   getdetail(data.json.item);
				   showbuttons(detail, 'edit,copy,delete');
			   },
			   'json');
		
		return false;
	});
	
	/* Cancel */
	$('ul.editbuttons > li > a.cancel', 'div.detail').live('click', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		getdetail(rowsdata[id]);
		showbuttons(detail, 'edit,copy,delete');
		return false;
	});
	
	/* Delete */
	$('ul.editbuttons > li > a.delete', 'div.detail').live('click', function() {
		return false;
	});
	
	/* Copy */
	$('ul.editbuttons > li > a.copy', 'div.detail').live('click', function() {
		return false;
	});
	
	/* Editor */
	$('a.wysiwyg').live('click', function() {
		$('textarea[name=description]', '#'+id).wysiwyg('destroy');
		return false;
	});
	
	/* ShippingType */
	// todo: check all browsers can detect [domestic] selector
	$('select[name=ShippingDetails_ShippingType[domestic]]').live('change', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		sel = getshippingservice(id);
		$('td.shippingservice', '#'+id).html(sel);
		
		return;
	});
	
	/* Import */
	$('div#importform input[type=button]').live('click', function() {
		$.post('/import',
			   $('select, input', 'div#importform').serialize(),
			   function(data) {
				   
			   });
	});
	
    //jQuery('div#loading').ajaxStart(function() {jQuery(this).show();});
    //jQuery('div#loading').ajaxStop( function() {jQuery(this).hide();});
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
		opt = $('<option/>').val(childid).html(str);
		sel.append(opt);
	});
	
	return sel;
}

function getcategorypulldowns(site, path)
{
	tmpid = 0;
	ctgr = hash[site]['category'];
	
	sels = $('<div/>');
	$.each(path, function(i, categoryid) {
		sel = $('<select class="category"/>');
		opt = $('<option/>').val('').text('');
		sel.append(opt);
		$.each(ctgr['children'][tmpid], function(i, cid) {
			str = ctgr['name'][cid];
			if (ctgr['children'][cid] != 'leaf') str += ' &gt;';
			opt = $('<option/>').val(cid).html(str);
			sel.append(opt);
		});
		
		sel.val(categoryid);
		tmpid = categoryid;
		
		sels.append(sel);
	});
	
	return sels.children();
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
	
	$.getJSON('/grandchildren?site='+site+'&pathstr='+npath.join('.'),
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
	
	$.getJSON('/categoryfeatures?site='+site+'&categoryid='+categoryid,
			  function(data) {
				  var tmpo = $.extend({}, hash[site]['category']['features'], data.json.features);
				  hash[site]['category']['features'] = tmpo;
				  //dump(hash[site]['category']['features']);
			  });
}

function preloadshippingtype(site)
{
	if (hash[site]['ShippingType']) return;
	
	$.getJSON('/users/getShippingType/'+site,
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
	
	$.post('/items',
		   loadings.serialize(),
		   function(data) {
			   dump(data.json);
			   $.each(data.json.items, function(idx, row) {
				   dom = getrow(idx, row);
				   if (row.ext.status == '') {
					   $('input:checkbox', dom).css('visibility', '').attr('checked', '');
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
			html += '<a href="">＞</a>';
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
	$('div#msg').append(o+'<br>');
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
	type = $('select[name=ShippingDetails_ShippingType[domestic]]', '#'+id).val();
	
	if (type == 'Calculated') {
		sel = $('<select class="ShippingPackage"/>');
		$.each(hash[site]['ShippingPackageDetails'], function(i, o) {
			$('<option/>').val(o['ShippingPackage']).html(o['Description']).appendTo(sel);
		});
		$('select[name=ShippingPackage]', '#'+id).html(sel.html());
		$('td.shippingpackage', '#'+id).children().show();
		$('td.dimensions',      '#'+id).children().show();
	} else {
		$('td.shippingpackage', '#'+id).children().hide();
		$('td.dimensions',      '#'+id).children().hide();
	}
	
	
	sel = $('<select class="ShippingService"/>');
	$.each(hash[site]['ShippingServiceDetails'], function(i, o) {
		if (o['ValidForSellingFlow'] != 'true') return;
		if (o['ShippingServiceID'] >= 50000) return;
		
		if ($.inArray(type, o['ServiceType']) >= 0 || o['ServiceType'] == type) {
			$('<option/>').val(o['ShippingService']).html(o['Description']).appendTo(sel);
		}
	});
	$('select.ShippingService', '#'+id).html(sel.html());
	//$('td.shippingservice', '#'+id).html(sel);
	
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
