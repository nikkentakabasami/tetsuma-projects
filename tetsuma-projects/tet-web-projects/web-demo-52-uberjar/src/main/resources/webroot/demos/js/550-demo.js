
let o1, o2, o3, o4,s1, s2, s3, arr1, desc, currentObject;

let entries;


//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {




  object_create: `
/*
Object
  Объект в javascript представляет собой ассоциативный массив. 
  Он хранит любые соответствия "ключ => значение".

this
  Для доступа к текущему объекту из метода объекта используется ключевое слово this.
Значение this называется контекстом вызова и будет определено в момент вызова функции.
	
*/

//Объявление простых объектов (без наследования)

o1 = new Object(); !

o2 = {}; !

//Определение атрибутов и методов при создании объекта
@
o3 = {
  firstname : "John",
  lastname  : "Doe",
  id        :  556,
	hi() {
		log2("hi, its " + this.firstname);
	},
	
	get fullName() {
	  return this.firstname + ' ' + this.lastname;
	},

	set fullName(value) {
	  var split = value.split(' ');
	  this.firstname = split[0];
	  this.lastname = split[1];
	}
	
};
@

o3.hi();

o3.fullName;

o3.fullName = "bob dilan"; !

o3;


//упрощённое создание объекта
@
function makeUser(name, age) {
  return {
    name,
    age
  };
}

o4 = makeUser("Bob",33);
@

# Object.fromEntries(iterable)
#  создание объекта на основе двумерного массива (или похожего итерируемого объекта)

Object.fromEntries(testMap1);


`,
  object_attributes: `

# атрибуты и методы объекта


//Добавление атрибутов в объект
@
o2 = {};
o2.firstname="John";
o2.lastname="Doe";
o2.age=50;
o2['name'] = 'Вася';

o2.hi = function(n) {
	log2("hi, its " + this.firstname);
}
@ !

o2;
o2.hi();

//Доступ к атрибутам объекта.

//Если такого атрибута нет - вернётся 'undefined'
o2.firstname;
o2['age'];


//Удаление атрибута
delete o2.firstname; !

o2;

//Очистка ячейки в массиве
delete testArray1[3]; !

testArray1;


# in - проверяет, содержит ли объект заданное property
"name" in testObject1;
"light" in testObject1;
karma in testObject1;


/*
for..in
  перебирает не-символьные ключи с флагом enumerable, а также ключи прототипов.
*/

@
for(k in testObject1){
  log2(k);
}
@	




`,

  object_constructor() {

    /*
    Создание объекта с использованием конструктора
      Конструктором становится любая функция, вызванная через new.
      Функции-конструкторы обычно называют с большой буквы.
    */

    function Person(firstname, lastname, age) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.age = age;

      //добавление метода
      this.changeName = function(name) {
        this.lastname = name;
      };

    }

    //Создание экземпляров
    o1 = new Person("John", "Doe", 50);
    o2 = new Person("Sally", "Rally", 48);
    o3 = new Person;	//Если аргументов нет - скобки можно не ставить.

    le(`
			o1;
			o2;
			
			o3;
			
			o3.changeName('Cat');
			
			o3;
			
			`);


    /*
    Правила обработки return
      Как правило, конструкторы ничего не возвращают.
    Но если явный вызов return всё же есть, то применяется простое правило:
    При вызове return с объектом, будет возвращён он, а не this.
    При вызове return с примитивным значением, оно будет отброшено.
    */

    function BigAnimal() {
      this.name = "Мышь";
      return { name: "Годзилла" };
    }

    o4 = new BigAnimal();

    le(`
			o4;
			`);




  },


  clone_assign: `

/*
# Клонирование объектов

Object.assign(target, source1)
Object.assign(target, ...sources)
  Копирует все properties из одного и более source объектов в target (в том числе и Symbol).
	То есть выполняет поверхностное клонирование объекта.
  Возвращает target.
*/

o1 = { a: 10, b: 20 }; !
o2 = { b: 99, c: 30 }; !
Object.assign(o1, o2); ~
			
//клонирование объекта
Object.assign({}, o2); ~

o1 = { a: 1 }; !
o2 = { b: 2 }; !
o3 = { c: 3 }; !

Object.assign(o1, o2, o3); ~	

o1; ~

`,

  structuredClone: `
# Клонирование объектов

/*
structuredClone(value)
  глубокое клонирование объекта
*/

o1 = { a: 0, b: { c: 0 } }; !
o2 = structuredClone(o1); !
o3 = Object.assign({}, o1); !


o1.a = 4; !
o1.b.c = 4; !
o1; ~
o2; ~
o3; ~
`,


  cloneObject: `
# Клонирование объектов

/*
accordUtils.cloneObject(source, ...attributes)
  мой метод для поверхностного клонирования объекта
*/

accordUtils.cloneObject(testObject1, "name", "fresh"); ~

accordUtils.cloneObject;


`,



  Object_static_functions: `

/*
# Object - Основные статические методы


Object.keys()
Object.values()
  Ключи и значения

Object.entries(obj)
 properties объекта в виде двумерного массива [[property, value]].
 
*/ 
	
Object.keys(testObject1);
Object.values(testObject1);
entries = Object.entries(testObject1);

@
for (const [key, value] of entries){ 
  log2(key,":",value);
}
@!

/*
Object.getOwnPropertyNames(obj)
  не-символьные ключи.

Object.getOwnPropertySymbols(obj)
  символьные ключи.
*/
	
Object.getOwnPropertyNames(testObject1);

Object.getOwnPropertySymbols(testObject1)[0].toString();



`,


  object_create_proto() {

    //# Object.create(proto)
    //# создаёт новый объект, используя существующий как прототип 

    const person = {
      isHuman: false,
      printIntroduction() {
        log2(`My name is ${this.name}. Am I human? ${this.isHuman}`);
      },
    };

    o1 = Object.create(person);

    o1.name = "Matthew";
    o1.isHuman = true;

    o1.printIntroduction();

    //Объект, создаваемый при помощи Object.create(null) не имеет прототипа, а значит в нём нет лишних свойств. 
    //Хорошо подходит для коллекции.
    var data = Object.create(null);
    data.text = "Привет";


  },

	
	

	  toPrimitive: `
/*
# Преобразование объектов в примитивы

Symbol.toPrimitive
  Самый универсальный и рекомендованный метод для преобразования.
  Если этот метод не задан - будут использоваться toString и valueOf

Метод задётся в таком формате:

obj[Symbol.toPrimitive] = function(hint) {
  // hint равно чему-то одному из: "string", "number" или "default"
	return "some value";
  return hint == "string" ? "string value" : 123;
};	
	
*/

@
testObject1[Symbol.toPrimitive] = function(hint) {
  return 22;
};

o1 = {
  name: "John",
  money: 1000,

  [Symbol.toPrimitive](hint) {
    return hint == "string" ? this.name : this.money;
  }
};
@ !

+testObject1;

"o1 is "+o1;

String(o1);

o1+100;


/*
toString()
  устаревший способ для строкового преобразования.
*/

@
o1 = {
  firstName: 'Василий',
  toString: function() {
    return 'Пользователь ' + this.firstName;
  }
};
@ !

" "+o1;

/*
valueOf()
  устаревший способ для преобразования в число.
*/
@
o1 = {
  number: 777,
  valueOf() { return this.number; },
  toString() { return "o1Str"; }
};
@ !
o1+100;

" "+o1;

delete o1.valueOf;

//теперь используется toString
" "+o1;

	`,
Symbol: `
/*
Symbol
  представляет собой уникальный идентификатор.


*/

// Создаём символ id с описанием/description  "id"
s1 = Symbol("id");

//символы надо преобразовывать в строки явно!
s1.toString();

s1.description;

s2 = Symbol();



/*
символами можно определять скрытые свойства объектов
они игнорируются циклом for…in, но копируются через Object.assign

При этом не нужно бояться конфликтов наименований атрибутов - каждый Symbol - уникален
*/

testObject1[s1] = 1; !

testObject1[s1];

@
o1 = {
  name: "Вася",
  [s1]: 123
};
@ !

o1[s1];

//Symbol игнорируется циклом for…in
@
for(k in o1){
  log2(k);
}
@

//символьные атрибуты можно получить через Object.getOwnPropertySymbols:
@
arr1 = Object.getOwnPropertySymbols(o1);
for(s of arr1){
	log2(s.toString());
}
@

/*
Symbol.for(key)
  считывание символа из глобального реестра.
Если символа там нет - он будет создан.
Значения попадают в реестр только через вызов Symbol.for!

*/

s1 = Symbol("id");

s2 = Symbol.for("id"); 

s3 = Symbol.for("id"); 

s1===s2;
s2===s3;


/*
Symbol.keyFor(sym)
  возвращает его имя символа из глобального реестра.
*/


Symbol.keyFor(s1);
Symbol.keyFor(s2);

	
`,
Properties: `
/*
Object properties


Object.defineProperty(obj, prop, descriptor)
  Основной метод для управления свойствами

obj
  Объект, в котором объявляется свойство.

prop
  Имя свойства, которое нужно объявить или модифицировать.

descriptor
  Дескриптор – объект, который описывает поведение свойства.

Содержит поля:

value
  значение свойства
writable
  (default false)
	если true, то значение свойства можно менять
	
configurable
  (default false)
	если true, то свойство можно удалять, и менять при помощи новых вызовов defineProperty.
	
enumerable
  (default false)
	если true, то свойство просматривается в цикле for..in и методе Object.keys().
	
get
  функция, которая возвращает значение свойства.

set
  функция, которая записывает значение свойства.


	Чтобы избежать конфликта, запрещено одновременно указывать value и функции get/set.
Также запрещено и не имеет смысла указывать writable при наличии get/set-функций.
*/

o1 = {}; !

//простое присваивание
o1.age = 33; !
o1.secName = "Иванов"; !

//задание атрибута через дескриптор
Object.defineProperty(o1, "name", { value: "Вася", configurable: true, writable: true, enumerable: true });

//Свойство-константа 
@
Object.defineProperty(o1, "title", {
  value: "менеджер",
  writable: false,
  configurable: false,
	enumerable: true
});
@

o1.name = "Петя"; !

//атрибут не изменится
o1.title = "директор"; !

o1;

`,

	
Properties2: `
/*
Object properties

get/set

Для считывания и задания свойства можно использовать функции.
Это позволяет сохранять совместимость со старыми версиями объектов.

*/

@

o1 = {
  name: "Вася",
  secName: "Петров",
};


Object.defineProperty(o1, "fullName", {
  get: function() {
    return this.name + ' ' + this.secName;
  },
  set: function(value) {
      var split = value.split(' ');
      this.name = split[0];
      this.secName = split[1];
    }
});
@ !

o1.fullName;


o1.fullName = "Вадим Толстов"; !

o1;



//Указание get/set в литералах

@
o2 = {
  firstName: "Вася",
  surname: "Петров",

  get fullName() {
    return this.firstName + ' ' + this.surname;
  },

  set fullName(value) {
    var split = value.split(' ');
    this.firstName = split[0];
    this.surname = split[1];
  }
};
@

o2.fullName;

o2.fullName = "Петя Иванов"; !

o2;

`,
	
Properties3: `

/*
Object.defineProperties(obj, descriptors)
  Позволяет объявить несколько свойств сразу:
*/

	
o1 = {};

//По умолчанию свойства будут не перечисляемые
@
Object.defineProperties(o1, {
  firstName: {
    value: "Петя"
  },

  surname: {
    value: "Иванов"
  },

  fullName: {
    get: function() {
      return this.firstName + ' ' + this.surname;
    }
  }
});
@ !

o1.fullName;



/*
Object.keys(obj)
Возвращает массив перечисляемых атрибутов объекта (кроме символьных).

Object.getOwnPropertyNames(obj)
Возвращает массив всех атрибутов объекта (кроме символьных).

Object.getOwnPropertySymbols(obj)
Возвращает массив символьных атрибутов объекта.

Object.values()
  значения
	
Object.entries(obj)
  properties объекта в виде двумерного массива [[property, value]].


*/

@
o1.age = 33;
s1 = Symbol("id");
o1[s1] = 123;
@ !

Object.keys(o1);

Object.getOwnPropertyNames(o1);

Object.getOwnPropertySymbols(o1)[0].toString();

Object.values(o1);

entries = Object.entries(o1);

@
for (const [key, value] of entries){
  log2(key,":",value);
}
@

`,
Properties4: `
/*
Object.getOwnPropertyDescriptor(obj, prop)
  Возвращает дескриптор для свойства obj[prop].
*/

@
o1 = {
  test: 5
};

desc = Object.getOwnPropertyDescriptor(o1, 'test');
@

// заменим value на геттер
delete desc.value; !
delete desc.writable; !
desc.get = function() {	return 777; }; !

// если не удалить - defineProperty объединит старый дескриптор с новым
delete o1.test; !
Object.defineProperty(o1, 'test', desc); !

o1;

o1.test;



`,
	
Properties4: `
/*
Прочие методы


Object.freeze(obj)
  Запрещает добавление, удаление и изменение свойств, все текущие свойства делает configurable: false, writable: false.

Object.isFrozen(obj)
  Возвращает true, если добавление, удаление и изменение свойств объекта запрещено, и все текущие свойства являются configurable: false, writable: false.

Object.preventExtensions(obj)
  Запрещает добавление свойств в объект.

Object.isExtensible(obj)
  Возвращает false, если добавление свойств объекта было запрещено вызовом метода Object.preventExtensions.

Object.seal(obj)
  Запрещает добавление и удаление свойств, все текущие свойства делает configurable: false.

Object.isSealed(obj)
  Возвращает true, если добавление и удаление свойств объекта запрещено, и все текущие свойства являются configurable: false.



*/

o1 = Object.assign({}, testObject1);

//запрещаем все изменения объекта
Object.freeze(o1);

//имя не изменится
o1.name = "billy";

o1;

Object.isFrozen(o1);


`,




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
    initFunction: () => {
    }
  };
}





