var products = [];
var clients = [];
var linenum = 1;
var porders = [];
var polines = [];

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
	
	$.ajax({
		headers: {          
	   		"Accept" : "application/json"
	   	}, 
		url: "http://localhost:9001/IMS/getAllPoLines.do",
		method: "GET",
		success: function(resp){
			$.each(resp, function(i, item) {
				item.index = i;
				polines.push(item);
				console.log(item);
			})
		}
	});
	
	$.ajax({
		headers: {
			"Accept" : "application/json"
		},
		url: "http://localhost:9001/IMS/getAllInvoices.do",
		method: "GET",
		success: function(resp){
			$("#invoices").html("<tr class='header'>"
				+"<th>Invoice #</th>"
				+"<th>Date</th>"
				+"<th>Client</th>"
				+"<th>Type</th>"
				+"<th>Subtotal</th>"
				+"<th>Tax</th>"
				+"<th>Total</th>"
				+"<th></th>"
				+"</tr>");
			$.each(resp, function(i, item) {
				item.index = i;
				porders.push(item);
				var invtype = "";
				if (item.client.type.clientId == 1)
					invtype = "Outgoing";
				else
					invtype = "Incoming";
				$("#invoices").append(
					"<tr><td class='tdnum'>"+item.orderNumber
					+"</td><td class='tddate'>"+item.purchaseDate
					+"</td><td class='tdcli'>"+item.client.name
					+"</td><td class='tdtype'>"+invtype
					+"</td><td class='tdsubt'>$"+item.subtotal.toFixed(2)
					+"</td><td class='tdtax'>$"+item.tax.toFixed(2)
					+"</td><td class='tdtot'>$"+item.total.toFixed(2)
					+"</td><td class='tdex'>"
						+"<button type='button' class='btn btn-default btn-md expand' data-toggle='modal' data-target='#exModal'>Expand</button></td></tr>");
				setButtons();
			})
		}
	});
	
	function setButtons() {
		$("#invoices tr").each(function(){
			$(this).find($(".tdex")).find($(".expand")).click(function(){
				$(document).find($("#invoiceLabel")).html("Invoice #"+$(this).parent().parent().find($(".tdnum")).html());
				var thisInv = [];
				for (var i=0; i<polines.length; i++) {
					if (polines[i].poLineId.order.orderNumber == $(this).parent().parent().find($(".tdnum")).html())
						thisInv.push(polines[i]);
				}
				$("#expandedinv").html(
						"<tr class='header'>"
			      		+"<th>Product UPC</th>"
			      		+"<th>Name</th>"
			      		+"<th>Unit Price</th>"
			      		+"<th>Quantity</th>"
			      		+"<th>Subtotal</th></tr>"
					);
				for (var i=0; i<thisInv.length; i++) {
					$("#expandedinv").append(
							"<tr><td class='tdupc'>"+thisInv[i].product.upc
				      		+"</td><td class='tdpname'>"+thisInv[i].product.productName
				      		+"</td><td class='tdprice'>$"+thisInv[i].price.toFixed(2)
				      		+"</td><td class='tdqty'>"+thisInv[i].quantity
				      		+"</td><td class='tdsubt'><div>$"+(thisInv[i].price * thisInv[i].quantity).toFixed(2)
				      		+"</td></tr>"
						);
				}
			});
		});
	}
})