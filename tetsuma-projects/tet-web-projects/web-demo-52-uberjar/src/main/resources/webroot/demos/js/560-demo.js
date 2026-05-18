
let testObject = {
	id: 123,
	name: "bob"
}

function heavyTask() {
  let result = 0;
  for (let i = 0; i < 1000000; i++) {
    result += Math.sqrt(i);
  }
  return result;
}


let selectorsData1 = {
	
	console1(){
		
		/*
		console.log
		  Удобный способ вывода отладочной информации.
		  Можно задавать несколько параметров - они будут выведены через пробел
		*/
		console.log("C+pgUp","changes selected tabs.",123,"+++");

		//# Вывод с форматированием
		//# %s — строка
		//# %d, %i — целое число
		//# %f — дробное число
		//# %o — объект в форматированном виде
		console.log(
		     "%s theory is %d concept. numbers: %i, %d, %f, %f",
		      "string", 1, 
			  48 , 2.87, 3.14, Math.PI
		);

		console.log("my object: %o is based",testObject);

		//# info, warn, error
		//# log выводит сообщения на уровне info
		console.info("%s numbers %d, %d and %d","hello",1,2,3);
		console.warn("%s numbers %d, %d and %d","hello",1,2,3);
		console.error("%s numbers %d, %d and %d","hello",1,2,3);
		
		
		//# console.assert(condition, msg)		
		//# выводит сообщение об ошибке, если условие нарушается

		console.assert(1 === "1", "A doesn't equal B");
		console.assert(true === "true");

		
		//# console.dir(object)
		//#   Вывести объект (его содержимое и иерархию)
		console.dir([1,2,3]);

		
		
	},
	console2(){
		
		//# Замер времени
		//# console.time(timerId)
		//# console.timeEnd(timerId)
		//# 
		console.time();
		heavyTask();
		console.timeEnd();

		console.time("timer1");
		heavyTask();
		console.timeEnd("timer1");

		
	},
	console3(){
		
		//# 
		//# console.profile()
		//# ...
		//# console.profileEnd()
		//#   Позволяет вывести стек профилирования, который подробно показывает, где и сколько времени потратил браузер.
		//#   Для его просмотра нужно перейти на вкладку Performance браузера 
		
		console.profile();
		heavyTask();
		console.profileEnd();
		
		
	},
	
	
	
	
	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		lfMode: true,
		selectedOption: "location2",
		initFunction: ()=>{
			
		}
	});	
	
});







