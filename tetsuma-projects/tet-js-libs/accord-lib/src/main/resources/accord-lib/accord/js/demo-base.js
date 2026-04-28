/**
 * Функционал, использующийся для создания демок, тестирующих js.
 * 
 * Основные функции:
 * logCurrentFunc - вывод currentFunc в лог1 
 * highlightJquery(val) - выделяет объекты с заданным селектором красной рамкой
 * reloadSandbox() - очищает .workPanel и загружает в неё элементы из #template1
 * execDemoFunc() - выполняет currentFunc
 * initDemoCodeSelect(selector, data) - инициализация селекта с демками
 * addDemoButtons(handlers, panelSelector = ".acc-button-panel") - добавляет набор демо кнопок на панель
 * initDemo()
 * initBriefDemo(options) - инициализация лаконичного демо
 * parseMainSelectorsData(sd) - если демки заданы строкой - он разбивает её на 2 массива
 * 
 */


//вспомогательная переменная
let result;

let mainJsHref = null;


//показывается ли сейчас auxPanel
let showAux = true;

//кнопка показа/скрытия auxPanel
let $hideAuxButton;


//выбранная демо функция/код/селектор
let currentFunc = null;

//popup с исходниками страницы
let helpPopup;

//песочница
let $workPanel;

//элементы песочницы - вспомогательные переменные
let $btn1, $btn2, $inp1, $inp2, $inp3, $inp4, $testBtn1, $testBtn2;
let formDiv1, formDiv2;
let $form1, $form2, $formPanel;

//селекты с демками
let $sel1, $sel2, $sel3, $sel4;

//первый селект, обслуживающий демки
let $mainSelect = null;

//данные первого селекта
let mainData = null;

//комменты, привязанные к mainData
let mainDataComments = null;

//кнопка выполнения кода
let $bExecute;


//input с текущим селектором ()
let $selectorText;


//демо-кнопки
let demoButtons = [];

//опции, определяющий, как будет работать текущая демка
let demoOptions = {

    //выполняются до и после выполнения currentFunc/демо-кнопок
    beforeExec: null,
    afterExec: null,

    //функция инициализации всего кода: выводить её при нажатии каждой демо-кнопки
    initFunction: null,
	
	//функция, выполняющаяся после reloadSandbox
	afterSandboxReload: null,

	//режим тестирования jquery функций (входные данные - это массив селекторов и jquery-запросов)
	jquerySelectorsMode: false,
	
	
};

//Содержит ли #template1 - используется ли песочница
let hasSandbox = false;

let greenSpan = '<span class="green"></span>';
let boldTag = "<b></b>";
let testHtmlSnippet = "<b>appended text</b>";
let greenBorderDivSnippet = '<div class="green-border"></div>';



//счётчик для добавления новых демо-кнопок
let newButtonNo = 1;


//возвращает ссылку на главный js-файл этой демки
function findMainJs() {

    const scripts = document.querySelectorAll('script[src]');


    const jsFiles = Array.from(scripts).forEach(script => {
        let src = script.src;
        if (!src.endsWith("-demo.js")) {
            return;
        }
        mainJsHref = src;
    });

    console.log("mainJs=" + mainJsHref);
    return mainJsHref;
}



//добавляет в демку недостающие доп. элементы
function addTitlePanelButtons() {

    let $tp = $(".titlePanel");

    if (!$tp.children("#hideAuxButton").length) {
        $tp.append('<button id="hideAuxButton" type="button" class="acc-btn">Скрыть описание</button>');
    }

    if (!$("#bClearLog").length) {
        $tp.append('<button id="bClearLog" type="button" class="acc-btn">Очистить логи</button>');
    }

    if (!$("#bReload").length && $("#template1").length) {
        $tp.append('<button id="bReload" type="button" class="acc-btn">Перезагрузить песочницу (0)</button>');
    }

    if (!$tp.children("a").length) {
        $tp.append('<a href="#" target="source">Исходники (F1)</a>');
    }



}




//вывод currentFunc в лог1 
function logCurrentFunc(){
	if (!currentFunc){
		return;
	}
	
	clearLog1();
	
	if (typeof currentFunc == "string") {
	    currentFunc = accordUtils.removeOddIndent(currentFunc);
	    log(currentFunc);
	} else {
	    let code = accordUtils.funcToString(currentFunc, true);
	    log(code);
		
		let initFunction = currentFunc.init || demoOptions.currentFunc;

		//указана доп. функция инициализации - вывести её в лог
		if (initFunction) {
		    log();
		    lognl("//функция инициализации:");
			
			let code = accordUtils.funcToString(initFunction, true);
			log(code);
		}
	}
	highlightLogComments1();	
	
}


function elementToString(el){
	let r = "";
	if (el.nodeName){
		r = el.nodeName.toLowerCase()+" ";
	}
	
	if (el.id){
		r+="#"+el.id+" ";
	}
	if (el.className){
		let cl = el.className.replace(/ +/g,".")
		r+="."+cl+" ";
	}
	return r;
}



//используется в jquerySelectorsMode
//выделяет объекты с заданным селектором красной рамкой
//выводит в лог значение выражения (или число найденных элементов)
function highlightJquery(val) {
    if (!val) {
        return;
    }

	reloadSandbox();

	clearLog2();
    log2(val);
	
	if (demoOptions.beforeExec) {
	    demoOptions.beforeExec();
	}
	
    if (val.indexOf("$") >= 0 && val.indexOf("$=") < 0) {

        val = eval(val);
        if (!val.jquery) {
            log(val);
            return;
        }

    } else {
        val = $(val);
    }

	
	log2();
    logVal2("elements found: ", val.length);
	log2();
	val.each((ind,el)=>{
	  log2(elementToString(el));
	});
	
	val.addClass("red-border");

	if (demoOptions.afterExec) {
	    demoOptions.afterExec();
	}
	

}

//очищает .workPanel и загружает в неё элементы из #template1
function reloadSandbox() {

	$workPanel.empty();
	if (!hasSandbox){
		return;
	}
	

    let $sandboxPanels = accordUtils.cloneTemplate("#template1");
    $sandboxPanels.appendTo($workPanel);


    $btn1 = $("#btn1");
    $btn2 = $("#btn2");
    $inp1 = $("#inp1");
    $inp2 = $("#inp2");
    $inp3 = $("#inp3");
    $inp4 = $("#inp4");

    $testBtn1 = $("#testBtn1");
    $testBtn2 = $("#testBtn2");

    $formDiv1 = $("#formDiv1");
    $formDiv2 = $("#formDiv2");

    $form1 = $("#form1");
    $form2 = $("#form2");

	$formPanel = $(".form-panel");
	
	
//    $panel1 = $("#formDiv1");
//    $panel2 = $("#formDiv2");


    if (demoOptions.afterSandboxReload) {
        demoOptions.afterSandboxReload();
    }


}



//выполняет currentFunc
function execDemoFunc() {
    if (!currentFunc) {
        return;
    }

	if (demoOptions.jquerySelectorsMode) {
		let val = $selectorText.val();
	    highlightJquery(val);
		return;
	}
	
	if (demoOptions.beforeExec) {
	    demoOptions.beforeExec();
	}
	
    $(".workPanel *").removeClass("red-border");

    clearLog2();

    if (typeof currentFunc == "string") {
        le2(currentFunc);
    } else {

        let r = null;
        try {
            r = currentFunc();

            let logMess = '\nexecuted. ';
            if (r && r.jquery) {
                r.addClass("red-border");
                logMess += "elements found: " + r.length;
            }
            log2(logMess);

        } catch (error) {
            log("Error:", error.message);
            console.error(error.stack);
        }

    }

	if (demoOptions.afterExec) {
	    demoOptions.afterExec();
	}
	
    highlightLogComments2();

}

//инициализация селекта с демками
function initDemoCodeSelect(selector = "selectors1", data = null) {

	if (!data){
		
		if (typeof selectorsData1 !=undefined){
			data = selectorsData1;
		} else {
			return;
		}
	}
	
	
	
//	jquerySelectorsMode = Array.isArray(data);
	
	
    let $sel = selector;
    if (!selector.jquery) {
        $sel = $(selector);
    }

	//заполняем вспомогательные переменные
    if (!$mainSelect) {
        $mainSelect = $sel;
    }

	//заполняем select
	let opts = {
	    data: data,
	    withNullOption: true,
	    selectedValue: null,
	    contentIsValue: true,
	    valueIsIndex: false
	};
	if (Array.isArray(data)) {
	    opts.valueIsIndex = true;
	    opts.contentIsValue = false;
	}
	accordUtils.fillSelect($sel, opts);
	
	//обработчик выбора в select
    $sel.change(e => {
        clearLog();
		reloadSandbox();
		currentFunc = null;

        let v = $sel.val();

		//задан массив jquery-селекторов
        if (demoOptions.jquerySelectorsMode) {
//            let exp = $sel.children("option:selected").text();
			
//			let ind = Number(v);
			let exp = mainData[v];
			let comment = mainDataComments[v];
			
			log(comment);
            log(exp);
			highlightLogComments1();	
						
			currentFunc = exp;
			$selectorText.val(exp);
        } else {
            currentFunc = data[v];
			logCurrentFunc();
			$selectorText.val(v);
        }
		

    });

}


//добавляет набор демо кнопок на панель
//при клике на кнопку - показывает код демо-функции и выполняет её
function addDemoButtons(handlers, panelSelector = ".acc-button-panel") {

    for (let handlerName in handlers) {
        let handler = handlers[handlerName];
        addDemoButton(handlerName, handler, panelSelector);
    }
}

function addDemoButton(handlerName, handler, panelSelector = ".acc-button-panel") {
    let $panel = $(panelSelector);

    let $newButton = $(`<button id="b${newButtonNo++}" type="button" class="acc-btn">${handlerName}</button>`);
    demoButtons.push($newButton);
    $panel.append($newButton);

    $newButton.click(event => {
		
		//выделяем последнюю нажатую кнопку
		$panel.find("button").removeClass("blue-border");
		$newButton.addClass("blue-border");

		currentFunc = handler;
		
		logCurrentFunc();
		execDemoFunc();
		
    });

}


function initDemo() {

	//вспомогательные переменные
	hasSandbox = $("#template1").length>0;
    $sel1 = $("#selectors1");
    $sel2 = $("#selectors2");
    $sel3 = $("#selectors3");
    $sel4 = $("#selectors4");
    $workPanel = $(".workPanel");
    $selectorText = $("#selectorText");

	//добавление кнопок, если их нет 
    addTitlePanelButtons();
	
	//прописываем ссылку на главный js-файл в ссылке в заголовке
	let src = findMainJs();
	if (src) {
		$(".titlePanel a").attr("href", src);
	}
	
	let options = {
	    draggable: false,
	    contentTextUrl: mainJsHref,
	    hideOnDblclick: true,
	    fullScreen: true,
	    cssClass: "help-panel",
	    panelExtraClasses: "acc-popup"
	}
	helpPopup = new AccPopup(options);
	
	
    $hideAuxButton = $("#hideAuxButton");
    if ($log1.parents(".auxPanel").children().length >= 2) {
        new AccSplitter({
            panelSelector: ".auxPanel",
            startLeftPanelWidth: 600
        });
    }

    //показывать исходники при нажатии на ссылку
    $(".titlePanel a").click(e => {
        e.preventDefault();
        helpPopup.show();
    });

	//кнопка скрытия панели с логами
    $hideAuxButton.click(e => {
        showAux = !showAux;
        if (showAux) {
            $("div.auxPanel").css("display", "flex");
            $hideAuxButton.text("скрыть описание");
        } else {
            $("div.auxPanel").css("display", "none");
            $hideAuxButton.text("показать описание");
        }
    });


    let tp = new TabbedPanel("#tabbedPanel1");
    new TabbedPanel("#tabbedPanel2");

    $bExecute = $("#bExecute");
    $bExecute.click(e => {
        execDemoFunc(currentFunc);
    });

    $("#bClearLog").click(e => {
        clearLog();
    });

    $("#bReload").click(e => {
        reloadSandbox();
    });

	//быстрые клавиши
    $(document).keydown(e => {
        if (e.keyCode == 109 || e.keyCode == 33) {  //-, pagUP
            accordUtils.selectNextOption($mainSelect, false);
        } else if (e.keyCode == 107 || e.keyCode == 34) { //+, pgDown
            accordUtils.selectNextOption($mainSelect, true);
        } else if (e.keyCode == 13) { //Enter    || e.keyCode == 123
            $bExecute.trigger("click");
        } else if (e.keyCode == 45 || e.keyCode == 96) { //0
            reloadSandbox()
        } else if (e.keyCode == 112) { //F1
			e.preventDefault();
			helpPopup.toggleVisible();
		}
//        console.log(e.keyCode);
    })

	reloadSandbox();			

}




const DT_SELECT = 1;
const DT_BUTTONS = 2;
const DT_SELECTORS = 3;

const TEMPLATE_FORM1 = 1;
const TEMPLATE_FORM2 = 2;

const defaultBruefDemoOptions = {
	demoType: DT_SELECT,
	workPanelTemplate: TEMPLATE_FORM1,
	initFunction: null,
	selectorsData: null,
	selectedOption: null,
	title: null,
	afterSandboxReload: null,
	jquerySelectorsMode: false,
}

//инициализация лаконичного демо
function initBriefDemo(options) {
	
	$(document.body).addClass("acc-default-font");
	
	options = $.extend({}, defaultBruefDemoOptions, options);
	
	demoOptions.afterSandboxReload = options.afterSandboxReload;
	demoOptions.jquerySelectorsMode = options.jquerySelectorsMode;
	
	parseMainSelectorsData(options.selectorsData);
	
	
	switch (options.demoType) {
	  case DT_SELECT:
		accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment1.html",null,true);
	    break;
		case DT_BUTTONS:
		accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment2.html",null,true);
		  break;
	  case DT_SELECTORS:
	  accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment3.html",null,true);
	    break;
	  default:
		console.log(`demoType ${options.demoType} not found.`);
		return;
	}	
		
	if (typeof options.workPanelTemplate == "string"){
		accordUtils.loadHtmlFragmentXHR(options.workPanelTemplate,null,false);
	} else {
		
		switch (options.workPanelTemplate) {
		  case TEMPLATE_FORM1:
			accordUtils.loadHtmlFragmentXHR("demos/fragments/formTemplate1.html",null,true);
		    break;
			case TEMPLATE_FORM2:
			accordUtils.loadHtmlFragmentXHR("demos/fragments/formTemplate2.html",null,true);
			  break;
		  default:
		}	
		
	}
	

	if (options.title){
		$(".titlePanel h2").text(options.title);
	}
	
		
	initDemoLogs();
	initDemo();
	
	if (options.demoType==DT_SELECT || options.demoType==DT_SELECTORS) {
		initDemoCodeSelect("#selectors1", mainData);
		
//		if (options.selectorsData){
//			initDemoCodeSelect("#selectors1", options.selectorsData);
//		}

		if (options.selectedOption){
			//выбрать опцию после загрузки страницы 
			$("#selectors1").val(options.selectedOption).trigger("change");
		}
	}
		  
	if (options.demoType==DT_BUTTONS) {
		if (options.selectorsData){
			//добавляем демо-кнопки
			//addDemoButtons(options.selectorsData)
			addDemoButtons(mainData);
		}
	}
	
	if (options.initFunction){
		options.initFunction();
	}
	
	
}

//если демки заданы строкой - он разбивает её на 2 массива: mainData - демки и mainDataComments - связанные с ними комменты
function parseMainSelectorsData(selectorsData){

	mainData = selectorsData;
	mainDataComments = null;
	

	//демки заданы одной большой строкой
	if (typeof selectorsData == "string") {

		mainDataComments = [];
		mainData = [];

		let lines = selectorsData.split("\n");

		let ind = 0;
		let currExp = null;
		let currComment = null;
		
		lines.push("");
		lines.forEach(line=>{
			
			line = line.trim();
			
			//пустая строка
			if (!line){
				if (!currExp){
					return;
				}
				mainData[ind] = currExp;
				mainDataComments[ind] = currComment;
				
				currExp = null;
				currComment = null;
				ind++;
				return;
				
			}

			if (line.startsWith("//") || line.startsWith("# ") || line=="#"){
				if (currComment){
					currComment+="\n"+line;					
				} else {
					currComment = line;
				}
			} else {
				currExp = line;
			}
			
		});//for lines
			
	}
	

	
} 



$(function() {

	if ($("div.basePanel").length){
		initDemo();
	}


});



