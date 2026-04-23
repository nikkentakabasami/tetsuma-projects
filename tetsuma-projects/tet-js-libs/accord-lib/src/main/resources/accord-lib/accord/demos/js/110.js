

import {showWaitPanel,hideWaitPanel} from '../../../accord/js/accord-bundle.js';


let buttonHandlers1 = {
	
	showWaitPanel1(){
		//показ панели ожидания без параметров
		showWaitPanel();
	},
	showWaitPanel2(){
		//показ панели ожидания с заданием заголовка
		showWaitPanel("test1");
	},
	showWaitPanel3(){
		//при подключении accord-publish.js - методы и классы библиотеки становятся глобальными.
		window.showWaitPanel("test2");
	},
	hideWaitPanel(){
		//скрыть панель
		hideWaitPanel();
	},
	
	
	
}



$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_BUTTONS,
		workPanelTemplate: null,
		selectorsData: buttonHandlers1,
		selectedOption: null,
		title: "Компонент waitPanel - панель ожидания",
		initFunction: ()=>{
			
		}
	});	
	
});
