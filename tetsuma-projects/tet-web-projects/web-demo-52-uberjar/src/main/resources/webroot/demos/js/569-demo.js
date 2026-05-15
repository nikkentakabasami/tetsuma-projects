

let url1,url2,url3,url4;
let blob1, blob2, blob3;
let arr1, buffer1, obj1, json1;

let selectorsData1 = {
	
	
		
	blob_constructors:`
	# Blob
	#   файло-подобный объект, или сырые немутабельные данные.
	#   Их можно считать как строку, бинарные данные, сконвертировать в ReadableStream
	#   состоит из необязательной строки type (обычно MIME-тип) и blobParts – последовательности других объектов Blob, строк и BufferSource.
	# 
	# Конструкторы:
	# 
	# new Blob(blobParts, options);
	#   blobParts – массив значений Blob/BufferSource/String.
	#   options: 
	#   type – тип объекта, обычно MIME-тип 
	#   endings – если указан, то окончания строк будут изменены в соответствии с текущей операционной системой
	
	// создадим Blob из строки
	blob1 = new Blob(["<html>…</html>"], {type: 'text/html'}); !

	blob1.text();
	
	// создадим Blob из типизированного массива и строк
	arr1 = new Uint8Array([72, 101, 108, 108, 111]);
	blob2 = new Blob([arr1, ' ', 'world'], {type: 'text/plain'}); !

	blob2.text();
	
	
	// создадим Blob из JSON
	obj1 = { hello: "world" }; !
	json1 = JSON.stringify(obj1, null, 2); !
	blob3 = new Blob([json1], {type: "application/json"}); !	
	
	blob3.text();
	
	//поля Blob
	blob2.size;
	blob2.type;
	
	
	
	`,
	
	blob_methods:`
	# Blob - методы
	#   
	
	arr1 = new Uint8Array([72, 101, 108, 108, 111]);
	blob1 = new Blob(["<html>",arr1,"</html>"], {type: 'text/html'}); !

	
	#   
	# blob.slice([byteStart], [byteEnd], [contentType]);
	#   Возвращает Blob, содержащий срез (подмножество) исходного Blob-а.
	# 
	blob2 = blob1.slice(1,10); !
	blob2.text();
	
		
	#   
	# blob.text()
	#   Возвращает Promise с данными блоба в текстовом формате (только в кодировке UTF-8)
	#   
	blob1.text().then(r=>log2(r));
	
	#   
	# blob.arrayBuffer()
	#   Возвращает Promise, содержащий ArrayBuffer в данными блоба.
	# 
	@
	blob1.arrayBuffer().then(buffer=>{
		arr1 = new Uint8Array(buffer);
		log2(arr1);
	})
	@
	
	#   
	# blob.bytes()
	#   Возвращает Promise, содержащий Uint8Array в данными блоба.
	#   Функция появилась только в 2026!!! Так что лучше использовать arrayBuffer().
	# 
//	blob1.bytes();
		
	
	`,
	
	blob_stream(){
		//# Blob - методы 2
		//#   
		//#   
		//# blob.stream()
		//#   Возвращает ReadableStream с данными блоба
		//#   
		
		arr1 = new Uint8Array([72, 101, 108, 108, 111]);
		blob1 = new Blob(["<html>",arr1,"</html>"], {type: 'text/html'});
		
		let stream = blob1.stream();
		
		const reader = stream.getReader();
		let chunks = [];

		reader.read().then(function processData({ done, value }) {
		  if (done) {
			
//			let arr = chunks.reduce((acc, chunk) => new Uint8Array([...acc, ...chunk]), new Uint8Array());
	
			//объединяем все данные в один Uint8Array
			let totalLength = chunks.reduce((sum, arr) => sum + arr.length, 0);
			let arr = new Uint8Array(totalLength);
			let offset = 0;
			for (let chunk of chunks) {
			  arr.set(chunk, offset);
			  offset += chunk.length;
			}

			//декодируем			
			let str = new TextDecoder().decode(arr);
		    log2('Поток завершён:', str);
		    return;
		  }
		  
		  //value - Uint8Array
		  chunks.push(value);
		  reader.read().then(processData);
		});
		
		
	},
	
	
	url_createObjectURL(){

		//# URL.createObjectURL
		//#   создаёт url-ссылку на Blob-объект.
		//#   Это быстрый и безопасный вариант создавать ссылки на динамические файлы.
		
		blob1 = new Blob(["Hello, world!"], {type: 'text/plain'});

		//Теперь при клике по ссылке - данные будут скачиваться в виде файла
		let $link = $("#link1"); 
		$link.attr("download","hello.txt")
		$link.attr("href",URL.createObjectURL(blob1))
		
		
	},
	fileReader_readAsDataURL(){

		//# fileReader.readAsDataURL(blob)
		//#   конвертация Blob-объекта в строку с кодировкой base64.
		//# 
		//# Эта кодировка представляет двоичные данные в виде строки с безопасными для чтения символами.
		//# data url имеет форму data:[<mediatype>][;base64],<data>.
		//# Мы можем использовать такой url где угодно наряду с «обычным» url.
				
		let $link = $("#link1"); 
		$link.attr("download","hello.txt")

		blob1 = new Blob(['Hello, world!'], {type: 'text/plain'});
		
		let reader = new FileReader();
		reader.readAsDataURL(blob1);
		reader.onload = function() {
			$link.attr("href",reader.result)
		};		
		
	},
	canvas_toBlob(){
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
	
	fileReader_readAsArrayBuffer(){
		
		// получаем ArrayBuffer из Blob
		blob1 = new Blob(['Hello, world!'], {type: 'text/plain'});
		
		let fileReader = new FileReader();
		fileReader.readAsArrayBuffer(blob1);

		fileReader.onload = function(event) {
		  arr1 = new Uint8Array(fileReader.result);		  
		  
		  log2(arr1);
		};		
		
	}
	
	

	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT, 
		workPanelTemplate: "../fragments/anchorsSandbox.html",
		selectorsData: selectorsData1,
		lfMode: true,
//		selectedOption: "blob_methods",
		initFunction: ()=>{
		
			$(".auxPanel").css("height","600px");
			
		}
	});	
	
});







