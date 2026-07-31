


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


let r1, r2;


/**
 * Примеры используют JqueryAjaxDemoServlet
 */

let selectorsData1 = {


  async fetch_intro() {

    /*
    fetch
      Современная замена XMLHttpRequest. 
      Доступен как глобальная функция. 
      Не использует коллбэки, основан на promise.

      fetch кидает ошибки когда некорректный url или сетевая ошибка.
      Не кидает при серверных ошибках вроде 404, 504. Их надо проверять через response.ok.

      let promise = fetch(resource, [options])
    	
      resource
      url ресурса. (String, URL)
      Так же это может быть объект Request
    	
      options
      настройки

      Возвращает Promise, который возвращает Response.

      request = new Request("../hi.jpg");
    	
      response = await fetch(request);
      response = await fetch("../hi.jpg");

      const response = await fetch("../dopost", {
        method: "POST",
        // …
      });			
    */

    //простой get-запрос в синхронном стиле
    try {
      const response = await fetch(sectionsUrl);

      log2(`status: ${response.status}, statusText: ${response.statusText}`);

      //Ошибки вроде 404 не кидают исключения.
      if (!response.ok) {
        throw new Error(`Bad response status: ${response.status}`);
      }

      const result = await response.text();

      log2(result);
    } catch (error) {
      log2("Error:", error.message);
    }

  },

  async universal_template() {
    /*
    Общий шаблон для использования fetch
    */

    try {
      let dataToSend = { key1: "value1", key2: "value2" };


      let url = testRequestUrl;

      /*
      В случае get-запроса всё выглядит проще:
    	
      //добавление параметров
      const urlParams = new URLSearchParams(paramsObj);
      url = `${url}?${urlParams}`;
      const response = await fetch(url);
      */

      const response = await fetch(url, {
        method: "POST",
        headers: {
          //"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
          "Content-Type": "application/json",
        },
        //body: new URLSearchParams(dataToSend),  //передача параметров
        body: JSON.stringify(dataToSend),		//передача json
      });

      let contentType = response.headers.get('Content-Type');

      log2(`status: ${response.status}, statusText: ${response.statusText}, contentType: ${contentType}`);

      //Ошибки вроде 404 не кидают исключения.
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

      log2Green(result, true);
    } catch (error) {
      log2("Error:", error.message);
    }

  },


  async fetch_options() {
    /*
    Основные опции fetch

    ---
    options.method
    default: "GET"

    ---
    options.body
    Тело запроса. Может иметь типы:

    String
    ArrayBuffer
    TypedArray
    DataView
    Blob
    File
    URLSearchParams
    FormData
    ReadableStream

    ---
    options.headers

     headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    //  "Content-Type": "application/json",
      },

    //заголовки можно задать объектом Headers
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    ...
    headers: myHeaders,
    */
    log2(sendPostFormRequest);

  },

  async Request1() {
    /*
    Request
		Содержит параметры запроса.
		В конструктор Request передаются те же самые параметры, что и в fetch.
		
		Его можно передать в fetch(r) в качестве параметра.
		Это позволяет легко выполнять несколько одинаковых запросов.
		
		Request - немутабельный объект, его нельзя изменить после создания!
  	
    Тело запроса - это поток. Его нельзая считать дважды.
    Поэтому чтобы выполнить post-запрос 2 раза подряд - нужно клонировать Request.
    */


    //с дублированием get-запросов всё просто, ведь у них есть только url
    let getRequest = new Request(testJsonUrl);
    let response = await fetch(getRequest);
    r1 = await response.text();
    log2("get1:", r1);
    log2hr();

    response = await fetch(getRequest);
    r1 = await response.text();
    log2("get2:", r1);
    log2hr();

    //А вот post-запросы нужно клонировать.
    const request1 = new Request(testRequestUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username: "example1" }),
    });

    const request2 = request1.clone();

    //не сработает
    //request2.body = JSON.stringify({ username: "example2" });
    //let b = await streamToString(request2.body);
    //log2("new body:", b);

    const response1 = await fetch(request1);
    let r = await response1.text();
    log2("post1:", r);
    log2hr();

    const response2 = await fetch(request2);
    r = await response2.text();
    log2("post2:", r);


  },

  async Request2() {
    /*
    Request
		
		Request - немутабельный объект.
		Поэтому создать копию с другими параметрами можно только так:
    */
    const request1 = new Request(testRequestUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username: "example1" }),
    });


    // Создаем копию request1 с другим телом
    const request2 = new Request(request1.url, {
      method: request1.method,
      headers: request1.headers,
      body: JSON.stringify({ username: "example2" }),
    });

    const response1 = await fetch(request1);
    const response2 = await fetch(request2);

    r1 = await response1.text();
    r2 = await response2.text();

    le(`
		r1;
		r2;
		`);

  },

  async AbortController() {
    /*

    options.signal
    Позволяет задавать объект, способный отменять запросы

  	
    */

    try {
      const controller = new AbortController();

      const response = await fetch(testJsonUrl, {
        signal: controller.signal,
      });

      //отмена запроса
      controller.abort();

      let result = await response.text();
      log2("result:", result);
    } catch (error) {
      log2("Error:", error.message);
    }

  },


  async Response() {
    /*
    Response

    const response = await fetch(url);

    ---
    response.text()
    response.json()
    response.formData()
    response.blob()
    response.arrayBuffer()
    Получение ответа в разных форматах

    ---
    response.headers
      Заголовки

    response.headers.get('Content-Type');

    ---
    response.status
      Возвращает числовой статус запроса

    ---
    response.statusText
      Текстовое описание response.status

    ---
    response.ok
      true, если response.status==200
    	
    ---
    response.clone()
    клонирует ответ, что позволяет считать с него данные дважды

    const response2 = response.clone();

    const result1 = await response.json();
    const result2 = await response2.json();
  	
    */
  },




  async fetch_get() {
    /*
    Пример get-запросов.
  	
    выполняем запросы последовательно, через цепочку промисов
    */

    Promise.resolve()
      .then(r => {
        return sendGetRequest(testJsonUrl);
      })
      .then(r => {
        return sendGetRequest(testRequestUrl, { key1: "value1", key2: "value2" });
      })
      .then(r => {
        return sendGetRequest("../../badUrl");
      });
    log(sendGetRequest);

  },

  async fetch_post() {
    /*
    Пример post-запросов
    */
    Promise.resolve()
      .then(r => {
        //отправка параметров в формате x-www-form-urlencoded).
        return sendPostFormRequest(testRequestUrl, { key1: "value1", key2: "value2" });
      })
      .then(r => {
        return sendPostFormRequest("../../badUrl");
      })
      .then(r => {
        //отправка json-объекта
        return sendPostFormRequest(testRequestUrl, testJson, true);
      });

    log(sendPostFormRequest);
  },





  async fetch_async() {

    //get-запрос в асинхронном стиле
    fetch(sectionsUrl)
      .then(response => {
        log2(`status: ${response.status}, statusText: ${response.statusText}`);
        if (!response.ok) {
          log2("Error!");
        }
        response.text().then(result => {
          log2(result);
        });
      })
      .catch(error => {
        log2("error:", error);
      })
      .finally(() => {
        log2("fetch finally.");
      });

  },






  async fetch_post_json() {

    //отправка POST запроса с использованием fetch.
    //при этом на сервер отправляются json-данные
    try {
      const response = await fetch(testRequestUrl, {
        method: "POST",

        //отправка json
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(testJson),

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



  async load_image_blob() {


    //загрузка изображения как блоба
    const myRequest = new Request("../images/Walkingwithstrangers.jpg");
    fetch(myRequest)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.blob();
      })
      .then((response) => {

        //задаём блоб-ссылку в картинку
        let myImage = document.querySelector("#img1");
        myImage.src = URL.createObjectURL(response);
      });

  },






}






//функция-шаблон для выполнения get-запросов
async function sendGetRequest(url, paramsObj) {
  log2(`get: ${url}`);

  //get-запрос в синхронном стиле
  try {

    //get-запрос не содержит тела, параметры передаются внутри url
    if (paramsObj) {
      const urlParams = new URLSearchParams(paramsObj);
      url = `${url}?${urlParams}`;
    }
    log2(`get: ${url}`);


    const response = await fetch(url);

    let contentType = response.headers.get('Content-Type');

    log2(`status: ${response.status}, statusText: ${response.statusText}, contentType: ${contentType}`);

    //Ошибки вроде 404 не кидают исключения.
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

    log2Green(result, true);
    return result;
  } catch (error) {
    log2("Error:", error.message);
  }


}



//функция-шаблон для выполнения post-запросов
async function sendPostFormRequest(url, paramsObj, jsonMode = false) {
  log2(`post form: ${url}`);

  try {
    let fetchOptions;
    if (jsonMode) {
      fetchOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
        },
        body: new URLSearchParams(paramsObj),
      };

    } else {
      fetchOptions = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(testJson),
      };
    }

    const response = await fetch(url, fetchOptions);

    let contentType = response.headers.get('Content-Type');

    log2(`status: ${response.status}, statusText: ${response.statusText}, contentType: ${contentType}`);

    //Ошибки вроде 404 не кидают исключения.
    if (!response.ok) {
      throw new Error(`Bad response status: ${response.status}`);
    }

    const result = await response.text();

    log2Green(result, true);
    return result;
  } catch (error) {
    log2("Error:", error.message);
  }

}


async function streamToString(stream) {
  const response = new Response(stream);
  const text = await response.text();
  return text;
};



function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT,
    workPanelTemplate: TEMPLATE_FORM1,
    //workPanelTemplate: null,
    selectorsData: selectorsData1,
    //	jquerySelectorsMode: true,
    lfMode: false,
    afterSandboxReload: null,
    selectedOption: "custom",
    debugMode: false,
    autoscrollLog2: false,
    initFunction: () => {
    }
  };
}


