<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="ISO-8859-1">
			<link href="main.css" rel="stylesheet" type="text/css">
			<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
			<meta name="viewport" content="width=device-width, initial-scale=1" />
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
			<title>Create Account</title>
		</head>

		<body>
			<div class="main">
				<p class="sign" align="center">Create Account</p>

				<form action="Create" method="POST">

					<br><input type="text" class="un" id="name" name="name" placeholder="Enter Name" required />
					<input type="text" class="un" id="address" name="address" placeholder="Enter Address" required />
					<input type="text" class="un" id="mobileNo" name="mobileNo" placeholder="Enter mobileNo" required />
					<input type="email" class="un" id="email" name="email" placeholder="Enter Email" required />
					<input type="password" class="un" id="password" name="password" placeholder="Enter Password"
						required />
					<input type="password" class="un" id="cpassword" name="confirm-password"
						placeholder="Confirm Password" required />
					<button type="submit" class="submit">Create</button>
				</form>
			</div>
			<font color="red">
				<c:if test="${param.status == false}">
					<c:out value="Registered Already" />
				</c:if>
			</font>
			<font color="green">
				<c:if test="${param.status}">
					<c:out value="Registered Successfully" />
				</c:if>
			</font>
		</body>

		</html>