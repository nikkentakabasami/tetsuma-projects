

let numbers, p1, user;

let selectorsData1 = {

  Proxy1() {
    /*
    Proxy
      Объект, который «оборачивается» вокруг другого объекта и может перехватывать разные действия с ним
	
    Создаётся в формате
    let proxy = new Proxy(target, handler);
	
    По хорошему, прокси должен иметь то же имя что и объект, перезаписывая его:
    dictionary = new Proxy(dictionary, ...);
	
	
    Ловушки
    handler содержит обработчики-ловушки
    Ловушки бывают следующие:
	
    get(target, propertyName, receiver)		чтение свойства
    set(target, property, value, receiver)	запись свойства
    apply(target, thisArg, args)			активируется при вызове прокси как функции
    has						оператор in
    deleteProperty			оператор delete
    construct				оператор new
    ownKeys					возвращает перечисляемые атрибуты объекта (которые можно получить через Object.keys)
    Ловушки должны возвращать true, если метод выполнен успешно.
    */

    numbers = [10, 11, 12];

    p1 = new Proxy(numbers, {
      get(target, prop) {
        log2(`GET ${prop}`);

        if (prop in target) {
          return target[prop];
        } else {
          return 0; // значение по умолчанию
        }
      },
      set(target, prop, val) {
        log2(`SET ${prop}`);
        //разрешаем добавлять только числа
        if (typeof val == 'number') {
          target[prop] = val;
          return true;
        } else {
          return false;
        }
      }

    });

    le(`
	p1[1];
	p1[123];
	p1.push(77);
	p1.push(88);
	numbers;
	p1.push("тест"); // кинет ошибку TypeError
	numbers;
	`);



  },
  Proxy_apply() {

    /*
    apply(target, thisArg, args)
      активируется при вызове прокси как функции
    */

    //оборачивание функции log2
    //будет выдавать сообщения с задержкой.
    p1 = new Proxy(log2, {
      apply(target, thisArg, args) {
        setTimeout(() => target.apply(thisArg, args), 1000);
      }
    });

    p1("delayed message.")
    log2("normal message");


  },
  Proxy_ownKeys() {

    /*
    ownKeys
      возвращает перечисляемые атрибуты объекта (которые можно получить через Object.keys)
    */

    user = {
      name: "Вася",
      age: 30,
      _password: "***",
      checkPassword(value) {
        return value === this._password;
      }
    };

    //пропускать свойства, начинающиеся с подчёркивания _, запрещаем их считывать и менять
    p1 = new Proxy(user, {
      ownKeys(target) {
        return Object.keys(target).filter(key => !key.startsWith('_'));
      },

      get(target, prop) {
        if (prop.startsWith('_')) {
          throw new Error("Отказано в доступе");
        } else {
          let value = target[prop];

          //bind вызывается для того, чтобы функции объекта имели доступ к полям с подчёркиванием.
          return (typeof value === 'function') ? value.bind(target) : value;
        }
      },
      set(target, prop, val) {
        if (prop.startsWith('_')) {
          throw new Error("Отказано в доступе");
        } else {
          target[prop] = val;
          return true;
        }
      },
      deleteProperty(target, prop) {
        if (prop.startsWith('_')) {
          throw new Error("Отказано в доступе");
        } else {
          delete target[prop];
          return true;
        }
      },


    });

    le(`
		@
		for(let key in p1){
			log2(key);
		}
		@
		
		Object.keys(user);
	
		Object.keys(p1);
		Object.values(p1);
		Object.entries(p1);
		
		p1._password;
		
		p1._password="123";
		
		p1.checkPassword("***") `);


  },
  Proxy_ownKeys2() {

    //добавляем псевдо-атрибуты (по факту их не будет, но показываться будут)
    user = {};

    p1 = new Proxy(user, {
      ownKeys(target) {
        return ['a', 'b', 'c'];
      },

      getOwnPropertyDescriptor(target, prop) {
        return {
          enumerable: true,
          configurable: true
        };
      }

    });

    le(`
		Object.keys(p1);
		Object.values(p1);
		Object.entries(p1);
		p1.a = "123";
		p1;
		user; `);

  },
  Proxy_has() {

    /*
    has
      отвечает за оператор in
    */

    let range = {
      start: 1,
      end: 10
    };

    p1 = new Proxy(range, {
      has(target, prop) {
        return prop >= target.start && prop <= target.end
      }
    });


    le(`
		5 in p1;
		20 in p1;
		9 in p1; `);

  },

  Reflect() {

    /*
    Reflect – встроенный объект, упрощающий создание прокси.
	
    Позволяет вызывать внутренние методы вроде  [[Get]], [[Set]].
	
    Операция			Вызов Reflect						Внутренний метод
    obj[prop]			Reflect.get(obj, prop)				[[Get]]
    obj[prop] = value	Reflect.set(obj, prop, value)		[[Set]]
    delete obj[prop]	Reflect.deleteProperty(obj, prop)	[[Delete]]
    new F(value)		Reflect.construct(F, value)			[[Construct]]
    */

    user = {};
    Reflect.set(user, 'name', 'Вася');
    log2(user.name);

  },

  Proxy_with_Reflect() {

    /*
    Reflect упрощает создание Proxy
    если надо перенаправить вызов на объект, то достаточно вызвать Reflect.<метод> с теми же аргументами.
    Желательно делать именно так, иначе будут проблемы с наследованием
    */

    user = {
      name: "Вася",
    };

    p1 = new Proxy(user, {
      get(target, prop, receiver) {
        log2(`GET ${prop}`);
        return Reflect.get(target, prop, receiver);
      },
      set(target, prop, val, receiver) {
        log2(`SET ${prop}=${val}`);
        return Reflect.set(target, prop, val, receiver);
      }
    });


    le(`
		p1.name;
		p1.name = "Петя";
		p1.name; `);

  },
  Proxy_inner() {

    /*
    Многие встроенные объекты, например Map, Set, Date, Promise и другие используют так называемые «внутренние слоты».
    Это как свойства, но только для внутреннего использования. Например, Map хранит элементы во внутреннем слоте [[MapData]].
    Встроенные методы обращаются к слотам напрямую, не через [[Get]]/[[Set]]. Таким образом, прокси не может перехватить их.
    */

    let map = new Map();
    p1 = new Proxy(map, {});
    p1.set('test', 1); // будет ошибка		


  },
  Proxy_inner2() {

    //исправляется привязыванием функций к оригинальному объекту

    let map = new Map();

    p1 = new Proxy(map, {
      get(target, prop, receiver) {
        let value = Reflect.get(...arguments);
        return typeof value == 'function' ? value.bind(target) : value;
      }
    });

    p1.set('test', 1);
    log2(p1.get('test'));

    //та же ситуация с приватными полями
    class User {
      #name = "Гость";
      getName() {
        return this.#name;
      }
    }

    user = new User();
    p1 = new Proxy(user, {
      get(target, prop, receiver) {
        let value = Reflect.get(...arguments);
        return typeof value == 'function' ? value.bind(target) : value;
      }
    });

    log2(p1.getName());


  },


  Proxy_revocable() {
    /*
     Отключаемый (revocable) прокси – это прокси, который может быть отключён вызовом специальной функции.
     Допустим, у нас есть какой-то ресурс, и мы бы хотели иметь возможность закрыть к нему доступ в любой момент.
     Для того, чтобы решить поставленную задачу, мы можем использовать отключаемый прокси, без ловушек. 

    let {proxy, revoke} = Proxy.revocable(target, handler)
      возвращает объект с proxy и функцию revoke, которая отключает его.

    Вызов revoke() удаляет все внутренние ссылки на оригинальный объект из прокси, 
     так что оригинальный объект может быть очищен сборщиком мусора.
    */
    let object = {
      data: "Важные данные"
    };

    let { proxy, revoke } = Proxy.revocable(object, {});

    log2(proxy.data);

    //хранить revoke-функции можно в WeakMap, для удобства
    let revokes = new WeakMap();
    revokes.set(proxy, revoke);

    // позже в коде
    revoke = revokes.get(proxy);
    revoke();

    // прокси больше не работает (отключён)
    log2(proxy.data);
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







