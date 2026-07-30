

let w1, w2;

let selectorsData1 = {

  window_attr: `
window.closed;	//Закрыто ли окно
window.name;	//Имя окна
window.opener.name;	//Окно, открывшее это окно
window.parent.name;	//родительское окно


//Внешняя высота и ширина
window.outerWidth;
window.outerHeight;

window.innerWidth;
window.innerHeight;

//Положение окна
window.pageXOffset;
window.pageYOffset;
	
	`,

  window_open: `
/*
window.open(url, winName, winParams)
  Cоздает новое окно браузера , аналогично команде "Новое окно" в меню браузера.
  Возвращает ссылку на новое окно (или null, если окно открыть не удалось)
  Если окно с именем winName уже существует, то url загрузится в существующее.

  Окно может не открыться, если включено блокирование всплывающих сообщений
  Использование не рекомендуется - плохой стиль. 
*/

//открыть в новой вкладке
w1 = window.open(testUrl); !
	`,
  window_open2() {
    //deprecated
    w1 = window.open(testUrl, "myWindow1", "toolbar=no, scrollbars=yes, location=no");
  },
  window_open_popup() {
    //в режиме всплывашки
    w1 = window.open(testUrl, "myWindow1", "popup=true");

  },
  window_open_size() {
    //с заданием размеров и положения
    w1 = window.open(testUrl, "myWindow1", "width=400,height=400,left=100,top=100,popup=true");

  },
  window_open_dynamic() {
    //с манипуляцией окном	
    w1 = window.open("about:blank", "hello", "width=200,height=200");
    w1.document.write("Привет, мир!");

  },
  window_open_events() {
    /*
    События window:

    onresize – событие изменения размера окна.
    onscroll – событие при прокрутке окна.
    onload – полностью загрузилась страница со всеми ресурсами.
    onfocus/onblur – получение/потеря фокуса.	
    */

    w1 = window.open("../demos_200_html_basics/colors.html", 'example', 'width=600,height=400');

    w1.onload = function() {
      let div = w1.document.createElement('div');
      let body = w1.document.body;

      div.innerHTML = 'Добро пожаловать!'
      div.style.fontSize = '30px'

      // вставить первым элементом в body нового окна
      body.insertBefore(div, body.firstChild);
    }



  },
  window_onerror() {
    /*
    onerror
      Обработчик ошибок.
    */

    //подавление сообщений об ошибках в скрипте:
    window.onerror = null;

    //или так.
    //window.onerror = ()=>{return true};

    throw new Error("test2");
  },
  window_open_click() {

    w1 = window.open("../demos_200_html_basics/colors.html", 'example', 'width=600,height=400');

    $(w1).click(e => {
      //закрытие окна
      w1.close();
    })


  },

}

function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
    selectedOption: "url2",
    debugMode: false,
    initFunction: () => {
    }
  };
}









