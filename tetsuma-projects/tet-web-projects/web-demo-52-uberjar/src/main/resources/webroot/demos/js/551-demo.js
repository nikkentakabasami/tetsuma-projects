
let o1,o2,o3,o4, currentObject;




//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {

class_basics:`

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
class_inheritance:`

@
clearLog1();
logFuncCode(Animal, true, $log1);
logFuncCode(Rabbit, true, $log1);
@!

rabbit = new Rabbit("Белый кролик", 10); !
rabbit.run(5); !
rabbit.stop(); !


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
	exitOnError: false,  //тестируем ошибки
    initFunction: () => {
    }
  };
}




