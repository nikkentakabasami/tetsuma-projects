
let v1,v2,v3;

let selectorsData1 = {
	
	location_attributes:`
	
	# window.location
	# Атрибуты:
	#

	location.href;			//Полный URL текущей страницы
	location.protocol		//часть с протоколом
	location.port;
	location.host;
	location.pathname;		//Всё после хоста
	location.search;		//Параметры поиска
	location.hash;			//Хэш		"#sometag"
	
	`,
	location_change_attributes:`
	# При изменении любых свойств window.location, кроме hash, документ будет перезагружен, 
	# как если бы был вызван метод window.location.assign().

	location.search = "?a=11&b=12"	

	//Перезагрузить страницу без параметров:
	//location.href = location.pathname;
	`,
	location_methods:`
	# Методы
	# 
	# assign(url)
	#   загрузить документ по данному url
	//location.assign(testUrl);

	# 
	# reload()
	#   Перезагрузить документ по текущему URL.
	//location.reload();

	# 
	# replace(url)
	#   Заменить текущий документ на документ по указанному url (редирект). 
	#   В отличии от assign() - страница не записывается в истории посещений. 
	//редирект с задержкой
	//setTimeout('location.replace("http:api.github.com/zen")', 3000);
	
	`,
	
	location_addParams(){
		
		//# $.param(obj) - метод для сериализации объекта или массива в строку параметров
		
		let params = {
			category: 2,
			name: "My name."
		};
		let paramString = $.param(params);
		
		log2(paramString);
		
		//Перезагрузить страницу с параметрами:
		//location.href = location.pathname + '?' + paramString;
		//location.search = '?' + paramString;	
		
	},
	
		
	getRequestParameter:`
	
	# функция для получения параметров запроса
	accordUtils.getRequestParameter;
	#
	
	accordUtils.getRequestParameter("category");
	accordUtils.getRequestParameter("name");
	`,
	
	
	url1:`

	# URL
	#   Встроенный классдля создания и разбора URL-адресов.
	#   
	# new URL(url, [base])
	#   base – необязательный базовый URL (protocol+host)
	#		

	url1 = new URL(testUrl);
	url2 = new URL("testPath?query=satori#sss", testUrl);
		
	url2.protocol;
	url2.host;

	url2.hostname;
	url2.port;

	url2.pathname;

	url2.href;		//полный URL-адрес
	url2.search;	//строка параметров, начинается с вопросительного знака ?
	url2.hash;		//начинается с символа #

	 

	 	



	`,
	url2:`

	# url.searchParams – объект типа URLSearchParams.
	#   предоставляет удобные методы для работы с параметрами:
	#   
	#   при задании параметров - они автоматически кодируются.
	#   Для кодирования так же используются функции: encodeURI, encodeURIComponent
	# 
	# методы:  
	# append(name, value) – добавить параметр по имени,
	# delete(name) – удалить параметр по имени,
	# get(name) – получить параметр по имени,
	# getAll(name) – получить все параметры с одинаковым именем name
	# has(name) – проверить наличие параметра
	# set(name, value) – задать/заменить параметр,
	# 


	url1 = new URL(testUrl);
	url1.searchParams.set('q', 'test me!');
	url1.searchParams.set('tbs', 'qdr:y');

	url1;

	@
	// перебрать параметры
	for(let [name, value] of url1.searchParams) {
	  log2(name+" - "+value);
	}
	@!	

	`,


	url3(){
		//URL.createObjectURL(blob)
		// создаёт url-ссылку на Blob-объект

		blob1 = new Blob(["Hello, world!"], {type: 'text/plain'});
		let href = URL.createObjectURL(blob1);
		
		log2(href);
				
		
		//URL.revokeObjectURL(url)
		//  удаляет внутреннюю ссылку на объект, что позволяет удалить его сборщику мусора		
		
		URL.revokeObjectURL(href);
		
		
	},
	
	
	
	encodeURI:`
	
	# encodeURI(val)
	#   Кодирует URI, заменяя определённые символы их UTF-8 кодировками вида %XX 
	#   Часто один символ кодируется несколькими кодами.
	#
	# decodeURI(val)
	#   декодирует URI, созданный через encodeURI()
	#   обрабатывает последовательности вида %XX
	#

	# Не кодирует символы:
	# A–Z a–z 0–9 - _ . ! ~ * ' ( )
	# ; / ? : @ & = + $ , #	

	  v1 = encodeURI("http://localhost:8090/demo-52/?x=this is хорошо!");
	  
	  v2 = decodeURI(v1);
	
	  encodeURI("@me & ;you!");
	
	`,	
	
	
	encodeURIComponent:`

	# encodeURIComponent(val)
	#   Кодирует заданную строку так, чтобы её можно было использовать в URI
	#   Кодирует больше символов чем encodeURI()
	  
	#
	# Не кодирует символы:
	# A–Z a–z 0–9 - _ . ! ~ * ' ( )
	#
	# Кодирует символы:
	# ; / ? : @ & = + $ , #	

	encodeURIComponent("test?");
	encodeURIComponent("я и ты!");
	
	encodeURIComponent("@me & ;you!");
	
	

	`,	
	
	decodeURIComponent:`

	# decodeURIComponent(val)
	#   Декодирует параметр из URI
	#

	decodeURIComponent("%3Fx%3Dtest");
	decodeURIComponent("%D1%8F%20%D0%B8%20%D1%82%D1%8B!");

	
	


	`,		
	
	
	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		lfMode: true,
		selectedOption: "location_addParams",
		initFunction: ()=>{
			
		}
	});	
	
});







