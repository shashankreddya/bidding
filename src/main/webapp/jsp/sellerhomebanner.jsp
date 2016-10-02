<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller</title>
</head>
<body>

	<div id="banner">
		<!--  <img alt="noimg" src="images/pic05.jpg"> -->
		<center>
			<font color="red"><b> <c:if
						test="${requestScope.status!='null'}">

						<c:out value="${requestScope.status}"></c:out>
						<%-- <c:out value="${param.status}"></c:out> --%>
					</c:if>
			</b> </font>

		</center>
	</div>
	<br></br><br></br><br></br><br></br><br></br><br></br>
</body>
</html>