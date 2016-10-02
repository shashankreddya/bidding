<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="js/gen_validatorv31.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ts_picker.js">
<script type="text/javascript" src="js/image.js">
	
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Items</title>
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
		</b> </font>

	</center>
	<div align="center">
		<form action="<%=request.getContextPath()%>/AuctionDatesAction" name="auction">
		<table align="center">
			<tr align="center">
				<th><h2>
						<font color="blue">Set Auction Dates for Items </font>
					</h2></th>
			</tr>
			<tr>
				<td>Item Name :</td>
				<td><input type="text" name="itemName" value="<%=request.getParameter("itemName")%>" /></td>
				<td><input type="hidden" name="itemId" value="<%=request.getParameter("itemId")%>" /></td>
			</tr>
			<tr>
				<td>Auction Start Date :</td>
				<td><input type="text" name="startdate" size="20" value=""
					readonly="readonly" /><a
					href="javascript:show_calendar('document.auction.startdate', document.auction.startdate.value);">
						<img src="<%=request.getContextPath() + "/images/cal.gif"%>"
						alt="cal" width="18" height="18" border="0" />
				</a></td>
			</tr>
			
			<tr>
				<td> Auction End Date :</td>
				<td><input type="text" name="enddate" size="20" value=""
					readonly="readonly" /><a
					href="javascript:show_calendar('document.auction.enddate', document.auction.enddate.value);">
						<img src="<%=request.getContextPath() + "/images/cal.gif"%>"
						alt="cal" width="18" height="18" border="0" />
				</a></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
			<tr>
		</table>
	</form>
	</div>
	<script language="JavaScript" type="text/javascript">
		//You should create the validator only after the definition of the HTML form
		var frmvalidator = new Validator("auction");
		frmvalidator.addValidation("itemName", "req",
				"Please enter your Username");
		frmvalidator.addValidation("password", "req",
				"Please enter your Password");
	</script>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>

</html>