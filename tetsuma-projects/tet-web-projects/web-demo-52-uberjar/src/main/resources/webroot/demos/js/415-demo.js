

let selectorsData1 = `


# -------CSS--------
# 
# css(имя) 
#   Возвращает свойство стиля для первого совпавшего элемента.
# 
$("#tc2").css("background-color")

# css(имя, значение)
# css(свойства)
#   Задание css стилей всех элементов набора
# 
$("#tc2").css("background-color", "red")

# addClass(класс)
#   Добавляет указанный(е) класс(ы) к каждому совпавшему элементу.
# 
$("#tc2").addClass("bg-red")

# toggleClass(класс) 
#   Добавляет указанный класс к элементу, если его нет, и удаляет, если он есть
# 
$("#tc2").toggleClass("bg-red")

# hasClass(класс)
#   Возвращает true, если хотя бы один из набора совпавших элементов обладает указанным классом.
# 
$("#tc2").hasClass("bg-red")

# removeClass(класс)
#   Удаляет все или указанный(е) класс(ы) из набора совпавших элементов.
# 
$(".A").removeClass("A")

//получение классов элемента
$("#tc2").attr("class")	




# ------Размеры----------
# 
# height()
# width()
# height(value)
# width(value)
#   размеры внутренней области элемента в пикселях (не включают в себя border и padding)
#   удобнее чем .css("height")
# 
//размеры внутренней области элемента (не включают в себя border и padding)
[$btn.width(), $btn.height()]

//задание размеров	
$btn.width(100).height(100)


# innerHeight()
# innerWidth()
# innerHeight(value)
# innerWidth(value)
#   внутренние размеры (исключая border, но включая padding)
# 
//внутренние размеры (исключая border, но включая padding)
[$btn.innerWidth(), $btn.innerHeight()]

//задание размеров	
$btn.innerWidth(100).innerHeight(100)


# outerHeight(includeMargin = false)
# outerWidth()
# outerHeight(value)
# outerWidth(value)
#   внешние размеры (включая padding, border и опционально margin)
# 
[$btn.outerWidth(), $btn.outerHeight()]

//задание размеров	
$btn.outerWidth(100).outerHeight(100)

# --------Позиционирование----------
# 
# offset()
#   позиция первого элемента относительно документа
# 
$btn.offset()


# position()
#   позиция первого элемента относительно родителя
# 
$btn.position()

# element.getBoundingClientRect()
#   внешние размеры (без учёта margin) и положение относительно документа
# 
//те же цифры что и $btn.offset(),  [$btn.outerWidth(), $btn.outerHeight()]
$btn.get(0).getBoundingClientRect()


# scrollTop()
# scrollLeft()
# scrollTop(значение)
# scrollLeft(значение) 
#   Значение прокрутки
#   
$log1.parent().scrollTop()
	

`;


$(() => {
  

  initBriefDemo(	{
  	demoType: DT_SELECTORS,
  	workPanelTemplate: "../fragments/selectorsSandbox1.html",
  	selectorsData: selectorsData1,
	jquerySelectorsMode: true,
	nextPage: "416_jquery.html",
		$btn = $(".B>button.c5");
	},
  	initFunction: ()=>{
  		
  	}
  });	
  
  
});


