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
//    $('#container2').highcharts({
//        chart: {
//            type: 'bar'
//        },
//        title: {
//            text: 'Best Selling Product'
//        },
//        subtitle: {
//            text: '2016'
//        },
//        xAxis: {
//            categories: ['prod1', 'prod2', 'prod3', 'prod4', 'prod5'],
//            title: {
//                text: null
//            }
//        },
//        yAxis: {
//            min: 0,
//            title: {
//                text: 'Revenue',
//                align: 'high'
//            },
//            labels: {
//                overflow: 'justify'
//            }
//        },
//        tooltip: {
//        	pointFormat: '{series.name}: <b>${point.y:.2f}</b>'
//        },
//        plotOptions: {
//            bar: {
//                dataLabels: {
//                    enabled: true
//                }
//            }
//        },
//        legend: {
//            layout: 'vertical',
//            align: 'right',
//            verticalAlign: 'top',
//            x: -40,
//            y: 80,
//            floating: true,
//            borderWidth: 1,
//            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
//            shadow: true
//        },
//        credits: {
//            enabled: false
//        },
//        series: [{
//            name: 'Profit',
//            data: [1052, 954, 4250, 740, 38]
//        }, {
//            name: 'Loss',
//            data: [1052, 954, 4250, 740, 38]
//        }]
//    });
//	}
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