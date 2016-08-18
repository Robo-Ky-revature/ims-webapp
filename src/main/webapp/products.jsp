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

    <title>Products | Inventory Management</title>

    <!-- Bootstrap core CSS -->
    <link href="css/custom.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
  </head>

  <body>
	
	<jsp:include page="navbar.jsp"></jsp:include>
	<script>
		$("#navpro").addClass("active");
	</script>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h1 style="font-size: 32px;">Product List</h1>
					<button id="newform" class="btn btn-default btn-lg" role="button" data-toggle="modal" data-target="#newModal">New Product</button>
					<table id="products" class="table">
						<tr id="header">
							<th>UPC</th>
							<th>Name</th>
							<th>Weight</th>
							<th>Size</th>
							<th>Unit Cost</th>
							<th>Description</th>
							<th>Category</th>
							<th>Actions</th>
						</tr>
						<tr id="thisrow">
							<td class="tdupc">1</td>
							<td class="tdname">Magic Core Set 2016</td>
							<td class="tdwei">0.2</td>
							<td class="tdsize">15x6x2</td>
							<td class="tdcost">20.0</td>
							<td class="tddesc">MTG</td>
							<td class="tdcat">CCG</td>
							<td class="tdacts">
								<button type="button" class="btn btn-default btn-sm update">Update</button>
								<button type="button" class="btn btn-default btn-sm delete">Delete</button>
							</td>
						</tr>
						<tr id="thatrow">
							<td class="tdupc">2</td>
							<td class="tdname">Settlers of Catan</td>
							<td class="tdwei">2.0</td>
							<td class="tdsize">15x15x4</td>
							<td class="tdcost">30.0</td>
							<td class="tddesc">settlers</td>
							<td class="tdcat">Board</td>
							<td class="tdacts">
								<button type="button" class="btn btn-default btn-sm update">Update</button>
								<button type="button" class="btn btn-default btn-sm delete">Delete</button>
							</td>
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
	        <h4 class="modal-title" id="myModalLabel">New Product</h4>
	      </div>
	      <div class="modal-body">
	      	<form id="newForm" action = "#" method="post">
	      		<h4>Name</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="newname" type="text" name="name" class="form-control" />
	    		</div>
	    		<h4>Weight</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="newwei" type="text" name="weight" class="form-control" />
	    		</div>
	    		<h4>Size</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="newsize" type="text" name="size" class="form-control" />
	    		</div>
	    		<h4>Cost</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="newcost" type="text" name="cost" class="form-control" />
	    		</div>
		    	<h4>Description</h4>
		    	<div class="input-group" style="padding-bottom: 15px;">
		  			<textarea id="newdesc" class="form-control" name="desc" rows="4"></textarea>
		    	</div>
		    	<h4>Category</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="newcat" type="text" name="cat" class="form-control" />
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
	<div class="modal fade" id="upModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Update Product</h4>
	      </div>
	      <div class="modal-body">
	      	<form id="updateForm" action = "#" method="post">
	      		<h4>Name</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="upname" type="text" name="name" class="form-control" />
	    		</div>
	    		<h4>Weight</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="upwei" type="text" name="weight" class="form-control" />
	    		</div>
	    		<h4>Size</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="upsize" type="text" name="size" class="form-control" />
	    		</div>
	    		<h4>Cost</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="upcost" type="text" name="cost" class="form-control" />
	    		</div>
		    	<h4>Description</h4>
		    	<div class="input-group" style="padding-bottom: 15px;">
		  			<textarea id="updesc" class="form-control" name="desc" rows="4"></textarea>
		    	</div>
		    	<h4>Category</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="upcat" type="text" name="cat" class="form-control" />
	    		</div>
	    	</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" form="updateForm" class="btn btn-default">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">WARNING</h4>
	      </div>
	      <div class="modal-body">
	      	This will delete this product from the database. Are you sure?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	        <button type="submit" form="deleteForm" class="btn btn-default" data-dismiss="modal">Confirm</button>
	      </div>
	    </div>
	  </div>
	</div>
	</body>
	<script src="js/productview.js"></script>
</html>