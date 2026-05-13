

let numbers, p;

let selectorsData1 = {

	dialogs(){

		//# Системные диалоги
		//# alert() - Показ сообщения
		
		alert("I am an alert box!!")


		//# confirm()
		//#   Диалог подтверждения

		result = confirm("Сделай выбор.")
		if (result){
			log2("Ты нажал Ok")
		} else {
			log2("Ты нажал Cancel")
		}

		//# prompt(title, default_value)
		//#   Получение строки от пользователя

		result = prompt("Твоё имя?");
		log2("Имя:"+result)
		
	},
	setTimeout(){
		//# setTimeout( func, delay[, arg1, arg2...])
		//#   Вызывает функцию func с задержкой в delay миллисекунд.
		//#   Передаёт в функцию параметры arg1, arg2...
		//#   Возвращает числовой идентификатор таймера timerId
		//# 
		//# clearTimeout(timerId)
		//#   Отменяет действие, заданное  в setTimeout

		function timerFunc(phrase, who) {
		  log2(phrase, who);
		}

		let timerId = setTimeout(timerFunc, 1000, "Привет", "Вася");

		//clearTimeout(timerId);
		
				
	},
	setInterval(){
		//# setInterval(func / code, delay[, arg1, arg2...])
		//#   Регулярно выполняет функцию через указанный интервал времени. 
		//# 
		//# clearInterval(timerId) 
		//#   Отменяет таймер

		// начать повторы с интервалом 1 сек
		let counter = 0;
		let timerId = setInterval(()=>{
		  log2("тик",++counter);
		}, 1000);

		// через 5 сек остановить повторы
		setTimeout(function() {
		  clearInterval(timerId);
		  log2("стоп");
		}, 5000);
		
	},
	setTimeout_recoursive(){
		
		//# Рекурсивный setTimeout
		//#   более гибкий метод тайминга, чем setInterval, так как время до следующего выполнения можно запланировать по-разному.

		let counter = 0;
		let timerId = setTimeout(function tick() {
			log2("тик",++counter);
			if (counter>10){
				log2("стоп");
				return;
			}			
			timerId = setTimeout(tick, counter*100);
		}, 100);		
		
	},
	eval(){
		
		//# eval
		//#   выполнение скрипта.
		//#   Возвращает последнее вычисленное выражение.
		
		a=false;
		result = eval("if (a) { 1+1 } else { 1+2 }");
		log2(result);
		
	},
	
	
	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		//		lfMode: true,
		selectedOption: "Proxy2",
		initFunction: ()=>{
			
		}
	});	
	
});







