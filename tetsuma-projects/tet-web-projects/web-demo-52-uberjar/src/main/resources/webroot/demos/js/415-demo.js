

let selectorsData1 = `

	//css
	$("#tc2").css("background-color")
	
	//css
	$("#tc2").css("background-color", "red")

	//получение классов элемента
	$("#tc2").attr("class")	

	//задание класса	
	$("#tc2").addClass("bg-red")
	
	//задание/удаление класса	
	$("#tc2").toggleClass("bg-red")
	
	//Возвращает true, если хотя бы один из набора совпавших элементов обладает указанным классом.
	$("#tc2").hasClass("bg-red")
	$(".A").removeClass("A")
	
	//размеры внутренней области элемента (не включают в себя border и padding)
	[$btn.width(), $btn.height()]

	//внутренние размеры (исключая border, но включая padding)
	[$btn.innerWidth(), $btn.innerHeight()]

	//outerHeight( includeMargin = false)
	//внешние размеры (включая padding, border и опционально margin)
	[$btn.outerWidth(), $btn.outerHeight()]

	//позиция первого элемента относительно документа
	$btn.offset()

	//позиция первого элемента относительно родителя
	$btn.position()
	
	//задание размеров	
	$btn.width(100).height(100)
	$btn.innerWidth(100).innerHeight(100)
	$btn.outerWidth(100).outerHeight(100)

	
	//внешние размеры (без учёта margin) и положение относительно документа
	//те же цифры что и $btn.offset(),  [$btn.outerWidth(), $btn.outerHeight()]
	$btn.get(0).getBoundingClientRect()
	
	

`;


$(() => {
  

  initBriefDemo(	{
  	demoType: DT_SELECTORS,
  	workPanelTemplate: "../fragments/selectorsSandbox1.html",
  	selectorsData: selectorsData1,
  	title: "css, classes, положение, размеры",
	jquerySelectorsMode: true,
	afterSandboxReload: ()=>{
		$btn = $(".B>button.c5");
	},
  	initFunction: ()=>{
  		
  	}
  });	
  
  
});


