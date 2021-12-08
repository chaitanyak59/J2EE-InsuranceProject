<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Admin</title>
</head>
<body>
	<h2>Hello! ${cookie.user.value}</h2>
	<br>
	<h3>Actions</h3>
	<ul type="disc">
	 <li><a href="create-product.jsp">Create Product</a></li>
	 <li><a href="Products">View Registered Products</a></li>
	 <li><a href="Logout">Logout</a></li>
	</ul>
</body>
</html>