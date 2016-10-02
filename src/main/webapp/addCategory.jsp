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
	<form
		action="<%=request.getContextPath()%>/AddCategoryAction?task=categories"
		name="addCategory" method="post" onSubmit="return validate()">
		<div align="center">
		<table align="center">
			<tr align="center">
				<th align="center"><h2>
						<div>
							<font color="blue">Add Category </font>
						</div>
					</h2></th>
			</tr>
			<!-- <tr>
			  
				<td></td>
				<td> <div id="label"><font color="blue">Add new Category :</font></div></td>
				<td><input id="category" type="text" name="category" value=""  /></td>
			</tr> -->
			
			<tr>
				<c:if test="${not empty category}">
					<td><font color="blue"> Check Available Category list:
					</font></td>
					<td><select name="CategoryList" id="CategoryList">
							<option selected="selected" value="Available Categories">Available
								Categories</option>
							<c:forEach var="cat" items="${category}">
								<option value="${cat.categoryId}">
									<c:out value="${cat.categoryName}" />
								</option>
							</c:forEach>
					</select></td>
				</c:if>
			</tr>
			
			<tr>
				<td><font color="blue">Add new Category :</font></td>
				<td><input type="text" name="category" value="" /></td>
				<td></td>
				<td></td>
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
		</div>
	</form>
	<script language="JavaScript" type="text/javascript">
		//You should create the validator only after the definition of the HTML form
		var frmvalidator = new Validator("addCategory");

		frmvalidator.addValidation("category", "req",
				"Please enter Category Name ");
	</script>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>