
let o1,o2,o3,o4, currentObject;


const testObject1 = {
  name: "some string",
  age: 42,
  fresh: true
};


const testMap1 = new Map([
  ["kaban", "bag"],
  ["tsukue", "table"],
]);



//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {
	
	
	basic_functions:`
	
	//основные функции

	Object.keys(testObject1);
	
	Object.values(testObject1);
	
	#
	# Object.entries(obj) - возвращает property объекта в виде двумерного массива.
	# каждый элемент массива - массив вида [property, value]
	#
	
	Object.entries(testObject1);

	for (const [key, value] of Object.entries(testObject1)) { log2(key,":",value); }
		
	#
	# Object.fromEntries(iterable) - создание объекта на основе двумерного массива (или похожего итерируемого объекта)
	#
	
	Object.fromEntries(testMap1);	~
	
	`,
	
	
	assign:`
	
	# Object.assign(target, source1)
	# Object.assign(target, ...sources)
	# Копирует все properties из одного и более source объектов в target.
	# Возвращает target

	o1 = { a: 1, b: 2 }; !
	o2 = { b: 4, c: 5 }; !
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
	
	# structuredClone(value) - глубокое клонирование объекта
	
	o1 = { a: 0, b: { c: 0 } }; !
	o2 = structuredClone(o1); !
	
	o1.a = 4; !
	o1.b.c = 4; !
	o1; ~
	o2; ~
	`,
	
	
	cloneObject:`
	# accordUtils.cloneObject(source, ...attributes)
	#   поверхностное клонирование объекта
	#
	
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




$(() => {
	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		lfMode: true,
		selectedOption: "demo1_script",
		initFunction: ()=>{
			
		}
	});	
});






