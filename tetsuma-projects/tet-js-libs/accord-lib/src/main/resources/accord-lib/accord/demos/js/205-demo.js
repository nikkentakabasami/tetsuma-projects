

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

		//generateSelect - Генерация элемента select с заданными опциями.
		let $sel = accordUtils.generateSelect("customer", customers);
		$formPanel.append($sel);

		//можно задать массив чисел
		$sel = accordUtils.generateSelect("numbers", [1,5,33]);
		$formPanel.append($sel);
		
		//можно задать массив строк
		$sel = accordUtils.generateSelect("strings", ["alfa","beta","gamma"]);
		$formPanel.append($sel);

		//предзаданный селект для выбора булевых значений		
		$sel = accordUtils.generateBooleanSelect("boolSelect");
		$formPanel.append($sel);
		
		
	},		
	

	generateSelect2(){

		//можно задать много опций
		$sel = accordUtils.generateSelect("customer2", {
			data: customers, 
			withNullOption: true, 
			selectedValue: 7,
			multi: true
		});
		$formPanel.append($sel);
		
		$sel = accordUtils.generateSelect("numbers2", {
			data: [77,88,99], 
			withNullOption: true, 
			valueIsIndex: true,
		});
		$formPanel.append($sel);
		
	},			
	
	
	generateSelect3(){

		//генерация опций для селектов
		let optionsCode = accordUtils.generateSelectOptions({
			data: customers, 
			withNullOption: true, 
		});
		$formPanel.text(optionsCode);
		
		
	},		
	
	
}


buttonHandlers1.generateSelect1.init = initCustomers;

function initCustomers(){
	
	//данные можно задать массивом объектов типа {id: 1, name: 'my name'}
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





