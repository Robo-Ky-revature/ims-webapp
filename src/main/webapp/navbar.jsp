<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="Carter Smith">
    <link rel="icon" href="../../favicon.ico">

    <title>Navbar | Inventory Management</title>

    <!-- Bootstrap core CSS -->
    <link href="css/custom.css" rel="stylesheet">
</head>
<body>
	<!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <a id="navbrand" class="navbar-brand" href="goHome.do">  Inventory Management  </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul id="navs" class="nav navbar-nav">
            <li id="navinv" type="submit"><a href="goInvoices.do">  Invoices  </a></li>
            <li id="navcli" type="submit"><a href="goClients.do">  Clients  </a></li>
            <li id="navpro" type="submit"><a href="goProducts.do">  Products  </a></li>
            <li id="navrep" type="submit"><a href="goHome.do">  Reports  </a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
</body>
</html>