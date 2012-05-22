/* store rows data */
var rowsdata = new Array();
var hasmore  = false;
var strdiff  = '';
var timeout  = null;
var foundproducts = new Array();

/* initialize */
$(function() {
	
	resizediv();
	bindevents();
	summary();
	//dump(hash);
	
	// todo: same code
	$.each(hash, function(k, v) {
		var optiontag = $('<option />').val(k).html(k);
		$('select[name="mod.Site"]', $('div#detailtemplate')).append(optiontag);
	});
	
	//$.ajaxSetup({async: false});
	
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
	//setTimeout(autoclick, 3000);
	
	return;
});

function bindevents()
{
	$(window).resize(resizediv);
	
	$('button#register').live('click', function() {
		var postdata = $('input', $(this).closest('form')).serialize();
		
		$.post('/json/register',
			   postdata,
			   function(data) {
				   if (data.json.result == true) {
					   var divtag = $('<div/>')
						   .css('color', 'blue')
						   .html(data.json.message);
					   $('button#register').parent().append(divtag);
				   } else {
					   var divtag = $('<div/>')
						   .css('color', 'red')
						   .html(data.json.message);
					   $('button#register').parent().append(divtag);
				   }
			   },
			   'json');
		
		return false;
	});

	$('button#addaccount').live('click', function() {
		$.post('/json/addaccount',
			   null,
			   function(data) {
				   //$('button#addaccount').append(data.json.url);
				   window.location.href = data.json.url;
			   },
			   'json');
	});
	
	$(window).scroll(function() {
		if (hasmore == false) return;
		
		if ($(window).scrollTop() + $(window).height() == $('body').height()) {
			$('div#loading').show();
			
			var offset = parseInt($('input.filter[name=offset]').val());
			var limit  = parseInt($('input.filter[name=limit]' ).val());
			
			// todo: check offset number after calling refresh(). ex: after 3 items listed.
			$('input.filter[name=offset]').val(offset+limit-0);
			items();
		}
	});
	
	$('tr.row1').live('click', clickTitle);
	
	$('tr.row1 input[type=checkbox]').live('click', function(event) {
		event.stopPropagation();
	});
	
	$('a.ItemID').live('click', function(event) {
		event.stopPropagation();
	});
	
	$('select[name="mod.Site"]').live('change', changeSite);
	$('select.category').live('change', changeCategory);
	
	$('div.editbuttons button.edit',   'div.detail').live('click', clickEdit);
	$('div.editbuttons button.save',   'div.detail').live('click', save);
	$('div.editbuttons button.cancel', 'div.detail').live('click', clickCancel);
	$('div.editbuttons button.delete', 'div.detail').live('click', clickDelete);
	$('div.editbuttons button.copy',   'div.detail').live('click', clickCopy);
	
	/* Bulk Buttons */
	$('div#bulkbuttons button').live('click', function() {
		if ($(this).attr('id') == 'settingsbutton') return;
		action = $(this).attr('class').replace(/ .+$/, '');
		
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
		
		$.post
		('/json/'+action,
		 postdata,
		 function(data) {
			 if (action == 'add'
				 || action == 'end'
				 || action == 'relist'
				 || action == 'verifyadditem') {
				 refresh();
			 }
			 if (action == 'copy' || action == 'delete') {
				 $("td.loading").removeClass('loading');
				 $("input[name='id'][value!=on]:checked")
					 .css('visibility', '')
					 .attr('checked', '');
			 }
			 dump(data);
		 });
		
		return;
	});
	
	/* Left Navi (eBay account name click) */
	$('ul.accounts > li').live('click', function() {
		
		if ($(this).attr('class') == undefined) {
			return;
		}
		
		$('div#settings').hide();
		$('div#ebayaccountsetting').hide();
		$('table#items').show();
		
		if ($(this).attr('class') == 'allitems'
			&& $('ul', $(this).next()).css('display') == 'block') {
			// don't collapse navi
		} else {
			$('ul', $(this).next()).slideToggle('fast');
		}
		
		userid = $(this).attr('class')
			.replace('tabselected', '')
			.replace('allitems', '')
			.replace(' ', '');
		
		$('input[name=UserID]').val(userid);
		$('input[name=selling]').val('allitems');
		$('input[name=offset]').val(0);
		$('table#items tbody:gt(1)').remove();
		items();
		
		$('ul.accounts li').removeClass('tabselected');
		$(this).closest('li').addClass('tabselected');
		
		return false;
	});
	
	/* Left navi (item status click) */
	$('ul.accountaction > li').live('click', function() {
		
		var v = $(this).attr('class').replace('tabselected', '').replace(' ', '');
		
		var userid = $(this).parent().attr('class')
			.replace(/^accountaction/, '')
			.replace(' ', '');
		
		if (v == 'setting') {
			
			$('div#ebayaccountsettingtarget').html(userid);
			
			$('div#settings').hide();
			$('table#items').hide();
			$('div#ebayaccountsetting').show();
			
		} else {
		
			$('div#settings').hide();
			$('div#ebayaccountsetting').hide();
			$('table#items').show();
			
			$('input[name=selling]').val(v);
			$('input[name=offset]').val(0);
			$('input[name=UserID]').val(userid);
			if (v == 'unsold' || v == 'sold' || v == 'allitems') {
				$('input[name=sort]').val('ListingDetails_EndTime DESC');
			} else {
				$('input[name=sort]').val('ListingDetails_EndTime');
			}
			$('table#items tbody:gt(1)').remove();
			items();
			
		}
		$('ul.accounts li').removeClass('tabselected');
		$(this).closest('li').addClass('tabselected');
		
		return false;
	});
	
	/* Picture */
    $('input:file').live('change', function() {
		
		var id = $(this).closest('tbody.itemrow').attr('id');
		var idform = $('<input/>').attr('name', 'id').val(id);
		$(this).closest('form').append(idform);
		
		var fileindex = $(this).attr('name');
		var fileindexform = $('<input/>').attr('name', 'fileindex').val(fileindex);
		$(this).closest('form').append(fileindexform);
		
		$(this).attr('name', 'uploadfile')
		$(this).closest('form').submit();
		$(this).closest('form')[0].reset();
		
		$(fileindexform).remove();
		$(idform).remove();
    });
    
	$('select[name="mod.ListingType"]').live('change', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		updateduration(id);
	});
	
	$('ul.tabNav li').live('click', function() {
		var id = $(this).closest('tbody').attr('id');
		
		var curIdx = $(this).prevAll().length + 1;
		
		$(this).parent().children('.current').removeClass('current');
		$(this).addClass('current');
		
		$('div.tabContainer', '#'+id).children('.current').hide();
		$('div.tabContainer', '#'+id).children('.current').removeClass('current');
		$('div.tabContainer', '#'+id).children('div:nth-child('+curIdx+')').show();
		$('div.tabContainer', '#'+id).children('div:nth-child('+curIdx+')').addClass('current');
		
		return false;
	});
	
	
	/* Editor */
	$('a.wysiwyg').live('click', function() {
		$('textarea[name=description]', '#'+id).wysiwyg('destroy');
		return false;
	});
	
	/* ShippingType */
	$('select[name="ShippingDetails.ShippingType.domestic"]').live('change', function() {
		var id = $(this).closest('tbody.itemrow').attr('id');
		var item_modifing =
			$('input[type=text], input:checked, input[type=hidden], select, textarea', '#'+id)
			.extractObject();
		item_modifing.id = id;
		setformelements_shipping(item_modifing);
		return;
	});
	$('select[name="ShippingDetails.ShippingType.international"]').live('change', function() {
		var id = $(this).closest('tbody.itemrow').attr('id');
		var item_modifing =
			$('input[type=text], input:checked, input[type=hidden], select, textarea', '#'+id)
			.extractObject();
		item_modifing.id = id;
		setformelements_shipping(item_modifing);
		return;
	});

	/* ShippingPackage */
	var _sdcsr = 'ShippingDetails.CalculatedShippingRate';
	$('select[name="mod.'+_sdcsr+'.ShippingPackage"]').live('change', function() {
		var id = $(this).closest('tbody.itemrow').attr('id');
		var item_modifing =
			$('input[type=text], input:checked, input[type=hidden], select, textarea', '#'+id)
			.extractObject();
		item_modifing.id = id;
		setformelements_shipping(item_modifing);
		return;
	});
	
	/* Offer additional service */
	$('a.addsso').live('click', function() {
		addsso(this);
		$.each($('input[name$=Priority]', $(this).parent()), function(i, o) {
			if ($(o).val() == '') $(o).val(i+1);
		});
		return false;
	});
	
	/* Remove service */
	$('a.removesso').live('click', function() {
		var pdiv = $(this).parent().parent();
		
		$(this).parent().remove();
		$('a.addsso', $(pdiv)).show();
		
		$.each($('div[class^=ShippingService]', pdiv), function(k, v) {
			if (k == 0) return;
			
			$(v).attr('class', 'ShippingService'+k);
			$('input[name$=ShippingServicePriority]', v).val(k+1);
			
			$.each($('select, input', v), function(j, o) {
				var orgname = $(o).attr('name');
				$(o).attr('name', orgname.replace(/\.[0-9]\./, '.'+k+'.'));
			});
		});
		
		return false;
	});
	
	/* Import */
	$('button#import', 'div#ebayaccountsetting').live('click', function() {
		$.post('/json/import',
			   'userid='+$('div#ebayaccountsettingtarget').html(),
			   function(data) {
				   
			   });
	});
	
	$('button.GetProductSearchResults').live('click', findproducts);
	
	$('div.foundproducts div.product').live('click', function() {
		
		var id = $(this).closest('tbody.itemrow').attr('id');
		var productid = $('div.productid', $(this)).html();
		var product = foundproducts['R'+productid];
		
		$('input[name="mod.ProductListingDetails.ProductID"]', '#'+id).val('');
		$('input[name="mod.ProductListingDetails.ProductReferenceID"]', '#'+id).val(productid);
		$('input[name="mod.Title"]', '#'+id).val(product.Title);
		
		$(this).closest('div.foundproducts').slideUp('fast');
		
		return;
	});
	
	$('a#toggledebug').live('click', function() {
		if ($('div#debug').css('display') == 'none') {
			$('div#debug').show();
			$('table#items').hide();
		} else {
			$('div#debug').hide();
			$('table#items').show();
		}
		return false;
	});
	
	// Title Search
	$('input.filter[name=Title]').live('keyup', function() {
		setTimeout('titlesearch_keyupdone("' + $(this).val() + '")', 500);
	});
	
	// Settings button
	$('button#settingsbutton').live('click', function() {

		$.post('/json/settings',
			   null,
			   function(data) {
				   $('div#settings').append('<pre>'+$.dump(data)+'</pre>');
			   },
			   'json');
		
		// setformelements
		$('select[name=TimeZone]', 'div#settings').children().remove();
		$.each(timezoneids, function(k, v) {
			var optiontag = $('<option/>').val(k).text(v);
			$('select[name=TimeZone]', 'div#settings').append(optiontag);
		});
		
		$('table#items').hide();
		$('div#ebayaccountsetting').hide();
		$('div#settings').show();
	});
	
	// Theme select
	$('select[name="ListingDesigner.GroupID"]').live('change', function() {
		var id = $(this).closest('tbody.itemrow').attr('id');
		var site = $('select[name="mod.Site"]', '#'+id).val();
		if ($(this).val() == '') {
			// todo: unset layout pulldown
			return;
		}
		
		$.getJSON
		('/json/descriptiontemplate?site='+site+'&groupid='+$(this).val(),
		 function(data) {
			 dump(data);
			 
			 $('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).children().remove();
			 var emptyoptiontag = $('<option/>').val('').text('(not selected)');
			 $.each(data.json, function(i, o) {
				 var optiontag = $('<option/>').val(o.ID).text(o.Name);
				 $('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).append(optiontag);
			 });
		 });
	});
	
    //jQuery('div#loading').ajaxStart(function() {jQuery(this).show();});
    //jQuery('div#loading').ajaxStop( function() {jQuery(this).hide();});
}	

var findproducts = function() {
	
	var td = $(this).parent();
	var keyword = $('input[name="ProductSearch.QueryKeywords"]', td).val();
	
	$.post('/json/findproducts',
		   'findtype=QueryKeywords&keyword='+keyword,
		   function(data) {
			   dump(data.json.result);
			   
			   $.each(data.json.result.Product, function(i, o) {
				   
				   var productids = arrayize(o.ProductID);
				   
				   // todo: care Reference, UPC, ISBN, etc...
				   var productid = productids[0]['#text'];
				   log(productid);
				   foundproducts['R'+productid] = o;
				   
				   var divtag = $('div.producttemplate', td).clone().attr('class', 'product');
				   $(divtag).show();
				   $('img',             divtag).attr('src', o.StockPhotoURL);
				   $('div.producttext', divtag).html(o.Title);
				   if (o.ItemSpecifics) {
					   $.each(arrayize(o.ItemSpecifics.NameValueList), function(j, k) {
						   $('div.producttext', divtag).append('<br/>'+k.Name+':'+k.Value);
					   });
				   }
				   $('div.productid',   divtag).html(productids[0]['#text']);
				   $('div.foundproducts', td).append(divtag);
				   
			   });
			   $('div.foundproducts', td).slideDown('fast');
			   
			   // todo: why dump(foundproducts) shows empty array?
			   //dump(foundproducts);
		   },
		   'json');
}

function titlesearch_keyupdone(str)
{
	var str2 = $('input.filter[name=Title]').val();
	if (str == str2 && str != strdiff) {
		$('input[name=offset]').val(0);
		$('table#items tbody:gt(1)').remove();
		items();
	}
	strdiff = str;
	
	return;
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
		if (namev.length == 0) return;
		
		if (namev.length == 1) {
			
			if (namev[0] == '') return;
			
			if (accum[namev[0]] != undefined) {
                if ($.isArray(accum[namev[0]])) {
                    accum[namev[0]].push(value);
                    //accum[namev[0]].push(encodeURI(value));
                } else {
                    var tmpvalue = accum[namev[0]];
                    accum[namev[0]] = [tmpvalue];
                    accum[namev[0]].push(value);
                    //accum[namev[0]].push(encodeURI(value));
                }
            } else {
				accum[namev[0]] = value;
				//accum[namev[0]] = encodeURI(value);
			}
			
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

function summary()
{
	var ulorg = $('ul.accounts').clone();
	
	$.getJSON('/json/summary', function(data) {
		$('ul.accounts > li.allitems').append(' (<span>'+data.json.alluserids.allitems+'</span>)');
		$.each(data.json.alluserids, function(k, v) {
			$('ul.accounts > li > ul.accountaction li.'+k).append(' (<span>'+v+'</span>)');
		});
		
		$.each(data.json, function(k, o) {
			if (k == 'alluserids') return;
			
			var ul = ulorg.clone();
			$('li.allitems', ul).attr('class', k).html(k+' (<span>'+o.allitems+'</span>)');
			$('ul.accountaction', ul).attr('class', 'accountaction '+k);
			$.each(o, function(j, v) {
				$('li.'+j, ul).append(' (<span>'+v+'</span>)');
			});
			$('ul.accounts').append(ul.html());
			
			var optiontag = $('<option />').val(k).html(k);
			$('select[name="org.Seller.UserID"]', $('div#detailtemplate')).append(optiontag);
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
		 
		 var offset = parseInt($('input.filter[name=offset]').val());
		 var limit  = parseInt($('input.filter[name=limit]' ).val());
		 
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
		 
		 if (data.json.message != '') {
			 $('div#message').html(data.json.message);
			 timeout = setTimeout('refresh()', 1000);
		 }
		 //$('table#items').css('min-height', h+'px');
	 },
	 'json');
}

function getrow(idx, row)
{
	var id = idx;
	var dom = $('#rowtemplate').clone().attr('id', id);

	if (row.mod == null) {
		$('td.Title', dom).html('ERROR!!! MOD DOES NOT EXISTS.');
		return dom;
	}
	
	$('td.Title', dom).html(row.mod.Title);
	
	if (row.org.ListingDetails.ViewItemURL) {
		$('a.ItemID', dom)
			.attr('href', row.org.ListingDetails.ViewItemURL)
			.html(row.org.ItemID);
	} else {
		$('a.ItemID', dom).remove();
	}
	$('td.EndTime', dom).html(row.endtime+'<br/>'
							  +row.org.ListingDetails.EndTime);
	$('td.price',   dom).html(row.price);
	
	/* status(loading icon) */
	if (typeof(row.status) == 'string' && row.status != '') {
		$('input:checkbox', dom).css('visibility', 'hidden');
		$('input:checkbox', dom).parent().addClass('loading');
	}
	$('input:checkbox', dom).val(id);
	
	/* Picture */
	var pictstr = '';
	if (row.mod.PictureDetails) {
		if (typeof(row.mod.PictureDetails.PictureURL) == 'string') {
			pictstr = row.mod.PictureDetails.PictureURL;
		} else if (typeof(row.mod.PictureDetails.PictureURL) == 'object') {
			pictstr = row.mod.PictureDetails.PictureURL[0];
		} else if (typeof(row.mod.PictureDetails.GalleryURL) == 'string') {
			pictstr = row.mod.PictureDetails.GalleryURL;
		} else if (typeof(row.mod.PictureDetails.GalleryURL) == 'object') {
			pictstr = row.mod.PictureDetails.GalleryURL[0];
		}
	}
	if (pictstr != '') {
		$('img.PictureURL', dom).attr('src', pictstr);
	} else {
		$('img.PictureURL', dom).remove();
	}
	
	/* Labels */
	if (typeof(row.labels) == 'object') {
		$.each(row.labels, function(k, v) {
			$('div.labelwrap', dom).append($('<div>').attr('class', 'label').text(v));
		});
	}
	
	/*
	if (row.status == 'listing') {
		$('input:checkbox', dom).css('visibility', 'hidden');
		$('input:checkbox', dom).parent().addClass('loading');
	}
	$('input:checkbox', dom).val(id);
	*/
	
	$('td.UserID', dom).html(row.org.Seller.UserID);
	
	/* status icon */
	var src = '/icon/04/10/10.png';
	if (row.org.SellingStatus) {
		if (row.org.SellingStatus.ListingStatus == 'Active') {
			src = '/icon/04/10/02.png';
		} else if (row.org.SellingStatus.ListingStatus == 'Completed') {
			src = '/icon/04/10/10.png';
		}
	}
	$('img.status', dom).attr('src', src);
	
	if (row.status) {
		$('td.Title', dom).append('<br/><span class="status">'+row.status+'</span>');
	}
	if (row.errors) {
		$.each(row.errors, function(k, v) {
			if (v != '') {
				$('td.Title', dom).append('<br/>');
				var spantag = $('<span/>')
					.addClass('error')
					.text(v.LongMessage);
				$('td.Title', dom).append(spantag);
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

// todo: skip some forms if selected category is not a leaf category.
function setformelements(item)
{
	var id = item.id;
	var site = item.mod.Site;
	
	/* Country */
	$('select[name="mod.Country"]', '#'+id).children().remove();
	$.each(hash[site].eBayDetails.CountryDetails, function(k, v) {
		var optiontag = $('<option/>').val(v.Country).text(v.Description);
		$('select[name="mod.Country"]', '#'+id).append(optiontag);
	});
	
	/* Currency */
	$('select[name="mod.Currency"]', '#'+id).children().remove();
	$.each(hash[site].eBayDetails.CurrencyDetails, function(k, v) {
		var optiontag = $('<option/>').val(v.Currency).text(v.Description);
		$('select[name="mod.Currency"]', '#'+id).append(optiontag);
	});
	
	/* Categories */
	var tmppath = item.categorypath.slice(0); // just copy?
	tmppath.unshift(0);
	var tmppds = getcategorypulldowns(site, tmppath);
	$('select[name="mod.PrimaryCategory.CategoryID"]', '#'+id).parent().html(tmppds);
	
	var tmppc = hash[site].Categories;
	if (item.categorypath.length >= 2) {
		tmppc = tmppc['c'+item.categorypath[item.categorypath.length-2]];
	}
	var category = tmppc['c'+item.mod.PrimaryCategory.CategoryID];
	
	/* Condition */
	if (category.CategoryFeatures) {
		if (category.CategoryFeatures.ConditionEnabled == 'Disabled') {
			var optiontag = $('<option/>').html('(disabled)');
			$('select[name="mod.ConditionID"]', '#'+id).children().remove();
			$('select[name="mod.ConditionID"]', '#'+id).append(optiontag);
		} else {
			$('select[name="mod.ConditionID"]', '#'+id).html($('<option/>'));
			var conditions = category.CategoryFeatures.ConditionValues.Condition;
			for (i in conditions) {
				var value = conditions[i].ID;
				var label = conditions[i].DisplayName;
				var optiontag = $('<option/>').val(value).html(label);
				$('select[name="mod.ConditionID"]', '#'+id).append(optiontag);
			}
		}
	}
	
	/* Category2CS */
	if (category.Category2CS && category.Category2CS.CatalogEnabled) {
		// todo: find more than 2 Sets.
		
		$('span.CharacteristicsSetsName', '#'+id)
			.html(category.Category2CS.CharacteristicsSets.Name);
		
		$('input[name="ProductSearch.CharacteristicSetIDs.ID"]', '#'+id)
			.val(category.Category2CS.CharacteristicsSets.AttributeSetID);
		
		$('div.productsearchform', '#'+id).show();
	} else {
		$('div.productsearchform', '#'+id).hide();
	}
	
	/* ItemSpecifics */
	setformelements_itemspecifics(item);
	
	$('form[name=APIForm]', '#'+id)
		.attr('id',   'APIForm'+id)
		.attr('name', 'APIForm'+id);
	
	/* ListingDuration */
	$('select[name="mod.ListingDuration"]', '#'+id).children().remove();
	if ($('select[name="mod.ListingType"]').val() == 'Chinese') {
		var optiontag = $('<option/>').val('Days_1').html('1 day');
		$('select[name="mod.ListingDuration"]', '#'+id).append(optiontag);
	}
	var durationsetid = null;
	for (i in category.CategoryFeatures.ListingDuration) {
		if (category.CategoryFeatures.ListingDuration[i]['@type'] == item.mod.ListingType) {
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
				$('select[name="mod.ListingDuration"]', '#'+id).append(optiontag);
			}
			break;
		}
	}
	
	/* ShippingPackage */
	var _sdcsr = 'ShippingDetails.CalculatedShippingRate';
	$('select[name="mod.'+_sdcsr+'.ShippingPackage"]', '#'+id).children().remove();
	var optiontag = $('<option/>').val('').html('');
	$('select[name="mod.'+_sdcsr+'.ShippingPackage"]', '#'+id).append(optiontag);
	$.each(hash[site].eBayDetails.ShippingPackageDetails, function(i, o) {
		var optiontag = $('<option/>').val(o.ShippingPackage).html(o.Description);
		$('select[name="mod.'+_sdcsr+'.ShippingPackage"]', '#'+id).append(optiontag);
	});
	
	/* ShippingService */
	setformelements_shipping(item);

	/* DispatchTimeMax */
	$('select[name="mod.DispatchTimeMax"]', '#'+id).children().remove();
	var optiontag = $('<option/>').val('').html('Select a handling time');
	$('select[name="mod.DispatchTimeMax"]', '#'+id).append(optiontag);
	$.each(hash[site].eBayDetails.DispatchTimeMaxDetails, function(i, o) {
		var optiontag = $('<option/>').val(o.DispatchTimeMax).html(o.Description);
		$('select[name="mod.DispatchTimeMax"]', '#'+id).append(optiontag);
	});
	
	/* PaymentMethods */
	// Do not use GeteBayDetails to discover the valid payment methods for a site.
	$('td.paymentmethod', '#'+id).children().remove();
	$.each(hash[site].CategoryFeatures.SiteDefaults.PaymentMethod, function(i, o) {
		var idforlabel = id+'.PaymentMethods.'+i;
		
		var checkboxtag = $('<input/>')
			.attr('type', 'checkbox')
			.attr('id', idforlabel)
			.attr('name', 'mod.PaymentMethods')
			.val(o);
		
		var labeltag = $('<label/>')
			.attr('for', idforlabel)
			.html(o+'('+i+')');
		
		var divtag2 = $('<div/>');
		$(divtag2).append(checkboxtag);
		$(divtag2).append(labeltag);

		if (o == 'PayPal') {
			var inputtag = $('<input/>')
				.attr('type', 'text')
				.attr('name', 'mod.PayPalEmailAddress');
			$(divtag2).append('<br/>&nbsp;&nbsp;&nbsp;&nbsp;PayPal email address : ');
			$(divtag2).append(inputtag);

			var checkbox = $('<input/>')
				.attr('type', 'checkbox')
				.attr('name', 'mod.AutoPay')
				.attr('value', 'true');
			$(divtag2).append('<br/>&nbsp;&nbsp;&nbsp;&nbsp;');
			$(divtag2).append(checkbox);
			$(divtag2).append('Require immediate payment when buyer uses Buy It Now');
		}
		
		$('td.paymentmethod', '#'+id).append(divtag2);
	});
	
	/* ReturnPolicy */
	$('select[name="mod.ReturnPolicy.ReturnsAcceptedOption"]', '#'+id).children().remove();
	var optiontag = $('<option/>').val('').text('');
	$('select[name="mod.ReturnPolicy.ReturnsAcceptedOption"]', '#'+id).append(optiontag);
	$.each(hash[site].eBayDetails.ReturnPolicyDetails.ReturnsAccepted, function(i, o) {
		var optiontag = $('<option/>').val(o.ReturnsAcceptedOption).text(o.Description);
		$('select[name="mod.ReturnPolicy.ReturnsAcceptedOption"]', '#'+id).append(optiontag);
	});
	
	$('select[name="mod.ReturnPolicy.ReturnsWithinOption"]', '#'+id).children().remove();
	var optiontag = $('<option/>').val('').text('');
	$('select[name="mod.ReturnPolicy.ReturnsWithinOption"]', '#'+id).append(optiontag);
	$.each(hash[site].eBayDetails.ReturnPolicyDetails.ReturnsWithin, function(i, o) {
		var optiontag = $('<option/>').val(o.ReturnsWithinOption).text(o.Description);
		$('select[name="mod.ReturnPolicy.ReturnsWithinOption"]', '#'+id).append(optiontag);
	});
	
	$('select[name="mod.ReturnPolicy.RefundOption"]', '#'+id).children().remove();
	var optiontag = $('<option/>').val('').text('');
	$('select[name="mod.ReturnPolicy.RefundOption"]', '#'+id).append(optiontag);
	$.each(hash[site].eBayDetails.ReturnPolicyDetails.Refund, function(i, o) {
		var optiontag = $('<option/>').val(o.RefundOption).text(o.Description);
		$('select[name="mod.ReturnPolicy.RefundOption"]', '#'+id).append(optiontag);
	});
	
	$('select[name="mod.ReturnPolicy.ShippingCostPaidByOption"]', '#'+id).children().remove();
	var optiontag = $('<option/>').val('').text('');
	$('select[name="mod.ReturnPolicy.ShippingCostPaidByOption"]', '#'+id).append(optiontag);
	$.each(hash[site].eBayDetails.ReturnPolicyDetails.ShippingCostPaidBy, function(i, o) {
		var optiontag = $('<option/>').val(o.ShippingCostPaidByOption).text(o.Description);
		$('select[name="mod.ReturnPolicy.ShippingCostPaidByOption"]', '#'+id).append(optiontag);
	});
	
	/* ThemeGroup */
	$('select[name="ListingDesigner.GroupID"]', '#'+id).children().remove();
	var emptyoptiontag = $('<option/>').val('').text('(not selected)');
	$('select[name="ListingDesigner.GroupID"]', '#'+id).append(emptyoptiontag);
	$.each(hash[site].ThemeGroup, function(i, o) {
		var optiontag = $('<option/>').val(o.GroupID).text(o.GroupName);
		$('select[name="ListingDesigner.GroupID"]', '#'+id).append(optiontag);
	});
	
	$('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).children().remove();
	var emptyoptiontag = $('<option/>').val('').text('(not selected)');
	$('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).append(emptyoptiontag);
	$.each(hash[site].DescriptionTemplate, function(i, o) {
		var optiontag = $('<option/>').val(o.ID).text(o.Name);
		$('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).append(optiontag);
	});
	
	return;
}

function setformelements_shipping(item)
{
	var id = item.id;
	var site = item.mod.Site;
	
	var dmsttype = item.ShippingDetails.ShippingType.domestic;
	var intltype = item.ShippingDetails.ShippingType.international;
	
	var packagetype = '';
	if (item.mod.ShippingDetails.CalculatedShippingRate) {
		packagetype = item.mod.ShippingDetails.CalculatedShippingRate.ShippingPackage;
		log(packagetype);
	}
	
	// hide and show
	if (dmsttype == 'NoShipping') {
		$('tbody.shippingmainrows', '#'+id).hide();
		$('tbody.internationalshippingmainrows', '#'+id).hide();
		// todo: remove 2>=
		return;
	} else {
		$('tbody.shippingmainrows', '#'+id).show();
		$('tbody.internationalshippingmainrows', '#'+id).show();
	}
	if (intltype == 'NoShipping') {
		$('tbody.internationalshippingmainrows', '#'+id).hide();
		// todo: remove 2>=
	} else {
		$('tbody.internationalshippingmainrows', '#'+id).show();
	}
	
	if (dmsttype == 'Calculated') {
		$('tr.packagetype, tr.dimensions, tr.weight', '#'+id).show();
	} else {
		$('tr.packagetype, tr.dimensions, tr.weight', '#'+id).hide();
	}
	
	var _dsso = 'ShippingDetails.ShippingServiceOptions';
	var _isso = 'ShippingDetails.InternationalShippingServiceOption';
	
	// set <option> tags
	$('select[name="mod.'+_dsso+'.0.ShippingService"]', '#'+id).children().remove();
	$('select[name="mod.'+_dsso+'.0.ShippingService"]', '#'+id)
		.append($('<option/>').val('').text('(not selected)'));
	
	$('select[name="mod.'+_isso+'.0.ShippingService"]', '#'+id).children().remove();
	$('select[name="mod.'+_isso+'.0.ShippingService"]', '#'+id)
		.append($('<option/>').val('').text('(not selected)'));
	
	$.each(hash[site].eBayDetails.ShippingServiceDetails, function(i, o) {
		if (o.ValidForSellingFlow != 'true') return;
		
		if (parseInt(o.ShippingServiceID) < 50000) {
			if ($.inArray(dmsttype, o.ServiceType) >= 0 || o.ServiceType == dmsttype) {
				
				if (dmsttype == 'Calculated') {
					var packages = arrayize(o.ShippingServicePackageDetails);

					for (i in packages) {
						if (packages[i].Name == packagetype) {
							$('select[name="mod.'+_dsso+'.0.ShippingService"]', '#'+id)
								.append($('<option/>').val(o.ShippingService).html(o.Description));
						}
					}
				} else {
					$('select[name="mod.'+_dsso+'.0.ShippingService"]', '#'+id)
						.append($('<option/>').val(o.ShippingService).html(o.Description));
				}
				
			}
		} else {
			if ($.inArray(intltype, o.ServiceType) >= 0 || o.ServiceType == intltype) {
				$('select[name="mod.'+_isso+'.0.ShippingService"]', '#'+id)
					.append($('<option/>').val(o.ShippingService).html(o.Description));
			}
		}
	});

	// ShippingLocation
	$('div.ShipToLocation', '#'+id).children().remove();
	$.each(hash[site].eBayDetails.ShippingLocationDetails, function(i, o) {
		var idforlabel = id+'.'+_isso+'.0.ShipToLocation.'+o.ShippingLocation;
		
		var checkboxtag = $('<input/>')
			.attr('type', 'checkbox')
			.attr('id', idforlabel)
			.attr('name', 'mod.'+_isso+'.0.ShipToLocation')
			.val(o.ShippingLocation);
		
		var labeltag = $('<label/>')
			.attr('for', idforlabel)
			.html(o.Description);
		
		var divtag = $('<div/>');
		$(divtag).append(checkboxtag);
		$(divtag).append(labeltag);
		
		$('div.ShipToLocation', '#'+id).append(divtag);
	});
	
	// copy 2,3,4,...
	// todo: don't copy when already 2, 3, ... is shown.
	var div0s = $('div.ShippingService0', '#'+id);
	if ($.isArray(item.mod.ShippingDetails.ShippingServiceOptions)) {
		$.each(item.mod.ShippingDetails.ShippingServiceOptions, function(k, v) {
			if (v.ShippingServicePriority == 1) return;
			addsso($('a.addsso:first', '#'+id).get());
		});
	}
	if ($.isArray(item.mod.ShippingDetails.InternationalShippingServiceOption)) {
		$.each(item.mod.ShippingDetails.InternationalShippingServiceOption, function(k, v) {
			if (v.ShippingServicePriority == 1) return;
			addsso($('a.addsso:last', '#'+id).get());
		});
	}
	
	return;
}

function addsso(elm)
{
	var divs = $('div[class^=ShippingService]', $(elm).parent());
	
	var tmpname = $('input:first', divs[0]).attr('name')
		.replace(/^mod\.ShippingDetails\./, '')
		.replace(/\..+$/, '');
	
	if (tmpname == 'ShippingServiceOptions') {
		if (divs.length >= 4) return;
	} else if (tmpname == 'InternationalShippingServiceOption') {
		if (divs.length >= 5) return;
	}
	
	var id = $(elm).closest('tbody.itemrow').attr('id');
	var sscopy = $(divs[0]).clone();
	
	var ssidx = divs.length;
	$(sscopy).attr('class', 'ShippingService'+ssidx);
	$.each($('select, input[type=text]', sscopy), function(j, o) {
		var orgname = $(o).attr('name');
		$(o).val('').attr('name', orgname.replace('.0.', '.'+ssidx+'.'));
	});
	$.each($('input[type=checkbox]', sscopy), function(j, o) {
		var orgname = $(o).attr('name');
		$(o).removeAttr('checked').attr('name', orgname.replace('.0.', '.'+ssidx+'.'));
		if (orgname.match(/ShipToLocation/)) {
			var orgid = $(o).attr('id');
			$(o).attr('id', orgid.replace('.0.', '.'+ssidx+'.'));
		}
	});
	$.each($('label[for*=ShipToLocation]', sscopy), function(j, o) {
		var orgfor = $(o).attr('for');
		$(o).attr('for', orgfor.replace('.0.', '.'+ssidx+'.'));
	});
	//alert(ssidx);
	//$('input[name^=Priority]', sscopy).val(ssidx+1);
	
	$(elm).before(sscopy);
	
	if (tmpname == 'ShippingServiceOptions') {
		if (divs.length >= 3) $(elm).hide();
	} else if (tmpname == 'InternationalShippingServiceOption') {
		if (divs.length >= 4) $(elm).hide();
	}
	
	return false;
}

function resizediv()
{
	var w = $('div#container').width()-225;
	var h = $('body').height() - 10;
	//var headerheight = $('div#header').height();
	//alert(headerheight);
	
	$('div#content').width(w);
	$('div#header').width($('div#container').width()-40);
	$('div#message').width($('div#container').width());
	$('table#items').width(w);
	$('div#toolbar').height($(window).height()-99);
	$('tbody#rowloading td').height($(window).height()-99);
	
	//$('div#content').css('margin-top', headerheight+'px');
	//$('div#toolbar').css('margin-top', headerheight+'px');
	
	return;
}

var changeCategory = function() {
	
	var id = $(this).closest('tbody.itemrow').attr('id');
	var site = $('select[name="mod.Site"]', '#'+id).val();
	
	$(this).nextAll().remove();
	$('select.category:last', '#'+id).attr('name', 'mod.PrimaryCategory.CategoryID');
	
	var categorypulldowns = $('select.category', '#'+id).get();
	var categorypath = new Array();
	for (node in categorypulldowns) {
		categorypath.push(categorypulldowns[node].value);
	}
	
	$.getJSON
	('/json/gc2?site='+site+'&path=0.'+categorypath.join('.'),
	 function(data) {
		 
		 hash[site].Categories = data.json.gc2.Categories;
		 
		 var item_modifing =
			 $('input[type=text], input:checked, input[type=hidden], select, textarea', '#'+id)
			 .extractObject();
		 
		 item_modifing.id = id;
		 item_modifing.categorypath = categorypath;
		 
		 setformelements(item_modifing);
		 fillformvalues(item_modifing);
		 
		 return;
	 });
	
	return;
}

var clickEdit = function() {
	
	id = $(this).closest('tbody.itemrow').attr('id');
	item = rowsdata[id];
	dom = $('div.detail', 'div#detailtemplate').clone().css('display', 'block');
	
	/* preserve selected tab */
	tab = $('ul.tabNav > li.current', 'tbody#'+id);
	tabnum = tab.prevAll().length + 1;
	$('.tabNav',       dom).children('.current').removeClass('current');
	$('.tabContainer', dom).children('.current').hide();
	$('.tabContainer', dom).children('.current').removeClass('current');
	$('.tabNav',       dom).children('li:nth-child('+tabnum+')').addClass('current');
	$('.tabContainer', dom).children('div:nth-child('+tabnum+')').show();
	$('.tabContainer', dom).children('div:nth-child('+tabnum+')').addClass('current');
	
	$('div.detail', 'tbody#'+id).replaceWith(dom);
	
	setformelements(item);
	fillformvalues(item);
	
	// todo: compare to CKEditor
	$('textarea[name="mod.Description"]', 'tbody#'+id).wysiwyg();
	
	showbuttons(dom, 'save,cancel');
	
	return;
}

var save = function() {
	
	id = $(this).closest('tbody.itemrow').attr('id');
	detail = $(this).closest('div.detail');
	
	// temp remove
	$('input.remove',  detail).remove();
	$('select.remove', detail).remove();
	
	// todo: varidation check
	if ($('select[name="mod.PrimaryCategory.CategoryID"]', '#'+id).val() == '') {
		alert('category error.');
		return false;
	}
	
	$.each($('img.PictureDetails_PictureURL', '#'+id), function(i, imgelm) {
		imgsrc = $(imgelm).attr('src');
		if (imgsrc == '/img/noimage.jpg') {
			$("input[name='PictureDetails[PictureURL]["+(i+1)+"]']", '#'+id).remove();
		} else {
			$("input[name='PictureDetails[PictureURL]["+(i+1)+"]']", '#'+id).val(imgsrc);
		}
	});
	
	// todo: Why Opera can't include <select> tags?
	// todo: Don't use numeric keys that causes "NCNames cannot start with...." javascript error.
	
	// remove empty value forms
	_dsso = 'ShippingDetails.ShippingServiceOptions';
	_isso = 'ShippingDetails.InternationalShippingServiceOptions';
	for (i = 0; i <= 3; i++) {
		if ($('select[name="'+_dsso+'.'+i+'.ShippingService"]', '#'+id).val() == '') {
			$('input[name="'+_dsso+'.'+i+'.ShippingServicePriority"]', '#'+id).val('');
		}
	}
	for (i = 0; i <= 4; i++) {
		if ($('select[name="'+_isso+'.'+i+'.ShippingService"]', '#'+id).val() == '') {
			$('input[name="'+_isso+'.'+i+'.ShippingServicePriority"]', '#'+id).val('');
		}
	}

	// todo: remove empty ItemSpecifics
	
	
	postdata = $('input[type=text], input:checked, input[type=hidden], select, textarea', '#'+id)
		.extractObject();
	postdata = JSON.stringify(postdata);
	postdata = encodeURIComponent(postdata);
	
	$.post('/json/save',
		   'id='+id+'&json='+postdata,
		   function(data) {
			   var item = data.json.item;
			   rowsdata[id] = item;
			   dump(data.json);
			   
			   var site = item.mod.Site;
			   
			   hash[site] = new Object;
			   hash[site].eBayDetails         = data.json.eBayDetails;
			   hash[site].Categories          = data.json.Categories;
			   hash[site].CategoryFeatures    = data.json.CategoryFeatures;
			   hash[site].ThemeGroup          = data.json.ThemeGroup;
			   hash[site].DescriptionTemplate = data.json.DescriptionTemplate;
			   
			   setformelements(data.json.item);
			   showformvalues(item);
			   showbuttons(detail, 'edit,copy,delete');
			   $('div.productsearchform', '#'+id).remove();
			   
		   },
		   'json');
	
	return false;
}

var clickCancel = function() {
	
	id = $(this).closest('tbody.itemrow').attr('id');
	setformelements(rowsdata[id]);
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
	var id = $(this).closest('tbody.itemrow').attr('id');
	var site = $(this).val();
	
	$.getJSON
	('/json/site?site='+site,
	 function(data) {
		 
		 hash[site] = new Object;
		 hash[site].eBayDetails      = data.json.eBayDetails;
		 hash[site].Categories       = data.json.Categories;
		 hash[site].CategoryFeatures = data.json.CategoryFeatures;
		 
		 var item_modifing =
			 $('input[type=text], input:checked, input[type=hidden], select, textarea', '#'+id)
			 .extractObject();
		 
		 item_modifing.id = id;
		 item_modifing.categorypath = [];
		 
		 setformelements(item_modifing);
		 fillformvalues(item_modifing);
		 
		 return;
	 });
	
	return;
}

var clickTitle = function() {
	
	var id = $(this).closest('tbody').attr('id');
	
	if ($('tr.row2 td', '#'+id).html().match(/^<div/i)) {
		//$('div.detail', '#'+id).slideToggle('fast');
		$('div.detail', '#'+id).toggle();
		return false;
	}
	
	detail = $('div.detail', 'div#detailtemplate').clone();
	$('td:nth-child(2)', detail).hide();
	$('tr.row2 td', '#'+id).html(detail);
	$('div.detail', '#'+id).toggle();
	
	$.post
	('/json/item',
	 'id='+id,
	 function(data) {
		 var item = data.json.item;
		 rowsdata[id] = item;
		 dump(data.json);
		 
		 var site = item.mod.Site;
		 
		 hash[site] = new Object;
		 hash[site].eBayDetails         = data.json.eBayDetails;
		 hash[site].Categories          = data.json.Categories;
		 hash[site].CategoryFeatures    = data.json.CategoryFeatures;
		 hash[site].ThemeGroup          = data.json.ThemeGroup;
		 hash[site].DescriptionTemplate = data.json.DescriptionTemplate;
		 
		 
		 setformelements(item);
		 showformvalues(item);
		 $('div.productsearchform', '#'+id).remove();
		 
		 $('td:nth-child(2)', '#'+id).show();
		 
		 //$('div.pictures', '#'+id).append('<pre>'+$.dump(item.mod.PictureDetails)+'</pre>');
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

function getcategorypulldowns(site, path)
{
	wrapper = $('<div/>');
	
	for (i in path) {
		
		var categoryid = path[i];
		if (hash[site].Categories['c'+categoryid] == undefined) break;
		
		var selecttag = $('<select class="category"/>').attr('name', 'categorypath.'+i);
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
	
	$('select.category:last', wrapper).attr('name', 'mod.PrimaryCategory.CategoryID');
	
	return wrapper.children();
}

function refresh()
{
	var postdata = '';
	
	var loadings = $('td.loading > input:checkbox[name=id][value!=on]');
	if (loadings.length > 0) {
		$.each($('td.loading > input:checkbox[name=id][value!=on]'), function(k, v) {
			postdata += '&id='+$(v).attr('value');
		});
	}
	
	$.post
	('/json/refresh',
	 postdata,
	 function(data) {
		 
		 dump(data.json);
		 
		 /* message */
		 if (data.json.message == '') {
			 $('div#message').html('');
		 } else {
			 $('div#message').html(data.json.message);
		 }
		 
		 /* summary */
		 if (data.json.summary) {
			 
			 $('ul.accounts > li.allitems > span').html(data.json.summary.alluserids.allitems);
			 $.each(data.json.summary.alluserids, function(k, v) {
				 $('ul.accounts > li > ul[class=accountaction] > li.'+k+' > span').html(v);
			 });
			 
			 $.each(data.json.summary, function(k, o) {
				 if (k == 'alluserids') return;
				 
				 $('ul.accounts > li.'+k+' > span').html(o.allitems);
				 $.each(data.json.summary.alluserids, function(j, v) {
					 $('ul.accounts > li > ul[class="accountaction '+k+'"] > li.'+j+' > span').html(v);
				 });
			 });
		 }
		 
		 /* items */
		 if (data.json.items) {
			 $.each(data.json.items, function(idx, row) {
				 dom = getrow(idx, row);
				 $('#'+idx).replaceWith(dom);
				 if (typeof(row.status) == 'string' && row.status != '') {
					 //
				 } else {
					 //$('input:checkbox', dom).css('visibility', '').removeAttr('checked');
					 $('input:checkbox', dom).parent().removeClass('loading');
				 }
				 rowsdata[idx] = row;
			 });
		 }
		 
		 /* timeout */
		 if (data.json.message != '') {
			 timeout = setTimeout('refresh()', 1000);
		 } else if (postdata != '') {
			 timeout = setTimeout('refresh()', 1000);
		 } else {
			 clearTimeout(timeout);
		 }
	 },
	 'json');
	
	return;
	
	// todo: check firefox pseudo class .... warning
	
	$.post('/json/items',
		   loadings.serialize(),
		   function(data) {
			   dump(data.json);
			   $.each(data.json.items, function(idx, row) {
				   dom = getrow(idx, row);
				   if (row.status == '') {
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

function log(str)
{
	$('#log').append(str+'<br/>');
}

function updateduration(id)
{
	site = $('select[name="mod.Site"]', '#'+id).val();
	listingtype = $('select[name="mod.ListingType"]', '#'+id).val();
	tmpo = hash[site]['category']['features'][categoryid]['ListingDuration'];
	
	sel = $('<select/>').attr('name', 'ListingDuration');
	$.each(rowsdata[id]['categoryfeatures']['ListingDuration'][listingtype], function(k, v) {
		opt = $('<option/>').val(k).text(v);
		sel.append(opt);
	});
	$('select[name=ListingDuration]', '#'+id).replaceWith(sel);
	
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

function showformvalues(item)
{
	if (item.mod.PictureDetails) {
		item.mod.PictureDetails.PictureURL
			= arrayize(item.mod.PictureDetails.PictureURL);
	}
	if (item.mod.ShippingDetails.ShippingServiceOptions) {
		item.mod.ShippingDetails.ShippingServiceOptions
			= arrayize(item.mod.ShippingDetails.ShippingServiceOptions);
	}
	
	var detail = $('div.detail', '#'+item.id);
	
	/* text */
	$.each($('input[type=text]', detail), function(i, form) {
		var formname = $(form).attr('name');
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		try {
			eval("tmpvalue = item"+formname);
			//var tmpvalue = item[formname];
			
			if (tmpvalue == null) tmpvalue = '';
			
			var htmlencoded = $('<div/>').text(tmpvalue).html();
			$(form).replaceWith(htmlencoded);
			
			if ($(form).attr('name').match(/^mod.PictureDetails.PictureURL./)) {
				var imgclass = $(form).attr('name')
					.replace(/^mod.PictureDetails.PictureURL./, 'PD_PURL_');
				$('img.'+imgclass, detail).attr('src', tmpvalue);
			}
		} catch (err) {
			$(form).replaceWith('');
			//$(detail).prepend('ERR: ['+formname+']'+err.description+'<br />');
		}
	});
	
	/* textarea */
	$.each($('textarea', detail), function(i, form) {
		var formname = $(form).attr('name');
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		try {
			eval("tmpvalue = item"+formname);
			
			if (tmpvalue == null) tmpvalue = '';
			
			var htmlencoded = $('<div/>').text(tmpvalue).html();
			$(form).replaceWith(htmlencoded);

		} catch (err) {
			$(form).replaceWith('');
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
			$(form).replaceWith(label);
		} catch (err) {
			$(form).replaceWith('');
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
						$(form).replaceWith('');
					}
				}
			} else {
				if (tmpvalue == $(form).val()) {
					$(form).replaceWith('');
				}
			}
			
		} catch (err) {
			$(form).replaceWith('');
			//$(detail).before('ERR: '+formname+' '+err+'<br />');
		}
	});
	$.each($('input[type=checkbox]', detail), function(i, form) {
		var idforlabel = $(form).attr('id');
		$(form).remove();
		$('label[for="'+idforlabel+'"]').remove();
	});
	
	/* PictureDetails */
	if (item.mod.PictureDetails) {
		$.each($('img.PictureDetails_PictureURL', detail), function(i, imgtag) {
			if (item.mod.PictureDetails.PictureURL[i]) {
				$(this).attr('src', item.mod.PictureDetails.PictureURL[i]);
			}
		});
	}
	$('input[type=file]', detail).remove();
	
	/* Description */
	iframe = $('<iframe/>')
		.attr('class', 'description')
		.attr('src', 'about:blank');
	
	iframe.load(function() {
		$(this).contents().find('body').append(item.mod.Description);
		var iframeheight = $(this).contents().find('body').height() + 16
		$(this).css('height', iframeheight+'px');
	});
	$('textarea[name="mod.Description"]', detail).replaceWith(iframe);
	
	return;
}

function fillformvalues(item)
{
	if (item.mod.ShippingDetails.ShippingServiceOptions) {
		item.mod.ShippingDetails.ShippingServiceOptions
			= arrayize(item.mod.ShippingDetails.ShippingServiceOptions);
	}
	
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
	$.each($('input[type=checkbox]', detail), function(i, form) {
		var formname = $(form).attr('name');
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		try {
			eval("var tmpvalue = item"+formname);
			
			if (typeof(tmpvalue) == 'object') {
				for (i in tmpvalue) {
					if (tmpvalue[i] == $(form).val()) {
						$(form).attr('checked', 'checked');
					}
				}
			} else {
				if (tmpvalue == $(form).val()) {
					$(form).attr('checked', 'checked');
				}
			}
			
		} catch (err) {
			//$(detail).prepend('ERR: '+err.description+'<br />');
		}
	});
	
	return;
}

/* ItemSpecifics */
function setformelements_itemspecifics(item)
{
	// todo: not return when undefined. (show empty forms)
	if (item.mod.ItemSpecifics == undefined) return;
	
	var detail = $('div.detail', '#'+item.id);
	$('table.ItemSpecifics', '#'+item.id).children().remove();
	
	var categoryid = item.mod.PrimaryCategory.CategoryID;
	var parentid = item.categorypath[item.categorypath.length-2];
	var category = hash[item.mod.Site]['Categories']['c'+parentid]['c'+categoryid];
	
	var specifics = item.mod.ItemSpecifics.NameValueList;
	var recomm = category.CategorySpecifics.NameRecommendation;
	
	var specificskey = new Array();
	for (i in specifics) {
		specificskey[specifics[i].Name] = i;
	}
	
	var recommkey = new Array();
	for (i in recomm) {
		recommkey[recomm[i].Name] = i;
	}
	
	/* Existing specifics */
	for (i in specifics) {
		var trtag = setformelements_itemspecifics_values(item.id,
														 i,
														 recomm[recommkey[specifics[i].Name]],
														 specifics[i]);
		$('table.ItemSpecifics', detail).append(trtag);
	}
	
	/* Remaining recommended specifics */
	var addspidx = specifics.length;
	for (i in recomm) {
		if (specificskey[recomm[i].Name] != null) continue;
		
		var trtag = setformelements_itemspecifics_values(item.id,
														 addspidx,
														 recomm[i],
														 null);
		
		$('table.ItemSpecifics', detail).append(trtag);
		
		addspidx++;
	}
	
	return;
}

function setformelements_itemspecifics_values(id, i, recomm, specific)
{
	var trtag = $('<tr />');

	/* Name */
	var tdtag = $('<td />');
	var inputtag = $('<input />')
		.attr('type', 'text')
		.attr('Name', 'mod.ItemSpecifics.NameValueList.'+i+'.Name');
	$(tdtag).append(i);
	$(tdtag).append(inputtag);
	if (specific == null) {
		$(inputtag).val(recomm.Name)
	}
	$(trtag).append(tdtag);
	
	/* Value */
	/* SelectionMode: one of FreeText, Prefilled, SelectionOnly */
	var tdtag = $('<td/>');
	
	if (recomm == null) {
		
		var inputtag = $('<input/>')
			.attr('type', 'text')
			.attr('Name', 'mod.ItemSpecifics.NameValueList.'+i+'.Value');
		var tdtag = $('<td/>').append(inputtag);
		
		$(trtag).append(tdtag);
		
	} else if (recomm.ValidationRules.SelectionMode == 'FreeText'
			   && recomm.ValidationRules.MaxValues == '1') {
		
		var inputtag = $('<input/>')
			.attr('type', 'text')
			.attr('Name', 'mod.ItemSpecifics.NameValueList.'+i+'.Value');
		$(tdtag).append(inputtag);
		
		if (recomm.ValueRecommendation != null) {
			var selecttag = $('<select/>')
				.attr('Name', 'mod.ItemSpecifics.NameValueList.'+i+'.Value.selector')
				.addClass('remove')
				.append($('<option/>').html('(select from list)'));
			
			var arrvr = arrayize(recomm.ValueRecommendation);
			for (j in arrvr) {
				var optiontag = $('<option/>').val(arrvr[j].Value).html(arrvr[j].Value);
				$(selecttag).append(optiontag);
			}
			
			$(tdtag).append(selecttag);
		}
		
	} else if (recomm.ValidationRules.SelectionMode == 'FreeText'
			   && recomm.ValidationRules.MaxValues != '1') {
		
		var divtag = $('<div/>');
		
		var checkboxidx = 0;
		
		if (specific != null) {
			for (j in specific.Value) {
				var value = specific.Value[j];

				// skip if exists in ValueRecommendation
				var existinrecomm = false;
				for (k in recomm.ValueRecommendation) {
					if (recomm.ValueRecommendation[k].Value == value) {
						existinrecomm = true;
						break;
					}
				}
				if (existinrecomm == true) continue;
				
				// add custom value checkbox
				var idforlabel = id+'.ItemSpecifics.NameValueList.'+i+'.Value.'+checkboxidx;
				
				var checkboxtag = $('<input/>')
					.attr('id', idforlabel)
					.attr('Name', 'mod.ItemSpecifics.NameValueList.'+i+'.Value')
					.attr('type', 'checkbox')
					.val(value);
				
				var labeltag = $('<label/>')
					.attr('for', idforlabel)
					.html(value);
				
				var divtag2 = $('<div/>');
				$(divtag2).append(checkboxidx);
				$(divtag2).append(checkboxtag);
				$(divtag2).append(labeltag);
				$(divtag).append(divtag2);
				
				checkboxidx++;
			}
		}
		
		for (j in recomm.ValueRecommendation) {
			
			var idforlabel = id+'.ItemSpecifics.NameValueList.'+i+'.Value.'+checkboxidx;
			
			var checkboxtag = $('<input/>')
				.attr('id', idforlabel)
				.attr('Name', 'mod.ItemSpecifics.NameValueList.'+i+'.Value')
				.attr('type', 'checkbox')
				.val(recomm.ValueRecommendation[j].Value);
			
			var labeltag = $('<label/>')
				.attr('for', idforlabel)
				.html(recomm.ValueRecommendation[j].Value);
			
			var divtag2 = $('<div/>');
			$(divtag2).append(checkboxidx);
			$(divtag2).append(checkboxtag);
			$(divtag2).append(labeltag);
			$(divtag).append(divtag2);
			
			checkboxidx++;
		}
		
		$(tdtag).append(divtag);
		
	} else if (recomm.ValidationRules.SelectionMode == 'SelectionOnly'
			   && recomm.ValidationRules.MaxValues == '1') {
		
		var selecttag = $('<select/>')
			.attr('Name', 'mod.ItemSpecifics.NameValueList.'+i+'.Value')
			.append($('<option/>').val('').html('(select from list)'));
		
		for (j in recomm.ValueRecommendation) {
			var optiontag = $('<option/>')
				.val(recomm.ValueRecommendation[j].Value)
				.html(recomm.ValueRecommendation[j].Value);
			$(selecttag).append(optiontag);
		}
		
		$(tdtag).append(selecttag);
		
		
	} else {
		
		$(tdtag).append('<pre>'+$.dump(recomm)+'</pre>');
		
	}
	
	// Help URL
	if (recomm != null) {
		if (recomm.HelpURL) {
			var atag = $('<a/>')
				.attr('href', recomm.HelpURL)
				.attr('target', '_blank')
				.html('help');
			$(tdtag).append(atag);
		}
	}
	
	$(trtag).append(tdtag);
	
	return trtag;
}
