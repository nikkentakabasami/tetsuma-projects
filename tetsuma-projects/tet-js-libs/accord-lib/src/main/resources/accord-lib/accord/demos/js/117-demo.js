

let p1;

let selectorsData1 = {
		
	show(){
		p1.show();
	},
	
	hideAll(){
		p1.hide();
	},
	
	assign_for_input(){
		
		//функция showForElement - показывает AccPopup рядом с заданным элементом ввода 
		$inp1.focus(e => {
			p1.showForElement(e.target, AccPopup.Layouts.BOTTOM_RIGHT);
		});

		$inp2.click(e => {
			p1.showForElement(e.target, AccPopup.Layouts.BOTTOM);
		});

		$inp3.click(e => {
		  p1.showForElement(e.target, AccPopup.Layouts.RIGHT);
		});		
		
		
	},
	
	
	
	
}

function init(){
	//опция hideOnOutsideClick - скрывает панель при клике за её пределами
	let options = {
	contentText: "p1 - Тестовая панель.",
	draggable: true,
	width: "300px",
	hideOnOutsideClick: true
	}
	p1 = new AccPopup(options);
	
}

$(document).ready(function() {

	
	selectorsData1.show.init = init;
	
	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: TEMPLATE_FORM1,
		selectorsData: selectorsData1,
		selectedOption: null,
		title: "AccPopup - создание плавающих панелей",
		initFunction: ()=>{
			init();
		}
	});	
	
	
	
	
	//добавляем демо-кнопки
//	addDemoButtons(buttonHandlers1)
	
	
		
	
});



