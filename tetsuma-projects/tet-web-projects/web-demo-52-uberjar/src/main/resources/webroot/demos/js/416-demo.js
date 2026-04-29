

let selectorsData1 = `


# --------Вставка внутрь------------
# 
# $target.append(content)
#   Добавляет content внутрь каждого элемента набора. Добавляемый контент следует за уже существующим.
#   Если вставить существующий элемент - он переползёт из старого места в новое (удалять не надо)
# 
//вставляет код внутрь панели А, возвращает панель А
$(".A").append(testHtmlSnippet)

//перемещает кнопку внутрь панели А, возвращает панель А
$(".A").append($btn)


# $target.prepend(content)
#   Добавляет content внутрь каждого элемента набора(в начало).
# 
//перемещает кнопку внутрь панели А(в начало), возвращает панель А
$(".A").prepend($btn)


# $source.appendTo(target)
#   Добавляет все элементы набора в target, возвращает $source
# 
//вставляет код внутрь панели А, возвращает testHtmlSnippet
$(testHtmlSnippet).appendTo(".A")

//перетаскивает .c1 внутрь панели А, возвращает .c1
$(".B>.c1").appendTo(".A")


# $source.prependTo(target)
#   то же что и appendTo, но добавляет элементы в начало
# 
//перетаскивает .c1 внутрь панели А (в начало), возвращает .c1
$(".B>.c1").prependTo(".A")


# ----Вставка снаружи--------
# 
# $target.after(content)
#   Вставляет content после каждого элемента набора.
# 
//перетаскивает $btn после панели А, возвращает панель А
$(".A").after($btn)

# $target.before(content) 
#   Вставляет контент перед каждым элементом набора.
# 
//перетаскивает $btn до панели А, возвращает панель А
$(".A").before($btn)

# $source.insertAfter(селектор)
# 
//перетаскивает $btn после панели А, возвращает кнопку
$btn.insertAfter(".A")

# $source.insertBefore(селектор)
# 
//перетаскивает $btn до панели А, возвращает кнопку
$btn.insertBefore(".A")



# --------Обертывание-----------
# 
# $target.wrap(html)
# $target.wrap(элемент)
#   Заключает каждый элемент в наборе в конструкцию HTML.
#   Если задать контейнер через селектор - контейнер будет клонирован
#   Возвращает исходный набор элементов!
# 
//Заключает $btn в конструкцию HTML.
$btn.wrap(greenBorderDivSnippet)

//Клонирует .c1 и обёртвает им кнопку
$btn.wrap(".A>.c1")

//Заключает каждый элемент в наборе в конструкцию HTML.
$(".A>*:lt(3)").wrap(greenBorderDivSnippet)



# $target.wrapAll(html)
# $target.wrapAll(элемент)
#   Заключает все элементы в наборе в единственную конструкцию HTML.
# 
$(".A>*:lt(3)").wrapAll(greenBorderDivSnippet)


# $target.wrapInner(html) 
# $target.wrapInner(элемент) 
#   Заключает содержимое каждого элемента в наборе в конструкцию HTML.
# 
$(".workPanel>*").wrapInner(greenBorderDivSnippet)



# 
# ---------Замена------------
# 
# $target.replaceWith(content)
#   Производит замену всех элементов набора элементами HTML или DOM. 
# 
$(".workPanel>*").replaceWith(testHtmlSnippet)

# $content.replaceAll(target)
#   то же что и replaceWith, но инвертировано: код слева будет заменять элементы справа
# 
$(testHtmlSnippet).replaceAll(".workPanel>*")

//заменяет кнопками дочерние элементы .workPanel
$btn.replaceAll(".workPanel>*")


# -------Удаление----------
# 
# empty()
#   Удаляет содержимое из каждого элемента в наборе
# 
$(".workPanel>*").empty()


# remove(выражение) 
#   Удаляет все совпавшие элементы из DOM (и все обработчики событий).
#   НЕ удаляет элементы из объекта jQuery, позволяя Вам воспользоваться ими позже. 
# 
$btn.remove()

$btn.remove().appendTo(".A")



# -------Копирование--------
# 
# clone(withDataAndEvents = false)
#   Клонирует соответствующие элементы DOM и выбирает клонов.
# withDataAndEvents -  копировать и обработчики
# 
$btn.clone().appendTo(".A")
	
`;


$(() => {
  
	

  initBriefDemo(	{
  	demoType: DT_SELECTORS,
  	workPanelTemplate: "../fragments/selectorsSandbox1.html",
  	selectorsData: selectorsData1,
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


