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
<script>
function bigImg(x) {
    x.style.height = "200px";
    x.style.width = "150px";
}

function normalImg(x) {
    x.style.height = "64px";
    x.style.width = "64px";
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Items</title>
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
						<font color="blue">View Items </font>
					</h2></th>
			</tr>
			<tr>
			</tr>
		</table>
	</div>
	<form action="" name="items">
		<div align="center">
			<table align="center" border="1" bgcolor="">
				<tr>
					<td align="center"><font color="blue">Select Category :
					</font></td>
					<td align="center"><select select name="cat" id="select"
						onchange="javascript:if(document.items.cat.value==''){alert('Select Category');}  {location.href='<%=request.getContextPath()%>/ViewItemsByCategoryAction?categoryName='+document.items.cat.value;}">
							<c:if test="${not empty category}">
								<option value="">--LIST OF CATEGORIES--</option>
								<c:forEach var="depart" items="${category}">
									<option value="${depart.categoryName}">${depart.categoryName}</option>
								</c:forEach>
							</c:if>


					</select></td>

				</tr>

			</table>
		</div>
		<div align="center">
			<table align="center" border="1" bgcolor="white" width="80%"
			height="100%">

			
				<c:if test="${not empty itemsList}">
				<tr bgcolor="skyblue">
				<th>ItemName</th>
				<th>ItemPrice</th>
				<th>Summary</th>
				<th>SetAuctionDates</th>
			</tr>
					<c:set var="itemslst" value="${itemsList}" scope="request"></c:set>
					<c:forEach var="item" items="${itemsList}">
						<tr>
							<%-- <td align="center" width="15%"><font color="DarkViolet " size="" face="verdana">${item.itemName}</font></td> --%>

							<td align="center" width="70%"><font color="DarkViolet "
								size="" face="verdana">${item.itemName}</font> <img
								alt="See Photo Here" id="previewField" onmouseover="bigImg(this)" onmouseout="normalImg(this)"
								src="${item.smallImagePath}" height="60" width="60"
								align="middle" /></td>
								<td align="center" width="30%"> <img
								alt="See Photo Here" id="previewField" onmouseover="bigImg(this)" onmouseout="normalImg(this)"
								src="${item.bigImagePath}" height="60" width="60"
								align="middle" /></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.price}</font></td>

							<td align="center" width="15%"><font color="DarkViolet "
								size="2" face="verdana">${item.summary}</font></td>


							<td align="center"><a
								href="<%=request.getContextPath()%>/SeeAuctionDatesAction?itemId=${item.itemId}&itemName=${item.itemName}"><font
									color="red " size="2" face="verdana">SeeAuctionDates</font></a></td>
						</tr>

					</c:forEach>
				</c:if>

			</table>
		</div>
		<br /> <br />
	</form>

	<br>
	<br>
	<%-- <%
	String value = (String) request.getAttribute("categoryName");
		if (value != null) {
	%>
	<center>
		<a
			href="<%=request.getContextPath()%>/addItemsFormBySeller.jsp?categoryName=${requestScope.categoryName}"><font
			color="red " size="2" face="verdana">Add New Item</font> </a>
	</center>
	<%
		}
	%> --%>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>