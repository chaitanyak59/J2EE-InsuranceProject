<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<link href="main.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<h2>Hello! ${cookie.user.value}</h2>
	<br>
	<h3>Actions</h3>
	<ul type="disc">
	 <li><a href="registration.jsp">Register Product</a></li>
	 <li><a href="RegistrationsList">View Registered Products</a></li>
	 <li><a href="ClaimsList">View Claim Requests</a></li>
	 <li><a href="Logout">Logout</a></li>
	</ul>
</body>
</html>