
let o1, o2, o3, o4, currentObject;



let selectorsData1 = {

  class_basics: `

/*	
Реализация классов и наследования через prototype
  
Наследование реализуется следующим образом:

1)При создании класса, в его конструкторе указываем новые поля объекта.
В объекте MyClass.prototype - указываем методы, которые должны унаследоваться.

2)Чтобы унаследовать от класса, после конструктора вызываем

MyClass.prototype = Object.create(MyParentClass.prototype);

В конструкторе же вызываем конструктор родительского класса
function MyClass(param1) {
  MyParentClass.call(this, param1);
}


Object.create(prototype)
  создаёт новый объект с указанным прототипом	

*/	
log2(initClasses);

`,

test1(){

	log(initClasses);
	
	o1 = new Rabbit("Кроля");
	
	o1.run(5);
	log2hr();
	
	o1.run(2);
	log2hr();
		
	o1.stop();
	
	o1.jump();
	
},
test2(){
	
},
test3(){
	
},




}




function createRabbitClasses(){
	
	
	
	
	
	
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




