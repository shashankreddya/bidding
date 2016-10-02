<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/jsp/homeheader.jsp"></jsp:include>
 <form action="">
   <table align="center"  cellpadding="12"
			cellspacing="8">
			<tr>
				<td ><font color="#FFBE9">USER NAME</font></td>
				<td><input type="text" name="loginid" value=""></td>
			</tr>
			<tr>
				<td><font color="#FFBE9">PASSWORD</font></td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td><input type="submit" value="submit"></td>
				<td><input type="reset" value="clear"></td>
			</tr>

		</table>
		</form>
		<center>
			<a href="./jsps/recoverpassword.jsp">Forgot Password></a>
		</center>
		<br>
		<center>
			<a href="./jsps/registrationform.jsp">User Register </a>
		</center>
  <jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>