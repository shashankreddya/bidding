<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/gen_validatorv31.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LoginPage</title>
</head>
<body>
	<jsp:include page="/jsp/homeheader.jsp"></jsp:include>
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
	<form action="<%=request.getContextPath()%>/LoginAction" name="login">
	<div align="center">
		<table align="center">
		<tbody>
			<tr align="center">
				<th><h2>
						<font color="blue">Login Page </font>
					</h2></th>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">First Name :</font></td>
				<td><input type="text" name="userName" value="" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Password : </font></td>
				<td><input type="password" name="password" value="" /></td>
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
			<tr>
				<td></td>
				<td><a
					href="<%=request.getContextPath()%>/passwordrecovery.jsp"><font
						color="red" size="3">Forgot Password</font></a></td>
			</tr>
			<tr>
				<td></td>
				<td><a href="<%=request.getContextPath()%>/registration.jsp"><font
						color="red" size="3">Registration</font></a></td>
			</tr>
       </tbody>
		</table>
		</div>
	</form>
	<script language="JavaScript" type="text/javascript">
		//You should create the validator only after the definition of the HTML form
		var frmvalidator = new Validator("login");
		frmvalidator.addValidation("userName", "req",
				"Please enter your Username");
		frmvalidator.addValidation("password", "req",
				"Please enter your Password");
	</script>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>