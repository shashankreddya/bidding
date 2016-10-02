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
<title>Change Password</title>
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

	</center>
	<div align="center">
		<table align="center">
			<tr align="center">
				<th align="center"><h2>
						<font color="blue">Change Password </font>
					</h2></th>
			</tr>
			<tr>
			</tr>
		</table>
	</div>
	<form action="<%=request.getContextPath()%>/ChangePasswordAction" name="changepassword">
	<div align="center">
		<table align="center">
		<tbody>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Current Password : </font></td>
				<td><input type="password" name="oldpassword" value="" /></td>
			</tr>
			
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">New Password : </font></td>
				<td><input type="password" name="newpassword" value="" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
			<tr>
			
       </tbody>
		</table>
		</div>
	</form>
	<script language="JavaScript" type="text/javascript">
		//You should create the validator only after the definition of the HTML form
		var frmvalidator = new Validator("changepassword");
		frmvalidator.addValidation("oldpassword", "req",
				"Please enter your current password");
		frmvalidator.addValidation("newpassword", "req",
				"Please enter New Password");
	</script>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>