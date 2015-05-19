$(document).ready(function() {
	$("#pResult").hide();
	$("#btnUpdateSlice").click(function(e) {
		$.ajax({
			url : 'SliceInfoServlet',
			type : 'POST',
			dataType : 'json',
			data : $("#formSliceInfo").serialize(),
			success : function(data) {
				if(data.sliceResult == "success")
					{
						$("#pResult").html("Slice updated successfully");
						$("#pResult").css("color","green");
						$("#pResult").fadeIn(300);
					}
				else{
					$("#pResult").html("Slice creation or logging failed. </br>Server returned error: "+data.sliceResult);
					$("#pResult").css("color","red");
					$("#pResult").fadeIn(300);
				}
			}
		});
		return false;
	});		
});
$(document).ready(function() {
	$("#pResult").hide();
	$("#btnPublishSlice").click(function(e) {
		$.ajax({
			url : 'PublishSliceServlet',
			type : 'POST',
			dataType : 'json',
			data : $("#formSliceInfo").serialize(),
			success : function(data) {
				if(data.sliceResult == "success")
					{
						$("#pResult").html("Slice published successfully");
						$("#pResult").css("color","green");
						$("#pResult").fadeIn(300);
					}
				else{
					$("#pResult").html("Slice publish failed. </br>Server returned error: "+data.sliceResult);
					$("#pResult").css("color","red");
					$("#pResult").fadeIn(300);
				}
			}
		});
		return false;
	});	
});