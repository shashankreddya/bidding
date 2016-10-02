<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/gen_validatorv31.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recover Password</title>
</head>
<body>
	<jsp:include page="/jsp/homeheader.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<center>
		<font color="red"><b> <c:if
					test="${requestScope.status!='null'}">
					<c:out value="${requestScope.status}"></c:out>
				</c:if> <br> <c:if test="${requestScope.password!='null'}">
					<c:out value="${requestScope.password}"></c:out>
				</c:if> 
		</b> </font>

	</center>

	<br>
	<form action="<%=request.getContextPath()%>/PasswordRecoveryAction"
		name="recovery">
		<div align="center">
			<table align="center">
				<tr align="center">
					<th><h2>
							<font color="blue">Password Recovery </font>
						</h2></th>
				</tr>
				<tr>
					<td><font color="DarkViolet" size="3" face="verdana">UserName
							:</font></td>
					<td><input type="text" name="userName" value="" /></td>
				</tr>
				<tr>
					<td>
				<tr>
					<td><font color="DarkViolet" size="3" face="verdana">Security
							Question :</font></td>
					<td><select name="question">
							<option value="select" selected="true"><font size="3"
									face="Verdana">--Select--</font>
							</option>
							<option value="Your Childhood friend"><font size="3"
									face="Verdana">Your Childhood friend</font>
							</option>
							<option value="Your first teacher"><font size="3"
									face="Verdana">Your first teacher</font>
							</option>
							<option value="Your pet name"><font size="3"
									face="Verdana">Your pet name</font>
							</option>
							<option value="Your first bank account"><font size="3"
									face="Verdana">Your first bank account</font>
							</option>
					</select></td>
				</tr>

				<tr>
					<td><font color="DarkViolet" size="3" face="verdana">Answer
							:</font></td>
					<td><input type="password" name="answer" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Submit"></td>
				</tr>
			</table>
		</div>
	</form>
	<script language="JavaScript" type="text/javascript">
		//You should create the validator only after the definition of the HTML form
		var frmvalidator = new Validator("recovery");
		frmvalidator.addValidation("userName", "req",
				"Please enter your Username");
		frmvalidator.addValidation("question", "dontselect=0");
		frmvalidator.addValidation("answer", "req",
				"Please enter your correct Answer");
	</script>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>