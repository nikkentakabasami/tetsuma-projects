
let numbersStr = "[0, 1, 2, 3]";
let userStr = '{ "name": "Вася", "age": 35, "isAdmin": false, "friends": [10,11,12,13] }';

let testEvent1 = {
  title: "Конференция",
  date: "сегодня",
  user: {
	id: 50,
	name: "Patrik"
  },
  id: 123
};


let str;



let object;

let selectorsData1 = {

	
	json1:`

	#   JSON формат отличается от JavaScript-объекта тем что
	# 1)Нужно использовать только двойные кавычки
	#   При этом ключи тоже должны быть в двойных кавычках.
	# 2)Недопустимы комментарии
	#

	numbersStr;

	userStr;
	`,
	

	json_parse:`
	# JSON.parse(str)
	#   превратит строку с данными в формате JSON в JavaScript-объект
	#
	
	numbersStr;

	object = JSON.parse(numbersStr);

	userStr;
		
	object = JSON.parse(userStr);
	`,
	
	
	json_parse_reviver(){
		//# JSON.parse(str, reviver)
		
		//# reviver
		//#   функция формата function(key, value) для преобразования строковых значений в объекты.
		
		str = '{"title":"Конференция","date":"2014-11-30T12:00:00.000Z"}';
	
		object = JSON.parse(str, function(key, value) {
		  if (key == 'date') return new Date(value);
		  return value;
		});
	
		return object;
	},	
	
	json_stringify(){
		//# JSON.stringify(value, replacer, space)
		//#   Преобразует значение в JSON-строку.
		
		str = JSON.stringify(testEvent1);
		log2nl(str);

		//# Если в объекте есть метод toJSON() - для сериализации будет использоваться он.

		let event = Object.assign({}, testEvent1);  //клонируем объект
		event.toJSON = function(){
			return this.id+"-"+this.title;
		}

		str = JSON.stringify(event);
		log2nl(str);
				
		
	},	
	json_stringify_replacer(){
		//# JSON.stringify(value, replacer, space)
		
		//# replacer
		//#   массив свойств, которые подлежат сериализации.
		//#   либо функция меняющая сериализацию.
			
		log2nl("replacer as array:");
		str = JSON.stringify(testEvent1,["id","date"]);
		log2nl(str);
	
		log2nl("replacer func1:");
		str = JSON.stringify(testEvent1,(key, value)=>{
			log2("---key:",key,", value:", (typeof value));
			
			//отфильтровываем поля со строковыми значениями
			if (typeof value === "string") {
			  return undefined;
			}
			return value;
		});
		log2nl(str);
		
	
	},	
	json_stringify_space(){
		//# JSON.stringify(value, replacer, space)
		
		//# space - используется для улучшения форматирования.
		//# Если он является числом – то уровни вложенности в JSON оформляются указанным количеством пробелов, 
		//# если строкой – вставляется эта строка.

		str = JSON.stringify(testEvent1,"",4);
		log2nl(str);
		
		str = JSON.stringify(testEvent1,"","__");
		log2nl(str);
		
	},	
	
	

}


$(()=>{
	
	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		selectedOption: "json_stringify_replacer",
		lfMode: true,
		autoscrollLog2: false,
		initFunction: ()=>{
			
		}
	});	

});




