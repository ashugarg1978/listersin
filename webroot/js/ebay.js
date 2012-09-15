/* store rows data */
var rowsdata = new Array();
var hasmore  = false;
var strdiff  = '';
var timeout  = null;
var foundproducts = new Array();

/* initialize */
$(function() {
	
	if (typeof(hash) == 'undefined') {
		resizediv_index();
		bindevents_index();
		return;
	}
	
	resizediv();
	bindevents();
	summary();
	//dump(hash);
	
	// todo: same code
	$.each(hash, function(k, v) {
		var optiontag = $('<option />').val(k).html(k);
		$('select[name="mod.Site"]', 'div#detailtemplate').append(optiontag);
	});
	
	/* scheduledays */
	$.each(scheduledays, function(k, v) {
		var optiontag = $('<option />').val(k).html(v);
		$('select[name="ScheduleTime.date"]', 'div#detailtemplate').append(optiontag);
	});
	
	
	//$.ajaxSetup({async: false});
	
	$('ul.accounts > li.allitems').click();
	//$('ul.accounts > li > ul:first').show();
	//$('li.active', $('ul.accountaction:first')).click();
	
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
	
	$('ul.pictures').sortable();
	
	return;
});

function bindevents_index()
{
	/* Sign up button */
	$('div#signupbox button').live('click', function() {
    
		var postdata = $('input', $(this).closest('form')).serialize();
		
		$.post('/json/signup',
			     postdata,
			     function(data) {
				     if (data.json.result == true) {
					     $('#signupmessage')
						     .css('color', 'blue')
						     .html('Confirmation mail was sent to<br/>'+data.json.message+'!');
               // todo: please also check spam folder.
				     } else {
					     $('#signupmessage')
						     .css('color', 'red')
						     .html(data.json.message);
				     }
			     },
			     'json');
		
		return false;
	});
}

function bindevents()
{
	$(window).resize(resizediv);
	
	/* Add eBay account */
	$('button.addebayaccount').live('click', function() {
    
    if (checkdemoaccount()) return;
		
		var loadingtext = $('<span/>')
			.css('color', '#f00')
			.css('margin-left', '10px')
			.html('<img src="/img/indicator.gif"/> please wait, redirecting to ebay site...');
		$(this).after(loadingtext);
    
		$.post('/json/addaccount',
			     null,
			     function(data) {
				     window.location.href = data.json.url;
			     },
			     'json');
	});
	
	$(window).scroll(function() {
		if (hasmore == false) return;
		
		if ($(window).scrollTop() + $(window).height() == $('body').height()) {
			
			// Don't auto paging when editing a new item.
			if ($('#newitem0').length) return; 
			
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
        
		var action = $(this).attr('class').replace(/ .+$/, '');
    
    if (action.match(/^add|relist|revise|verifyadditem|end$/)) {
      if (checkdemoaccount()) return;
    }
    
    if (action == 'delete') {
      if (!confirm('Delete checked items?')) {
        return;
      }
    }
		
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
            
			if (action == 'delete') {
        $('#'+$(this).val()).remove();
      }
		});
		
		$.post
		('/json/'+action,
		 postdata,
		 function(data) {
			 if (action == 'add'
				 || action == 'end'
				 || action == 'relist'
				 || action == 'revise'
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
		
		showcontent('#items');
		
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
		
		showcontent('#items');
			
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
		
		$('ul.accounts li').removeClass('tabselected');
		$(this).closest('li').addClass('tabselected');
		
		return false;
	});
	
	/* Picture */
  $('input:file').live('change', function() {
		
		var id = $(this).closest('tbody.itemrow').attr('id');
    var userid = $('select[name=UserID]', '#'+id).val();
    
		var idform = $('<input/>').attr('name', 'id').val(id);
		$(this).closest('form').append(idform);
    
		var useridform = $('<input/>').attr('name', 'userid').val(userid);
		$(this).closest('form').append(useridform);
    
		$('ul.pictures', '#'+id).sortable('destroy');
		
		//var fileindex = $(this).attr('name');
		//var fileindexform = $('<input/>').attr('name', 'fileindex').val(fileindex);
		//$(this).closest('form').append(fileindexform);
		
		//$(this).attr('name', 'uploadfile')
		$(this).attr('name', 'fileUpload')
		$(this).closest('form').submit();
		$(this).closest('form')[0].reset();
		
		//$(fileindexform).remove();
		$(idform).remove();
		$(useridform).remove();
  });
  
	$('select[name="mod.ListingType"]').live('change', function() {
		var id = $(this).closest('tbody.itemrow').attr('id');
		updateduration(id);
	});
	
	$('ul.tabNav li').live('click', function() {
		var id = $(this).closest('tbody').attr('id');
		var curIdx = $(this).prevAll().length + 1;
		
		$(this).parent().children('.current').removeClass('current');
		$(this).addClass('current');
		
		if ($(this).html() == 'All') {
			$('div.tabContainer', '#'+id).children().show();
		} else {
			$('div.tabContainer', '#'+id).children().hide();
			$('div.tabContainer', '#'+id).children('.current').removeClass('current');
			$('div.tabContainer', '#'+id).children('div:nth-child('+curIdx+')').show();
			$('div.tabContainer', '#'+id).children('div:nth-child('+curIdx+')').addClass('current');
		}
		
    _gaq.push(['_trackEvent', 'Item', $(this).html()]);
    
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

	/* Add ItemSpecifics */
	$('a.addis').live('click', function() {
		var id = $(this).closest('tbody.itemrow').attr('id');
		var iscnt = $('table.ItemSpecifics tr', '#'+id).length;
		var trtag = setformelements_itemspecifics_values(id, iscnt, null, null);
		$('table.ItemSpecifics', '#'+id).append(trtag);
		
		return false;
	});
	
	/* Remove ItemSpecifics */
	$('a.removeispc').live('click', function() {
		var id = $(this).closest('tbody.itemrow').attr('id');
		
		$(this).closest('tr').remove();
		
		$.each($('table.ItemSpecifics tr', '#'+id), function(k, v) {
			$.each($('select, input', v), function(j, o) {
				var orgname = $(o).attr('name');
				$(o).attr('name', orgname.replace(/\.[0-9]+\./, '.'+k+'.'));
			});
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
	$('button[class^=sync]', 'div#settings').live('click', function() {
    
    if (checkdemoaccount()) return;
    
    var userid = $(this).attr('class').replace(/^sync-/, '');
    // todo: importing message
    
		$.post('/json/import',
			     'userid='+userid,
			     function(data) {
				     
			     });
	});
  
	/* RemoveAccount */
	$('button[class^=removeaccount]', 'div#settings').live('click', function() {
    
    if (checkdemoaccount()) return;
    
    var userid = $(this).attr('class').replace(/^removeaccount-/, '');
    
		$.post('/json/removeaccount',
			     'userid='+userid,
			     function(data) {
				     
			     });
	});
	
	$('#cancelaccountlink').live('click', function() {
    if (checkdemoaccount()) return false;
		
		if (confirm('Cancel your account?')) {
			return true;
		} else {
			return false;
		}
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
			showcontent('#debug');
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
    
		// setformelements
		$('select[name=TimeZone]', '#settings').empty();
		$.each(timezoneids, function(k, v) {
			//var optiontag = $('<option/>').val(k).text(v);
			var optiontag = $('<option/>').val(k).text(k);
			$('select[name=TimeZone]', '#settings').append(optiontag);
		});
		
		$.post('/json/settings',
			     null,
			     function(data) {
				   
             $('#settings-email').html(data.json.settings.email);
             $('#settings-status').html(data.json.settings.status);
             $('#settings-expiration').html(data.json.settings.expiration);
             $('#settings-itemlimit').html(data.json.settings.itemlimit);
				     $('select[name=TimeZone]', '#settings').val(data.json.settings.timezone);
             
				     $('table#setting_ebay_accounts').empty();
				   
				     if (data.json.settings.userids) {
					     $.each(data.json.settings.userids, function(i, o) {
                 
						     var trtag = $('<tr/>');
						     
						     $(trtag).append($('<td/>').html(i));
						     
						     $(trtag).append($('<button/>')
                                 .attr('class', 'sync-'+i)
                                 .html('Sync items from eBay'));
						     
						     $(trtag).append($('<button/>')
                                 .attr('class', 'updatetoken-'+i)
                                 .html('Update token'));
						     
						     $(trtag).append($('<button/>')
                                 .attr('class', 'removeaccount-'+i)
                                 .html('Delete from ListersIn'));
						     
						     $('table#setting_ebay_accounts').append(trtag);
					     });
				     }
				     
				     dump(data.json.settings);
			     },
			     'json');
		
		showcontent('#settings');
	});
	
	$('a#showhelp').live('click', function() {
		showcontent('#help');
	});
    
    $('select[name=TimeZone]', '#settings').live('click', function() {
      
      var postdata = 'timezone='+encodeURIComponent($(this).val());
      
      $.post('/json/settings',
             postdata,
             function (data) {
               
             },
             'json');
      
      return;
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
			 
			 $('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).empty();
			 var emptyoptiontag = $('<option/>').val('').text('(not selected)');
			 $.each(data.json, function(i, o) {
				 var optiontag = $('<option/>').val(o.ID).text(o.Name);
				 $('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).append(optiontag);
			 });
		 });
	});
	
	// Add New Item
	$('button.newitem').live('click', function() {
		
		showcontent('#items');
		
		var id = 'newitem0';
		
		var dom = $('#rowtemplate').clone().attr('id', id);
		$('tr.row1', dom).hide();
		$('tbody#rowloading').hide();
		$('#items tbody:gt(1)').remove();
		$('#items').append(dom);
		
		var detail = $('div.detail', 'div#detailtemplate').clone().css('display', 'block');
		
		$('tr.row2 td', '#'+id).html(detail);
		
		$('div.tabContainer', '#'+id).children().show();
		
		// todo: compare to CKEditor
		$('textarea[name="mod.Description"]', '#'+id).wysiwyg();
		
		showbuttons(dom, 'save,cancel');
		
		// same as changeSite function
		// todo: don't hard code 'US'
		var site = 'US';
		
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
			 //item_modifing.mod.Quantity = 1;
			 
			 dump(item_modifing);
			 
			 setformelements(item_modifing);
			 
			 // todo: fillformvalues_newitem, currency, shippingpriority
			 var tmpcurval = $('select[name="mod.Currency"]', '#'+id).val();
			 $('input[name$="@currencyID"]',         '#'+id).val(tmpcurval);
			 $('input[name="mod.StartPrice.#text"]', '#'+id).val('0.99');
			 $('input[name="mod.Quantity"]',         '#'+id).val('1');
			 
			 return;
		 });
		
		_gaq.push(['_trackEvent', 'Left', 'AddNewItem']);
		
		return false;
	});
	
	$('select[name="mod.Currency"]').live('change', changeCurrency);
	
	$('#checkduplicateitems').live('click', function() {
		
		$('input[name=UserID]').val('');
		$('input[name=selling]').val('allitems');
		$('input[name=offset]').val(0);
		$('input[name=option]').val('checkduplicateitems');
		$('table#items tbody:gt(1)').remove();
		items();
		
		return false;
	});
	
	// Delete Picture
	$('a.deletepicture').live('click', function() {
		$(this).closest('li').remove();
		return false;
	});
  
	// Send a message to buyer
  $('div.buyer-sendmessage button').live('click', function() {
    
		var id = $(this).closest('tbody.itemrow').attr('id');
    var selleruserid = rowsdata[id].org.Seller.UserID;
    var itemid = rowsdata[id].org.ItemID;
    var message = $('textarea', $(this).parent()).val();
    var buyeruserid = $('div.buyer-userid', $(this).parent().parent()).text();
    
    var postdata = 'id='+id;
    postdata += '&userid='+selleruserid;
    postdata += '&itemid='+itemid;
    postdata += '&buyer='+buyeruserid;
    postdata += '&body='+encodeURIComponent(message);
    
    $.post('/json/sendmessage',
           postdata,
           function (data) {
             
           },
           'json');
  });
	
	/* AddMemberMessageRTQ */
  $('div.question-block button').live('click', function() {
		
		var id     = $(this).closest('tbody.itemrow').attr('id');
    var userid = rowsdata[id].org.Seller.UserID;
    var itemid = rowsdata[id].org.ItemID;
		var parent = $('li.question-parent', $(this).parent()).text();
    var body   = $('textarea', $(this).parent()).val();
    
		if (body == '') {
			alert('Please input answer text.');
			return false;
		}
		if (!confirm('Send this answer?')) {
			return false;
		}
		
		$(this).after('<img src="/img/indicator.gif"/> sending message...');
		
    var postdata = 'id='+id;
    postdata += '&userid='+userid;
    postdata += '&itemid='+itemid;
    postdata += '&parent='+parent;
    postdata += '&body='+encodeURIComponent(body);
    
    $.post('/json/addmembermessagertq',
           postdata,
           function (data) {
						 var item = data.json.item;
						 rowsdata[id] = item;
						 showmembermessages(id);
           },
           'json');
  });
  
	/*
    jQuery('div#message').ajaxStart(function() {
		$(this).html('Loading');
	  });
    jQuery('div#message').ajaxStop(function() {
		$(this).html('');
	  });
	*/
}	

function checkdemoaccount() {
    
  var email = $('#headerupper_right').html().replace(/[\r\n\s\t]/g, '');
  
  if (email == 'demo@listers.in') {
    alert('Sorry, this function is not available for demo account.');
    return true;
  }
  
  return false;
}

var changeCurrency = function() {
	
	var id = $(this).closest('tbody.itemrow').attr('id');
	
	$('input[name$="@currencyID"]', '#'+id).val($(this).val());
	
	return;
}

var findproducts = function() {
	
	$('div.foundproducts', td).hide();
	$('div.producttemplate', td).nextAll().remove();
  
	var td = $(this).parent();
	var keyword = $('input[name="ProductSearch.QueryKeywords"]', td).val();
  if (keyword == '') {
    $('div.productsearchmessage', td).html('Please input keyword.');
    return;
  }
  
  $('div.productsearchmessage', td).html('<img src="/img/indicator.gif"/> Searching...');
	
	$.post('/json/findproducts',
		     'findtype=QueryKeywords&keyword=' + encodeURIComponent(keyword),
		     function(data) {
           
           if (data.json.result.Ack == 'Failure') {
             $('div.productsearchmessage', td).html('No product found for "'+keyword+'".');
             return;
           }
			     
			     $.each(data.json.result.Product, function(i, o) {
				     
				     var productids = arrayize(o.ProductID);
				     
				     // todo: care Reference, UPC, ISBN, etc...
				     var productid = productids[0]['#text'];
             
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
           $('div.productsearchmessage', td).empty();
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

		if (!data.json.alluserids) {
			showcontent('#help');
			return;
		}
		
		$('ul.accounts > li.allitems').append(' (<span>'+data.json.alluserids.allitems+'</span>)');
		$.each(data.json.alluserids, function(k, v) {
			$('ul.accounts > li > ul.accountaction li.'+k).append(' (<span>'+v+'</span>)');
		});
		
		$.each(data.json, function(ebayuserid, o) {
			if (ebayuserid == 'alluserids') return;
			
			var ul = ulorg.clone();
			
			$('li.allitems', ul)
				.attr('id', 'euid_'+ebayuserid)
				.attr('class', ebayuserid)
				.html(ebayuserid+' (<span>'+o.allitems+'</span>)');

			$('li.itemstatuses', ul)
				.attr('id', 'euidstatuses_'+ebayuserid);
			
			$('ul.accountaction', ul)
				.attr('class', 'accountaction '+ebayuserid);
			
			$.each(o, function(j, v) {
				$('li.'+j, ul).append(' (<span>'+v+'</span>)');
			});
			
			$('ul.accounts').append(ul.html());
			
			var optiontag = $('<option />').val(ebayuserid).html(ebayuserid);
			$('select[name=UserID]', $('div#detailtemplate')).append(optiontag);
		});
		
		var licount = $('ul.accounts > li').length;
		if (licount == 2) {
			showcontent('#help');
		}
		
		return;
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
		 
		 dump(data.json);
		 
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
		 
		 if (data.json.message != '' && data.json.message != null) {
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
    
	if (row.org) {
		$('a.ItemID', dom)
			.attr('href', row.org.ListingDetails.ViewItemURL)
			.html(row.org.ItemID);
		
		$('td.EndTime', dom).html(row.endtime);
		
	} else {
		$('a.ItemID', dom).remove();
	}
	$('td.price', dom).html(row.price);
	
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
	
	$('td.UserID', dom).html(row.UserID);
	
	/* status icon */
	var src = '/icon/04/10/10.png';
	if (row.org) {
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
	if (row.message) {
		$('td.Title', dom).append(row.message);
	}
	if (row.org) {
		if (row.org.SellingStatus.QuantitySold > 0) {
			var soldtag = $('<div/>')
        .addClass('soldlabel')
				.html(row.org.SellingStatus.QuantitySold+' sold!');
			$('td.Title', dom).append(soldtag);
		}
		if (row.org.ListingDetails.HasUnansweredQuestions == 'true') {
			var huqtag = $('<div/>')
        .addClass('questionlabel')
				.html('Unanswered Question');
			$('td.Title', dom).append(huqtag);
		}
	}
	
  if (row.scheduled) {
	  if (row.scheduled) {
		  $('td.EndTime', dom).html('<img src="/icon/02/10/03.png"> '+row.scheduled);
    }
	}
	
	return dom;
}

// todo: skip some forms if selected category is not a leaf category.
function setformelements(item)
{
	var id = item.id;
	var site = item.mod.Site;
	
	/* Country */
	$('select[name="mod.Country"]', '#'+id).empty();
	$.each(hash[site].eBayDetails.CountryDetails, function(k, v) {
		var optiontag = $('<option/>').val(v.Country).text(v.Description);
		$('select[name="mod.Country"]', '#'+id).append(optiontag);
	});
	
	/* Currency */
	var sitecur = new Array();
	sitecur['US']             = ['USD'];
	sitecur['Canada']         = ['CAD', 'USD'];
	sitecur['UK']             = ['GBP'];
	sitecur['Germany']        = ['EUR'];
	sitecur['Australia']      = ['AUD'];
	sitecur['France']         = ['EUR'];
	sitecur['eBayMotors']     = ['USD'];
	sitecur['Italy']          = ['EUR'];
	sitecur['Netherlands']    = ['EUR'];
	sitecur['Spain']          = ['EUR'];
	sitecur['India']          = ['INR'];
	sitecur['HongKong']       = ['HKD'];
	sitecur['Singapore']      = ['SGD'];
	sitecur['Malaysia']       = ['MYR'];
	sitecur['Philippines']    = ['PHP'];
	sitecur['CanadaFrench']   = ['CAD', 'USD'];
	sitecur['Poland']         = ['PLN'];
	sitecur['Belgium_Dutch']  = ['EUR'];
	sitecur['Belgium_French'] = ['EUR'];
	sitecur['Austria']        = ['EUR'];
	sitecur['Switzerland']    = ['CHF'];
	sitecur['Ireland']        = ['EUR'];
	$('select[name="mod.Currency"]', '#'+id).empty();
	$.each(hash[site].eBayDetails.CurrencyDetails, function(k, v) {
		if ($.inArray(v.Currency, sitecur[site]) == -1) return;
		var optiontag = $('<option/>').val(v.Currency).text(v.Currency+' ('+v.Description+')');
		$('select[name="mod.Currency"]', '#'+id).append(optiontag);
	});
	//var tmpcurval = $('select[name="mod.Currency"]', '#'+id).val();
	//$('input[name$="@currencyID"]', '#'+id).val(tmpcurval);
	
	
	/* Categories */
	var tmppc = hash[site].Categories;
	
	if (item.categorypath) {
		
	} else {
		item.categorypath = [];
	}
	if (item.categorypath) {
		
		var tmppath = item.categorypath.slice(0); // just copy?
		tmppath.unshift(0);
		
		var tmppds = getcategorypulldowns(site, tmppath);
		$('select[name="mod.PrimaryCategory.CategoryID"]', '#'+id).parent().html(tmppds);
		
		if (item.categorypath.length >= 2) {
			tmppc = tmppc['c'+item.categorypath[item.categorypath.length-2]];
		}
	}
	
	var category;
	if (item.mod.PrimaryCategory) {
		var category = tmppc['c'+item.mod.PrimaryCategory.CategoryID];
		
		/* Condition */
		if (category.CategoryFeatures) {
			if (category.CategoryFeatures.ConditionEnabled == 'Disabled') {
				var optiontag = $('<option/>').val('').html('(disabled)');
				$('select[name="mod.ConditionID"]', '#'+id).empty();
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
	}
	
	/* ItemSpecifics */
	setformelements_itemspecifics(item);
	
	/* ListingDuration */
	$('select[name="mod.ListingDuration"]', '#'+id).empty();
	if ($('select[name="mod.ListingType"]').val() == 'Chinese') {
		var optiontag = $('<option/>').val('Days_1').html('1 day');
		$('select[name="mod.ListingDuration"]', '#'+id).append(optiontag);
	}
	var durationsetid = null;
	if (item.mod.PrimaryCategory) {
		if (category.CategoryFeatures) {
			for (i in category.CategoryFeatures.ListingDuration) {
				if (category.CategoryFeatures.ListingDuration[i]['@type'] == item.mod.ListingType) {
					durationsetid = category.CategoryFeatures.ListingDuration[i]['#text'];
					break;
				}
			}
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
	$('select[name="mod.'+_sdcsr+'.ShippingPackage"]', '#'+id).empty();
	var optiontag = $('<option/>').val('').html('');
	$('select[name="mod.'+_sdcsr+'.ShippingPackage"]', '#'+id).append(optiontag);
	if (hash[site].eBayDetails.ShippingPackageDetails) {
		$.each(hash[site].eBayDetails.ShippingPackageDetails, function(i, o) {
			var optiontag = $('<option/>').val(o.ShippingPackage).html(o.Description);
			$('select[name="mod.'+_sdcsr+'.ShippingPackage"]', '#'+id).append(optiontag);
		});
	}
	
	/* ShippingService */
	setformelements_shipping(item);
	
	/* DispatchTimeMax */
	$('select[name="mod.DispatchTimeMax"]', '#'+id).empty();
	var optiontag = $('<option/>').val('').html('Select a handling time');
	$('select[name="mod.DispatchTimeMax"]', '#'+id).append(optiontag);
	$.each(hash[site].eBayDetails.DispatchTimeMaxDetails, function(i, o) {
		var optiontag = $('<option/>').val(o.DispatchTimeMax).html(o.Description);
		$('select[name="mod.DispatchTimeMax"]', '#'+id).append(optiontag);
	});
	
	/* PaymentMethods */
	// Do not use GeteBayDetails to discover the valid payment methods for a site.
	$('td.paymentmethod', '#'+id).empty();
	$.each(hash[site].CategoryFeatures.SiteDefaults.PaymentMethod, function(i, o) {
		var idforlabel = id+'.PaymentMethods.'+i;
		
		var checkboxtag = $('<input/>')
			.attr('type', 'checkbox')
			.attr('id', idforlabel)
			.attr('name', 'mod.PaymentMethods')
			.val(o);
		
		var labeltag = $('<label/>')
			.attr('for', idforlabel)
			.html(o);
		
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
	var rparr = ['ReturnsAccepted', 'ReturnsWithin', 'Refund', 'ShippingCostPaidBy'];
	for (i in rparr) {
		var rpname = rparr[i];
		var optiontag = $('<option/>').val('').text('');
		$('select[name="mod.ReturnPolicy.'+rpname+'Option"]', '#'+id).empty();
		$('select[name="mod.ReturnPolicy.'+rpname+'Option"]', '#'+id).append(optiontag);
		$.each(hash[site].eBayDetails.ReturnPolicyDetails[rpname], function(i, o) {
			var optiontag = $('<option/>').val(o[rpname+'Option']).text(o.Description);
			$('select[name="mod.ReturnPolicy.'+rpname+'Option"]', '#'+id).append(optiontag);
		});
	}
	
	/* ThemeGroup */
	var optiontag = $('<option/>').val('').text('(not selected)');
	$('select[name="ListingDesigner.GroupID"]', '#'+id).empty();
	$('select[name="ListingDesigner.GroupID"]', '#'+id).append(optiontag);
	if (hash[site].ThemeGroup) {
		$.each(hash[site].ThemeGroup, function(i, o) {
			var optiontag = $('<option/>').val(o.GroupID).text(o.GroupName);
			$('select[name="ListingDesigner.GroupID"]', '#'+id).append(optiontag);
		});
	}
	
	var optiontag = $('<option/>').val('').text('(not selected)');
	$('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).empty();
	$('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).append(optiontag);
	if (hash[site].DescriptionTemplate) {
		$.each(hash[site].DescriptionTemplate, function(i, o) {
			var optiontag = $('<option/>').val(o.ID).text(o.Name);
			$('select[name="mod.ListingDesigner.ThemeID"]', '#'+id).append(optiontag);
		});
	}	

	/* checkbox,radio and label */
	$('input[type=checkbox][id^=_id]', '#'+id).each(function (i, o) {
		var newid = $(o).attr('id').replace(/^_id/, id);
		$(o).attr('id', newid);
	});
	$('input[type=radio][id^=_id]', '#'+id).each(function (i, o) {
		var newid = $(o).attr('id').replace(/^_id/, id);
		$(o).attr('id', newid);
	});
	$('label[for^=_id]', '#'+id).each(function (i, o) {
		var newid = $(o).attr('for').replace(/^_id/, id);
		$(o).attr('for', newid);
	});
	
	return;
}

function setformelements_shipping(item)
{
	if (item.ShippingDetails == undefined) {
		return;
	}
	
	var id = item.id;
	var site = item.mod.Site;
	
	var dmsttype = item.ShippingDetails.ShippingType.domestic;
	var intltype = item.ShippingDetails.ShippingType.international;
	
	var _dsso = 'ShippingDetails.ShippingServiceOptions';
	var _isso = 'ShippingDetails.InternationalShippingServiceOption';
	
	var packagetype = '';
	if (item.mod.ShippingDetails) {
		if (item.mod.ShippingDetails.CalculatedShippingRate) {
			packagetype = item.mod.ShippingDetails.CalculatedShippingRate.ShippingPackage;
		}
	}
	
	
	/* Domestic */
	if (dmsttype == '' || dmsttype == undefined) {
		$('tbody.shippingmainrows', '#'+id).hide();
		// todo: remove 2>=
		
		// also set NoShipping to international.
		$('select[name="ShippingDetails.ShippingType.international"]', '#'+id).val('');
		$('tbody.internationalshippingmainrows', '#'+id).hide();
		
		return;
	}
	
	$('tbody.shippingmainrows', '#'+id).show();
	$('tbody.internationalshippingmainrows', '#'+id).show();
	
	if (dmsttype == 'Calculated') {
		$('tr.packagetype, tr.dimensions, tr.weight', '#'+id).show();
	} else {
		$('tr.packagetype, tr.dimensions, tr.weight', '#'+id).hide();
	}
	
	/* International */
	log(intltype);
	if (intltype == '' || intltype == undefined) {
		$('tbody.internationalshippingmainrows', '#'+id).hide();
		// todo: remove 2>=
	} else {
		$('tbody.internationalshippingmainrows', '#'+id).show();
	}
	
	
	// set <option> tags
	$('select[name="mod.'+_dsso+'.0.ShippingService"]', '#'+id).empty();
	$('select[name="mod.'+_dsso+'.0.ShippingService"]', '#'+id)
		.append($('<option/>').val('').text('(not selected)'));
	
	$('select[name="mod.'+_isso+'.0.ShippingService"]', '#'+id).empty();
	$('select[name="mod.'+_isso+'.0.ShippingService"]', '#'+id)
		.append($('<option/>').val('').text('(not selected)'));
	
	$.each(hash[site].eBayDetails.ShippingServiceDetails, function(i, o) {
		if (o.ValidForSellingFlow != 'true') return;
		
		var arrservicetype = arrayize(o.ServiceType)
		
		if (parseInt(o.ShippingServiceID) < 50000) {

			// Domestic
			if ($.inArray(dmsttype, arrservicetype) >= 0) {
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
			} else if (dmsttype == 'Freight') {
				if (o.ShippingService == 'FreightShipping' || o.ShippingService == 'Freight') {
					log(o.ShippingService);
					$('select[name="mod.'+_dsso+'.0.ShippingService"]', '#'+id)
						.append($('<option/>').val(o.ShippingService).html(o.Description));
				}
			}
			
		} else {

			// International
			if ($.inArray(intltype, arrservicetype) >= 0) {
				$('select[name="mod.'+_isso+'.0.ShippingService"]', '#'+id)
					.append($('<option/>').val(o.ShippingService).html(o.Description));
			}
			
		}
	});
	
	// ShippingLocation
	$('div.ShipToLocation', '#'+id).empty();
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
	if (item.mod.ShippingDetails) {
		
		if ($.isArray(item.mod.ShippingDetails.ShippingServiceOptions)
				&& item.mod.ShippingDetails.ShippingServiceOptions.length > 1) {
			
			$.each(item.mod.ShippingDetails.ShippingServiceOptions, function(k, v) {
				if (v.ShippingServicePriority == 1) return;
				addsso($('a.addsso:first', '#'+id).get());
			});
		}
		
		if ($.isArray(item.mod.ShippingDetails.InternationalShippingServiceOption)
				&& item.mod.ShippingDetails.InternationalShippingServiceOption.length > 1) {
			
			$.each(item.mod.ShippingDetails.InternationalShippingServiceOption, function(k, v) {
				if (v.ShippingServicePriority == 1) return;
				addsso($('a.addsso:last', '#'+id).get());
			});
		}
		
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
	
	if (tmpname == 'ShippingServiceOptions') {
		$('input[name$="FreeShipping"]', sscopy).remove();
		$('label[for$="FreeShipping"]', sscopy).remove();
	}
	
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

function resizediv_index()
{
	$('#header').width($('#container-white').width()-40);
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
	
	// todo: disable modifying some fields when the item is active. (UserID, Site, etc...)
	setformelements(item);
	fillformvalues(item);
	
	// todo: compare to CKEditor
	$('textarea[name="mod.Description"]', 'tbody#'+id).wysiwyg();
	
	showbuttons(dom, 'save,cancel');
	
	return;
}

var save = function() {
	
	var id     = $(this).closest('tbody.itemrow').attr('id');
	var detail = $(this).closest('div.detail');
	
	// temp remove
	$('input.remove',  detail).remove();
	$('select.remove', detail).remove();
	
	// todo: varidation check
	//if (!checkformvalues(id)) return false;
	
	// PictureURL
	$.each($('ul.pictures li', '#'+id), function (i, li) {
		if ($(li).attr('class') == 'template') return;
		
		var src = $('img', li).attr('src');
		
		var input = $('<input />')
			.attr('type', 'hidden')
			.attr('name', 'mod.PictureDetails.PictureURL')
			.val(src);
		
		$('div.pictures', '#'+id).append(input);
	});
	
	// todo: Why Opera can't include <select> tags?
	// todo: Don't use numeric keys that causes "NCNames cannot start with...." javascript error.
	
	// remove empty value forms
	var _dsso = 'mod.ShippingDetails.ShippingServiceOptions';
	var _isso = 'mod.ShippingDetails.InternationalShippingServiceOption';
	for (i = 0; i <= 3; i++) {
		if ($('select[name="'+_dsso+'.'+i+'.ShippingService"]', '#'+id).val() == '') {
			$('input[name="'+_dsso+'.'+i+'.ShippingServicePriority"]', '#'+id).val('');
		} else if ($('select[name="'+_dsso+'.'+i+'.ShippingService"]', '#'+id).val() == null) {
			$('input[name="'+_dsso+'.'+i+'.ShippingServicePriority"]', '#'+id).val('');
		}
	}
	for (i = 0; i <= 4; i++) {
		if ($('select[name="'+_isso+'.'+i+'.ShippingService"]', '#'+id).val() == '') {
			$('input[name="'+_isso+'.'+i+'.ShippingServicePriority"]', '#'+id).val('');
		} else if ($('select[name="'+_isso+'.'+i+'.ShippingService"]', '#'+id).val() == null) {
			$('input[name="'+_isso+'.'+i+'.ShippingServicePriority"]', '#'+id).val('');
		}
	}
	
	// remove empty currency symbols
	$('input[name$="@currencyID"]', '#'+id).each(function(i, o) {
		var cname = $(o).attr('name').replace('@currencyID', '#text');
		if ($('input[name="'+cname+'"]', '#'+id).val() == '') {
			$(o).val('');
		}
	});
	
	// todo: remove empty ItemSpecifics
	$('input[name^="mod.ItemSpecifics.NameValueList"][type=checkbox]', '#'+id).each(function (i, o){
		var fvalue = $(o).val();
		if ($(o).attr('checked') != 'checked') $(o).remove();
	});
	$('input[name^="mod.ItemSpecifics.NameValueList"]', '#'+id).each(function (i, o){
		var fname = $(o).attr('name');
		if (!fname.match(/Name$/)) return;
		
		var fvalue = $('input[name="'+fname.replace(/Name$/, 'Value')+'"]', '#'+id).val();
		if (fvalue == '') $(o).remove();
	});
	
	var postdata = $('input[type=text], input:checked, input[type=hidden], input[type=datetime-local], select, textarea', '#'+id).extractObject();
	postdata = JSON.stringify(postdata);
	postdata = encodeURIComponent(postdata);
	
	$.post('/json/save',
		     'id='+id+'&json='+postdata,
		     function(data) {
			     var item = data.json.item;
					 if (id == 'newitem0') {
						 id = item.id
						 $('#newitem0').attr('id', id);
					 }
					 rowsdata[id] = item;
					 
			     var site = item.mod.Site;
			     
			     hash[site] = new Object;
			     hash[site].eBayDetails         = data.json.eBayDetails;
			     hash[site].Categories          = data.json.Categories;
			     hash[site].CategoryFeatures    = data.json.CategoryFeatures;
			     hash[site].ThemeGroup          = data.json.ThemeGroup;
			     hash[site].DescriptionTemplate = data.json.DescriptionTemplate;
			     
			     setformelements(item);
			     showformvalues(item);
			     showbuttons(detail, 'edit,copy,delete');
			     $('div.productsearchform', '#'+id).remove();
			     
		     },
		     'json');
	
	return false;
}

function checkformvalues(id) {

	if ($('select[name="mod.PrimaryCategory.CategoryID"]', '#'+id).val() == '') {
		alert('category error');
		return false;
	}
	
	return true;
}


var clickCancel = function() {
	
	var id = $(this).closest('tbody.itemrow').attr('id');
	
	// cancel add new item
	if (id == 'newitem0') {
		items();
		$('#newitem0').remove();
		return false;
	}
	
	setformelements(rowsdata[id]);
	showformvalues(rowsdata[id]);
	showbuttons('#'+id, 'edit');
	$('div.productsearchform', '#'+id).remove();
	
	return false;
}

var clickDelete = function() {
	return false;
}

var clickCopy = function() {
	return false;
}


var changeSite = function() {
    
	var id   = $(this).closest('tbody.itemrow').attr('id');
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
	
	var detail = $('div.detail', 'div#detailtemplate').clone();
	$('td:nth-child(2)', detail).hide();
	$('tr.row2 td', '#'+id).html(detail);
	$('div.detail', '#'+id).toggle();
	
	if (id == 'newitem0') {
		return false;
	}
	
	$.post
	('/json/item',
	 'id='+id,
	 function(data) {
		 var item = data.json.item;
		 rowsdata[id] = item;
		 dump(data.json.item);
		 
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
	var sel = $('<select class="category"/>');
	var opt = $('<option/>').val('').text('');
	sel.append(opt);
    
	$.each(hash[site]['category']['children'][categoryid], function(i, childid) {
    
		var str = hash[site]['category']['name'][childid];
		str += '('+childid+')';
		if (hash[site]['category']['children'][childid] != 'leaf') str += ' &gt;';
		opt = $('<option/>').val(childid).html('old|'+str);
		sel.append(opt);
	});
	
	return sel;
}

function getcategorypulldowns(site, path)
{
	var wrapper = $('<div/>');
	
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
		 if (data.json.message != '' && data.json.message != null) {
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
	var buttons = 'button.'+buttons.replace(/,/g, ',button.');
	
	var ulbtn = $('div.editbuttons', detail);
	$('div.editbuttons button', detail).hide();
	$(buttons, ulbtn).show();
	
	return;
}

function showcontent(contentid)
{
	$('#items').hide();
	$('#content > div').hide();
	$(contentid).show();
	
	return;
}

function msg(o)
{
	$('div#msg').prepend(o+'<br>');
}

function dump(o)
{
	var htmlencoded = $('<div/>').text($.dump(o)).html();
	$('div#debug').html('<pre>'+htmlencoded+'</pre>');
}

function log(str)
{
	$('#log').append(str+'<br/>');
}

function updateduration(id)
{
	var site = $('select[name="mod.Site"]', '#'+id).val();
	var listingtype = $('select[name="mod.ListingType"]', '#'+id).val();
	var tmpo = hash[site]['category']['features'][categoryid]['ListingDuration'];
	
	var sel = $('<select/>').attr('name', 'ListingDuration');
	$.each(rowsdata[id]['categoryfeatures']['ListingDuration'][listingtype], function(k, v) {
		var opt = $('<option/>').val(k).text(v);
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

function arrayize(object)
{
  var result = null;
  
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
	if (item.mod.PictureDetails && item.mod.PictureDetails.PictureURL) {
		item.mod.PictureDetails.PictureURL
			= arrayize(item.mod.PictureDetails.PictureURL);
	}
	
	if (item.mod.ShippingDetails) {
		if (item.mod.ShippingDetails.ShippingServiceOptions) {
			item.mod.ShippingDetails.ShippingServiceOptions
				= arrayize(item.mod.ShippingDetails.ShippingServiceOptions);
		}
	}
	
	if (item.mod.ItemSpecifics) {
		item.mod.ItemSpecifics.NameValueList
			= arrayize(item.mod.ItemSpecifics.NameValueList);
	}
	
	var detail = $('div.detail', '#'+item.id);
	
	/* text */
	$.each($('input[type=text],input[type=datetime-local]', detail), function(i, form) {
		var formname = $(form).attr('name');
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		try {
			eval("tmpvalue = item"+formname);
			
			if (tmpvalue == null) tmpvalue = '';
			
			var htmlencoded = $('<div/>').text(tmpvalue).html();
			$(form).replaceWith(htmlencoded);
			
			/*
			if ($(form).attr('name').match(/^mod.PictureDetails.PictureURL./)) {
				var imgclass = $(form).attr('name')
					.replace(/^mod.PictureDetails.PictureURL./, 'PD_PURL_');
				$('img.'+imgclass, detail).attr('src', tmpvalue);
			}
			*/
		} catch (err) {
			$(form).replaceWith('');
			//$(detail).prepend('ERR: ['+formname+']'+err.description+'<br />');
		}
	});
	
	/* Description (before replacing textarea)*/
	//$('textarea[name="mod.Description"]', detail).wysiwyg('clear');
	var iframe = $('<iframe/>').attr('class', 'description').attr('src', '/blank.html');
	iframe.load(function() {
		$(this).get(0).contentWindow.document.write(item.mod.Description);
		
		var iframeheight = $(this).contents().find('body').height() + 16
		$(this).css('height', iframeheight+'px');
	});
	$('textarea[name="mod.Description"]', detail).replaceWith(iframe);
	
	/* textarea */
	$.each($('textarea[name~=mod]', detail), function(i, form) {
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
						$(form).replaceWith('<img src="/icon/03/10/02.png"/>');
					}
				}
			} else {
				if (tmpvalue == $(form).val()) {
					$(form).replaceWith('<img src="/icon/03/10/02.png"/>');
				}
			}
			
		} catch (err) {
			var idforlabel = $(form).attr('id');
			$(form).replaceWith('<img src="/icon/03/10/04.png"/>');
			$('label[for="'+idforlabel+'"]').addClass('unchecked');
		}
	});
	$.each($('input[type=checkbox]', detail), function(i, form) {
		var idforlabel = $(form).attr('id');
		$(form).replaceWith('<img src="/icon/03/10/04.png"/>');
		$('label[for="'+idforlabel+'"]').addClass('unchecked');
	});
	
	/* PictureDetails */
	$('input[type=file]', detail).remove();
	
	$('ul.pictures li:gt(0)', detail).remove();
	if (item.mod.PictureDetails) {
		$.each($(item.mod.PictureDetails.PictureURL), function (i, url) {
			var lidiv = $('ul.pictures li.template', detail).clone();
			$(lidiv).removeClass('template');
			$('img', lidiv).attr('src', url);
			$('ul.pictures', detail).append(lidiv);
		});
		//$('ul.pictures', detail).sortable();
	}
	
	/* hide links */
	$('a.addis',      detail).remove();
	$('a.removeispc', detail).remove();
	$('a.addsso',     detail).remove();
	$('a.removesso',  detail).remove();
	
	showmembermessages(item.id);
	
	/* buyers information if exists */
	/*
	if (item.transactions) {
		$.each(item.transactions, function(i, o) {
            
			var divtag = $('div.buyer-template', detail).clone();
            
			$(divtag).removeClass('buyer-template').css('display', 'block');
			
			$('div.buyer-userid', divtag).append(o.Buyer.UserID);
			$('div.buyer-information', divtag).append('Quantity:'+o.QuantityPurchased);
			
			$('div.buyer-template', detail).parent().append(divtag);
		});
	}
	*/
	
	return;
}

function showmembermessages(id)
{
	var item = rowsdata[id];
  
	$('div.question-template', '#'+id).nextAll().remove();
	
  if (item.membermessages) {
    $.each(item.membermessages, function(i, o) {
      
			var divtag = $('div.question-template', '#'+id).clone();
			$(divtag).removeClass('question-template').css('display', 'block');
      
			$('li.question-status', divtag).append(o.MessageStatus);
      if (o.MessageStatus == 'Unanswered') {
			  $('li.question-status', divtag)
          .css('font-weight', 'bold')
          .css('color', '#f00');
      } else if (o.MessageStatus == 'Answered') {
			  $('li.question-status', divtag)
          .css('font-weight', 'bold')
          .css('color', '#090');
      }
      
			$('li.question-date',   divtag).append(o.creationdate_l);
			$('li.question-sender', divtag).append(o.Question.SenderID);
			$('li.question-parent', divtag).append(o.Question.MessageID);
			$('div.question-body',  divtag).append(o.Question.Body.replace(/\n/g, '<br/>'));
      
			$('div.question-template', '#'+id).parent().append(divtag);
    });
  }
	
	return;
}

function fillformvalues(item)
{
	var id = item.id
	
	if (item.mod.ShippingDetails) {
		if (item.mod.ShippingDetails.ShippingServiceOptions) {
			item.mod.ShippingDetails.ShippingServiceOptions
				= arrayize(item.mod.ShippingDetails.ShippingServiceOptions);
		}
	}
	
	if (item.mod.ItemSpecifics) {
		item.mod.ItemSpecifics.NameValueList = arrayize(item.mod.ItemSpecifics.NameValueList);
	}
	
	// input, text, textarea
	$.each($('input[type=text], input[type=hidden], input[type=datetime-local], select, textarea[name^=mod]', '#'+id), function(i, form) {
		var formname = $(form).attr('name');
		formname = "['" + formname.replace(/\./g, "']['") + "']";
		
		try {
			eval("var tmpvalue = item"+formname);
			$(form).val(tmpvalue);
		} catch (err) {
			//$(detail).prepend('ERR: '+err.description+'<br />');
		}
	});
	
	// checkbox
	$.each($('input[type=checkbox]', '#'+id), function(i, form) {
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
			//log('ERR:'+err.description+'<br/>'+formname+'<br/>'+typeof(tmpvalue));
		}
	});
	
	// CurrencyID
	$('input[name$="@currencyID"]', '#'+id).each(function(i, o) {
		var currency = $('select[name="mod.Currency"]', '#'+id).val();
		if ($(o).val() == '') {
			$(o).val(currency);
		}
	});
	
	// ItemSpecifics Name
	$('th input[name^="mod.ItemSpecifics.NameValueList"]', '#'+id).each(function(i, o) {
		$(o).hide();
		$(o).after($(o).val());
	});
	
	// Pictures (duplicate code with showformvalues().)
	if (item.mod.PictureDetails && item.mod.PictureDetails.PictureURL) {
		item.mod.PictureDetails.PictureURL
			= arrayize(item.mod.PictureDetails.PictureURL);
		
		$.each($(item.mod.PictureDetails.PictureURL), function (i, url) {
			var lidiv = $('ul.pictures li:first', '#'+id).clone();
			$(lidiv).removeClass('template');
			$('img', lidiv).attr('src', url);
			$('ul.pictures', '#'+id).append(lidiv);
		});
		$('ul.pictures', '#'+id).sortable({
			items: ':not(.template)'
		});
	}
	
	return;
}

/* ItemSpecifics */
function setformelements_itemspecifics(item)
{
	// todo: not return when undefined. (show empty forms)
	if (item.mod.ItemSpecifics == undefined) return;
	
	var detail = $('div.detail', '#'+item.id);
	$('table.ItemSpecifics', '#'+item.id).empty();
	
	var categoryid = item.mod.PrimaryCategory.CategoryID;
	var parentid = item.categorypath[item.categorypath.length-2];
	var category = hash[item.mod.Site]['Categories']['c'+parentid]['c'+categoryid];
	
	var specifics = arrayize(item.mod.ItemSpecifics.NameValueList);
	var recomm = arrayize(category.CategorySpecifics.NameRecommendation);
	
	var specificskey = new Array();
	for (i in specifics) {
		if (specifics[i] == null) continue;
		specificskey[specifics[i].Name] = i;
	}
	
	var recommkey = new Array();
	for (i in recomm) {
		recommkey[recomm[i].Name] = i;
	}
	
	/* Existing specifics */
	for (i in specifics) {
		if (specifics[i] == null) continue;
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
		//if (recomm[i].ValidationRules.VariationSpecifics == 'Disabled') continue;
		
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
	var thtag = $('<th />');
	var inputtag = $('<input />')
		.attr('type', 'text')
		.attr('Name', 'mod.ItemSpecifics.NameValueList.'+i+'.Name');
	$(thtag).append(inputtag);
	if (specific == null && recomm != null) {
		$(inputtag).val(recomm.Name)
	}
	$(trtag).append(thtag);
	
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
		
		var tabletag = $('<table />');
		
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
				
				if (checkboxidx % 3 == 0) {
					$(tabletag).append($('<tr />'));
				}
				
				// add custom value checkbox
				var idforlabel = id+'.ItemSpecifics.NameValueList.'+i+'.Value.'+checkboxidx;
				
				var checkboxtag = $('<input/>')
					.attr('id', idforlabel)
					.attr('name', 'mod.ItemSpecifics.NameValueList.'+i+'.Value')
					.attr('type', 'checkbox')
					.val(value);
				
				var labeltag = $('<label/>')
					.attr('for', idforlabel)
					.html(value);
				
				var tdtagv = $('<td />')
					.append(checkboxtag)
					.append(labeltag);
				
				$('tr:last', $(tabletag)).append(tdtagv);
				
				checkboxidx++;
			}
		}
		
		for (j in recomm.ValueRecommendation) {
			
			if (checkboxidx % 3 == 0) {
				$(tabletag).append($('<tr />'));
			}
			
			var idforlabel = id+'.ItemSpecifics.NameValueList.'+i+'.Value.'+checkboxidx;
			
			var checkboxtag = $('<input/>')
				.attr('id', idforlabel)
				.attr('name', 'mod.ItemSpecifics.NameValueList.'+i+'.Value')
				.attr('type', 'checkbox')
				.val(recomm.ValueRecommendation[j].Value);
			
			var labeltag = $('<label/>')
				.attr('for', idforlabel)
				.html(recomm.ValueRecommendation[j].Value);
			
			var tdtagv = $('<td />')
				.append(checkboxtag)
				.append(labeltag);
			
			$('tr:last', $(tabletag)).append(tdtagv);
			
			checkboxidx++;
		}
		
		$(tdtag).append(tabletag);
		
	} else if (recomm.ValidationRules.SelectionMode == 'SelectionOnly'
			   && recomm.ValidationRules.MaxValues == '1') {
		
		var selecttag = $('<select/>')
			.attr('name', 'mod.ItemSpecifics.NameValueList.'+i+'.Value')
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
	//$(tdtag).append('<pre>'+$.dump(recomm)+'</pre>');
	
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
	
	var tdtag = $('<td />').attr('class', 'removeispc');
	var removelink = $('<a />').attr('href', '#').attr('class', 'removeispc').text('Remove');
	$(tdtag).append(removelink);
	$(trtag).append(tdtag);
	
	return trtag;
}

function addimage(id, files) {
	
	$.each(files, function(i, url) {
		var li = $('ul.pictures li.template', '#'+id).clone();
		$(li).removeClass('template');
		$('img', li).attr('src', url);
		$('ul.pictures', '#'+id).append(li);
	});
	
	$('ul.pictures', '#'+id).sortable({
		items: ':not(.template)'
	});
	
	return;
}
