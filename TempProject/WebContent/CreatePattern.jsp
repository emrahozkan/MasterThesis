<%@page import="com.packages.rdf.FileAnalyse"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.packages.business.*" %>
	<%@ page import="com.packages.database.*" %>
	<%@ page import="com.packages.objects.*" %>
	<%@ page import="java.sql.*" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script src="javascript/CreatePattern.js"></script>
<script src="javascript/CreatePattern2.js"></script>
<script src="javascript/CreateSlice.js"></script>
<script src="javascript/tabs.js"></script>
<script src="javascript/FileAnalyse.js"></script>
<script src="javascript/ClassAnalyse.js"></script>
<script src="javascript/SliceDetails.js"></script>
<script src="javascript/PatternAutoComplete.js"></script>
<script src="javascript/chosen-select/chosen.jquery.js"></script>
<script src="javascript/chosen-select/chosen.jquery.min.js"></script>
<link rel="stylesheet" href="css/Tabs.css">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="javascript/chosen-select/chosen.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RDFSlice</title>
<style type="text/css">
<%
session = request.getSession();
if(session.getAttribute("userData") == null)
{
	System.out.println("I am here");
	response.sendRedirect("/TempProject/Login.jsp");
	return;
}
ResultSet rs = null;
UserInfo user = new UserInfo();
user = (UserInfo)session.getAttribute("userData");
DatabaseConnection db = new DatabaseConnection();
SliceInfo slice = new SliceInfo();
rs = db.SliceInfoGet(slice,user.getUserID());

%>

</style>
<script type="text/javascript">
$(window).load(function(){
$(".chosen-select").chosen()
});
</script>
</head>
<body>
	
	<div class="tabs" id="rdfTabs2">
	<form id="formCreatePattern">
	    <ul class="tab-links">
	    	<li class="active"><a href="#data">Data</a></li>
	        <li ><a href="#pattern">Pattern</a></li>
	        <li><a href="#tab3">Class</a></li>
	        <li><a href="#tab4">Manage</a></li>
	    </ul>	
	    <div class="tab-content" id="rdfTabs">
	    	<div id="data">
	    		<p>Please enter dataset URL or absolute file location (e.g: c:\data\dbpedia.rdf)</p>
	            <table width="100%">
	            	<tr>
	            		<td colspan="2"><input name="txtDataSource" id="txtDataSource" type="text" class="textbox"/>
	            		<input type="button" value="Analyse File"
							name="btnAnalyseFile" id="btnAnalyseFile" class="btn-blue" />
							<div id="loadingImage" style="display:none">
	       			<img style="height: 24px; width: 22px" src="http://community.phytec.com/templates/base/images/loading.gif">
	            </div>
							</td>
	            		
	            	</tr>
	            	
	            </table>
	         	
	            <p id="presult">
	 		</div>
	        <div id="pattern" class="tab">		
				<table style="width:100%;" id="tblPattern" class="classPattern">
					<tr>
						<td><input type="hidden" name="hdnCounter" id="hdnCounter" /></td>
					</tr>
					<tr class="emrah">
						<td style="width: 33%">Subject</td>
						<td style="width: 33%">Predicate</td>
						<td style="width: 33%">Object</td>
					</tr>
					<tr>
						<td bordercolor="black"><input type="radio" name="radGroupSubj" value="Variable" checked="checked"/>
							Any </br> <input type="radio" id="radSubj"
							name="radGroupSubj" value="Resource" />
							<input type="text" style="width:100%" id="txtSearchSubject" name="txtSearchSubject" placeholder="Type here to filter subjects"/><br>
							<input type="text" style="display:none" id="hdnSubject" name="hdnSubject"><br>
							<img id="loadingSubject" style="display:none; height: 24px; width: 22px" src="http://community.phytec.com/templates/base/images/loading.gif">
							</td>
						<td><input type="radio" name="radGroupPred" value="Variable" checked="checked"/>
							Any </br> <input type="radio" id="radPred"
							name="radGroupPred" value="Resource" /><input id="txtSearchPredicate" style="width:100%" type="text" placeholder="Type here to filter predicates"/></br>
							<input type="text" style="display:none" id="hdnPredicate" name="hdnPredicate"><br>
							<img id="loadingPredicate" style="display:none; height: 24px; width: 22px" src="http://community.phytec.com/templates/base/images/loading.gif">
							</td>
						<td><input type="radio" name="radGroupObj" value="Variable" checked="checked"/>
							Any</br>  <input id="radObj"
							type="radio" name="radGroupObj" value="Resource" />
							<input id="txtSearchObject" type="text" name="txtSearchObject" style="width:100%" placeholder="Type here to filter objects"/></br>
							<input type="text" style="display:none" id="hdnObject" name="hdnObject"><br>
							<img id="loadingObject" style="display:none; height: 24px; width: 22px" src="http://community.phytec.com/templates/base/images/loading.gif"> </td>
					</tr>
				
					<tr align="center">
					<td align="left">
						<input type="radio" name="radGroupQueryType" value="query" checked="checked"/>
							Query 
							<input type="radio" id="radSubj" name="radGroupQueryType" value="pattern" />
							Pattern
					</td>
						<td colspan="2" align="right">
						<input type="button" name="btnAddRow" id="btnAddRow" value="Add Pattern" class="btn-blue"/></td>
					</tr>
				</table>
				<table width="100%">
					<tr class="emrah">
							<td style="width: 100%">Graph Patterns</td>	
						</tr>
				</table>
				<table id="tblGraphPattern" width="40%" class="graphPattern" align="center">
				</table>
				<table width="100%">
				<tr align="right">
				
						<td align="right"><input type="button" name="btnAddGraph" id="btnAddGraph" Value="Add Graph Pattern" class="btn-hidden" /></td>
					</tr>
				</table>
				<table width="100%">
				<tr class="emrah">
						<td style="width: 100%">Select Patterns</td>
						
					</tr>
				</table>
				<table id="tblSelectPattern" width="40%" class="selectPattern" align="center">
				</table>
				<table width="100%">
					<tr align="right">
					<td align="center" width="100%"><input type="text" name="txtDescription" id="txtDescription" class="textbox" width="100%"
				placeholder="Slice description" style="width: 500px; "></td>
						<td align="right"><input type="button" name="btnCreateSlice" id="btnCreateSlice" Value="Create Slice" class="btn-blue"/></td>
					</tr>
				</table>
				<p id="patternResult">
	        </div>
	 
	        <div id="tab3" class="tab" >
	        	<table align="center" width="100%">
	            	<tr class="emrah" align="center"><td align="center">Classes</td></tr>
	            </table>
	            <table width="100%" class="class-pattern" >
	            	<tr align="center">
	            		<td>
	            			<input type="text" name="txtSearchClass" id="txtSearchClass" placeholder="Filter classes.."/>
	            		</td>
	            	</tr>
	            	<tr align="center">
	            		<td>
	            			<select name="ddlClass" id="ddlClass"  style="width:40%; height:120px" multiple="multiple">
	            		
	            			</select>
	            		</td>
	            	</tr>
	            	<tr align="right">
	            		<td>
	            			<input type="button" value="Add Class" class="btn-blue" id="btnAddClass"/>
	            			<input type="hidden" id="hdnClassCtr" name="hdnClassCtr"/>
	            		</td>
	            	</tr>
	            </table>
	           <table id="tblClassPattern" width="40%" align="center" class="classPattern">
	           		
	           </table>
	           <table width="100%">
					<tr align="right">
					<td align="center" width="100%"><input type="text" name="txtClassDesc" id="txtClassDesc" class="textbox" width="100%"
				placeholder="Slice description" style="width: 500px; "></td>
						<td align="right"><input type="button" name="btnCreateClassSlice" id="btnCreateClassSlice" Value="Create Slice" class="btn-blue"/></td>
					</tr>
				</table>
				<p id="classResult">
	        </div>
	 
	        <div id="tab4" class="tab">
	           <table width="100%" style="align:center" class="emrah">
	           	<tr>
	           		<td width="7%" style="font-weight: bold">Slice Id</br>View</td>
	           		<td width="36%" style="font-weight: bold">Description</td>
	           		<td width="9%" style="font-weight: bold">Source Type</td>
	           		<td width="10%" style="font-weight: bold">Created By</td>
	           		<td width="13%" style="font-weight: bold">Create Date</td>
	           		<td width="13%" style="font-weight: bold">Last Updated</td>
	           	</tr>
	           <%
				while (rs.next()){%>
				<tr>
				<td><input type="button" class="btn-blue" name="btnSliceInfo"
				onclick="OpenSliceWindow(<%=rs.getString("SLICE_ID")%>);" value="<%=rs.getString("SLICE_ID")%>"/>  </td>
				<td><%=rs.getString("SLICE_DESC") %></td>
				<td><%=rs.getString("DATA_SOURCE_TYPE")%></td>
				<td><%=rs.getString("NAME")%> <%=rs.getString("SURNAME")%></td> 
				<td><%=rs.getString("CREATE_DATE") %></td>
				<td><%=rs.getString("LAST_UPDATE") %></td>
				</tr>			
						<%}
				 %>
	           </table>
	        </div>
	    </div>
	    </form>
	</div>	
	
</body>
</html>