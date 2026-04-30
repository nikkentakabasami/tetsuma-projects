


let testJson = {
  title: "Иван",
  id: 30
}

let badTestJson = {
  title: "Иван",
  badField: 30
}


const sectionsUrl = "../../testAjax/getSectionsJson";
const testJsonUrl = "../misc/test.json";

//post запрос с параметрами (из формы)
const testPostUrl = "../../testAjax/TestPostRequest";

//post запрос с отправкой json
const updateFilterUrl = "../../testAjax/updateTasksFilter";



let selectorsData1 = {

	templates: function() {
		
	//# Примеры типичных ajax-запросов
		
	//get запрос с получением json
	$.get({
	  url: sectionsUrl,
	  dataType: "json",    //Тип данных, который Вы ожидаете от сервера
	  data: {title: "Иван", id: 30},    //параметры запроса.
	  success: (data, textStatus)=>{
	    log2("textStatus: ", textStatus);
	    log2("data1: ", data);
	  },
	  error: (jqXHR, textStatus, errorThrown)=>{
	    log2("textStatus:", textStatus);
	    log2("errorThrown3:", errorThrown);
	  }
	});

	//отправляем json на сервер
	$.post({
	  url: updateFilterUrl,
	  contentType: 'application/json',    //Формат, в котором данные отправляются на сервер.
	  data: JSON.stringify(testJson),    //тело запроса
	  success: (data, textStatus)=>{
	    log2("textStatus: ", textStatus);
	    log2("data2: ", data);
	  },
	  error: (jqXHR, textStatus, errorThrown)=>{
	    log2("textStatus:", textStatus);
	    log2("errorThrown3:", errorThrown);
	  }
	});

	//отправляем параметры запроса на сервер
	$.post({
	  url: testPostUrl,
	  data: JSON.stringify(testJson),    //параметры запроса (будут отправлены в теле запроса)
	  success: (data, textStatus)=>{
	    log2("textStatus: ", textStatus);
	    log2("data3: ", data);
	  },
	  error: (jqXHR, textStatus, errorThrown)=>{
	    log2("textStatus:", textStatus);
	    log2("errorThrown3:", errorThrown);
	  }
	});	


	},	
	
	
	
  ajax_get_json_file: function() {
	//# $.ajax(settings)
	
	//получаем json из файла
	$.ajax({
	  url: testJsonUrl,
	  type: "GET",
	  dataType: "json",		//Тип данных, который Вы ожидаете от сервера (text, html, xml, json, jsonp, script).
	  success: debugSuccessHandler,
	  error: debugErrorHandler,
	  complete: debugCompleteHandler
	});

  },
  ajax_get_json_from_server: function() {

	//получаем json из сервлета
	//отправляем на сервер параметры запроса (в testJson)
	$.ajax({
	  url: sectionsUrl,
	  type: "GET",
	  dataType: "json",		//Тип данных, который Вы ожидаете от сервера
	  data: testJson,		//get-запросы не имеют тела. Так что в data передаются параметры запроса.
	  success: debugSuccessHandler,
	  error: debugErrorHandler,
	  complete: debugCompleteHandler
	});

  },
  ajax_post_json: function() {

	//отправляем json на сервер
	$.ajax({
	  url: "../../testAjax/updateTasksFilter",
	  type: 'POST',
	  contentType: 'application/json',		//Формат, в котором данные отправляются на сервер. По умолчанию это параметры запроса.
	  data: JSON.stringify(testJson),		//тело запроса
	  success: debugSuccessHandler,
	  error: debugErrorHandler,
	  complete: debugCompleteHandler
	});


  },
  ajax_post_request_params: function() {

	//отправляем параметры запроса на сервер
	$.ajax({
	  url: testPostUrl,
	  type: 'POST',
	  data: testJson,		//параметры запроса (будут отправлены в теле запроса)
	  contentType: 'application/x-www-form-urlencoded; charset=UTF-8',	//можно не указывать - он такой по умолчанию
	  success: debugSuccessHandler,
	  error: debugErrorHandler,
	  complete: debugCompleteHandler
	});


  },
  ajax_handlers_alternative: function() {

	//альтернативный способ задания обработчиков через объект jqXHR (возвращается $.ajax())
	$.ajax({
	  url: testJsonUrl,
	  type: "GET"
	})
	  .done(debugSuccessHandler)
	  .fail(debugErrorHandler)
	  .always(debugCompleteHandler);


  },
  ajax_post_form: function() {

	//отправляем данные формы на сервер
	var serializedForm = $form1.serialize();
	$.ajax({
	  url: testPostUrl,
	  type: 'POST',
	  data: serializedForm,
	  contentType: 'application/x-www-form-urlencoded; charset=UTF-8',	//можно не указывать - он такой по умолчанию
	  success: debugSuccessHandler,
	  error: debugErrorHandler,
	  complete: debugCompleteHandler
	});
  },
  
  
  ajax_post_form2: function() {


	//перехватываем submit формы
	$form1.submit(function(event) {
		event.preventDefault();
		var serializedForm = $form1.serialize();
		$.ajax({
		  url: testPostUrl,
		  type: 'POST',
		  data: serializedForm,
		  success: debugSuccessHandler,
		  error: debugErrorHandler,
		});
	});
	
  },
  
  
  
  xhr_get_sync: function() {
	//# XMLHttpRequest - встроенный в браузер класс, который даёт возможность делать ajax-запросы

	//GET-запрос с использованием XMLHttpRequest. 
	//Синхронный запрос
	let xhr = new XMLHttpRequest();
	xhr.open("GET", sectionsUrl, false); // false для синхронного вызова
	xhr.send();

	log2(`status: ${xhr.status}, statusText: ${xhr.statusText}`);
	if (xhr.status === 200) {
	  log2(`responseType: ${xhr.responseType}`);
	  log2("data: ", xhr.responseText);
	} else {
	  log2("Ошибка загрузки");
	}

  },


  xhr_get_async: function() {

	//GET-запрос с использованием XMLHttpRequest. 
	//Асинхронный запрос
	let xhr = new XMLHttpRequest();
	xhr.open("GET", sectionsUrl); //вызов асинхронный по умолчанию
	//  xhr.open("GET", "../../badUrl"); //проверяем ошибку

	xhr.send();
	
	//load - загрузка завершена
	xhr.onload = (event) => {
	  log2(`status: ${xhr.status}, statusText: ${xhr.statusText}`);

	  if (xhr.status != 200) {
		log2("Ошибка");
		return;
	  }
	  log2(`Готово, получили ${xhr.response.length} байт`);
	  log2("data: ", xhr.responseText);
	};

	//error - ошибка
	xhr.onerror = (event) => {
	  log2(`status: ${xhr.status}, statusText: ${xhr.statusText}`);
	  log2("Запрос не удался");
	};

	//progress - запускается периодически, по мере загрузки данных
	xhr.onprogress = (event) => {
	  if (event.lengthComputable) {
		log2(`Получено ${event.loaded} из ${event.total} байт`);
	  } else {
		log2(`Получено ${event.loaded} байт`);
	  }
	};

	//так же обработчики можно добавлять в стиле:
	//addEventListener("load", (event) => { })
  },

  fetch_get_sync: async function() {

	//# fetch - Современная замена XMLHttpRequest. Доступен как глобальная функция. 
	//# Не использует коллбэки, основан на promise.
	
	//get-запрос в синхронном стиле
	try {
	  const response = await fetch(sectionsUrl);
	  log(`status: ${response.status}, statusText: ${response.statusText}`);
	  if (!response.ok) {
		throw new Error(`Bad response status: ${response.status}`);
	  }

	  const result = await response.text();
	  //получение ответа в прочих форматах
	  //const result = await response.json();
	  //const result = await response.arrayBuffer();
	  //const blob = await response.blob();
	  //image.src = URL.createObjectURL(blob);	  

	  //в виде потока
	  //const stream = response.body.pipeThrough(new TextDecoderStream());
	  //for await (const value of stream) {console.log(value);}	  

	  log2(result);
	} catch (error) {
	  log2(error.message);
	}

  },

  fetch_get_async: async function() {

	  //get-запрос в асинхронном стиле (fetch возвращает Promise)
	  fetch(sectionsUrl)
	  	.then(response => {
	  		log2(`status: ${response.status}, statusText: ${response.statusText}`);
	  		if (!response.ok) {
	  			log2("Error!");
	  		}
	  		response.text().then(result=>{
	  			log2(result);
	  		});
	  });
  },    
  
  
  fetch_post_params_sync: async function() {

	//отправка POST запроса с использованием fetch.
	//при этом на сервер отправляются параметры запроса
	try {
	  const response = await fetch(testPostUrl, {
		method: "POST",

		//отправка формы
		headers: {
		  "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
		},
		body: new URLSearchParams(testJson),

	  });
	  log2(`status: ${response.status}, statusText: ${response.statusText}`);
	  if (!response.ok) {
	    throw new Error(`Bad response status: ${response.status}`);
	  }
	  const result = await response.text();

	  log2(result);
	} catch (error) {
	  log2(error.message);
	}

  },

  fetch_post_json_sync: async function() {

	//отправка POST запроса с использованием fetch.
	//при этом на сервер отправляются json-данные (класс TSGTaskFilter)
	try {
	  const response = await fetch(updateFilterUrl, {
		method: "POST",

		//отправка json
		headers: {
		  "Content-Type": "application/json",
		},
		body: JSON.stringify(testJson),
		//body: JSON.stringify(badTestJson),
		
	  });
	  log2(`status: ${response.status}, statusText: ${response.statusText}`);
	  if (!response.ok) {
	    throw new Error(`Bad response status: ${response.status}`);
	  }
	  const result = await response.text();

	  log2(result);
	} catch (error) {
	  log2(error.message);
	}

  },

  short_format: function() {
	
	//# $.post( url [, data ] [, success ] [, dataType ] )
	//# $.get( url [, data ] [, success ] [, dataType ] )
	//# $.post(settings)
	//# $.get(settings)
	//#   Являются сокращением метода ajax.
	
	$.get({
	  url: testJsonUrl,
	  data: { 'key': '123' },
	  success: debugSuccessHandler,
	  error : debugErrorHandler
	});
	
	//get запрос с получением json
	$.get({
	  url: sectionsUrl,
	  dataType: "json",    //Тип данных, который Вы ожидаете от сервера
	  data: {title: "Иван", id: 30},    //параметры запроса.
	  success: (data, textStatus)=>{
	    log2("textStatus: ", textStatus);
	    log2("data: ", data);
	  },
	  error: (jqXHR, textStatus, errorThrown)=>{
	    log2("textStatus:", textStatus);
	    log2("errorThrown:", errorThrown);
	  }
	});	
	
	
	},
  
	


}





$(() => {

	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: TEMPLATE_FORM1,
		selectorsData: selectorsData1,
		selectedOption: "templates",
		initFunction: ()=>{
			
		}
	});	
	


  
  

});
