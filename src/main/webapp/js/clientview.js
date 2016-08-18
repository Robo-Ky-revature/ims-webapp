$(document).ready(function(){
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
							+"</td><td class='tdadd'><p id='l1'>"+item.address.streetAddress1+"</p><p id='l2'>"+item.address.streetAddress2
								+"</p><p id='ct'>"+item.address.city+"</p><p id='st'>"+item.address.state.abbreviation+"</p><p id='zp'>"+item.address.zip
							+"</p></td><td class='tdphone'>"+item.phone
							+"</td><td class='tdfax'>"+item.fax
							+"</td><td class='tdacts'><button type='button' class='btn btn-default btn-sm update' data-toggle='modal' data-target='#upModal'>Update</button> "
								+"<button type='button' class='btn btn-default btn-sm delete' data-toggle='modal' data-target='#delModal'>Delete</button>"
							+"</td></tr>");
					});
					setButtons();
				}
		});
	
	
	function setButtons() {
		$("#clients tr").each(function(){
			var formVals = {upname: $(this).find($(".tdname")).html(),
					upemail: $(this).find($(".tdemail")).html(),
					upl1: $(this).find($(".tdadd")).find($("#l1")).html(),
					upl2: $(this).find($(".tdadd")).find($("#l2")).html(),
					upct: $(this).find($(".tdadd")).find($("#ct")).html(),
					upst: $(this).find($(".tdadd")).find($("#st")).html(),
					upzp: $(this).find($(".tdadd")).find($("#zp")).html(),
					upphone: $(this).find($(".tdphone")).html(),
					upfax: $(this).find($(".tdfax")).html()};
			
			$(this).find($(".tdacts")).find($(".update")).click(function(){
				
				$("#upname").val(formVals.upname);
				$("#upemail").val(formVals.upemail);
				$("#upaddline1").val(formVals.upl1);
				$("#upaddline2").val(formVals.upl2);
				$("#upaddcity").val(formVals.upct);
				$("#upaddstate").val(formVals.upst);
				$("#upaddzip").val(formVals.upzp);
				$("#upphone").val(formVals.upphone);
				$("#upfax").val(formVals.upfax);
			})
		})
	}
})