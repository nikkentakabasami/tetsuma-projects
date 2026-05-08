
let v1,v2,v3;

let selectorsData1 = {
	
	
	location(){
		
		//# window.location
		
		
		//# Атрибуты:

		//Полный URL текущей страницы
		location.href

		//части пути
		location.protocol	//часть с протоколом:	"http:"
		location.port		//порт		"8080"
		location.host		//часть с хостом	"localhost:8080"
		location.pathname	//Всё после хоста	"/mytestartefactId/01_window.html"
		location.search		//Параметры поиска	"?p1=123"
		location.hash		//Хэш		"#sometag"

		//# При изменении любых свойств window.location, кроме hash, документ будет перезагружен, 
		//# как если бы был вызван метод window.location.assign().

		//Перезагрузить страницу без параметров:
		//location.href = location.pathname;
		
		
	},
	

	location3(){

		//# Методы
		//# 
		//# assign(url)
		//#   загрузить документ по данному url

		//location.assign("http://api.github.com/zen");
		

		//# reload()
		//#   Перезагрузить документ по текущему URL.

		//location.reload();

		//# replace(url)
		//#   Заменить текущий документ на документ по указанному url (редирект). 
		//#   В отличии от assign() - страница не записывается в истории посещений. 

		//редирект с задержкой
		//setTimeout('location.replace("http://api.github.com/zen")', 3000);
		
	},
	
	location_addParams(){
		
		//# $.param(obj) - метод для сериализации объекта или массива в строку параметров
		
		let params = {
			category: 2,
			name: "My name."
		};
		let paramString = $.param(params);
		
		log2(paramString);
		
		//Перезагрузить страницу с параметрами:
		location.href = location.pathname + '?' + paramString;
		
	},
	
		
	location_showParams(){
		
		log2(accordUtils.getRequestParameter);
		log2nl();
		
		
		le2(`
			# функция для получения параметров запроса
			#
			accordUtils.getRequestParameter("category");
			accordUtils.getRequestParameter("name");
			
			`)
			
		
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
		//		lfMode: true,
		selectedOption: "encodeURI",
		initFunction: ()=>{
			
		}
	});	
	
});







