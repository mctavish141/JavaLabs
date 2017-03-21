<%@page import="com.flights.CountryAccess"%>
<%@page import="java.util.List"%>
<%@page import="com.flights.Country"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Country</title>
</head>
<body>

<%
Country country = (Country)request.getAttribute("country");
%>
Name: <%= country.name %>
<br>
<a href="EditCountry?country_id=<%= country.id %>">Edit country</a>
<br>
<a href="DeleteCountry?country_id=<%= country.id %>">Delete country</a>

</body>
</html>