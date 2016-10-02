<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.*, com.auction.daomgr.ItemsDaoManager,com.auction.pojo.*"%>
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
		</b> </font>

	</center>
	<div align="center">
	<table align="center">
		<tr align="center">
			<th align="center"><h2>
					<font color="blue">Transaction Details</font>
				</h2></th>
		</tr>
		<tr>
		</tr>
	</table>
	</div>
	<%
		String bidid = request.getParameter("bidid");
		String itemName = request.getParameter("itemname");
		Transaction transaction = new ItemsDaoManager().getTransactionInfo(Integer.parseInt(bidid));
	%>
<div align="center">
	<table align="center" border="1" bgcolor="">
		<thead>

			<tr>
				<td align="center" width="15%"><font color="DarkViolet "
					size="2" face="verdana">Transaction Id : <%=transaction.getTransactionId()%>
				</font></td>

			</tr>
			<tr>

				<td align="center" width="15%"><font color="DarkViolet "
					size="2" face="verdana">Item Name : <%=itemName%>
				</font></td>
			</tr>
			<tr>
				<td align="center" width="15%"><font color="DarkViolet "
					size="2" face="verdana">Transaction Amount : <%=transaction.getTransactionAmount()%></font></td>
			</tr>
			<tr>
				<td align="center" width="15%"><font color="DarkViolet "
					size="2" face="verdana">Transaction Date : <%=transaction.getTransactionDate()%></font></td>

			</tr>
			<tr>


				<td align="center"><a
					href="<%=request.getContextPath()%>/ViewWinners"><font
						color="red " size="2" face="verdana">Back</font></a></td>

			</tr>

		</thead>
	</table>
	</div>
	<br />
	<br />




	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>