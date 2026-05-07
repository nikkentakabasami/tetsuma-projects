

let collator1,collator2, date1;
let formatter1,formatter2,formatter3;
let loc1;

const fruits = ['яблоко', 'БАНАН', 'груша', 'банан', 'Апельсин', 'ананас', 'ЁЛКА', 'ёлка', 'ель'];


let selectorsData1 = {
	
	intl:`
	

	# Intl
	#  является пространством имён для API интернационализации
	#  Содержит конструкторы, которые принимают 2 параметра: locales, options
	# 
	# 
	# locales - обычно это: "ru", "en-US"
	# Локаль по умолчанию задаётся в атрибуте html.lang:
	# 
	// Получаем язык страницы из html.lang
	loc1 = document.documentElement.lang || 'en-US'	

	// Используем предпочтительный язык пользователя
	loc1 = navigator.language || 'ja-JP';
	# 
	# options.localeMatcher
	#   способ подбора локали, если желаемая недоступна.
	#  Значения: 
	#  "best fit" – (default)использует встроенные алгоритмы и предпочтения браузера
	# "lookup" – означает простейший порядок поиска путём обрезания суффикса
	
	
	`,
	
	intl_Collator:`

	# Intl.Collator([locales, [options]])
	#   Конструктор сортировщиков — объектов, включающих сравнение строк.
	# 
	# Опции:
	# 
	# options.sensitivity
	#   чувствительность: какие различия в символах учитывать
	# variant – (default)учитывать всё
	# base – учитывать только разные символы, без диакритических знаков и регистра, например: а ≠ б, е = ё, а = А.
	# accent – учитывать символы и диакритические знаки, например: а ≠ б, е ≠ ё, а = А.
	# case – учитывать символы и регистр, например: а ≠ б, е = ё, а ≠ А.
	# 
	# options.ignorePunctuation
	#   игнорировать знаки пунктуации
	#   default: false;
	# 
	# options.numeric
	#   использовать ли численное сравнение.
	#   если true, то будет 12 > 2, иначе 12 < 2.
	# 
	# options.caseFirst
	#   в сортировке должны идти первыми прописные или строчные буквы
	#   "upper" - прописные
	#   "lower" - строчные
	#   "false" - (default) стандартное для локали	
	# 
	collator1 = new Intl.Collator(); !
	collator2 = new Intl.Collator(undefined, {sensitivity: "accent"}); !
	
	collator1.compare("ЁжиК", "ёжик");  // 1, больше
	collator2.compare("ЁжиК", "ёжик");  // 0, равно
	

	// Сортируем массив, используя Collator
	collator1 = new Intl.Collator("ru", {sensitivity: "base"}); !
	collator2 = new Intl.Collator("ru", {sensitivity: "case"}); !
	
	fruits.sort((a, b) => collator1.compare(a, b));
	fruits.sort((a, b) => collator2.compare(a, b));
	
		
	`,
	
	intl_DateTimeFormat:`
	
	# Intl.DateTimeFormat([locales, [options]])
	#   Конструктор форматера даты и времени.
	#

	@
	date1 = new Date(2014, 11, 31, 12, 30, 0);

	formatter1 = new Intl.DateTimeFormat("ru");
	
	formatter2 = new Intl.DateTimeFormat("en-US");
	
	formatter3 = new Intl.DateTimeFormat("ru", {
	  weekday: "long",
	  year: "numeric",
	  month: "long",
	  day: "numeric"
	});
	@
		
	formatter1.format(date1);
	formatter1.format(new Date());
	
	formatter2.format(date1);
	formatter3.format(date1);
	
	`,
	
	intl_DateTimeFormat2:`
	
	//Простейшие форматтеры: выводить в числовом формате дату и время

	@
	date1 = new Date();
	
	formatter1 = new Intl.DateTimeFormat("ru", {
	  hour: "numeric",
	  minute: "numeric",
	  second: "numeric"
	});

	formatter2 = new Intl.DateTimeFormat("ru", {
	  year: "numeric",
	  month: "numeric",
	  day: "numeric",
	  hour: "numeric",
	  minute: "numeric",
	  second: "numeric"
	});
	@
	
	formatter1.format(date1);
	formatter2.format(date1);
	
	`,
	
	intl_DateTimeFormat3:`

	//Месяц в длинном формате

	@
	date1 = new Date();

	formatter1 = new Intl.DateTimeFormat("ru", {
	  year: "numeric",
	  month: "long",
	  day: "numeric",
	  hour: "numeric",
	  minute: "numeric",
	  second: "numeric"
	});
	@

	formatter1.format(date1);

	`,	
	
	intl_PluralRules:`
	
	# Intl.PluralRules: работа с множественным числом
	
	formatter1 = new Intl.PluralRules('en-US');

	formatter1.select(0);
	formatter1.select(1);
	formatter1.select(2);
	formatter1.select(10);
	formatter1.select(100);

	formatter1 = new Intl.PluralRules('ru');

	formatter1.select(0);
	formatter1.select(1);
	formatter1.select(2);
	formatter1.select(10);
	formatter1.select(100);
		
	
	`,	
	
	DisplayNames:`
	
	# Intl.DisplayNames: локализованные названия для любых объектов
	#
	// Отображение названий языков
	formatter1 = new Intl.DisplayNames(['en'], { type: 'language' }); !
	formatter1.of('en');
	formatter1.of('fr');
	formatter1.of('es-MX');
	formatter1.of('zh-Hans');
	
	formatter1 = new Intl.DisplayNames(['ru'], { type: 'language' }); !
	formatter1.of('en');
	formatter1.of('fr');
	formatter1.of('es-MX');
	formatter1.of('zh-Hans');

	formatter1 = new Intl.DisplayNames(['fr'], { type: 'language' }); !

	formatter1.of('en');
	formatter1.of('fr');
	formatter1.of('es-MX');
	formatter1.of('zh-Hans');
	
	// Отображение названий регионов
	formatter1 = new Intl.DisplayNames(['ru'], { type: 'region' }); !

	formatter1.of('US');
	formatter1.of('DE');

	// Отображение названий систем письма
	formatter1 = new Intl.DisplayNames(['en'], { type: 'script' }); !
	
	formatter1.of('Latn');
	formatter1.of('Arab');
	
	
	
	`,	
	
	
	
	intl_NumberFormat:`
	
	# Intl.NumberFormat([locales[, options]])
	#   Конструктор форматера чисел
	# 
	# Опции:
	# 
	# minimumIntegerDigits	default: 21
	# minimumFractionDigits
	# maximumFractionDigits
	# minimumSignificantDigits
	# maximumSignificantDigits
	
	@
	formatter1 = new Intl.NumberFormat("ru");
	
	  //для валюты
	formatter2 = new Intl.NumberFormat("ru", {
	 style: "currency",
	  currency: "GBP",
	  minimumFractionDigits: 2
	});
	
	formatter3 = new Intl.NumberFormat("ru", {
	  minimumFractionDigits: 2,
  	  maximumFractionDigits: 6
	});
	
	@	
	
	formatter1.format(1234567890.123);

	formatter2.format(1234.5);	  

	formatter3.format(Math.PI);	  
	
	`,
	
	intl_NumberFormat2:`
	
	new Intl.NumberFormat('en-US', { style: 'unit', unit: 'meter', unitDisplay: 'long' }).format(100);

	new Intl.NumberFormat(undefined, { style: 'unit', unit: 'meter', unitDisplay: 'long' }).format(100);
	
	
	
	`,
	
	intl_ListFormat:`
	
	# Intl.ListFormat
	#   перечисления на естественном языке
	
	
	// Список с союзом "и" (conjunction)
	new Intl.ListFormat('en-US', { type: 'conjunction' }).format(fruits);

	new Intl.ListFormat('ru', { type: 'conjunction' }).format(fruits);

	// Список с союзом "или" (disjunction)
	new Intl.ListFormat('en-US', { type: 'disjunction' }).format(fruits);

	new Intl.ListFormat('ru', { type: 'disjunction' }).format(fruits);
	
	`,
	
	
	embedded:`

	# Методы форматирования также поддерживаются в обычных строках, датах, числах:
	# 
	# String.localeCompare(that [, locales [, options]])
	# Date.toLocaleString([locales [, options]])
	# Number.toLocaleString([locales [, options]])
		
	"ёжик".localeCompare("яблоко", "ru");	

	date1 = new Date(); !
	date1.toLocaleString("ru", { year: 'numeric', month: 'numeric', day: "numeric" }); 
	
	Math.PI.toLocaleString(	"ru", {maximumFractionDigits: 3});
	
	`,
	
	
	
	
	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		//		lfMode: true,
		selectedOption: "DisplayNames",
		initFunction: ()=>{
			
		}
	});	
	
});







