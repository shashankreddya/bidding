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
<title>View Items</title>
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
		</b> </font> <font color="red" size="6" face="verdana">Items</font>
	</center>
	<br>
	<br>
	<br>
	<table align="center" width="60%">
		<%-- <caption >
			<font color="red" size="6" face="verdana">Items</font>
		</caption> --%>
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
		<tr>
			<!-- <th><font color="DarkOrange " size="4" face="verdana">Item Name</font></th>
			<th><font color="DarkOrange " size="" face="verdana">Image</font></th> -->
		</tr>
		<tr></tr>
		<tr></tr>
		<tr>
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>
	</table>
	<div align="center">
		<table>
			<c:if test="${not empty itemsList}">
				<c:set var="itemslst" value="${itemsList}" scope="request"></c:set>
				<c:forEach var="item" items="${itemsList}">
					<tr>
						<%-- <td align="center" width="15%"><font color="DarkViolet " size="" face="verdana">${item.itemName}</font></td> --%>

						<td align="center" width="70%"><a
							href="<%=request.getContextPath()%>/ViewItemsAction?singleItem=${item.itemId}"><font
								color="DarkViolet " size="" face="verdana">${item.itemName}</font>
								<img alt="See Photo Here" id="previewField"
								src="${item.smallImagePath}" height="200" width="120"
								align="middle" /></a></td>

						<td align="center" width="15%"><font color="DarkViolet "
							size="2" face="verdana">${item.price}</font></td>

						<td align="center" width="15%"><font color="DarkViolet "
							size="2" face="verdana">${item.summary}</font></td>

						<%-- <td border="" align="center" rowspan="" colspan=''><img
						alt="See Photo Here" id="previewField" src="${item.bigImagePath}"
						height="80" width="80" /></td> --%>
						<%-- <td align="center"><a
						href="./DeleteCropAction?cropid=${crop.cropid}">Delete</a></td> --%>
					</tr>

				</c:forEach>
			</c:if>

		</table>
	</div>
</body>
</html>