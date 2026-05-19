/**
 * функционал, связанный с парсингом кода из options.selectorsData
 * 
 * 
 * parseMainSelectorsData(sd) - если демки заданы строкой - он разбивает её на 2 массива
 */



//выбранная демо функция/код/селектор
let currentFunc = null;

//распарсенный код currentFunc в виде объекта ParsedScript
let parsedScript = null;




//данные первого селекта
let mainData = null;

//комменты, привязанные к mainData
let mainDataComments = null;



function parseShortScript(comment, exp){

	let r = new ParsedScript();
	r.sourceCode = exp;
	
	r.setComm();
	r.addLine(line);
	r.newPart();
	
	r.setCode();
	r.addLine(exp);
	r.newPart();

	return r;	
	
}

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
		
		//дококоммент: # 
//		ind = line.search(/(?<=^\s*)#/);
//		if (ind>=0){
		if (tl.startsWith("#")){
			if (!r.isDoc()){
				r.newPart();
				r.setDoc();
			}
			r.addLine(tl.length>2?tl.substring(2):"");
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
			r.addLine(line);
						
		}
		
		
		


	});

	r.newPart();
	return r;		
	
	
}




class ParsedScript {
	
	//исходный код (скрипта или функции), который был распарсен
	//из этого кода уже были убраны лишние элементы и пробелы.	
	sourceCode;
	
	parts = [];
	
	currPart = null;
	
	constructor(){
		this.currPart = new ScriptPart(0);
	}
	
	
	newPart(){
		if (this.currPart.lc>0){
			this.parts.push(this.currPart);
			this.currPart = new ScriptPart(0);
		}
	}
	
	addLine(line){
		this.currPart.addLine(line);
	}

	
	//-------задание типа текущего блока--------
		
	isDoc(){ return this.currPart.type==ST_DOC;}
	setDoc(){this.currPart.type=ST_DOC;}
	isMdoc(){ return this.currPart.type==ST_DOC_MULT;}
	setMdoc(){this.currPart.type=ST_DOC_MULT;}
	isComm(){return this.currPart.type==ST_COMMENT;}
	setComm(){this.currPart.type=ST_COMMENT;}
	isCode(){return this.currPart.type==ST_CODE;}
	setCode(){this.currPart.type=ST_CODE;}
	isMcode(){return this.currPart.type==ST_CODE_MULT;}
	setMcode(){this.currPart.type=ST_CODE_MULT;}
	
	
	//Выводит код в отформатированном виде (с подсветкой элементов)
	formatCode(){
		return this.parts.map(p=>p.formatCode()).join("\n\n");
	}
	
		
	toString(){
		
		let r = "";
		this.parts.forEach(p=>{
			r+=p.toString();
		})
		return r;
		
	}
	
	
	
}


const ST_CODE = 1;			//одно или многострочный код. Может содержать комменты в конце строк.
const ST_CODE_MULT = 2;		//многострочный код.
const ST_COMMENT = 3;		//однострочный коммент (//)
const ST_DOC_MULT = 4;		//многострочный коммент-документация (/* */)
const ST_DOC = 5;			//коммент-документация (# ) (тоже может занимать несколько строк)

const sp_gray = '<span class="gray">';
const sp_green = '<span class="green">';
const sp_blue = '<span class="blue">';
const sp_end = '</span>';


class ScriptPart {
	type = 0;
	text = "";
	lc = 0;	//число строк
	
	//выражение заканчивается ! - результат выводить не нужно
	logResult = true;
	
	//выражение заканчивается ~ - выводить результат как JSON
	logAsJson = false;
	
	constructor(type){
		this.type = type;
	}
	
	addLine(line){
		let emptyLine = line.length==0;
		//игнорируем ведущие пустые строки
		if (this.lc==0 && emptyLine){
			return;
		}
		
		if (this.lc>0){
			this.text+="\n";
		}
		if (!emptyLine){
			this.text+=line;
		}
		this.lc++;
	}
	
	toString(){
		return this.type+":\n"+this.text+"\n";
	}	
	
	//Выводит код в отформатированном виде (с подсветкой элементов)
	formatCode(){
		
		let r = this.text;
		switch (this.type){
		case ST_CODE:
		case ST_CODE_MULT:
			r = sp_blue + r + sp_end;
			break;
		case ST_COMMENT:
			r = sp_gray + r + sp_end;
			break;
		case ST_DOC_MULT:
		case ST_DOC:
			r = sp_green + r + sp_end;
			break;
		default:
		}
		return r;
	}
	
	
	
}





/*


*/



//если демки заданы строкой - он разбивает её на 2 массива: mainData - демки и mainDataComments - связанные с ними комменты
//jquerySelectorsMode, regexpMode
function parseMainSelectorsData(selectorsData){

	mainData = selectorsData;
	mainDataComments = null;
	

	//демки заданы одной большой строкой
	if (typeof selectorsData == "string") {

		mainDataComments = [];
		mainData = [];

		let lines = selectorsData.split("\n");

		let ind = 0;
		let currExp = null;
		let currComment = null;
		
		lines.push("");
		lines.forEach(line=>{
			
			line = line.trim();
			
			//пустая строка
			if (!line){
				if (!currExp){
					return;
				}
				mainData[ind] = currExp;
				mainDataComments[ind] = currComment;
				
				currExp = null;
				currComment = null;
				ind++;
				return;
				
			}

			if (line.startsWith("//") || line.startsWith("# ") || line=="#"){
				if (currComment){
					currComment+="\n"+line;					
				} else {
					currComment = line;
				}
			} else {
				if (currExp){
					currExp+="\n"+line;					
				} else {
					currExp = line;
				}
			}
			
		});//for lines
			
	}
	

	
} 



