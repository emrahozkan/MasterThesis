var fileSubject = "";
var filePredicate = "";
var fileObject = "";
var hdnSubject = $("#hdnSubject");
var hdnObject = $("#hdnObject");
(function() {
    $(".chzn-select").chosen();
});
$(document).ready(function() {
	alert(3);
    $('#txtSearchSubject').autocomplete({
        delay: 0,  
        source: function (request, response) {
        	$("#loadingSubject").show();
            var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(request.term), "i" );
            $.ajax({
                url: fileSubject,
                dataType: "json",
                success: function (autoCompleteData) {
                	$("#loadingSubject").hide();
                    response($.map(autoCompleteData.subjects, function(v,i){         
                        var text = v.key;
                        if ( text && ( !request.term || matcher.test(text) ) ) {
                            return {
                                    label: v.key,
                                    value: v.value
                                   };
                        }
                    }));       
                }
            });
        },
        select: function (event, ui) {
//            alert(ui.item.value);
//            alert(ui.item.label);
            $('#txtSearchSubject').val(ui.item.label);
            hdnSubject.val(ui.item.value);
            return false;
          },
        minLength: 3
    });
});
$(document).ready(function() {
    $('#txtSearchPredicate').autocomplete({
        delay: 0,  
        source: function (request, response) {
        	$("#loadingPredicate").show();
            var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(request.term), "i" );
            $.ajax({
                url: filePredicate,
                dataType: "json",
                success: function (autoCompleteData) {
                	$("#loadingPredicate").hide();
                    response($.map(autoCompleteData.predicates, function(v,i){         
                        var text = v.key;
                        if ( text && ( !request.term || matcher.test(text) ) ) {
                            return {
                                    label: v.key,
                                    value: v.value
                                   };
                        }
                    }));       
                }
            });
        },
        select: function (event, ui) {
        	$('#txtSearchPredicate').val(ui.item.label);
        	$('#hdnPredicate').val(ui.item.value);
        	alert($('#hdnPredicate').val());
            return false;
          },
        minLength: 3
    });
});
$(document).ready(function() {
    $('#txtSearchObject').autocomplete({
        delay: 0,  
        source: function (request, response) {
            var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(request.term), "i" );
            $.ajax({
                url: fileObject,
                dataType: "json",
                success: function (autoCompleteData) {
                    response($.map(autoCompleteData.objects, function(v,i){         
                        var text = v.key;
                        if ( text && ( !request.term || matcher.test(text) ) ) {
                            return {
                                    label: v.key,
                                    value: v.value
                                   };
                        }
                    }));       
                }
            });
        },
        select: function (event, ui) {
        	 $('#txtSearchObject').val(ui.item.label);
        	 hdnObject(ui.item.value);
            return false;
          },
        minLength: 3
    });
});