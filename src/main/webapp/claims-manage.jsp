<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<%
	int index = 0;
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Claims(Admin)</title>
<script>
	function confirmGo(m, u) {
		if (confirm(m)) {
			window.location = u;
		}
	}

	function updateStatus(id, flag) {
	 if(confirm("Do you want to update claim status")) {
		if(flag) {
			window.location=`UpdateClaim?id=${id}&approve=true`;
		} else {
			window.location=`UpdateClaim?id=${id}&approve=false`;
		}
	 }
	}
</script>
<link href="main.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
	<h3>Manage Claim Requests</h3>
	<hr size="3" color="gray" />
    <div style="text-align: center;">
		<font color="red"> <c:if test="${param.status == false}">
				<c:out value="Failed to Update Claim" />
			</c:if>
		</font> <font color="green"> <c:if test="${param.status}">
				<c:out value="Claim status updated Successfully" />
			</c:if>
		</font>
	</div>
	<c:choose>
		<c:when test="${users.size() == 0}">
			<h3 align="center">
				<c:out value="No Active Claims" />
			</h3>
		</c:when>
		<c:when test="${claims_list.size() >= 0}">
			<form method="POST" action="ClaimsList" id="claimsList">
				<input style="background-color: aliceblue;" value="${param.search}" type="text" name="search" class="un" placeholder="Search by category" autocomplete="off"/>
				<i class="fa fa-search" style="font-size: 20px;position: relative;left:-20%"></i>
			</form>
			<table>
				<tr>
					<th>S.NO</th>
					<th>User Email</th>
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
						<td><a style="text-decoration:underline;" href="mailto:<c:out value="${item.userEmail}"/>"><c:out value="${item.userEmail}" /></a></td>
						<td><c:out value="${item.productName}" /></td>
						<td><c:out value="${item.category}" /></td>
						<td><c:out value="${item.description}" /></td>
						<td><c:out value="${item.claimStatus ? 'Approved' : 'Pending'}" /></td>
						<td><c:out value="${item.lastModifiedDate}" /></td>
						<td><a onclick="updateStatus(<c:out value="${item.id}" />, true)"><font style="font-weight:bold;text-decoration:underline;color:blue;">Approve</font></a> /
							<a onclick="updateStatus(<c:out value="${item.id}" />, false"><font style="font-weight:bold;text-decoration:underline;color:blue;">Reject</font></a>
						</td>
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