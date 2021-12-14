<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link href="main.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<title>Claim Request Form</title>
<script>
function redirectClaimsList() {
	window.location="ClaimsList";
	return false;
}
</script>
</head>
<body>
	<div class="main">
		<p class="sign" align="center">Claim Request Form</p>
		<div style="text-align: center;">
			<font color="red">
				<c:if test="${param.status == false}">
					<c:out value="Failed to Create/Invalid Details" />
				</c:if>
			</font>
			<font color="green">
				<c:if test="${param.status}">
					<c:out value="Claim Created Successfully! " />
				</c:if>
			</font>
		</div>
		<form action="CreateClaim" method="POST">
			<br><input type="hidden" id="product_id" name="reg_id" value="${param.id}"/>
			<select name="category" id="category" class="un">
				<option value="ScreenDamage">Screen Damage</option>
				<option value="BatteryRepair">BatteryRepair</option>
				<option value="Theft">Theft</option>
				<option value="HadwareIssue">HadwareIssue</option>
				<option value="SoftwareIssue">SoftwareIssue</option>
			</select>


			<textarea rows="4" cols="50" maxlength="300" id="description" name="description" class="un" placeholder="Details about your issue" ></textarea>
			<div class="flex-btn-submit" style="right:18%">
				<button type="submit" class="submit">Create</button>
			    <input type="button" class="submit" onclick="redirectClaimsList()" id="myButton" value="Your Claims">
			</div>
		</form>
	</div>
</body>
</html>