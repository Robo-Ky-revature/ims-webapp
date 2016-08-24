$(document).ready(function(){
	var clientdd = document.createElement("select");
	clientdd.name = "client";
	clientdd.id = "clientdd";
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:9001/IMS/getAllClients.do",
		method: "GET",
		success: function(resp){
			$.each(resp, function(i, item) {
				console.log(item);
				var op = document.createElement("option");
				op.value = item.clientId;
				op.text = item.name;
				clientdd.appendChild(op);
			})
			clientdd.onchange = function(){
				console.log("buttered toast");
			};
			$("#selclient").append(clientdd);
		}
	});
	
	var productdd = document.createElement("select");
	productdd.name = "upc";
	productdd.id = "productdd";
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:9001/IMS/getAllProducts.do",
		method: "GET",
		success: function(resp){
			$.each(resp, function(i, item) {
				console.log(item);
				var op = document.createElement("option");
				op.value = item.upc;
				op.text = item.productName;
				productdd.appendChild(op);
			})
			productdd.onchange = function(){
				console.log("gravy");
			};
			$("#selproduct").append(productdd);
		}
	});
})
