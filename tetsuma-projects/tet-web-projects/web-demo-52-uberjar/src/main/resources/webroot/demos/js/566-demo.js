

let url1,url2,url3,url4;
let blob1, blob2;

let selectorsData1 = {
	
	
	url1:`

	# URL
	#   Встроенный классдля создания и разбора URL-адресов.
	#   
	# new URL(url, [base])
	#   base – необязательный базовый URL (protocol+host)
	#		

	url1 = new URL(testUrl);
	url2 = new URL("testPath?query=satori#sss", testUrl);
		
	url2.protocol;
	url2.host;
	
	url2.hostname;
	url2.port;
	
	url2.pathname;
	
	url2.href;		//полный URL-адрес
	url2.search;	//строка параметров, начинается с вопросительного знака ?
	url2.hash;		//начинается с символа #
	
	 
	
	 	
	
	
	
	`,
	url2:`
	
	# url.searchParams – объект типа URLSearchParams.
	#   предоставляет удобные методы для работы с параметрами:
	#   
	#   при задании параметров - они автоматически кодируются.
	#   Для кодирования так же используются функции: encodeURI, encodeURIComponent
	# 
	# методы:  
	# append(name, value) – добавить параметр по имени,
	# delete(name) – удалить параметр по имени,
	# get(name) – получить параметр по имени,
	# getAll(name) – получить все параметры с одинаковым именем name
	# has(name) – проверить наличие параметра
	# set(name, value) – задать/заменить параметр,
	# 

	
	url1 = new URL(testUrl);
	url1.searchParams.set('q', 'test me!');
	url1.searchParams.set('tbs', 'qdr:y');

	url1;

	@
	// перебрать параметры
	for(let [name, value] of url1.searchParams) {
	  log2(name+" - "+value);
	}
	@!	
	
	`,

	
	url3(){
		//URL.createObjectURL(blob)
		// создаёт url-ссылку на Blob-объект

		blob1 = new Blob(["Hello, world!"], {type: 'text/plain'});
		let href = URL.createObjectURL(blob1);
		
		log2(href);
				
		
		//URL.revokeObjectURL(url)
		//  удаляет внутреннюю ссылку на объект, что позволяет удалить его сборщику мусора		
		
		URL.revokeObjectURL(href);
		
		
	},
		
	
	blob1(){
		
/*
		Blob
		  состоит из необязательной строки type (обычно MIME-тип) и blobParts – последовательности других объектов Blob, строк и BufferSource.


		new Blob(blobParts, options);
		  blobParts – массив значений Blob/BufferSource/String.
		  options: 
		  type – тип объекта, обычно MIME-тип 
		  endings – если указан, то окончания строк будут изменены в соответствии с текущей операционной системой
		  
		  blob.slice([byteStart], [byteEnd], [contentType]);
		  Мы можем получить срез Blob, используя:
		  
*/

		// создадим Blob из строки
		blob1 = new Blob(["<html>…</html>"], {type: 'text/html'});

		// создадим Blob из типизированного массива и строк
		let helloArray = new Uint8Array([72, 101, 108, 108, 111]);    // "hello" в бинарной форме
		blob2 = new Blob([helloArray, ' ', 'world'], {type: 'text/plain'});
		
		
	},
	blob2(){

		//URL.createObjectURL - создаёт url-ссылку на Blob-объект
		//Это быстрый и безопасный вариант создавать ссылки на динамические файлы
		
		blob1 = new Blob(["Hello, world!"], {type: 'text/plain'});

		//Теперь при клике по ссылке - данные будут скачиваться в виде файла
		let $link = $("#link1"); 
		$link.attr("download","hello.txt")
		$link.attr("href",URL.createObjectURL(blob1))
		
		
	},
	blob3(){

		//конвертация Blob-объекта в строку с кодировкой base64.
		//Эта кодировка представляет двоичные данные в виде строки с безопасными для чтения символами.
		//data url имеет форму data:[<mediatype>][;base64],<data>.
		//Мы можем использовать такой url где угодно наряду с «обычным» url.
				
		let $link = $("#link1"); 
		$link.attr("download","hello.txt")

		blob1 = new Blob(['Hello, world!'], {type: 'text/plain'});
		
		let reader = new FileReader();
		reader.readAsDataURL(blob1);
		reader.onload = function() {
			$link.attr("href",reader.result)
		};		
		
	},
	blob4(){
		//Создаём Blob на основе картинки из canvas
		
		let img = document.querySelector('#img1');

		// создаём <canvas> того же размера
		let canvas = document.createElement('canvas');
		canvas.width = img.clientWidth;
		canvas.height = img.clientHeight;

		let context = canvas.getContext('2d');

		// копируем изображение в  canvas
		context.drawImage(img, 0, 0);

		canvas.toBlob(function(blob) {
			
			let $link = $("#link1"); 
			$link.attr("download","example.png")
			$link.attr("href",URL.createObjectURL(blob))

			// удаляем внутреннюю ссылку на Blob, что позволит браузеру очистить память
			URL.revokeObjectURL(link.href);
			
		}, 'image/png');		
		
		
	},
	
	blob5(){
		
		// получаем ArrayBuffer из Blob
		blob1 = new Blob(['Hello, world!'], {type: 'text/plain'});
		
		let fileReader = new FileReader();
		fileReader.readAsArrayBuffer(blob1);

		fileReader.onload = function(event) {
		  let arrayBuffer = fileReader.result;
		  log2(arrayBuffer);
		};		
		
	}
	
	

	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT, 
		workPanelTemplate: "../fragments/anchorsSandbox.html",
		selectorsData: selectorsData1,
		lfMode: false,
		selectedOption: "blob2",
		initFunction: ()=>{
			
		}
	});	
	
});







