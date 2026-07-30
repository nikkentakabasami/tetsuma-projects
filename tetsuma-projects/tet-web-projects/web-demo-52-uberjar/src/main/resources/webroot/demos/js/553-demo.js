


let testJson = {
  title: "Иван",
  id: 30
}

let badTestJson = {
  title: "Иван",
  badField: 30
}


//post запрос с отправкой json
const updateFilterUrl = "../../testAjax/updateTasksFilter";


/**
 * Примеры используют JqueryAjaxDemoServlet
 */

let selectorsData1 = {

  XMLHttpRequest_intro: function() {
    /*
    XMLHttpRequest
      встроенный в браузер класс, который даёт возможность делать ajax-запросы
    Несмотря на приставку XML - отправляет любые запросы.
  	
    Считается устаревшим, рекомендуется использовать fetch
    Не имплементирует Promise!
  	
    xhr = new XMLHttpRequest();
    Конструктор обычно не содержит параметров.
    	
    Методы:
  	
    ---
    xhr.open(method, url, async, user, password)
    инициализирует запрос
  	
    async
    (default true)
    Получать данные асинхронно (через коллбэки)
    Если false - метод подвиснет до получения ответа
    	
    ---
    xhr.send(body)
    Отправляет запрос на сервер.
  	
  	
    body
    Данные для отправки на сервер (опционально)
    Blob, an ArrayBuffer, a TypedArray, a DataView, a FormData, a URLSearchParams, or a string.
    
    ---
    xhr.abort();
    Отмена запроса.
    Генерирует событие abort.		
    	
    ---
    xhr.setRequestHeader(header, value)		
    Задание заголовков
  	
    	
    */

    //Простейший пример - загрузка json
    let xhr = new XMLHttpRequest();
    xhr.open("GET", testJsonUrl);
    xhr.send();
    xhr.onload = (event) => {
      log2(`Готово, получили ${xhr.response.length} байт`);
      log2("data: ", xhr.responseText);
    };

  },


  xhr_template_example: function() {

    /*
    Полный базовый пример использования.
    GET-запрос с параметрами.
  	
    Выполняем запросы через цепочку промисов, чтобы они выполнялись последовательно.
    */

    //простой запрос
    sendGetRequest("r1", testRequestUrl)
      .then(r => {
        //простой запрос
        return sendGetRequest("r2", testJsonUrl);
      }).then(r => {
        //запрос с параметрами
        return sendGetRequest("r3", testRequestUrl, { key1: "value1", key2: "value2" });
      }).then(r => {
        //запрос с ошибкой на сервере
        return sendGetRequest("r4", testRequestUrl, { errorTest: true });
      }).then(r => {
        //проверяем 404
        return sendGetRequest("r5", "../../badUrl");
      }).then(r => {
        //проверяем несуществующий адрес
        return sendGetRequest("r6", "http://badRequest.com/");
      });


    function sendGetRequest(requestId, url, paramsObj) {
      return new Promise(function(resolve, reject) {

        let xhr = new XMLHttpRequest();

        //параметры get-запроса надо добавлять в url:
        if (paramsObj) {
          const urlParams = new URLSearchParams(paramsObj).toString();
          url = `${url}?${urlParams}`;
        }
				log2(`${requestId}. onload. GET url: ${url}`);

        xhr.open("GET", url);  //с параметрами

        xhr.send();

        //load - загрузка завершена
        xhr.onload = (event) => {
          log2(`${requestId}. onload. status: ${xhr.status}, statusText: ${xhr.statusText}`);

          if (xhr.status != 200) {
            log2(`${requestId}. Ошибка на сервере!`);
          }

          log2(`${requestId}. onload. Готово, получили ${xhr.response.length} байт`);
          log2(`${requestId}. onload. data:`);
					log2Green(xhr.responseText);
					log2nl();
          resolve();
        };

        //error - ошибка
        xhr.onerror = (event) => {
          log2(`${requestId}. onerror. Ошибка! status: ${xhr.status}, statusText: ${xhr.statusText}`);
					log2nl();
          resolve();
        };

        //progress - запускается периодически, по мере загрузки данных
        xhr.onprogress = (event) => {
          if (event.lengthComputable) {
            log2(`${requestId}. onprogress. Получено ${event.loaded} из ${event.total} байт`);
          } else {
            log2(`${requestId}. onprogress. Получено ${event.loaded} байт`);
          }
        };

        //так же обработчики можно добавлять в стиле:
        //addEventListener("load", (event) => { })			

      });//promise
    }
  },




  XMLHttpRequest_post_json: function() {
    /*
    отправляем json на сервер
    */

    let xhr = new XMLHttpRequest();
    xhr.open("POST", testRequestUrl);

    //xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");  //кидает ошибку!
    xhr.setRequestHeader("Content-Type", "application/json");
    let data = JSON.stringify(testJson);
    xhr.send(data);

    xhr.onload = (event) => {
      log2("data: ");
			log2Green(xhr.responseText)			
			
    };

  },
  XMLHttpRequest_post_form: function() {
    /*
    Отправляем HTML Form data
    */

    xhr = new XMLHttpRequest();
    xhr.open("POST", testRequestUrl);

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let data = "key1=value1&key2=value2";
    //let data = JSON.stringify(testJson);  //так не получится

    xhr.send(data);

    xhr.onload = (event) => {
      log2("data: ", xhr.responseText);
    };



  },

  XMLHttpRequest_post_xml: function() {
    /*
    post XML data
    */

    xhr = new XMLHttpRequest();
    xhr.open("POST", testRequestUrl);

    xhr.setRequestHeader("Content-Type", "application/xml");

    let data = `<?xml version="1.0" encoding="utf-8"?>
<Request>
  <Login>login</Login>
  <Password>password</Password>
</Request>`;

    xhr.send(data);

    xhr.onload = (event) => {
      log2("data: ", xhr.responseText);
    };



  },

  XMLHttpRequest_events: function() {
    /*
    События
    Используются при асинхронных запросах.
    	
    Могут добавляться двумя способами:

    xhr.addEventListener("load", (event) => { })
    xhr.onload = event => { }

  	
    У всех событий есть атрибуты:
  	
    event.loaded
      сколько байт body загружено

    event.total
      всего байт в body (Content-Length)


    event.lengthComputable
      можно ли вычислить прогресс?		
  	
    ---
    load
    Запускается при успешном завершении запроса.
    Но всё же необходимы проверки на статусы вроде 404!

    error
    срабатывает, если запрос не достиг сервера.
    Ошибки вроде 404 не запускают это событие, так как ответ всё таки пришёл!

    progress
    запускается периодически, по мере загрузки данных		
  	
    */


  },


  XMLHttpRequest_get_sync: function() {
    /*
    Синхронный запрос
      Не рекомендуется.
    */

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



}





function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT,
    workPanelTemplate: TEMPLATE_FORM1,
    selectorsData: selectorsData1,
    //	jquerySelectorsMode: true,
    lfMode: false,
    afterSandboxReload: null,
    selectedOption: "custom",
    debugMode: false,
    autoscrollLog2: true,
    initFunction: () => {
    }
  };
}

