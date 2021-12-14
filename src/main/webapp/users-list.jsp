<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Accounts(Admin)</title>
<script>
	function confirmGo(m, u) {
		if (confirm(m)) {
			window.location = u;
		}
	}
</script>
<link href="main.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
	<h3>Registered Users</h3>
	<hr size="3" color="gray" />
	<c:choose>
		<c:when test="${users.size() == 0}">
			<h3 align="center">
				<c:out value="No Users" />
			</h3>
		</c:when>
		<c:when test="${users.size() > 0}">
			<form method="POST" action="UsersList" id="usersList">
			<input style="background-color: aliceblue;" value="${param.search}" type="text" name="search" class="un" placeholder="Search by name or email" autocomplete="off"/>
			<i class="fa fa-search" style="font-size: 20px;position: relative;left:-20%"></i>
			</form>
			<table>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Mobile No</th>
					<th>Created On</th>
					<th><i class="fa fa-bars" style="font-size: 25px"></i></th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.id}" /></td>
						<td><c:out value="${user.name}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td><c:out value="${user.mobileNo}" /></td>
						<td><c:out value="${user.isCreatedAt}" /></td>
						<td></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
	<br>
	<br>
	<button style="margin-left: unset;" class="submit"
		onclick="window.location='Home'" id="myButton">Home</button>
</body>

</html>