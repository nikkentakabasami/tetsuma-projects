
let $myPopup;

let r;

//accordUtils - dom методы
let buttonHandlers1 = {
	

	

	decorInput(){
		
		//декорирует input, добавляя к нему кнопку (впереди или позади) с заданной иконкой.
		accordUtils.decorInput($inp1);

		accordUtils.decorInput($inp2,{
			addButton: true,
			buttonClasses: "acc-btn-check",
			placeButtonBefore: false,
			buttonHandler: e=>{
				log2("inp2 click!");
			}
		});
		
		
	},		
	
	test4_alignToCenter(){
		//alignToCenter($panel) - Располагает панель с абсолютным позиционированием по центру браузера	
		
		$myPopup.css("display","flex");
		accordUtils.alignToCenter($myPopup);
	},	


	escapeHTML(){
		let optionsHtml = $("#selectors1").html();
		
		//экранирует спецсимволы в тексте (для вставки его в html)
		optionsHtml = accordUtils.escapeHTML(optionsHtml);
		
		$workPanel.text(optionsHtml);
	},

	hiddenContainer(){
		
		//невидимое хранилище для разных скрытых элементов
		r = accordUtils.getHiddenContainer();
		r.text("test");
		$workPanel.text(r.prop("outerHTML"));
		
	},

	addCssFile(){
		//динамическое подключение css файла
		accordUtils.addCssFile('misc/test.css');
				
	},	
	

	formToJSON:`
		$inp1.val("111"); !
		$inp2.val("123"); !
		
		$form = $("#testForm1"); !
		
		//возвращает данные из формы в виде json-объекта.
		accordUtils.formToJSON($form); ~
		
	`,

	calcElementPosition:`
		//вычисляет положение заданного тега в окне
		accordUtils.calcElementPosition($inp1); ~
		
	`,
	
	

	
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
		selectedOption: "decorInput",
		title: "accordUtils - dom методы",
		initFunction: ()=>{
			popupInit();
			
		}
	});		
	
	
		
	
});





