
let reader;

let arr1,arr2,arr3;

let files, file;

let selectorsData1 = {
	
	s1:`
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
	s3:`
	
	@
	//Получаем объекты File из <input type="file">
	files = $("#if1").get(0).files;
	file = files[0];
	@!
	
	file.size;
	file.type;
	
	file.name;
	file.lastModified;
	
	`,
	
	s2:`

	# FileReader
	#   объект для считывания данных из Blob (и File).
	#   Данные передаются при помощи событий, так как чтение с диска может занять время.
	# 
	# reader = new FileReader();
	# 
	# Основные методы:
	# reader.readAsArrayBuffer(blob)
	#   считать данные как ArrayBuffer
	# 
	# reader.readAsText(blob, [encoding])
	#   считать данные как строку (default encoding: utf-8)
	#   альтернатива TextDecoder
	# 
	# reader.readAsDataURL(blob)
	#   считать данные как base64-кодированный URL.
	#   Альтернатива: URL.createObjectURL(file)
	# 
	# reader.abort()
	#   отменить операцию.
	# 
	# В процессе чтения происходят события:
	# 
	# loadstart – чтение начато.
	# progress – срабатывает во время чтения данных.
	# load – нет ошибок, чтение окончено.
	# abort – вызван abort().
	# error – произошла ошибка.
	# loadend – чтение завершено (успешно или нет).
	# 
	# Когда чтение закончено, мы сможем получить результаты через:
	# 
	# reader.result - результат чтения (если оно успешно)
	# reader.error - объект ошибки (при неудаче).

	`,
	
	
	s4:`
	
	files = $("#if1").get(0).files; !
	file = files[0]; !
	
	reader = new FileReader(); !
	reader.readAsText(file); !

	@
	reader.onload = function() {
	  log2(reader.result);
	};

	reader.onerror = function() {
	  log2(reader.error);
	};	
	@!
	
	`,
	s5:`
	`,


	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT, 
		workPanelTemplate: "../fragments/anchorsSandbox.html",
		selectorsData: selectorsData1,
		lfMode: true,
		selectedOption: "s4",
		initFunction: ()=>{
			$(".auxPanel").css("height","600px");
		}
	});	
	
});







