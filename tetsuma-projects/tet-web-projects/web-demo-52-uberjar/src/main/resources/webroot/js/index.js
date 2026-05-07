

$(()=>{
	
	$("#demoscan").click(e=>{
		e.preventDefault();

		$.get({
		  url: "demoscan/refreshDemoList",
		  success: function(data, status, request){
			location.reload(true);
		  }
		});	
				
	})
	
	
	
})



