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
<title>Today Auction Items</title>
</head>
<body>
	<c:choose>
		<c:when test="${requestScope.role=='Seller'}">
			<jsp:include page="/jsp/sellerheader.jsp"></jsp:include>
			<br />
		</c:when>
		<c:when test="${requestScope.role=='ADMIN'}">
			<jsp:include page="/jsp/adminheader.jsp"></jsp:include>
			<br />
		</c:when>
		<c:when test="${requestScope.role=='Buyer'}">
			<jsp:include page="/jsp/buyerheader.jsp"></jsp:include>
			<br />
		</c:when>
		<c:otherwise>
			<jsp:include page="/jsp/homeheader.jsp"></jsp:include>
			<br />
		</c:otherwise>
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
						<font color="blue">View Transactions </font>
					</h2></th>
			</tr>
			<tr>
			</tr>
		</table>
	</div>
	<div align="center">
		<table align="center" border="1" bgcolor="">
			<thead>
				<tr bgcolor="skyblue">
					<th>TransactionId</th>
					<th>Item Name</th>
					<th>TransactionAmount</th>
					<th>TransactionDate</th>
					<th>SellerName</th>
					<th>BuyerName</th>
				</tr>
				<c:if test="${not empty transation}">
					<c:forEach var="item" items="${transation}">

						<tr>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.transactionId}</font></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.itemName}</font></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.transactionAmount}</font></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.transactionDate}</font></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.sellerName}</font></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.buyerName}</font></td>

						</tr>

					</c:forEach>
				</c:if>
			</thead>
		</table>
	</div>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>