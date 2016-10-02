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
<title>View Feeddback</title>
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
						<font color="blue">View Feedback </font>
					</h2></th>
			</tr>
			<tr>
			</tr>
		</table>
		</div>
	<div align="center">
		<table align="center" border="1" bgcolor="white" width="70%"
			height="70%">

			<tr bgcolor="skyblue">
				<th>FeedBackId</th>
				<th>Subject</th>
				<th>Message</th>
				<th>From</th>

			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>

			<c:if test="${not empty feedback}">
				<c:forEach var="u" items="${feedback}">
					<tr>
						<td align="center">${u.feedbackId}</td>
						<td align="center">${u.subject}</td>
						<td align="center">${u.message}</td>
						<td align="center">${u.fromName}</td>
					</tr>

				</c:forEach>
			</c:if>

		</table>
	</div>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>