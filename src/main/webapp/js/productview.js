$(document).ready(function(){
	var categorydd = document.createElement("select");
	var categoryddc = document.createElement("select");
	categorydd.name = "state"; categoryddc.name = "state";
	
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:7001/IMS/getCategories.do",
		method: "GET",
		success: function(resp){
			$.each(resp, function(i, item) {
				var op = document.createElement("option");
				var opc = document.createElement("option");
				op.value = item.categorydd; opc.value = item.catagoryId;
				op.text = item.description; opc.text = item.description;
				categorydd.appendChild(op); catagoryddc.appendChild(opc);
			})
			$("#newcat").append(statedd);
			$("#newcat").append(stateddc);
		}
	});
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:7001/IMS/getAllProducts.do",
		method: "GET",
		success: function(resp){
			$("#products").html("<tr id='header'>"
					+"<th>UPC</th>"
					+"<th>Name</th>"
					+"<th>Weight</th>"
					+"<th>Size</th>"
					+"<th>Unit Cost</th>"
					+"<th>Description</th>"
					+"<th>Category</th>");
			$.each(resp, function(i, item){
				console.log(item);
				$("#products").append(
					"<tr><td class='tdupc'>"+item.upc
					+"</td><td class='tdname'>"+item.productName
					+"</td><td class='tdwei'>"+item.weight
					+"</td><td class='tdsize'>"+item.size
					+"</div></td><td class='tdcost'>"+item.cost
					+"</td><td class='tdcat'>"+item.description
					+"</td><button type='button' class='btn btn-default btn-sm update'>Update</button>"
					+"<button type='button' class='btn btn-default btn-sm delete'>Delete</button>"
					+"</td></tr>");
			});
			setButtons();
		}
	});
	
	function setButtons() {
	$("#products tr").each(function(){
		$(".update").attr("data-toggle","modal");
		$(".update").attr("data-target","#upModal");
		$(".delete").attr("data-toggle","modal");
		$(".delete").attr("data-target","#delModal");
		var formVals = {upname: $(this).find($(".tdname")).html(),
				upwei: $(this).find($(".tdwei")).html(),
				upsize: $(this).find($(".tdsize")).html(),
				upcost: $(this).find($(".tdcost")).html(),
				updesc: $(this).find($(".tddesc")).html(),
				upcat: $(this).find($(".tdcat")).html()};
		
		$(this).find($(".update")).click(function(){
			$("#upname").val(formVals.upname);
			$("#upwei").val(formVals.upwei);
			$("#upsize").val(formVals.upsize);
			$("#upcost").val(formVals.upcost);
			$("#updesc").val(formVals.updesc);
			$("#upcat").val(formVals.upcat);
		})
	})
	}
})