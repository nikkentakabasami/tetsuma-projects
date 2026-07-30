


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


  fetch_intro: async function() {

    /*
    fetch
      Современная замена XMLHttpRequest. 
      Доступен как глобальная функция. 
      Не использует коллбэки, основан на promise.
    */

    //get-запрос в синхронном стиле
    try {
      const response = await fetch(sectionsUrl);

      log2(`status: ${response.status}, statusText: ${response.statusText}`);

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

      log2(result);
    } catch (error) {
      log2("Error:", error.message);
    }

  },


  fetch_options: function() {
    /*
    Опции
  	
    let promise = fetch(url, [options])

    ---
    method
    default: "GET"

    ---
    body
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
    headers

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




  },

  fetch_get: function() {
    /*
    Пример get-запросов.
  	
    выполняем запросы последовательно, через цепочку промисов
    */

    sendGetRequest(testJsonUrl)
      .then(r => {
        return sendGetRequest(testRequestUrl, { key1: "value1", key2: "value2" });
      })
      .then(r => {
        return sendGetRequest("../../badUrl");
      });
    log(sendGetRequest);

  },


  fetch_post: function() {
    /*
    Пример post-запросов (отправляющих параметры в формате x-www-form-urlencoded).
    */
    sendPostFormRequest(testRequestUrl, { key1: "value1", key2: "value2" })
      .then(r => {
        return sendPostFormRequest("../../badUrl");
      });
    log(sendPostFormRequest);
  },



  fetch_post_json: async function() {

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



  fetch_async: function() {

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




//функция-шаблон для выполнения get-запросов
async function sendGetRequest(url, paramsObj) {

  //get-запрос в синхронном стиле
  try {

    if (paramsObj) {
      const urlParams = new URLSearchParams(paramsObj).toString();
      url = `${url}?${urlParams}`;
    }
    log2(`get: ${url}`);


    const response = await fetch(url);
    //const response = await fetch("../../badUrl");

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

    log2Green(result, "\n");
    return result;
  } catch (error) {
    log2("Error:", error.message);
  }


}



//функция-шаблон для выполнения get-запросов
async function sendPostFormRequest(url, paramsObj) {

  try {

    log2(`post form: ${url}`);

    let fetchOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
      },
      body: new URLSearchParams(paramsObj),
    };

    const response = await fetch(url, fetchOptions);

    let contentType = response.headers.get('Content-Type');
    log2(`status: ${response.status}, statusText: ${response.statusText}, contentType: ${contentType}`);

    //Ошибки вроде 404 не кидают исключения.
    if (!response.ok) {
      throw new Error(`Bad response status: ${response.status}`);
    }

    const result = await response.text();

    log2Green(result, "\n");
    return result;
  } catch (error) {
    log2("Error:", error.message);
  }


}
