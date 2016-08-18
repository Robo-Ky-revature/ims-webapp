$(document).ready(function(){
	
		console.log("gravy");
		$.ajax({
			headers: {          
	    			"Accept" : "application/json"
	    		}, 
				url: "http://localhost:9001/IMS/getAllClients.do",
				method: "GET",
				success: function(resp){
					$("#clients").html("<tr id='header'>"
							+"<th>ID</th>"
							+"<th>Name</th>"
							+"<th>Email</th>"
							+"<th>Type</th>"
							+"<th>Address</th>"
							+"<th>Phone</th>"
							+"<th>Fax</th>"
							+"<th>Actions</th>"
							+"</tr>");
					$.each(resp, function(i, item){
						console.log(item);
						$("#clients").append(
							"<tr><td class='tdid'>"+item.clientId
							+"</td><td class='tdname'>"+item.name
							+"</td><td class='tdemail'>"+item.email
							+"</td><td class='tdtype'>"+item.type.type
							+"</td><td class='tdadd'>"+item.address.streetAddress1+'<br>'+item.address.streetAddress2
								+'<br>'+item.address.city+', '+item.address.state.abbreviation+' '+item.address.zip
							+"</td><td class='tdphone'>"+item.phone
							+"</td><td class='tdfax'>"+item.fax
							+"</td><td class='tdacts'><button type='button' class='btn btn-default btn-sm update' data-toggle='modal' data-target='#upModal'>Update</button> "
								+"<button type='button' class='btn btn-default btn-sm delete' data-toggle='modal' data-target='#delModal'>Delete</button>"
							+"</td></tr>");
					});
				}
		});
	
	
	
	$("#clients tr").each(function(){
		var formVals = {upname: $(this).find($(".tdname")).html(),
				upemail: $(this).find($(".tdemail")).html(),
				upphone: $(this).find($(".tdphone")).html(),
				upfax: $(this).find($(".tdfax")).html()};
		
		$(this).find($(".update")).click(function(){
			$("#upname").val(formVals.upname);
			$("#upemail").val(formVals.upemail);
			$("#upphone").val(formVals.upphone);
			$("#upfax").val(formVals.upfax);
		})
	})
})