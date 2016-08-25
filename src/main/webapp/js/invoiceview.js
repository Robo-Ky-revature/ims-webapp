var products = [];
var clients = [];
var linenum = 1;

$(document).ready(function(){
	$("#newbutton").click(function(){
		$("#productlines").html("<tr><th>Product</th><th>Unit Cost</th><th>On Hand</th><th>Quantity</th><th>Subtotal</th></tr>");
		linenum = 1;
	});
	
	var clientdd = document.createElement("select");
	clientdd.name = "client";
	clientdd.id = "clientdd";
	var dummy = document.createElement("option");
	dummy.value = 0; dummy.text = "---"; dummy.label = "null";
	clientdd.appendChild(dummy);
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:9001/IMS/getAllClients.do",
		method: "GET",
		success: function(resp){
			$.each(resp, function(i, item) {
				item.index = i;
				clients.push(item);
				var op = document.createElement("option");
				op.value = item.clientId;
				op.text = item.name;
				op.label = i;
				clientdd.appendChild(op);
			})
			clientdd.onchange = function(){
				var parentform = $(this).parent().parent().parent();
				var current = $(this).find(":selected");
				var index = current.attr("label");
				if (clients[index] == undefined) {
					parentform.find($("#clitype")).find($("#ctypetext")).html("---");
					parentform.find($("#clitype")).find("input").val("0");
				} else {
					var ctype = clients[index].type.clientId;
					if (ctype == 1) {
						parentform.find($("#clitype")).find($("#ctypetext")).html("Outgoing To Retailer");
						parentform.find($("#clitype")).find("input").val("1");
					} if (ctype == 2) {
						parentform.find($("#clitype")).find($("#ctypetext")).html("Incoming From Supplier");
						parentform.find($("#clitype")).find("input").val("2");
					}
				}
			};
			$("#selclient").append(clientdd);
		}
	});
	
	
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:9001/IMS/getAllProducts.do",
		method: "GET",
		success: function(resp){
			$.each(resp, function(i, item) {
				item.index = i;
				products.push(item);
			})
			//$("#selproduct").append(productdd);
		}
	});
	
	$("#addproduct").click(function(){
		var newrow = document.createElement("tr");
		newrow.className = "prow";
		var tdprod = document.createElement("td");
		tdprod.className = "selproduct";
		var linein = document.createElement("input");
		linein.name = "line";
		linein.hidden = true;
		linein.value = linenum;
		linenum++;
		tdprod.appendChild(linein);
		
		var productdd = document.createElement("select");
		productdd.name = "upc";
		productdd.id = "productdd";
		var dummy2 = document.createElement("option");
		dummy2.value = 0; dummy2.text = "---"; dummy2.label = "null";
		productdd.appendChild(dummy2);
		for (var i=0; i<products.length; i++) {
			var op = document.createElement("option");
			op.value = products[i].upc;
			op.text = products[i].productName;
			op.label = products[i].index;
			productdd.appendChild(op);
			tdprod.appendChild(productdd);
		}
		productdd.onchange = function(){
			var parentrow = $(this).parent().parent();
			var current = $(this).find(":selected");
			if (current.attr("label") != "null") {
				parentrow.find($(".ucost")).find($("div")).html(products[current.attr("label")].cost);
				parentrow.find($(".ucost")).find($("input")).val(products[current.attr("label")].cost);
				parentrow.find($(".onhand")).find($("div")).html(products[current.attr("label")].onHand);
				parentrow.find($(".onhand")).find($("input")).val(products[current.attr("label")].onHand);
				parentrow.find($(".qty")).find($(".qfield")).val(1);
				var calctotal = parentrow.find($(".qty")).find($(".qfield")).val() * parentrow.find($(".ucost")).find($("div")).html()
				parentrow.find($(".subtot")).html(calctotal);
			} else {
				parentrow.find($(".ucost")).find($("div")).html(0);
				parentrow.find($(".ucost")).find($("input")).val(0);
				parentrow.find($(".onhand")).find($("div")).html(0);
				parentrow.find($(".onhand")).find($("input")).val(0);
				parentrow.find($(".qty")).find($(".qfield")).val(0);
				parentrow.find($(".subtot")).html(0);
			}
		};
		newrow.appendChild(tdprod);
		
		var tdc = document.createElement("td");
		tdc.className = "ucost";
		var ucdiv = document.createElement("div");
		ucdiv.innerHTML = "0";
		tdc.appendChild(ucdiv);
		var udin = document.createElement("input");
		udin.name = "price";
		udin.hidden = true;
		tdc.appendChild(udin);
		newrow.appendChild(tdc);
		var tdo = document.createElement("td");
		tdo.className = "onhand";
		var ohdiv = document.createElement("div");
		ohdiv.innerHTML = "0";
		tdo.appendChild(ohdiv);
		var ohin = document.createElement("input");
		ohin.name = "onHand";
		ohin.hidden = true;
		tdo.appendChild(ohin);
		newrow.appendChild(tdo);
		var tdq = document.createElement("td");
		tdq.className = "qty";
		var qfield = document.createElement("input");
		qfield.name = "quantity";
		qfield.className = "qfield";
		qfield.onchange = function() {
			var parentrow = $(this).parent().parent();
			if ($(this).val() > 0) {// 
				var calctotal = $(this).val() * parentrow.find($(".ucost")).find($("div")).html();
				parentrow.find($(".subtot")).html(calctotal);
			} else {
				parentrow.find($(".subtot")).html(0);
			}
		};
		tdq.appendChild(qfield);
		newrow.appendChild(tdq);
		var tdst = document.createElement("td");
		tdst.className = "subtot";
		tdst.innerHTML = "0";
		newrow.appendChild(tdst);
		$("#productlines").append(newrow);
	});
})
