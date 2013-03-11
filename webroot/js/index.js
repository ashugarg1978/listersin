$(function() {
	bindevents();
});

function bindevents()
{
	/* Sign up button */
	$('button', '#signupbox').click(function() {
		
		var postdata = $('input', $(this).closest('form')).serialize();
		
		$.post('/json/signup',
			     postdata,
			     function(data) {
				     if (data.json.result == true) {
					     $('#signupmessage')
						     .css('color', 'blue')
						     .html('Confirmation mail was sent to<br/>'
                       + data.json.message+'!<br/>'
                       + 'If you can\'t find the email,<br/>'
                       + 'Please also check spam folder.');
				     } else {
					     $('#signupmessage')
						     .css('color', 'red')
						     .html(data.json.message);
				     }
			     },
			     'json');
		
		return false;
	});
	
	/* Forgot password */
	$('#forgotpasswordlink').click(function() {
		$('#forgotpassworddiv').slideDown();
		return false;
	});
	
	$('button', '#forgotpassworddiv').click(function() {
		
		var postdata = $('input', $(this).closest('form')).serialize();
		
		$.post('/json/forgotpassword',
					 postdata,
					 function(data) {
				     if (data.json.result == true) {
					     $('#forgotpasswordmessage')
						     .css('color', 'blue')
						     .html('Password reset mail was sent to<br/>'
                       + data.json.message+'!<br/>'
                       + 'If you can\'t find the email,<br/>'
                       + 'Please also check spam folder.');
				     } else {
					     $('#forgotpasswordmessage')
						     .css('color', 'red')
						     .html(data.json.message);
				     }
					 },
					 'json');
		
		return false;
	});

	/* Reset password button */
	$('button', '#resetpassworddiv').click(function() {
    
		var postdata = $('input', $(this).closest('form')).serialize();
		
		$.post('/json/resetpassword',
			     postdata,
			     function(data) {
				     if (data.json.result == true) {
					     $('#resetpasswordmessage')
						     .css('color', 'blue')
						     .html('Your password was changed.<br/>'
                       + 'Please sign in with new password.');
				     } else {
					     $('#resetpasswordmessage')
						     .css('color', 'red')
						     .html(data.json.message);
				     }
			     },
			     'json');
		
		return false;
	});
}

