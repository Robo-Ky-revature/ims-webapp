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

    <title>Clients | Inventory Management</title>

    <!-- Bootstrap core CSS -->
    <link href="css/custom.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
  </head>

  <body>
	
	<jsp:include page="navbar.jsp"></jsp:include>
	<script>
		$("#navcli").addClass("active");
	</script>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h1 style="font-size: 32px;">Client List</h1>
					<button id="newform" class="btn btn-default btn-lg" role="button" data-toggle="modal" data-target="#newModal">New Client</button>
					<table id="clients" class="table">
						<tr id="header">
							<th>ID</th>
							<th>Name</th>
							<th>Email</th>
							<th>Type</th>
							<th>Address</th>
							<th>Phone</th>
							<th>Fax</th>
							<th>Actions</th>
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
	      	<form id="newForm" action = "insertClient.do" method="post">
	      		<h4>Name</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="newname" type="text" name="name" class="form-control" />
	    		</div>
	    		<h4>Email</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="newemail" type="text" name="email" class="form-control" />
	    		</div>
	    		<h4>Type</h4>
	    		<div class="input-group" style="padding-bottom: 30px;">
	    			<label class="radio-inline"><input type="radio" name="type" value="1">Retailer</label>
	    			<label class="radio-inline"><input type="radio" name="type" value="2">Supplier</label>
	    		</div>
		    	<h4>Address</h4>
		    	<div class="input-group" style="padding-bottom: 15px;">
		  			<input id="newaddline1" type="text" name="streetAddress1" class="form-control" placeholder="Street address line 1"/>
		  			<input id="newaddline2" type="text" name="streetAddress2" class="form-control" placeholder="Street address line 2"/>
		  			<input id="newaddcity" type="text" name="city" class="form-control" placeholder="City" />
		  			<div id="newaddstate">State </div>
		  			<input id="newaddzip" type="text" name="zip" class="form-control" placeholder="Zip" />
		    	</div>
		    	<h4>Phone</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="newphone" type="text" name="phone" class="form-control" />
	    		</div>
	    		<h4>Fax</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="newfax" type="text" name="fax" class="form-control" />
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
	        <h4 class="modal-title" id="myModalLabel">Update Client</h4>
	      </div>
	      <div class="modal-body">
	      	<form id="updateForm" action = "updateClient.do" method="post">
	      		<input id="upid" type="hidden" name="clientId">
	      		<input id="uptype" type="hidden" name="type">
	      		<h4>Name</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="upname" type="text" name="name" class="form-control" />
	    		</div>
	    		<h4>Email</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="upemail" type="text" name="email" class="form-control" />
	    		</div>
		    	<h4>Address</h4>
		    	<div class="input-group" style="padding-bottom: 15px;">
		    		<input id="upaid" type="hidden" name="addressId" />
		  			<input id="upaddline1" type="text" name="streetAddress1" class="form-control" placeholder="Street address line 1"/>
		  			<input id="upaddline2" type="text" name="streetAddress2" class="form-control" placeholder="Street address line 2"/>
		  			<input id="upaddcity" type="text" name="city" class="form-control" placeholder="City" />
		  			<div id="upaddstate">State </div>
		  			<input id="upaddzip" type="text" name="zip" class="form-control" placeholder="Zip" />
		    	</div>
		    	<h4>Phone</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="upphone" type="text" name="phone" class="form-control" />
	    		</div>
	    		<h4>Fax</h4>
	    		<div class="input-group" style="padding-bottom: 15px;">
	    			<input id="upfax" type="text" name="fax" class="form-control" />
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
	      	<form id="delForm" action="deleteClient.do" method="post">
	      		<input type="hidden" id="delid" name="clientId" />
	      		This will delete <div id="delname"></div> from the database. Are you sure?
	      	</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	        <button type="submit" form="delForm" class="btn btn-default">Confirm</button>
	      </div>
	    </div>
	  </div>
	</div>
	</body>
	<script src="js/clientview.js"></script>
</html>