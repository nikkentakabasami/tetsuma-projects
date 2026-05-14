
let promise, r;

let counter = 0;


function doTestOperation(){
	promise = new Promise(function(resolve, reject) {
	  counter++;
	  setTimeout(() => {
		if (counter%2){
			resolve("done "+counter);
		} else {
			reject("some error "+counter);
		}
	  }, 1000);
	  
	});
	return promise;
}

function makeDemoPromise(promiseName, timeout, withError = false){
	return new Promise(function(resolve, reject) {
	  setTimeout(() => {
		if (withError){
			reject("some error happened with "+promiseName);
		} else {
			resolve(promiseName+" result.");
		}
	  }, timeout);
	  
	});
}

//загрузка скриптов через прописы
function loadScript(src) {
  return new Promise(function(resolve, reject) {
    let script = document.createElement('script');
    script.src = src;

    script.onload = () => resolve(script);
//    script.onerror = () => reject(new Error(`Ошибка загрузки скрипта ${src}`));
	script.onerror = () => reject(`Ошибка загрузки скрипта ${src}`);

	try {
		document.head.append(script);
	} catch (err) {
		//не ловится.
		reject(err);
	}
	
  });
}

function errorHandler(error){
	log2("promise error:",error);	
}


let selectorsData1 = {


	pf1(){
		//Promise
		//  Класс для выполнения длительных, асинхронных задач, 
		// которые надо отслеживать и получить в конце результат
		//  Сделаны для более читаемого кода, без коллбэков

		//promise.then(onFulfilled, onRejected)
		//  Обработка результата выполнения
		//  Можно вызывать несколько раз, добавляя разные обработчики.
		//  Возвращает Promise, что позволяет создавать цепочку промосов.
		
		
		doTestOperation().then(
			result => {
				log2("promise finished:",result);	
			},
			error => {
				log2("promise error:",error);	
			}
		);
		
		logFuncCode2(doTestOperation, true);
	},
	pf2(){
		

		//Альтернативный способ задания обработчиков	
		doTestOperation()
			.then(result => {
				log2("promise finished:",result);	
			})
			.catch(error => {
				log2("promise error:",error);	
			})
			.finally(() => {
				log2("finally.");	
			})

		logFuncCode2(doTestOperation, true);		
		
	},
	pf3(){
		
		//let promise = Promise.all([...промисы...]);
		//   Возвращает промис, который сработает когда будут завершены все заданные промисы.
		//   Вернёт массив результатов.
		//   Если один из них завершится ошибкой - вернёт ошибку

		Promise.all([
			makeDemoPromise("Porcia",1500),
			makeDemoPromise("Bianka",500),
			makeDemoPromise("Mark",700),
		]).then(result => {
			log2nl("promise finished:",result);	
			
			Promise.all([
				makeDemoPromise("Porcia",1500),
				makeDemoPromise("Bianka",500),
				makeDemoPromise("Mark",700, true),
			]).then(result => {
				log2nl("promise finished:",result);	
			}).catch(error => {
				log2nl("promise error:",error);	
			});
		});
	},
	pf4(){
		
		//let promise = Promise.allSettled([...промисы...]);
		//   Возвращает промис, который сработает когда будут завершены все заданные промисы.
		//   Вернёт массив объектов вида: {"status": "fulfilled","value":"..."}
		//   Если один из них завершится ошибкой - вернёт ошибку

		Promise.allSettled([
			makeDemoPromise("Porcia",1500),
			makeDemoPromise("Bianka",500, true),
			makeDemoPromise("Mark",700),
		]).then(result => {
			log2nl("promise finished:",result);	
		});
	},
	pf5(){
		//Promise.race
		//  ждёт только первый выполненный промис, из которого берёт результат (или ошибку).
		
		Promise.race([
			makeDemoPromise("Porcia",1500),
			makeDemoPromise("Bianka",500),
			makeDemoPromise("Mark",700, true),
		]).then(result => {
			log2nl("promise finished:",result);	
		});
		
	},
	pf6: async function(){
		//await
		//  Ключевое слово await заставит интерпретатор JavaScript ждать до тех пор, пока промис справа от await не выполнится.
		//  После чего оно вернёт его результат, и выполнение кода продолжится.
		//  можно использовать только в функциях с async.
		//  При ошибках - кинет исключение. Ошибки перехватываются через try catch
		//
		//Пока промис не выполнится, JS-движок может заниматься другими задачами: выполнять прочие скрипты, обрабатывать события!
		//Последовательность действий сохраняется только в async-методах!
		
		try {

			r = await makeDemoPromise("Porcia",1500);
			log2nl("promise finished:",r);	

			r = await makeDemoPromise("Mark",700, true),
			log2nl("promise finished:",r);	
						
		} catch (err) {
			log2nl("promise error:",err);	
		}		
		
		
	},
	pf7(){
		
		//async
		//  ключевое слово для функций
		//  отмеченная функция всегда возвращает промис
		//  Значения других типов оборачиваются в завершившийся успешно промис автоматически.

		async function f() {
		  return "async function result.";
		}

		f().then(r=>log2(r));
		
		
		
	},
	pf8: async function(){

				
		//fetch() возвращает промис, который можно ожидать с помощью await.
				
		try {
		  const response = await fetch("../fragments/anchorsSandbox.html");
		  const htmlContent = await response.text();

		  log2(htmlContent);

		} catch (err) {
			log2nl("promise error:",err);	
		}		
		
	},
	pf9(){
		
		//promise.then(onFulfilled, onRejected)
		//  Возвращает Promise, что позволяет создавать цепочку промисов.

		//цепочка позволяет выполнять асинхронные задачи последовательно, без излишней вложенности
				
		//пример с простыми значениями
		makeDemoPromise("Porcia",700)
		.then(result=>{
			log2(result);
			return 10;
		})
		.then(result=>{
			log2(result);
			return result*3;
		})
		.then(result=>{
			log2(result);
			return result*3;
		});


				
	},
	pf91(){

		//пример с длительными промисами
		//добавляем в пример обработчик ошибок errorHandler
		makeDemoPromise("Porcia",700)
		.then(result=>{
			log2(result);
			return makeDemoPromise("bob",800)
		},errorHandler)
		.then(result=>{
			log2(result);
			return makeDemoPromise("Alice",800)
		},errorHandler)
		.then(result=>{
			log2(result);
			return makeDemoPromise("Porcia",500, true)
		},errorHandler)
		.then(result=>{
			log2(result);
		},errorHandler)
		
		
	},
	pf92(){
		
		//последовательная загрузка скриптов
		loadScript("../misc/test_script1.js")
		  .then(script => loadScript("../misc/test_script2.js"),errorHandler)
		  .then(script => loadScript("../misc/test_script3.js"),errorHandler)
//		  .then(script => loadScript("../misc/test_script4.js"),errorHandler);
				
		  logFuncCode2(loadScript, true);
		  
		
		
		
		
	},
		

	
	
	
		
	
}

$(document).ready(function() {

	initBriefDemo(	{
		demoType: DT_SELECT_NO_WP, 
		workPanelTemplate: 0,
		selectorsData: selectorsData1,
		lfMode: true,
		selectedOption: "pf92",
		initFunction: ()=>{
			
		}
	});	
	
});







