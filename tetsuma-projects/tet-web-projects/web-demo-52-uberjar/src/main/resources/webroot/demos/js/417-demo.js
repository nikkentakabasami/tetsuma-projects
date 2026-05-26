

let selectorsData1 = {
	
	
	each: ()=>{
		//пробегаемся по вложенным узлам, включая текстовые
		$(".A").contents().each(function(){
			log2(`nodeType: ${this.nodeType}, textContent: ${this.textContent} `);
			log2hr();
		});
				
	},
	
	filter: ()=>{
		
		//выделяем жирным текстовые узлы с текстом
		$(".A").contents().filter(function(){
			return (this.nodeType==3) && (this.textContent.trim().length);
		}).wrap(boldTag);

		
				
	}
	
	
};


function getBriefDemoOptions() {
  return {
	demoType: DT_SELECT,
	workPanelTemplate: "../fragments/selectorsSandbox1.html",
    selectorsData: selectorsData1,
    lfMode: false,
    afterSandboxReload: null,
    selectedOption: null,
    debugMode: false,
	afterSandboxReload: ()=>{
		$btn = $(".B>button.c5");
	},
    initFunction: () => {
    }
  };
}


