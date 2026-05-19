/**
 * Функционал, использующийся для создания демок, тестирующих js.
 * 
 * Методы для вывода сообщений в лог:
 * 
stringifyObject(o, indent = "", withBraces = false)
logMessage($log, ...vals)

log(...vals)
log2(...vals)
log3(...vals)

_le($log, exp)
le(exp)
le2(exp)
le2nl(exp)

logParsedExpression(pe)

_lf($log, func) - //выводит в лог код функции, выполняет её, выводит в лог результат
lf(func)
lf2(func)

clearLog()
clearLog1()
clearLog2()

loghr()
log2hr()

logTitle(title)
logTitle2(title)

logTextFragment(text, title="found fragment")
logTextSample(text, title)
logTextSample2(text, title)

log2Blue(val)
log2Gray(val)
log2Green(val)

 * 
 * 
 * 
 */

//панели-логи. В них выводятся сообщения.
let $log1, $log2, $log3;

//панель с $log1, $log2...
let $logPanel;



//преобразовывает объекты в строки, с форматированием, для вывода в лог
function stringifyObject(o, indent = "", withBraces = false) {

	if (o==null){
		return "";
	}
		
	let t = (typeof o);
	if (t=="string" || t=="number" || t=="boolean"){
		return o;
	}
	
	if (t == 'function') {
		return String(o);		
	}
	
	
	//dom-объект
	if (o instanceof Element){
		return o.outerHTML;
	}

	//async функция
	if (o instanceof Promise){
		return "";
	}
		
	let result = "";
	
	if (o instanceof Map){
		o = Array.from(o);		
	}
	if (o instanceof Set){
		o = Array.from(o);		
	}
	if (o instanceof Date){
		return accordUtils.formatDateTime(o);
	}
	
	if (ArrayBuffer.isView(o)){
		return String(o);		
	}	
	
	if (o._isAMomentObject){
		return o.format('DD.MM.YYYY, hh:mm:ss');
	}	
	
	
	if (o instanceof RegExp){
		return String(o);		
	}

	if (t == 'bigint') {
		return String(o);		
	}
			
	
	
	if ( (t == 'object') && (!Array.isArray(o)) && !demoOptions.logObjectsAsJson ) {
		
		if (withBraces){
			result = indent+"{";
		}
		
		let first = true;
		for (let key in o) {
			let val = o[key];
			
			let valStr;
			
			let t = (typeof val);
			
			if (val instanceof Element){
				valStr = val.tagName+"#"+val.id;
			} else if (val instanceof Date){
				valStr = accordUtils.formatDateTime(val);
			} else if (t == "function"){
				valStr = "func";
			} else if (Array.isArray(val)){
				valStr = JSON.stringify(val);
			} else if (val instanceof Map || val instanceof Set){
				valStr = stringifyObject(val);
			} else if (t == "object"){
				valStr = String(val);
			} else {
				valStr = stringifyObject(val, "  ", withBraces);
			}
			
			if (!first){
				result+="\n";
			}
			
			result+= indent+key + ": " + valStr;
			first=false;
		}
		if (withBraces){
			result = result+"\n"+ indent+"}";
		}
		
	} else {
		result = JSON.stringify(o);
	}
	return result;	
}



//выводит в лог заданное распарсенное выражение
async function logParsedExpression(pe) {
	if (!pe || pe.parts.length==0){
		return;
	}
	
	for(p of pe.parts){

		let exp = p.text.trimRight();
		
		
		if (p.type==ST_COMMENT){
			exp+="\n";
			log2Gray(exp);
			continue;
		}
		if (p.type==ST_DOC || p.type==ST_DOC_MULT){
			exp+="\n\n";
			log2Green(exp);
			continue;
		}
				
				
//		if (p.type==ST_CODE || p.type==ST_CODE_MULT){
//		}

		try {
			
			let ind = exp.indexOf("//");
			if (ind>0 && exp[ind-1]!=":"){
				log2Blue(exp.substring(0,ind));
				log2Gray(exp.substring(ind));
			} else {
				log2Blue(exp);
			}
			log2();
			
			//вычисляем выражение
			let val = eval(exp);
			
			if (val!=null && p.logResult){
				
				//с промисами - результат придётся выводить в конце
				if (val instanceof Promise){
					log2();
					val = await val;

					if (!val){
						continue;
					}
					log2();
					
					//выводим значения промисов в конце блока:				
					log2Blue(exp);
					log2(val, "\n");
					continue;
				}
				
				if (p.logAsJson){
					val = JSON.stringify(val);
				} else if (typeof val === "string") {
					val = '"'+val+'"';
				}
				log2(val, "\n");
				continue;
			} else {
				log2();
			}
		} catch (err) {
		  console.error('Произошла ошибка:', err.message);
		  console.error('exp:', exp);
		  console.error('Стек вызовов:', err.stack);
			log2('Произошла ошибка:', err.message);
			return;
		}
		
	}//for
	
	
}



//выводит в лог заданное выражение, выполняет его через eval(), выводит в лог результат
async function _le($log, exp, blockMode = false) {
	if (!exp){
		return;
	}
	
	//многострочное выражение
	if (!blockMode && exp.includes("\n")){
		let lines = exp.split("\n");
		
		let multiLine = "";
		let multiMode = false;
		let multiCommentMode = false;
		let si = 0;  //чтобы убрать ведущие пробелы
		
		
		
		lines.forEach(line=>{

			//ищем и просто выводим многострочные комменты
			let ind = line.search(/^\s*\/\*/);
			if (ind>=0){
				multiCommentMode = true;
				logMessage($log, line);
				return;
			}
			
			if (multiCommentMode){
				ind = line.search(/^\s*\*\//);
				if (ind>=0){
					multiCommentMode = false;
				}
				logMessage($log, line);
				return;
			}
						
			
			ind = line.indexOf("@"); 
			let at = ind>=0; 	//строка начинается с @ - включаем многострочный режим
			if (at){
				//действительно ли @ - первый символ?
				ind = line.search(/^\s*@/);
				at = (ind>=0);
			}

			//многострочные выражения окружены собачками
			if (multiMode){
				if (at){
					multiMode = false;
					si = 0;
					
					if (line.indexOf("!")>0){
						multiLine+=" !";
					}
					
					_le($log, multiLine, true);
					
				} else {
					multiLine+=line.substring(si)+"\n";
				}
				return;
			}
			
			//встретилась собака - включаем многострочный режим.
			if (at){
				multiMode = true;
				multiLine = "";
				si = ind;
				return;
			}

			_le($log, line);
			
		});
		log2hr();
		return;
	};
	
	//------однострочное выражение-------
	exp = exp.trim();

	//коммент	
	if (!blockMode && exp.startsWith("//")){
		let codeNode = logMessage($log, exp);
		return;
	}
	
	//коммент-документация
	if (!blockMode && exp.startsWith("#")){
		if (exp.length<=2){
			exp = "";
		}
		logMessage($log, exp);
		return;
	}
	
	try {
		let showResult = true;
		let resultAsJson = false;
				
		//выражение заканчивается ! - результат выводить не нужно
		if (exp.endsWith("!")){
			showResult = false;
			exp = exp.slice(0,-1).trim();
		}
		
		//выражение заканчивается ~ - выводить результат как JSON
		if (exp.endsWith("~")){
			resultAsJson = true;
			exp = exp.slice(0,-1).trim();
		}
		
		//выводим в лог выражение и подкрашиваем его голубым
		let codeNode = logMessage($log, exp);
		if (codeNode.nodeValue.trim().length){
			$(codeNode).wrap(blueSpan);
		}
		
		
		//вычисляем выражение
		let val = eval(exp);
		if (val!=null && showResult){
			
			//с промисами - результат придётся выводить в конце
			if (val instanceof Promise){
				logMessage($log);
				val = await val;

				if (!val){
					return;
				}
				
				//выводим значения промисов в конце блока:				
				let codeNode = logMessage($log, exp);
				if (codeNode.nodeValue.trim().length){
					$(codeNode).wrap(blueSpan);
				}
				logMessage($log, val, "\n");
				return;
			}
			
			if (resultAsJson){
				val = JSON.stringify(val);
			} else if (typeof val === "string") {
				val = '"'+val+'"';
			}
			logMessage($log, val, "\n");
			return val;
		} else {
			logMessage($log);
		}
	} catch (err) {
	  console.error('Произошла ошибка:', err.message);
	  console.error('exp:', exp);
	  console.error('Стек вызовов:', err.stack);
		log2('Произошла ошибка:', err.message);
		return;
	}
	
}
function le(exp) {
	return _le($log1, exp)
}
function le2(exp) {
	return _le($log2, exp)
}
function le2nl(exp) {
	log2();
	return _le($log2, exp)
}



//очистка логов
function clearLog() {
	$log1.text("");
	$log2.text("");
}
function clearLog1() {
	$log1.text("");
}
function clearLog2() {
	$log2.text("");
}

//вывод в логи
function log(...vals) {
	logMessage($log1, ...vals);
}
function log2(...vals) {
	logMessage($log2, ...vals);
}
function log3(...vals) {
	logMessage($log3, ...vals);
}
function log2nl(...vals) {
	log2();
	log2(...vals);
}
function lognl(...vals) {
	log();
	log(...vals);
}






//выводит в лог код заданной функции, выполняет её, выводит в лог результат функции
function _lf($log, func) {
	let code = accordUtils.funcToString(func,true);
	
	//ищем первую строку без комментов
	let ind = code.search(/^[^\/#]{3}/gm);	
	
	let codeNode;
	if (ind>0){
		logMessage($log, code.substring(0,ind));
		codeNode = logMessage($log, code.substring(ind));
	} else {
		codeNode = logMessage($log, code);
	}
	$log.append("<hr>");
		
	//выделяем код синим
//	$(codeNode).wrap(blueSpan);
	
	let val = func();
	if (val!=null){
		logMessage($log, "\n", val);
		return val;
	}
}
function lf(func) {
	return _lf($log1, func);
}
function lf2(func) {
	return _lf($log2, func);
}
function lf2nl(func) {
	log2();
	return _lf($log2, func);
}

//вывод ссылки
function la2(href, mess) {
	$log2.append(`<a href="${href}" target="logref">${mess}</a> \n`);
}




//Показ функции в логе
function logFuncCode(f, withHr = false){
	let code = accordUtils.funcToString(f);
	log(code);
	if (withHr){
		loghr();
	}
}
function logFuncCode2(f, withHr = false){
	let code = accordUtils.funcToString(f);
	log2(code);
	if (withHr){
		log2hr();
	}
}


function log2Blue(val) {
	val = stringifyObject(val);
	val = sp_blue+val+sp_end;
	$log2.append(val);
}

function log2Gray(val) {
	val = stringifyObject(val);
	val = sp_gray+val+sp_end;
	$log2.append(val);
}
function log2Green(val) {
	val = stringifyObject(val);
	val = sp_green+val+sp_end;
	$log2.append(val);
}


function logMessage($log, ...vals) {
	
	let line = vals.map(v=>stringifyObject(v)).join(" ");
	
	line = line+"\n";
	
	//чтобы избавиться от спецсимволов
	let lineNode = document.createTextNode(line)
	
	$log.append(lineNode);

	if (demoOptions.autoscrollLog1 && $log==$log1 || demoOptions.autoscrollLog2 && $log==$log2){
		
		let $p = $log.parent();
		//scroll to bottom	
		var h = $p.prop('scrollHeight');
		$p.scrollTop(h);
	}

	return lineNode;
}



function logTitle(title){
	log("----------"+(title?title:"")+"------------");
}
function logTitle2(title){
	log2("----------"+(title?title:"")+"------------");
}

//выводит в лог фрагент найденного текста
function logTextFragment(text, title="found fragment"){
	log2("----------"+(title?title:"")+"------------");
	log2(text);
	log2("----------------------");
}

function logTextSample(text, title){
	log("----------"+(title?title:"")+"------------");
	log(text);
	log("----------------------");
}
function logTextSample2(text, title){
	log2("----------"+(title?title:"")+"------------");
	log2(text);
	log2("----------------------");
}




function loghr(){
	$log1.append("<hr>");	
}
function log2hr(){
	$log2.append("<hr>");	
}


function initDemoLogs(){
	$log1 = $('#log1');
	$log2 = $('#log2');
	$log3 = $('#log3');
	$logPanel = $('.logPanel');
}


$(function() {
	
	initDemoLogs();
	
});
