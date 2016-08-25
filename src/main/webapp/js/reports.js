$(document).ready(function(){
	var warning = document.createElement("select")
	warning.multiple=true;
	warning.name="warning";
	
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:7001/IMS/getAllProducts.do",
		method: "GET",
		success: function(resp){
			$.each(resp, function(i, item){
				
				if(item.reorder>item.onHand){
					if((1.0*(item.onHand /item.reorder))<=.1 ){
						var op = document.createElement("option");
						op.value= item.upc;
						op.text = item.productName;
						warning.appendChild(op);
					}
				}
				
			})
			$('#warning').append(warning);
		}
	   	
	});
});
