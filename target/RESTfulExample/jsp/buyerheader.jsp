<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller header</title>
<meta charset="utf-8">
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800"
	rel="stylesheet" type="text/css">
<script src="js/jquery-1.8.3.min.js"></script>
<script
	src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
<script src="js/jquery.dropotron-1.2.js"></script>
<script src="js/init.js"></script>
<noscript>
	<link rel="stylesheet" href="css/5grid/core.css">
	<link rel="stylesheet" href="css/5grid/core-desktop.css">
	<link rel="stylesheet" href="css/5grid/core-1200px.css">
	<link rel="stylesheet" href="css/5grid/core-noscript.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/style-desktop.css">
</noscript>
<!--[if lte IE 9]>
<link rel="stylesheet" href="css/ie9.css">
<![endif]-->
<!--[if lte IE 8]>
<link rel="stylesheet" href="css/ie8.css">
<![endif]-->
<!--[if lte IE 7]>
<link rel="stylesheet" href="css/ie7.css">
<![endif]-->
</head>
<body class="homepage">
	<header id="header">
	<div class="inner">
		<h1>
			<a href="#" class="mobileUI-site-name"> Sankar Auction</a>
		</h1>
		<nav id="nav" class="mobileUI-site-nav">
		<ul>
			<li class="current_page_item"><a href="buyerhomepage.jsp">Home</a></li>
			<li><a href="buyerhomepage.jsp" class="arrow">BidItems</a>
				<ul>
					<li><a href="<%=request.getContextPath()%>/ViewCurrentBidItems?task=cat&check=current">Current Bid Items</a></li>
					<li><a href="<%=request.getContextPath()%>/ViewCurrentBidItems?task=cat&check=starttoday">Today Bid Items</a></li>
					<li><a href="<%=request.getContextPath()%>/ViewCurrentBidItems?task=cat&check=endtoday">End Items Today</a></li>
				</ul></li>
			<li><a href="<%=request.getContextPath()%>/ViewWinners">View Winners</a></li>
			<li><a href="<%=request.getContextPath()%>/ViewTransactions">View Transactions</a></li>
			<li><a href="buyerhomepage.jsp" class="arrow">Messages</a>
				<ul>
					<li><a href="<%=request.getContextPath()%>/ViewFeedBackAction">View Messages</a></li>
					<li><a href="<%=request.getContextPath()%>/feedback.jsp">FeedBack</a></li>
				</ul></li>
			<li> <a href="adminhomepage.jsp" class="arrow"><img height="40" width="40" alt="Profile" src="<%=request.getContextPath()%>/images/logout1.jpg"></a>
                  <ul>
                      <li><a href="<%=request.getContextPath()%>/changepassword.jsp">ChangePassword</a></li>
                     <li><a href="<%=request.getContextPath()%>/ViewProfileAction">View Profile</a></li>
                     <li><a href="<%=request.getContextPath()%>/LogoutAction">LogOut</a></li>
                  </ul>
                </li>
		</ul>
		</nav>
	</div>
	</header>
</body>
</html>