<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.auction.daomgr.ItemsDaoManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/gen_validatorv31.js">
	
</script>
<script type="text/javascript">
 
	function validateBidAmount() {
		var max = document.getElementById("bidmaxprice").value;
		var bidprice = document.getElementById("bidprice").value;
		if (bidprice <= max) {
			alert("Please enter more than max bid amount.");
			document.getElementById("bidprice").focus();
			return false;
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bidding Items</title>
</head>
<body>
	<jsp:include page="/jsp/buyerheader.jsp"></jsp:include>
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
	<form action="<%=request.getContextPath()%>/AddBidItemsAction"
		name="addItems" method="post">
		<table align="center">
			<tr align="center">
				<th align="center"><h2>
						<font color="blue">Enter Bid Amount </font>
					</h2></th>
			</tr>
			<tr>
				<td><font color="blue">Item Name :</font></td>
				<td><font color="red"> <%=request.getParameter("itemname")%></font>
					<input type="hidden" name="itemid"
					value="<%=request.getParameter("itemid")%>" /></td>
					<td> &nbsp;</td> <td><%
					String itemid = request.getParameter("itemid");
					double maxAmount = new ItemsDaoManager().getMaxBidAmount(Integer.parseInt(itemid));
					%> 
					<marquee><font color="yellow">Max Bidding amount till now $: <%=maxAmount %></font></marquee>
					</td>
			</tr>

			<tr>
				<td><font color="blue">Bidding Price :</font></td>
				<td><input type="text" id="bidprice" name="bidprice" value="" />
				<input type="hidden" id="bidmaxprice" name="bidmaxprice" value="<%=maxAmount %>" />
				</td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Submit" onclick="return validateBidAmount()"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<tr>
		
		</table>
	</form>
	<script language="JavaScript" type="text/javascript">
		//You should create the validator only after the definition of the HTML form
		var frmvalidator = new Validator("addItems");
		frmvalidator.addValidation("bidprice", "req", "Please enter Bid Price");
		frmvalidator.addValidation("bidprice", "maxlen=12");
		frmvalidator.addValidation("bidprice", "numeric");
	</script>
				<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>

			</body>
</html>