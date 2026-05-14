
let buffer, view, dataView;

let arr1,arr2,arr3;

let selectorsData1 = {
	
	
	ArrayBuffer1:`
	
	# ArrayBuffer
	#   ссылка  на непрерывную область памяти фиксированной длины.
	#   объём памяти задаётся при создании, его нельзя изменить
	#   Для доступа к отдельным байтам нужен вспомогательный объект-представление (ArrayBufferView).
	#
	# TypedArray - типизированный массив
	#   Представление буфера как последовательности чисел (байтных, двухбайтных)
	#   интерпретирует бинарные данные, хранящиеся в ArrayBuffer.
	# Реализации:
	#
	# Uint8Array,Uint16Array,Uint32Array	целые беззнаковые числа
	# Int8Array, Int16Array, Int32Array 	целые числа со знаком
	# Float32Array, Float64Array			32/64-битные числа со знаком и плавающей точкой.
	#
	# BufferSource – это общее название для ArrayBuffer или ArrayBufferView.
	#
	
	
	buffer = new ArrayBuffer(16); 	//буфер длиной 16 байт !
	view = new Uint32Array(buffer);

	Uint32Array.BYTES_PER_ELEMENT;
	view.length;
	view.byteLength;

	//записываем значение
	view[0] = 123456;

	view;
	
	@
	//итерация по элементам
	for(let num of view) {
	  log2(num);
	}
	@		
	
	
	
	`,
	TypedArray2:`
	
	# Конструкторы типизированных массивов имеют вид:
	# 
	# new TypedArray();
	#   Пустой массив. 
	# 

	arr1 = new Uint16Array(); !
	arr1.length;
		
	# 
	# new TypedArray(buffer, [byteOffset], [length]);
	#   На основе ArrayBuffer
	# 

	buffer = new ArrayBuffer(16); !
	arr1 = new Uint16Array(buffer, 8); !
	arr1.length;	
	
	
	#
	# new TypedArray(object);
	#   аналог TypedArray.from()
	#
	arr1 = new Uint8Array([2, 3, 5, 7]);
	arr1.length;
	arr1[1];

	#
	# new TypedArray(typedArray);
	#   создаёт копию typedArray, но приводит значения в ячейках к новому типу данных.
	#   
	arr1 = new Uint16Array([1, 1000]);
	//старшие биты чисел будут обрезаны
	arr2 = new Uint8Array(arr1);
	
	arr2[1];
	
	#
	# new TypedArray(length);
	#   Массив с заданным числом элементов
	#
	
	arr1 = new Uint16Array(4);
	arr1.byteLength;	
	
	#
	# Если не задавать при создании ArrayBuffer - он будет создан автоматом!
	# TypedArray - всего лишь представление!
	
	
	`,
	
	TypedArray3:`
	# typedArray.buffer – ссылка на объект ArrayBuffer.
	#

	arr1 = new Uint8Array([2, 3, 5, 7, 11, 13]);

	arr1.buffer.byteLength;

	//всегда можно создать новое представление для данных
	arr2 = new Uint16Array(arr1.buffer);	

	# Проверить на TypedArray можно так:

	ArrayBuffer.isView(arr1) && !(arr1 instanceof DataView);

	`,
	
	
	TypedArray4:`
	
	# Методы TypedArray
	#   TypedArray содержит большую часть методов из Array.
	#   Не содержат методы, которые меняют размер массива (splice, push...)
	#
	arr1 = new Uint8Array([2, 3, 5, 7, 11, 13, 17]);

	arr1.indexOf(7);
	arr1.lastIndexOf(13);
	arr1.includes(5);
	arr1.some(item=>item>13);
	arr1.every(item=>item>13);

	arr1.find(item=>item>11);
	
			  
	//преобразование элементов
	arr1.map((item,i)=>item*2);
	
	//подмножество/копирование
	arr1.slice(1,3);

	//фильтрация
	arr1.filter(item=>item>5);

	arr1.reduce((sum, current)=>sum+current, 100);

	arr1.toReversed()

	arr1.toSorted()

	//возвращает копию массива, с изменённой ячейкой
	arr1.with(0, 111)
		
	
	arr1.join(';');
	
	//fill(value, start, end) - заполняет массив заданным значением
	arr1.fill(99,5);

	//copyWithin(target, start, end) - копирует часть массива от start до end в позицию target
	arr1.copyWithin(0,4)
	
	`,
	TypedArray5:`
	# Методы TypedArray
	#
	
	# typedArray.set(fromArr, [targetOffset])
	#   копирует все элементы из fromArr в arr, начиная с позиции targetOffset
	#   fromArr может быть обычным или типизированным массивом.
	#

	arr1 = new Uint8Array(8);
	arr1.set([1, 2, 3], 3);

	arr1;
	
	# typedArray.subarray([begin, end])
	#   создаёт новое представление того же типа для данных, начиная с позиции begin до end (не включая). 
	#   в отличии от slice - не копирует массив, а создаёт новое представление для того же ArrayBuffer.
	#
	arr2 = new Uint8Array([10, 20, 30, 40, 50]);

	arr2.subarray(1, 4);
	
	`,
	
	DataView1:`
	
	# DataView
	#   нетипизированное представление данных из ArrayBuffer.
	#   Позволяет обращаться к данным на любой позиции и в любом формате.
	  
	arr1 = new Uint8Array([255, 255, 255, 255])

	dataView = new DataView(arr1.buffer); !

	dataView.getUint8(0);

	dataView.getUint16(0);
	dataView.getUint32(0);

	//обнуление
	dataView.setUint32(0, 0); 	
	  
	arr1;
	
	`,


	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
//		workPanelTemplate: "../fragments/anchorsSandbox.html",
		selectorsData: selectorsData1,
		lfMode: true,
//		selectedOption: "DataView1",
		initFunction: ()=>{
			
		}
	});	
	
});







