


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
const testUrl = "../../testAjax/TestRequest";



//post запрос с отправкой json
const updateFilterUrl = "../../testAjax/updateTasksFilter";


/**
 * Примеры используют JqueryAjaxDemoServlet
 */

let selectorsData1 = {

  templates: function() {

    /*	
    Примеры типичных ajax-запросов
    */

    //get запрос с получением json
    $.get({
      url: sectionsUrl,
      dataType: "json",    //Тип данных, который Вы ожидаете от сервера

      //параметры запроса.
      data: { title: "Иван", id: 30 },
      //data: "title=Боря&id=50",  //можно задать и так


      success: (data, textStatus) => {
        log2("textStatus: ", textStatus);
        log2("data1: ", data);
        log2hr();
      },
      error: (jqXHR, textStatus, errorThrown) => {
        log2("textStatus:", textStatus);
        log2("errorThrown3:", errorThrown);
      }
    });

    //отправляем json на сервер
    $.post({
      url: updateFilterUrl,
      contentType: 'application/json',    //Формат, в котором данные отправляются на сервер.
      data: JSON.stringify(testJson),    //тело запроса
      success: (data, textStatus) => {
        log2("textStatus: ", textStatus);
        log2("data2: ", data);
        log2hr();
      },
      error: (jqXHR, textStatus, errorThrown) => {
        log2("textStatus:", textStatus);
        log2("errorThrown3:", errorThrown);
      }
    });

    //отправляем параметры запроса на сервер
    $.post({
      url: testUrl,
      data: testJson,    //параметры запроса (будут отправлены в теле запроса)

      //или в строковом виде
      //data: JSON.stringify(testJson),

      success: (data, textStatus) => {
        log2("textStatus: ", textStatus);
        log2("data3: ", data);
        log2hr();
      },
      error: (jqXHR, textStatus, errorThrown) => {
        log2("textStatus:", textStatus);
        log2("errorThrown3:", errorThrown);
      }
    });


  },



  ajax_get_json_file: function() {
    /*
    $.ajax(settings)
    */

    le("testJsonUrl");

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
      url: testUrl,
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
    $.get({
      url: testJsonUrl,
    }).done(debugSuccessHandler)
      .fail(debugErrorHandler)
      .always(debugCompleteHandler);
  },

  ajax_promise: function() {

    /*
    jqXHR имплементирует интерфейс Promise, что позволяет делать асинхронные запросы синхронными
    */

    $.get({
      url: testJsonUrl,
    })
      .then(function(response) {
        log2('Success:', response);
      })
      .catch(function(error) {
        log2('Error:', error);
      });
  },

  ajax_promise2: async function() {

    /*
    Синхронное получение данных через Promise
    */
    try {
      let result = await $.get({
        url: testJsonUrl,
      });
      log2('Success:', result);

    } catch (err) {
      log2('Error:', err);
    }



  },


  ajax_post_form: function() {

    //отправляем данные формы на сервер
    var serializedForm = $form1.serialize();
    $.ajax({
      url: testUrl,
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
        url: testUrl,
        type: 'POST',
        data: serializedForm,
        success: debugSuccessHandler,
        error: debugErrorHandler,
      });
    });

  },



  short_format: function() {
    /*
    $.post( url [, data ] [, success ] [, dataType ] )
    $.get( url [, data ] [, success ] [, dataType ] )
    $.post(settings)
    $.get(settings)
      Являются сокращением метода ajax.
    */

    $.get({
      url: testJsonUrl,
      data: { 'key': '123' },
      success: debugSuccessHandler,
      error: debugErrorHandler
    });

    //get запрос с получением json
    $.get({
      url: sectionsUrl,
      dataType: "json",    //Тип данных, который Вы ожидаете от сервера
      data: { title: "Иван", id: 30 },    //параметры запроса.
      success: (data, textStatus) => {
        log2("textStatus: ", textStatus);
        log2("data: ", data);
      },
      error: (jqXHR, textStatus, errorThrown) => {
        log2("textStatus:", textStatus);
        log2("errorThrown:", errorThrown);
      }
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

