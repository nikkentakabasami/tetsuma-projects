
let o1, o2, o3, o4, currentObject;




//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {

  class_basics: `

@
clearLog1();
logFuncCode(User, true, $log1);
@!

user = new User("Иван"); ~


//получение атрибута объекта
user.name;

//получение атрибутов, используя getter-ы
user.age;
user.fullName;

//запись атрибута объекта
user.name = "Коля"; !

//использование setter-ов
user.age = 33; !
user.password = "orbital"; !

user.age = 2;   //Ошибочное значение !

//вызов методов объекта			
user.sayHi();
user.printpw();

user; ~

//bad constructor			
user = new User("Иван","ss"); ~



//статические методы
User.staticMethod1();
User.staticMethod2();
User.createBob(); ~

//Статические свойства
User.desc;


`,
  class_inheritance: `

@
clearLog1();
logFuncCode(Animal, true, $log1);
logFuncCode(Rabbit, true, $log1);
@!

rabbit = new Rabbit("Белый кролик", 10); !
rabbit.run(5); !
rabbit.stop(); !


//исходник функции
rabbit.stop;

rabbit.__proto__.stop;

//исходник функции родителя
Object.getPrototypeOf(rabbit.__proto__).stop;

`,

  mixin() {

    /*
    Миксин
      это класс, методы которого предназначены для использования в других классах, причём без наследования от примеси.
    */

    let sayHiMixin = {
      sayHi() {
        log2(`Привет, ${this.name}`);
      },
      sayBye() {
        log2(`Пока, ${this.name}`);
      }
    };

    // копируем методы
    Object.assign(Animal.prototype, sayHiMixin);

    rabbit = new Animal("Белый кролик");

    rabbit.sayHi();
    rabbit.sayBye();


  },

  mixin2() {


    //Пример: миксин, добавляющий возможность работы с событиями

    let eventMixin = {

      on: function(eventName, handler) {
        if (!this._eventHandlers) this._eventHandlers = {};
        if (!this._eventHandlers[eventName]) {
          this._eventHandlers[eventName] = [];
        }
        this._eventHandlers[eventName].push(handler);
      },

      off: function(eventName, handler) {
        var handlers = this._eventHandlers && this._eventHandlers[eventName];
        if (!handlers) return;
        for (var i = 0;i < handlers.length;i++) {
          if (handlers[i] == handler) {
            handlers.splice(i--, 1);
          }
        }
      },

      trigger: function(eventName) {
        if (!this._eventHandlers || !this._eventHandlers[eventName]) {
          return;
        }
        var handlers = this._eventHandlers[eventName];
        for (var i = 0;i < handlers.length;i++) {
          handlers[i].apply(this, [].slice.call(arguments, 1));
        }

      }
    };

    class Menu {
      choose(value) {
        this.trigger("select", value);
      }
    }
    Object.assign(Menu.prototype, eventMixin);

    let menu = new Menu();
    menu.on("select", value => log2(`Выбранное значение: ${value}`));
    menu.choose("123");

  },





}



function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_NO_WP,
    workPanelTemplate: 0,
    selectorsData: selectorsData1,
    lfMode: true,
    afterSandboxReload: null,
    selectedOption: null,
    debugMode: false,
    exitOnError: false,  //тестируем ошибки
    initFunction: () => {
    }
  };
}




