<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="formUserDetails">
		<div>
			<table>
				<tr>
					<th colspan="4"><label>Update User Information</label></th>
				</tr>
				<tr>
					<td><label>Name : </label> <input type="text" name="txtName"
						id="txtName" /></td>
					<td><label>Surname : </label><input type="text"
						id="txtSurname" name="txtSurname" /> <input type="hidden"
						name="hdnUserID" id="hdnUserID" value="0" /></td>
				</tr>
				<tr>
					<td><label>User name : </label><input type="text"
						name="txtUserName" disabled/></td>
					<td><label>E-mail : </label> <input type="text"
						name="txtEmail" /></td>
				</tr>
				<tr>
					<td><label>Password : </label><input type="password"
						id="txtPassword" name="txtPassword" /></td>
					<td><label>Re-enter password : </label><input type="password"
						id="txtPassword2" /></td>
				</tr>
			</table>
			<input type="button" value="Update" name="btnSubmit" id="btnSubmit" />
		</div>
	</form>
	<form id="formUserSlices">
		<div>
			<table>
				<tr>
					<th>View Details</th>
					<th>Slice Name</th>
					<th>Create Date</th>
					<th>Size</th>
					<th>Created By</th>
					<th>Last Update</th>
				</tr>
				<tr>
					<td><input type="button" value="View"/></td>
					<td>World War 2</td>
					<td>21.12.2014 20:45</td>
					<td>56.6 mb</td>
					<td>Emrah Ozkan</td>
					<td>21.12.2014 20:45</td>
				</tr>
				<tr>
					<td><input type="button" value="View"/></td>
					<td>Football Players</td>
					<td>01.12.2012 13:38</td>
					<td>56.6 mb</td>
					<td>Some User</td>
					<td>06.11.2013 19:55</td>
				</tr>
			</table>

		</div>
	</form>
</body>
</html>