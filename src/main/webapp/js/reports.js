var porders = [];

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
	$.ajax({
		headers: {
			"Accept" : "application/json"
		},
		url: "http://localhost:7001/IMS/getAllInvoices.do",
		method: "GET",
		success: function(resp){
			$("#invoice5").html("<tr class='header'>"
				+"<th>Invoice #</th>"
				+"<th>Date</th>"
				+"<th>Client</th>"
				+"<th>Type</th>"
				+"<th>Total</th>"
				+"<th></th>"
				+"</tr>");
			$.each(resp, function(i, item) {
				if(i<5){
				item.index = i;
				porders.push(item);
				var invtype = "";
				if (item.client.type.clientId == 1)
					invtype = "Outgoing";
				else
					invtype = "Incoming";
				
				$("#invoice5").append(
					"<tr><td class='tdnum'>"+item.orderNumber
					+"</td><td class='tddate'>"+item.purchaseDate
					+"</td><td class='tdcli'>"+item.client.name
					+"</td><td class='tdtype'>"+invtype
					+"</td><td class='tdtot'>$"+item.total.toFixed(2)
					+"</td><td class='tdex'>");
				}
			})
		}
	});
	
//	$.ajax({
//		headers: {          
//	   		"Accept" : "application/json"
//	   	}, 
//		url: "http://localhost:7001/IMS/getAllProducts.do",
//		method: "GET",
//		success: function(resp){
//	    $('#container').highcharts({
//	        chart: {
//	            type: 'bar'
//	        },
//	        title: {
//	            text: 'Stacked bar chart'
//	        },
//	        xAxis: {
//	            categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
//	        },
//	        yAxis: {
//	            min: 0,
//	            title: {
//	                text: 'Total fruit consumption'
//	            }
//	        },
//	        legend: {
//	            reversed: true
//	        },
//	        plotOptions: {
//	            series: {
//	                stacking: 'normal'
//	            }
//	        },
//	        series: [{
//	            name: 'John',
//	            data: [5, 3, 4, 7, 2]
//	        }, {
//	            name: 'Jane',
//	            data: [2, 2, 3, 2, 1]
//	        }, {
//	            name: 'Joe',
//	            data: [3, 4, 4, 2, 5]
//	        }]
//	    });
//		}
//	});
//});

$.ajax({
	headers: {          
   		"Accept" : "application/json"
   	}, 
	url: "http://localhost:7001/IMS/getAllInvoices.do",
	method: "GET",
	success: function(resp){
		var income = 0;
		var loss = 0;
		$.each(resp, function(i, item) {
			if(item.client.type.clientId == 1){
				loss=parseInt(item.total)+ loss;
				
				}
			
			else{
				income=parseInt(item.total)+ income;
			}
		})
	

    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Profits and losses from 2016'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>${point.y:.2f}</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: 'Post Sales Tax',
            colorByPoint: true,
            data: [{
                name: 'Income',
                y: income,
                
                            
            }, {
                name: 'Loss',
                
                y: loss,

            }]
        }]
    });
	}
});

});