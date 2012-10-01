$(function() {

  $('a').bind('click', function() {
    
    if (!confirm('delete?')) return false;
    
    var id = $(this).closest('tr').attr('id');
    
    $.post('/admin/deleteuser',
           'id='+id,
           function(data) {
             
           },
          'json');
    
    return false;
  });

});
