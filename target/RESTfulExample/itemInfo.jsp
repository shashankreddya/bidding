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
<title> Items</title>
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
		</b> </font> <font color="red" size="6" face="verdana">Item details</font>
	</center>
	<br>
	<br>
	<br>
	<table align="center" width="60%">
		<tr>
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>
		<tr></tr>
		<tr></tr>
		<tr>
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>

		<c:if test="${not empty singleItem}">
			<%
				Items item = (Items) request.getAttribute("singleItem");
					session.setAttribute("singleItem", item);
			%>
			<tr>

				<td align="center" width="35%"><font color="DarkViolet "
					size="" face="verdana">${singleItem.itemName}</font> <img
					alt="See Photo Here" id="previewField"
					src="${singleItem.smallImagePath}" height="200" width="120"
					align="middle" /></td>

				<td align="center" width="35%"><img alt="See Photo Here"
					id="previewField" src="${singleItem.bigImagePath}" height="200"
					width="120" align="middle" /></td>

				<td align="center" width="15%"><font color="DarkViolet "
					size="2" face="verdana"> ${singleItem.price} $ </font></td>

				<td align="center" width="15%"><font color="DarkViolet "
					size="2" face="verdana">${singleItem.summary}</font></td>

				<td align="center" width="15%"><font color="DarkViolet "
					size="2" face="verdana">${singleItem.description}</font></td>

				<td align="center"><a
					href="<%=request.getContextPath()%>/updateItem.jsp"><font
						color="red " size="2" face="verdana">Update</font></a></td>
				<td>||</td>

				<td align="center"><a
					href="<%=request.getContextPath()%>/DeleteItemAction?itemid=${singleItem.itemId}"><font
						color="red " size="2" face="verdana">Delete</font></a></td>
			</tr>

		</c:if>

	</table>
</body>
</html>