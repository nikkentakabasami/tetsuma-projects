
let selectorsData1 = `

//выделить строки с комментами
/^.*\\/\\/.*/gm

//Просмотр назад
//содержимое комметов
/(?<=\\/\\/).*/gm

//квантификатор
//пробелы когда их 5 и более
/\\s{5,}/gm

//строки начинаются с let
/\\s*let.*/gm	

//строки, которые кончаются на ;
/.*;$/gm

//группы
//обращения к полям объекта a
/(a)\\.(\\w+)/gm	

//Просмотр вперёд и назад
//ищем содержимое функций addClass
/(?<=addClass\\(\").+(?=\"\\))/gm	

//Поиск на границе слова
/\\bre/g

//Поиск не на границе слова
/\\Bd+/g

`;



$(document).ready(function() {

	initBriefDemo(	{
	demoType: DT_REGEXP,
	selectorsData: selectorsData1,
	regexpMode: true,
	sampleText: `
		
	//демо-функции.
	let bh1 = {
		
		test1(){
			//тестовая функция 1
			log2("remove first text field")
			$inp1.remove();
			a.hello();
			
		},
		test2(){
			$inp2.addClass("red-border");
		},
		test3(){
			$inp2.addClass("bg-red");
		},
		
	}
	
	
		----`,
	
	initFunction: ()=>{
		
	}
	});	
	
});

