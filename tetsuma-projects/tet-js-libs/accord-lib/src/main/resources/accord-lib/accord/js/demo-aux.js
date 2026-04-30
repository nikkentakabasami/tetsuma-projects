/**
 * Вспомогательные функции
 * 
 */

//тестовый обработчик для всех типов событий - выводит данные из event в лог
function universalEventHandler(event) {
  log2();

  event.preventDefault();

  log2(
	//event.type - тип события: click, mouseover...
	`type: ${event.type}, ` +

	//event.currentTarget - Элемент, на который назначен обработчик события в данный момент.
	`currentTarget: ${event.currentTarget?.id}, ` +
	
	//event.target - самый глубокий элемент, на котором произошло событие.
	//То есть, это элемент, на котором фактически сработало событие
	`target: ${event.target?.id}`

  );

  let type = event.type;


  if (type == 'mouseover' || type == 'mouseout' || type == 'mouseenter' || type == 'mouseleave') {

	log2(
	  //event.relatedTarget - mouseover(элемент, с которого пришел курсор мыши), mouseout(на который перешел)
	  `relatedTarget: ${event.relatedTarget?.id}`
	);
  }



  if (type == 'mousedown' || type == 'mouseup' || type == 'click') {

	log2(
	  //event.button - Какая кнопка мыши была нажата (0-левая, 1-средняя, 2-правая)
	  `button: ${event.button}, ` +

	  //event.pageX - координаты относительно документа, учитывая прокрутку
	  `pageX: ${event.pageX}, pageY: ${event.pageY},` +

	  //event.clientX - координаты кликнутой точки относительно окна
	  `clientX: ${event.clientX}, clientY: ${event.clientY},` +

	  //event.which - jquery атрибут. Для нажатия клавиши = event.keyCode. Для нажатия кнопки мыши = event.button
	  `\nwhich: ${event.which}, ` +

	  //нажаты ли клавиши-модификаторы
	  `shiftKey: ${event.shiftKey}, altKey: ${event.altKey}, ctrlKey: ${event.ctrlKey}, metaKey: ${event.metaKey}`
	);

  }


  if (type == 'keydown' || type == 'keyup' || type == 'keypress') {

	log2(
	  //code - название клавиши. Примеры: KeyD, Digit5, F2
	  `code: ${event.originalEvent.code}, ` +

	  //key - символьное значение клавиши: F3, Q, q, Alt...
	  `key: ${event.key}, ` +

	  //keyCode - Устаревший. Возвращает числовой код клавиши.
	  `keyCode: ${event.keyCode}, ` +

	  //which ==event.keyCode
	  `which: ${event.which}, ` +

	  //При долгом нажатии клавиши возникает автоповтор: keydown срабатывает снова и снова
	  //Для таких событий event.repeat=true.
	  `repeat: ${event.originalEvent.repeat}, ` +

	  //нажаты ли клавиши-модификаторы
	  `\nshiftKey: ${event.shiftKey}, altKey: ${event.altKey}, ctrlKey: ${event.ctrlKey}, metaKey: ${event.metaKey}`
	);

  }

}



/*
function universalDemoHandler2(event) {
  event.preventDefault();

  let r = {
	type: event.type,
	currentTarget: event.currentTarget?.id,
	target: event.target?.id,
  };
  
  logObject2(r,"event");
}
*/


//-------------ajax обработчики-------------------

//Функция, которая будет вызвана в случае удачного завершения запроса к серверу. 
//data	:данные, присланные сервером и уже прошедшие предварительную обработку (которая отлична для разных dataType)
//textStatus	: Статус выполнения ("success", "notmodified", "nocontent", "error", "timeout", "abort", "parsererror")
function debugSuccessHandler(data, textStatus) {
	log2("# debugSuccessHandler");
  let type = typeof data;
  log2("textStatus: ", textStatus);
  log2("data type:", type);
  
  if (type=="object"){
	logJson2("data", data);
  } else {
	log2("data: ", data);
  }
  
  highlightLogComments2();
  
//  logObject2(data, "data")  
  
}

//Функция, которая будет вызвана в случае неудачного завершения запроса к серверу.
function debugErrorHandler(jqXHR, textStatus, errorThrown) {
	log2("# debugErrorHandler");
  log2("textStatus:", textStatus);
  log2("errorThrown:", errorThrown);
  highlightLogComments2();
}


//Функция, которая будет вызвана после завершения ajax-запроса.
//Вызывается позднее success и error.
function debugCompleteHandler(jqXHR, textStatus) {
  log2("# debugCompleteHandler");
  log2("textStatus:", textStatus);
  highlightLogComments2();
}


