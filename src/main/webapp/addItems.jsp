<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/gen_validatorv31.js">
</script>
 <script type="text/javascript" src="js/image.js"> </script>
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
	function disableCategory() {
		var val = document.getElementById("Category").value;
		/* window.alert(val); */
		if (val == "" || val =='undefined' || val == null ) {
			$("#newCategory2").css("display", "block");
			$("#label").css("display", "block");
		} else {
			$("#newCategory2").css("display", "none");
			$("#label").css("display", "none");
		}

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
	<form action="<%=request.getContextPath()%>/AddItemsAction"
		name="addItems" method="post" onSubmit="return validate()" enctype="multipart/form-data">
		<div align="center">
		<table align="center">
			<tr align="center">
				<th align="center"><h2>
						<font color="blue">Add Items </font>
					</h2></th>
			</tr>
			<tr>
			   <c:choose>
				<c:when test="${not empty category}">
					<td> <font color="blue"> Category : </font></td>
					<td><select name="Category" id="Category" onchange="return disableCategory()">
					        <option selected="selected" value=""> Select Available Category</option>
							<c:forEach var="cat" items="${category}">
								<option value="${cat.categoryId}">
									<c:out value="${cat.categoryName}" />
								</option>
							</c:forEach>
					</select>
					</td>
				</c:when>
				<c:otherwise><td> <font color="blue"> Add new Category : </font></td>
				<td><input id="newCategory2" type="text" name="newCategory2" value="" /></td></c:otherwise>
				</c:choose>
				<td></td>
				<c:if test="${not empty category}">
				<td> <div id="label"><font color="blue">Add new Category :</font></div></td>
				<td><input id="newCategory2" type="text" name="newCategory2" value=""  /></td>
				</c:if>
			</tr>
			<tr>
				<td><font color="blue">Item Name :</font></td>
				<td><input type="text" name="itemname" value="" /></td>
			</tr>

			<tr>
				<td><font color="blue">Summary :</font></td>
				<td><input type="text" name="summary" value="" /></td>
			</tr>

			<tr>
				<td><font color="blue"> Description :</font></td>
				<td><textarea name="description" ></textarea></td>
			</tr>
			<tr>
				<td><font color="blue">Upload small size Image :</font></td>
				<td><input type="file" name="smallImage" class="textfield"
					onchange="readURL(this);" /></td><td></td><td></td><td><img align="right" height="10"
					id="blah" src="#" alt="your image" /></td>
			</tr>
			<tr>
				<td><font color="blue">Upload big size Image :</font></td>
				<td><input type="file" name="bigImage" class="textfield"
					onchange="readURLTwo(this);" /></td> <td></td><td></td>
					<td><img align="right" height="10"
					id="blah1" src="#" alt="your image" /></td>
			</tr>

			<tr>
				<td><font color="blue">Item Price :</font></td>
				<td><input type="text" name="itemprice" value="" /></td>
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
		var frmvalidator = new Validator("addItems");
		var val = document.getElementById("newCategory2").value;
		/* if(val==null || val =='undefined' || val == ""){
		frmvalidator.addValidation("Category", "dontselect=0");
		}else{
			frmvalidator.addValidation("newCategory2", "req",
			"Please enter your Enter Category Name ");
		} */
		frmvalidator.addValidation("itemname", "req",
				"Please enter your Itemname ");
		frmvalidator.addValidation("summary", "req",
				"Please enter your Summary");
		frmvalidator.addValidation("Description", "req",
		"Please enter Item Desription");
		frmvalidator.addValidation("smallImage", "req",
		"Please enter item small Image");
		frmvalidator.addValidation("bigImage", "req",
		"Please enter item big image");
		frmvalidator.addValidation("itemprice", "req",
		"Please enter item Price");
		
	</script>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>