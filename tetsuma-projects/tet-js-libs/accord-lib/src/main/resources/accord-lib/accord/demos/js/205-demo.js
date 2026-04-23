

let customers;

//accordUtils - select методы
let buttonHandlers1 = {
	
/*
	sel1: `
	
		customers = [
			{ id: 1568, name: "Yamada Taro" },
			{ id: 2599, name: "Ivanov Ivan" },
			{ id: 7, name: "Petrov Petr" },
		];		
				
		accordUtils.generateSelect("customer", customers);
	
	`,
	*/

	generateSelect1(){

		let $sel = accordUtils.generateSelect("customer", customers);
		$(".form-panel").append($sel);
		
				
		le2(`
			
			`);
		
	},		
	

	
}


buttonHandlers1.generateSelect1.init = initCustomers;

function initCustomers(){
	customers = [
		{ id: 1568, name: "Yamada Taro" },
		{ id: 2599, name: "Ivanov Ivan" },
		{ id: 7, name: "Petrov Petr" },
	];		
}


$(document).ready(function() {

	
	
	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: TEMPLATE_FORM2,
		selectorsData: buttonHandlers1,
		selectedOption: "generateSelect1",
		title: "accordUtils - select методы",
		initFunction: ()=>{
			initCustomers();
		}
	});		
	
	
		
	
});





