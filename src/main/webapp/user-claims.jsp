<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="claims.model.Claims"%>
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
<title>Your Claims</title>
<script>
function confirmGo(m, u) {
	if (confirm(m)) {
		window.location = u;
	}
}
</script>
</head>
<body>
	<h3>Your Claims</h3>
	<hr size="3" color="gray" />
	<div style="text-align: center;">
		<font color="red"> <c:if test="${param.status == false}">
				<c:out value="Failed to Cancel Claim" />
			</c:if>
		</font> <font color="green"> <c:if test="${param.status}">
				<c:out value="Claim request cancelled Successfully" />
			</c:if>
		</font>
	</div>
	<c:choose>
		<c:when test="${claims_list.size() == 0}">
			<h3 align="center">
				<c:out value="You have no requests" />
			</h3>
		</c:when>
		<c:when test="${claims_list.size() > 0}">
			<table>
				<tr>
					<th>S.NO</th>
					<th>Name</th>
					<th>Category</th>
					<th>Description</th>
					<th>Claim Status</th>
					<th>Last Updated Date</th>
					<th><i class="fa fa-bars" style="font-size:25px"></i></th>
				</tr>
				<c:forEach items="${claims_list}" var="item">
					<tr>
						<td><%=++index%></td>
						<td><c:out value="${item.productName}" /></td>
						<td><c:out value="${item.category}" /></td>
						<td><c:out value="${item.description}" /></td>
						<td><c:out value="${item.claimStatus ? 'Approved' : 'Pending'}" /></td>
						<td><c:out value="${item.lastModifiedDate}" /></td>
						<td><a href="javascript:confirmGo('Do you want to cancel current claim?','DeleteClaim?id=<c:out value="
														${item.id}" />')"><font style="font-weight:bold;text-decoration:underline;color:blue;">Cancel Claim</font></a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
	<br><br>
	<button style="margin-left:unset;" class="submit" onclick="window.location='Home'" id="myButton">Home</button>
</body>
</html>