<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="products.model.Products"%>
<!DOCTYPE html>
<% List<Products> productLists = (List<Products>) request.getAttribute("productLists");
						%>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Products List</title>
<script>
								function confirmGo(m, u) {
									if (confirm(m)) {
										window.location = u;
									}
								}
							</script>
<link href="main.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Ubuntu"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>

<body>
	<h3>Products List</h3>
	<hr size="3" color="gray" />
	<div style="text-align: center;">
		<font color="red"> <c:if test="${param.status == false}">
				<c:out value="Failed to Delete Product" />
			</c:if>
		</font> <font color="green"> <c:if test="${param.status}">
				<c:out value="Product Deleted Successfully" />
			</c:if>
		</font>
	</div>

	<c:choose>
		<c:when test="${productLists.size() == 0}">
			<h3 align="center">
				<c:out value="No Products to Display" />
			</h3>
		</c:when>
		<c:when test="${productLists.size() > 0}">
			<table>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Type</th>
					<th>Serial NO</th>
					<th>Price</th>
					<th><i class="fa fa-bars" style="font-size: 25px"></i></th>
				</tr>
				<c:forEach items="${productLists}" var="product">
					<tr>
						<td><c:out value="${product.id}" /></td>
						<td><c:out value="${product.name}" /></td>
						<td><c:out value="${product.type}" /></td>
						<td><c:out value="${product.serial_no}" /></td>
						<td><c:out value="${product.price}" /></td>
						<td><a
							href="javascript:confirmGo('Do you want to Delete the Product?','DeleteProduct?id=<c:out value="
														${product.id}" />')"><i
								class="fa fa-trash" style="font-size: 25px"></i></a></td>
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