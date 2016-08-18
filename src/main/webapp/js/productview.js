$(document).ready(function(){
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
})