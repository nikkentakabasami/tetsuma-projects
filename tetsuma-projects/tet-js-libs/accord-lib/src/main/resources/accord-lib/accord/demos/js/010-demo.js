

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

	initBriefDemo(	{
		demoType: DT_SELECT,
		workPanelTemplate: TEMPLATE_FORM1,
		selectorsData: selectorsData1,
		selectedOption: "demo1_script",
		title: "mytitle",
		initFunction: ()=>{
			
		}
	});	
	
});
