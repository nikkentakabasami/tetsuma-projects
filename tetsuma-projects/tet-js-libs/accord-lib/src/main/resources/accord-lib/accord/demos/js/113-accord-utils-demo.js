
let $myPopup;

//демо-функции.
//при вызове addDemoButtons - для каждой такой функции будет создана и добавлена демо-кнопка (на acc-button-panel)
let buttonHandlers1 = {
	
	test1_decorInput(){
		
		
		//decorInput - декорирует input, добавляя к нему кнопку (впереди или позади) с заданной иконкой.
		accordUtils.decorInput($inp1);
	
		accordUtils.decorInput($inp2,{
			addButton: true,
			buttonClasses: "acc-btn-check",
			placeButtonBefore: false,
			buttonHandler: e=>{
				log2("hello");
			}
		});
	},
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
	test4_alignToCenter(){
		//alignToCenter($panel) - Располагает панель с абсолютным позиционированием по центру браузера	
		
		$myPopup.css("display","flex");
		accordUtils.alignToCenter($myPopup);
	},	


	
}

buttonHandlers1.test4_alignToCenter.init = popupInit;

function popupInit(){
	
	$myPopup = $('<div class="acc-popup-default no-select acc-popup">Плавающая панель.</div>'); 
	$myPopup.css("display","none");
	$myPopup.appendTo(document.body);
	
	$myPopup.dblclick(e=>{
		$myPopup.css("display","none");
	});
	
}


$(document).ready(function() {

	
	
	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: TEMPLATE_FORM1,
		selectorsData: buttonHandlers1,
		selectedOption: "test1_decorInput",
		title: "accordUtils - содержит разнообразные вспомогательные методы",
		initFunction: ()=>{
			popupInit();
			
		}
	});		
	
	
		
	
});





