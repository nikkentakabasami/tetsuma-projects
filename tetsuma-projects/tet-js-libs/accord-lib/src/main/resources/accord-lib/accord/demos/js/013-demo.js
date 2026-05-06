

let selectorsData1 = `

//слова из русских символов
/[а-яё]+/gi

//слова из символов и чисел
/[а-яё\\w]+/gi

//Просмотр вперёд и назад
/(?<=text_before).+(?=text_after)/g



`;



$(document).ready(function() {

	initBriefDemo(	{
	demoType: DT_REGEXP,
	selectorsData: selectorsData1,
	regexpMode: true,
	sampleText: `ЛюдовикXV, ЛюдовикXVI, ЛюдовикXVIII,
		ЛюдовикV, ЛюдовикVI, ЛюдовикVIII, ЛюдовикLXVII, ЛюдовикXXL
		aaa aaa
		Сергей Иванов, Игорь Иванов
		text_before satori text_after
		1 индейка стоит 30€
		1 индейка стоит $50
		----`,
	
	initFunction: ()=>{
		
	}
	});	
	
});



