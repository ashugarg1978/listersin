/* store rows data */
var rowsdata;

/* initialize */
$(document).bind({
	ready: function(event) {
		items();
		bindevents();

		$('div#content').width(($('div#container').width()-194));
		$('table#items').width(($('div#container').width()-194));
		
		$('ul#selling li:contains("Active")').click();
	}
});

/* list items */
function items()
{
	$.post('/users/items/',
		   $('input, select', '#filter').serialize(),
		   function(data) {
			   
			   paging(data.cnt);
			   rowsdata = data.res;
			   
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
	$('table.detail', dom).remove();
	
	$.each(row, function(colname, colval) {
		if (colname == 'Title' || colname == 'Description') {
			//colval = colval.replace(/\n/g, "___LF___");
			//colval = $('<div/>').text(colval).html();
			//colval = colval.replace(/___LF___/g, '<br>');
			//colval = colval.replace(/\n/g, '<br>');
		}
		$('.'+colname, dom).html(colval);
	});
	
	$('input:checkbox', dom).val(id);
	$('a.ItemId', dom).attr('href', row['viewitemurl']);
	$('img.PictureDetails_PictureURL', dom).attr('src', row['PictureDetails_PictureURL']);
	
	return dom;
}

function getdetail(id, row)
{
	detail = $('table.detail', '#rowtemplate').clone();
	
	$('img.PictureDetails_PictureURL', detail).attr('src', row['PictureDetails_PictureURL']);
	
	iframe = $('<iframe/>').attr('src', '/users/description/'+id);
	$('textarea[name=description]', detail).replaceWith(iframe);
	
	$('input:file', detail).remove();
	
	$.each(row, function(colname, colval) {
		if (colname == 'Title' || colname == 'Description') {
			//colval = colval.replace(/\n/g, "___LF___");
			//colval = $('<div/>').text(colval).html();
			//colval = colval.replace(/___LF___/g, '<br>');
			//colval = colval.replace(/\n/g, '<br>');
		}
		$('input[name='+colname+']', detail).replaceWith($('<div>'+colval+'</div>'));
	});
	
	form = $('<form method="post" action=""')
	
	return detail;
}


function descriptionframe(id)
{
	iframe = $('iframe.description', 'tbody#'+id);
	
	$(iframe).attr('src', '/users/description/'+id);
	
	if ($(td).html().match(/^<iframe/i)) return;
	
	description = $('<iframe/>').attr('src', '/users/description/'+itemid);
	
	$(td).html(description);
	
	return;
}

function bindevents()
{
	$(window).resize(function() {
		$('#content').css('width', ($(window).width()-50)+'px');
	});
	
	$('ul#selling li').live('click', function() {
		$('input[name=selling]').val($(this).text());
		items();
		$('ul#selling li').removeClass('tabselected');
		$(this).addClass('tabselected');
	});
	
	$('a.Title').live('click', function() {
		
		id = $(this).closest('tbody').attr('id');
		
		if (!$('div.detail', '#'+id).html().match(/^<table/i)) {
			$.post('/users/item/',
				   'id='+id,
				   function(data) {
					   rowsdata[id] = data;
					   detail = getdetail(id, data);
					   $('div.detail', '#'+id).html(detail);
					   $('div.detail', '#'+id).slideToggle('fast');
				   },
				   'json');
		} else {
			$('div.detail', '#'+id).slideToggle('fast');
		}
		
		//descriptionframe(id);
		//$.scrollTo('#'+id, {duration:200, axis:'y', offset:-42});
		
		return false;
	});
	
	$('#paging > a').live('click', function() {
		offset = ($(this).html() - 1) * limit;
		$('input[name=offset]').val(offset);
		items();
		return false;
	});
	
	$('input:button.edit', 'div.detail').live('click', function() {
		
		dom = $('table.detail', '#rowtemplate').clone();
		
		$('img.PictureDetails_PictureURL', dom).attr('src', rowsdata[id]['PictureDetails_PictureURL']);
		
		$('textarea[name=description]', dom).val(rowsdata[id]['Description']);
		
		id = $(this).closest('tbody.itemrow').attr('id');
		$.each(rowsdata[id], function(colname, colval) {
			$('input[name='+colname+']', dom).val(colval);
		});
		
		showbuttons(dom, 'update,cancel');
		
		$('div.detail', '#'+id).html(dom);
		
		$('textarea[name=description]', '#'+id).wysiwyg();
		
		return;
	});
	
	$('input:button.update', 'div.detail').live('click', function() {
		
		id = $(this).closest('tbody.itemrow').attr('id');
		
		postdata = $('input:text, textarea', $(this).closest('table')).serialize();
		
		$.post('/users/update/',
			   'id='+id+'&'+postdata,
			   function(data) {
				   rowsdata[id] = data;
				   dom = getrow(id, data.res[id]);
				   detail = getdetail(id, data.res[id]);
				   $('div.detail', dom).append(detail).css('display', 'block');
				   $('tbody#'+id).replaceWith(dom);
			   },
			   'json');
	});
	
	$('input:button.cancel', 'div.detail').live('click', function() {
		id = $(this).closest('tbody.itemrow').attr('id');
		
		detail = getdetail(id, rowsdata[id]);
		showbuttons(detail, 'edit,copy,delete');
		$('table.detail', '#'+id).replaceWith(detail);
		
	});
	
	$('#delete').live('click', function() {
		$.post();
	});

	$('a.wysiwyg').live('click', function() {
		$('textarea[name=description]', '#'+id).wysiwyg('destroy');
		return false;
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
		   function(data) {
			   
			   $.each(data, function(idx) {
				   id = data[idx].items.id;
				   
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
	postdata = $("input[name='id[]']:checked").serialize();
	
	$("input[name='id[]']:checked").each(function() {
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
	
	html = (offset+1)+' - ';
	if (offset+limit >= cnt) {
		html += cnt;
	} else {
		html += (offset+limit);
	}
	html += ' of '+cnt+'&nbsp;';
	
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

function showbuttons(detail, buttons)
{
	$('input:button', detail).hide();

	buttons = 'input:button.'+buttons.replace(/,/g, ',input:button.');
	
	$('input:button', detail).hide();
	$(buttons, detail).show();
	
	return;
}
