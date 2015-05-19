/**
 * 
 */
var subjectList = {};
$(function(){
    $(".chzn-select").chosen();
});
//$(document).ready(
//		function()
//		{
//			$("#btnAddPattern").click(
//					function(e)
//					{
//						$.ajax({
//							url : 'CreatePatternServlet',
//							type : 'POST',
//							dataType : 'json',
//							data : $("#formCreatePattern").serialize(),
//							success : function(data)
//							{
//								$(".chosen-select").chosen({disable_search_threshold: 1});
//								var predicates = {};
//								var objectList = {};
//								var ddlSubject = $('#ddlSubject');
//								var ddlPredicate = $('#ddlPredicate');
//								var ddlObject = $('#ddlObject');
//								$.each(data.Subjects, function(key, value)
//								{
//									ddlSubject.append($('<option></option>')
//											.val(value).html(key));
//								});
//								$.each(data.Objects, function(key, value)
//								{
//									ddlObject.append($('<option></option>')
//											.val(value).html(key));
//
//								});
//								$.each(data.Predicates, function(key, value)
//								{
//									ddlPredicate.append($('<option></option>')
//											.val(value).html(key));
//								});
//								$('#ddlSubject').filterByText(
//										$('#txtSearchSubject'));
//								$('#ddlPredicate').filterByText(
//										$('#txtSearchPredicate'));
//								$('#ddlObject').filterByText(
//										$('#txtSearchObject'));
//							}
//						});
//						return false;
//					});
//		});

jQuery.fn.filterByText = function(textbox)
{
	return this.each(function()
	{
		var select = this;
		var options = [];
		$(select).find('option').each(function()
		{
			options.push({
				value : $(this).val(),
				text : $(this).text()
			});
		});
		$(select).data('options', options);
		$(textbox).bind(
				'change keyup',
				function()
				{
					var options = $(select).empty().data('options');
					var search = $(this).val().trim();
					var regex = new RegExp(search, "gi");

					$.each(options, function(i)
					{
						var option = options[i];
						if (option.text.match(regex) !== null)
						{
							$(select).append(
									$('<option>').text(option.text).val(
											option.value));
						}
					});
				});
	});
};
jQuery.fn.complete = (function(myTextbox, tags)
{
	$(myTextbox).autocomplete({
		minLength : 0,
		source : tags,
		focus : function(event, ui)
		{
			$(myTextbox).val(ui.item.value);
			return false;
		},
		select : function(event, ui)
		{
			$(myTextbox).val(ui.item.key);
			return false;
		}
	});
});

$(document).ready(function()
{
	$("#txtSearchPredicate").on('click', function(){
		$("#radPred").prop("checked", true);
		 
	});
});

$(document).ready(function()
		{
			$("#txtSearchSubject").on('click', function(){
				$("#radSubj").prop("checked", true);
				 
			});
		});

$(document).ready(function()
		{
			$("#txtSearchObject").on('click', function(){
				$("#radObj").prop("checked", true);
				 
			});
		});



