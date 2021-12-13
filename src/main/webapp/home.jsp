<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h2>Hello! ${cookie.user.value}</h2>
	<br>
	<h3>Actions</h3>
	<ul type="disc">
	 <li><a href="registration.jsp">Register Product</a></li>
	 <li><a href="registrations-list.jsp">View Your Registrations</a></li>
	 <li><a href="claims.jsp">View Claims</a></li>
	 <li><a href="Logout">Logout</a></li>
	</ul>
</body>
</html>