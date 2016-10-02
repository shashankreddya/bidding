<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.auction.pojo.Items"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/gen_validatorv31.js">
	
</script>
<script type="text/javascript" src="js/image.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Users</title>
</head>
<body>
	<jsp:include page="/jsp/adminheader.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<br>
	<center>
		<font color="red"><b> <c:if
					test="${requestScope.status!='null'}">

					<c:out value="${requestScope.status}"></c:out>
				</c:if>
		</b> </font> <font color="red" size="6" face="verdana">User's Details</font>
	</center>
	<br>
	<br>
	<br>
	<div align="center">
	<table align="center" border="1" bgcolor="white" width="70%"
		cellspacing="10">

		<tr bgcolor="skyblue">
			<th>Full Name</th><th></th>
			<th>Gender</th>
			<th>Email</th><th></th>
			<th>Phone No</th>
			<th>House No</th>
			<th>Area</th>
			<th>City</th>
			<th>State</th>
			<th>Country</th>
			<th>Pin</th>
			<th>Status</th>
		</tr>


		<c:if test="${not empty user}">
			<c:forEach var="u" items="${user}">





				<tr>

					<td>${u.firstName}.${u.lastName}</td><td></td>
					<td>${u.gender}</td>
					<td>${u.emailId}</td><td></td>
					<td>${u.phone}</td>
					<td>${u.houseNo}</td>
					<td>${u.street}</td>
					<td>${u.city}</td>
					<td>${u.state}</td>
					<td>${u.country}</td>
					<td>${u.pin}</td>
					<td><a href="<%=request.getContextPath()%>/ChangeStatusAction?userStatus=${u.status}&userId=${u.userId}">${u.status=='active'?'Active':'Inactive' }</a></td>

					

				</tr>

			</c:forEach>
		</c:if>

	</table>
	</div>
</body>
</html>