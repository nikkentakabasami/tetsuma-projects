
let encoder, decoder;

let arr1,arr2,arr3;

let files, file;

let selectorsData1 = {
	
	
	s1:`
	
	# TextDecoder
	#   позволяет декодировать данные из бинарного буфера в обычную строку.
	# 
	# new TextDecoder([label], [options]);
	# label
	#   тип кодировки(default: utf-8)
	# options.fatal
	#   если true, то генерируется ошибка для невалидных символов, 
	#   иначе они заменяются символом \uFFFD (Маркер ошибки кодировки).
	#   (default: false)
	# options.ignoreBOM
	#   если true, то игнорируется BOM (признак, определяющий порядок следования байтов)
	
	
	# 
	# let str = decoder.decode([input], [options]);
	#   декодирование.
	# input – бинарный буфер (BufferSource) для декодирования.
	# options.stream – true для декодирования потока данных.
	# 
	
	arr1 = new Uint8Array([72, 101, 108, 108, 111]); !

	decoder = new TextDecoder(); 
	decoder.decode(arr1);
	
	arr2 = new Uint8Array([228, 189, 160, 229, 165, 189]);  !
	decoder.decode(arr2);
	
	
	`,
	s2:`
	
	# TextEncoder
	#   позволяет кодировать строку.
	# Поддерживается только кодировка utf-8.
	# 
	# let encoder = new TextEncoder();
	# 
	# encoder.encode(str)
	#   возвращает бинарный массив Uint8Array, содержащий закодированную строку.
	#   
	# encoder.encodeInto(str, destination)
	#   кодирует строку (str) и помещает её в destination, который должен быть экземпляром Uint8Array.
	#   
	  
	encoder = new TextEncoder(); !

	arr1 = encoder.encode("Hello, 你好");  //Uint8Array
	
	`,

	s4:`
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
		selectedOption: "s3",
		initFunction: ()=>{
			$(".auxPanel").css("height","600px");
		}
	});	
	
});







