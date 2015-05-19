<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="javascript/jquery-1.11.1.js"></script>
<script src="javascript/CreateUserJS.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="formCreateUser">
		<div>
			<table>
				<tr>
					<th colspan="4"><label>New User Information</label></th>
				</tr>
				<tr>
					<td><label>Name : </label> <input type="text" name="txtName"
						id="txtName" /></td>
					<td><label>Surname : </label><input type="text"
						id="txtSurname" name="txtSurname" />
						<input type="hidden" name="hdnUserID" id="hdnUserID" value="0"/></td>		
				</tr>
				<tr>
					<td><label>User name : </label><input type="text"
						name="txtUserName" /></td>
					<td><label>E-mail : </label> <input type="text"
						name="txtEmail" /></td>
				</tr>
				<tr>
					<td><label>Password : </label><input type="password"
						id="txtPassword" name="txtPassword"/></td>
					<td><label>Re-enter password : </label><input type="password"
						id="txtPassword2" /></td>
				</tr>
				<tr>
					<td><label>Is Admin ?</label> <input type="checkbox"
						value="Admin" name="chkAdmin" id="chkAdmin" /></td>
				</tr>
			</table>
			<input type="button" value="Submit" name="btnSubmit" id="btnSubmit" />
		</div>
		<div>
			<p id="output"/>
		</div>
	</form>
</body>
</html>