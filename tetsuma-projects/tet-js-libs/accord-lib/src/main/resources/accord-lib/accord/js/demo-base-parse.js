/**
 * функционал, связанный с парсингом кода из options.selectorsData
 * 
 * 
 * parseStringSelectorsData(selectorsData) - если демки заданы строкой - он разбивает её
 * parseScript(script, funcMode = false)
 * 
 */



//выбранная демо функция/код/селектор
let currentFunc = null;

//распарсенный код currentFunc в виде объекта ParsedScript
let parsedScript = null;

//== demoOptions.selectorsData
//Но если данные заданые одной строкой - тут они разбиты в массив
let mainData = null;



/**
 * Парсит заданный демо-код в объект ParsedScript.
 * 
 * funcMode - задан код функции.
 * 
 */
function parseScript(script, funcMode = false){
	
	const lines = script.split('\n');

	let r = new ParsedScript();
	r.sourceCode = script;
	
	
	const processedLines = lines.forEach(line => {
		
		let ind;
		
		line = line.trimRight();
		let tl = line.trim();
		
		//считывается многострочный коммент: /* */
		if (r.isMdoc()){
		
			//найдено завершение коммента: */
			ind = line.indexOf("*/");
			if (ind>=0){
				
				if (tl.length>2){
					r.addLine(line.substring(0, ind));
				}
				r.newPart();
			} else {
				r.addLine(line);
			}
			return;
				
		} else {
			
			//начало многострочного коммента: /*
			
//			ind = line.search(/(?<=^\s*)\/\*/);
//			if (ind>=0){
			if (tl.startsWith("/*")){
				r.newPart();
				r.setMdoc();
				r.addLine(tl.substring(2));
				return;
			}
			
		}
		
		//считывается многострочный код: @...@
		if (r.isMcode()){
			if (tl.startsWith("@")){
				if (tl.endsWith("!")){
					r.currPart.logResult = false;
				}
				if (tl.endsWith("~")){
					r.currPart.logAsJson = true;
				}
				if (tl.endsWith("$")){
					r.currPart.logAsString = true;
				}
				
				r.newPart();
			} else {
				r.addLine(line);
			}
			return;
				
		} else {
			//начало многострочного коммента: /*
			if (tl=="@"){
				r.newPart();
				r.setMcode();
				return;
			}
		}		
		
		if (tl.startsWith("---")){
			r.newPart();
			r.setHr();
			r.addLine(tl);
			return;
		}
		
		//дококоммент: # 
		if (tl.startsWith("# ")){
			if (!r.isDoc()){
				r.newPart();
				r.setDoc();
			}
			tl = tl.substring(2).trim();
			if (tl.length<=1){
				tl = "";
			} 
			r.addLine(tl);
			return;
		}
		if (tl=="#"){
			if (!r.isDoc()){
				r.newPart();
				r.setDoc();
			}
			r.addLine("");
			return;
		}
		
		
		//дококоммент: //# 
//		ind = line.search(/(?<=^\s*)\/\/#/);
//		if (ind>=0){
		if (tl.startsWith("//#")){
			if (!r.isDoc()){
				r.newPart();
				r.setDoc();
			}
			r.addLine(tl.length>4?tl.substring(4):"");
			return;
		}
				
		//строка с комментом
		if (tl.startsWith("//")){
			if (!r.isComm()){
				r.newPart();
				r.setComm();
			}
			r.addLine(line);
			return;
		}		

		
		//строка с кодом
		if (funcMode){

			//если задана функция - объёдиняем кодовые строки
			if (!r.isCode()){
				r.newPart();
				r.setCode();
			}
			r.addLine(line);
		} else {

			r.newPart();
			r.setCode();
			
			if (line.endsWith("!")){
				line = line.slice(0,-1).trimRight();
				r.currPart.logResult = false;
			}
			if (tl.endsWith("~")){
				line = line.slice(0,-1).trimRight();
				r.currPart.logAsJson = true;
			}
			if (tl.endsWith("$")){
				line = line.slice(0,-1).trimRight();
				r.currPart.logAsString = true;
			}
			
			r.addLine(line);
						
		}
		
		
		


	});

	r.newPart();
	return r;		
	
	
}




//если демки заданы строкой - он разбивает её на массив в mainData
//jquerySelectorsMode, regexpMode
function parseStringSelectorsData(selectorsData){
	
	mainData = selectorsData;

	//демки заданы одной большой строкой
	if (typeof selectorsData != "string") {
		return;
	}
	
	mainData = [];
	
	let lines = selectorsData.split("\n");

	let ind = 0;
	let currExp = null;

	lines.push("");
	lines.forEach(line=>{
		
		line = line.trim();
		
		//пустая строка
		if (!line){
			if (!currExp){
				return;
			}
			mainData[ind] = currExp;
			
			currExp = null;
			ind++;
			return;
			
		}
		
		if (currExp){
			currExp+="\n"+line;					
		} else {
			currExp = line;
		}
		
	});//for lines	

	
} 






