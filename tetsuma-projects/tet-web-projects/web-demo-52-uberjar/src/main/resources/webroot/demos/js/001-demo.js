
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

	
	//добавляем демо-кнопки
	addDemoButtons(bh1)
	
	//код, который будет выполняться перед каждой демо-функцией
	demoOptions.beforeExec = ()=>{
		
		//очищает .workPanel и загружает в неё элементы из #template1
		reloadSandbox();
	};

	//и после неё
	demoOptions.afterExec	= ()=>{
	};

	//сразу запускаем первую демку
	demoButtons[0].trigger("click");
		
	
});



