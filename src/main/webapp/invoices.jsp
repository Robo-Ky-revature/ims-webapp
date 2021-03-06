<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.js"></script>
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
					<button id="newbutton" class="btn btn-default btn-lg" role="button" data-toggle="modal" data-target="#newModal">Create Invoice</button>
					<table id="invoices" class="table">
						<tr class="header">
							<th>Invoice #</th>
							<th>Date</th>
							<th>Client</th>
							<th>Type</th>
							<th>Subtotal</th>
							<th>Tax</th>
							<th>Total</th>
							<th></th>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="newModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Create Invoice</h4>
	      </div>
	      <div class="modal-body">
	      	<form id="newForm" action = "insertInvoice.do" method="post">
	      		<h4>Client</h4>
	    		<div class="form-group" style="padding-bottom: 15px;">
	    			<div id="selclient"></div>
	    		</div>
	      		<div id="clitype" class="form-group" style="padding-bottom: 15px;">
	      			<input type="hidden" name="clientType" value="0"/>
	      			<div id="ctypetext">---</div>
	      		</div>
	      		<button id="addproduct" type="button" class="btn btn-default btn-md" role="button">Add Product</button>
	      		<table id="productlines" class="table">
	      			<tr>
	      				<th>Product</th>
	      				<th>Unit Cost</th>
	      				<th>On Hand</th>
	      				<th>Quantity</th>
	      				<th>Subtotal</th>
	      			</tr>
	      		</table>
	      		
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
		      	<table id="expandedinv" class="table">
		      		<tr class="header">
		      			<th>Product UPC</th>
		      			<th>Name</th>
		      			<th>Category</th>
		      			<th>Unit Price</th>
		      			<th>Quantity</th>
		      			<th>Total</th>
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