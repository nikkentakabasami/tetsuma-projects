
let o1,o2,o3,o4, currentObject;

let entries;


//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {

basic_functions:`

/*
основные функции

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


# in - проверяет, содержит ли объект заданное property
"name" in testObject1;
"light" in testObject1;

/*
for..in
  перебирает не-символьные ключи с флагом enumerable, а также ключи прототипов.
*/

@
for(k in testObject1){
  log2(k);
}
@	

/*
Object.fromEntries(iterable)
  создание объекта на основе двумерного массива (или похожего итерируемого объекта)
*/

Object.fromEntries(testMap1); ~


`,


assign:`

/*
Object.assign(target, source1)
Object.assign(target, ...sources)
  Копирует все properties из одного и более source объектов в target.
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
	
structuredClone:`

/*
structuredClone(value)
  глубокое клонирование объекта
*/

o1 = { a: 0, b: { c: 0 } }; !
o2 = structuredClone(o1); !

o1.a = 4; !
o1.b.c = 4; !
o1; ~
o2; ~
`,


cloneObject:`
/*
accordUtils.cloneObject(source, ...attributes)
  поверхностное клонирование объекта
*/

accordUtils.cloneObject(testObject1, "name", "fresh"); ~

`,
	

create(){

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
    initFunction: () => {
    }
  };
}





