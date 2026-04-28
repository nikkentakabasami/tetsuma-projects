

let selectorsData1 = `

# -------Родители, предки-------------
# 
# parent(selector)
#   Возвращает прямого “родителя” элемента. 
#   Можно задать селектор, для фильтрации
# 
$btn.parent()

//с фильтрацией родителей
$(".workPanel .c1").parent(".c3")


# parents(selector)
#   Поиск среди предков.
#   Если вызвать без параметров - вернёт всех предков!
# 
$(".workPanel .c3").parents(".A")


# parentsUntil(selector)
#   Поиск среди предков до элемента с заданным селектором (не включая его)
#   Если вызвать без параметров - вернёт всех предков!
# 
$btn.parentsUntil(".workPanel")

# closest(selector)
#   Поиск среди предков, но включает в поиск текущий элемент, и возвращает не больше одного элемента
# 
//вернёт родителя .c2
$(".C>.c3>.c3>.c2").closest(".c3")


# offsetParent()
#   Возвращает ближайшего позиционированного предка (предка с атрибутом position)
# 
$(".workPanel .c3").offsetParent()


# ----------Дети, потомки----------
# 
# find(selector)
#   Поиск среди потомков
# 
$(".A").find(".c2")

# children(selector)
#   Поиск среди дочерних элементов
# 
$(".A").children(".c2")


# contents()
#   Возвращает все дочерние элементы (включая текстовые и комменты)
# 
$(".A").contents()

//выделит жирным тексты
$(".A").contents().filter(function(){return this.nodeType !== 1;}).wrap(boldTag)


# --------Сиблинги (соседние элементы с тем же родителем)-------------
# 
# next(selector)
#   Следующий сиблинг
# 
$(".A>.c2").next()

# prev(selector)
#   Предыдущий сиблинг
# 
$(".A>.c2").prev()

# nextAll(selector)
# 
$(".A>.c2").nextAll()

# prevAll(selector)
$(".A>.c2").prevAll()

# siblings(выражение)
#   Все соседние элементы
$(".A>.c2").siblings()


`;


$(() => {
  

  initBriefDemo(	{
  	demoType: DT_SELECTORS,
  	workPanelTemplate: "../fragments/selectorsSandbox1.html",
  	selectorsData: selectorsData1,
  	title: "функции: переход",
	jquerySelectorsMode: true,
	afterSandboxReload: ()=>{
		$btn = $(".B>button.c5");
	},
  	initFunction: ()=>{
  		
  	}
  });	
  
  
});


