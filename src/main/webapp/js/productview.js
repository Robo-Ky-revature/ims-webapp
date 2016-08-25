$(document).ready(function(){
	var categorysel = document.createElement("select");
	var categoryup = document.createElement("select");
	categorysel.name = "category"; categoryup.name="category";
	categorysel.multiple = true; categoryup.multiple=true;
	

	
	
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:7001/IMS/getAllCategories.do",
		method: "GET",
		success: function(resp){
			$.each(resp, function(i, item) {
				var op = document.createElement("option");
				var upop = document.createElement("option");
				op.value= item.catagoryId; upop.value= item.catagoryId;
				op.text = item.description;	upop.text = item.description;			
				categorysel.appendChild(op); categoryup.appendChild(upop);
			})
			$('#newcat').append(categorysel);
			$("#upcat").append(categoryup);
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
					+"<th>Sales Price</th>"
					+"<th>Description</th>"
					+"<th>Inventory</th>"
					+"<th>Category</th>" 
					+"<th>Actions</th>");
			$.each(resp, function(i, item){
				//console.log(item);
				$("#products").append(
					"<tr><td class='tdupc'>"+item.upc
					+"</td><td class='tdname'>"+item.productName
					+"</td><td class='tdwei'>"+item.weight
					+"</td><td class='tdsize'>"+item.size
					+"</td><td class='tdcost'>"+item.cost
					+"</td><td class='tdprice'>"+item.price
					+"</td><td class='tddesc'>"+item.description
					+"</td><td class='tdonHand'>"+item.onHand
					+"</td><td class='tdcat'>" + item.price
					+"</td><td class='tdacts'><button type='button' class='btn btn-default btn-sm update'>Update</button>"
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
		var formVals = {
				upupc: $(this).find($(".tdupc")).html(),
				upname: $(this).find($(".tdname")).html(),
				upwei: $(this).find($(".tdwei")).html(),
				upsize: $(this).find($(".tdsize")).html(),
				upcost: $(this).find($(".tdcost")).html(),
				upprice: $(this).find($(".tdprice")).html(),
				updesc: $(this).find($(".tddesc")).html(),
				upquan: $(this).find($(".tdonHand")).html(),
				upcat: $(this).find($(".tdcat")).html()};
		
		$(this).find($(".update")).click(function(){
			$("#upupc").val(formVals.upupc);
			$("#upname").val(formVals.upname);
			$("#upwei").val(formVals.upwei);
			$("#upsize").val(formVals.upsize);
			$("#upcost").val(formVals.upcost);
			$("#upprice").val(formVals.upprice);
			$("#updesc").val(formVals.updesc);
			$("#upquan").val(formVals.upquan);
			$("#upcat").val(formVals.upcat);
		})
		$(this).find($(".tdacts")).find($(".delete")).click(function(){
				$("#delname").html(formVals.upname);
				$("#delid").val(formVals.upupc);
			})
	})
	}
})