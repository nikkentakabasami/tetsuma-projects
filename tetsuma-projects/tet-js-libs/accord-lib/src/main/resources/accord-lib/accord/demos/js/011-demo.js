

//демо-функции.
//при вызове addDemoButtons - для каждой такой функции будет создана и добавлена демо-кнопка (на acc-button-panel)
let bh1 = {
	
	test1(){
		//тестовая функция 1
		log2("remove first text field")
		$inp1.remove();
		
	},
	test2(){
		$inp2.addClass("red-border");
	},
	test3(){
		$inp2.addClass("bg-red");
	},
	
}

//для демо-функции можно задать связанную функцию, которая будет выводится в лог при её выполнении.
bh1.test1.init = test1Init;

function test1Init(){
	//init function
}


$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_BUTTONS,
		workPanelTemplate: TEMPLATE_FORM1,
		selectorsData: bh1,
		selectedOption: null,
		title: "mytitle",
		initFunction: ()=>{
			
		}
	});	
	
});



