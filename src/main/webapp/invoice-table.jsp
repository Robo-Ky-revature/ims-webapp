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
	
		<table id="invoices" class="table">
			<tr id="header">
				<th></th>
				<th>Invoice #</th>
				<th>Date</th>
				<th>Client</th>
				<th>Incoming/Outgoing</th>
				<th>Total</th>
			</tr>
			<tr>
				<td><span class="glyphicon glyphicon-plus"/></td>
				<td>1</td>
				<td>08-12-2016</td>
				<td>Wizards of the Coast</td>
				<td>Incoming</td>
				<td>$300.00</td>
			</tr>
		</table>
	</body>
</html>