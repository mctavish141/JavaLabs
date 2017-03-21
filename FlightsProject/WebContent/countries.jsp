<%@page import="com.flights.CountryAccess"%>
<%@page import="java.util.List"%>
<%@page import="com.flights.Country"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Countries</title>
</head>
<body>

<h2>Countries:</h2>
<%
List<Country> countries = CountryAccess.readAll();
for (Country country : countries) {
	%>
	<%= country.name %><br>
	<%
}
%>

<h4>Add new country:</h4>
<form name="add_country" action="AddCountry" method="post">
	Name:
	<input type="text" name="country_name" required="required" maxlength="64">
	<input type="submit" value="Submit" name="submit"/>
</form>

</body>
</html>