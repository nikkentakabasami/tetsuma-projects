

let selectorsData1 = `

//все кнопки
.workPanel button

//одновременно имеет классы acc-btn и c5
.workPanel button.acc-btn.c5

//имеет класс c1, находится внутри div.А
.A .c1

//имеет класс c1, находится непосредственно внутри div.А
.A>.c1

//все элементы внутри div.А
.A *

//все дочерние элементы div.А
.A>*
	
//объединение двух селекторов
.A .c1, .B>.c1

//элемент стоящий после div.c1
.A>.c1 + *

//все элементы стоящие после div.c2 (но имеющие того же родителя)
.A>.c2 ~ *

//-----------------по атрибутам--------------------------

//поиск по наличию атрибута
.A *[title]
	
//поиск по значению атрибута. Значение можно задавать и без скобок.
.A>div[title='c1_title'], .B *[title=c3_title]

//значение атрибута начинается со значения
.workPanel *[title^='c1']

//значение атрибута заканчивается значением
.workPanel *[title$='le']

//значение атрибута содержит значение
.workPanel *[title*='2']

//с заданием двух условий по атрибутам
.A>*[title*=1][id=ac1]

//Соответствует всем элементам input, select, button
.workPanel :input

//
.workPanel :hidden

`;



$(document).ready(function() {

	initBriefDemo(	{
	demoType: DT_SELECTORS,
	workPanelTemplate: "fragments/ss1.html",
	selectorsData: selectorsData1,
	title: "селекторы: основы, атрибуты",
	jquerySelectorsMode: true,
	afterSandboxReload: ()=>{
		$btn = $(".B>button.c5");
	},
	initFunction: ()=>{
		
	}
	});	
	
});



