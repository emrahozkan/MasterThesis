<%@page import="java.io.IOException"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.packages.business.*"%>
<%@ page import="com.packages.database.*"%>
<%@ page import="com.packages.objects.*"%>
<%@ page import="java.util.Date.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.text.DateFormat.*" %>
<%@ page import="java.text.SimpleDateFormat.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script src="javascript/SliceDetails.js"></script>
<link rel="stylesheet" href="css/SliceInfo.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Slice Information</title>

</head>
<%!
public Date GetDateForm(String dateString) throws ParseException
{
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	return formatter.parse(dateString);
}
public long GetLastUpdate (String urlString) throws MalformedURLException, IOException
{
	URL url = new URL(urlString);
	URLConnection connection = url.openConnection();
	connection.connect();
	long time = connection.getLastModified();
	return time;
}
 %>
<%
ResultSet rs = null;
DatabaseConnection db = new DatabaseConnection();
SliceInfo slice = new SliceInfo();
slice.setSliceID(Integer.parseInt(request.getParameter("id").toString()));
//slice.setSliceID(5);
UserInfo user = new UserInfo();
user = (UserInfo)session.getAttribute("userData");
rs = db.SliceInfoGet(slice,user.getUserID());
while(rs.next()){
%>
<body>
	<div align="center" style="background:#eee;width:100%">
		<form id="formSliceInfo">
			<table class="emrah" width="100%">
				<tr>
					<td
						style="font-size: 20px; font-weight: 600; letter-spacing: 1px; text-transform: uppercase;">Slice
						Information</td>
				</tr>
			</table>
			<table class="emrah" width="100%" align="left">
				<tr style="box-shadow:none;">
					<td width="10%" style="box-shadow:none"></td>
					<td width="35%" style="box-shadow:none"></td>
					
				</tr>
				<tr style="box-shadow:none;">
					<td style="font-weight: 600">Slice Id:</td>
					<td><%=rs.getString("SLICE_ID") %>
					<input type="hidden" name="hdnSliceID" id="hdnSliceID" value="<%=rs.getString("SLICE_ID") %>" /></td>
				</tr>
				<tr style="box-shadow:none;">
					<td style="font-weight: 600">Description:</td>
					<td><%=rs.getString("SLICE_DESC") %></td>
					
				</tr>
				<tr style="box-shadow:none;">
					<td style="font-weight: 600">Data Source:</td>
					<td><%=rs.getString("DATA_SOURCE") %></td>
				</tr>
				<tr style="box-shadow:none;">
					<td style="font-weight: 600">Data Source Type:</td>
					<td><%=rs.getString("DATA_SOURCE_TYPE") %></td>
				</tr>
				<tr style="box-shadow:none;">
					<td style="font-weight: 600">Query:</td>
					<td><%=rs.getString("QUERY").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&lt;/br&gt;", "</BR>") %></td>
				</tr>
				<tr style="box-shadow:none;">
					<td style="font-weight: 600">Create Date:</td>
					<td><%=rs.getString("CREATE_DATE") %></td>
				</tr>
				<tr style="box-shadow:none;">
					<td style="font-weight: 600"></td>
					<td><%
					if(user.getUserID() == Integer.parseInt(rs.getString("CREATED_BY")) &&  (rs.getString("IS_PUBLIC").equals("0")))
					{
						System.out.println(rs.getString("IS_PUBLIC"));
						System.out.println(Boolean.parseBoolean(rs.getString("IS_PUBLIC")));
					%> <button title="Publish Slice"  name="btnPublishSlice" id="btnPublishSlice" class="btn-blue" value="Publish Slice">Publish Slice</button> <%
					}
					  %></td>
				</tr >
				<tr style="box-shadow:none;">
					<td style="font-weight: 600">Last Update:</td>
					<td><%=rs.getString("LAST_UPDATE") %></td>
				</tr >
				<tr >
				<td>
				
				</td>
				<td><%
				if(rs.getString("DATA_SOURCE_TYPE").toLowerCase().trim().equals("url"))
				{
					if(GetLastUpdate(rs.getString("DATA_SOURCE"))==0)
					{
						%>Modification date for this resource cannot be received. Please check for possible updates manually.  <%
					}
					else if(new Date(GetLastUpdate(rs.getString("DATA_SOURCE").trim().toLowerCase())).compareTo(GetDateForm(rs.getString("LAST_UPDATE")))>0)
					{
						%>Data Source Updated !</br>
						 <%
					}
					else{
						%><input type="button" name="btnUpdateSlice" id="btnUpdateSlice" class="btn-blue" value="Update Slice"/><%
					
					}
				}
				else{
					%>slice created with local data source, update check disabled. <%
				}
				%></td>		
				</tr>	
			</table>
			<%} %>
			<p id="pResult"/>
		</form>
	</div>
</body>
</html>