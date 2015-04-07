/**
 * 
 */
$(document).ready(function() {	
	$("#btnSubmit").click(function(e) {
		$.ajax({
			url : 'CreateUserServlet',
			type : 'POST',
			dataType : 'json',
			data : $("#formCreateUser").serialize(),
			success : function(data) {
				if(data.createResult){
					$('#output').html("User created successfully");
					$('#formCreateUser').each(function(){
					    this.reset();
					});
				}
				else{
					alert(data.errorMessage);
					$('#output').html("User create failed. Error message: "+data.errorMessage);
					$('#output').slideDown(500);
				}
			}
		});
		return false;
	});		
});