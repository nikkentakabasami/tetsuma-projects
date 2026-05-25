

const ST_CODE = 1;			//одно или многострочный код. Может содержать комменты в конце строк.
const ST_CODE_MULT = 2;		//многострочный код.
const ST_COMMENT = 3;		//однострочный коммент (//)
const ST_DOC_MULT = 4;		//многострочный коммент-документация (/* */)
const ST_DOC = 5;			//коммент-документация (# ) (тоже может занимать несколько строк)
const ST_HR = 6;			//разделитель <hr>  (---)

const sp_gray = '<span class="gray">';
const sp_green = '<span class="green">';
const sp_blue = '<span class="blue">';
const sp_end = '</span>';


class ParsedScript {
	
	//исходный код (скрипта или функции), который был распарсен
	//из этого кода уже были убраны лишние элементы и пробелы.	
	sourceCode;
	
	parts = [];
	
	currPart = null;
	
	//функция-исходник (если этот скрипт задан функцией)
	func = null;
	
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
	isHr(){ return this.currPart.type==ST_HR;}
	setHr(){this.currPart.type=ST_HR;}
	
	
	
	//Выводит код в отформатированном виде (с подсветкой элементов)
	formatCode(){
		return this.parts.map(p=>p.formatCode()).join("\n");
	}

	//Возвращает только код
	getCode(){
		return this.parts.filter(p=>(p.type==ST_CODE || p.type==ST_CODE_MULT)).map(p=>p.text).join("\n");
	}
		
	toString(){
		
		let r = "";
		this.parts.forEach(p=>{
			r+=p.toString();
		})
		return r;
		
	}
	
	
	
}



class ScriptPart {
	type = 0;
	text = "";
	lc = 0;	//число строк
	
	//выражение заканчивается ! - результат выводить не нужно
	logResult = true;
	
	//выражение заканчивается ~ - выводить результат как JSON
	logAsJson = false;

	//выражение заканчивается $ - выводить результат как String
	logAsString = false;
	
		
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
		
		let r = accordUtils.escapeHTML(this.text);
		
		switch (this.type){
		case ST_CODE:
		case ST_CODE_MULT:
			r = sp_blue + r + sp_end+"\n";
			break;
		case ST_COMMENT:
			r = sp_gray + r + sp_end;
			break;
		case ST_DOC_MULT:
		case ST_DOC:
			r = sp_green + r + sp_end+"\n";
			break;
		case ST_HR:
			r = "<hr>";
			break;
		default:
		}
		return r;
	}
	
	
	
}

