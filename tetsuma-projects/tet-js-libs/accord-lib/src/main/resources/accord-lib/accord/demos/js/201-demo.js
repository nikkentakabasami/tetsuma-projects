

//accordUtils - ajax методы
let buttonHandlers1 = {
	
	test2_loadHtmlFragmentXHR(){
		
		//loadHtmlFragmentXHR(fragmentUrl, $target, relativeToAccord = false)
		//загружает html-фрагмент в заданный контейнер ($target) или body (если контейнер не задан)
		//использует для этого объект XMLHttpRequest (считается устаревшим способом).
		//Загружает синхронно.
		
		accordUtils.loadHtmlFragmentXHR("demos/misc/testFragment.html",".workPanel",true);
		log("loadHtmlFragmentXHR finished");
		
		
	},
	test3_loadHtmlFragmentFetch(){
		
		//loadHtmlFragmentFetch(fragmentUrl, $target, relativeToAccord = false) 
		//загружает html-фрагмент в заданный контейнер ($target) или body (если контейнер не задан)
		//использует для этого метод fetch(url).
		//Возвращает promise, так что загружать можно синхронно или асинхронно.
		accordUtils.loadHtmlFragmentFetch("demos/misc/testFragment.html",".workPanel",true)
		.then(result => {
			log("loadHtmlFragmentFetch finished");
		});
	},
	
	loadFileAsString(){
		//синхронная загрузка файла в виде строки
		let cssCode = accordUtils.loadFileAsString('misc/test.css');
		log2(cssCode);
	},

	openDownloadUrl(){
		//запуск скачивания файла
		//Скачиваться будет только если на сервере задан заголовок:
		//Content-Disposition: attachment; filename="testFragment.html"
		accordUtils.openDownloadUrl('misc/test.css');
	},	
	

	
}



$(document).ready(function() {

	
	
	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: TEMPLATE_FORM1,
		selectorsData: buttonHandlers1,
		selectedOption: "test2_loadHtmlFragmentXHR",
		title: "accordUtils - ajax методы",
		initFunction: ()=>{
		}
	});		
	
	
		
	
});





