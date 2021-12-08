<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to Insurance</title>
</head>
<body>
	<form action="Login" method="post">
		Enter email : <input type="text" name="email" value="${param.email}" /> <BR>
		Enter password : <input type="password" name="password" value="${param.password}"/> <BR>
		<input type="submit" />
	</form>
	<font color="red"><c:if test="${param.authenticated == false}">
            <c:out value="Invalid Credentials" />
        </c:if></font>
        <font color="green"><c:if test="${param.authenticated}">
            <c:out value="Valid Credentials" />
        </c:if></font>
</body>
</html>