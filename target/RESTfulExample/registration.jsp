<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript" src="js/gen_validatorv31.js"></script>
<script language="JavaScript" type="text/javascript"
	src="js/ts_picker.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#blah').attr('src', e.target.result).width(150).height(200);
			};

			reader.readAsDataURL(input.files[0]);
		}
	}

	function checkUserId() {
		var userid = document.getElementById("san").value;
		var obj = null;
		try {
			if (window.XMLHttpRequest) {
				obj = new XMLHttpRequest();
			} else {
				obj = new ActiveXObject("Microsoft.XMLHTTP");
			}
		} catch (e) {
			alert("xmlHttp object creation failed");
		}
		obj.onreadystatechange = function() {
			if (obj.readyState == 4) {
				document.getElementById("avail").innerHTML = obj.responseText;
				obj.responseText = null;
				obj.abort();
			}
		}
		var url = "<%=request.getContextPath()%>/CheckUserIDAvailbility?userid="+ userid;
		obj.open("GET", url, true);
		obj.send(null);

	}
	function enablePmtOption() {
		var pmtmode = document.getElementById("pmtmode").value;
		if (pmtmode == "card") {
			$("#accountno").css("display", "none");
			$("#cardno").css("display", "block");
			$("#month").css("display", "block");
			$("#year").css("display", "block");
			$("#securityCode").css("display","block");
			$("#cardDiv").css("display","block");
			$("#accDiv").css("display","none");
		} else if (pmtmode == "account") {
			$("#accountno").css("display", "block");
			$("#cardno").css("display", "none");
			$("#month").css("display", "none");
			$("#year").css("display", "none");
			$("#securityCode").css("display","none");
			$("#cardDiv").css("display","none");
			$("#accDiv").css("display","block");
		}

	}
</script>
</head>
<body>
	<jsp:include page="/jsp/homeheader.jsp"></jsp:include>
	<br>
	<center>
		<h2><font color="DarkViolet" size="3" face="verdana">Registration Page</font></h2>
	</center>
	<form action="<%=request.getContextPath()%>/RegistrationAction"
		name="registration">
		<div align="center">
		<table border="10" align="center">
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">UserName :</font></td>
				<td><input type="text" name="userName" id="san"
					onchange="checkUserId();"
					value="<%if (request.getParameter("userName") != null)
				out.print(request.getParameter("userName"));%>" /></td>
				<td>
					<center>
						<font color="green"> <c:if
								test="${'requestScope.status1'!='null'}">

								<c:out value="${param.status1}"></c:out>
							</c:if>
						</font>
					</center>
				</td>
				<!-- <td></td><td><img align="top" height="10"
					id="blah" src="#" alt="your image" /></td> -->
			</tr>
			<div id="avail"></div>
			<font color="red">  
			<c:if test="${'requestScope.status'!='null'}">
								<c:out value="${param.status}"></c:out>
							</c:if>
			 ${requestScope.status } </font>

			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Password :</font></td>
				<td><input type="password" name="password" /></td>

			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Security Question :</font></td>
				<td><select name="question">
						<option value="select" selected="true">--Select--</option>
						<option value="Your Childhood friend">Your Childhood
							friend</option>
						<option value="Your first teacher">Your first teacher</option>
						<option value="Your pet name">Your pet name</option>
						<option value="Your first bank account">Your first bank
							account</option>
				</select></td>
			</tr>

			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Answer :</font></td>
				<td><input type="text" name="answer" /></td>
			</tr>

			<!-- <tr>
				<td>Browse Your Photo :</td>
				<td><input type='file' onchange="readURL(this);" /> </td>
			</tr> -->
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Role :</font></td>
				<td><select name="role">
						<option value="select" selected="true">--Select--</option>
						<option value="Seller">Seller</option>
						<option value="Buyer">Buyer</option>
				</select></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">FirstName :</font></td>
				<td><input type="text" name="fname" /></td>
				<td><font color="DarkViolet" size="3" face="verdana">LastName :</font></td>
				<td><input type="text" name="lname" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">DOB :</font></td>
				<td><input type="text" name="dob" size="20" value=""
					readonly="readonly" /><a
					href="javascript:show_calendar('document.registration.dob', document.registration.dob.value);">
						<img src="<%=request.getContextPath() + "/images/cal.gif"%>"
						alt="cal" width="18" height="18" border="0" />
				</a></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Gender :</font></td>
				<td><select name="gender">
						<option value="select" selected="true">--Select--</option>
						<option value="Male">Male</option>
						<option value="Female">Female</option>
				</select></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">House No :</font></td>
				<td><input type="text" name="hno" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Location :</font></td>
				<td><input type="text" name="location" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">City :</font></td>
				<td><input type="text" name="city" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">State :</font></td>
				<td><input type="text" name="state" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Country :</font></td>
				<td><input type="text" name="country" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Pin :</font></td>
				<td><input type="text" name="pin" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Email :</font></td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Phone :</font></td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td><font color="DarkViolet" size="3" face="verdana">Bank Name :</font></td>
				<td><input type="text" name="bankName" /><td><input type="hidden" name="pmtmode" id="pmtmode"  value="card"/></td></td>
				
			</tr>
			<!-- <tr>
				<td>Payment Mode :</td>
				<td><select name="pmtmode" id="pmtmode"
					onchange="return enablePmtOption()">
						<option value="select" selected="true">--Select--</option>
						<option value="card">Card</option>
						<option value="account">Account</option>
				</select></td>
			</tr> -->

			<!-- <tr>
				<td><div id="accDiv">Bank Account Number :</div></td>
				<td><input type="text" name="accountno" id="accountno" /></td>
			</tr> -->
			<tr>
				<td><div id="cardDiv"><font color="DarkViolet" size="3" face="verdana"> Card Number :</font></div></td>
				<td><input type="text" name="cardno" id="cardno" /></td>
			</tr>
			<tr>
				<td><div id="cardDiv"><font color="DarkViolet" size="3" face="verdana">Security Code (CVV) :</font></div></td>
				<td><input type="text" name="securityCode" id="securityCode" /></td>
			</tr>

			<tr>
				<td><div id="cardDiv"><font color="DarkViolet" size="3" face="verdana">Expiry Month and Year</font></div></td>
				<td><select name="month" id="month">
						<option value="" selected="true">--Select--
						<option value="01">January</option>
						<option value="02">February</option>
						<option value="03">March</option>
						<option value="04">April</option>
						<option value="05">May</option>
						<option value="06">June</option>
						<option value="07">July</option>
						<option value="08">August</option>
						<option value="09">September</option>
						<option value="10">October</option>
						<option value="11">November</option>
						<option value="12">December</option>
				</select> ---- <select name="year" id="year">
						<option value="" selected>--Select--
						<option value="2015">2015</option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
						<option value="2020">2020</option>
						<option value="2021">2021</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
						<option value="2026">2026</option>
						<option value="2027">2027</option>
						<option value="2028">2028</option>
						<option value="2029">2029</option>
						<option value="2030">2030</option>
				</select></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		</div>
	</form>
	<script language="JavaScript" type="text/javascript">
		//You should create the validator only after the definition of the HTML form
		var frmvalidator = new Validator("registration");
		frmvalidator.addValidation("userName", "req",
				"Please enter your Username");
		frmvalidator.addValidation("password", "req",
				"Please enter your Password");
		frmvalidator.addValidation("role", "dontselect=0");
		frmvalidator.addValidation("fname", "req",
				"Please enter your  FirstName");
		frmvalidator.addValidation("lname", "req",
				"Please enter your  LastName");
		frmvalidator.addValidation("dob", "req", "Please enter your DOB");
		frmvalidator.addValidation("gender", "dontselect=0");

		frmvalidator.addValidation("hno", "req", "Please enter your house no");

		frmvalidator.addValidation("email", "maxlen=50");
		frmvalidator.addValidation("email", "req");
		frmvalidator.addValidation("email", "email");

		frmvalidator.addValidation("phone", "req");
		frmvalidator.addValidation("phone", "maxlen=10");
		frmvalidator.addValidation("phone", "numeric");
		frmvalidator.addValidation("phone", "Phone");
		frmvalidator.addValidation("bankName", "req",
				"Please enter your  BankName");
		
		frmvalidator.addValidation("account", "req",
				"Please enter your  Bank Account Number OR Credit card Number");
		frmvalidator.addValidation("account", "maxlen=12");
		frmvalidator.addValidation("account", "numeric");
		frmvalidator.addValidation("Month", "dontselect=0");
		frmvalidator.addValidation("Year", "dontselect=0");
	</script>



	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />

	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>