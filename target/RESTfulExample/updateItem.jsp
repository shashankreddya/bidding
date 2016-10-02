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
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result).width(80).height(120);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
	function readURLTwo(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#blah1').attr('src', e.target.result).width(100).height(150);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Items</title>
</head>
<body>
	<jsp:include page="/jsp/adminheader.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<br>
	<center>
	 <font color="red" size="6" face="verdana">Update Item</font> <br>
		<br>
		<br>
		<br>
		<font color="red"><b> <c:if
					test="${requestScope.status!='null'}">

					<c:out value="${requestScope.status}"></c:out>
				</c:if>
		</b> 
		</font>
	</center>
	<%
		Items items = (Items) session.getAttribute("singleItem");
	%>

	<form action="<%=request.getContextPath()%>/UpdateItemsAction"
		name="updateItems" method="post" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td><font color="blue">Item Name :</font></td>
				<td><input type="text" name="itemname"
					value="<%=items.getItemName()%>" />
					
					<input type="hidden" name="itemid" value="<%=items.getItemId()%>"/>
					</td>
			</tr>

			<tr>
				<td><font color="blue">Summary :</font></td>
				<td><input type="text" name="summary"
					value="<%=items.getSummary()%>" /></td>
			</tr>

			<tr>
				<td><font color="blue"> Description :</font></td>
				<td><textarea name="description"><%=items.getDescription()%> </textarea></td>
			</tr>
			<%-- <tr>
				<td><font color="blue">Upload small size Image :</font></td>
				<td colspan="2"><input type="file" name="smallImage" class="textfield"
					onchange="readURL(this);" /></td>
					<td></td>
				<td></td>
				<td>
					<img align="right" height="10"
					id="blah" src="#" alt="Update Photo" /></td><td colspan="2"> </td><td><img alt="See Photo Here"
					id="previewField" src="<%=items.getSmallImagePath()%>" align="right" /></td>

				<td align="center" width="70%" ><a href="<%=request.getContextPath()%>/ViewItemsAction?singleItem=${item.itemId}"><font color="DarkViolet " size="" face="verdana">${item.itemName}</font> <img
						alt="See Photo Here" id="previewField" src="${item.smallImagePath}"
						height="200" width="120" align="middle"/></a></td>
			</tr>
			<tr>
				<td><font color="blue">Upload big size Image :</font></td>
				<td><input type="file" name="bigImage" class="textfield"
					onchange="readURLTwo(this);" /></td>
				<td></td>
				<td></td>
				<td><img align="right" height="10"
					id="blah1" src="#" alt="Update Photo" /></td><td colspan="2"> </td><td><img alt="See Photo Here"
					id="previewField" src="<%=items.getBigImagePath()%>" align="right" /></td>
			</tr>
 --%>
			<tr>
				<td><font color="blue">Item Price :</font></td>
				<td><input type="text" name="itemprice" value="<%=items.getPrice() %>" /></td>
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

</body>
</html>