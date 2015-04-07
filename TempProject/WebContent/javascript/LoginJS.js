/**
 * Javascript & jquery code for Login.jsp page
 */

$(document).ready(function() {
	
	$("#btnSubmit").click(function(e) {
		$.ajax({
			url : 'LoginServlet',
			type : 'POST',
			dataType : 'json',
			data : $("#formLogin").serialize(),
			success : function(data) {
				if(data.loginResult){
					window.location.replace("/TempProject/CreatePattern.jsp");
				}
				else{
					$('#output').html("Login failed, please check your credentials");
					$('#output').slideDown(500);
				}
			}
		});
		return false;
	});		
});