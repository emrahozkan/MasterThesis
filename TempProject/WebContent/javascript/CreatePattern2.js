var counter = 0;
var selectCounter = 1;
var num=1;
$(document).ready(function()
	{
	$("#btnAddGraph").hide();
	$("#btnCreateSlice").hide();
	$("#txtDescription").hide();
		$("#btnAddRow").on("click",function(){
		
			$("#patternResult").html("");
			counter = $('#tblGraphPattern tr').length+2;
			var subjectVal;
			var subjectText;
			var predicateVal ;
			var predicateText;
			var objectVal;
			var objectText;
	
			if($("input[name='radGroupSubj']:checked").val()=="Resource")
			{subjectVal = $("#txtSearchSubject").val();
			subjectText = $("#hdnSubject").val();
			}
			else{subjectText="Any"; subjectVal="Any";}
			
			if($("input[name='radGroupPred']:checked").val()=="Resource")
			{ 
//				predicateVal = $("#ddlPredicate option:selected").val();
//				predicateText = $("#ddlPredicate option:selected").text();
				predicateText= $("#txtSearchPredicate").val();
				predicateVal =$("#hdnPredicate").val();
			}
			else{predicateText="Any"; predicateVal="Any";}
			
			if($("input[name='radGroupObj']:checked").val()=="Resource")
			{
//				objectVal = $("#ddlObject option:selected").val();
//				objectText = $("#ddlObject option:selected").text();
				objectVal = $("#txtSearchObject").val();
				objectText =  $("#hdnObject").val()
			}
			else{objectText="Any"; objectVal="Any";}
			var newRow = $('<tr style="display:none;"></tr>');
			newRow.attr("align","center");
//			newRow.css("border-radius", "2px");
//			newRow.css("box-shadow","-0 5px 4px rgba(0, 0, 0, 0.10)");
			newRow.addClass("yarrak");
			//newRow.css("text-transform","none");
			var cols = "";

			cols += '<td align="right"><div id="divSubject'+counter+'">'+subjectText+'</div><input type="text" value="'+subjectVal+'" name="txtGraphSubject'
					+ counter + '" id="txtGraphSubject'	+ counter + '" style="display:none"/></td>';
			cols +=  '<td align="center"><div id="divPredicate'+counter+'">'+predicateText+'</div><input type="text" value="'+predicateVal+
			'" name="txtGraphPredicate' + counter + '" id="txtGraphPredicate'	+ counter + '" style="display:none"/></td>';
			cols +=  '<td align="left"><div id="divObject'+counter+'">'+objectText+'</div><input type="text" value="'+objectVal+
			'" name="txtGraphObject' + counter + '" id="txtGraphObject'	+ counter + '" style="display:none"/></td>';
			cols += '<td><input type="button" class="ibtnDel"  value="Delete"></td>';
			newRow.append(cols);
			
			$("table.graphPattern").append(newRow);
			newRow.fadeIn(800);
			counter++;
			
			if ($('#tblGraphPattern tr').length > 0)
			{
				$("#btnAddGraph").fadeIn(500);
			} else
			{
				//$("#btnAddGraph").prop('disabled', true);
				$("#btnAddGraph").hide();
			}
		});
///////on click method for delete button
		$("table.graphPattern").on("click", ".ibtnDel",function(event){
					$(this).closest("tr").remove();				
					if ($('#tblGraphPattern tr').length > 0)
					{
						//$("#btnAddGraph").prop('disabled', false);
						$("#btnAddGraph").fadeIn(300);
					} else
					{
						//$("#btnAddGraph").prop('disabled', true);
						$("#btnAddGraph").hide();
					}
				});
	});
$(document).ready(function()
		{
			$('#tblSelectPattern tr').length
			$("#btnAddGraph").on("click",function(){
				$("#patternResult").html("");
				var subjectVal;
				var subjectText;
				var predicateVal ;
				var predicateText;
				var objectVal;
				var objectText;
				
				var newRow = $('<tr style="display:none;"></tr>');
				newRow.attr("align","center");
				newRow.css("border-radius", "2px");
				newRow.css("box-shadow","-0 5px 4px rgba(0, 0, 0, 0.10)")
				for(var i = 1; i<counter;++i)
				{
					
					if($("#divSubject"+i+"").length>0)
					{		
					var cols = "";			
					subjectText=$("#divSubject"+i+"").html();
					if(subjectText=="Any")
						{subjectVal = "?s"+selectCounter;}
					else{subjectVal=$("#txtGraphSubject"+i+"").val();}
					
					predicateText=$("#divPredicate"+i+"").html();
					if(predicateText=="Any")
						{predicateVal = "?p"+selectCounter;}
					else{predicateVal=$("#txtGraphPredicate"+i+"").val();}
					objectText=$("#divObject"+i+"").html();
					if(objectText=="Any"){objectVal="?o"+selectCounter;}
					else{
						objectVal=$("#txtGraphObject"+i+"").val();
					}			
					
					cols += '<tr><td align="right">'+
					'<input type="hidden" name="hdnSelectCounter'+num+'" id="hdnSelectCounter'+num+'" value="'+selectCounter+'"/>'+
					'<div id="divSelectSubject'+num+'">'+subjectText+'</div><input type="text" value="'+subjectVal+'" name="txtSelectSubject'
							+ num + '" id="txtSelectSubject'	+ num + '"/></td>';
					cols +=  '<td align="center"><div id="divSelectPredicate'+num+'">'+predicateText+'</div><input type="text" value="'+predicateVal+
					'" name="txtSelectPredicate' + num + '" id="txtSelectPredicate'	+ num + '"/></td>';
					cols +=  '<td align="left"><div id="divSelectObject'+num+'">'+objectText+'</div><input type="text" value="'+objectVal+
					'" name="txtSelectObject' + num + '" id="txtSelectObject'	+ num + '"/></td></tr>';
					
					newRow.append(cols);
					}
					num++;
				}
				selectCounter++;
				newRow.append('<td><input type="button" class="ibtnDel"  value="Delete"></td>');
				$("table.selectPattern").append(newRow);
				newRow.fadeIn(500);
				
				////clean table and disable button
				$("#tblGraphPattern tr").fadeOut(300, function(){ 
					$("#tblGraphPattern tr").remove(); 
		            });
				$("#btnAddGraph").hide();
			
				
				///////check to disable the CreateSlice button
				if ($('#tblSelectPattern tr').length > 0)
				{
					$("#btnCreateSlice").fadeIn(500);
					$("#txtDescription").fadeIn(500);
				} else
				{
					$("#btnCreateSlice").hide();
					$("#txtDescription").hide();
				}
				

			});
		///////onClick method for delete button
			$("table.selectPattern").on("click", ".ibtnDel",function(event){
						$(this).closest("tr").remove();
						if ($('#tblSelectPattern tr').length > 0)
						{
							$("#btnCreateSlice").fadeIn(500);
							$("#txtDescription").fadeIn(500);
						} else
						{
							$("#btnCreateSlice").hide();
							$("#txtDescription").hide();
						}
					});
		});
function OpenSliceWindow(path)
{
	window.open("../TempProject/SliceInfo.jsp?id="+path);
}
