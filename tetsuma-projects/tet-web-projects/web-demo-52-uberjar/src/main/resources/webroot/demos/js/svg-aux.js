
let $gridType, $svgScale;
let $svg1,$svg2,$svg3,$svg4;

let $svgs;




function loadHtmlFragmentXHR(fragmentUrl, $target, relativeToAccord = false) {


}


function loadFragments(){
	

	//добавляем панель инструментов, если её нет.	
	let $panel = $("div.acc-button-panel");
	if (!$panel.length){
		$panel = $(`<div class="acc-button-panel"></div>`);
		$panel.prependTo(document.body);
	}

	
		
	let xhr = new XMLHttpRequest();

	let url = "fragments/svg-selects.html"
	xhr.open("GET", url, false); // false для синхронного вызова
	xhr.send();

	if (xhr.status === 200) {

		let $selects = $(xhr.responseText);
		
		$selects.prependTo($panel);

	} else {
	  console.error("Ошибка загрузки");
	}
		
}


//добавляет перед каждым svg панель с его исходником
function showAllSvgDesc(){
	
	$svgs.each((ind,el)=>{
		let $svg = $(el);
		
		let code = $svg.html();

		//убираем множественные переносы и пробелы		
		code = code.replace(/\n{2,}/g,"\n").replace(/\s{2,}/g," ");
		
		//убираем динамически добавляемую сетку
		code = code.replace(/<use class="grid-use".*>/g,"").trim();

		//находим/добавляем панель с исходниками
		let id = el.id+"desc";
		let $desc = $("div."+id);
		if (!$desc.length){
			$desc = $(`<div class="svg-code ${id}"></div>`);
			$svg.before($desc);
		}

		$desc.text(code);
	});
	
	$(".acc-desc-panel").each((ind,el)=>{
		let info = $(el).html().trim();
		$(el).html(info);
	});	
	
}


function loadDemoSvgTemplates(){
	
	let $t = $( ".demoSvgTemplates" );
	if (!$t.length){
		$t = $('<div class="demoSvgTemplates"></div>').appendTo(document.body);
	}
	
	$t.load( "svgs/demo-templates.svg", function() {
	  console.log( "Load was performed." );
	});
}



//Показать заданную сетку на всех svg
//Пример: showGrid("grid-net-20");
function showGrid(gridType){
	
	$svgs.each((ind,el)=>{
			
		let $svg = $(el);
		
		$svg.find(".grid-use").remove();
		$use = document.createElementNS('http://www.w3.org/2000/svg', 'use');
		$use.setAttribute('class',"grid-use");
		$use.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href',"#"+gridType);
	//	$use.setAttribute('transform', 'scale('+gridScale+') translate('+x+',    '+y+')');
		$svg.append($use);  
					
	});
	

	
}



function selectNextOption($select, next = true){
	
	let $opt = $select.find('option:selected');
	
	
	let $nextOption = next?$opt.next('option'):$opt.prev('option');

	if ($nextOption.length) {
	    $select.val($nextOption.val()).trigger('change');
	}	
	
}

function setSvgScale(scale){

	$svgs.each((ind,el)=>{
			
		let $svg = $(el);
		
		let w = $svg.width()/scale;
		let h = $svg.height()/scale;
		$svg.attr("viewBox",`0 0 ${w} ${h}`);
					
	});	
		
}

$(() => {
	
	//загружаем селекты
	loadFragments();
	
	$gridType = $("#gridType");
	$svgScale = $("#svgScale");
	
	$svg1 = $("#svg1");
	$svg2 = $("#svg2");
	$svg3 = $("#svg3");
	$svg4 = $("#svg4");

	$svgs = $("svg[id^='svg']");
	
	
	$svgScale.change(event=>{
		let val = $svgScale.val();
		scale = Number(val);
		setSvgScale(scale);
	});
	$svgScale.trigger("change");
	
		
	
	$gridType.change(event=>{
		let val = $gridType.val();
		showGrid(val);
	});
	$gridType.trigger("change");
	
	showAllSvgDesc();
	loadDemoSvgTemplates();
	
	
	$(document).keydown(e=>{
		
		if (e.keyCode==109){  //-
			selectNextOption($gridType, false)			
		} else if (e.keyCode==107){ //+
			selectNextOption($gridType, true)			
		} else if (e.keyCode==37){ //<-
			selectNextOption($svgScale, false)			
		} else if (e.keyCode==39){ //->
			selectNextOption($svgScale, true)			
		}
		
		
		console.log(e.keyCode);
		
	})
	
	
});


