<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="registration.model.Registrations"%>
<!DOCTYPE html>
<%
	int index = 0;
%>
<html>
<head>
	<link href="main.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<meta charset="ISO-8859-1">
<title>Your Registrations</title>
</head>
<body>
	<h3>Your Registrations</h3>
	<hr size="3" color="gray" />
	<c:choose>
		<c:when test="${registrations.size() == 0}">
			<h3 align="center">
				<c:out value="You have no registrations" />
			</h3>
		</c:when>
		<c:when test="${registrations.size() >= 0}">
			<table>
				<tr>
					<th>S.NO</th>
					<th>Name</th>
					<th>Purchase Date</th>
					<th>Registration Date</th>
					<th>Product Type</th>
				</tr>
				<c:forEach items="${registrations}" var="item">
					<tr>
						<td><%=++index%></td>
						<td><c:out value="${item.getName()}" /></td>
						<td><c:out value="${item.purchaseDate}" /></td>
						<td><c:out value="${item.registrationDate}" /></td>
						<td><c:out value="${item.productType}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
	<br><br>
	<button style="margin-left:unset;" class="submit" onclick="window.location='Home'" id="myButton">Home</button>
</body>
</html>