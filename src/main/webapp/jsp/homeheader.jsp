<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Auction</title>
<meta charset="utf-8">
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800" rel="stylesheet" type="text/css">
<script src="js/jquery-1.8.3.min.js"></script>
<script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
<script src="js/jquery.dropotron-1.2.js"></script>
<script src="js/init.js"></script>
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
            <h1><a href="#" class="mobileUI-site-name"> Sankar Auction</a></h1>
            <nav id="nav" class="mobileUI-site-nav">
              <ul>
                <li class="current_page_item"><a href="home.jsp">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/ViewCurrentBidItems?task=cat&check=current">CurrentBidItems</a></li>
                <li><a href="<%=request.getContextPath()%>/ViewCurrentBidItems?task=cat&check=starttoday">NewBidItemsToday</a></li>
                <li><a href="<%=request.getContextPath()%>/ViewCurrentBidItems?task=cat&check=endtoday">EndItemsToday</a></li>
                <li><a href="loginpage.jsp">Login</a></li>
              </ul>
            </nav>
          </div>
        </header>
     
</body>
</html>