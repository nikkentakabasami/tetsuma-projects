

const testObject1 = {
    name: "some string",
    age: 42,
    fresh: true
};



//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {



    demo1_script: `
		
		//! - не выводить результат выражения
		result = 111;  !
								
		//вычислить и вывести результат
		Object.entries(testObject1);
		
		//~ - вывести результат как json
		Object.fromEntries(testMap1);			  ~
	`,

    demo2_function() {

        lc2("test_comment");

        result = lf2nl(() => {
            return 775 / 33;
        });
        le2nl("Math.round(result*2)");


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

}




$(document).ready(function() {



	////DT_BUTTONS, DT_SELECTORS
	//DT_SELECT_NO_WP - no sandbox
	//TEMPLATE_FORM2 - пустая форма
	initBriefDemo(	{
		demoType: DT_SELECT, 
		workPanelTemplate: TEMPLATE_FORM1,
		selectorsData: selectorsData1,
		//		lfMode: true,
		selectedOption: "demo1_script",
		initFunction: ()=>{
			
		}
	});	
	
	
	
});
