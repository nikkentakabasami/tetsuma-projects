

let testArray1, testArray2, testArray3;
let testString1, testMap1;
let testObject1;
let testSet1;
let arr1, arr2, arr3, arr4;


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


//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {

  //способы объявления массивов
  createArrays1:`
  
	  //пустые массивы
	  arr1 = [];
	  arr1 = new Array();
	  arr1 = Array();
	  arr1 = new Array(6);		//с заданием размера
	
	  //размер массива увеличится автоматом при записи значений (хотя такой подход не желателен - мешает оптимизации)
	  arr1 = []; !
	  arr1[0] = "Saab"; !
	  arr1[1] = "Volvo"; !
	  arr1[2] = "BMW"; !
	  arr1[7] = "Toyota"; !
	  arr1;	
	  
	  //с заданием значений
	  arr1 = new Array(5, 2, 7, 77 );
	  arr2 = new Array("Wind","Rain","Fire")
	  arr3 = ["Яблоко", "Апельсин", "Слива"];
	
	  //объединение массивов в один (через оператор расширения)
	  arr4 = [0, ...arr1, 2, ...arr2];
	  
	  //concat - создаёт новый массив
	  arr4 = arr1.concat(2,3);
	  arr4 = arr1.concat(arr2);
	
	  arr4 = Array.of(5, 2, 7, 77);
  `,
  
  createArrays2:`
	
	  //Array.from(items, mapFn) - создаёт массив на основе массиво-подобных объектов 
	
	  //копирование массива через Array.from
	  arr1 = Array.from(testArray1);
	  
	  //копирование с модификацией элементов
	  testArray2;

	  arr1 = Array.from(testArray2, (x, index) => x + x);
	
	  //создание на основе строки
	  arr1 = Array.from(testString1);
	
	  //генерация массива
	  arr1 = Array.from({ length: 5 }, (el, index) => index);
	
	  //создание на основе строки через оператор расширения
	  arr1 = [...testString1]; 
	
	  //копирование массива через оператор расширения
	  arr1 = [...testArray1]; 
	
	  //Создание массива на основе итератора
	  arr1 = [...testMap1.values()]; 
	
	  //создание на основе Map
	  arr1 = Array.from(testMap1);
	  arr1 = Array.from(testMap1.values());
	  arr1 = Array.from(testMap1.keys());
	  
	  //копирование массива через slice(start, end)
	  arr1 = testArray1.slice();
	  arr1 = testArray1.slice(0,3);  //первые 3 записи
	
	  //-----------прочие методы---------------
	
	  //получение массива разбиением сроки через string.split(separator, maxArraySize)
	  names;
	  
	  arr1 = names.split(', ');
	
	  //Если не задать разделитель - будет разбиение по буквам
	  arr1 = names.split("",5);
	
	  //Получение свойств объекта в виде массива
	  arr1 = Object.keys(accordUtils);
	
	  //создание массива на основе dom-элементов
	  arr1 = Array.from($("button"),el=>el.id)
	  
	  arr1 = Array.from(document.querySelectorAll("button"),el=>el.id)  
  
  `,
  
  
	createArrays3(){
		
		//Создание многомерных массивов
		a.arr1 = [ [1,2,3], [4,5,6], [7,8,9] ]
		a.arr2 = [];
		  for (let y = 0; y < 3; y++) {
		      a.arr2.push(new Array());
		      for (let x = 0; x < 7; x++) {
		          a.arr2[y].push(0);
		      }
		}
	},

	
	//----------способы итерации по массивам------------
	
	iterate1(){
		
		//итерация через for	
		for (let i = 0; i < testArray1.length; i++) {
			let val = testArray1[i];
			if (i==1){
				continue;
			}
			if (i>4){
				break;
			}
			log2(`testArray1[${i}] = ${val}`);
		}
		
		
	},
	iterate2(){
		
		//итерация по индексам (и добавленным полям объекта)	
		testArray1.testField = "testValue";	
		for (let f in testArray1) {
			log2(f);
		}
		
		logTitle2();

		//итерация по значениям	
		for (let v of testArray1) {
			log2(v);
		}
		
	},
		
	iterate3(){

		//arr.forEach
		testArray1.forEach((item, index, array) => {
			log2(`testArray1[${index}] = ${item}`);
		});
		
		logTitle2();
		
		//Получение объекта Iterator из массива
		let vi = testArray1.values();
		let ki = testArray1.keys();
		let ei = testArray1.entries();

		
		//по итераторам обычно проходят так
		log2("testArray1.entries():");
		for(entry of ei){
			log2(`key=${entry[0]}, val=${entry[1]}`);
		}
			
	},
	

	mutate1:`

	# Mutating methods - меняют массив
	# 
	
	arr1 = [7,8]; !
	#

	//push - добавляет элементы в конец массива и возвращает его новую длину
	newLength = arr1.push(5,6); arr1;

	//pop- Удаляет последний элемент из массива и возвращает его
	v = arr1.pop(); arr1;

	//unshift - Добавляет элементы в начало массива
	newLength = arr1.unshift(1,1); arr1;

	//shift - Удаляет из массива первый элемент и возвращает его:
	v = arr1.shift(); arr1;

	//удаление значения (вставляет в ячейку null)
	delete arr1[3]; arr1;

	//укоротить до 2 элементов
	arr1.length = 2; arr1;

	//вернуть length обратно, как было
	arr1.length = 4; arr1;

	//Очистка массива
	arr1.length = 0; arr1;
	
	`,
	
	

	mutate3:`

		arr1 = Array.from(testArray2);
		
		# arr.splice(index[, deleteCount, elem1, ..., elemN])
		# Удаляет deleteCount элементов, начиная с номера index, а затем вставляет elem1, ..., elemN на их место. 
		# Возвращает массив из удалённых элементов.
		#

		//удаляем 4 элемента, начиная со второго
		arr2 = arr1.splice(2, 4);
		
		arr1;
		
		arr2 = arr1.splice(0, 2, 51, 52, 53);

		arr1;
		
		//fill(value, start, end) - заполняет массив заданным значением
		arr1.fill(6);

		arr1.fill(1,2);
		arr1.fill(0,5);

		arr1 = Array.from({ length: 9 }, (el, index) => index+1);
		
		//очищаем первые 5 ячеек для наглядности
		arr1.fill(0,0,5);
				
		//copyWithin(target, start, end) - копирует часть массива от start до end в позицию target
		arr1.copyWithin(0,7)
		arr1.copyWithin(2,5,8)

	`,		
	
	
	
	
	
	
	
	mutate4:`

	//генерируем новый массив
	arr1 = Array.from({ length: 9 }, (el, index) => index+1);

	arr1.reverse();
					
	arr1.sort();

	//числа больше 5 - в начало списка	
	arr1.sort((a, b) => a>5?-1:1 );

	`,		
	
	
	copy1:`

		arr1 = Array.from({ length: 9 }, (el, index) => index+1);
		
		# Copying methods - методы, которые возвращают модифицированную копию массива, не затрагивая оригинал
		
		//arr.concat(value1, value2, … valueN)
		//создаёт новый массив, в который копируются элементы из arr, а также value1, value2, ... valueN.
		//Если аргумент массив - добавятся элементы из него.
		arr1.concat(2,3);
		
		//arr.slice(start, end) - копирует участок массива от begin до end, не включая end
		//без аргументов - копирует массив
		arr1.slice();
		
		//подмножество массива
		arr1.slice(1,5);
		
		//фильтрация
		arr1.filter(item=>item>5);
		
		//преобразование элементов
		arr1.map((item,i)=>item*2);
		
		//arr.reduce(callback, initialValue = 0)
		//используется для последовательной обработки каждого элемента массива с сохранением промежуточного результата.

		//получение суммы всех чисел
		arr1.reduce((sum, current)=>sum+current);
		
		//получение суммы всех чисел + initialValue
		arr1.reduce((sum, current)=>sum+current, 100);

		arr1.toReversed()
		
		arr1.toSorted()
		
		arr1.toSpliced(0,2)
		
		//arr.with(index, value) - возвращает копию массива, с изменённой ячейкой
		arr1.with(0, 111)
		
		
		//flat(depth=1) - создаёт одноуровневый массив из многоуровневых
		[0, 1, 2, [3, 4]].flat();
		
		[0, 1, [2, [3, [4, 5]]]].flat();
		
		[0, 1, [2, [3, [4, 5]]]].flat(3);
		
		//flatMap(callbackFn) - аналог вызова map().flat()
		[1, 2, 3, 4].flatMap((x) => [x, x * 2]);
		
		//arr.join([separator]) - связывает все элементы массива в строку (через запятую по умолчанию);
		arr1.join(';');
	
	
	`,		
	check:`
		arr1 = testArray2.slice();
		arr1.indexOf(3);
		arr1.lastIndexOf(3);
		arr1.includes(3);
		arr1.some(item=>item>3);
		arr1.every(item=>item>3);
		
		//arr.find(callback) - возвращает значение первого найденного в массиве элемента, которое удовлетворяет условию
		//В противном случае возвращается undefined.
		arr1.find(item=>item>11);
		
		//сравнение массивов можно делать так
		JSON.stringify(arr1) === JSON.stringify(testArray2)

		//проверка на массив
		Array.isArray(arr1);	
		
	`,		
	splice:`
		arr1 = testArray2.slice();
	
		//удаляем все элементы начиная со 2-го
		arr1.splice(2);

		arr1;

		arr1 = testArray2.slice();

		//удаляем 3 элемента начиная со 2-го
		removed = arr1.splice(2,3);

		arr1 = testArray2.slice();

		//удаляем 3 элемента начиная со 2-го, вставляем 3 других
		removed = arr1.splice(2,3,111,222,333);
	
		arr1;
	`,		
	
	
}

$(() => {
	
		initBriefDemo(	{
			demoType: DT_SELECT_NO_WP,
			workPanelTemplate: 0,
			selectorsData: selectorsData1,
			selectedOption: "mutate1",
			lfMode: true,
			autoscrollLog2: false,
			
			initFunction(){
				initArrays();
			},
		});		
  
});

