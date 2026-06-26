/**
 * Функционал, использующийся для создания демок, тестирующих js.
 * 
 * Основные функции:
 * highlightJquery(val) - выделяет объекты с заданным селектором красной рамкой
 * reloadSandbox() - очищает .workPanel и загружает в неё элементы из #template1
 * execDemoFunc() - выполняет currentScript
 * initDemoCodeSelect(selector) - инициализация селекта с демками
 * addDemoButtons(handlers, panelSelector = ".acc-button-panel") - добавляет набор демо кнопок на панель
 * initDemo()
 * initBriefDemo(options) - инициализация лаконичного демо
 * 
 */


//demoType
const DT_SELECT = 1;
const DT_BUTTONS = 2;
const DT_SELECTORS = 3;
const DT_SELECT_NO_WP = 4;  //без песочницы
const DT_REGEXP = 5;
const DT_SELECT_SINGLE_LOG = 6;  //только один лог, много место для html-кода
const DT_OPENLAYERS = 7;  //для тестирования openlayers


//workPanelTemplate - шаблон для песочницы
const TEMPLATE_FORM1 = 1;	//formTemplate1.html (2 текстовых инпута, 1 select)
const TEMPLATE_FORM2 = 2;	//formTemplate2.html (пустая форма)


//опции, определяющие, как будет работать текущая демка
let demoOptions = {};

const defaultBruefDemoOptions = {
  demoType: DT_SELECT,
  workPanelTemplate: TEMPLATE_FORM1,
  selectorsData: null,
  selectedOption: null,
  regexpMode: false,
  sampleText: null,
  reloadSandboxOnChange: true,

  exitOnError: true,		//прекращать выполнение при ошибках

  //выполняются до и после выполнения currentScript/демо-кнопок
  beforeExec: null,
  afterExec: null,
	afterSelectChange: null,

  //функция инициализации всего кода: выводить её при нажатии каждой демо-кнопки
  initFunction: null,

  //функция, выполняющаяся после reloadSandbox
  afterSandboxReload: null,

  //режим тестирования jquery функций (входные данные - это массив селекторов и jquery-запросов)
  jquerySelectorsMode: false,

  //выводить ли демо-функции во второй лог при выполнении
  lfMode: false,

  //настройки, позволяющие включить автоскролинг в первый или второй лог 
  autoscrollLog1: false,
  autoscrollLog2: false,

  //выводить объекты в лог в json-виде
  logObjectsAsJson: true,
	
	//форматировать json
	formattedJson: false,
	
	//кастомная функция, форматирующая значения для вывода в лог
	customFormatter: null,

}





//объект, в который можно выводить множественные результаты выполнения функции.
//в конце выполнения каждой демки, если в него что то записано - он будет выведен во второй лог
let a = {};

//вспомогательная переменная
let result;

//ссылка на js-файл с исходниками текущей демки (показывается на F1)
let mainJsHref = null;

//предыдущая и следующая страницы
let siblingPages = null;

//показывается ли сейчас auxPanel
let showAux = true;

//кнопка показа/скрытия auxPanel
let $hideAuxButton;

//popup с исходниками страницы
let helpPopup;

//песочница
let $workPanel;

//элементы песочницы - вспомогательные переменные
let $btn1, $btn2, $btn3, $inp1, $inp2, $inp3, $inp4, $testBtn1, $testBtn2, $btnSubmit;
let formDiv1, formDiv2;
let $form1, $form2, $formPanel;
let $sel1, $sel2, $sel3;
//селект, обслуживающий демки
let $mainSelect = null;

//кнопка выполнения кода
let $bExecute;


//input с текущим селектором ()
let $selectorText;


//демо-кнопки
let demoButtons = [];

//Содержит ли #template1 - используется ли песочница
let hasSandbox = false;


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

	let $tp = $(".titlePanel, .titlePanel2");
	if (demoOptions.demoType!=DT_SELECT_SINGLE_LOG && demoOptions.demoType!=DT_OPENLAYERS){
		
		if (!$tp.children("#hideAuxButton").length) {
		  $tp.append('<button id="hideAuxButton" type="button" class="acc-btn">Скрыть описание</button>');
		}
	}	

  if (!$("#bClearLog").length) {
    $tp.append('<button id="bClearLog" type="button" class="acc-btn">Очистить логи</button>');
  }

  if (!$("#bReload").length && $("#template1").length) {
    $tp.append('<button id="bReload" type="button" class="acc-btn">Перезагрузить песочницу (0)</button>');
  }

  if (!$tp.children("a").length) {
    $tp.append('<a id="mainsrc" href="#">Исходники (F1)</a>');
  }

}




function elementToString(el) {
  let r = "";
  if (el.nodeName) {
    r = el.nodeName.toLowerCase() + " ";
  }

  if (el.id) {
    r += "#" + el.id + " ";
  }
  if (el.className) {
    let cl = el.className.replace(/ +/g, ".")
    r += "." + cl + " ";
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
  log2Blue(val);
  log2();

  a = {};
  if (demoOptions.beforeExec) {
    demoOptions.beforeExec();
  }

  if (val.indexOf("$") >= 0 && val.indexOf("$=") < 0) {

    val = eval(val);
    if (!val.jquery) {
      log2(val);
      return;
    }

  } else {
    val = $(val);
  }


  log2();
  log2("elements found: ", val.length);
  log2();
  val.each((ind, el) => {
    log2(elementToString(el));
  });

  val.addClass("red-border");

  if (demoOptions.afterExec) {
    demoOptions.afterExec();
  }
  if (Object.keys(a).length) {
    let as = stringifyObject(a, "", false, false)
    log2nl(as);
  }


}

//очищает .workPanel и загружает в неё элементы из #template1
function reloadSandbox() {

  $workPanel.empty();
  if (!hasSandbox) {
    return;
  }


  let $sandboxPanels = accordUtils.cloneTemplate("#template1");
  $sandboxPanels.appendTo($workPanel);

  $btnSubmit = $("#btnSubmit");

  $btn1 = $("#btn1");
  $btn2 = $("#btn2");
  $btn3 = $("#btn3");
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

  
  $sel1 = $("#select1");
  $sel2 = $("#select2");
  $sel3 = $("#select3");
  
  
  $formPanel = $(".form-panel");


  //    $panel1 = $("#formDiv1");
  //    $panel2 = $("#formDiv2");


  if (demoOptions.afterSandboxReload) {
    demoOptions.afterSandboxReload();
  }


}


//тестирование регулярных выражений
function testRegExp(re) {

  let opts = accordUtils.highlightText({
    $div: $log1,
    regex: re,
    class: "bg-green",
    matchHandler: match => {
      log2("match=", match, ", match.index=", match.index);
    }
  });


}


function execDemoFunc() {

  if (demoOptions.jquerySelectorsMode) {
    let val = $selectorText.val();
    highlightJquery(val);
    return;
  }

  if (demoOptions.regexpMode) {
    let regexp = $selectorText.val();
    log2();
    testRegExp(regexp);
    return;
  }

  if (!currentScript) {
    return;
  }

  a = {};
  if (demoOptions.beforeExec) {
    demoOptions.beforeExec();
  }

  $(".workPanel *").removeClass("red-border");

  clearLog2();


  if (!currentScript.func) {
		
		if (!$log2.length){
			clearLog1()
		}
		
    logParsedExpression(currentScript);
  } else {

    let r = null;
    try {

      if (demoOptions.lfMode) {
        $log2.append(currentScript.formatCode());

        log2hr();

        r = currentScript.func();
        if (r) {
          log2(r);
          return r;
        }

      } else {
        r = currentScript.func();

        let logMess = '';
        if (r && r.jquery) {
          r.addClass("red-border");
          logMess += "elements found: " + r.length;
        }
        log2(logMess);
      }

    } catch (error) {
      log("Error:", error.message);
      console.error(error.stack);
    }

  }

  if (demoOptions.afterExec) {
    demoOptions.afterExec();
  }

  //Выводим поля переменной а, если они были заданы
  if (Object.keys(a).length) {
    let as = stringifyObject(a, "", false, false)
    log2nl(as);
  }

  setTimeout(() => {

    let $l = demoOptions.lfMode ? $log2 : $log1;
    $l.parent().trigger("focus");
  }, 100);

}

//инициализация селекта с демками
function initDemoCodeSelect() {


  //заполняем select
  let opts = {
    data: mainData,
    withNullOption: true,
    selectedValue: null,
    contentIsValue: true,
    valueIsIndex: false
  };

  if (demoOptions.regexpMode || demoOptions.jquerySelectorsMode) {
    opts.data = Object.values(mainDataParsed).map(ps => ps.getCode());
  }

  if (Array.isArray(opts.data)) {
    opts.valueIsIndex = true;
    opts.contentIsValue = false;
  }
  accordUtils.fillSelect($mainSelect, opts);

  //обработчик выбора в select
  $mainSelect.change(e => {
    if (demoOptions.reloadSandboxOnChange) {
      reloadSandbox();
    }
		
		if (demoOptions.afterSelectChange) {
		  demoOptions.afterSelectChange();
		}
		
		

    //снимаем фокус (иначе будут глюки при нажатии на pgUp/pgDown)
    $mainSelect.blur();

    //получаем текущий скрипт		
    let v = $mainSelect.val();
    currentScript = mainDataParsed[v];
    if (!currentScript) {
      clearLog();
      return;
    }

    //выводим в поле ввода код скрипта (без комментов)
    if ($selectorText.length) {
      $selectorText.val(currentScript.getCode());
    }

    clearLog2();

    //выводим скрипт в первый лог (во второй, если тестируются регулярные выражения)		
    if (demoOptions.regexpMode) {
      logCurrentScript($log2);
    } else {
      logCurrentScript();
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

function addDemoButton(buttonTitle, handler, panelSelector = ".acc-button-panel") {
  let $panel = $(panelSelector);

  let $newButton = $(`<button id="b${newButtonNo++}" type="button" class="acc-btn">${buttonTitle}</button>`);
  demoButtons.push($newButton);
  $panel.append($newButton);

  $newButton.click(event => {

    //выделяем последнюю нажатую кнопку
    $panel.find("button").removeClass("blue-border");
    $newButton.addClass("blue-border");
	
	currentScript = mainDataParsed[buttonTitle];
	if (!currentScript) {
	  clearLog();
	  return;
	}

	clearLog2();
	logCurrentScript();
	
    execDemoFunc();

  });

}


function initDemo() {

  //Парсим все скрипты/функции в selectorsData, помещая результат в mainDataParsed
  parseSelectorsData(demoOptions.selectorsData);

  //вспомогательные переменные
  hasSandbox = $("#template1").length > 0;
  $mainSelect = $("#selectors1");
  $workPanel = $(".workPanel");
  $selectorText = $("#selectorText");

  //добавление кнопок, если их нет 
  addTitlePanelButtons();

  //прописываем ссылку на главный js-файл в ссылке в заголовке
  let src = findMainJs();
  if (src) {
    $("a#mainsrc").attr("href", src);
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
  $("a#mainsrc").click(e => {
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
    execDemoFunc();
  });

  $("#bClearLog").click(e => {
	if ($log2.length){
	    clearLog2();
	} else {
		clearLog();		
	}
	
  });

  $("#bReload").click(e => {
    reloadSandbox();
  });

  //быстрые клавиши
  $(document).keydown(e => {
    if (e.keyCode == 112) { //F1
      e.preventDefault();
      helpPopup.toggleVisible();
    } else if (e.ctrlKey && e.keyCode == 37) { // <-
      e.preventDefault();
      if (siblingPages[0]) {
        location.href = siblingPages[0];
      }

    } else if (e.ctrlKey && e.keyCode == 39) { // ->
      e.preventDefault();
      if (siblingPages[1]) {
        location.href = siblingPages[1];
      }

    }
    //	    console.log(e.keyCode);
  })


  $(document).keyup(e => {
    //инпут в фокусе
    let tf = $selectorText.is(':focus');

    if (!tf) {
      if (e.keyCode == 109 || e.keyCode == 33) {  //-, pagUP
        accordUtils.selectNextOption($mainSelect, false);
      } else if (e.keyCode == 107 || e.keyCode == 34) { //+, pgDown
        accordUtils.selectNextOption($mainSelect, true);
      } else if (e.keyCode == 45 || e.keyCode == 96) { //0
        reloadSandbox()
      }
    }

    if (e.keyCode == 13) { //Enter
      $bExecute.trigger("click");
    }

  })

  createSiblingPageAnchors();

  //задаём заголовок страницы (берём его из <title>)
  let title = $("title").text()
  if (title) {
    $(".titlePanel h2, .titlePanel2 h2").text(title);
  }




  reloadSandbox();

}







//инициализация лаконичного демо
function initBriefDemo(options) {

  $(document.body).addClass("acc-default-font");

  demoOptions = $.extend({}, defaultBruefDemoOptions, options);



  switch (demoOptions.demoType) {
    case DT_SELECT:
      accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment1.html", null, true);
      break;

    case DT_BUTTONS:
      accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment2.html", null, true);
      break;

    case DT_SELECTORS:
      accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment3.html", null, true);
      break;


    case DT_SELECT_NO_WP:
      accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment4.html", null, true);
      break;

    case DT_REGEXP:
      accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment5.html", null, true);
      break;

  case DT_SELECT_SINGLE_LOG:
    accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment6.html", null, true);
    break;
		case DT_OPENLAYERS:
		  accordUtils.loadHtmlFragmentXHR("demos/fragments/demoFragment8.html", null, true);
		  break;
	  	  
		
		
	  
    default:
      console.log(`demoType ${demoOptions.demoType} not found.`);
      return;
  }

  if (typeof demoOptions.workPanelTemplate == "string") {
    accordUtils.loadHtmlFragmentXHR(demoOptions.workPanelTemplate, null, false);
  } else {

    switch (demoOptions.workPanelTemplate) {
      case TEMPLATE_FORM1:
        accordUtils.loadHtmlFragmentXHR("demos/fragments/formTemplate1.html", null, true);
        break;
      case TEMPLATE_FORM2:
        accordUtils.loadHtmlFragmentXHR("demos/fragments/formTemplate2.html", null, true);
        break;
      default:
    }

  }


  initDemoLogs();
  initDemo();

  if (demoOptions.demoType == DT_SELECT
    || demoOptions.demoType == DT_SELECT_NO_WP
    || demoOptions.demoType == DT_SELECTORS
    || demoOptions.demoType == DT_REGEXP
	|| demoOptions.demoType == DT_SELECT_SINGLE_LOG
	|| demoOptions.demoType == DT_OPENLAYERS
	
  ) {
    initDemoCodeSelect("#selectors1");


    if (demoOptions.selectedOption) {
      //выбрать опцию после загрузки страницы 
      $("#selectors1").val(options.selectedOption).trigger("change");
    }
  }

  if (demoOptions.demoType == DT_REGEXP) {
    $log1.text(demoOptions.sampleText);
    $("#bTestText").click(() => {
      demoOptions.sampleText = prompt("Введите тестовый текст.")
      $log1.text(demoOptions.sampleText);
    });
  }


  if (demoOptions.demoType == DT_BUTTONS) {
    if (demoOptions.selectorsData) {
      //добавляем демо-кнопки
      //addDemoButtons(options.selectorsData)
      addDemoButtons(mainData);
    }
  }

  if (demoOptions.initFunction) {
    demoOptions.initFunction();
  }


}



function createSiblingPageAnchors() {

  let ind = location.pathname.lastIndexOf("/");

  let pageName = location.pathname.substring(ind + 1);

  $.get({
    url: "../../demoscan/siblingPages",
    data: { 'pageName': pageName },
    success: function(data, status, request) {

      if (!data) {
        console.log("siblings not found");
        return;
      }

      siblingPages = data;

      let $tp = $(".titlePanel");
      if (siblingPages[0]) {
        $tp.append(`<a href="${data[0]}">Prev demo (Ctrl+left)</a>`);
      }
      if (siblingPages[1]) {
        $tp.append(`<a href="${data[1]}">Next demo (Ctrl+right)</a>`);
      }

      //		console.log(data);

    }
  });





}


$(function() {

  if ($("div.basePanel").length) {
    initDemo();
  }


});



