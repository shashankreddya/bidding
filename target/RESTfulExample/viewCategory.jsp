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
	<div align="center">
		<table align="center" border="1" bgcolor="white" width="70%"
			height="70%">

			<tr bgcolor="skyblue">
				<th>CategoryNo</th>
				<th>CategoryName</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>


			<c:if test="${not empty category}">
				<c:forEach var="u" items="${category}">
					<tr>
						<td align="center">${u.categoryId}</td>
						<td align="center">${u.categoryName}</td>
						<td align="center"><a
							href="<%=request.getContextPath()%>/updateCategory.jsp?categoryId=${u.categoryId}&category=${u.categoryName}">Update</a></td>
						<td align="center"><a
							href="<%=request.getContextPath()%>/AddCategoryAction?categoryId=${u.categoryId}&category=${u.categoryName}&task=delete">Delete</a></td>
					</tr>

				</c:forEach>
			</c:if>

		</table>
	</div>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>