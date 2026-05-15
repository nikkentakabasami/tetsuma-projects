
let reader, fileReader;

let blob1, arr1,arr2,arr3;

let files, file;

let testBlob1 = new Blob(['Hello, world!'], {type: 'text/plain'});



let selectorsData1 = {
	
	File_info:`
	# File
	#   наследует от Blob, обладает возможностями по взаимодействию с файловой системой.
	# 
	# new File(fileParts, fileName, [options])
	#   конструктор, похожий на Blob:
	# fileParts
	#   массив значений Blob/BufferSource/строки.
	# fileName
	#   имя файла
	# options.lastModified
	#   дата последнего изменения в формате таймстамп (целое число).
	
	`,
	File_get:`
	
	//Получаем объекты File из <input type="file">
	files = $("#if1").get(0).files; !
	file = files[0]; !
	
	file.size;
	file.type;
	
	file.name;
	file.lastModified;
	
	`,
	
	FileReader_info:`

	# FileReader
	#   объект для считывания данных из Blob (и File).
	#   Данные передаются при помощи событий, так как чтение с диска может занять время.
	# 
	# fileReader = new FileReader();
	# 
	# Основные методы:
	# fileReader.readAsArrayBuffer(blob)
	# fileReader.readAsText(blob, [encoding])
	# fileReader.readAsDataURL(blob)
	# 
	# fileReader.abort()
	#   отменить операцию.
	# 
	# В процессе чтения происходят события:
	# 
	# load – нет ошибок, чтение окончено.
	# error – произошла ошибка.
	# loadstart – чтение начато.
	# progress – срабатывает во время чтения данных.
	# abort – вызван abort().
	# loadend – чтение завершено (успешно или нет).
	# 
	# Когда чтение закончено, мы сможем получить результаты через:
	# 
	# reader.result - результат чтения (если оно успешно)
	# reader.error - объект ошибки (при неудаче).

	`,
	
	
	
	fileReader_readAsArrayBuffer(){
		
		//# fileReader.readAsArrayBuffer(blob)
		//#   считать данные как ArrayBuffer
		//# 
		//получаем блоб
		files = $("#if1").get(0).files;
		if (files.length>0){
			file = files[0];
		} else {
			file = testBlob1;
		}
		
		fileReader = new FileReader();
		fileReader.readAsArrayBuffer(file);
		fileReader.onload = function(event) {
		  arr1 = new Uint8Array(fileReader.result);		  
		  log2(arr1);
		};		
		fileReader.onerror = function() {
		  log2(fileReader.error);
		};
		
	},
	
	fileReader_readAsText(){
		
		//# reader.readAsText(blob, [encoding])
		//#   считать данные как строку (default encoding: utf-8)
		//#   альтернатива TextDecoder
		//# 
		
		//получаем блоб
		files = $("#if1").get(0).files;
		if (files.length>0){
			file = files[0];
		} else {
			file = testBlob1;
		}
		

		fileReader = new FileReader();
		fileReader.readAsText(file);

		fileReader.onload = function() {
		  log2(fileReader.result);
		};

		fileReader.onerror = function() {
		  log2(fileReader.error);
		};	

		
	},	
	
	fileReader_readAsDataURL(){
		//# fileReader.readAsDataURL(blob)
		//#   конвертация Blob-объекта в url-строку с кодировкой base64.
		//# 
		//# Эта кодировка представляет двоичные данные в виде строки с безопасными для чтения символами.
		//# data url имеет форму data:[<mediatype>][;base64],<data>.
		//# Мы можем использовать такой url где угодно наряду с «обычным» url.
				
		let $link = $("#link1"); 
		$link.attr("download","hello.txt")
		
		fileReader = new FileReader();
		fileReader.readAsDataURL(testBlob1);
		fileReader.onload = function() {
			log2("href to testBlob1:",fileReader.result);
			$link.attr("href",fileReader.result)
		};		
		
	},
	
	
	
	
	

	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT, 
		workPanelTemplate: "../fragments/anchorsSandbox.html",
		selectorsData: selectorsData1,
		lfMode: true,
//		selectedOption: "s4",
		initFunction: ()=>{
			$(".auxPanel").css("height","600px");
		}
	});	
	
});







