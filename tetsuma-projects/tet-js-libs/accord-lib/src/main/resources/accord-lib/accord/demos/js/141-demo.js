let selectorsData1 = {

	serializeForms1: `
		$("#form1").serialize();
		$("#form2").serialize();
		$("#form3").serialize();
		$("#form4").serialize();
		$("#form5").serialize();
		`
	
}


$(document).ready(function() {
	
	//добавляем демо-кнопки
//	addDemoButtons(bh1)
	
	
	demoOptions.beforeExec = ()=>{
		clearLog2();
	};
	
	
	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: null,
		selectorsData: selectorsData1,
		selectedOption: "serializeForms1",
		title: "mytitle",
		initFunction: ()=>{
			
		}
	});		
	
	
});




