


$(document).ready(function() {

    $("#presult").hide();
    
    $("#btnAnalyseFile").click(
            function(e) {              
                $("#loadingImage").show();
                $.ajax({
                        url: 'CreatePatternServlet',
                        type: 'POST',
                        dataType: 'json',
                        data: $("#formCreatePattern").serialize(),
                        success: function(data) {
                        	$("#loadingImage").hide();
                            if (data.analyseResult == true) {
                            	fileSubject = "data/"+data.subjectFile;
                            	filePredicate = "data/"+data.predicateFile;   
                            	fileObject = "data/"+data.objectFile;   
//                                var ddlSubject = $('#ddlSubject');
//                                var ddlPredicate = $('#ddlPredicate');
//                                var ddlObject = $('#ddlObject');
                                  var ddlClass = $('#ddlClass');
//                                $.each(data.Subjects, function(key, value) {
//                                    ddlSubject.append($('<option></option>').val(value).html(key));
//                                });
                                $.each(data.Classes, function(key, value) {
                                    ddlClass.append($('<option></option>').val(value).html(key));

                                });
//                                $.each(data.Predicates, function(key, value) {
//                                    ddlPredicate.append($('<option></option>')
//                                        .val(value).html(key));
//                                });
//                                $.each(data.Objects, function(key, value) {
//                                    ddlObject.append($('<option></option>').val(value).html(key));
//                                });
                                $('#ddlSubject').filterByText($('#txtSearchSubject'));
                                $('#ddlPredicate').filterByText($('#txtSearchPredicate'));
                                $('#ddlObject').filterByText($('#txtSearchObject'));
                                $('#ddlClass').filterByText($('#txtSearchClass'));

                                $("#presult").html("Data uploaded successfully");
                                $("#presult").css("color","green");
                                $("#presult").fadeIn(500);

                            } else {
                                $("#presult").html("Upload failed, please check file path or URL. Server returned error: " + data.result);
                                $("#presult").css("color","red");
                                $("#presult").fadeIn(500);
                            }

                        }
                    })
                    .done(function() {
                        $('#loadingImage').hide();
                    })
                    .fail(
                        function() {
                            $("#presult")
                                .html("Server error");
                            $("#presult").css("color","red");
                            $("#presult").hide();
                        });
                return false;
            });
});

jQuery.fn.filterByText = function(textbox) {
    return this.each(function() {
        var select = this;
        var options = [];
        $(select).find('option').each(function() {
            options.push({
                value: $(this).val(),
                text: $(this).text()
            });
        });
        $(select).data('options', options);
        $(textbox).bind(
            'change keyup',
            function() {
                var options = $(select).empty().data('options');
                var search = $(this).val().trim();
                var regex = new RegExp(search, "gi");

                $.each(options, function(i) {
                    var option = options[i];
                    if (option.text.match(regex) !== null) {
                        $(select).append(
                            $('<option>').text(option.text).val(
                                option.value));
                    }
                });
            });
    });
};


$(document).ready(function() {
    $("#txtSearchPredicate").on('click', function() {
        $("#radPred").prop("checked", true);

    });
});

$(document).ready(function() {
    $("#txtSearchSubject").on('click', function() {
        $("#radSubj").prop("checked", true);

    });
});

$(document).ready(function() {
    $("#txtSearchObject").on('click', function() {
        $("#radObj").prop("checked", true);

    });
});
$( window ).unload(function() {
		$.ajax({
			url : 'PageUnloadServlet',
			type : 'POST',
			dataType : 'json',
			async : false,
			data : $("#formCreateUser").serialize(),
			success : function(data) {
				
			}
		});
		return false;
	});