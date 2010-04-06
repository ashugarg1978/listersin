function bindevents ()
{
	$('a.title').bind({
			click: function(event){
				
				itemid = $(this).parent().attr('id').replace(/^r/, '').replace(/tt$/, '');
				
				if ($('#d'+itemid).css('display') == 'block') {
					$('#d'+itemid).slideToggle('fast', function(){
							$('#rd'+itemid).remove();
						});
					return false;
				}
				if ($('#d'+itemid).css('display')) {
					$('#d'+itemid).slideToggle('fast');
					return false;
				}
				
				$.getJSON('/users/item/'+itemid, function(data){
						tmpl = $('#templatewrap').html();
						tmpl = tmpl.replace('template', 'd'+itemid+'');
						
						$.each(data, function(idx, val){
								tmpl = tmpl.replace('['+idx+']', val);
							});
						
						$('#r'+itemid).after('<tr id="rd'+itemid+'" class="detail">'
											 + '<td colspan="8">'
											 + tmpl
											 + '</td>'
											 + '</tr>');
						
						$('#d'+itemid).slideDown('first');
						$.scrollTo('#r'+itemid, {duration:200, axis:'y', offset:-42});
						bindform(itemid);
					});
				
				return false;
			}
		});
	
	$('#delete').bind({
			click: function(){
				$.post();
			}
		});
}	
	
function bindform(itemid)
{
	$("td.edit").bind({
			click: function(event){
				
				if (this.firstChild.tagName == "INPUT") return;
				if (this.firstChild.tagName == "TEXTAREA") return;
				
				colname = $(this).attr('class').replace('edit ', '');
				
				orgval = $(this).html();
				
				fobj = $('td.form'+colname).children().first().clone();
				fobj.val(orgval);
				
				$(this).html(fobj);
				
				$(this).children().first().focus();
				
				$(this).children().first().bind({
						blur: function(event){
							val = $(this).val();
							$(this).parent().html(val);
							
							$.post('/users/edit/'+itemid,
								   $(this).serialize(),
								   function(data){
									   a = '';
								   });
						}
					});
				
			}
		});
	
	
	return;
	
	
	$("tr").bind("mouseover", function(event){
		$(this).addClass('rowover');
	});
	$("tr").bind("mouseout", function(event){
		$(this).removeClass('rowover');
	});
	
	return;
}

function copyitems()
{
	$.post('/users/copy/',
		   $("input[name='item[]']:checked").serialize(),
		   function(data){
			   
			   html = '';
			   $.each(data, function(idx){
				   
				   iid = data[idx].items.itemid;
				   
				   html += itemrow(data[idx]);
				   
			   });
			   $('#r0').after(html);
		   },
		   'json');
	
	return;
}

function itemrow(data)
{
	itemid = data.items.itemid;
	
	html = '<tr id="r'+itemid+'">'
		+ '<td id="r'+itemid+'cb">'
		+ '<input type="checkbox" name="item[]" value="'+itemid+'"></td>'
		+ '<td id="r'+itemid+'ii">'+itemid+'</td>'
		+ '<td id="r'+itemid+'im" align="center">'
		+ '<img src="'+data.items.galleryurl+'" height="20"></td>'
		+ '<td id="r'+itemid+'tt"><a href="" class="title">'+data.items.title+'</a></td>'
		+ '<td id="r'+itemid+'eu">'+data.accounts.ebayuserid+'</td>'
		+ '<td id="r'+itemid+'ei"></td>'
		+ '<td id="r'+itemid+'et"></td>'
		+ '<td id="r'+itemid+'sp"></td>'
		+ '</tr>';
	
	return html;
}

function openebay(ebayitemid)
{
	window.open('http://cgi.sandbox.ebay.com/ws/eBayISAPI.dll'
			   + '?ViewItem&item='+ebayitemid, 'ebay');
	return false;
}

function update()
{
	$.post('/users/update/',
		   $("input[name='item[]']:checked").serialize(),
		   function(data){
			   
			   $.each(data, function(idx){
				   itemid = data[idx].items.itemid;

				   if (data[idx].items.ebayitemid) {
					   $('#r'+itemid+'ei').html('<a href="">'+data[idx].items.ebayitemid+'</a>');
					   $('#r'+itemid+'st').html(data[idx].items.starttime.replace(/^.....(.+)...$/, '$1'));
					   $('#r'+itemid+'et').html(data[idx].items.endtime.replace(/^.....(.+)...$/, '$1'));
					   $('#r'+itemid+'cb').removeClass('loading');
					   $('#r'+itemid+'cb > input').css('visibility', '');
					   $('#r'+itemid+'cb > input').attr('checked', '');
					   
				   }
				   
			   });
			   
			   if ($("input[name='item[]']:checked").length > 0) {
				   setTimeout(function(){update();}, 2000);
			   }
			   
		   },
		   'json');
	
	return;
}

function submititems()
{
	var postdata = "";
	postdata = $("input[name='item[]']:checked").serialize();
	
	$("input[name='item[]']:checked").each(function(){
		$(this).css('visibility', 'hidden');
		$(this).parent().addClass('loading');
	});
	
	$.post('/users/submit/',
		   postdata,
		   function(data){
			   $('#debug').html('<pre>'+data+'</pre>');
		   });
	
	setTimeout(function(){update();}, 2000);
	
	return;
}

function updatelist()
{
	$.post('/users/items/',
		   $('#filter').serialize(),
		   function(data){
			   html = '';
			   $.each(data.res, function(idx, val){
					   html += itemrow(val);
				   });
			   $('#tbdy').html(html);
			   
			   paging(data.cnt);
			   
			   bindevents();
	
		   },
		   'json');
}

function paging(cnt)
{
	limit  = $('input[name=limit]').val() - 0;
	offset = $('input[name=offset]').val() - 0;
	
	html = (offset+1)+' 〜 ';
	if (offset+limit >= cnt) {
		html += cnt;
	} else {
		html += (offset+limit);
	}
	html += ' / '+cnt+'<br>';
	
	for (i=0; i<(cnt/limit); i++) {
		if (offset == i*limit) {
			html += '<a href="" style="background-color:#ccffcc;">'+(i+1)+'</a>';
		} else {
			html += '<a href="">'+(i+1)+'</a>';
		}
	}
	
	$('#paging').html(html);
	
	$('#paging > a').bind({
			click: function(event){
				offset = ($(this).html() - 1) * limit;
				$('input[name=offset]').val(offset);
				updatelist();
				return false;
			}
		});
	
	return;
}

function chkall()
{
	$(":checkbox").attr('checked', 'checked');
}

function unchkall()
{
	$(":checkbox").attr('checked', '');
}
