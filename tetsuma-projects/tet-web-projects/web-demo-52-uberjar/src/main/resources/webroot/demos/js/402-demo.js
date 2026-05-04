


//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {


  

  event_desc() {
	
	//# Стандартные события в DOM:
	//# blur, focus, load, resize, scroll, unload, beforeunload, click, dblclick, 
	//# mousedown, mouseup, mousemove, mouseover, mouseout, mouseenter, mouseleave, 
	//# change, select, submit, keydown, keypress, keyup
	
	//# Атрибуты событий:
	//# 
	//# event.type
	//# 
	//# event.currentTarget - Элемент, на который назначен обработчик события в данный момент.
	//# event.target - самый глубокий элемент, на котором произошло событие.
	//# event.relatedTarget - mouseover(элемент, с которого пришел курсор мыши), mouseout(на который перешел)
	//# 
	//# mousedown,mouseup,click:
	//# 
	//# event.button - Какая кнопка мыши была нажата (0-левая, 1-средняя, 2-правая)
	//# event.pageX/pageY - координаты относительно документа, учитывая прокрутку
	//# event.clientX/clientY - координаты кликнутой точки относительно окна
	//# event.which - jquery атрибут. Для нажатия клавиши = event.keyCode. Для нажатия кнопки мыши = event.button
	//# 
	//# keydown,keyup:
	//# 
	//# event.originalEvent.code - код клавиши. Примеры: KeyD, Digit5, F2
	//# event.key - нажатый символ
	//# shiftKey, altKey, ctrlKey, metaKey - нажаты ли клавиши-модификаторы
	//# event.keyCode - Устаревший. Возвращает числовой код клавиши.
	//# event.repeat - автоповтор	
	
	$inp1.on("click keydown", universalEventHandler);
	
  },

  

	templates() {

	//шаблоны для использования
	$inp1.keydown(event => {
	  event.preventDefault();
	  log2(`keydown, key=${event.key}, keyCode=${event.keyCode}`);
	});

	$inp1.mousedown(event => {
	  log2("mousedown, button="+event.button);
	});

	$form1.submit(event => {
	  event.preventDefault();  //предотвратить поведение элемента по умолчанию
	  log2("form1 submit");
	});
	
	$inp2.click(event => {
	  event.stopPropagation();  //предотвращает всплытие события вверх по дереву.
	  log2("inp2 click");
	});
	
	//однократное событие
	$inp3.one("click", event => {
	  log2("inp1 click once");
	});	
	

	},
	
	
    
  on1() {

	//# .on( events [, selector ] [, data ], handler )
	//#   Назначение событий. Полностью заменяет bind
	

	
	//назначает обработчик на все инпуты
	$("#form1 input").on("click", universalEventHandler);

	
	$btn1.click(e=>{
		//# .off( events [, handler ] )
		//#   Удаление обработчиков
		
		//убирает обработчик
		$inp1.off("click", universalEventHandler);

		//убирает все обработчики событий, привязанные к этому элементу
		$inp2.off();
	});
	
	
	
  },

  on_two_events() {
	//назначение обработчика на 2 события
	$inp1.on("mouseenter mouseleave", event => {
	  $inp1.toggleClass("bg-red");
	});
	
	//назначение сразу нескольких обработчиков на разные события
	$inp2.on({
	  mouseenter: universalEventHandler,
	  mouseleave: universalEventHandler,
	  click: universalEventHandler
	});
	
  },

  trigger(){
	//передача фокуса ввода
	$inp1.trigger("focus");
  },
  

  
  
  on_data_param: function() {
	
	//# .on( events [, selector ] [, data ], handler )
	//# 
	//# data
	//#   объект, который будет передан в event.data
	
	//эта фича используется редко, ведь обработчик - closure
	let outerVar = 477;	
	$inp1.on("click", {msg: "my message."}, event=> {
	  log2("event.data.msg:", event.data.msg);
	  log2("outerVar:", outerVar);
	});

	$btn1.on("click", event => {
	  $inp1.trigger("click");
	});

	return $().add($inp1).add($btn1);
  },

  trigger_data: function() {
	//при вызове trigger можно передать data-объект.
	//Его можно получить через доп. параметр обработчика события.
	//В event.data он не попадёт!
	$btn1.on("click", event => {
	  $inp1.trigger("click", "Trigger message");
	});
	
	$inp1.on("click", (event, data) => {
	  log2("inp1 click. data:", data);
	});

	return $().add($inp1).add($btn1);
  },


  on_selector: function() {
	
	//# .on( events [, selector ] [, data ], handler )
	//# 
	//# selector
	//#   селектор-фильтр, определяющий дочерние элементы, которые запустят событие
	//#   фактически будет 1 обработчик, но срабатывать он будет только на заданных в селекторе элементах
	
	return $workPanel.on("click", "#form1 input:text", universalEventHandler);
  },


  events_mouse1: function() {

	//события мыши
	$inp1.on("click", universalEventHandler);

	$inp2.on("mousedown", universalEventHandler);
	$inp2.on("mouseup", universalEventHandler);

	$btn1.on("mouseover", universalEventHandler);
	$btn1.on("mouseout", universalEventHandler);

	//	Похожи на mouseover/mouseout, но есть отличия:
	//  1)Переходы внутри элемента, на его потомки и с них, не считаются.
	//  2)Эти события не всплывают.
	$btn2.on("mouseenter", universalEventHandler);
	$btn2.on("mouseleave", universalEventHandler);

  },

  events_keyboard: function() {

	//клавиатурные события
	$inp1.on("keydown", universalEventHandler);
	$inp1.on("keyup", universalEventHandler);
	$inp2.on("keypress", universalEventHandler);

	return $().add($inp1).add($inp2);
  },


  events_misc: function() {

	//получение и потеря фокуса ввода
	$inp1.on("focus", universalEventHandler);
	$inp1.on("blur", universalEventHandler);

	//копирование/вставка
	$inp2.on("cut", universalEventHandler);
	$inp2.on("copy", universalEventHandler);
	$inp2.on("paste", universalEventHandler);

	//срабатывает по окончании изменения элемента.
	//Для input-ов срабатывает при потере ими фокуса, для остальных - сразу же
	$inp2.on("change", universalEventHandler);
	$inp3.on("change", universalEventHandler);

	return $().add($inp1).add($inp2);
  },

  events_preventDefault: function() {

	//запрет на копирование-вставку
	$inp1.on("cut copy paste", event => { return false });

	//event.preventDefault()- предотвратить поведение элемента по умолчанию
	//запрет на submit формы 
	$form1.submit(event => {
	  event.preventDefault();
	  log2("form1 submit");
	});

	//запрет на клавиатурные события в $inp2 
	$inp2.keydown(event => {
	  event.preventDefault();
	  log2("inp2 keydown");
	});


	return $().add($inp1).add($inp2);
  },


  events_stopPropagation: function() {
	//stopPropagation() - предотвращает всплытие события вверх по дереву.
	//$form1 это событие уже не получит
	$inp1.click(event => {
		event.stopPropagation();
		log2("inp1 click");
	});
	$inp2.click(event => {
		log2("inp2 click");
	});

	$form1.click(event => {
	  log2("panel1 click");
	});
	
	
	
	return $().add($inp1).add($inp2);
  },

  
  one: function() {
	//# .one(events [, data ], handler)
	//#   Назначает обработчик, который выполняется единожды.
	
	$inp1.one("click", event => {
	  log2("inp1 click once");
	});
	return $().add($inp1);
  },

  

  
  //-------------------deprecated----------------------
  
  bind1: function() {

  //# .bind( eventType[, eventData], handler )
  //#   Назначает обработчик к одному или более событиям
  //#   Считается устаревшим - следует использовать on (у него такой же интерфейс)
  //# 
  //# eventData - объект, который будет передан в обработчик.
  //# 
  //# unbind( event_type, handler)
  //#   удаляет привязанные события из каждого элемента набора.
  	

  //назначает обработчик на все инпуты
  $("#form1 input").bind("click", universalEventHandler);

  //unbind - убирает обработчик
  $inp2.unbind("click", universalEventHandler);

  },

  bind2: function() {

  //назначение сразу нескольких обработчиков на разные события
  $inp1.bind({
    click: universalEventHandler,
    mouseenter: universalEventHandler
  });	

  //назначение обработчика на 2 события
  $inp2.bind("mouseenter mouseleave", event => {
    $inp2.toggleClass("bg-red");
  });




  },
  bind3: function() {

  //обращение к элементу, отправившему событие
  $inp1.bind("keydown", function() {
    log2($(this).val());
  });

  $inp2.bind("keyup", event=> {
    log2($(event.target).val());
  });
  	
  },


  bind4: function() {

  //с передачей data-объекта, который можно получить из event
  //эта фича используется редко, ведь обработчик - closure
  let outerVar = 477;	
  $inp1.bind("click", {msg: "my message."}, event=> {
    log2("event.data.msg:", event.data.msg);
  	  log2("outerVar:", outerVar);
  });

  },  
  
  
  
    
  
}





$(() => {

	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: TEMPLATE_FORM1,
		selectorsData: selectorsData1,
//		selectedOption: "on1",
		reloadSandboxOnChange: true,
		initFunction: ()=>{
			
		}
	});	
	
});



