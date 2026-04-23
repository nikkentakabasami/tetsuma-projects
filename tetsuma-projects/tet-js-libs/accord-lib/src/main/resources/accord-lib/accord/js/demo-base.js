/**
 * Функционал, использующийся для создания демок, тестирующих js.
 * 
 * 
 */

//вспомогательная переменная
let result;

let mainJsHref = null;

let $workPanel;

//показывается ли сейчас auxPanel
let showAux = true;

//кнопка показа/скрытия auxPanel
let $hideAuxButton;

//input с текущим селектором
let $selectorText;

//выбранная функция/код
let currentFunc = null;

//элементы песочницы
let $btn1, $btn2, $inp1, $inp2, $inp3, $inp4, $testBtn1, $testBtn2;
let formDiv1, formDiv2;
let form1, form2;
let $panel1, $panel2;
let $sel1, $sel2, $sel3, $sel4;

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
	reloadSandboxVars: null,

	

};

//Содержит #template1 - используется песочница
let hasSandbox = false;


let greenSpan = '<span class="green"></span>';


//счётчик для добавления новых демо-кнопок
let newButtonNo = 1;

//первый селект, обслуживающий демки
let $demoSelect1 = null;
let $bExecute;

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

//прописывает ссылку на главный js-файл в ссылке в заголовке
function fixSrcRef() {

    let src = findMainJs();
    if (!src) {
        console.log("mainJs not found!");
        return;
    }

    $(".titlePanel a").attr("href", src);

}

//добавляет в демку доп. элементы
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
        $tp.append('<a href="#" target="source">Исходники</a>');
    }



}


function showMainJs() {
    let options = {
        draggable: false,
        contentTextUrl: mainJsHref,
        hideOnDblclick: true,
        fullScreen: true,
        cssClass: "help-panel",
        panelExtraClasses: "acc-popup"
    }
    let p1 = new AccPopup(options);
    p1.show();
}





//убирает лишние отступы в коде
function removeOddIndent(code) {

    let lines = code.split("\n")
    if (lines.length == 1) {
        return code.trim();
    }

    lines = lines.map(line => line.replaceAll("\t", "  "));

    //убираем пустые строки в начале	
    while (lines.length > 0 && lines[0].trim().length == 0) {
        lines.shift();
    }

    //убираем пустые строки в конце	
    while (lines.length > 0 && lines[lines.length - 1].trim().length == 0) {
        lines.pop();
    }


    let minIndent = 0;
    for (let i = 0;i < lines.length;i++) {
        let line = lines[i];

        if (line.trim().length == 0) {
            continue;
        }

        let r = line.match(/^ +/i);
        if (r) {

            let indent = r[0].length;
            if (!minIndent) {
                minIndent = indent;
                continue;
            }
            if (indent < minIndent) {
                minIndent = indent;
            }
        }
    }

    if (minIndent) {
        lines = lines.map(line => line.substring(minIndent));
    }

    return lines.join("\n");

}

//возвращает код заданной функции.
//убирает её объявление, убирает лишние отступы
function trimFuncCode(func) {
    let code = String(func);

    let ind1 = code.indexOf("{");
    let ind2 = code.lastIndexOf("}");
    code = code.substring(ind1 + 1, ind2);
    code = removeOddIndent(code);
    return code;
}

//подсвечивает комменты в логах серым цветом
function highlightLogComments1() {
    highlightLogComments($log1);
}
function highlightLogComments2() {
    highlightLogComments($log2);
}
function highlightLogComments($log) {

    const text = $log.html();
    const lines = text.split('\n');

    const processedLines = lines.map(line => {
        let ind = line.indexOf('//');
        if (ind >= 0) {
            return line.substring(0, ind) + '<span class="gray">' + line.substring(ind) + '</span>';
        } else {
            return line;
        }
    });

    let newText = processedLines.join('\n');

    $log.html(newText);
}


//вывод currentFunc в лог1 
function logCurrentFunc(){
	clearLog1();
	
	if (typeof currentFunc == "string") {
	    currentFunc = removeOddIndent(currentFunc);
	    log(currentFunc);
	} else {
	    let code = trimFuncCode(currentFunc);
	    log(code);
		
		let initFunction = currentFunc.init || demoOptions.currentFunc;

		//указана доп. функция инициализации - вывести её в лог
		if (initFunction) {
		    log();
		    lognl("//функция инициализации:");
		    log(String(initFunction));
		}
	}
	highlightLogComments1();	
	
}

//выполняет currentFunc
function execDemoFunc() {
    if (!currentFunc) {
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

function initDemoCodeSelect(selector, data) {

    let $sel = selector;
    if (!selector.jquery) {
        $sel = $(selector);
    }

	//заполняем вспомогательные переменные
    if (!$demoSelect1) {
        $demoSelect1 = $sel;
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
        if (Array.isArray(data)) {
            v = $sel.children("option:selected").text();
            log(v);
        } else {
            currentFunc = data[v];
			logCurrentFunc();
        }
		
		$selectorText.val(v);

    });



}





//очищает .workPanel и загружает в неё элементы из #template1
function reloadSandbox() {

	if (!hasSandbox){
		return;
	}
	
    $workPanel.empty();

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

    $panel1 = $("#formDiv1");
    $panel2 = $("#formDiv2");


    if (demoOptions.reloadSandboxVars) {
        demoOptions.reloadSandboxVars();
    }


}




//let emptyDiv = $("<div></div>");
//let defaultStyles = window.getComputedStyle(emptyDiv.get(0));

let showCssStylesDefaultOptions = {
    showInContent: false,
    showInPrevSibling: false
}


function showCssStylesForElements(selector, opt) {

    let options = $.extend({}, showCssStylesDefaultOptions, opt);


    $(selector).each((index, el) => {
        let $el = $(el);
        let styleText = $(el).attr("style");
        if (!styleText) {
            return;
        }
        let infoPanelSelector = $el.data("show-style-in");
        if (infoPanelSelector) {
            $("#" + infoPanelSelector).text(styleText);
        }
        if (options.showInContent) {
            $el.text(styleText);
        }
        if (options.showInPrevSibling) {
            $el.prevAll(":header:first").text(styleText);
        }
    });

}

function showStyleTagText() {


    $(":header[data-style-element]").each((index, el) => {
        let $el = $(el);
        let styleElement = $(el).data("style-element");

        let styleText = $("#" + styleElement).text();

        $el.text(styleText)
    });


    //	$("#style1").text()

}




let beforeHighlight = null;


//выделяет объекты с заданным селектором красной рамкой
//выводит в лог значение выражения (или число найденных элементов)
function highlight(val) {
    reloadSandbox();
    if (!val) {
        return;
    }

    if (beforeHighlight) {
        beforeHighlight();
    }


    clearLog();
    log(val);
    if (val.indexOf("$") >= 0 && val.indexOf("$=") < 0) {

        val = eval(val);
        if (!val.jquery) {
            log(val);
            return;
            //			throw new Error('Выражение должно возвращать jquery-объект!');
        }

    } else {
        val = $(val);
    }

    val.addClass("red-border");

    logVal("elements found", val.length);

}




function formatDateTime(date) {
    let r = formatDate(date);
    let t = formatTime(date);
    if (t != "00:00:00") {
        r = r + " " + t;
    }
    return r;
    //	return formatDate(date)+" "+formatTime(date);
}

function formatTime(date) {
    let h = date.getHours();
    let m = date.getMinutes();
    let s = date.getSeconds();
    return (h <= 9 ? '0' + h : h) + ':' + (m <= 9 ? '0' + m : m) + ':' + (s <= 9 ? '0' + s : s);
}

function formatDate(date) {
    let d = date.getDate();
    let m = date.getMonth() + 1;
    let y = date.getFullYear();
    return (d <= 9 ? '0' + d : d) + '.' + (m <= 9 ? '0' + m : m) + '.' + y;
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

	hasSandbox = $("#template1").length>0;

    $sel1 = $("#selectors1");
    $sel2 = $("#selectors2");
    $sel3 = $("#selectors3");
    $sel4 = $("#selectors4");


    $workPanel = $(".workPanel");
    $selectorText = $("#selectorText");


    addTitlePanelButtons();
    fixSrcRef();

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
        showMainJs();
    });

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
        if (!currentFunc) {
            let v = $selectorText.val();
            highlight(v);
            return;
        }

        execDemoFunc(currentFunc);

    });

    $("#bClearLog").click(e => {
        clearLog();
    });

    $("#bReload").click(e => {
        reloadSandbox();
    });

    $(document).keydown(e => {
        if (e.keyCode == 109) {  //-
            accordUtils.selectNextOption($demoSelect1, false);
        } else if (e.keyCode == 107) { //+
            accordUtils.selectNextOption($demoSelect1, true);
        } else if (e.keyCode == 13 || e.keyCode == 123) { //Enter
            $bExecute.trigger("click");
        } else if (e.keyCode == 45 || e.keyCode == 96) { //0
            reloadSandbox()
        }



        console.log(e.keyCode);
    })

	reloadSandbox();			

}




const DT_SELECT = 1;
const DT_BUTTONS = 2;

const TEMPLATE_FORM1 = 1;

const defaultBruefDemoOptions = {
	demoType: DT_SELECT,
	workPanelTemplate: TEMPLATE_FORM1,
	initFunction: null,
	selectorsData: null,
	selectedOption: null,
	title: null,
}


function initBriefDemo(options) {
	
	$(document.body).addClass("acc-default-font");
	
	options = $.extend({}, defaultBruefDemoOptions, options);
	
	switch (options.demoType) {
	  case DT_SELECT:
		accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment1.html",null,true);
	    break;
		case DT_BUTTONS:
		accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment2.html",null,true);
		  break;
	  default:
		console.log(`demoType ${options.demoType} not found.`);
		return;
	}	
		
	switch (options.workPanelTemplate) {
	  case TEMPLATE_FORM1:
		accordUtils.loadHtmlFragmentXHR("demos/fragments/formTemplate1.html",null,true);
	    break;
	  default:
	}	

	if (options.title){
		$(".titlePanel h2").text(options.title);
	}
	
		
	initDemoLogs();
	initDemo();
	
	if (options.demoType==DT_SELECT) {
		if (options.selectorsData){
			initDemoCodeSelect("#selectors1", options.selectorsData);
		}

		if (options.selectedOption){
			//выбрать опцию после загрузки страницы 
			$("#selectors1").val(options.selectedOption).trigger("change");
		}
	}
		  
	if (options.demoType==DT_BUTTONS) {
		if (options.selectorsData){
			//добавляем демо-кнопки
			addDemoButtons(options.selectorsData)
		}

	}
	
	
	
	if (options.initFunction){
		options.initFunction();
	}
	
	//typeof selectorsData2=='undefined'
	
}




$(function() {

	if ($("div.basePanel").length){
		initDemo();
	}


});



