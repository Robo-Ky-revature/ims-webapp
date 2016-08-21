$(document).ready(function(){
	var statedd = document.createElement("select");
	var stateddc = document.createElement("select");
	statedd.name = "state"; stateddc.name = "state";
	
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:7001/IMS/getAllStates.do",
		method: "GET",
		success: function(resp){
			$.each(resp, function(i, item) {
				var op = document.createElement("option");
				var opc = document.createElement("option");
				op.value = item.stateId; opc.value = item.stateId;
				op.text = item.abbreviation; opc.text = item.abbreviation;
				statedd.appendChild(op); stateddc.appendChild(opc);
			})
			$("#newaddstate").append(statedd);
			$("#upaddstate").append(stateddc);
		}
	});
	
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:7001/IMS/getAllClients.do",
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
					+"</td><td class='tdadd'><div id='l1'>"+item.address.streetAddress1+"</div><div id='l2'>"+item.address.streetAddress2
						+"</div><div id='ct'>"+item.address.city+"</div><div id='st'>"+item.address.state.abbreviation+"</div><div id='zp'>"+item.address.zip
					+"</div></td><td class='tdphone'>"+item.phone
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
			var formVals = {
					upid: $(this).find($(".tdid")).html(),
					upname: $(this).find($(".tdname")).html(),
					upemail: $(this).find($(".tdemail")).html(),
					upl1: $(this).find($(".tdadd")).find($("#l1")).html(),
					upl2: $(this).find($(".tdadd")).find($("#l2")).html(),
					upct: $(this).find($(".tdadd")).find($("#ct")).html(),
					upzp: $(this).find($(".tdadd")).find($("#zp")).html(),
					upphone: $(this).find($(".tdphone")).html(),
					upfax: $(this).find($(".tdfax")).html()};
			
			$(this).find($(".tdacts")).find($(".update")).click(function(){
				
				$("#upname").val(formVals.upname);
				$("#upemail").val(formVals.upemail);
				$("#upaddline1").val(formVals.upl1);
				$("#upaddline2").val(formVals.upl2);
				$("#upaddcity").val(formVals.upct);
				$("#upaddzip").val(formVals.upzp);
				$("#upphone").val(formVals.upphone);
				$("#upfax").val(formVals.upfax);
			})
			
			$(this).find($(".tdacts")).find($(".delete")).click(function(){
				$("#delname").html(formVals.upname);
				$("#delid").val(formVals.upid);
			})
		})
	}
})