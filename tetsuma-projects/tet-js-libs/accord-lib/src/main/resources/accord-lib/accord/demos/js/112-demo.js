
import { AccModalDialog } from '../../js/accord-bundle.js';

let dialog1;


let buttonHandlers1 = {
	
	showDialog1(){
		dialog1.show();
	},
	
}

buttonHandlers1.showDialog1.init = createDialog1;

function createDialog1(){
	dialog1 = new AccModalDialog({
	  title: "Диалог по умолчанию",
	  contentUrl: accordUtils.accordPath + "demos/misc/contentFragment.html",
	  fragmentLoadMode: AccModalDialog.LoadModes.FETCH,
	  autosize: true,
	  onOk: () => {
	    log("dialog1 сохранён.");
	  },
	  onCancel: () => {
	    log("dialog1 закрытие без сохранения.");
	  },
	  onInitiated: () => {
//	    dialog1.show();
	  },
	});
	
	dialog1.addEventListener(AccModalDialog.AccModalDialogEvents.onClose, e => {
	  console.log("dialog1 закрыт.");
	});
}





$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_BUTTONS,
		workPanelTemplate: null,
		selectorsData: buttonHandlers1,
		selectedOption: null,
		title: "AccModalDialog - получение содержимого по ссылке",
		initFunction: ()=>{
			createDialog1();
		}
	});	
	
});



