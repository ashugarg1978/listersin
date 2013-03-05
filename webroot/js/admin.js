$(function() {
  
  $('a.delete').bind('click', function() {
    
    if (!confirm('delete?')) return false;
    
    var id = $(this).attr('data-id');
    
    $.post('/admin/deleteuser',
           'id='+id,
           function(data) {
             
           },
          'json');
    
    return false;
  });
  
});
