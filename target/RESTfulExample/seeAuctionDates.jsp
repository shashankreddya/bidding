<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.auction.pojo.Items" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/gen_validatorv31.js">
</script>
<script language="JavaScript" type="text/javascript"
	src="js/ts_picker.js">
	</script>
<script type="text/javascript" src="js/image.js">
	
</script>
<script type="text/javascript">
 function statusAlert(){
	 var statusForChange = document.getElementById("status").value;
	 alert("1. init -- Default value. \n 2. start -- starting auction. \n 3. end -- closed auction ");
 }
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
		</b> </font>
       <font color="red" size="6" face="verdana">Auction Dates </font>
	</center>
	<br><br><br>
	<div align="center">
	<table align="center"   width="60%" >
		
		<tr>
		<th><font color="red " size="3" face="verdana">Item Name</font></th><th><font color="red " size="3" face="verdana">AuctionStartDate</font></th><th><font color="red " size="3" face="verdana">AuctionEndDate</font></th><th><font color="red " size="3" face="verdana">Status(init or start or end)</font></th>
		</tr>
		<tr>
		<td></td><td></td><td></td><td></td>
		<td></td><td></td><td></td><td></td>
		</tr>
		<c:if test="${not empty bid}">
		<c:set var="itemslst" value="${bid}" scope="request"></c:set>
			<c:forEach var="item" items="${bid}">
			<form name="changeStatus" action="<%=request.getContextPath()%>/UpdateBidStatusAction" method="post">
			
				<tr>
					    <td align="center" width="15%"><font color="DarkViolet " size="" face="verdana">${requestScope.itemName}<input type="hidden" name="bidid" value="${item.bidId}"/></font></td>
						<td align="center" width="15%"><font color="DarkViolet " size="2" face="verdana"><input type="text" name="startdate"  value="${item.bidStartDate}"/><a
					href="javascript:show_calendar('document.changeStatus.startdate', document.changeStatus.startdate.value);">
						<img src="<%=request.getContextPath() + "/images/cal.gif"%>"
						alt="cal" width="18" height="18" border="0" />
				</a></font></td>
						
						<td align="center" width="15%"><input type="text" name="enddate" readonly="readonly" value="${item.bidEndDate}"/><a href="javascript:show_calendar('document.changeStatus.enddate', document.changeStatus.enddate.value);">
						<img src="<%=request.getContextPath() + "/images/cal.gif"%>"
						alt="cal" width="18" height="18" border="0" /></a></td>
						
						<td align="center" width="15%"><font color="DarkViolet " size="2" face="verdana"><input type="text" name="status" id ="status" align="top" value="${item.status}" onfocus="return statusAlert()"/></font></td>
						
						<td> <input type="submit" value="Submit"/></td>
						<%-- <td border="" align="center" rowspan="" colspan=''><img
						alt="See Photo Here" id="previewField" src="${item.bigImagePath}"
						height="80" width="80" /></td> --%>
					<%-- <td align="center"><a
						href="./DeleteCropAction?cropid=${crop.cropid}">Delete</a></td> --%>
				</tr>
				</form>
			</c:forEach>
		</c:if>

	</table>
	</div>
</body>
</html>
