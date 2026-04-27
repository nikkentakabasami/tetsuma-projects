

let selectorsData1 = `

	
	//--------вставка внутрь--------
	
	//вставляет код/кнопку внутрь панели А, возвращает панель А
	$(".A").append(testHtmlSnippet)
	$(".A").append($btn)

	//вставляет кнопку внутрь панели А (в начало), возвращает панель А
	$(".A").prepend($btn)
	
	//вставляет код внутрь панели А, возвращает testHtmlSnippet
	$(testHtmlSnippet).appendTo(".A")
	
	//вставляет .c1 внутрь панели А, возвращает панель .c1
	$(".B>.c1").appendTo(".A")
	
	//вставляет .c1 внутрь панели А (в начало), возвращает .c1
	$(".B>.c1").prependTo(".A")
	
	//--------вставка снаружи--------
	
	//вставляет $btn после панели А, возвращает панель А
	$(".A").after($btn)
	
	//вставляет $btn до панели А, возвращает панель А
	$(".A").before($btn)
	
	//вставляет $btn после панели А, возвращает кнопку
	$btn.insertAfter(".A")
	
	//вставляет $btn до панели А, возвращает кнопку
	$btn.insertBefore(".A")

	//--------обёртывание--------
		
	//Заключает $btn (каждый элемент в наборе) в конструкцию HTML.
	$btn.wrap(greenBorderDivSnippet)
	
	//Заключает $btn (каждый элемент в наборе) в конструкцию HTML.
	//Если задать контейнер через селектор - контейнер будет клонирован
	$btn.wrap(".A>.c1")
	
	//Заключает каждый элемент в наборе в конструкцию HTML.
	$(".A>*:lt(3)").wrap(greenBorderDivSnippet)
	
	//Заключает все элементы в наборе в единственную конструкцию HTML.
	$(".A>*:lt(3)").wrapAll(greenBorderDivSnippet)
	
	//Заключает содержимое каждого элемента в наборе в конструкцию HTML.
	$(".workPanel>*").wrapInner(greenBorderDivSnippet)

	//--------Замена--------
	
	//Производит замену всех элементов элементами HTML или DOM. 
	$(".workPanel>*").replaceWith(testHtmlSnippet)

	//то же что и replaceWith, но инвертировано: код слева будет заменять элементы справа
	$(testHtmlSnippet).replaceAll(".workPanel>*")

	//заменяет кнопками дочерние элементы .workPanel
	$btn.replaceAll(".workPanel>*")

	//--------Удаление--------
		
	//Удаляет содержимое из каждого элемента в наборе
	$(".workPanel>*").empty()
	
	//Удаляет все совпавшие элементы из DOM (и все обработчики событий).
	//НЕ удаляет элементы из объекта jQuery, позволяя Вам воспользоваться ими позже.
	$btn.remove()
	$btn.remove().appendTo(".A")
		
	//--------Копирование--------
	
	$btn.clone().appendTo(".A")

	

`;


$(() => {
  
	

  initBriefDemo(	{
  	demoType: DT_SELECTORS,
  	workPanelTemplate: "../fragments/selectorsSandbox1.html",
  	selectorsData: selectorsData1,
  	title: "dom manipulation",
	jquerySelectorsMode: true,
	afterSandboxReload: ()=>{
		$btn = $(".B>button.c5");
	},
  	initFunction: ()=>{
		log2(selectorsData1.trim());
		highlightLogComments2();
  	}
  });	
  
  
});


