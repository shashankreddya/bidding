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
<title>Winners</title>
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
					<font color="blue">Winners List </font>
				</h2></th>
		</tr>
		<tr>
		</tr>
</table>
</div>
<div align="center">
		<table align="center" border="1" bgcolor="">
			<thead>
				<c:if test="${not empty winners}">
					<c:forEach var="item" items="${winners}">
						<tr>
							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.firstName}${item.lastName} </font></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.phone}</font></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.emailId}</font></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.itemName}</font></td>
								
								<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.bidAmount}</font></td>

							<c:if test="${requestScope.role=='ADMIN'}">
							<td align="center"><a
								href="<%=request.getContextPath()%>/winnerTransaction.jsp?bidid=${item.bidId}&itemname=${item.itemName}"><font
									color="red " size="2" face="verdana">ViewTransactionDetails</font></a></td>
							</c:if>
							
						</tr>
						
					</c:forEach>
				</c:if>
			</thead>
		</table>
		</div>
		<br />
		<br />



	</table>

	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>