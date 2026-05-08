






let selectorsData1 = {

	addParams(){

		//добавляем параметры к текущей странице		
		let params = {
			category: 2,
			name: "My name."
		};
		let paramString = $.param(params);
		
		location.href = location.pathname + '?' + paramString;
	},	
		
	test1: `
		accordUtils.getRequestParameter("category");
	
		accordUtils.getRequestParameter("name");
	
		
	`,
	
	
	
	
	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		//		lfMode: true,
//		selectedOption: "test1",
		initFunction: ()=>{
			
		}
	});	
	
});









