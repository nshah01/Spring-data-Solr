<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Apache Solr Demo</title>
</head>
<body>
<form name = "searchForm" action = "<%=request.getContextPath()%>/search">
Search Here : <input type="text" name = "searchTerm" placeholder = "What are you looking for?"> 
<input type="submit" value = "Search">
</form>
</body>
</html>