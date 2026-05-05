

let testArray1, testArray2, testArray3;
let testString1, testMap1;
let testObject1;
let testSet1;
let map1, map2, set1;


let testMap;
let removed;
let names,v,newLength;

function initArrays(){
	testArray1 = ["Яблоко", "Апельсин", "Слива", "Груша","Финик","Вишня"];
	testArray2 = new Array(11, 3, 5, 2, 7, 9, 13, 3, 33 );
	testString1 = "Привет мир!";
	
	names = 'Маша, Петя, Марина, Василий';
	
	testArray3 = Array.from({ length: 9 }, (el, index) => index);	// [0, 1, 2, 3...]
	
	
	testObject1 = {
	  name: "John",
	  age: 30
	};	
	
	testMap1 = new Map([
	  [1, "a"],
	  [2, "b"],
	  [3, "c"],
	]);
	
	
}


let selectorsData1 = {

	map_create1: `
	
		# Map - коллекция ключ/значение
		# отличие от Object в том, что Map позволяет использовать ключи любого типа, даже объекты

		//Проверка на тип
		testMap1 instanceof Map;
		
		//Способы создания

		map1 = new Map();

		//мап из массива
		map1 = new Map([["огурец", 500],["помидор", 350],["лук",    50]]);

		//мап из объекта (Object.entries возвращает 2d массив)
		map1 = new Map(Object.entries(testObject1));

		//копирование Map
		map1 = new Map(testMap1);		
		
		map1 = new Map(Array.from(testMap1));			
	
	`,
	map_modify: `
	
		# map.set(key, value) - задание. Возвращает map.
		# map.get(key) - получение значения
		map1 = new Map();		
		map1.set("1", "str1");
		
		map1.set(1, "num1").set(2, "num2").set(true, "bool1");
	
		map1.get(1)
		map1.get("1")
	
		//map.delete(key) - удаление. 
		//Возвращает true, если элемент был успешно удалён, false - если был не найден
		map1.delete(2);
		
		map1;
	
		//map.has(key) - проверка на наличие
		map1.has(1)
		map1.size
	
		//map.clear() - очистка
		map1.clear(); map1;
	
	
		//getOrInsert(key, defaultValue) - если ключ найден - возвращает значение, иначе вставляет defaultValue и возвращает его
		//поддерживается только новыми браузерами!
//		map1.getOrInsert(10, "juu");
//		map1.getOrInsert(10, "ten");
	
		
	`,
	map_transform: `
		//Создание объекта из мапа
		Object.fromEntries(testMap1.entries());
	
		//Создание массива из мапа
		Array.from(testMap1);
		Array.from(testMap1.keys());
		Array.from(testMap1.values());
		
		[...testMap1.values()];
		
	
		//Map в строковом виде
		JSON.stringify(Array.from(testMap1));		
	`,
	
	
	map_iterate(){
		
		//map.keys(), map.values(), map.entries() - Возвращают Iterator		
		
		//Итерация по итератору map.keys()		
		for (let key of testMap1.keys()) {
			let value = testMap1.get(key);
			log2(`${key}: ${value}`);
		}

		logTitle2();
		
		//Итерация по итератору map.entries()
		for (let e of testMap1.entries()) {
			log2(`${e[0]}: ${e[1]}`);
		}
		
		logTitle2();
				
		//Итерация через метод forEach		
		testMap1.forEach((value, key, map) => {
			log2(`${key}: ${value}`);
		});
		
	},		
	

	set_create: `
	
		//Set - множество уникальных значений

		set1 = new Set();
		
		set2 = new Set(["апельсин", "яблоко", "банан"]);
		
	`,

	
	
	set_mod: () => {
		
		let john = { name: "John" };
		let pete = { name: "Pete" };
		let mary = { name: "Mary" };

		set1 = new Set();

		// считаем гостей, некоторые приходят несколько раз
		set1.add(john);
		set1.add(pete);
		set1.add(mary);
		set1.add(john);
		set1.add(mary);

		//итерация через for
		for (let v of set1) {
			log2(v.name);
		}
		
		logTitle2();

		set1.delete(john);

		//итерация через forEach
		set1.forEach((value, valueAgain, set) => {
			log2(value);
		});
		
	},		
	
	
	weak_set: () => {
		
		
		let john = { name: "John" };
		let pete = { name: "Pete" };

		set1 = new WeakSet();
		set1.add(john);
		set1.add(pete);

		john = null; // перезаписываем ссылку на объект
		// объект john удалён из памяти!
		
	},		
	
}



$(() => {
	
		initBriefDemo(	{
			demoType: DT_SELECT_NO_WP,
			workPanelTemplate: 0,
			selectorsData: selectorsData1,
//			selectedOption: "weak_set",
			lfMode: true,
			autoscrollLog2: false,
			
			initFunction(){
				initArrays();
			},
		});		
  
});

