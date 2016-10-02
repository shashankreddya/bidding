<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/gen_validatorv31.js">
	
</script>
<script type="text/javascript" src="js/image.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Profile</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.ROLE=='Seller'}">
			<jsp:include page="/jsp/sellerheader.jsp"></jsp:include>
			<br />
		</c:when>
		<c:when test="${sessionScope.ROLE=='ADMIN'}">
			<jsp:include page="/jsp/adminheader.jsp"></jsp:include>
			<br />
		</c:when>
		<c:when test="${sessionScope.ROLE=='Buyer'}">
			<jsp:include page="/jsp/buyerheader.jsp"></jsp:include>
			<br />
		</c:when>
	</c:choose>
	<br>
	<br>
	<br>
	<br>
	<center>
		<font color="red"><b> <c:if
					test="${requestScope.status!='null'}">

					<c:out value="${requestScope.status}"></c:out>
				</c:if>
		</b> </font>
		<h2>
			<font color="blue">Update Profile </font>
		</h2>
	</center>
   <div align="center">
	<form action="<%=request.getContextPath()%>/UpdateProfileAction"
		name="profile">
		<c:if test="${not empty user}">
			<c:forEach var="u" items="${user}">
				<table align="center" width="80%" height="100%" border="1">
					<tr>
						<td nowrap="nowrap"><font color="DarkViolet" size="3"
							face="verdana">First Name :</font></td>
						<td><input type="hidden" name="userId" value="${u.userId}" /><input
							type="text" name="firstName" value="${u.firstName}" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="DarkViolet" size="3" face="verdana">Last
								Name :</font></td>
						<td><input type="text" name="lastName" value="${u.lastName}" /></td>
					</tr>
					<tr>
						<td><font color="DarkViolet" size="3" face="verdana">Gender
								:</font></td>
						<td><input type="text" name="gender" value="${u.gender}" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="DarkViolet" size="3" face="verdana">Email
								:</font></td>
						<td><input type="text" name="emailId" value="${u.emailId}" /></td>
					</tr>
					<tr>
						<td><font color="DarkViolet" size="3" face="verdana">Phone Number
								:</font></td>
						<td><input type="text" name="phone" value="${u.phone}" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="DarkViolet" size="3" face="verdana">Bank
								Name :</font></td>
						<td><input type="text" name="bankName" value="${u.bankName}" /></td>
					</tr>
					
					<tr>
						<td><font color="DarkViolet" size="3" face="verdana">Card Number
								:</font></td>
						<td><input type="text" name="cardNo" value="${u.cardNo}" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="DarkViolet" size="3" face="verdana">Security code(cvv)
								 :</font></td>
						<td><input type="text" name="securityCode" value="${u.securityCode}" /></td>
					</tr>
					
					<tr>
						<td><font color="DarkViolet" size="3" face="verdana">Expiry year
								:</font></td>
						<td><input type="text" name="expYear" value="${u.expYear}" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="DarkViolet" size="3" face="verdana">Expiry
								Month :</font></td>
						<td><input type="text" name="expMonth" value="${u.expMonth}" /></td>
					</tr>
					
					<tr>
						<td><font color="DarkViolet" size="3" face="verdana">House Number
								:</font></td>
						<td><input type="text" name="houseNo" value="${u.houseNo}" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="DarkViolet" size="3" face="verdana">Location
								 :</font></td>
						<td><input type="text" name="street" value="${u.street}" /></td>
					</tr>
					
					<tr>
						<td><font color="DarkViolet" size="3" face="verdana">City
								:</font></td>
						<td><input type="text" name="city" value="${u.city}" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="DarkViolet" size="3" face="verdana">State
								 :</font></td>
						<td><input type="text" name="state" value="${u.state}" /></td>
					</tr>
					
					<tr>
						<td><font color="DarkViolet" size="3" face="verdana">Country
								:</font></td>
						<td><input type="text" name="country" value="${u.country}" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td><font color="DarkViolet" size="3" face="verdana">Pin
								 :</font></td>
						<td><input type="text" name="pin" value="${u.pin}" /></td>
					</tr>
					<tr><td>&nbsp;</td>
						<td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td>
						<td>&nbsp;</td></tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td align="right"><input type="submit" value="Update"></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<tr>

		
				</table>
		</c:forEach>
			</c:if>
	</form>
	</div>
	<script language="JavaScript" type="text/javascript">
		//You should create the validator only after the definition of the HTML form
		var frmvalidator = new Validator("profile");
		frmvalidator.addValidation("firstName", "req", "Please enter FirstName");
		frmvalidator.addValidation("lastName", "req", "Please enter  Last Name");
	</script>
						<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>

					</body>
</html>