
/////////////BU TEMPLATE ON CLICK
$(document).ready(function() {
	
	$("#btnSubmit").click(function(e) {
		$.ajax({
			url : 'CreateUserServlet',
			type : 'POST',
			dataType : 'json',
			data : $("#formCreateUser").serialize(),
			success : function(data) {
				
			}
		});
		return false;
	});		
});

///////////////TEMPLATE END

$(document).ready(function() {
	$("#btnSubmit2").click(function(e) {
		$.ajax({
			url : 'myservlet',
			type : 'POST',
			dataType : 'json',
			data : $("form").serialize(),
			success : function(data) {
				var s = $("<select id=\"ddlClassList\" name=\"ddlClassList\" />");
				$.each(data, function() {
					  $.each(this, function(k, v) {
						  $("<option />", {value: v, text: v}).appendTo(s);
					  });
					});
				$('#output').html(s);
			}
		});
		return false;
	});		
});

/////////////
