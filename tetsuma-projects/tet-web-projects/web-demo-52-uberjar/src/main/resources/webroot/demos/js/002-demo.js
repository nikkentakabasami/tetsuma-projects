
let a = {};

let testMap1 = new Map([
    [1, "a"],
    [2, "b"],
    [3, "c"],
]);

const testObject1 = {
    name: "some string",
    age: 42,
    fresh: true
};



//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {



    demo1_script: `
		//код для демок задаётся массивом selectorsData1.
		//каждый элемент массива - отдельная демка.
		//демка может быть задана строкой или функцией
			
		//самый универсальный способ - задать весь код демки мульти-строкой.
		//в этом случае код будет интерпретироваться построчно.
		//то же самое делают функции le2, le2nl
		//минус - выражения могут быть только однострочными.
		
		
		//в подобных скриптах можно применять спецсимволы:
		
		//! - не выводить результат выражения
		result = 111;  !
								
		//~ - вывести результат как json
		Object.fromEntries(testMap1);	  ~
		
		//вычислить и вывести результат
		Object.entries(testObject1);
	`,

    demo2_function() {
        //код этой демки задан функцией.

        //lc2 - вывести коммент во второй лог
        lc2("test_comment");

        //le2 - построчно интерпретировать заданный скрипт, выводя результаты во второй лог
        testObject1.name = "Jim";
        le2nl(`
			Object.entries(testObject1);
			
			//создание объекта на основе двумерного массива
			Object.fromEntries(testMap1);  ~		
		`);


        //lf2(func)
        //1) выводит в лог код заданной функции 
        //2) выполняет её 
        //3) результат записывает в result и выводит в лог
        lf2(() => {
            let testArray1 = Array.from("testString");
            for (let f in testArray1) {
                log2(f);
            }
            return testArray1;
        });

        //le2nl, lf2nl - то же что и le2, lf2, но добавляет переход на новую строку
        result = lf2nl(() => {
            return 775 / 33;
        });
        le2nl("Math.round(result*2)");



    },

    demo3_function() {

        //устаревший подход
        a = {};

        //Создание многомерных массивов
        a.arr0 = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]

        //создание на основе строки
        a.arr12 = Array.from("tenka musou");

        //генерация массива
        a.arr13 = Array.from({ length: 5 }, (el, index) => index);

        log2(a);
    },

    test1() {
        //меняем dom
        log2("remove first text field")
        $inp1.remove();
    },

    test2() {
        $inp2.addClass("blue-border");
    },


    test3() {
        $inp2.addClass("bg-blue");

    },
    test4() {
        //шаблон1
        lf2nl(() => {
        });

        le2nl(`
	`);
    },
    test5() {
    },



    test_func1() {
        //функция для работы с dom.
        //возвращает jquery объект. Эти элементы будут выделены рамкой.
        return $(".form-panel input").bind("click", event => {
            log2("inp click.");
        });
    },

    le_err_demo() {
        le2("d1.dosome();");
    },

}





$(() => {
    initDemoCodeSelect("#selectors1", selectorsData1);

    //выбрать опцию после загрузки страницы 
    $("#selectors1").val("le_demo2").trigger("change");

	//код, который будет выполняться перед каждой демо-функцией
	demoOptions.beforeExecDemoFunc = ()=>{
		
		//очищает .workPanel и загружает в неё элементы из #template1
		reloadSandbox();
	};

	//и после неё
	demoOptions.afterExecDemoFunc	= ()=>{
	};
	
    reloadSandbox();

});



