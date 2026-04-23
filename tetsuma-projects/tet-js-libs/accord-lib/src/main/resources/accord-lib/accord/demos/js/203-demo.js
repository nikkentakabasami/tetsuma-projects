

const testObject1 = {
    name: "Bob",
    age: 42,
    fresh: true
};

let r;
let $form

let selectorsData1 = {



	random_and_format:`

	//возвращает случайное целое число в заданном диапазоне
	accordUtils.random(10);

	accordUtils.random(10, 15);

	//возвращает случайную дату
	accordUtils.randomDate();

	//можно задать диапазон дат: randomDate(minYear, maxYear)
	accordUtils.randomDate(2020, 2021);
		
	//простое форматирование даты: dd.mm.yyy
	accordUtils.formatDate(new Date());

	//простой парсинг даты: dd.mm.yyy
	accordUtils.parseDate("05.05.2025");

	`,		
	

	accordUtils_demo1:`
	
	//путь к библиотеке accord. Вычисляется через import.meta.url
	accordUtils.accordPath;
	
	
	//клонирование объекта через JSON стрингификацию и парсинг
	accordUtils.jsonCopy(testObject1); ~
	
	//поверхностное клонирование объекта
	accordUtils.cloneObject(testObject1); ~

	//можно задать копируемые атрибуты
	accordUtils.cloneObject(testObject1, "name", "age"); ~
	
	//клонировать объект можно и так:
	Object.assign({}, testObject1); ~
	
	
	`,
	
	highlightText1(){
		
		//ищем числа вроде: 355, 766
		
		//подсвечивает текст в заданном div, заданным цветовым классом.
		//области подсветки можно задавать регулярным выражением или массивом sections.
		//возвращает результат поиска и модифицированный текст (в формате options)
		let opts = accordUtils.highlightText({
			$div: $log1,
			regex: /\d+/g,
			class: "bg-green",
			matchHandler: match=>{
				log2("match=",match,", match.index=",match.index);
			}
		});
		
		
	},
		
	highlightText2(){
		
		//выделяем заданные секции текста
		
		let opts = accordUtils.highlightText({
			$div: $log1,
			sections: [[10,5],[20,10]],   //массив из пар [index, length]
			class: "bg-green"
		});
		
		
	},		

	regex: `
		
		//преобразовывает строку с регулярным выражением в объект RegExp
		r = accordUtils.stringToRegex("/\\\\d/g");
		
		r.global;
		
		result = r.exec("then see Chapter 1.2.3.4.5")
		
		result.index;
		
	`,

	
	
	copyTextToBuffer(){
		//Закидывает заданный текст в буфер обмена
		accordUtils.copyTextToBuffer('Привет!');
				
	},
	

	

	
	
	
}





$(() => {
	
	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: TEMPLATE_FORM1,
		selectorsData: selectorsData1,
		selectedOption: "random_and_format",
		title: "accordUtils - различные функции",
		initFunction: ()=>{
			
		}
	});		

});



