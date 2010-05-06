$(document).bind({
	ready: function(event) {
		items();
		bindevents();
	}
});

function items()
{
	$.post('/users/items/',
		   $('input, select', '#filter').serialize(),
		   function(data) {
			   paging(data.cnt);
			   
			   $('tbody:gt(2)').remove();
			   $.each(data.res, function(id, row) {
				   dom = getrow(id, row);
				   $('#items').append(dom);
			   });
		   },
		   'json');
}

function getrow(id, row)
{
	dom = $('#rowtemplate').clone().attr('id', id);
	
	$.each(row, function(colname, colval) {
			if (colname == 'Title' || colname == 'Description') {
				//colval = colval.replace(/\n/g, "___LF___");
				colval = $('<div/>').text(colval).html();
				//colval = colval.replace(/___LF___/g, '<br>');
				colval = colval.replace(/\n/g, '<br>');
			}
			$('.'+colname, dom).html(colval);
		});
	
	$('input:checkbox', dom).val(id);
	$('a.itemid',   dom).attr('href', row['viewitemurl']);
	$('img.PictureDetails_PictureURL', dom).attr('src', row['PictureDetails_PictureURL']);
	//$('iframe.description',     dom).attr('src', '/users/description/'+id);
	
	return dom;
}

function descriptionframe(itemid)
{
	td = $('td.description', 'tbody#'+itemid);
	
	if ($(td).html().match(/^<iframe/i)) return;
	
	description = $('<iframe/>').attr('src', '/users/description/'+itemid);
	
	$(td).html(description);
	
	return;
}

function bindevents ()
{
	$('a.title').live('click', function(){
			
			itemid = $(this).closest('tbody').attr('id');
			
			descriptionframe(itemid);
			
			$('div.detail', '#'+itemid).slideToggle('fast');
			
			//$.scrollTo('#'+itemid, {duration:200, axis:'y', offset:-42});
			
			return false;
		});
	
	$('#paging > a').live('click', function() {
			offset = ($(this).html() - 1) * limit;
			$('input[name=offset]').val(offset);
			items();
			return false;
		});
	
	$('input:button.edit', 'div.detail').live('click', function() {
			itemid = $(this).closest('tbody.itemrow').attr('id');
			
			$.each($('td', $(this).closest('table')), function(i, v) {
					
					colname = $(v).attr('class');
					if (colname == '') return;
					
					colval = $(v).html();
					if (colname == 'title' || colname == 'description') {
						//colval = colval.replace(/<br>/g, '___LF___');
						colval = $('<div/>').html(colval).text();
						//colval = colval.replace(/___LF___/g, "\n");
						colval = colval.replace(/<br>/g, '\n');
					}
					
					f = $('.'+colname, '#editform').clone().val(colval);
					if (colname == 'description') {
						f.attr('rows', (colval.split(/\n/).length + 2));
					}
					
					$(v).html(f);
					//$('.'+colname, '#'+itemid).html($(v));
				});
			
		});
	
	$('input:button.update', 'div.detail').live('click', function() {
			itemid = $(this).closest('tbody.itemrow').attr('id');
			
			postdata = $('input:text, textarea', $(this).closest('table')).serialize();
			
			$.post('/users/update/',
				   'itemid='+itemid+'&'+postdata,
				   function(data){
					   row = getrow(itemid, data.res[itemid]);
					   $('#'+itemid).replaceWith(row);
					   $('div.detail', '#'+itemid).show();
				   },
				   'json');
		});
	
	$('input:button.cancel', 'div.detail').live('click', function() {
			itemid = $(this).closest('tbody.itemrow').attr('id');
			
			$.post('/users/items/',
				   'itemid='+itemid,
				   function(data) {
					   row = getrow(itemid, data.res[itemid]);
					   $('#'+itemid).replaceWith(row);
					   descriptionframe(itemid);
					   $('div.detail', '#'+itemid).show();
				   },
				   'json');
			
		});
	
	$('#delete').live('click', function(){
			$.post();
		});
	
}	
	
function copyitems()
{
	$.post('/users/copy/',
		   $("input[name='item[]']:checked").serialize(),
		   function(data){
			   
			   html = '';
			   $.each(data, function(idx){
			   });
			   $('#tbdy').html(html+$('#tbdy').html());
		   },
		   'json');
	
	return;
}

function update()
{
	$.post('/users/update/',
		   $("input[name='item[]']:checked").serialize(),
		   function(data){
			   
			   $.each(data, function(idx){
				   itemid = data[idx].items.itemid;
				   
			   });
			   
			   if ($("input[name='item[]']:checked").length > 0) {
				   setTimeout(function(){update();}, 2000);
			   }
			   
		   },
		   'json');
	
	return;
}

function additems()
{
	var postdata = "";
	postdata = $("input[name='item[]']:checked").serialize();
	
	$("input[name='item[]']:checked").each(function() {
		$(this).css('visibility', 'hidden');
		$(this).parent().addClass('loading');
	});
	
	$.post('/users/additems/',
		   postdata,
		   function(data){
			   $('#debug').html('<pre>'+data+'</pre>');
		   });
	
	setTimeout(function(){update();}, 2000);
	
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
	limit  = $('input[name=limit]').val() - 0;
	offset = $('input[name=offset]').val() - 0;
	
	html = (offset+1)+' 〜 ';
	if (offset+limit >= cnt) {
		html += cnt;
	} else {
		html += (offset+limit);
	}
	html += ' / '+cnt+'&nbsp;&nbsp;&nbsp;';
	
	for (i=0; i<(cnt/limit); i++) {
		if (offset == i*limit) {
			html += '<a href="" style="background-color:#ccffcc;">'+(i+1)+'</a>';
		} else {
			html += '<a href="">'+(i+1)+'</a>';
		}
	}
	
	$('#paging').html(html);
	
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
