
//демо-функции.
//при вызове addDemoButtons - для каждой такой функции будет создана и добавлена демо-кнопка (на acc-button-panel)
let selectorsData1 = {
	
	test1(){
		reloadSandbox()		
		
		//тестовая функция 1
		log2("remove first text field")
		$inp1.remove();
		
	},
	test2(){
		reloadSandbox()		
		$inp2.addClass("red-border");
	},
	test3(){
		reloadSandbox()		
		$inp2.addClass("bg-red");
	},
	
}

//для демо-функции можно задать связанную функцию, которая будет выводится в лог при её выполнении.
selectorsData1.test1.init = test1Init;


function test1Init(){
	//init function
}



function getBriefDemoOptions() {
  return {
    demoType: DT_BUTTONS,
    workPanelTemplate: TEMPLATE_FORM1,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
    selectedOption: null,
    debugMode: false,
    initFunction: () => {
    }
	
  };
}
