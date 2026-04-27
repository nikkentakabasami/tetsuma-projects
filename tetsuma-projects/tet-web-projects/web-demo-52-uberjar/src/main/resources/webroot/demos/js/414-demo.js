

let selectorsData1 = `

	$(".A>.c2").attr("title","my new title")
	
	$(".A>.c2").attr("title")
	$(".A>.c2").removeAttr("title")

	//	Методы prop почти всегда могут заменить attr. Предпочтительнее использовать их.
	$(".A>.c2").prop("title","my new title")
	
	$(".A>.c2").prop("title")
	$(".B>button").prop("disabled",true)
	$(".B>#vehicle").prop("checked",false)
	
	//можно задать сразу несколько атрибутов
	$(".B>button").prop({disabled: true, title: "new title"})
		
	//возвращает строку, содержащую HTML (innerHTML) первого элемента в наборе (его содержимое)
	$(".A>.c4").html()
	
	//возвращает строку, содержащую HTML элемента
	$(".A>.c4").prop("outerHTML")
	
	//совмещённое текстовое содержание всех элементов в наборе (включая их потомков)
	$(".A>.c4").text()

	//задание текстового содержимого
	$(".A>.c2").text("my new text")
	$(".A>.c1").text($(".A>.c4").html())
	
	//задание html содержимого
	$(".A>.c2").html("my new text")
	$(".A>.c1").html($(".A>.c4").html())
		
	//возвращает содержимое атрибута value для первого элемента ввода в наборе.
	$(".B>input:checkbox").val()
	
	//задание value
	$(".B>input:checkbox").val("train").val()

	//Позволяет сохранять в элементе любые данные (и позже получать их обратно)
	$(".A>.c1").data()
	$(".A>.c1").data("f1")
	$(".A>.c1").data("f1","new data").data()
	
	//Удаляет данные заданные через data()
	//Не удаляет данные, заданые атрибутами.
	$(".A>.c1").removeData("f1").data()
	
	//Удаляет данные заданные через атрибуты
	$(".A>.c1").removeAttr("data-f1").data()
	

`;


$(() => {
  

  initBriefDemo(	{
  	demoType: DT_SELECTORS,
  	workPanelTemplate: "../fragments/selectorsSandbox1.html",
  	selectorsData: selectorsData1,
  	title: "attr,prop,text,html,val",
	jquerySelectorsMode: true,
	afterSandboxReload: ()=>{
		$btn = $(".B>button.c5");
	},
  	initFunction: ()=>{
  		
  	}
  });	
  
  
});


