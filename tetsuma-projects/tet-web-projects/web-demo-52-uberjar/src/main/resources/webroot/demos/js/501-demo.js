

let testString = "  Seishun tte a to iu ma to iu koto.";
let testString2;

let n1, n2, n3, n4;


//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {
	
	String_declaration() {
		
		//способы объявления строк
		a.s1 = "моя строка 1";
		a.s2 = 'моя строка 2';
		a.s4 = String ("моя строка 3");

		//возвращает объект String, а не примитив!
		a.s5 = new String ("моя строка 3");

		//String(x) - конвертирует аргумент в строку
		a.s5 = String(22);
		a.s6 = String(false);

		//Переноc строки
		a.s7 = "Hello \n\
			World!";

		//Template Strings - обратные кавычки.
		//позволяют выполнять перенос строк (multiline string), использовать любые кавычки
		a.s11 = `Это очень длинная строка,
				которая продолжается на следующей строке.`;

		//позволяют встраивать выражения в строку, заключая их в ${…}
		let name = "Иван";
		a.s12 = `Привет, ${name}!`;
		a.s13 = `результат: ${1 + 2}`;
		
	},

	String_escape(){
		
		//backslash escape character - Экранирование спецсимволов
		a.s10= 'It\'s alright.\\';

		a.s11 = "I'm a JavaScript \"programmer\".";
					
		//escape sequences
		a.s12= "\n-New Line,\r-Carriage Return,\t-tab";
		
	},
	
	
	String_encoding(){
		
		//  \uNNNN - Символ в кодировке Юникод.
		a.s1 = "\u00A9";

		//	\xXX	Символ с шестнадцатеричным юникодным кодом.
		a.s2 = "\x7A";

		// \u{X…XXXXXX} Нотация для указания всех возможных символов юникода
		a.s3 = "\u{1F60D}";

		//Для указания символов с диакретическими знаками - указывается символ а затем сами знаки.
		a.s4 = "S\u0307";	//символ S и знак "точка сверху"
		a.s5 = "S\u0307\u0323";

	},
	
	String_encoding_func: `
		//String.fromCharCode() - создание строк из кодов UTF-16.
		//Работает только с кодами в диапазоне от 0 до 65535.
		//Не поддерживает эмодзи или исторические символы, у которых кодовые точки выше 0xFFFF.
		String.fromCharCode(189, 43, 190, 61);

		//String.fromCodePoint() - то же что и fromCharCode, но поддерживает значения выше 0xFFFF.
		String.fromCodePoint(189, 43, 190, 61);
		String.fromCodePoint(9731, 9733, 9842, 0x2f804)

		//str.charCodeAt(pos) - Возвращает код символа на позиции pos (0-65535)
		testString.charCodeAt(0);

		//str.codePointAt(pos) - Возвращает полный код символа на позиции.
		//Этот код может стостоять из двух суррогатных пар
		testString.codePointAt(0);			
	`,
	
	
	String_functions: `
		testString;
		testString.length;
				
		//str.charAt(ind) - Получение символа по индексу.
		//В JavaScript нет отдельного типа «символ», так что charAt возвращает строку, состоящую из выбранного символа.
		//Символ так же можно получить квадратными скобками, как в массиве.
		testString.charAt(5);
		testString[5];

		//str.at(ind) - Аналог charAt, но поддерживает отрицательный индекс.
		//(-1 - последний символ)
		testString.at(-1);
		testString.at(-5);
						
		testString.toLowerCase();
		testString.toUpperCase();
				
		//str.indexOf(searchValue, fromIndex)
		testString.indexOf('to');
				
		//str.lastIndexOf(searchValue, fromIndex)
		testString.lastIndexOf('to');
				
		testString.substring(5,10);
				
		//str.slice(start, end) - аналог substring, но удобнее.
		//Отличие: Отрицательные значения отсчитываются от конца строки (-1 - последний символ)
		testString.slice(-5);  //5 последних символов
		testString.slice(1,-1);  //убрать первый и последний символы
				
		testString.replace('to','AA');
		testString.replaceAll('to','AA');
		
		//Чтобы заменить все значения - можно использовать регулярные выражения
		testString.replace(/to/g,'AA');
				
		//str.match(re) - поиск регулярным выражением.
		//Возвращает массив найденных значений
		testString.match(/(?<= )\\w+/g);  //слова, перед которыми пробел
				
		//str.split(separator, limit) - разбиение строки
		testString.split(' ',3);
				
		//str.concat(...o) - склеивание строк
		'ae'.concat(true,'-',66);
				
		testString.trim();
		testString.trimEnd();
		testString.trimStart();
				
		//str.toWellFormed() - устраняет некорректные последовательности символов Unicode
		testString2 = "Hello World \uD800";
		
		testString2.isWellFormed();
		testString2 = testString2.toWellFormed();
		testString2.isWellFormed();
				
		//padStart(targetLength, padString), padEnd(...) - дополнят строку до нужной длины повтором заданной строки
		testString.padStart(40,'*');
		testString.padEnd(40,'*#');
		'hello_'.repeat(5);
		
	`,
	
	
}



$(() => {

	
	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP,
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
//		selectedOption: "demo1_script",
		lfMode: true,
		autoscrollLog2: false,
		initFunction(){
			
		},
	});		
	
	
});






