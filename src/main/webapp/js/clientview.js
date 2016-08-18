$(document).ready(function(){
	//popClients();
	
	$("#clients tr").each(function(){
		$(".update").attr("data-toggle","modal");
		$(".update").attr("data-target","#upModal");
		$(".delete").attr("data-toggle","modal");
		$(".delete").attr("data-target","#delModal");
		var formVals = {upname: $(this).find($(".tdname")).html(),
				upemail: $(this).find($(".tdemail")).html(),
				upadd: $(this).find($(".tdadd")).html(),
				upphone: $(this).find($(".tdphone")).html(),
				upfax: $(this).find($(".tdfax")).html()};
		
		$(this).find($(".update")).click(function(){
			$("#upname").val(formVals.upname);
			$("#upemail").val(formVals.upemail);
			$("#upadd").val(formVals.upadd);
			$("#upphone").val(formVals.upphone);
			$("#upfax").val(formVals.upfax);
		})
	})
})

function popClients() {
	$.ajax({
		headers: {          
			"Accept" : "application/json"
		}, 
		url: "getAllClients.do",
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
				$("#clients").append(
				"<tr><td class='tdid'>" + item.clientId
				+"</td><td class='tdname'>" + item.name
				+"</td><td class='tdemail'>" + item.email
				+"<tr><td class='tdtype'>"
				+"</td><td class='tdadd'>"
				+"</td><td class='tdphone'>" + item.phone
				+"</td><td class='tdfax'>" + item.fax
				+"</td><td class='tdacts'>"
				+"</td></tr>");
			});
		}
	});
}