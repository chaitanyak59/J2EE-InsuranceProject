<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="ISO-8859-1">
			<link href="main.css" rel="stylesheet" type="text/css">
			<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
			<meta name="viewport" content="width=device-width, initial-scale=1" />
			<link rel="stylesheet" href="fontawesome.min.css">
			<title>Create Account</title>

		</head>

		<body>
			<div class="main">
				<p class="sign" align="center">Create Product</p>
				<form action="CreateProduct" method="POST">
					<br><input type="text" class="un" id="name" name="name" placeholder="Enter Name" required />
					<select name="type" id="type" class="un">
						<option value="Monitor">Monitor</option>
						<option value="TV">TV</option>
						<option value="Camera">Camera</option>
						<option value="Mobile">Mobile</option>
						<option value="Watch">Watch</option>
					</select>


					<input type="text" id="serial_no" name="serial_no" placeholder="Enter Serial no" class="un"
						required />


					<input type="text" id="price" name="price" placeholder="Enter Price" class="un" required />

					<div>
						<button type="submit" class="submit">Create</button>
						<button class="submit" onclick="window.location='Home'" id="myButton" class="submit">Home</button>
					</div>

				</form>
				<div>
					<font color="red">
						<c:if test="${param.status == false}">
							<c:out value="Failed to Create/Product already registered" />
						</c:if>
					</font>
					<font color="green">
						<c:if test="${param.status}">
							<c:out value="Created Successfully" />
						</c:if>
					</font>
				</div>
			</div>

		</body>

		</html>