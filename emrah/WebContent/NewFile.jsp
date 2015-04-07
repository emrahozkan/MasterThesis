<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="javascript/jquery-1.11.1.js"></script>
<script>
$(document).ready(function() {
	$("#formFilepath2").submit(function() {	
		$.ajax({
			url : 'myservlet',
			type : 'POST',
			dataType : 'json',
			data : $('#formFilepath2').serialize,
			success : function(data) {
				if (data.isValid) {
					$('#output').html(data.filePath2);
					$('#output').slidedown(499);
				} else {
					alert('Data is not valid!');
				}
			}
		});
	});
	return false;
});
</script>

<body>
	<form id="formFilepath2" action="#">
		<input type="text" name="txtFilepath2" id="txtFilepath2"/> <br>
		<input type="Submit" id="btnSubmit">
	</form>
	<p id="output" />
</body>
</html>