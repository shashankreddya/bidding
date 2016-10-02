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
<title>View Auction Dates</title>
</head>
<body>
	<jsp:include page="/jsp/sellerheader.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<br>
	<center>
		<font color="red"><b> <c:if
					test="${requestScope.status!='null'}">

					<c:out value="${requestScope.status}"></c:out>
				</c:if>
		</b> </font> <font color="red" size="6" face="verdana">Auction dates </font>
	</center>
	<br>
	<br>
	<br>
	<div align="center">
	<table align="center" border="1" bgcolor="white" width="70%"
		cellspacing="10">

		<tr bgcolor="skyblue">
			<th>Item Name</th>
			<th>AuctionStartDate</th>
			<th>AuctionEndDate</th>
			<th>Status</th>

		</tr>


		<c:if test="${not empty bid}">
			<c:forEach var="b" items="${bid}">





				<tr>

					<td align="center">${b.itemName}</td>
					<td align="center">${b.bidStartDate}</td>
					<td align="center">${b.bidEndDate}</td>
					<td align="center">${b.status} ${b.status eq 'init'?'(Waiting for Admin approval)':''} </td>

				</tr>

			</c:forEach>
		</c:if>

	</table>
	</div>
</body>
</html>