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

    <title>Invoices | Inventory Management</title>

    <!-- Bootstrap core CSS -->
    <link href="css/custom.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
  </head>

  <body>
	
	<jsp:include page="navbar.jsp"></jsp:include>
	<script>
		$("#navinv").addClass("active");
	</script>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h1 style="font-size: 32px;">All Invoices</h1>
					<button id="newform" class="btn btn-default btn-lg" role="button" data-toggle="modal" data-target="#newModal">Create Invoice</button>
					<table id="invoices" class="table">
						<tr class="header">
							<th>Invoice #</th>
							<th>Date</th>
							<th>Client</th>
							<th>Incoming/Outgoing</th>
							<th>Total</th>
							<th></th>
						</tr>
						<tr>
							<td>1</td>
							<td>08-12-2016</td>
							<td>Wizards of the Coast</td>
							<td>Incoming</td>
							<td>$300.00</td>
							<td align="center"><button id="lineview" class="btn btn-default btn-md" role="button" data-toggle="modal" data-target="#exModal">Expand</button></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="newModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">New Client</h4>
	      </div>
	      <div class="modal-body">
	      	<form id="newForm" class="form-horizontal" action = "insertClient.do" method="post">
	      		<h4>Client</h4>
	    		<div class="form-group" style="padding-bottom: 15px;">
	    			<div id="selclient"></div>
	    		</div>
	      		<div class="form-group" style="padding-bottom: 15px;">
	      			<label class="radio-inline"><input type="radio" name="type" value="1" checked>Retailer</label>
    				<label class="radio-inline"><input type="radio" name="type" value="2">Supplier</label>
	      		</div>
	      		<h4>Product</h4>
	    		<div class="form-group" style="padding-bottom: 15px;">
	    			<div id="selproduct"></div>
	    		</div>
	    		
	    	</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" form="newForm" class="btn btn-default">Submit</button>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="modal fade" id="exModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="invoiceLabel">Invoice #</h4>
		      </div>
		      <div class="modal-body">
		      	<table id="expandedInv" class="table">
		      		<tr class="header">
		      			<th>Product UPC</th>
		      			<th>Name</th>
		      			<th>Category</th>
		      			<th>Unit Price</th>
		      			<th>Quantity</th>
		      			<th>Total</th>
		      		</tr>
		      		<tr>
		      			<td>69</td>
		      			<td>Settlers of Catan</td>
		      			<td>Board</td>
		      			<td>20</td>
		      			<td>15</td>
		      			<td>300.00</td>
		      		</tr>
		      	</table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
	</body>
	<script src="js/invoiceview.js"></script>
</html>