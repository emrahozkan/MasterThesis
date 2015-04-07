var classCtr=1;


$(document).ready(function() {
	$("#classResult").hide();
	$("#btnCreateClassSlice").click(function(e) {
		 $("#hdnClassCtr").val(classCtr);
		$.ajax({
			url : 'CreateClassSliceServlet',
			type : 'POST',
			dataType : 'json',
			data : $("#formCreatePattern").serialize(),
			success : function(data) {
				$("#patternResult").html("");
				if(data.result == "success")
					{
					$("#classResult").html("Slice created successfully. You can find the output file on your Desktop");
					$("#classResult").css("color","green");
					$("#classResult").fadeIn(500);
					}
					else{
						$("#classResult").html("Slice creation failed. Please check your selections");
						$("#classResult").css("color","red");
						$("#classResult").fadeIn(500);
					}
				}
		});
		$("#tblClassPattern tr").fadeOut(300, function(){ 
			$("#tblClassPattern tr").remove(); 
            });
		$("#btnCreateClassSlice").hide();
		$("#txtClassDesc").hide();
		return false;
	});		
});
$(document).ready(function()
		{
			$("#btnCreateClassSlice").hide();
			$("#txtClassDesc").hide();
			$("#btnAddClass").on("click",function(){
				
				$("#classResult").html("");
				var classVal = "<"+$("#ddlClass option:selected").val()+">";
				var className = $("#ddlClass option:selected").text();
		
				
				var newRow = $('<tr style="display:none;"></tr>');
				newRow.attr("align","center");
				
				newRow.addClass("yarrak");
				//newRow.css("text-transform","none");
				var cols = "";

				cols += '<td align="center"><div id="divClass'+classCtr+'">Select all instances of class '+className+'</div><input type="text" value="'+classVal+'" name="txtClass'
						+ classCtr + '" id="txtClass'	+ classCtr + '"/></td>';	
				cols += '<td><input type="button" class="ibtnDel"  value="Delete"></td>';
				newRow.append(cols);
				
				$("table.classPattern").append(newRow);
				newRow.fadeIn(800);
				classCtr++;
				
				if ($('#tblClassPattern tr').length > 0)
				{
					$("#btnCreateClassSlice").fadeIn(300);
					$("#txtClassDesc").fadeIn(300);
				} else
				{
					//$("#btnAddGraph").prop('disabled', true);
					$("#btnCreateClassSlice").hide();
				}
			});
	///////on click method for delete button
			$("table.classPattern").on("click", ".ibtnDel",function(event){
						$(this).closest("tr").remove();				
						if ($('#tblClassPattern tr').length > 0)
						{
							//$("#btnAddGraph").prop('disabled', false);
							$("#btnCreateClassSlice").fadeIn(300);
							$("#txtClassDesc").fadeIn(300);
							
						} else
						{
							$("#txtClassDesc").hide();
							$("#btnCreateClassSlice").hide();
						}
					});
		});