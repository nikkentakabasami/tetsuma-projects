

let selectorsData1 = {


  set_handler() {
    /*
    Задание обработчиков.
  	
    События обычно обрабатываются асинхронно (последовательно).
    Исключением является ситуация, когда событие инициировано из обработчика другого события.
  	
    1)Задание обработчика статически:
    */
    let staticHandlerButton = `<button type="button" class="acc-btn" onclick="log2('static added click')">testButton</button>`;
    $(".acc-form-button-panel1").append(staticHandlerButton);


    /*
    2)Задание обработчика через скрипт:
    */
    let btn1 = document.getElementById('btn1');

    //Так можно задать только один обработчик
    btn1.onclick = function() {
      log2('keep');
    }
    btn1.onclick = function() {
      log2('memories');
    }


    /*
    3) Установка обработчика по стандарту W3C
      Позволяет задать несколько обработчиков на один элемент
      Имя события указывается без префикса "on".

    element.addEventListener(event, handler, [options]);
    element.removeEventListener(event, handler, [options]);

    options.once
      если true - обработчик будет автоматически удалён после выполнения.

    options.capture:
      если true - обработчик будет задан на фазу погружения (capturing)
      по умолчанию обработчики задаются на фазу всплытия (bubbling)

    options.passive
      если true - обработчик никогда не вызовет preventDefault()		
  	
    исторически сложилось, что options может быть false/true, это то же самое, что {capture: false/true}
    */

    let btn2 = document.getElementById('btn2');
    btn2.addEventListener("click", event => {
      log2('already');
    });
    btn2.addEventListener("click", event => {
      log2('gone');
    });


    /*
    Задание обработчиков через jquery
    */

    $inp1.on("click", event => {
      log2('inp1 click');
    });


  },
  set_handler_object() {
    /*
    В качестве обработчика можно задать объект.
    */
    class Menu {
      handleEvent(event) {
        switch (event.type) {
          case 'mousedown':
            log2("Нажата кнопка мыши");
            break;
          case 'mouseup':
            log2("...и отжата");
            break;
        }
      }
    }

    let btn1 = document.getElementById('btn1');

    let menu = new Menu();
    btn1.addEventListener('mousedown', menu);
    btn1.addEventListener('mouseup', menu);

  },
  handler_this() {
    /*
    this
     Внутри обработчика события this ссылается на текущий элемент. 
    Это можно использовать, чтобы получить свойства или изменить элемент.

     <button onclick="alert(this.innerHTML)">Нажми меня</button>
  	
    */
    let btn1 = document.getElementById('btn1');
    btn1.onclick = function(event) {
      this.innerHTML = String(accordUtils.random(100));
    }

    let btn2 = document.getElementById('btn2');
    btn2.addEventListener("click", function(event) {
      this.innerHTML = String(accordUtils.random(100));
    })


  },

  bubbling_desc: `
	/*
	Принцип всплытия (bubbling)
	  Когда на элементе происходит событие, обработчики сначала срабатывают на нём, 
	потом на его родителе, затем выше и так далее, вверх по цепочке предков.

	Почти все события всплывают. (исключения: focus)
	Всегда можно узнать, на каком конкретно элементе произошло событие.

	event.target
	  Самый глубокий элемент, который вызывает событие.
	  Называется целевым элементом

	event.currentTarget
	  текущий элемент. Равен this.

	Прекращение всплытия

	event.stopPropagation()
	  Вызывается, если промежуточный обработчик решил, что событие полностью обработано, и надо остановить всплытие.
	Если у элемента есть несколько обработчиков на одно событие, то даже при прекращении всплытия все они будут выполнены.

	
	*/
	
	
	`,

  bubbling() {
    /*
    bubbling
    */
    let form1 = document.getElementById('form1');

    form1.addEventListener('click', function(event) {
      clearLog2();

      //самый глубокий элемент
      log2("click", "target:", event.target);

      //элемент, на который назначили обработчик - this
      log2("  currentTarget:", event.currentTarget);
    });


    $btn1.click(event => {
      //отключаем всплытие при клике на кнопке
      event.stopPropagation();
    });


  },





  capturing() {
    /*
    Погружение (capturing)
      Используется редко.

    есть 3 фазы прохода события:
      Фаза погружения (capturing phase) – событие сначала идёт сверху вниз.
      Фаза цели (target phase) – событие достигло целевого элемента.
      Фаза всплытия (bubbling stage) – событие начинает всплывать.

    В фазе погружения событие идёт по цепочке родителей вниз к элементу
    Обработчики событий addEventListener обычно работают на двух последних фазах.

    Для перехвата событий фазы погружения задаётся опция capture: true
    elem.addEventListener(..., {capture: true})
    elem.addEventListener(..., true)
    */


    //jquery не поддерживает напрямую capture
    $form1.get(0).addEventListener("click", e => {
      clearLog2();
    }, true);


    for (let elem of document.querySelectorAll('#form1 *')) {
      elem.addEventListener("click", e => {
        log2("Погружение:", e.currentTarget);
      }, true);
      elem.addEventListener("click", e => {
        log2("Всплытие:", e.currentTarget)

      });
    }


    //		let btn1 = document.getElementById('btn1');
    //		btn1.addEventListener("click", e => log2(`Погружение: ${elem.tagName}`), true);
    //		btn1.addEventListener("click", e => alert(`Всплытие: ${elem.tagName}`));




  },
  delegation() {
    /*
    Делегирование событий
      благодаря такой системе можно не назначать по обработчику на каждый элемент,
    а задать один обработчик для всех элементов (сколько бы их не было)
    */

    $form1.click(event => {
      let elem = event.target;
      $(elem).toggleClass("red-border");
    });



  },



  event() {
    /*
    Event
      Встроенный класс, основа встроенных событий.

    Конструктор:	
    let event = new Event(type[, options]);

    type
      тип события
      например "click", "my-event".

    options.bubbles
      default false
      если true, тогда событие всплывает.

    options.cancelable
      default false
      если true, тогда можно отменить действие по умолчанию.

    options.composed
      default false
      если true, тогда событие будет всплывать наружу за пределы Shadow DOM. 

    elem.dispatchEvent(event).
      запуск события на элементе
  	
    */


    $form1.on("hello", function(event) {
      log2("Привет от ", event.target);
    });


    let helloEvent = new Event("hello", { bubbles: true });
    $btn1.get(0).dispatchEvent(helloEvent);

    //метод trigger из jquery понимает только свои события
    let helloEvent2 = $.Event("hello", { bubbles: true });
    $btn2.trigger(helloEvent2);

    //не будет перехвачено, ведь options.bubbles = false
    $btn3.trigger("hello");

  },

  event_methods() {
    /*
    Методы событий:
  	
    event.stopPropagation()
      Вызывается, если промежуточный обработчик решил, что событие полностью обработано, и надо остановить всплытие.

    element.onclick = function(event) {
      ...
      event.stopPropagation()
    }

    event.stopImmediatePropagation()
      не только предотвращает всплытие, но и останавливает обработку событий на текущем элементе.

    event.preventDefault()
      Предотвратить поведение элемента по умолчанию
      Отменяемые события должны иметь опцию cancelable: true
      При отмене события - вызов elem.dispatchEvent(event) возвратит false. 
    И код, сгенерировавший событие, узнает, что продолжать не нужно.
  	
    */

    $form1.on('click', event => {
      log2("click", "target:", event.target);
    });

    $inp3.on('click', event => {
      event.preventDefault();
    });


    $btn1.click(event => {
      //отключаем всплытие при клике на кнопке
      event.stopPropagation();
    });



  },


  CustomEvent() {
    /*
    CustomEvent
      Для генерации событий совершенно новых типов, следует использовать конструктор CustomEvent. 
    В такое событие можно передавать опцию detail, в которой можно указывать информацию для передачи в событие.
    */

    let helloEvent = new CustomEvent("hello", {
      detail: { name: "Вася" }
    });

    let btn1 = $btn1.get(0);

    btn1.addEventListener("hello", function(event) {
      log2(event.detail);
    });

    btn1.dispatchEvent(helloEvent);



  },

  capturing_desc: `
	/*


	*/


	`,

  EventTarget() {
    /*
    EventTarget
      интерфейс, реализуемый объектами, которые могут генерировать события и могут иметь подписчиков на эти события.
      Обычно это Element, document, и window

    Методы

    EventTarget.addEventListener()
    EventTarget.removeEventListener()
    EventTarget.dispatchEvent()
    Генерирует событие на объекте EventTarget.

    */

    class MyGrid extends EventTarget {

      dispatch(eventName, detail) {
        this.dispatchEvent(new CustomEvent(eventName, { detail: detail }));
      }

    }

    const c = new MyGrid();

    let handler = e => {
      log2(e.type, "e.detail:", e.detail);
    };

    c.addEventListener('amazing',handler);
    c.dispatch('amazing', "my details");

    c.removeEventListener('amazing', handler);
		
		//не поймается
		c.dispatch('amazing', "my details2");


  },
  document_fragment_proxy() {
    /*
    Можно прикрутить систему событий к своему объекту, используя элемент документа как прокси.
    */

    function Emitter() {
      var eventTarget = document.createDocumentFragment();

      function delegate(method) {
        this[method] = eventTarget[method].bind(eventTarget);
      }

      Emitter.methods.forEach(delegate, this);
    }

    Emitter.methods = ["addEventListener", "dispatchEvent", "removeEventListener"];


    //Класс, использующий события
    function Example() {
      Emitter.call(this);
    }

    //Использование
    var e = new Example();

    e.addEventListener("something", function(event) {
      log2("something happened!");
    });

    e.dispatchEvent(new Event("something"));



  },
  ttt3() {
    /*
  	
    */


  },









  capturing_desc: `
	/*


	*/


	`,


}










function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT,
    workPanelTemplate: TEMPLATE_FORM1,
    selectorsData: selectorsData1,
    //	jquerySelectorsMode: true,
    lfMode: false,
    afterSandboxReload: null,
    selectedOption: null,
    debugMode: false,
    autoscrollLog2: true,
    logObjectsAsJson: false,
    initFunction: () => {
    }
  };
}

