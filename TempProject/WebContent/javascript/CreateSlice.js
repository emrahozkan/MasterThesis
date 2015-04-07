$(document).ready(function() {
	$("#patternResult").hide();
	$("#btnCreateSlice").click(function(e) {
		 $("#hdnCounter").val(num);
		 
//		 alert(num);
//		 alert(counter);
//		 alert(selectCounter);
		$.ajax({
			url : 'CreateSliceServlet',
			type : 'POST',
			dataType : 'json',
			data : $("#formCreatePattern").serialize(),
			success : function(data) {
				$("#patternResult").html("");
				if(data.result == "success")
					{
					$("#patternResult").html("Slice created successfully. You can find the output file on your Desktop");
					$("#patternResult").css("color","green");
					$("#patternResult").fadeIn(500);
					}
					else{
						$("#patternResult").html("Slice creation failed. Please check your selections");
						$("#patternResult").css("color","red");
						$("#patternResult").fadeIn(500);
					}
				}
		});
		$("#tblSelectPattern tr").fadeOut(300, function(){ 
			$("#tblSelectPattern tr").remove(); 
            });
		$("#btnCreateSlice").hide();
		$("#txtDescription").hide();
		$('#rdfTabs2').tabs('load', 4);
		return false;
	});		
});
