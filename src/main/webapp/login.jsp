<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="main.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Ubuntu"
	rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="fontawesome.min.css">
<title>Login to Insurance</title>
</head>

<body>
	<div class="main">
		<p class="sign" align="center">Login</p>
		<div style="text-align: center;">
			<font color="red"> <c:if
					test="${param.authenticated == false}">
					<c:out value="Invalid Credentials" />
				</c:if>
			</font> <font color="green"> <c:if test="${param.authenticated}">
					<c:out value="Valid Credentials" />
				</c:if>
			</font>
		</div>
		<form action="Login" method="post" class="form1">
			<input type="text" name="email" value="${param.email}" class="un"
				placeholder="Enter email" /> <input type="password" name="password"
				class="pass" value="${param.password}" placeholder="Password" /> <input
				type="submit" class="submit" value="Login" />
		</form>
	</div>



</body>

</html>