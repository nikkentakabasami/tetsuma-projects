

function showAllSvgDesc(){
	
	$("svg").each((ind,el)=>{
		
		let code = $(el).html().trim();
		
//		code = code.replaceAll("<","\n<");
		
		$("div."+el.id+"desc").text(code);
	});
	
		$(".acc-desc-panel").each((ind,el)=>{
//			let info = $(el).text().trim();
//			$(el).text(info);
			
			let info = $(el).html().trim();
			$(el).html(info);
		});	
	
	
	
		
	
}
