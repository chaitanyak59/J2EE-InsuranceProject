<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
<style>
body {
	font-family: sans-serif;
}

form {
	margin: 0 auto;
}

input, label, button {
	width: 10vw;
	font-size: 1em;
	display: inline-block;
}
</style>
</head>
<body>
	<div class="container">
		<form action="Create" method="POST">
			<div class="form-group">
				<label for="name">Name:</label> <input type="text"
					class="form-control" id="name" name="name" placeholder="Enter Name"
					required />
			</div>
			<div class="form-group">
				<label for="address">Address:</label> <input type="text"
					class="form-control" id="address" name="address"
					placeholder="Enter Address" required />
			</div>
			<div class="form-group">
				<label for="mobileNo">Phone:</label> <input type="text"
					class="form-control" id="mobileNo" name="mobileNo"
					placeholder="Enter mobileNo" required />
			</div>
			<div class="form-group">
				<label for="email">Email Address:</label> <input type="email"
					class="form-control" id="email" name="email"
					placeholder="Enter Email" required />
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password"
					placeholder="Enter Password" required />
			</div>
			<div class="form-group">
				<label for="cpassword">Confirm Password:</label> <input type="password"
					class="form-control" id="cpassword" name="confirm-password"
					placeholder="Confirm Password" required />
			</div>
			<div class='form-group'>
				<button type="submit">Create</button>
			</div>
		</form>
	</div>
	<font color="red"><c:if test="${param.status == false}">
			<c:out value="Registered Already" />
		</c:if></font>
	<font color="green"><c:if test="${param.status}">
			<c:out value="Registered Successfully" />
		</c:if></font>
</body>
</html>