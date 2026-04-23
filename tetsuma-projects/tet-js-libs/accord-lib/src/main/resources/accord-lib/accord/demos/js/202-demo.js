
let myVar = 10;

let currentJS;


let selectorsData1 = {


	addJSToPage1(){
		//addJSToPage(jsHref, onload) - добавляет в документ js-файл (и выполняет его)
		
		
		selectorsData1.removeJSFromPage();	
		currentJS  = accordUtils.addJSToPage("js/dt1.js");
		window.currentJS = currentJS;
		
		let code = accordUtils.loadFileAsString(currentJS.src);
//		log2("added file",currentJS.src);
//		log2(code);
		
		
	},
	addJSToPage2(){
		selectorsData1.removeJSFromPage();	
		currentJS = accordUtils.addJSToPage("js/dt2.js");
		window.currentJS = currentJS;
		
	},
	removeJSFromPage(){
		if (currentJS){
			accordUtils.removeJSFromPage(currentJS);
			log2(`${currentJS.src} removed!`);
			currentJS = null;
		}
	},

	
	


}





$(() => {
	
	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		selectedOption: "addJSToPage1",
		title: "accordUtils - динамическая подгрузка js файлов",
		initFunction: ()=>{
		}
	});		

});



