<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RDFSlice Login</title>
</head>
<link rel="stylesheet" href="css/Tabs.css">
<script src="javascript/jquery-1.11.1.js"></script>
<script src="javascript/LoginJS.js"></script>
<body>
	<form id="formLogin">
		<table align="center" style="width:80%">
			<tr class="emrah">
			<td align="center">
			Please provide credentials to login
			<br>
		
			<input type="text" id="txtUserName" name="txtUserName" placeholder="User name" class="textbox" style="width:150px"/>
			<br>
		
			<input type="password" id="txtPassword" name="txtPassword" placeholder="Password" class="textbox" style="width:150px"/>
			<br>
			<input type="Submit" id="btnSubmit" value="Login" class="btn-blue">
			</td>
			</tr>
		</table>
	</form>
	<p id="output" />
</body>
</html>