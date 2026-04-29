

let selectorsData1 = {
	
	
	each: ()=>{
		//пробегаемся по вложенным узлам, включая текстовые
		$(".A").contents().each(function(){
			log2nl("nodeType:",this.nodeType);
			log2("textContent:",this.textContent);
		});
				
	},
	
	filter: ()=>{
		
		//выделяем жирным текстовые узлы с текстом
		$(".A").contents().filter(function(){
			return (this.nodeType==3) && (this.textContent.trim().length);
		}).wrap(boldTag);

		
				
	}
	
	
};


$(() => {
  
	
	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: "../fragments/selectorsSandbox1.html",
		selectorsData: selectorsData1,
		selectedOption: "demo1_script",
		afterSandboxReload: ()=>{
			$btn = $(".B>button.c5");
		},
		initFunction: ()=>{
			
		}
	});	
	
  
  
});


