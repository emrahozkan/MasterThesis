<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.text.DateFormat.*" %>
<%@ page import="java.text.SimpleDateFormat.*" %>
<%@ page import="java.util.Date.*"%>
<%@ page import="java.net.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// 	long test = 297395;
	
// 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	
// 	Date ikinci = formatter.parse("2015-03-05 16:25:13.0");
// 			//format("2015-03-05 16:04:07"));
	
	URL url = new URL("HTTP://DBPEDIA.ORG/SPARQL");
	URLConnection connection = url.openConnection();
	connection.connect();
	long time = connection.getLastModified();
	System.out.println(time);
%>
</body>
</html>