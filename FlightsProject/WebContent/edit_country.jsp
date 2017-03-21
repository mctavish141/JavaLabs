<%@page import="com.flights.Country"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit country</title>
</head>
<body>

<%
Country country = (Country)request.getAttribute("country");
%>

<h4>Edit country:</h4>
<form name="edit_country" action="EditCountry" method="post">
	Name:
	<input type="text" name="country_name" value="<%= country.name %>" required="required" maxlength="64">
	<input type="submit" value="Submit" name="submit"/>
</form>

</body>
</html>