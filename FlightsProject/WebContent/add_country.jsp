<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new country</title>
</head>
<body>

<h4>Add new country:</h4>
<form name="add_country" action="AddCountry" method="post">
	Name:
	<input type="text" name="country_name" required="required" maxlength="64">
	<input type="submit" value="Submit" name="submit"/>
</form>

</body>
</html>