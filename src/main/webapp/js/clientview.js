$(document).ready(function(){
	$("#clients tr").each(function(){
		$(".update").attr("data-toggle","modal");
		$(".update").attr("data-target","#upModal");
		$(".delete").attr("data-toggle","modal");
		$(".delete").attr("data-target","#delModal");
		var formVals = {upname: $(this).find($(".tdname")).html(),
				upemail: $(this).find($(".tdemail")).html(),
				upadd: $(this).find($(".tdadd")).html(),
				upphone: $(this).find($(".tdphone")).html(),
				upfax: $(this).find($(".tdfax")).html()};
		
		$(this).find($(".update")).click(function(){
			$("#upname").val(formVals.upname);
			$("#upemail").val(formVals.upemail);
			$("#upadd").val(formVals.upadd);
			$("#upphone").val(formVals.upphone);
			$("#upfax").val(formVals.upfax);
		})
	})
})