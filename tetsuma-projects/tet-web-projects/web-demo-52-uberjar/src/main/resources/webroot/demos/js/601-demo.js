



//тестовые функции
//возвращают query-объекты, задействованные в тесте: они будут выделены красной рамкой
let selectorsData1 = {

    test1() {
		/*
		Громоздкий мультиселект
		Сложно настраивать расположение, подглючивает.
		Конфликтует с моими стилями на формы.

		Опции:

		allowClear	(true, false)
		  Допустимы ли пустые значения

		placeholder
		  Текст при пустом значении

		maximumSelectionLength
		  Выбор нескольких опций


		*/
		
		//вид по умолчанию
		$sel1.select2();

		$sel2.select2();
		
		
		//с заданием данных
		log("testSelectData1=",testSelectData1);
		
		$sel3.select2({
		  data: testSelectData1,
		  width: "300px",

		  placeholder: "Select a state",
		  allowClear: true,
		});
				
    },

	test2() {
		
		/*
		Мультивыбор.
		
		Опции:
		
		multiple: true,
		  Возможность выбора нескольких опций
		  
		maximumSelectionLength
		*/		
		
		$sel1.select2({
		  multiple: true,
		  maximumSelectionLength: 4,
		  placeholder: "Select a state",
		  width: "300px",
		});		
		$sel2.select2({
		  multiple: true,
		  maximumSelectionLength: 4,
		  placeholder: "Select a state",
		  width: "300px",
		});
		$sel3.select2({
		  data: testSelectData1,
		  multiple: true,
		  maximumSelectionLength: 4,
		  placeholder: "Select a state",
		  width: "300px",
		});		
		
		
	},
	

}




function getBriefDemoOptions() {
  return {
    demoType: DT_SELECT_SINGLE_LOG,
	workPanelTemplate: "../fragments/select2Sandbox.html",
    selectorsData: selectorsData1,
    lfMode: false,
	reloadSandboxOnChange: true,
    afterSandboxReload: null,
    selectedOption: null,
	autoscrollLog1: true,
    debugMode: false,
    initFunction: () => {
    }
  };
}










