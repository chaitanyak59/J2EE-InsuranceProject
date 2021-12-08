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

font {
	display: inline;
}

input, label, button {
	width: 20vw;
	font-size: 1em;
	display: inline-block;
}
</style>
</head>
<body>
	<div class="container">
		<form action="CreateProduct" method="POST">
			<div class="form-group">
				<label for="name">Name</label> <input type="text"
					class="form-control" id="name" name="name" placeholder="Enter Name"
					required />
			</div>
			<div class="form-group">
				<label for="type">Type</label> <select name="type" id="type">
					<option value="Monitor">Monitor</option>
					<option value="TV">TV</option>
					<option value="Camera">Camera</option>
					<option value="Mobile">Mobile</option>
					<option value="Watch">Watch</option>
				</select>
			</div>
			<div class="form-group">
				<label for="serial_no">Serial No</label> <input type="text"
					class="form-control" id="serial_no" name="serial_no"
					placeholder="Enter Serial no" required />
			</div>
			<div class="form-group">
				<label for="price">Price</label> <input type="text"
					class="form-control" id="price" name="price"
					placeholder="Enter Price" required />
			</div>
			<div class='form-group'>
				<button type="submit">Create</button>
				<font color="red"><c:if test="${param.status == false}">
					<c:out value="Failed to Create/Product already registered" />
				</c:if></font> <font color="green"><c:if test="${param.status}">
					<c:out value="Created Successfully" />
				</c:if></font>
			</div>
		</form>
	</div>
	<br>
	<button onclick="window.location='Home'" id="myButton">Home</button>
</body>
</html>