

let s1,s2;

let n1, n2, n3, n4;

let matches;

//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {
	
	String_declaration() {
		
		//способы объявления строк
		a.s1 = "моя строка 1";
		a.s2 = 'моя строка 2';
		a.s4 = String ("моя строка 3");

		//возвращает объект String, а не примитив!
		a.s5 = new String ("моя строка 5");

		//String(x) - конвертирует аргумент в строку
		a.s10 = String(22);
		a.s11 = String(false);

		//Переноc строки
		a.s12 = "Hello \n\
			World!";

		//Template Strings - обратные кавычки.
		//позволяют выполнять перенос строк (multiline string), использовать любые кавычки
		a.s20 = `Это очень длинная строка,
				которая продолжается на следующей строке.`;

		//позволяют встраивать выражения в строку, заключая их в ${…}
		let name = "Иван";
		a.s21 = `Привет, ${name}!`;
		a.s22 = `результат: ${1 + 2}`;
		
	},

	String_escape(){
		
		//backslash escape character - Экранирование спецсимволов
		a.s1= 'It\'s alright.\\';

		a.s2 = "I'm a JavaScript \"programmer\".";
					
		//escape sequences
		a.s3= "\n-New Line,\r-Carriage Return,\t-tab";
		
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
/*
String.fromCharCode()
  создание строк из кодов UTF-16.
  Работает только с кодами в диапазоне от 0 до 65535.
  Не поддерживает эмодзи или исторические символы, у которых кодовые точки выше 0xFFFF.
*/
  
String.fromCharCode(189, 43, 190, 61);

/*
String.fromCodePoint()
  то же что и fromCharCode, но поддерживает значения выше 0xFFFF.
*/
String.fromCodePoint(189, 43, 190, 61);
String.fromCodePoint(9731, 9733, 9842, 0x2f804)

/*
str.charCodeAt(pos)
  Возвращает код символа на позиции pos (0-65535)
*/

testString1;

testString1.charCodeAt(0);

/*
str.codePointAt(pos)
  Возвращает полный код символа на позиции.
  Этот код может стостоять из двух суррогатных пар
*/

testString1.codePointAt(2);			
`,
	
	String_functions: `

//базовые функции:
testString1;
testString1.length;
testString1.toLowerCase();
testString1.toUpperCase();
testString1.trim();
testString1.trimEnd();
testString1.trimStart();

/*
str.charAt(ind)
  Получение символа по индексу.
  В JavaScript нет типа «символ», так что charAt возвращает строку.
  Символ так же можно получить квадратными скобками, как в массиве.

str.at(ind)
  Аналог charAt, но поддерживает отрицательный индекс.
  (-1 - последний символ)
  
*/

"abcd".charAt(1);
"abcd"[1];
"abcd".at(-1);
"abcd".at(-3);

/*
str.substring(start, end)
  получение подстроки

str.slice(start, end)
  аналог substring, но можно задавать индексы от конца строки (-1 - последний символ)
*/
testString1.substring(5,10);

testString1.slice(-5);  //5 последних символов
testString1.slice(1,-1);  //убрать первый и последний символы

/*
str.split(separator/regexp, limit)
  разбиение строки (можно и регулярным выражением)
*/
"a b c d".split(' ',3);
testString1.split(/ to /,3);

/*
str.concat(...o)
  склеивание строк
*/
'ae'.concat(true,'-',66);

/*
string.isWellFormed()
  строка не содержит некорректных последовательностей
  
str.toWellFormed()
  устраняет некорректные последовательности символов Unicode

*/
s1 = "Hello World \uD800";

s1.isWellFormed();
s1 = s1.toWellFormed();
s1.isWellFormed();

/*
str.padStart(targetLength, padString)
str.padEnd(targetLength, padString)
  дополнят строку до нужной длины повтором заданной строки
  
str.repeat(count)
  дублирует строку заданное число раз.
*/
"hi".padStart(10,'*');
"hi".padEnd(10,'*#');
"hi".repeat(5);

`,	
	

String_functions_search: `

testString1;

# str.indexOf(val, fromIndex)
testString1.indexOf('to');

testString1.indexOf('to',18);


# str.lastIndexOf(val, fromIndex)
testString1.lastIndexOf('to');

/*
str.search(regexp)
возвращает позицию первого совпадения или -1
Аналог indexOf(str), но с регулярным выражением.
*/

testString1.search(/(to )\\w+/);

/*
str.match(regexp)
  с флагом g - возвращает обычный массив из всех совпадений.
  без флага g - возвращает первое совпадение (массив вида [result, group1, group2...] с атрибутом index)
*/

testString1.match(/(to )\\w+/);

testString1.match(/(to )\\w+/g);

//слова, перед которыми пробел
testString1.match(/(?<= )\\w+/g);


/*
str.matchAll(regexp)
  возвращает iterator по всем совпадениям regexp (включая группы)
  Поддерживает только глобальные выражения!
*/
@
matches = testString1.matchAll(/(to )\\w+/g);
for (const match of matches) {
  log2("match=",match,", match.index=",match.index);
}
@!

//получить данные массивом
@
matches = testString1.matchAll(/(?<= )\\w+/g);
Array.from(matches, m => m.index);
@

/*
str.replace(val/regexp, newVal)
  Замена одного значения.
  searchValue - строка или RegExp
  Если RegExp глобальный - заменит все значения.
*/

testString1.replace('to','AA');
testString1.replace(/to/g,'AA');

/*
str.replaceAll(val/regexp, newVal/func)
  Замена всех значений.
  searchValue - строка или глобальный RegExp
  newValue - может быть функцией.
*/

testString1.replaceAll('to','AA');
testString1.replaceAll(/to/g,'AA');
testString1.replaceAll('to',x=>x.toUpperCase());

`,	

	
}



function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
    selectedOption: "String_functions",
    debugMode: false,
		logObjectsAsJson: false,
    initFunction: () => {
    }
	
  };
}





