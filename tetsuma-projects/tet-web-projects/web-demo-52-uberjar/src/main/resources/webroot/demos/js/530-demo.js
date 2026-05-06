

let regex;
let textSample;



function logRegexParams(){
	le2("textSample");
	le2("regex");
}


//выполняет основные функции с заданнымм регулярным выражением
function testRegexp(text, re, showText = true){
	
	textSample = text;
	regex = re;

	
	if (showText){
		le2("textSample");
	}
	le2("regex");
	
		
	if (regex.global){
		
		
		le2(`
			//-------использование глобального regexp---------

			textSample.search(regex)
						
			@
			const matches = textSample.matchAll(regex);
			for (const match of matches) {
			  log2("match=",match,", match.index=",match.index);
			}
			@

			textSample.match(regex)

			#
			textSample.replace(regex,'A');							
			`);
		
		
	} else {
		
		le2(`
			//-------использование одинарных regexp---------
			
			textSample.search(regex)
			
			result = textSample.match(regex);
			
			result.index
			
			textSample.replace(regex,'A');							
			`);		

	}

	
}



let textSample2 = "_ОЙ-Ой-ой";
let textSample3 = "  The quick brown fox jumps over the lazy dog. It barked.";
let textSample4 = "then see Chapter 1.2.3.4.5";
let s;

let regexSamples = [
	/(\.\d)(\.\d)/g,
	/see (chapter \d+(\.\d)*)/gi,
	/see (chapter \d+(\.\d)*)/i,
	/\.\d/,		//3
	/\.\d/g,
	/(\.\d)(\.\d)/g		//5
	
	
]



let selectorsData1 = {

	regexp_fields: `
	
	//поля RegExp:
	
	textSample = textSample4;
	regex = regexSamples[0];
					
	regex.flags
	regex.global
	regex.ignoreCase
	regex.multiline
	regex.source
			
	//lastIndex - индекс, с которого надо начать поиск (в методах exec, test)
	//Это поле меняют методы test, exec!
	regex.lastIndex
	
	regex.test(textSample);
	regex.lastIndex

	//всегда можно сбросить или задать это поле
	regex.lastIndex = 0;  !
	
	
	//RegExp.escape(string) - помогает эскейпить спецсимволы в строке, чтобы использовать её в паттерне
	//Поддерживается только в новых браузерах!
	//s = RegExp.escape("1.2.");
	//regex = new RegExp(s+"\\d+", "g");
	//textSample.match(regex);

	
	`,
	

	search_test_demo1:`

	# str.search(regexp)
	#   возвращает позицию первого совпадения или -1, если ничего не найдено.
	#   Нельзя заставить search искать дальше первого совпадения.

	textSample = textSample4;
	regex = regexSamples[5];

	textSample.search(regex)
		

	# regexp.test(str) - проверяет, есть ли хоть одно совпадение в строке str.
	# Возвращает true/false. Работает так же, как и проверка str.search(reg) != -1
	# В глобальных выражениях его можно вызвать несколько  раз, при этом он меняет поле lastIndex!
	# Довольно бесполезная функция...


	regex.test(textSample)
	regex.lastIndex

	regex.test(textSample)
	regex.lastIndex

	regex.test(textSample)
	regex.lastIndex

	`,	
	
	
	
	matchall_demo1:`

	# str.matchAll(regexp) - возвращает iterator по всем совпадениям regexp (включая группы)
	# Поддерживает только глобальные выражения!
	# Самый универсальный способ поиска!

	textSample = textSample2;
	regex = /ой/ig;

	@
	const matches = textSample.matchAll(regex);
	for (const match of matches) {
		log2("match=",match,", match.index=",match.index);
	}
	@

	Array.from(textSample.matchAll(regex), (m) => m[0]);


	//Поиск с группами
	textSample = textSample4;
	regex = regexSamples[0];

	@
	const matches = textSample.matchAll(regex);
	for (const match of matches) {
		log2("match=",match,", match.index=",match.index);
	}
	@

	`,
	
	
	match_demo1:`

	# str.match(reg)
	# с флагом g - возвращает обычный массив из всех совпадений.
	# без флага g - возвращает обычный массив, содержащий первое найденное совпадение и результаты поиска групп (частей в круглых скобках).
	# При этом результат содержит доп. свойства: index – позиция обнаружения, input - строка по которой вёлся поиск
	# Остальные элементы результата содержат результаты поиска групп
	# 
	textSample = textSample2;
	
	//поиск повторяющегося паттерна
	result = textSample.match( /ой/ig );
	
	result = textSample.match( /ой/i );

	result.index;
	
	
	textSample = textSample3;

	//ищем все заглавные буквы
	textSample.match( /[A-Z]/g );

	//ищем первую заглавную букву
	result = textSample.match( /[A-Z]/ );
	
	result.index;
	result.input;
	
	textSample = "определённо javascript - это такой язык";

	//результат поиска групп (частей в круглых скобках) - будет выведен в доп элементах результата поиска
	result = textSample.match(/JAVA(SCRIPT)/i);

	result.index;

	textSample = textSample4;
	regex = regexSamples[1];
	
	result = textSample.match(regex);
	
	
	regex = regexSamples[2];
	
	result = textSample.match(regex);
	
	result.index;
	result.input;

	`,
	

	
	replace_demo1:`
	
	# str.replace(reg, str/func) – поиск и замена подстроки
	#
			
	textSample = textSample4;

	//без регулярных выражений
	textSample.replace("e","L");

	textSample.replaceAll("e","L");

	regex = regexSamples[3];

	//заменить только первый найденный результат
	textSample.replace(regex,"A");

	regex = regexSamples[4];
	
	//заменить все вхождения (делает то же что и replaceAll)
	textSample.replace(regex,"A");


	# В строке для замены можно использовать специальные символы:
	# $&	Вставляет всё найденное совпадение.
			
	//окружить все вхождения скобками
	textSample.replace(regex,"($&)");

	# $1, $2... - вхождение, соответствующее 1-й, 2-й группе внутри выражения
	# Это позволяет менять найденные вхождения местами!

	regex = regexSamples[5];
	
	//поменять первые 2 группы местами
	textSample.replace(regex,"($2 $1)");
	
	`,
	
	replace_demo2:`
	
	# str.replace(reg, str/func)
	#
	# Замена с использованием функции.
	# функция получает следующие аргументы:
	# str 	найденное совпадение,
	# p1, p2, ..., pn 	содержимое скобок (если есть),
	# offset 	позиция, на которой найдено совпадение,
	# s 	исходная строка.
	# Если скобок в регулярном выражении нет, то у функции всегда будет ровно 3 аргумента: replacer(str, offset, s)
	
	textSample = textSample2;

	//замена функцией - удвоение найденных значений
	@
	textSample.replace(/ой/gi,(str,offset)=>{ 
		return str+str;
	});
	@

	textSample = textSample4;

	regex = regexSamples[5];
	
	//замена групп функцией - меняем группы местами, окружаем скобками
	@
	textSample.replace(regex,(str,g1,g2,offset)=>{
		return "["+g2+g1+"]";
	});
	@
	
	
	`,
	
	
	exec_demo1:`
	
	# regexp.exec(str) - более неуклюжий вариант str.matchAll(regexp)
	# Если флага g нет, то возвращает первое совпадение
	# Если флаг g есть - возвращает первое совпадение и записывает в regexp.lastIndex позицию, с которой нужно возобновить поиск.
	# Последующий поиск он начнёт уже с этой позиции. Если совпадений не найдено, то сбрасывает regexp.lastIndex в ноль.
					
	textSample = textSample2;
			
	//поиск всех совпадений. Поиск должен быть глобальным, иначе возникнет бесконечный цикл.
	@
	regex = /ой/gi;
	while (result = regex.exec(textSample)) {
		log2("result=",result,", result.index=",result.index,", regexp.lastIndex=",regex.lastIndex);
	}
	@

	//поиск только первого совпадения			
	regex = /ой/i;

	@
	result = regex.exec(textSample)
	if (result){
		log2("result=",result,", result.index=",result.index);
	}
	@

	#
	//поиск с группами
	#
	textSample = textSample4;

	regex = regexSamples[5];

	result = regex.exec(textSample);
			
	result.index

	result = regex.exec(textSample);
			
	result.index
	
		
	`,
	


	testRegexp1: () => {
		testRegexp(textSample4, /(\.\d)(\.\d)/g);
		testRegexp(textSample4, /(\.\d)(\.\d)/);
	},

	testRegexp2: () => {
		testRegexp(textSample2, /ой/gi);
		testRegexp(textSample2, /ой/i);
	},
	
	
	expressions2: () => {
		
		let s = accordUtils.loadFileAsString("../js/001-demo.js");
		
		
//		testRegexp(s, /^.*le2.*$/g);
//		testRegexp(s, /\(\) *=/g);

		//ищем комментарии
		testRegexp(s, /^(\s*)(\/\/).+/gm, false);
		
		
		
	},	
	

	
	
	

}




$(() => {

	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		lfMode: false,
		selectedOption: "testRegexp1",
		initFunction: ()=>{
			
		},
		beforeExec: ()=>{
			for(re of regexSamples){
				re.lastIndex = 0;
			}
		}
		
		
	});	
	
		

});



	/*
  search_demo2: () => {

		logTextSample(textSample1);
		
		
		lc2("str.search(regexp) - возвращает позицию первого совпадения или -1, если ничего не найдено.");

//		le2("");
		


		let ind = lf2nl(() => {
			//ищем текст "le2"
			return textSample1.search(/le2/);
		});
		let s = textSample1.substring(ind, ind+20);
		logTextFragment(s);

		
		ind = lf2nl(() => {
			//первая функция
			return textSample1.search(/\(\) *=/);
		});
		s = textSample1.substring(ind, ind+20);
		logTextFragment(s);
		
		ind = lf2nl(() => {
			//первый коммент
			return textSample1.search(/ *\/\//);
		});
		s = textSample1.substring(ind, ind+20);
		logTextFragment(s);
		
		
	},
*/
