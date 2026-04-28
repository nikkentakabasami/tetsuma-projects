

let selectorsData1 = `


# attr(name)
#   получение значения атрибута
#   Если элементов в наборе несколько - будет использован первый
# 
$(".A>.c2").attr("title")

# attr(key, value)
#   Задаёт атрибут для каждого совпавшего элемента.
# 
$(".A>.c2").attr("title","my new title")

# removeAttr(имя) 
#   Удаляет указанный атрибут из каждого совпавшего элемента.
# 
$(".A>.c2").removeAttr("title")



# -------properties---------
#   Методы prop почти всегда могут заменить attr.
#   Они появились в версии 1.6, предпочтительнее использовать их.
#   Они гораздо удобнее, когда нужно задавать атрибуты типа checked или disabled.
# 
# prop(propertyName)
# prop(propertyName, value)
#   Получение и задание проперти.


$(".A>.c2").prop("title","my new title")

$(".A>.c2").prop("title")

$(".B>button").prop("disabled",true)

$(".B>#vehicle").prop("checked",false)

//можно задать сразу несколько атрибутов
$(".B>button").prop({disabled: true, title: "new title"})



# html()
#   возвращает строку, содержащую HTML (innerHTML) первого элемента в наборе (его содержимое)
# 
$(".A>.c4").html()

//возвращает строку, содержащую HTML элемента
$(".A>.c4").prop("outerHTML")


# html(val)
#   Задаёт innerHTML для каждого совпавшего элемента. 
# 
$(".A>.c4").html("<b>hello</b>")

$(".A>.c1").html($(".A>.c4").html())



# text() 
#   совмещённое текстовое содержание всех элементов в наборе (включая их потомков)
# 
$(".A>.c4").text()


# text(val)
#   Задание текста во все совпавшие элементы.
#   При этом корректно обработает служебные символы
# 
//задание текстового содержимого
$(".A>.c2").text("my new text")

$(".A>.c1").text($(".A>.c4").html())


# val()
#   возвращает содержимое атрибута value для первого элемента ввода в наборе.
# 
//возвращает содержимое атрибута value для первого элемента ввода в наборе.
$(".B>input:checkbox").val()

# val(val)
#   Устанавливает значение атрибута value для каждого совпавшего элемента в наборе.
# 
//задание value
$(".B>input:checkbox").val("train").val()


# .data(key, value)
# .data(obj)
#   Позволяет сохранять в элементе любые данные (и позже получать их обратно)
# 

//Позволяет сохранять в элементе любые данные (и позже получать их обратно)
$(".A>.c1").data()

$(".A>.c1").data("f1")

$(".A>.c1").data("f1","new data").data()


# removeData(key)
#   Удаляет данные заданные через data()
#   Не удаляет данные, заданые атрибутами - их нужно удалять через removeAttr

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


