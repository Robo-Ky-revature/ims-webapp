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

    <title>Home | Inventory Management</title>

    <!-- Bootstrap core CSS -->
    <link href="css/custom.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	
  </head>

  <body>
	
	<jsp:include page="navbar.jsp"></jsp:include>
	<script>
		$(".navbar-brand").addClass("active");
	</script>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<div class="jumbotron">
					<h1>Inventory Warnings</h1>
					<div id="warning" name="warning"></div>
				</div>
				<a class="btn btn-default btn-lg" href="#" role="button">Order More</a>
			</div>
			<div class="col-md-9">
				<div class="jumbotron">
					<h1>Last 5 Invoices</h1>
					<table id="invoice5" class="table">
						<tr id="header">
							<th><span class="glyphicon glyphicon-plus"/></th>
							<th>Invoice #</th>
							<th>Date</th>
							<th>Client</th>
							<th>Incoming/Outgoing</th>
							<th>Total</th>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	</body>
	<script src="js/reports.js"></script>
</html>